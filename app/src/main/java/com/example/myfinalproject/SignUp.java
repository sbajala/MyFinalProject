package com.example.myfinalproject;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        context = this;

        Button signUpButton = findViewById(R.id.signUpButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        signUpButton.setOnClickListener((view) -> {
            //TODO
        });

        cancelButton.setOnClickListener((view) -> {
            //TODO
        });
    }
}
