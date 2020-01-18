package com.example.caclculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultView;
    private String currentNum = "", numAtRight = "", numAtLeft = "";

    //Instance Variable
    private double calculatedResult = Double.MAX_VALUE;
    private String placeHolder = "";
    private String storedOperator = "";
    private boolean isOpPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.zeroNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.oneNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.twoNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.threeNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.fourNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.fiveNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.sixNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.sevenNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.eightNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.nineNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.pointNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.clearScreen).setOnClickListener(MainActivity.this);
        findViewById(R.id.deleteNum).setOnClickListener(MainActivity.this);
        findViewById(R.id.plusOp).setOnClickListener(MainActivity.this);
        findViewById(R.id.minusOp).setOnClickListener(MainActivity.this);
        findViewById(R.id.multiplyOp).setOnClickListener(MainActivity.this);
        findViewById(R.id.divOp).setOnClickListener(MainActivity.this);
        findViewById(R.id.equalOp).setOnClickListener(MainActivity.this);
        findViewById(R.id.percentOp).setOnClickListener(MainActivity.this);
        resultView = findViewById(R.id.resultView);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearScreen:
                clearScreen();
                break;
            case R.id.deleteNum:
                if (!currentNum.equals("")) {
                    deleteNum();
                }
                break;
            case R.id.plusOp:
                opIsPressed(OPERATOR.PLUS);

                break;
            case R.id.minusOp:
                opIsPressed(OPERATOR.MINUS);

                break;
            case R.id.multiplyOp:
                opIsPressed(OPERATOR.MULTIPLY);

                break;
            case R.id.divOp:
                opIsPressed(OPERATOR.DIVIDE);

                break;
            case R.id.percentOp:
                opIsPressed(OPERATOR.PERCENTAGE);

                break;
            case R.id.equalOp:
                eqIsPressed();
                break;
            case R.id.oneNum:
                numIsPressed(1);
                break;
            case R.id.twoNum:
                numIsPressed(2);
                break;
            case R.id.threeNum:
                numIsPressed(3);
                break;
            case R.id.fourNum:
                numIsPressed(4);
                break;
            case R.id.fiveNum:
                numIsPressed(5);
                break;
            case R.id.sixNum:
                numIsPressed(6);
                break;
            case R.id.sevenNum:
                numIsPressed(7);
                break;
            case R.id.eightNum:
                numIsPressed(8);
                break;
            case R.id.nineNum:
                numIsPressed(9);
                break;
            case R.id.zeroNum:
                numIsPressed(0);
                break;
            case R.id.pointNum:
                dotIsPassed();
                break;

        }

        //size of the text
        if (placeHolder.length() < 10 && placeHolder.length() > 0) {
            resultView.setTextSize(50);
        } else if (placeHolder.length() >= 10 && placeHolder.length() < 13) {
            resultView.setTextSize(40);
        } else if (placeHolder.length() >= 13 && placeHolder.length() < 17) {
            resultView.setTextSize(30);
        } else if (placeHolder.length() >= 17 && placeHolder.length() < 25) {
            resultView.setTextSize(20);
        } else if (placeHolder.length() >= 25) {
            resultView.setTextSize(20);
        }
        //text on the screen
        resultView.setText(placeHolder);


    }

    private void numIsPressed(int pressedNum) {
        currentNum += String.valueOf(pressedNum);
        if (isOpPressed) {
            numAtRight = currentNum;
            placeHolder = numAtLeft + storedOperator + numAtRight;
        } else {
            numAtLeft = currentNum;
            placeHolder = numAtLeft;
        }
        calculatedResult = Double.MAX_VALUE;
    }
    //just adding this line

    private void dotIsPassed() {
        if (currentNum.equals("")) {
            currentNum += "0.";
        } else if (currentNum.contains(".")) {
            //i am doing nothing
        } else {
            currentNum += ".";
        }
        if (isOpPressed) {
            numAtRight = currentNum;
            placeHolder = numAtLeft + storedOperator + numAtRight;
        } else {
            numAtLeft = currentNum;
            placeHolder = numAtLeft;
        }
    }

    private void clearScreen() {
        currentNum = "";
        numAtLeft = "";
        numAtRight = "";
        storedOperator = "";
        calculatedResult = Double.MAX_VALUE;
        placeHolder = "0";
        isOpPressed = false;
    }

    private void deleteNum() {
        int jpt;
        if (isOpPressed) {
            jpt = numAtRight.length();
            numAtRight = numAtRight.substring(0, jpt - 1);
            placeHolder = numAtLeft + storedOperator + numAtRight;

        } else {
            jpt = numAtLeft.length();
            numAtLeft = numAtLeft.substring(0, jpt - 1);
            placeHolder = numAtLeft;
        }
        currentNum = currentNum.substring(0, jpt - 1);
    }

    private void opIsPressed(OPERATOR pressedOperator) {
        currentNum = "";
        if (isOpPressed) {
            eqIsPressed();
        }
        if (calculatedResult != Double.MAX_VALUE) {
            numAtLeft = String.valueOf(calculatedResult);
        }

        switch (pressedOperator) {
            case PLUS:
                storedOperator = "+";
                break;
            case MINUS:
                storedOperator = "-";
                break;
            case MULTIPLY:
                storedOperator = "*";
                break;
            case DIVIDE:
                storedOperator = "/";
                break;
            case PERCENTAGE:
                storedOperator = "%";
                break;
        }
        placeHolder = numAtLeft + storedOperator;
        isOpPressed = true;

    }

    private void eqIsPressed() {
        switch (storedOperator) {
            case "+":
                if (numAtLeft.equals("")) {
                    numAtLeft = "0";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "0";
                }
                calculatedResult = Double.parseDouble(numAtLeft) + Double.parseDouble(numAtRight);
                break;
            case "-":
                if (numAtLeft.equals("")) {
                    numAtLeft = "0";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "0";
                }
                calculatedResult = Double.parseDouble(numAtLeft) - Double.parseDouble(numAtRight);
                break;
            case "*":
                if (numAtLeft.equals("")) {
                    numAtLeft = "1";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "1";
                }
                calculatedResult = Double.parseDouble(numAtLeft) * Double.parseDouble(numAtRight);
                break;
            case "/":
                if (numAtLeft.equals("")) {
                    numAtLeft = "1";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "1";
                }
                calculatedResult = Double.parseDouble(numAtLeft) / Double.parseDouble(numAtRight);
                break;
            case "%":
                if (numAtLeft.equals("")) {
                    numAtLeft = "1";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "1";
                }
                calculatedResult = (Double.parseDouble(numAtLeft) * Double.parseDouble(numAtRight)) / 100;

                break;
            default:
                if (numAtLeft.equals("")) {
                    numAtLeft = "0";
                }
                if (numAtRight.equals("")) {
                    numAtRight = "0";
                }
                calculatedResult = Double.parseDouble(numAtLeft);
        }
        placeHolder = String.valueOf(calculatedResult);
        //if(numAtLeft.equals("6")&&numAtRight.equals("5")&&storedOperator.contains("+") ||numAtLeft.equals("5")&&numAtRight.equals("6")&&storedOperator.contains("+")){
        //    placeHolder ="I MISS YOU :)";
        //}
        currentNum = "";
        numAtRight = "";
        isOpPressed = false;


    }

    enum OPERATOR {
        PLUS, MINUS, MULTIPLY, DIVIDE, PERCENTAGE
    }

}
