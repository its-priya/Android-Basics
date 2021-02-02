package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntentsResult extends AppCompatActivity {
    int num1, num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_result);
        /* //SignUp.java -> this
        // 1. Fetching data using unique name for each.
        Bundle b= getIntent().getExtras();           Bundle b= getIntent();
        String fname= b.getString("fn");               String fname= b.getStringExtra("fn");
        String lname= b.getString("ln");               String lname= b.getString("ln");

        // 2. Fetching data from a class object.
        Intent i= getIntent();
        PersonData obj= (PersonData) i.getSerializableExtra("data");
        Toast.makeText(HomePage.this,"Welcome "+obj.firstName+" "+obj.lastName, Toast.LENGTH_LONG);*/

        Bundle b= getIntent().getExtras();
        num1= b.getInt("val1");
        num2= b.getInt("val2");
    }
    public void backPage(View v){
        Intent i= new Intent();
        //i.putExtra("msg","Got the value");

        i.putExtra("ans",(num1+num2)+"");
        setResult(RESULT_OK,i);
        finish();
    }
}