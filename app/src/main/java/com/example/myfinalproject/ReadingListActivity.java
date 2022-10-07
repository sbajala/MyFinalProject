package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproject.adapters.ListAdapter;
import com.example.myfinalproject.doa.ReadingListManager;
import com.example.myfinalproject.enums.ReadingStatus;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ReadingListActivity extends AppCompatActivity {
    Context context;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readinglist_activity);
        getSupportActionBar().setTitle(R.string.reading_list_item);
        context = this;

        //Retrieving books
        ArrayList<Book> books;
        Intent initialIntent = getIntent();
        //If intent contains a reading status, then books will be retrieved by reading status.
        // Else, all books will be retrieved.
        if (initialIntent.hasExtra(getString(R.string.hasReadingStatus))) {
            String readingStatus = initialIntent.getStringExtra(getString(R.string.hasReadingStatus));
            books = ReadingListManager.getBooksByReadingStatus(context, readingStatus);
        } else {
            books = ReadingListManager.getBooks(context);
        }

        //Creating listview with list adapter
        ListView listView = findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(context, R.layout.library_list_item, books);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Book book = books.get(i);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.selectOption);
            builder.setIcon(R.drawable.menu_icon);

            int checkedAction= -1;
            String[] actionList = getResources().getStringArray(R.array.actionOptions);
            builder.setSingleChoiceItems(actionList, checkedAction, ((dialogInterface, i1) -> {
                switch(actionList[i1]) {
                    case "Edit book info":
                        //Creating edit dialog box
                        AlertDialog.Builder editBuilder = new AlertDialog.Builder(context);
                        editBuilder.setTitle(R.string.edit_book_title);
                        editBuilder.setIcon(R.drawable.edit_icon);
                        LayoutInflater inflater = getLayoutInflater();
                        View editView = inflater.inflate(R.layout.edit_dialog_layout, null);
                        editBuilder.setView(editView);

                        //Diplaying book information in EditText boxes
                        EditText title = editView.findViewById(R.id.edit_title_input);
                        title.setText(book.getTitle());

                        EditText author = editView.findViewById(R.id.edit_author_input);
                        author.setText(book.getAuthor());

                        EditText note = editView.findViewById(R.id.edit_note_input);
                        note.setText(book.getNote());

                        //Defining dialog buttons
                        editBuilder.setPositiveButton("OK", ((dialogInterface4, i4) -> {
                            dialogInterface4.dismiss();

                            String editedTitle = title.getText().toString().trim();
                            book.setTitle(editedTitle);
                            String editedAuthor = author.getText().toString().trim();
                            book.setAuthor(editedAuthor);
                            String editedNote = note.getText().toString().trim();
                            book.setNote(editedNote);

                            ReadingListManager.updateBookInfo(context, book);
                            listAdapter.notifyDataSetChanged();
                    }));

                        editBuilder.setNegativeButton(R.string.cancelMessage,
                                (dialogInterface4, i4) -> dialogInterface4.cancel());

                        AlertDialog editDialog = editBuilder.create();
                        editDialog.show();
                        break;

                    case "Update reading status":
                        //Creating update dialog
                        android.app.AlertDialog.Builder updateBuilder = new android.app.AlertDialog.Builder(context);
                        updateBuilder.setTitle(R.string.selectOption);
                        updateBuilder.setIcon(R.drawable.in_progress_icon);

                        String [] readingStatusOptions = getResources().getStringArray(R.array.readingStatusOptions);
                        updateBuilder.setSingleChoiceItems(readingStatusOptions, checkedAction, (dialogInterface2, i2) -> {
                            String selectedReadingStatus = readingStatusOptions[i2];
                            switch(selectedReadingStatus) {
                                case "Not started":
                                    book.setReadingStatus(ReadingStatus.NOT_STARTED);
                                    break;
                                case "In progress":
                                    book.setReadingStatus(ReadingStatus.IN_PROGRESS);
                                    break;
                                case "Completed":
                                    book.setReadingStatus(ReadingStatus.COMPLETED);
                                    break;
                            }
                        });

                        //Defining dialog buttons
                        updateBuilder.setPositiveButton(R.string.okMessage, (dialogInterface2, i2) -> {
                            dialogInterface2.dismiss();

                            ReadingListManager.updateBookReadingStatus(context, book);

                            //Notifying listAdapter to update ListView
                            listAdapter.notifyDataSetChanged();
                        });

                        updateBuilder.setNegativeButton(R.string.cancelMessage,
                                (dialogInterface2, i2) -> dialogInterface2.cancel());

                        android.app.AlertDialog dialog = updateBuilder.create();
                        dialog.show();

                        LayoutInflater updateInflater = getLayoutInflater();
                        updateBuilder.setView(updateInflater.inflate(R.layout.readinglist_activity, null));
                        break;
                    case "Remove from reading list":
                        //Creating delete dialog
                        android.app.AlertDialog.Builder deleteBuilder = new android.app.AlertDialog.Builder(context);
                        deleteBuilder.setTitle(R.string.remove_book_title);
                        deleteBuilder.setMessage(R.string.remove_book_message);
                        deleteBuilder.setIcon(R.drawable.delete_icon);

                        deleteBuilder.setPositiveButton(R.string.okMessage, (dialogInterface3, i3) -> {
                            dialogInterface3.dismiss();

                            ReadingListManager.deleteBook(context, book);

                            //Notifying listAdapter to update ListView
                            books.remove(book);
                            listAdapter.notifyDataSetChanged();
                        });

                        deleteBuilder.setNegativeButton(R.string.cancelMessage,
                                ((dialogInterface3, i3) -> dialogInterface3.cancel()));

                        android.app.AlertDialog deleteDialog = deleteBuilder.create();
                        deleteDialog.show();

                        LayoutInflater deleteInflater = getLayoutInflater();
                        deleteBuilder.setView(deleteInflater.inflate(R.layout.readinglist_activity, null));
                        break;
                }
                dialogInterface.dismiss();
            } ));
            builder.setNegativeButton(R.string.cancelMessage, ((dialogInterface, i1) -> dialogInterface.cancel()));

            AlertDialog dialog = builder.create();
            dialog.show();

            LayoutInflater inflater = getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.readinglist_activity, null));
        });

        //Getting bottom navigation and defining its onClick behaviour
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Setting this activity as selected on bottom navigation bar
        bottomNavigationView.setSelectedItemId(R.id.reading_list);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch(item.getItemId()) {
                case R.id.add:
                    intent = new Intent(context, AddActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    break;
                case R.id.reading_list:
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
