package com.example.user.testserver;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-25.
 */
public class PushListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor mCursor;

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        List<PushItem> items = new ArrayList<PushItem>();
        items = db.getAllItems();

        for (PushItem item : items) {
            adapter.addItem(item.getTitle().toString(),item.getText().toString(),item.getTime().toString());
        }


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.pushlist);
        listview.setAdapter(adapter);


    }

}
