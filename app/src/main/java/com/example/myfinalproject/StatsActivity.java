package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproject.doa.ReadingListManager;
import com.example.myfinalproject.enums.ReadingStatus;
import com.example.myfinalproject.utils.BounceInterpolator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StatsActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_activity);
        getSupportActionBar().setTitle(R.string.stats_item);
        context = this;

        //Setting values of each ReadingStatus count
        TextView bookNotStarted = findViewById(R.id.book_not_started_number);
        int bookNotStartedCount = ReadingListManager.getBookCount(context, ReadingStatus.NOT_STARTED.name());
        bookNotStarted.setText(String.valueOf(bookNotStartedCount));

        TextView bookInProgress = findViewById(R.id.book_in_progress_number);
        int bookInProgressCount = ReadingListManager.getBookCount(context, ReadingStatus.IN_PROGRESS.name());
        bookInProgress.setText(String.valueOf(bookInProgressCount));

        TextView bookCompleted = findViewById(R.id.books_complete_number);
        int bookCompletedCount = ReadingListManager.getBookCount(context, ReadingStatus.COMPLETED.name());
        bookCompleted.setText(String.valueOf(bookCompletedCount));

        //Defining button animation and applying it to buttons
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);

        Button notStartedButton = findViewById(R.id.not_started_button);
        notStartedButton.startAnimation(animation);
        Button inProgressButton = findViewById(R.id.in_progress_button);
        inProgressButton.startAnimation(animation);
        Button completedButton = findViewById(R.id.completed_button);
        completedButton.startAnimation(animation);

        //Defining buttons onClick behaviour
        notStartedButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadingListActivity.class);
            intent.putExtra(getString(R.string.hasReadingStatus), ReadingStatus.NOT_STARTED.name());
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        inProgressButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadingListActivity.class);
            intent.putExtra(getString(R.string.hasReadingStatus), ReadingStatus.IN_PROGRESS.name());
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        completedButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadingListActivity.class);
            intent.putExtra(getString(R.string.hasReadingStatus), ReadingStatus.COMPLETED.name());
            startActivity(intent);
            overridePendingTransition(0,0);
        });


        //Getting bottom navigation and defining its onClick behaviour
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Setting this activity as selected on bottom navigation bar
        bottomNavigationView.setSelectedItemId(R.id.stats);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch(item.getItemId()) {
                case R.id.add:
                    intent = new Intent(context, AddActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    break;
                case R.id.reading_list:
                    intent = new Intent(context, ReadingListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    break;
                case R.id.stats:
                    break;
            }
            return false;
        });
    }
}
