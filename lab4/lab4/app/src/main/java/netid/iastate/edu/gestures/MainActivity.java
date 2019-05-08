package netid.iastate.edu.gestures;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Main activity which represents the middle view.
 */
public class MainActivity extends CustomGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLeftRight(ThirdActivity.class, SecondActivity.class);
        Bundle extras = getIntent().getExtras();
        ConstraintLayout c = findViewById(R.id.mainLayout);
        if(extras != null){
            if(extras.getInt("Direction") == 0){
                //left to right
                c.setBackgroundColor(getResources().getColor(R.color.red));
            }
            if(extras.getInt("Direction") == 1){
                //left to right
                c.setBackgroundColor(getResources().getColor(R.color.gold));
            }
        }

        //TODO - check for passed intent and set background color according to the swipe direction.
        //Use KEY_INTENT_DIRECTION defined in CustomGestureListener as the key for the direction
        // data.
    }
}
