package netid.iastate.edu.gestures;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.widget.RelativeLayout;

/**
 * Activity which represents the right view.
 */
public class SecondActivity extends CustomGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setLeftRight(MainActivity.class, ThirdActivity.class);
        ConstraintLayout c = findViewById(R.id.secondActivity);
        Bundle extras = getIntent().getExtras();
        if(extras.getInt("Direction") == 0){
            //left to right
            c.setBackgroundColor(getResources().getColor(R.color.red));
        }
        if(extras.getInt("Direction") == 1){
            //left to right
            c.setBackgroundColor(getResources().getColor(R.color.gold));
        }


        //TODO - check for passed intent and set background color according to the swipe direction.
    }
}