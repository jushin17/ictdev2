package com.example.user.testserver;

/**
 * Created by shcard on 2016. 8. 2..
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;

public class ExpandableListForItems extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listChildQuantity;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.explistforerr);


        // preparing list data
        prepareListData();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.errors);
        Log.e("checkcheck", "2");

        Log.e("checkcheck", "3");
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, listChildQuantity);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        Log.e("checkcheck", "4");

        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String stylenum = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                //Intent intent = new Intent(ExpandableListForItems.this, ModifyItem.class);
                //intent.putExtra("stylenumber",stylenum);
                //startActivity(intent);
                return false;
            }

        });
    }

    /*
     * Preparing the list data
     */

    private void prepareListData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listChildQuantity = new HashMap<String, List<String>>();


        float[] errors = new float[9];
        errors = db.countError();

        listDataHeader.add("Server1");
        listDataHeader.add("Server2");
        listDataHeader.add("Server3");

        Log.e("찍히나", "0");



        for(int i=1;i<4;i++) {
            List<String> Error = new ArrayList<String>();
            List<String> Quantity = new ArrayList<String>();

            for(int j=1;j<4;j++) {
                Error.add("Error "+j);
                int num = (int) errors[(i-1)*3+j-1];
                Quantity.add(String.valueOf(num));
            }

            listDataChild.put(listDataHeader.get(i-1), Error);
            listChildQuantity.put(listDataHeader.get(i-1), Quantity);
        }

        db.close();
    }
}