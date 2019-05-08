package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.content.IntentSender;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

/**
 * This class is used for managing a BetterCompass object, we use this to do business logic before updating the UI
 */
public class BetterCompass implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mMagField;//used to get and start/register the Sensor
    private Sensor mAcc;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to
    private float lastOr;
    private float thirdOr;

    private float[] mAccelerometerReading = new float[3];
    private float[] mMagnetometerReading = new float[3];

    public BetterCompass(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); //  Get the Sensor Service using the application context
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); //  Get a magnetic field sensor
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //  Get an accelerometer
        mCallback = callback;
        lastOr = 0.0f;
        thirdOr = 0.0f;
    }

    /**
     * This method is called from the activity when the sensor listeners should be registered
     */
    public void start() {
        //  Register the magnetic field and accelerometer listeners
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * This method is called from the activity when the sensor listeners should be unregistered
     */
    public void stop() {
        // TODO Unregister the magnetic field and accelerometer listeners
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            //  Store magnetic field data in mMagnetometerReading
            mMagnetometerReading = lowPass(event.values, mMagnetometerReading);
        }
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //  Store accelerometer data in mAccelerometerReading
            mAccelerometerReading = lowPass(event.values, mAccelerometerReading);
        }

        float orientation = 0.0f;

        float[] mRotationM = new float[9];
        float[] mOrientAngles = new float[3];
        SensorManager.getRotationMatrix(mRotationM, null, mAccelerometerReading, mMagnetometerReading);
        SensorManager.getOrientation(mRotationM, mOrientAngles);
        if(mOrientAngles[0] != 0)
            orientation = (float) (-mOrientAngles[0] * 180 / Math.PI);

        //  Get orientation from magnetometer and accelerometer
        mCallback.update(orientation);



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing in our scenario
    }

    protected float[] lowPass(float[] input, float[] output){
        if(output == null) return input;

        for(int i=0; i < input.length; i++){
            output[i] = output[i] + 0.2f * (input[i] - output[i]);
        }
        return output;
    }
}
