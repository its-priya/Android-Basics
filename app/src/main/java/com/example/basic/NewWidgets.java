package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class NewWidgets extends AppCompatActivity {
    ProgressBar progressDet, progressInDet;
    Button buttonD, buttonNotify, buttonCancel, downloadCancel;
    int progressStatus;
    boolean exit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_widgets);

        progressDet = findViewById(R.id.progressDet);
        progressInDet = findViewById(R.id.progressInDet);
        buttonD = findViewById(R.id.buttonD);
        downloadCancel = findViewById(R.id.downloadCancel);
        buttonNotify = findViewById(R.id.buttonNotify);
        buttonCancel = findViewById(R.id.buttonCancel);

        progressDet.setProgress(progressStatus);
        progressInDet.setIndeterminate(false);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit = false;
                progressStatus = 0;
                progressInDet.setIndeterminate(true);
                Thread threadD = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100 && !exit) {
                            progressStatus += 10;
                            if(progressStatus==100){
                                progressStatus=0;
                                exit= true;
                            }
                            progressDet.setProgress(progressStatus);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                threadD.start();
            }
        });
        downloadCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearProgress();
            }
        });
        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent= new Intent();
                PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, intent, PendingIntent);*/
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(NewWidgets.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (Exception e) {

                }
            }
        });
    }

    private void clearProgress() {
        progressInDet.setIndeterminate(false);
        Thread.currentThread().interrupt();
        exit = true;
        progressStatus = 0;
        progressDet.setProgress(progressStatus);
    }
}