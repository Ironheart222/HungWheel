package com.example.hungwheel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBaseAdapater extends BaseAdapter {
    Context context;
    ArrayList<RestModel> dataModelArrayList;
    public MyBaseAdapater(Context context , ArrayList<RestModel> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }


    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        convertView = layoutInflater.inflate(R.layout.activity_raw__listview,null);

        TextView tvFoodname = (TextView) convertView.findViewById(R.id.tv_foodname);
        TextView tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.tv_location);
        TextView tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
        ImageView imgFoodname = (ImageView) convertView.findViewById(R.id.img_foodimag);

        tvFoodname.setText(dataModelArrayList.get(position).getResname());
        tvCategory.setText(dataModelArrayList.get(position).getCategory());
        tvLocation.setText(dataModelArrayList.get(position).getCityname());
        tvMoney.setText(dataModelArrayList.get(position).getMoney());
       // imgFoodname.setImageResource(dataModelArrayList.get(position).getImgData());

        //Log.e("BASE","lang"+dataModelArrayList.get(position).getStrData());

/*
        tvData.setText(dataModelArrayList.get(position).getStrLang());
        imgData.setImageResource(dataModelArrayList.get(position).getImgLang());
*/
        return convertView;
    }
}
