package netid.iastate.edu.gestures;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This Activity shows touch information on a TextView.
 */
public class CoordinateActivity extends Activity {

    LinearLayout l;
    TextView t;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);


        l = findViewById(R.id.view);
        t = findViewById(R.id.coord_view);
        l.setOnTouchListener(new Coordinates(){
        });
    }
    private class Coordinates implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //if(event.getAction() == (MotionEvent.ACTION_MOVE | MotionEvent.ACTION_UP)){
                int x = (int)event.getX();
                int y = (int)event.getY();
                t.setText(String.format("%s %s", Integer.toString(x), Integer.toString(y)));
            //}
            return true;
        }
    }

    // true, updates (set) the TextView in the layout to display the ( x , y ) coordinates of the
    // moving finger

}
