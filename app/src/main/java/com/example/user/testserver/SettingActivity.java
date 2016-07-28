package com.example.user.testserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-07-25.
 */
public class SettingActivity extends ActionBarActivity {
    CheckBox server1;
    CheckBox server2;
    CheckBox server3;
    int server1_flag = 0;
    int server2_flag = 0;
    int server3_flag = 0;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity);

        server1 = (CheckBox) findViewById(R.id.server1);
        server2 = (CheckBox) findViewById(R.id.server2);
        server3 = (CheckBox) findViewById(R.id.server3);

        db = new DatabaseHelper(getApplicationContext());

        if(db.checkServer("server1"))
            server1.setChecked(true);
        else
            server1.setChecked(false);
        if(db.checkServer("server2"))
            server2.setChecked(true);
        else
            server2.setChecked(false);
        if(db.checkServer("server3"))
            server3.setChecked(true);
        else
            server3.setChecked(false);


        Button submit = (Button) findViewById(R.id.submitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(getApplicationContext());

                if(server1.isChecked()) {
                    server1_flag = 1;
                }
                if(server2.isChecked()) {
                    server2_flag = 1;
                }
                if(server3.isChecked()) {
                    server3_flag = 1;
                }
                db.updateServer(server1_flag,server2_flag,server3_flag);

                String toastmsg = new String();

                if(db.checkServer("server1"))
                    toastmsg+="server1 ";
                if(db.checkServer("server2"))
                    toastmsg+="server2 ";
                if(db.checkServer("server3"))
                    toastmsg+="server3";

                Toast.makeText(getApplicationContext(), "알람 받을 서버는 "+toastmsg +" 입니다.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }

}