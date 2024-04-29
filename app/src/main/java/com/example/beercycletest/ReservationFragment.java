package com.example.beercycletest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class ReservationFragment extends Fragment {

    private Spinner dropdown;
    private ImageView bicycleimage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reservation, container, false);

        init(view);


        String[] items = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        bicycleimage.setImageResource(R.drawable.small);
                        break;

                    case 1:
                        bicycleimage.setImageResource(R.drawable.mediumbike);
                        break;

                    case 2:
                        bicycleimage.setImageResource(R.drawable.largebike);

                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;

    }


    public void init(View view){


        dropdown = view.findViewById(R.id.dropdown);
        bicycleimage = view.findViewById(R.id.imagebicycle);
    }
}
