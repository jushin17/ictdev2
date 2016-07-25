package com.example.user.testserver;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-25.
 */
public class PushListActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushlistactivity);


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        ListView listview = (ListView) findViewById(R.id.pushlist);
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        List<PushItem> items = new ArrayList<PushItem>();
        items = db.getAllItems();

        for (PushItem item : items) {
            adapter.addItem(item.getTitle().toString(),item.getText().toString(),item.getTime().toString());
        }


        // 리스트뷰 참조 및 Adapter달기

        listview.setAdapter(adapter);


    }

}
