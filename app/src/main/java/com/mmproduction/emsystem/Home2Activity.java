package com.mmproduction.emsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Home2Activity extends AppCompatActivity {

    TextView txtNewBetTimer;
    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;

    private Toolbar mtoolbar;

    AdapterViewFlipper adapterViewFlipper;
    int[] Images={
            R.drawable.img2,
            R.drawable.infoss,
            R.drawable.pastdatass,
            R.drawable.reportss
    };
    String[] name={
            "EMS",
            "Easy To find Boothlocation",
            "Find Past Data",
            "Report Mal-Practices"
    };

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //txtNewBetTimer = (TextView) findViewById(R.id.tv1);

    //    mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        initNavigationDrawer();
        // mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);
        mDrawerlayout = (DrawerLayout)findViewById(R.id.activity_home_drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setTitle("Ems");


        //setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("EMS");
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapterViewFlipper=(AdapterViewFlipper)findViewById(R.id.adapterViewFlipper);
        ImageCustomAdapter imageCustomAdapter =new ImageCustomAdapter(getApplicationContext(),name,Images);
        adapterViewFlipper.setAdapter(imageCustomAdapter);
        adapterViewFlipper.setFlipInterval(3500);
        adapterViewFlipper.setAutoStart(true);



        txtTimerDay = (TextView) findViewById(R.id.txtTimerDay);
        txtTimerHour = (TextView) findViewById(R.id.txtTimerHour);
        txtTimerMinute = (TextView) findViewById(R.id.txtTimerMinute);
        txtTimerSecond = (TextView) findViewById(R.id.txtTimerSecond);
        tvEvent = (TextView) findViewById(R.id.tvhappyevent);

        countDownStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2019-01-01");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText("" + String.format("%02d", minutes));
                        txtTimerSecond.setText("" + String.format("%02d", seconds));
                    } else {
                        tvEvent.setVisibility(View.VISIBLE);
                        tvEvent.setText("Happy New Year!");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout10).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout11).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout12).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout13).setVisibility(View.GONE);
        findViewById(R.id.textView1).setVisibility(View.GONE);
        findViewById(R.id.textView2).setVisibility(View.GONE);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.without_login_user_menu, menu);
        return true;
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }*/

    public void initNavigationDrawer() {

        NavigationView mnavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mnavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.Information:{
                        startActivity(new Intent(Home2Activity.this, InformationActivity.class));
                        break;
                    }
                    case R.id.about: {
                        startActivity(new Intent(Home2Activity.this, AboutusActivity.class));
                        break;
                    }
                    case R.id.pastdata: {
                        startActivity(new Intent(Home2Activity.this, ResultActivity.class));
                        break;
                    }
                    case R.id.login: {
                        startActivity(new Intent(Home2Activity.this, LoginActivity.class));
                        break;
                    }

            /*case R.id.Account_setting: {
                startActivity(new Intent(HomeActivity.this, AccountsettingActivity.class));
                break;
            }*/
                }
                return true;
            }
        });
    }
}
