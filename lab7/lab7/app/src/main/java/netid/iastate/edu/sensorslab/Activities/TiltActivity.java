package netid.iastate.edu.sensorslab.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;
import netid.iastate.edu.sensorslab.Models.TiltCalculator;
import netid.iastate.edu.sensorslab.R;

/**
 * This is the activity for the accelerometer tilt implementation
 */
public class TiltActivity extends AppCompatActivity implements SensorUpdateCallback {
    private TiltCalculator mTilt;//used to store the TiltCalculator object
    private ImageView mArrow;//used for modifying the shown image view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        mTilt = new TiltCalculator(getApplicationContext(), this); //  Instantiate a TiltCalculator object

        mArrow = findViewById(R.id.image);
        mArrow.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.arrow));

        Button leftButton = findViewById(R.id.btn1);
        leftButton.setText(R.string.flat_compass_text);

        Button rightButton = findViewById(R.id.btn2);
        rightButton.setText(R.string.better_compass_text);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTilt.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTilt.stop();
    }

    @Override
    public void update(float orientation) {
        //  Rotate arrow to orientation
        mArrow.setRotation(orientation);
    }

    public void leftButtonClicked(View view){
        startActivity(new Intent(this, FlatCompassActivity.class));
    }

    public void rightButtonClicked(View view){
        startActivity(new Intent(this, BetterCompassActivity.class));
    }

}

