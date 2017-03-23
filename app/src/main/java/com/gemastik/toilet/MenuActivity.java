package com.gemastik.toilet;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setTitle("SMARTLET APPS");

        ImageView imgbtnInfo = (ImageView) findViewById(R.id.imgbtnInfo);

        imgbtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgbtnRiwayat = (ImageView) findViewById(R.id.imgbtnRiwayat);

        imgbtnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, RiwayatActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgbtnHasil = (ImageView) findViewById(R.id.imgbtnHasil);

        imgbtnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuActivity.this, HasilActivity.class);
                startActivity(intent);
            }
        });
    }
}
