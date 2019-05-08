package netid.iastate.edu.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * The instance of the calculator model for use by this controller.
     */
    private CalculatorModel calculatorModel;

    /*
     * The instance of the calculator display TextView. You can use this to update the calculator display.
     */
    TextView calculatorDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the instace of the calculator display TextView.  Don't forget to set the ID in the layout file.
        calculatorDisplay = findViewById(R.id.CalculatorDisplay);

        calculatorModel = new CalculatorModel(calculatorDisplay);

        // Hint: a helper function can be re-used in other places to update the display.
    }

    // TODO - from your layout, hook up these event handlers to the appropriate buttons.

    public void onZeroClicked(View view) {
        String s = calculatorModel.buttonPress("0");
        updateScreen(s);
    }

    public void onOneClicked(View view) {
        String s = calculatorModel.buttonPress("1");
        updateScreen(s);
    }

    public void onTwoClicked(View view) {
        String s = calculatorModel.buttonPress("2");
        updateScreen(s);
    }

    public void onThreeClicked(View view) {
        String s = calculatorModel.buttonPress("3");
        updateScreen(s);
    }

    public void onFourClicked(View view) {
        String s = calculatorModel.buttonPress("4");
        updateScreen(s);
    }

    public void onFiveClicked(View view) {
        String s = calculatorModel.buttonPress("5");
        updateScreen(s);
    }

    public void onSixClicked(View view) {
        String s = calculatorModel.buttonPress("6");
        updateScreen(s);
    }

    public void onSevenClicked(View view) {
        String s = calculatorModel.buttonPress("7");
        updateScreen(s);
    }

    public void onEightClicked(View view) {
        String s = calculatorModel.buttonPress("8");
        updateScreen(s);
    }

    public void onNineClicked(View view) {
        String s = calculatorModel.buttonPress("9");
        updateScreen(s);
    }

    public void onDecimalClicked(View view) {
        String s = calculatorModel.buttonPress(".");
        updateScreen(s);
    }

    public void onPlusClicked(View view) {
        calculatorModel.setOperator("+");
    }

    public void onMinusClicked(View view) {
        String s = calculatorModel.setOperator("-");
        updateScreen(s);
    }

    public void onTimesClicked(View view) {
        String s = calculatorModel.setOperator("*");
        updateScreen(s);
    }

    public void onDivideClicked(View view) {
        String s = calculatorModel.setOperator("/");
        updateScreen(s);
    }

    public void onEqualClicked(View view) {
        String s = calculatorModel.calculate();
        updateScreen(s);
    }

    public void onClearClicked(View view) {
        calculatorDisplay.setText("");
        calculatorModel.clearExpression1();
        calculatorModel.clearExpression2();
        calculatorModel.clearDisplay();
        calculatorModel.operatorBool = false;
    }
    public void updateScreen(String display){
        calculatorDisplay.setText(display);
    }
    //TODO - any other methods you want to use related to the UI
}
