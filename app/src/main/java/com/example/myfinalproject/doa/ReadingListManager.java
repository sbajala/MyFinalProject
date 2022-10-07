package com.example.myfinalproject.doa;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.myfinalproject.Book;
import com.example.myfinalproject.enums.ReadingStatus;

import java.util.ArrayList;

/** Manages data from database */
public class ReadingListManager {

    /** Retrieving all books from reading list */
    public static ArrayList<Book> getBooks(Context context) {
        ArrayList<Book> libraryBooks = null;
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);
        Cursor cursor = databaseHelper.getBooks();

        if (cursor.isBeforeFirst()) {
            libraryBooks = new ArrayList<>();
        }

        while (cursor.moveToNext()) {
            int bookId = cursor.getInt(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String note = cursor.getString(3);
            String readingStatusString = cursor.getString(4);

            ReadingStatus readingStatus = ReadingStatus.NOT_STARTED;
            switch(readingStatusString) {
                case "COMPLETED":
                    readingStatus = ReadingStatus.COMPLETED;
                    break;
                case "IN_PROGRESS":
                    readingStatus = ReadingStatus.IN_PROGRESS;
                    break;
                case "NOT_STARTED":
                    readingStatus = ReadingStatus.NOT_STARTED;
                    break;
            }
            libraryBooks.add(new Book(bookId, title, author, note, readingStatus));
        }

        cursor.close();
        return libraryBooks;
    }

    /** Adding book to reading list */
    public static void addBook(Context context, Book book) {
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);

        String message;
        //Validating user input
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            message = "Must enter a title and author";
        } else {
            //Adding book to DB
            if (databaseHelper.addBook(book)) {
                message = "Book has been added.";
            } else {
                message = "Failed. Book was not added.";
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    /** Updating book's information (Title, author, note) */
    public static void updateBookInfo(Context context, Book book) {
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);

        String message;
        //Validating user input
        if(book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            message = "Must enter a title and author";
        } else {
            if (databaseHelper.updateBookInfo(book)) {
                message = "Book info was updated.";
            } else {
                message = "Failed. Book was no updated";
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** Updating book's reading status */
    public static void updateBookReadingStatus(Context context, Book book) {
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);

        String message;
        if (databaseHelper.updateBookReadingStatus(book)) {
            message = "Reading status was updated.";
        } else {
            message = "Failed. Book was not updated.";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** Deleting book from reading list */
    public static void deleteBook(Context context, Book book) {
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);
        String message;
        if (databaseHelper.deleteBook(book.getId())) {
            message = "Book removed.";
        } else {
            message = "Failed. Book was not removed.";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** Retrieving book count for specified reading status */
    public static int getBookCount(Context context, String readingStatus) {
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);
        Cursor cursor = databaseHelper.getBookCount(readingStatus);
        int count = 0;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }

    /** Retrieving books by reading status */
    public static ArrayList<Book> getBooksByReadingStatus(Context context, String readingStatus) {
        ArrayList<Book> filteredBooks = null;
        ReadingListDBHelper databaseHelper = new ReadingListDBHelper(context);
        Cursor cursor = databaseHelper.getBooksByReadingStatus(readingStatus);

        if (cursor.isBeforeFirst()) {
            filteredBooks = new ArrayList<>();
        }

        while (cursor.moveToNext()) {
            int bookId = cursor.getInt(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String note = cursor.getString(3);
            String readingStatusString = cursor.getString(4);

            ReadingStatus reading_status = ReadingStatus.NOT_STARTED;
            switch(readingStatusString) {
                case "COMPLETED":
                    reading_status = ReadingStatus.COMPLETED;
                    break;
                case "IN_PROGRESS":
                    reading_status = ReadingStatus.IN_PROGRESS;
                    break;
                case "NOT_STARTED":
                    reading_status = ReadingStatus.NOT_STARTED;
                    break;
            }
            filteredBooks.add(new Book(bookId, title, author, note, reading_status));
        }

        cursor.close();
        return filteredBooks;
    }

}
