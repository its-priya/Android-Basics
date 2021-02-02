package com.example.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListViewDemo extends AppCompatActivity {
    ArrayList aList;
    ListView listView;
    //ListView listView, listView2;
    int[] colors;
    int oldposition;
    Spinner spinner;
    Button change, checkSnackBar, popupMenu;
    Boolean list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        colors = new int[]{Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN};   // final int[] colors= {Color.RED,Color.GREEN,Color.YELLOW,Color.CYAN};
        aList = new ArrayList();
        aList.add("RED");
        aList.add("GREEN");
        aList.add("YELLOW");
        aList.add("BLUE");
        final ArrayAdapter listAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aList);
        listView = (ListView) findViewById(R.id.lv);
        popupMenu = (Button) findViewById(R.id.popupMenuId);
        //listView2= (ListView)findViewById(R.id.lv2);
        listView.setAdapter(listAdapter1);
        registerForContextMenu(listView);
        list1 = true;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(position);   // adapterView= parent
                Toast.makeText(ListViewDemo.this, "You clicked " + selectedItem, Toast.LENGTH_SHORT).show();
                if (oldposition != position) {
                    listView.getChildAt(oldposition).setBackgroundColor(Color.TRANSPARENT);
                    ((TextView) listView.getChildAt(oldposition)).setTextColor(Color.BLACK);
                    view.setBackgroundColor(colors[position]);  //listView.getChildAt(position).setBackgroundColor(colors[position]);
                    ((TextView) view).setTextColor(Color.BLUE);
                    oldposition = position;
                }
            }
        });
        spinner = findViewById(R.id.sp);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, aList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getSelectedItem();
                //Toast.makeText(ListViewDemo.this, "You selected "+selectedItem, Toast.LENGTH_SHORT).show();
                Toast t = Toast.makeText(ListViewDemo.this, "You selected " + selectedItem, Toast.LENGTH_SHORT); //Toast t= new Toast(getApplicationContext());
                t.setText("You selected " + selectedItem);
                //t.setDuration(Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                t.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final ArrayAdapter listAdapter2 = ArrayAdapter.createFromResource(this, R.array.subjects, android.R.layout.simple_expandable_list_item_1);
        //listView2.setAdapter(listAdapter2);
        change = (Button) findViewById(R.id.clickButton);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list1) {
                    listView.setAdapter(listAdapter2);
                    list1 = false;
                } else {
                    listView.setAdapter(listAdapter1);
                    list1 = true;
                }
            }
        });
        checkSnackBar = (Button) findViewById(R.id.checkButton);
        checkSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "An error occurred!", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Hey done!");
                    }
                }).show();
            }
        });
        // POPUP MENU
        popupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(ListViewDemo.this, view);
                popup.getMenuInflater().inflate(R.menu.menu1, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(ListViewDemo.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.renameId: {
                Toast.makeText(ListViewDemo.this, "Renamed Successfully!", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.yesId: {
                Toast.makeText(ListViewDemo.this, "Removed Successfully!", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.noId: {
                Toast.makeText(ListViewDemo.this, "Cancelled Successfully!", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }
}
    /*
    public void onChange(View v){
        replaceView(listView,listView2);
    }
    private void replaceView(View oldV, View newV){
        ViewGroup par= (ViewGroup)oldV.getParent();
        if(par==null){ return; }
        int i= par.indexOfChild(oldV);
        par.removeViewAt(i);
        par.addView(newV,i);
        //listView2.setVisibility(View.VISIBLE);
    }*/