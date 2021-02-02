package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
//import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

//import static android.text.method.LinkMovementMethod.*;

public class SignUp extends Activity {
    TextView textView;
    EditText firstName, lastName, emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textView= findViewById(R.id.loginlink);
        //textView.setMovementMethod(LinkMovementMethod.getInstance());

        firstName= (EditText)findViewById(R.id.firstname);
        lastName= (EditText)findViewById(R.id.lastname);
        emailId= (EditText)findViewById(R.id.email);
    }
    /*public void changeActivity(View v){
        Intent i= new Intent(this,HomePage.class);
        String fName= firstName.getText().toString();
        String lName= lastName.getText().toString();
        String email= emailId.getText().toString();
        i.putExtra("fn",fName);
        i.putExtra("ln",lName);
        PersonData obj= new PersonData(fName, lName, email);
        i.putExtra("data",obj);
        //startActivity(i);                          // When we don't expect any response
        startActivityForResult(i,1);                 // When we expect response
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && RESULT_OK==resultCode){
            String str= data.getStringExtra("msg");
            Toast.makeText(SignUp.this,str, Toast.LENGTH_SHORT);
        }
    }*/
}