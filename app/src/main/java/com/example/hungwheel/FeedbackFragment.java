package com.example.hungwheel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {
RatingBar ratingBar;
Button btnSubmit;
Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_feedback,container,false);
        ratingBar = (RatingBar) rootview.findViewById(R.id.ratingbar);
        btnSubmit = (Button)rootview.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thank you for your feedback", Toast.LENGTH_LONG).show();
                /*Intent i = new Intent(context,HomeFragment.class);
                context.startActivity(i);*/
            }
        });



        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Feedback");
    }
}

