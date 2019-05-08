package netid.iastate.edu.calculatorapp;

import android.widget.TextView;

import java.util.Locale;

public class CalculatorModel {
    /**
     * A string builder to represent the first number entered in the series of entries
     */
    private StringBuilder expression1;

    /**
     * A string builder to represent the second number entered in the series of entries
     */
    private StringBuilder expression2;

    /**
     * A string to represent the last operator performed
     */
    private String operator;

    /*
     *  Which operator are we on
     */
    public Boolean operatorBool = false;
    private StringBuilder display;
    TextView calculatorDisplay;

    CalculatorModel(TextView tv) {
        // Main Strings to represent the expressions
        expression1 = new StringBuilder();
        expression2 = new StringBuilder();
        calculatorDisplay = tv;
        display = new StringBuilder();
    }

    /**
     * This method appends to string builders the buttons that are pressed
     * @param button that was pressed as a string
     */
    public String buttonPress(String button) {
        if(operatorBool){
            expression2.append(button);
            if(expression2.length() >= 11){

            }
            else {
                display.append(button);
            }
        }
        else
        {
            expression1.append(button);
            if(expression1.length() >= 11){

            }
            else {
                display.append(button);
            }
        }
        return display.toString();
    }
    public String setOperator(String operator){
        if(operatorBool){
            calculate();
        }
        operatorBool = true;
        this.operator = operator;
        display.append(operator);
        return display.toString();
    }
    public String calculate() {
        String result = "";
        Double r = 0.0;
        Double exp1 = Double.parseDouble(expression1.toString());
        Double exp2 = Double.parseDouble(expression2.toString());
        if (operator == "-") {
            r = exp1 - exp2;
        }
        if (operator == "+") {
            r = exp1 + exp2;
        }
        if (operator == "*") {
            r = exp1 * exp2;
        }
        if (operator == "/") {
            r = exp1 / exp2;
        }
        expression1.setLength(0);
        expression2.setLength(0);
        display.setLength(0);

        result = Double.toString(r);
        result = result.substring(0,Math.min(result.length(), 11));
        display.append(result);
        expression1.append(result);
        operatorBool = false;
        return result;
    }
    public void clearExpression1(){
        expression1.setLength(0);
    }
    public void clearExpression2(){
        expression2.setLength(0);
    }
    public void clearDisplay(){
        display.setLength(0);
    }

    //TODO - add other calculator logic methods you want to use in this calculator model class
}
