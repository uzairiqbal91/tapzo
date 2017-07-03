package com.example.mohsinhussain.allinoneapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.mohsinhussain.allinoneapp.DAL.bitmaps;

/**
 * Created by Hp on 6/15/2017.
 */

public class CustomListView extends ArrayAdapter<String> {

    ArrayList<String>brand;
     //String[] brand;
//     ArrayList<Bitmap>bitmaps;
     ArrayList<String>images;
     Context mcontext;



    public CustomListView(Activity context, ArrayList<String> brand, ArrayList<String> images) {
        super(context, R.layout.custom_view);
        this.brand = brand;

        this.images = images;
        this.mcontext = context;

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return brand.size();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mcontext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.custom_view, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mcontext).load(images.get(position)).into(mViewHolder.mFlag);

        mViewHolder.mName.setText(brand.get(position));

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }
}

