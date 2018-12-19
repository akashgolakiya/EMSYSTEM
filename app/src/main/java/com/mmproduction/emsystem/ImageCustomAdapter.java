package com.mmproduction.emsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

class ImageCustomAdapter extends BaseAdapter {
    Context context;
    int[] Images;
    String[] name;
    LayoutInflater inflater;
    public ImageCustomAdapter(Context applicationContext, String[] names, int[] images){
        this.context=applicationContext;
        this.Images=images;
        this.name=names;
        inflater=(LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount(){
        return name.length;
    }
    @Override
    public Objects getItem(int position)
    {
        return null;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        view=inflater.inflate(R.layout.list_item,null);
        TextView names=(TextView)view.findViewById(R.id.name);
        ImageView image=(ImageView)view.findViewById(R.id.image);
        names.setText(name[position]);
        image.setImageResource(Images[position]);
        return view;
    }
}
