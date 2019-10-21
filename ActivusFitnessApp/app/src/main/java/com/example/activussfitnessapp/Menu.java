package com.example.activussfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final ImageButton notificationbtn = (ImageButton) findViewById(R.id.notificationbtn);
        ImageButton workoutNtm = (ImageButton) findViewById(R.id.workoutbtn);
        ImageButton trainingprogramBtn = (ImageButton) findViewById(R.id.Trainingprogrambtn);
        ImageButton profileBtn = (ImageButton) findViewById(R.id.profilbtn);
        ImageButton statsBtn = (ImageButton) findViewById(R.id.statsbtn);
        ImageButton inboksBtn = (ImageButton) findViewById(R.id.inboksbtn);
        ImageButton exicieseBtn = (ImageButton) findViewById(R.id.excisebtn);
        ImageButton chatBtn = (ImageButton) findViewById(R.id.chatbtn);
        ImageButton calenderBtn = (ImageButton) findViewById(R.id.calenderbtn);
        ImageButton buddylistBtn = (ImageButton) findViewById(R.id.buddylistbtn);

        buddylistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            }
        });


        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        exicieseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inboksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        trainingprogramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        notificationbtn.setVisibility(View.INVISIBLE);
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

public void notification(ImageButton i){
        i.setVisibility(View.VISIBLE);
}



}
