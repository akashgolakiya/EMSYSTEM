package com.mmproduction.emsystem;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class AboutusActivity extends AppCompatActivity {


    private  Toolbar mtoolbar;

    ListView listViewobj;

    RelativeLayout facebook,instagram;
    //Button policy;

    String[] title = {"MILAN MIYANI", "AKASH GOLAKIYA", "RAJNISH GUJARATI", "PARAM MORADIYA", "JAIMIK NAVADIYA" };

    String[] subtitle = {"7284-067-682", "9773-294-960", "8460-564-604", "9033-377-908" ,"9173026738"};

    /*Integer[] img = {R.drawable., R.drawable.pavbhaji, R.drawable.khavsa, R.drawable.dabeli};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        facebook = (RelativeLayout) findViewById(R.id.facebook);
        instagram = (RelativeLayout) findViewById(R.id.instagram);
      //  policy = (Button)findViewById(R.id.policy);

       /* listViewobj = (ListView) findViewById(R.id.listview);
        AboutusCustomaddapter custobj = new AboutusCustomaddapter(AboutusActivity.this, title, subtitle);
        listViewobj.setAdapter(custobj);*/

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = openFacebook(AboutusActivity.this);
                startActivity(i);
            }
        });


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/m_m_a_150/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/m_m_a_150")));
                }
            }
        });

        /*policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
    public static Intent openFacebook(Context context)
    {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/100007949177744"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/akash.golakiya.7"));
        }
    }
}
