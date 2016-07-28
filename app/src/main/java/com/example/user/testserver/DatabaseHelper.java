package com.example.user.testserver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016-07-22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "PushManager";

    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String STRINGKEY = "stringkey";
    public static final String TABLENAME = "ErrMsg";
    private static final String CREATE_TABLE = "create table "+TABLENAME+"(ID "+
            "integer primary key autoincrement, "
            +TITLE+" text not null , "
            +TEXT+" text not null , "
            +TIME+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP , "
            +STRINGKEY+" text not null );";


    public static final String COUNTTABLE = "ErrorCount";
    public static final String COUNT = "count";
    private static final String CREATE_COUNT_TABLE = "create table "+COUNTTABLE+"(ID "+
            "integer primary key autoincrement, "
            +TITLE+ " text not null , "
            +TEXT+ " text not null , "
            +COUNT+ " integer );";

    public static final String SERVERTABLE = "ServerFlag";
    public static final String SERVER = "server";
    public static final String FLAG = "flag";
    private static final String CREATE_SERVER_TABLE = "create table "+SERVERTABLE+"(ID "+
            "integer primary key autoincrement, "
            +SERVER+ " text not null , "
            +FLAG+ " integer not null ); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_COUNT_TABLE);
        db.execSQL(CREATE_SERVER_TABLE);

        for(int i=1; i<4; i++) {
            ContentValues values = new ContentValues();
            values.put(SERVER, "server"+i);
            values.put(FLAG, 1);
            db.insert(SERVERTABLE,null,values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTTABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SERVERTABLE);

        // create new tables
        onCreate(db);
    }

    public boolean checkServer(String servername) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + SERVERTABLE + " WHERE "
                + SERVER + " = '" + servername + "'";

        Log.e("LOG", selectQuery);
        int flag=0;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            flag = c.getInt(c.getColumnIndex(FLAG));
        }

        if(flag == 1)
            return true;
        else
            return false;
    }

    public void updateServer(int server1, int server2, int server3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SERVER, "server1");
        values.put(FLAG, server1);

        db.update(SERVERTABLE, values, SERVER + " = ?", new String[]{String.valueOf("server1")});

        values = new ContentValues();
        values.put(SERVER, "server2");
        values.put(FLAG, server2);

        db.update(SERVERTABLE, values, SERVER + " = ?", new String[]{String.valueOf("server2")});

        values = new ContentValues();
        values.put(SERVER, "server3");
        values.put(FLAG, server3);

        db.update(SERVERTABLE, values, SERVER + " = ?", new String[]{String.valueOf("server3")});
    }

    public void createItems(PushItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        values.put(TEXT, item.getText());
        values.put(TIME, item.getTime());
        values.put(STRINGKEY, item.getTitle()+item.getText()+item.getTime());

        db.insert(TABLENAME, null, values);
        Log.e("database", "insert success");
    }

    public void createCount(CountItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        values.put(TEXT, item.getText());
        values.put(COUNT, item.getCount());

        db.insert(COUNTTABLE, null, values);
        Log.e("database", "insert success");
    }

    public void upgradeCount(CountItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        values.put(TEXT, item.getText());
        values.put(COUNT, item.getCount());

        db.update(COUNTTABLE, values, TEXT + " = ?", new String[]{String.valueOf(item.getText())});
        Log.e("database", "update success");
    }

    public boolean checkItem(String err_text) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + COUNTTABLE + " WHERE "
                + TEXT + " = '" + err_text + "'";

        Log.e("LOG", selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);
        Log.e("LOG", String.valueOf(c.getCount()));
        if (c.getCount() <= 0) {
            return false;
        }
        c.close();
        return true;
    }

    public int checkCount(String err_text) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + COUNTTABLE + " WHERE "
                + TEXT + " = '" + err_text + "'";

        Log.e("LOG", selectQuery);


        Cursor c = db.rawQuery(selectQuery, null);
        int countresult =0;
        if (c.moveToFirst()) {
            countresult = c.getInt(c.getColumnIndex(COUNT));
        }

        return countresult;
    }



    public List<CountItem> getAllCount() {
        // open database
        SQLiteDatabase db = this.getWritableDatabase();


        List<CountItem> items = new ArrayList<CountItem>();
        String selectQuery = "SELECT * FROM " + COUNTTABLE;

        Cursor c = db.rawQuery(selectQuery, null);



        if (c.moveToFirst()) {
            do {
                CountItem item = new CountItem();
                item.setTitle(c.getString(c.getColumnIndex(TITLE)));
                item.setText(c.getString(c.getColumnIndex(TEXT)));
                item.setCount(c.getInt(c.getColumnIndex(COUNT)));
                Log.e("get count test ", String.valueOf(c.getColumnIndex(COUNT)));
                items.add(item);
            } while (c.moveToNext());
        }
        return items;
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

    public void deleteItem(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLENAME+ " WHERE " + STRINGKEY + " = '" + key + "'";
        Log.e("query test ", query);
        db.execSQL(query);
    }

    public void deleteCount(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + COUNTTABLE+ " WHERE " + TEXT + " = '" + key + "'";
        Log.e("query test ", query);
        db.execSQL(query);
    }
}
