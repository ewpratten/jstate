package ca.retrylife.jstate;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A scheduler that runs in its own thread, at a specific interval
 */
public class ThreadedScheduler<State, Context> extends Scheduler<State, Context> {

    // Internal timer
    private Timer timer;
    private double intervalSeconds;

    // Task to run
    private TimerTask task;

    /**
     * Create a threaded scheduler
     * 
     * @param context         Context to pass between tasks
     * @param intervalSeconds Interval for running run() in seconds
     */
    public ThreadedScheduler(Context context, double intervalSeconds) {
        super(context);

        // Configure a timer for the scheduler
        this.timer = new Timer(true);
        this.timer.cancel();
        this.intervalSeconds = intervalSeconds;

        // Set up the timer task
        this.setTaskFromRunnable(super::run);

    }

    private void setTaskFromRunnable(Runnable runnable) {
        this.task = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }

    /**
     * Start the thread
     */
    public void start() {
        this.start(this.intervalSeconds);
    }

    /**
     * Start the thread with a custom interval
     * 
     * @param intervalSeconds Interval in seconds
     */
    public void start(double intervalSeconds) {
        this.timer.scheduleAtFixedRate(this.task, 0L, (long) (intervalSeconds * 1000));
    }

    /**
     * Stop the thread
     */
    public void stop() {
        this.timer.cancel();
    }

}
