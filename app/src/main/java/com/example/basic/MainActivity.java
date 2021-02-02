package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    ArrayList alist;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alist= new ArrayList();
        alist.add("One");
        alist.add("Two");
        alist.add("Three");
        ArrayAdapter listAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,alist);
        listView= (ListView) findViewById(R.id.lv);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Item Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void clearFun(View v){
        username= (EditText)findViewById(R.id.username);
        username.getText().clear();
        password= (EditText)findViewById(R.id.password);
        password.getText().clear();
    }
    public void submitFun(View v){
        //this or getApplicationContext()
        Toast.makeText(this, "Submitted Successfully", LENGTH_SHORT).show();
    }
    public void cancelFun(View v){
        Toast.makeText(this, "Cancelled", LENGTH_SHORT).show();
    }
}