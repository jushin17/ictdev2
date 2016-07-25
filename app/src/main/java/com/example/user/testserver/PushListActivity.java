package com.example.user.testserver;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-25.
 */
public class PushListActivity extends ActionBarActivity {
    ListView listview;
    ListViewAdapter adapter;
    List<PushItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushlistactivity);




        listview = (ListView) findViewById(R.id.pushlist);


        // Adapter 생성
        adapter = new ListViewAdapter() ;
        prepareListData();
        // 리스트뷰 참조 및 Adapter달기

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                PushItem delete = new PushItem();
                delete = items.get(position);
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
                final PushItem finalDelete = delete;
                alertDlg.setPositiveButton("Delete", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                        db.deleteItem(finalDelete.getTime().toString());

                        prepareListData();
                        // 리스트뷰 참조 및 Adapter달기

                        listview.setAdapter(adapter);
                        dialog.dismiss();  // AlertDialog를 닫는다.
                    }
                });
                alertDlg.show();
            }
        });



    }


    private void prepareListData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        adapter.clearItem();
        items = new ArrayList<PushItem>();
        items = db.getAllItems();

        for (PushItem item : items) {
            adapter.addItem(item.getTitle().toString(),item.getText().toString(),item.getTime().toString());
        }
    }

}
