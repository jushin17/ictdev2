package com.example.user.testserver;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

/**
 * Created by user on 2016-07-28.
 */
public class BarChartActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchartactivity);
        //DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        String[] mLabels= {"A", "B", "C", "D"};
        float [] mValues = {6.5f, 8.5f, 2.5f, 10f};

        BarChartView barChart = (BarChartView) findViewById(R.id.barchart);



        BarSet barSet = new BarSet(mLabels, mValues);

        barChart.addData(barSet);
    }
}
