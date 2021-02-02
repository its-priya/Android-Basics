package com.example.basic;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class GuessGame extends AppCompatActivity {
    TextView hintText;
    Button checkGuess, resetB;
    NumberPicker numPicker;
    static int guessedNum, guessCount, randomNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);
        hintText= (TextView)findViewById(R.id.hintText);
        checkGuess= (Button)findViewById(R.id.checkB);
        resetB= (Button)findViewById(R.id.resetB);
        numPicker= (NumberPicker)findViewById(R.id.numPicker);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(31);

        generateRandomNum();
        checkGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessedNum= numPicker.getValue();
                if(guessedNum>randomNum){
                    hintText.setText("Try lower!!");
                    guessCount+=1;
                }
                else if(guessedNum<randomNum){
                    hintText.setText("Try higher!!");
                    guessCount+=1;
                }
                else{
                    Toast.makeText(GuessGame.this, "You guessed it right in "+guessCount+"th guess!", Toast.LENGTH_LONG).show();
                    resetGame(view);
                }
            }
        });
    }
    public void generateRandomNum(){
        randomNum= new Random().nextInt(30)+1;
    }
    public void resetGame(View view) {
        guessCount=0;
        hintText.setText("");
        generateRandomNum();
    }
}