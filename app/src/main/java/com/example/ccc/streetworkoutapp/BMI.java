package com.example.ccc.streetworkoutapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI extends AppCompatActivity {
    private EditText height,weight;
    private TextView result;
    private Button calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        calculator = (Button) findViewById(R.id.calc);

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                calculateBMI(v);
            }
        });
    }




    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            double heightValue = Double.parseDouble(heightStr) / 100;
            double weightValue = Double.parseDouble(weightStr);

            double bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(double bmi) {
        String bmiLabel = "";

        if (Double.compare(bmi, 16) <= 0) {
            bmiLabel = "wygłodzenie";
        } else if (Double.compare(bmi, 16) > 0  &&  Double.compare(bmi, 16.99) <= 0) {
            bmiLabel = "wychudzenie";
        } else if (Double.compare(bmi, 17) > 0  &&  Double.compare(bmi, 18.49) <= 0) {
            bmiLabel = "niedowaga";
        } else if (Double.compare(bmi, 18.5) > 0  &&  Double.compare(bmi, 24.99) <= 0) {
            bmiLabel = "wartość prawidłowa";
        } else if (Double.compare(bmi, 25) > 0  &&  Double.compare(bmi, 29.99) <= 0) {
            bmiLabel = "nadwaga";
        } else if (Double.compare(bmi, 30) > 0  &&  Double.compare(bmi, 34.99) <= 0) {
            bmiLabel = "I stopień otyłości";
        } else if (Double.compare(bmi, 35) > 0  &&  Double.compare(bmi, 39.99) <= 0) {
            bmiLabel = "II stopień otyłości";
        } else {
            bmiLabel = "otyłość skrajna";
        }

        bmiLabel = round(bmi,2) + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}