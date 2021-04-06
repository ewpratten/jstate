package ca.retrylife.jstate;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Scheduler is the base class for running tasks
 */
public class Scheduler<State, Context> implements AutoCloseable, Runnable {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // List of child tasks
    private Map<State, Task<State, Context>> children = new HashMap<State, Task<State, Context>>();

    // Context
    private Context context;

    // Current state
    private State currentState = null;
    private State lastState = null;
    private boolean needsToSetUp = false;

    /**
     * Create a scheduler with a context object
     * 
     * @param context Context to be passed between tasks
     */
    public Scheduler(Context context) {
        this.context = context;

        // Set an empty idle task
        this.overrideIdleTask(() -> {
        });

    }

    /**
     * Add a child task
     * 
     * @param key  Task key
     * @param task Task
     */
    public void addChild(State key, Task<State, Context> task) {
        logger.debug(String.format("Adding task: %s", key.toString()));
        this.children.put(key, task);
    }

    /**
     * Remove a child task
     * 
     * @param key Task key
     */
    public void removeChild(State key) {
        logger.debug(String.format("Removing child: %s", key.toString()));
        if (key == null) {
            // Set an empty idle task
            this.overrideIdleTask(() -> {
            });
        } else {
            this.children.remove(key);
        }
    }

    /**
     * Override the idle task with a runnable
     * 
     * @param task Runnable to run when no other tasks are running
     */
    public void overrideIdleTask(Runnable task) {
        this.overrideIdleTask(new Task<State, Context>() {

            @Override
            public void setup(State currentState, State lastState, Context context) {
            }

            @Override
            public State execute(State currentState, State lastState, Context context) {

                // Run the runnable
                task.run();

                return null;
            }

            @Override
            public void close() {
            }

        });
    }

    /**
     * Override the task to run when no other tasks are running
     * 
     * @param task Idle task
     */
    public void overrideIdleTask(Task<State, Context> task) {
        logger.debug("Overriding the idle task");
        this.children.put(null, task);
    }

    public void forceSwitchTask(State newState) {
        logger.debug(String.format("Force-switching task to: %s", newState.toString()));

        // Close the current task
        this.getCurrentTask().close();

        // Change state trackers
        this.lastState = this.currentState;
        this.currentState = newState;
        this.needsToSetUp = true;
    }

    /**
     * Get a reference to the currently running task
     * 
     * @return Current task
     */
    public Task<State, Context> getCurrentTask() {
        // Safety check the current task
        if (!this.children.containsKey(this.currentState)) {
            throw new NoSuchElementException(
                    String.format("State %s is not registered with this scheduler", this.currentState));
        }

        // Get the current task
        return this.children.get(this.currentState);
    }

    @Override
    public void close() {
        logger.debug("Closing");

        // Switch to the idle state
        this.forceSwitchTask(null);

    }

    /**
     * Run one iteration of the scheduler
     */
    @Override
    public void run() {

        // Get the current task
        Task<State, Context> task = this.getCurrentTask();

        // Handle setup if needed
        if (this.needsToSetUp) {
            task.setup(this.currentState, this.lastState, this.context);
        }

        // Execute the current task
        State nextState = task.execute(this.currentState, this.lastState, this.context);

        // If the next state differs, close the task and move on
        if (!nextState.equals(this.currentState)) {
            task.close();
            this.lastState = this.currentState;
            this.needsToSetUp = true;
        }

        // Set the next task
        this.currentState = nextState;
    }

}
