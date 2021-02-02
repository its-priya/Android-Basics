package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Form extends AppCompatActivity {
    EditText nameV, idV;
    CheckBox hindi, english, other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        nameV= (EditText)findViewById(R.id.nameValue);
        idV= (EditText)findViewById(R.id.idValue);
        hindi= (CheckBox)findViewById(R.id.hindi);
        english= (CheckBox)findViewById(R.id.english);
        other= (CheckBox)findViewById(R.id.other);
    }
    public void onSubmit(View v){
        Boolean checkHindi= hindi.isChecked();
        Boolean checkEng= english.isChecked();
        Boolean checkOther= other.isChecked();
        String nameText= nameV.getText().toString().trim();
        String idText= idV.getText().toString().trim();
        if(nameText.length()==0){
            nameV.requestFocus();
            nameV.setError("Field cannot be empty!");
        }
        else if(idText.length()==0){
            idV.requestFocus();
            idV.setError("Field cannot be empty!");
        }
        else if(!checkEng & !checkHindi & !checkOther){
            hindi.requestFocus();
            hindi.setError("At least one checkbox must be selected!");
        }
        else if(idText.length()>=5){
            idV.requestFocus();
            idV.setError("Invalid ID! Maximum limit is 4.");
        }
        else if(!idText.matches("[0-9 ]+")){
            idV.requestFocus();
            idV.setError("Invalid ID! Numbers accepted.");
        }
        /*else if(!checked){
            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        }*/
        else{
            Toast.makeText(this,"Welcome "+nameText+" with ID "+idText,Toast.LENGTH_SHORT).show();
        }
    }
}