package com.nikhilchauhan.com.diceroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    int[] myDices={

            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
    };


    public void rollTapped(View view){

        //Log.i("Message", "Roll is Tapped ");
        Random ran = new Random();        //creating object of class random
        int randomNumber = ran.nextInt(6); //accessing and storing value in randomNumver through object ran
        //Log.i("Random","number is"+randomNumber);

        ImageView myImage = (ImageView) findViewById(R.id.dice);

        myImage.setImageResource(myDices[randomNumber]);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
