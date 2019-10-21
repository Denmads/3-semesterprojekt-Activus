package com.example.activussfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private String userName;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton loginBtn = findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText un = (EditText) findViewById(R.id.UsernameOnPage);
                EditText pw = (EditText) findViewById(R.id.passwordOnPage);
                userName = un.getText().toString();
                password = pw.getText().toString();

                if(true){
                    Intent goToMenu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(goToMenu);
                }
            }
        });
    }
}
