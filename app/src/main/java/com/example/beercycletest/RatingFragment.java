package com.example.beercycletest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

/**
 * The type Rating fragment.
 */
public class RatingFragment extends Fragment {

    private RatingBar ratingbar;
    private Button submit;
    /**
     * The Rate.
     */
    float rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_rating, container, false);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        init(view);

        return  view;
    }

    /**
     * Init.
     *
     * @param view the view
     */
    public void init(View view){
        ratingbar = view.findViewById(R.id.ratingBar);
        submit = view.findViewById(R.id.submit);
    }
}