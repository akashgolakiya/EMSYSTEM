package com.mmproduction.emsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {

    private  Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("RESULT OF PAST YEAR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
