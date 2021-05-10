package com.example.ebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, "EBook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table SubBooks(id INTEGER primary key AUTOINCREMENT, title Text NOT NULL UNIQUE, pdf_path Text NOT NULL, parent_book_id Int NOT NULL, isfavorite INTEGER NOT NULL, FOREIGN KEY (parent_book_id) REFERENCES Books(id))");
        db.execSQL("create Table Books(id INTEGER primary key AUTOINCREMENT, title Text NOT NULL UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Books");
        db.execSQL("drop Table if exists SubBooks");
    }

    public Cursor retriveData(String table, String where_clause) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (where_clause != "") {
            query = "select * from " + table + " where " + where_clause;
        }
        else {
            query = "select * from " + table;
        }
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Boolean insertBooks(String table, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        Long result =  db.insert(table, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean insertSubBook(String title, String pdf_path, Integer parentBookId, Integer isfavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("pdf_path", pdf_path);
        contentValues.put("parent_book_id", parentBookId);
        contentValues.put("isfavorite", isfavorite);
        Long result =  db.insert("SubBooks", null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean updateSubBook(String title, Integer isfavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isfavorite", isfavorite);
        Cursor cursor = db.rawQuery("SELECT * FROM SubBooks" + " WHERE title = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            Integer result = db.update("SubBooks", contentValues, "title=?", new String[]{title});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public void deleteBook(String table, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table + " WHERE title = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            db.delete(table, "title=?", new String[]{title});
        }
    }
}
