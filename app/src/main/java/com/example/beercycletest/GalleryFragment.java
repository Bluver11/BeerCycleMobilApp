package com.example.beercycletest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Egy Fragment osztály a galéria megjelenítéséhez.
 */
public class GalleryFragment extends Fragment {

    private ImageView imageView;

    private LinearLayout linearLayout;


    /**
     * Létrehozza a Fragment nézetét.
     *
     * @param inflater           A nézet inflálásához használt LayoutInflater.
     * @param container          A szülő nézet, amelybe a Fragment kerül.
     * @param savedInstanceState Az előző állapotból visszaállított adatok.
     * @return A létrehozott nézet.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        init(view);
        return view ;
    }

    /**
     * Inicializálja a nézet elemeit.
     *
     * @param view A Fragment nézete.
     */
    public void init(View view){
        imageView=view.findViewById(R.id.imageView2);
        linearLayout = view.findViewById(R.id.linearLayoutGallery);
    }
}