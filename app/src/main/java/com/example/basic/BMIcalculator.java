package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIcalculator extends AppCompatActivity {
    EditText height, weight;
    TextView bmi;
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_icalculator);
        height= (EditText)findViewById(R.id.height);
        weight= (EditText)findViewById(R.id.weight);
        bmi= (TextView)findViewById(R.id.bmi);
        calculate= (Button)findViewById(R.id.calculateB);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(height.getText().toString().length()!=0 && weight.getText().toString().length()!=0){
                    Float heightVal= Float.parseFloat(height.getText().toString().trim());
                    Float weightVal= Float.parseFloat(weight.getText().toString().trim());
                    Float ansBMI= weightVal/(heightVal*heightVal);
                    bmi.setText(""+ansBMI);
                }
            }
        });
    }
}