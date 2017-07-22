package com.gemastik.toilet;

import android.Manifest;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class PanduanActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Smartlet Fingerprint", "Scan fingerprints after urination using SmartLet.", R.drawable.app_fingerprint, Color.parseColor("#f64c73")));
        addSlide(AppIntroFragment.newInstance("Statistics", "View results of urine content data on Smartphone. Shows the dehydration level and glucose of your body.", R.drawable.app_statistic, Color.parseColor("#20d2bb")));
        addSlide(AppIntroFragment.newInstance("Suggestions and Recommendations", "View suggestions and health recommendations based on the results of your data.", R.drawable.app_drinking, Color.parseColor("#3395ff")));

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices
        //setNavBarColor(Color.parseColor("#3F51B5"));

        // Turn vibration on and set intensity
        // NOTE: you will need to ask VIBRATE permission in Manifest if you haven't already
        setVibrate(true);
        setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        setFadeAnimation(); // OR
        setZoomAnimation(); // OR
        setFlowAnimation(); // OR
        setSlideOverAnimation(); // OR
        setDepthAnimation(); // OR

        // Permissions -- takes a permission and slide number
        askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}

