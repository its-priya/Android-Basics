package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class AddContact extends AppCompatActivity {
    Button save, cancel;
    EditText userNameVal, userNumberVal;
    ArrayList pList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        // Get IDs
        userNameVal= (EditText)findViewById(R.id.userNameId);
        userNumberVal= (EditText)findViewById(R.id.userNumberId);
        save= (Button)findViewById(R.id.saveId);
        cancel= (Button)findViewById(R.id.cancelId);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValid()){
                    String pName= userNameVal.getText().toString();
                    String pNumber=userNumberVal.getText().toString();

                    Intent intent= getIntent();
                    Bundle b= intent.getBundleExtra("ArrayList");
                    ArrayList<Person> pList= (ArrayList<Person>) b.getSerializable("contactList");
                    pList.add(new Person(pName, pNumber, R.drawable.ic_account_box));

                    b.putSerializable("ArrayList", (Serializable)pList);
                    intent.putExtra("newContactList", b);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    public boolean checkValid(){
        if(userNameVal.getText().toString().length()==0 | userNumberVal.getText().toString().length()<10)
            return false;
        return true;
    }
}