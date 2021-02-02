package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class form1 extends AppCompatActivity {
    EditText nameVal, phoneVal;
    Button submit;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        nameVal= (EditText)findViewById(R.id.nameValue);
        phoneVal= (EditText)findViewById(R.id.phoneValue);
        submit= (Button)findViewById(R.id.submit);
        img=(ImageButton)findViewById(R.id.img);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr= nameVal.getText().toString();
                String phoneStr= phoneVal.getText().toString();
                if(nameStr.length()!=0 && phoneStr.length()==10){
                    Toast.makeText(form1.this,"Submitted "+nameStr+" having phone no. "+phoneStr+"!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Snackbar.make(view,"Incorrect!",Snackbar.LENGTH_SHORT).setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("!!!");
                        }
                    }).show();
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(form1.this, "Image clicked!", Toast.LENGTH_SHORT).show();
                img.setImageResource(R.drawable.pic3);
            }
        });

    }
}