package com.example.basic;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Intents extends Activity {
    EditText num1, num2, res;
    ImageView image;
    Button cameraReq, gallaryReq, dialer, clickPic, map;
    private static final int ADD_REQUEST_CODE = 1;
    private static final int CLICK_REQUEST_CODE = 2;
    private static final int CAMERA_REQUEST_CODE = 3;
    private static final int GALLARY_REQUEST_CODE = 4;
    protected static final String[] cameraPermissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    protected static final String[] gallaryPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        res = (EditText) findViewById(R.id.res);
        image = (ImageView) findViewById(R.id.img);
        dialer = (Button) findViewById(R.id.dialId);
        cameraReq = (Button) findViewById(R.id.cameraId);
        gallaryReq = (Button) findViewById(R.id.gallaryId);
        clickPic = (Button) findViewById(R.id.clickId);
        map= (Button)findViewById(R.id.mapId);

        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // IMPLICIT INTENT
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                startActivity(dialIntent);
            }
        });
        clickPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IMPLICIT INTENT
                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    //Single click Camera
                startActivityForResult(cam, CLICK_REQUEST_CODE);
            }
        });

        gallaryReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasAllPermissions(gallaryPermissions)) {
                    ActivityCompat.requestPermissions(Intents.this, gallaryPermissions, GALLARY_REQUEST_CODE);
                }
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
        cameraReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasAllPermissions(cameraPermissions)) {
                    ActivityCompat.requestPermissions(Intents.this, cameraPermissions, CAMERA_REQUEST_CODE);
                }
            }
        });
    }

    private boolean hasAllPermissions(String[] requiredPermissions) {
        for (String permission : requiredPermissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                    Toast.makeText(Intents.this, "Camera permission Granted!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Intents.this, "Camera permission Denied!", Toast.LENGTH_SHORT).show();
                break;
            case GALLARY_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallary();
                    Toast.makeText(Intents.this, "Gallary permission Granted!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Intents.this, "Gallary permission Denied!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void openGallary() {
        Intent galIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivity(galIntent);
    }

    private void openCamera() {
        // IMPLICIT INTENT
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);    // Camera App
        startActivity(camIntent);
    }
    public void openMap(){
        Uri u= Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent= new Intent(Intent.ACTION_VIEW, u);
        startActivity(mapIntent);
    }
    public void addFun(View v) {
        // EXPLICIT INTENT
        Intent expIntent = new Intent(this, IntentsResult.class);
        expIntent.putExtra("val1", Integer.parseInt(num1.getText().toString()));
        expIntent.putExtra("val2", Integer.parseInt(num2.getText().toString()));
        startActivityForResult(expIntent, ADD_REQUEST_CODE);
    }

    public void openWebsite(View v) {
        // IMPLICIT INTENT
        Intent webIntent = new Intent();
        webIntent.setAction(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse("https://www.google.co.in"));
        // App chooser
        Intent chooser= Intent.createChooser(webIntent, "Open Browser");
        startActivity(chooser);
        //startActivity(webIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_REQUEST_CODE: {
                String ans = data.getStringExtra("ans");
                res.setText(ans);
                break;
            }
            case CLICK_REQUEST_CODE: {
                Bundle extras = data.getExtras();                  // The single clicked pic is fetched.
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                image.setImageBitmap(imageBitmap);
                break;
            }
        }
    }
    protected void sendMessage(View v){
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage("9876543201", null, "Heyy!", null, null);
    }
    protected void openMessageApp(View v){
        Intent smsIntent= new Intent(Intent.ACTION_SENDTO);
        String phoneNumber= "9876543201";
        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.setData(Uri.parse("sms:"+phoneNumber));
        startActivity(smsIntent);
    }
}
