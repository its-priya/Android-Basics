package com.example.basic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MenusDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_demo);
        View view1= getLayoutInflater().inflate(R.layout.activity_basic_log,null);
        new AlertDialog.Builder(MenusDemo.this)
                .setIcon(R.drawable.ic_account_circle)
                .setTitle("Main Dialog Box")
                .setView(view1)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder yesDialog= new AlertDialog.Builder(MenusDemo.this);
                        yesDialog.setTitle("Your preferred colors?");
                        String[] colors= new String[]{
                                "Red",
                                "Green",
                                "Black"
                        };
                        final boolean[] checkedColors= new boolean[]{
                                false,  // Red
                                false,  // Green
                                false   // Black
                        };
                        yesDialog.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                                checkedColors[i]= isChecked;
                            }
                        });
                        yesDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        yesDialog.setCancelable(false);
                        yesDialog.show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new AlertDialog.Builder(MenusDemo.this)
                        .setIcon(R.drawable.ic_account_circle)
                        .setTitle("No Dialog Box")
                        .setMessage("Click No")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        })
                .setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AlertDialog.Builder(MenusDemo.this)
                                .setIcon(R.drawable.ic_account_circle)
                                .setTitle("Neutral Dialog Box")
                                .setMessage("Click Neutral")
                                .setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }
                })
                .show();
    }
}