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
    private final static int DEFAULT_COLOR = Color.GRAY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchartactivity);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        float[] errorcount= new float[9];
        errorcount = db.countError();

        String[] mLabels= {"Serv1 err1", "Serv1 err2", "Serv1 err3", "Serv2 err1", "Serv2 err2", "Serv2 err3", "Serv3 err1", "Serv3 err2", "Serv3 err3"};

        BarChartView barChart = (BarChartView) findViewById(R.id.barchart);



        BarSet barSet = new BarSet(mLabels, errorcount);
        barSet.setColor(DEFAULT_COLOR);

        barChart.addData(barSet);
        barChart.show();
    }
}
