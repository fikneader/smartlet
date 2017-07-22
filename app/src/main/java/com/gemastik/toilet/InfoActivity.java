package com.gemastik.toilet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gemastik.toilet.object.DataRecord;
import com.gemastik.toilet.object.DataRiwayat;

public class InfoActivity extends AppCompatActivity {
    Firebase firebase;
    ImageView status1, status2, status3, status4, status5, status6, statusArrow1, statusArrow2, statusArrow3, statusArrow4, statusArrow5, statusArrow6;
    TextView usernameText, conditionText, levelText, placeText, timeText, recomendationText;
    String username, condition, place, time, recomendation;
    int level;
    LinearLayout main_layout;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle("Body Condition Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Firebase.setAndroidContext(this);

        status1 = (ImageView)findViewById(R.id.status1);
        status2 = (ImageView)findViewById(R.id.status2);
        status3 = (ImageView)findViewById(R.id.status3);
        status4 = (ImageView)findViewById(R.id.status4);
        status5 = (ImageView)findViewById(R.id.status5);
        status6 = (ImageView)findViewById(R.id.status6);
        statusArrow1 = (ImageView)findViewById(R.id.statusArrow1);
        statusArrow2 = (ImageView)findViewById(R.id.statusArrow2);
        statusArrow3 = (ImageView)findViewById(R.id.statusArrow3);
        statusArrow4 = (ImageView)findViewById(R.id.statusArrow4);
        statusArrow5 = (ImageView)findViewById(R.id.statusArrow5);
        statusArrow6 = (ImageView)findViewById(R.id.statusArrow6);

        usernameText = (TextView)findViewById(R.id.usernameText);
        conditionText = (TextView)findViewById(R.id.conditionText);
        levelText = (TextView)findViewById(R.id.levelText);
        placeText = (TextView)findViewById(R.id.locationText);
        timeText = (TextView)findViewById(R.id.timeText);
        recomendationText = (TextView)findViewById(R.id.recomendationText);

        main_layout = (LinearLayout)findViewById(R.id.main_layout);
        loading = (ProgressBar)findViewById(R.id.progress_bar);


        firebase = new Firebase("https://smartlet-632ad.firebaseio.com/");

        firebase.child("record").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int number = 1;
                for(DataSnapshot object : dataSnapshot.getChildren()) {
                    if(number == 1){
                        level = Integer.parseInt(object.child("level").getValue().toString());
                        username = "Hernawan";
                        place = object.child("location").getValue().toString();
                        time = object.child("created_at").getValue().toString();
                        condition = getCondition(level);
                        recomendation = getRecomendation(level);
                    }else{
                        break;
                    }
                    number++;
                }
                usernameText.setText(username);
                conditionText.setText(condition);
                levelText.setText("Dehydration Level "+level);
                placeText.setText(place);
                timeText.setText(time);
                setIndicator(level);
                recomendationText.setText(recomendation);
                loading.setVisibility(View.GONE);
                main_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public String getCondition(int level){
        switch (level){
            case 1:
                return "Healthy";
            case 2:
                return "Good";
            case 3:
                return "Warning";
            case 4:
                return "Mild dehydration";
            case 5:
                return "Medium dehydration";
            case 6:
                return "Severe dehydration";
            default:
                return "Healthy";
        }
    }

    public String getRecomendation(int level){
        switch (level){
            case 1:
                return "Keep your drinking patterns.";
            case 2:
                return "Drinking 1/4 liter water or 1 glass in 1 hour. Keep exercising and reduce the sweet foods.";
            case 3:
                return "Drink 1/2 liter water or 2 glasses in 1 hour.";
            case 4:
                return "Drink 1 liter water or 4 glasses in 2 hours.";
            case 5:
                return "Drinking water 1 - 1.5 liters in 2 hours.";
            case 6:
                return "Drinking water at least 1.5 liters in 2 hours. If necessary consult to your doctor.";
            default:
                return "Keep your drinking patterns.";
        }
    }

    public void setIndicator(int level){
        statusArrow1.setVisibility(View.GONE);
        statusArrow2.setVisibility(View.GONE);
        statusArrow3.setVisibility(View.GONE);
        statusArrow4.setVisibility(View.GONE);
        statusArrow5.setVisibility(View.GONE);
        statusArrow6.setVisibility(View.GONE);

        //Set Arrow
        setIndicatorArrow(level);

        status1.setBackgroundColor(getResources().getColor(R.color.colorLevel1));
        status2.setBackgroundColor(getResources().getColor(R.color.colorLevel2));
        status3.setBackgroundColor(getResources().getColor(R.color.colorLevel3));
        status4.setBackgroundColor(getResources().getColor(R.color.colorLevel4));
        status5.setBackgroundColor(getResources().getColor(R.color.colorLevel5));
        status6.setBackgroundColor(getResources().getColor(R.color.colorLevel6));

        //Set Background
        setIndicatorBackground(level);
    }

    public void setIndicatorArrow(int level) {
        switch (level) {
            case 1:
                statusArrow1.setVisibility(View.VISIBLE);
                break;
            case 2:
                statusArrow2.setVisibility(View.VISIBLE);
                break;
            case 3:
                statusArrow3.setVisibility(View.VISIBLE);
                break;
            case 4:
                statusArrow4.setVisibility(View.VISIBLE);
                break;
            case 5:
                statusArrow5.setVisibility(View.VISIBLE);
                break;
            case 6:
                statusArrow6.setVisibility(View.VISIBLE);
                break;
            default:
                statusArrow1.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setIndicatorBackground(int level){
        switch (level) {
            case 1:
                status1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 2:
                status2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 3:
                status3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 4:
                status4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 5:
                status5.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case 6:
                status6.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            default:
                status1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }
}
