package com.example.user.testserver;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.communication.IOnItemFocusChangedListener;
import org.eazegraph.lib.models.PieModel;

/**
 * Created by user on 2016-07-28.
 */
public class ChartActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serverpiechart);

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("server1", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("server2", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("server3", 35, Color.parseColor("#CDA67F")));

        mPieChart.startAnimation();

    }
}
