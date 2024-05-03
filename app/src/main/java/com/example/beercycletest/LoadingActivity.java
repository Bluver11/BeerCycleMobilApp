package com.example.beercycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Az alkalmazás betöltőképernyőjét reprezentáló Activity.
 */
public class LoadingActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;

    /**
     * Az Activity létrehozásakor meghívódó metódus.
     *
     * @param savedInstanceState Az előző állapotot tartalmazó Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");
            if (token.isEmpty()) {
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(LoadingActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}