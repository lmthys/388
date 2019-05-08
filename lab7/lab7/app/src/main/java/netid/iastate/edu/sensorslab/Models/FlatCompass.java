package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

/**
 * This class is used for managing a FlatCompass object, we use this to do business logic before updating the UI
 */
public class FlatCompass implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mMagField;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to

    public FlatCompass(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); //  Get the Sensor Service using the application context
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); //  Get a magnetic field sensor
        mCallback = callback;
    }

    /**
     * This method is called from the activity when the sensor listener should be registered
     */
    public void start() {
        //  Register magnetic field sensor listener
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * This method is called from the activity when the sensor listener should be unregistered
     */
    public void stop() {
        //  Unregister magnetic field sensor listener
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;

        // Calculate the orientation
        if((event.values[0] > 0) || (event.values[1] > 0)){
            orientation = (float) (Math.atan2(event.values[0], event.values[1]) * 180 / Math.PI);
        }

        mCallback.update(orientation);//use callback to call update() method in the activity
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //do nothing in our scenario
    }
}