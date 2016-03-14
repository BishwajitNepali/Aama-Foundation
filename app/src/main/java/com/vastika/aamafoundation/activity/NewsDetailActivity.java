package com.vastika.aamafoundation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vastika.aamafoundation.R;

public class NewsDetailActivity extends AppCompatActivity {

    TextView newstitle;
    TextView newsdescription;
    String title;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        newstitle=(TextView)findViewById(R.id.newsTitle);
        newsdescription=(TextView)findViewById(R.id.newsDesc);

        Intent intent= getIntent();
        title=intent.getStringExtra("title");
        description=intent.getStringExtra("description");
        newstitle.setText(title);
        newsdescription.setText(description);

    }


}
