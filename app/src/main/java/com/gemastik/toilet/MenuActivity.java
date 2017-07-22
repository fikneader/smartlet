package com.gemastik.toilet;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.iid.FirebaseInstanceId;

public class MenuActivity extends AppCompatActivity {
    private Intent intent;
    private static final String TAG = "FirebaseIDServiceTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startService(new Intent(getApplicationContext(), EventPusherService.class));

        getSupportActionBar().setTitle("Smarlet Apps");

        CardView imgbtnInfo = (CardView) findViewById(R.id.infoBtn);

        imgbtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        CardView imgbtnRiwayat = (CardView) findViewById(R.id.recordBtn);

        imgbtnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, RiwayatActivity.class);
                startActivity(intent);

            }
        });

        CardView imgbtnHasil = (CardView) findViewById(R.id.colorBtn);

        imgbtnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, HasilActivity.class);
                startActivity(intent);
            }
        });

        CardView imgbtnPanduan = (CardView) findViewById(R.id.guideBtn);
        imgbtnPanduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, PanduanActivity.class);
                startActivity(intent);

            }
        });
    }
}
