package com.nikhilchauhan.com.bulbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


//    public void blueTapped(View view){
//
//        ImageView blue = (ImageView)findViewById(R.id.blueBulb);
//
//        blue.animate().alpha(0).setDuration(1000);
//
//    }

    public  void blueTapped(View view){

        ImageView green = (ImageView)findViewById(R.id.greenBulb);
        green.animate().alpha(0).setDuration(1000);

        ImageView blue = (ImageView)findViewById(R.id.blueBulb);
        blue.animate().alpha(1).setDuration(1000);


    }

    public void greenTapped(View view){

        ImageView blue = (ImageView)findViewById(R.id.blueBulb);
        blue.animate().alpha(0).setDuration(1000);

       ImageView green = (ImageView)findViewById(R.id.greenBulb);
       green.animate().alpha(1).setDuration(1000);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
