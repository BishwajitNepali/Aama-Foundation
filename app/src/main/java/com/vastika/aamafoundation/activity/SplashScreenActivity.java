package com.vastika.aamafoundation.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.activeandroid.ActiveAndroid;
import com.vastika.aamafoundation.R;


public class SplashScreenActivity extends AppCompatActivity {
    public static final int TIME = 4 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ActiveAndroid.initialize(SplashScreenActivity.this);

        openNewActivity();
    }

    private void openNewActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,
                        MainActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, TIME);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, TIME);


    }


}
