package ca.retrylife.jstate;

/**
 * A Task is a high level descriptor of something that is run by a scheduler.
 */
public interface Task<State, Context> extends AutoCloseable {

    /**
     * This is run once, any time this task has just been switched to from another
     * task. Any reset or setup code needed can be run here.
     * 
     * @param currentState An enum value describing what this task is
     * @param lastState    An enum value of the last task executed, or NULL if no
     *                     last task was defined (this happens if there is no
     *                     default task for a scheduler to run)
     * @param context      The context of the scheduler
     */
    public void setup(State currentState, State lastState, Context context);

    /**
     * This is run periodically while this task is active. It is expected that the
     * return value is the next task to execute. This can also be NULL to return to
     * the default task, or <code>currentState</code> to keep executing the current
     * task. This may be overridden by external callers.
     * 
     * @param currentState An enum value describing what this task is
     * @param lastState    An enum value of the last task executed, or NULL if no
     *                     last task was defined (this happens if there is no
     *                     default task for a scheduler to run)
     * @param context      The context of the scheduler
     */
    public State execute(State currentState, State lastState, Context context);

    /**
     * Called whenever this task has been called for the last time before switching
     * to another task. Use for temporary cleanup
     */
    public void close();

}
