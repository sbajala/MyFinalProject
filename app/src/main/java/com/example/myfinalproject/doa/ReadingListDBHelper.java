package com.example.myfinalproject.doa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myfinalproject.Book;

public class ReadingListDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myReadingList.db";
    private static final int DB_VERSION = 2;

    //Creating reading_list table
    private static final String TABLE_READING_LIST = "reading_list";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_READING_STATUS = "reading_status";
    private static final String CREATE_READING_LIST_TABLE_QUERY = "CREATE TABLE "
            + TABLE_READING_LIST + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_AUTHOR + " TEXT NOT NULL, "
            + COLUMN_NOTE + " TEXT NOT NULL, "
            + COLUMN_READING_STATUS + " TEXT NOT NULL);";


    public ReadingListDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_READING_LIST_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_READING_LIST);
        onCreate(db);
    }

    /** Select all books from reading list */
    public Cursor getBooks() {
        String query = "SELECT * FROM " + TABLE_READING_LIST;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //Checking if there is data in DB
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /** Retrieves book count of reading statuses
     * @param readingStatus Book's reading status */
    public Cursor getBookCount(String readingStatus) {
        String query = "SELECT COUNT(*) FROM " + TABLE_READING_LIST + " WHERE " +
                COLUMN_READING_STATUS + " = \"" + readingStatus + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //Checking if there is data in DB
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /** Retrieves books by readingStatus
     * @param readingStatus Book's reading status */
    public Cursor getBooksByReadingStatus(String readingStatus) {
        String query = "SELECT * FROM " + TABLE_READING_LIST + " WHERE " + COLUMN_READING_STATUS
                + " = \"" + readingStatus + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        //Checking if there is data in DB
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /** Add book to reading list
     * @param book Book object
     * */
    public boolean addBook(Book book) {
        //getWriteableDatabase() allows us to write to our table
        SQLiteDatabase db = this.getWritableDatabase();

        //Inserting data in DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, book.getTitle());
        contentValues.put(COLUMN_AUTHOR, book.getAuthor());
        contentValues.put(COLUMN_NOTE, book.getNote());
        contentValues.put(COLUMN_READING_STATUS, book.getReadingStatus().name());

        long result = db.insert(TABLE_READING_LIST, null, contentValues);

        return (result != -1);
    }

    /** Update book's information (Title, Author, Note) */
    public boolean updateBookInfo(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, book.getTitle());
        contentValues.put(COLUMN_AUTHOR, book.getAuthor());
        contentValues.put(COLUMN_NOTE, book.getNote());

        int result = db.update(TABLE_READING_LIST, contentValues, "id=?",
                new String[] {String.valueOf(book.getId())});
        db.close();

        return result ==1;
    }

    /** Update book's reading status
     * @param book Book object */
    public boolean updateBookReadingStatus(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_READING_STATUS, book.getReadingStatus().name());

        int result = db.update(TABLE_READING_LIST, contentValues, "id=?",
                new String[] {String.valueOf(book.getId())});
        db.close();

        return result == 1;
    }

    /** Delete book from reading list
     * @param id book ID*/
    public boolean deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_READING_LIST, "id=?",
                new String[] {String.valueOf(id)});
        db.close();

        return rowsDeleted == 1;
    }

}
