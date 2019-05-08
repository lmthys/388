package netid.iastate.edu.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Stopwatch activity.  This acts as an MVC Controller.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * REFRESH_RATE defines how often we should update the timer to show how much time has elapsed.
     * refresh every 100 milliseconds
     */
    private final int REFRESH_RATE = 100;
    /**
     * The stopwatch model instance that will keep time.
     */
    private StopwatchModel mStopwatch;
    /**
     * A Handler for interfacing with the main thread's MessageQueue. This is not a HandlerThread,
     * because we don't need a background thread. Use this when to post delayed Runnables to the
     * main/UI thread.
     */
    private Handler mHandler;

    TextView timerFront;
    TextView timerBack;

    ViewGroup start;
    ViewGroup stop;

    long currTime;
    long centi;


    // TODO: create instance variables for any View and ViewGroup object you regularly need to
    // access.  Don't forget to set them in onCreate().


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerFront = findViewById(R.id.front);
        timerBack = findViewById(R.id.back);

        start = findViewById(R.id.startResetGroup);
        stop = findViewById(R.id.stopGroup);

        currTime = 0;
        centi = 0;

        mStopwatch = new StopwatchModel();
        // https://developer.android.com/reference/android/os/Handler
        // Creating a new Handler with no arguments binds it to the current thread's event Looper,
        // which is the main thread.
        mHandler = new Handler();
        //updateTimerDisplay();
    }

    /**
     * This method starts the current stopwatch clock.
     *
     * @param view the calling View
     */
    public void onStartClick(View view) {
        mStopwatch.start();
        updateButtonState(view);
        mHandler.post(startTimer);

    }

    /**
     * This method resets the current stopwatch clock to 0.
     *
     * @param view the calling View
     */
    public void onResetClick(View view) {
        mStopwatch.reset();
        currTime = 0;
        centi = 9;
        updateTimerDisplay();
    }

    /**
     * This method will stop the current stopwatch.
     *
     * @param view the calling View
     */
    public void onStopClick(View view) {
        mHandler.removeCallbacks(startTimer);
        mStopwatch.stop();
        updateButtonState(view);
        // TODO
        // You need to stop the recurring UI update events here.  One simple way to do this is to
        // remove any pending Runnables in the UI thread by using mHandler.
        // Using the documentation, you can find an appropriate function:
        // https://developer.android.com/reference/android/os/Handler#removeCallbacks(java.lang.Runnable)
    }

    /**
     * If the stopwatch is running, this method will show the stop button and hide the start and
     * reset buttons.
     * If the stopwatch is not running, this method will hide the stop button and show the start and
     * reset buttons.
     */
    private void updateButtonState(View v) {
        // Hint: On a View or ViewGroup use setVisibility(int)
        // https://developer.android.com/reference/android/view/View#setVisibility(int)
        if (mStopwatch.isRunning()) {
            start.setVisibility(v.GONE);
            stop.setVisibility(v.VISIBLE);
        } else {
            start.setVisibility(v.VISIBLE);
            stop.setVisibility(v.GONE);
        }
    }

    /**
     * Converts the elapsed given time and updates the display
     */
    private void updateTimerDisplay() {
        long timeElapsed = mStopwatch.getTime();
        currTime += timeElapsed;
        // Convert the milliseconds, seconds, minutes, and hours to String and format to ensure it
        // has leading zeros where required.  A good way to do this is use String.format().
        // From the documentation: https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
        // A format specifier has the syntax:  %[flags][width]conversion
        //Date c = new Date(timeElapsed);
        //DateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
        //String display = f.format(c);
        //String.format("%T",timeElapsed);
        // Set the timer display text to the elapsed time.
        String s = String.format(getString(R.string.hours_mins_secs), TimeUnit.MILLISECONDS.toHours(currTime),
                TimeUnit.MILLISECONDS.toMinutes(currTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(currTime) % TimeUnit.MINUTES.toSeconds(1)
                );
        centi++;
        if(centi == 10){
            centi = 0;
        }
        String s2 = Long.toString(centi);
        timerFront.setText(s);
        timerBack.setText("."+s2);

    }

    /**
     * Create a Runnable startTimer that makes timer runnable.
     */
    private Runnable startTimer = new Runnable() {
        public void run() {
            updateTimerDisplay();
            mHandler.postDelayed(startTimer, REFRESH_RATE);
            // TODO: Update the UI and trigger this runnable again in the future.
        }
    };
}
