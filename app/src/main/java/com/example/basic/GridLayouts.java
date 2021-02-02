package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class GridLayouts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layouts);
    }
    public void onImageClick(View view) {
        switch(view.getId()){
            case R.id.Img1:{
                Toast.makeText(this,"Image1 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img2:{
                Toast.makeText(this,"Image2 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img3:{
                Toast.makeText(this,"Image3 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img4:{
                Toast.makeText(this,"Image4 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img5:{
                Toast.makeText(this,"Image5 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img6:{
                Toast.makeText(this,"Image6 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img7:{
                Toast.makeText(this,"Image7 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img8:{
                Toast.makeText(this,"Image8 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Img9:{
                Toast.makeText(this,"Image9 clicked!",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
