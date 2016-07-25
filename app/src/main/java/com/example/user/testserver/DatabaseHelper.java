package com.example.user.testserver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PushManager";

    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String TABLENAME = "ErrMsg";
    private static final String CREATE_TABLE = "create table "+TABLENAME+"(ID "+
            "integer primary key autoincrement, "
            +TITLE+" text not null , "
            +TEXT+" text not null , "
            +TIME+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);

        // create new tables
        onCreate(db);
    }

    public void createItems(PushItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        values.put(TEXT, item.getText());
        values.put(TIME, item.getTime());

        db.insert(TABLENAME, null, values);
        Log.e("database", "insert success");
    }



    public List<PushItem> getAllItems() {
        // open database
        SQLiteDatabase db = this.getWritableDatabase();

        List<PushItem> items = new ArrayList<PushItem>();
        String selectQuery = "SELECT * FROM " + TABLENAME;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                PushItem item = new PushItem();
                item.setTitle(c.getString(c.getColumnIndex(TITLE)));
                item.setText(c.getString(c.getColumnIndex(TEXT)));
                item.setTime(c.getString(c.getColumnIndex(TIME)));
                items.add(item);
            } while (c.moveToNext());
        }
        return items;
    }
}
