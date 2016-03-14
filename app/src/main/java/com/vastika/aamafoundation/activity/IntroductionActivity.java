package com.vastika.aamafoundation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vastika.aamafoundation.R;

public class IntroductionActivity extends AppCompatActivity {

    ImageView image;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image=(ImageView)findViewById(R.id.introLogo);
        Picasso.with(this).load(R.drawable.logo).fit().into(image);
    }
}
