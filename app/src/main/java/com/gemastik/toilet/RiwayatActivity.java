package com.gemastik.toilet;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gemastik.toilet.adapter.RiwayatListAdapter;
import com.gemastik.toilet.object.DataRiwayat;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {
    LineChart chart;
    Firebase firebase;
    List<Entry> entries;
    ArrayList<String> labels;
    ArrayList<Integer> colors;
    ArrayList<DataRiwayat> items;
    RecyclerView listview;
    LinearLayoutManager linearLayoutManager;
    LinearLayout mainLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        getSupportActionBar().setTitle("Records");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chart = (LineChart)findViewById(R.id.chart);
        chart.setNoDataText("Loading data...");
        Firebase.setAndroidContext(this);

        mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        listview = (RecyclerView)findViewById(R.id.riwayat_data);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        listview.setHasFixedSize(false);
        listview.setLayoutManager(linearLayoutManager);

        firebase = new Firebase("https://smartlet-632ad.firebaseio.com/");
        firebase.child("record").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entries = new ArrayList<Entry>();
                labels = new ArrayList<String>();
                colors = new ArrayList<Integer>();
                items = new ArrayList<DataRiwayat>();

                mainLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                int number = 0;
                for(DataSnapshot object : dataSnapshot.getChildren()){
                    float level = Float.parseFloat(object.child("level").getValue().toString());
                    entries.add(new Entry((float) number, level));
                    labels.add(object.child("created_at").getValue().toString());
                    colors.add(colorSelection(level));
                    items.add(new DataRiwayat(Integer.parseInt(object.child("level").getValue().toString()),object.child("created_at").getValue().toString(),object.child("location").getValue().toString()));
                    number++;
                }
                //set Axis
                chart.getAxisRight().setEnabled(false);
                chart.getXAxis().setDrawGridLines(false);
                YAxis yAxis = chart.getAxisLeft();
                yAxis.setAxisMaxValue(6);
                yAxis.setAxisMinValue(0);

                LineDataSet lineDataSet = new LineDataSet(entries, "Level indication");
                LineData dataSet = new LineData(lineDataSet);
                lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                lineDataSet.setColor(Color.parseColor("#00897B"));
                lineDataSet.setColors(new int [] {
                        getResources().getColor(R.color.colorLevel6),
                        getResources().getColor(R.color.colorLevel5),
                        getResources().getColor(R.color.colorLevel4),
                        getResources().getColor(R.color.colorLevel3),
                        getResources().getColor(R.color.colorLevel2),
                        getResources().getColor(R.color.colorLevel1)
                });
                lineDataSet.setCircleColors(colors);
                lineDataSet.setDrawCircleHole(false);


                //Chart Interaction Control
                chart.setTouchEnabled(true);
                chart.setDragEnabled(true);
                chart.setScaleEnabled(true);
                chart.setPinchZoom(true);

                //set Dataset
                chart.setData(dataSet);

                //set Description
                chart.getDescription().setText("Level Indication");

                chart.animateXY(2000,2000);

                chart.invalidate();

                //set Listview
                RiwayatListAdapter adapter = new RiwayatListAdapter(getApplicationContext(), items);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public int colorSelection(float level){
        int levelPoint = Math.round(level);
        switch (levelPoint){
            case 6:
                return Color.RED;
            case 5:
                return Color.RED;
            case 4:
                return Color.RED;
            case 3:
                return Color.YELLOW;
            case 2:
                return Color.GREEN;
            case 1:
                return Color.GREEN;
            default:
                return Color.GREEN;
        }
    }
}
