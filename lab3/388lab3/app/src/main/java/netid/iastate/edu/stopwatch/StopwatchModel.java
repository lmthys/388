package netid.iastate.edu.stopwatch;

import android.os.SystemClock;

import java.sql.Time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A stopwatch that keeps time with simple start, stop, and reset operations.
 */
class StopwatchModel {
    /** Whether the stopwatch is currently running (counting time) or stopped. */
    private boolean mRunning = false;
    private boolean mReset = false;

    // TODO: Create any instance variables you need to track the time.
    long startTime;
    long endTime;
    // Hint: use SystemClock.elapsedRealtime() to track time between start and stop events.

    /**
     * A getter for the stopwatch's current state.
     * @return true if the stopwatch is running; false otherwise
     */
    public boolean isRunning() {
        return mRunning;
    }

    /**
     * Starts the stopwatch counting. This counts from where the stopwatch left off counting, unless
     * it was reset to 0.
     */
    public void start() {
        // TODO
        mRunning = true;
        startTime = new Date().getTime();

    }

    /**
     * Stops the stopwatch's counting, effectively pausing the current time value.
     */
    public void stop() {
        mRunning = false;
    }

    /**
     * Resets the elapsed time to 0.
     */
    public void reset() {
        startTime = 0;
        mReset = true;
    }

    /**
     * Gets the stopwatch's counted time.
     * @return the number of milliseconds that have been counted
     */
    public long getTime() {
        if(mReset){
            mReset = false;
            return 0;
        }
        endTime = new Date().getTime();
        long time = endTime - startTime;
        startTime = endTime;
        return time;

    }
}
