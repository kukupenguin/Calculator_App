package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView tv;
    private TextView preTv;
    private double preNum = 0;
    private String op = "+";
    private String numStr = "0";
    boolean isLastequal = false;
    boolean isLastOp = false;
    boolean isLastChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.tv);
        preTv = findViewById(R.id.preTv);
    }

    public void numClick(View view) {
        if (numStr.length() >= 8)
            return;
        if (isLastequal) {
            clearClick(view);
            isLastequal = false;
        }
        if (numStr.equals("0"))
            numStr="";
        isLastOp = false;
        switch (view.getId()) {
            case R.id.btn0:
                numStr += '0';
                break;
            case R.id.btn1:
                numStr += '1';
                break;
            case R.id.btn2:
                numStr += '2';
                break;
            case R.id.btn3:
                numStr += '3';
                break;
            case R.id.btn4:
                numStr += '4';
                break;
            case R.id.btn5:
                numStr += '5';
                break;
            case R.id.btn6:
                numStr += '6';
                break;
            case R.id.btn7:
                numStr += '7';
                break;
            case R.id.btn8:
                numStr += '8';
                break;
            case R.id.btn9:
                numStr += '9';
                break;
            case R.id.point:
                if (numStr.equals(0))
                    numStr = "0";
                numStr += '.';
                break;
        }
        tv.setText(numStr);

    }

    public void backClick(View view) {
        if(numStr.length() == 1)
            numStr = "0";
        else
            numStr = numStr.substring(0, numStr.length()-1);
        tv.setText(numStr);
    }

    public void clearClick(View view) {
        numStr = "0";
        preNum = 0;
        tv.setText(numStr);
        preTv.setText("");
        isLastequal = false;
        isLastOp = false;
        op="+";
    }
    //改變正負號
    public void changeClick(View view) {
        double n = Double.valueOf(tv.getText().toString());
        if (n == 0)
            return;
        numStr = String.valueOf(n * -1);
        tv.setText(numStr);
        isLastChange = true;
        isLastOp = false;
        //preTv.setText("");
    }

    public void opClick(View view) {
        if (isLastequal == false && isLastOp==false) {
            equalClick(view);
            isLastequal = false;
            preNum = Double.valueOf(tv.getText().toString());
        }
        numStr = "0";
        tv.setText(numStr);
        switch (view.getId()) {
            case R.id.plus:
                op = "+";
                break;
            case R.id.minus:
                op = "-";
                break;
            case R.id.mul:
                op = "×";
                break;
            case R.id.div:
                op = "÷";
                break;
        }


        preTv.setText(preNum + op);
        isLastOp = true;
        isLastequal = false;
    }

    public void equalClick(View view) {
        double answer = preNum;
        if (isLastChange) {
            answer = Double.valueOf(numStr);
            isLastChange = false;
        }
        switch (op){
            case "+":
                answer = preNum + Double.valueOf(numStr);
                break;
            case "-":
                answer = preNum - Double.valueOf(numStr);
                break;
            case "×":
                answer = preNum * Double.valueOf(numStr);
                break;
            case "÷":
                answer = preNum / Double.valueOf(numStr);
                break;
        }

        tv.setText(String.valueOf(answer));
        preTv.setText(preNum + op + numStr + '=');
        preNum = answer;
        isLastequal = true;
    }

    public void facClick(View view) {
        double n = Double.valueOf(tv.getText().toString());
        if ( n<0 || n%1 !=0 )
            return;
        if (n == 1 || n == 0)
            n = 1;
        else {
            int f = 1;
            for (int i=2; i<=n; i++) {
                f *= i;
            }
            n = f;
        }
        numStr = String.valueOf(n);
        tv.setText(numStr);
    }

}