package com.example.user.testserver;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.model.BarSet;
import com.db.chart.model.ChartSet;
import com.db.chart.view.BarChartView;

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
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        int[] servercount = new int[3];
        servercount = db.countServer();

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("server1", servercount[0], Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("server2", servercount[1], Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("server3", servercount[2], Color.parseColor("#CDA67F")));

        mPieChart.startAnimation();


        String[] mLabels= {"A", "B", "C", "D"};
        float [] mValues = {6.5f, 8.5f, 2.5f, 10f};

        BarChartView barChart = (BarChartView) findViewById(R.id.barchart);



        BarSet barSet = new BarSet(mLabels, mValues);

        barChart.addData(barSet);

    }
}
