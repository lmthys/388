package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

/**
 * This class is used for managing a TiltCalculator object, we use this to do business logic before updating the UI
 */
public class TiltCalculator implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mAcc;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to

    public TiltCalculator(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); // TODO Get the Sensor Service using the application context
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // TODO Get an accelerometer
        mCallback = callback;
    }

    /**
     * This method is called from the activity when the sensor listener should be registered
     */
    public void start() {
        // Register accelerometer sensor listener
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * This method is called from the activity when the sensor listener should be unregistered
     */
    public void stop() {
        //  Unregister accelerometer sensro listener
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;
        if(event.values[0] > 0 || event.values[1] > 0 || event.values[2] > 0){
            double pitch = Math.atan(event.values[1]/(Math.sqrt(Math.pow(event.values[0], 2) + Math.pow(event.values[2], 2)))); // TODO Determine pitch from accelerometer
            double roll = Math.atan((-event.values[0])/event.values[2]); // TODO Determine roll from accelerometer
            orientation = (float) ((Math.atan2(pitch, roll) * 180) / Math.PI) + 90.0f;
        } //  Determine orientation from pitch and roll
        mCallback.update(orientation);//use callback to call update() in activity
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
