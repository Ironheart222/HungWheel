package com.example.hungwheel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BusinessRestaurant extends Fragment {

    Button btnadditem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_business_restaurant, container, false);

        btnadditem = (Button) rootview.findViewById(R.id.btn_menu);

        btnadditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),MenuDetails.class);
                getContext().startActivity(i);
            }
        });

        return rootview;
    }
}
