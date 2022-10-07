package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproject.doa.ReadingListManager;
import com.example.myfinalproject.enums.ReadingStatus;
import com.example.myfinalproject.utils.BounceInterpolator;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        getSupportActionBar().setTitle(R.string.add_item);
        context = this;

        //Defining addButton onClick behaviour
        Button addButton = findViewById(R.id.addButton);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        addButton.startAnimation(animation);
        addButton.setOnClickListener((view -> {
            //Also adding animation on button click
            addButton.startAnimation(animation);

            //Retrieving user input
            EditText authorInput = findViewById(R.id.authorInput);
            String author = authorInput.getText().toString().trim();

            EditText titleInput = findViewById(R.id.titleInput);
            String title = titleInput.getText().toString().trim();

            EditText noteInput = findViewById(R.id.noteInput);
            String note = noteInput.getText().toString().trim();
            ReadingListManager.addBook(context, new Book(title, author, note,
                    ReadingStatus.NOT_STARTED));
        }));


        //Getting bottom navigation and defining its onClick behaviour
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Setting this activity as selected on bottom navigation bar
        bottomNavigationView.setSelectedItemId(R.id.add);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch(item.getItemId()) {
                case R.id.add:
                    break;
                case R.id.reading_list:
                    intent = new Intent(context, ReadingListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    break;
                case R.id.stats:
                    intent = new Intent(context, StatsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    break;
            }
            return false;
        });

    }

}
