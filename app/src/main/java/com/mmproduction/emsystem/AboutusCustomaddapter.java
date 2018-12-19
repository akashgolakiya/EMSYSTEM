package com.mmproduction.emsystem;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class AboutusCustomaddapter extends ArrayAdapter {

    Activity context;
    String[] title;
    String[] subtitle;


    public AboutusCustomaddapter(Activity context, String[] title, String[] subtitle) {
        super(context, R.layout.listlayout, title);

        this.context = context;
        this.title = title;
        this.subtitle = subtitle;

    }//this activity used for inflate data in listview in about us activity

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listlayout, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(title[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }



}


