package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {
    EditText username, password;
    Button loginB;
    CheckBox changePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username= (EditText)findViewById(R.id.userNameId);
        password= (EditText)findViewById(R.id.passwordId);
        loginB= (Button)findViewById(R.id.loginId);
        loginB.setEnabled(false);
        changePassword= (CheckBox)findViewById(R.id.changePasswordId);
    }
}