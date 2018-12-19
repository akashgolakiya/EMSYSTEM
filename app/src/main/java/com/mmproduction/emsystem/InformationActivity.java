package com.mmproduction.emsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private  TextView t1, t2, t3, t4, t5, t6;

    private  Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);




        t1 = (TextView) findViewById(R.id.b1);
        t2 = (TextView) findViewById(R.id.b2);
        t3 = (TextView) findViewById(R.id.b3);
        t4 = (TextView) findViewById(R.id.b4);
        t5 = (TextView) findViewById(R.id.b5);
        t6 = (TextView) findViewById(R.id.b6);
        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("InformationActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Vadla circle"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Vadla circle"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Ghogha Circle"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Tarsamiya"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Virani Circle"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Dolat Anantvaliya,Bhavnagar"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("HillDrive,Bhavnagar"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}


