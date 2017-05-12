package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "notes.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NOTES = "notes";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NOTESCONTENT = "noteContent";
	private static final String COLUMN_STARS = "stars";


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSql = "CREATE TABLE " + TABLE_NOTES +  "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NOTESCONTENT + " TEXT,"
				+ COLUMN_STARS + " INTEGER )";
		db.execSQL(createTableSql);
		Log.i("info" ,"created tables");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		// Get an instance of the database for writing
		SQLiteDatabase db = this.getWritableDatabase();
		// We use ContentValues object to store the values for
		//  the db operation
		ContentValues values = new ContentValues();
		// Store the column name as key and the description as value
		values.put(COLUMN_NOTESCONTENT, noteContent);
		// Store the column name as key and the date as value
		values.put(COLUMN_STARS, stars);
		// Insert the row into the TABLE_TASK
		db.insert(TABLE_NOTES, null, values);
		// Close the database connection
		db.close();
	}

	public ArrayList<Note> getAllNotes() {
		ArrayList<Note> notes = new ArrayList<Note>();
		String selectQuery = "SELECT " + COLUMN_ID + ", "
				+ COLUMN_NOTESCONTENT + ", "
				+ COLUMN_STARS
				+ " FROM " + TABLE_NOTES;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String content = cursor.getString(1);
				int stars = cursor.getInt(2);
				Note obj = new Note(id, content, stars);
				notes.add(obj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return notes;
	}


    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
		String selectQuery = "SELECT " + COLUMN_NOTESCONTENT
				+ " FROM " + TABLE_NOTES;


        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {

				notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
