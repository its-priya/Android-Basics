package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class RelativeLayout extends AppCompatActivity {
    NumberPicker nump;
    TextView incVal, decVal;
    Integer curInc, curDec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        nump= findViewById(R.id.numberPicker);
        nump.setMinValue(0);
        nump.setMaxValue(10);
        incVal= (TextView) findViewById(R.id.incValue);
        decVal= (TextView) findViewById(R.id.decValue);
        nump.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                curInc= i1;
                curDec= i1;
            }
        });
    }
    public void incValue(View v){
        curInc+=1;
        incVal.setText(""+curInc);
    }
    public void decValue(View v){
        curDec-=1;
        decVal.setText(""+curDec);
    }
}