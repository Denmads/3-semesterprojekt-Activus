package com.example.activussfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageButton buddylistBtn = (ImageButton) findViewById(R.id.buddylistbtn);
        buddylistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton calenderBtn = (ImageButton) findViewById(R.id.calenderbtn);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton chatBtn = (ImageButton) findViewById(R.id.chatbtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton exicieseBtn = (ImageButton) findViewById(R.id.excisebtn);
        exicieseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton inboksBtn = (ImageButton) findViewById(R.id.inboksbtn);
        inboksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton profileBtn = (ImageButton) findViewById(R.id.profilbtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton statsBtn = (ImageButton) findViewById(R.id.statsbtn);
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton trainingprogramBtn = (ImageButton) findViewById(R.id.Trainingprogrambtn);
        trainingprogramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageButton workoutNtm = (ImageButton) findViewById(R.id.workoutbtn);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton notificationbtn = (ImageButton) findViewById(R.id.notificationbtn);
        notificationbtn.setVisibility(View.INVISIBLE);
        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }





}
