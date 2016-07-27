package com.example.user.testserver;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-25.
 */
public class CountPushListActivity extends ActionBarActivity {
    ListView listview;
    ListViewAdapter adapter;
    List<CountItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countpushlistactivity);




        listview = (ListView) findViewById(R.id.countpushlist);


        // Adapter 생성
        adapter = new ListViewAdapter() ;
        prepareListData();
        // 리스트뷰 참조 및 Adapter달기

        listview.setAdapter(adapter);


    }

    private void prepareListData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        items = new ArrayList<CountItem>();
        items = db.getAllCount();

        for (CountItem item : items) {
            adapter.addItem(item.getTitle().toString(),item.getText().toString(),Integer.toString(item.getCount()));
        }
    }
/*
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
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
    }
    */

}
