package com.example.basic;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity{
    ArrayList aList;
    ListView listView;
    ArrayAdapter aAdapter;
    int totalSelected= 0;
    EditText textItem;
    Button addButton;
    SparseBooleanArray selectedPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        listView= findViewById(R.id.list);
        addButton= (Button)findViewById(R.id.addId);
        textItem= (EditText)findViewById(R.id.textItem);
        textItem.setFocusableInTouchMode(true);

        /*View view= this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }*/

        aList= new ArrayList();
        aList.add("One");
        aList.add("Two");
        aList.add("Three");

        aAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, aList);
        listView.setAdapter(aAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean isChecked) {
                if(isChecked)
                    listView.getChildAt(i).setSelected(true);
                else
                    listView.getChildAt(i).setSelected(false);
                totalSelected= listView.getCheckedItemCount();
                if(totalSelected==1)
                    actionMode.setTitle("1 item selected");
                else
                    actionMode.setTitle(totalSelected+" items selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflator= actionMode.getMenuInflater();
                inflator.inflate(R.menu.menu1, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.deleteId:
                        selectedPos= listView.getCheckedItemPositions();
                        for(int ind=(selectedPos.size()-1);ind>=0;ind--){
                            if(selectedPos.valueAt(ind)){
                                aAdapter.remove(aAdapter.getItem(selectedPos.keyAt(ind)));
                            }
                        }
                        aAdapter.notifyDataSetChanged();
                        actionMode.finish();
                        String str;
                        if(totalSelected==1)
                            str= " item ";
                        else
                            str= " items ";
                        Toast.makeText(ToDoList.this,totalSelected+str+"deleted!",Toast.LENGTH_SHORT).show();
                        totalSelected= 0;
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aAdapter.add(textItem.getText().toString());
                textItem.setText("");
                aAdapter.notifyDataSetChanged();
            }
        });
    }

}

/*
new AlertDialog.Builder(ToDoList.this)
                                .setIcon(android.R.drawable.ic_delete)
                                .setTitle("Confirm Delete..")
                                .setMessage("Are you sure you want to delete ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SparseBooleanArray selectedPos= listView.getCheckedItemPositions();
                                        for(int ind=(selectedPos.size()-1);ind>=0;ind--){
                                            if(selectedPos.valueAt(ind)){
                                                aAdapter.remove(aAdapter.getItem(selectedPos.keyAt(ind)));
                                                aAdapter.notifyDataSetChanged();
                                            }
                                        }
                                        String str;
                                        if(totalSelected==1)
                                            str= " item ";
                                        else
                                            str= " items ";
                                        Toast.makeText(ToDoList.this,totalSelected+str+"deleted!",Toast.LENGTH_SHORT).show();
                                        totalSelected= 0;
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();
*/