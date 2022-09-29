package com.example.myfinalproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = this;

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((view) -> {
            //TODO
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });
    }
}
