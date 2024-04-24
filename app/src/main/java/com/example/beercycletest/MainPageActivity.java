package com.example.beercycletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    FrameLayout frameLayout;
    Button button;
    ImageView imageView;

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_page);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.fragmentcontainer);
        button = findViewById(R.id.buttonfoglalas);
        imageView= findViewById(R.id.beercycle);
        imageButton = findViewById(R.id.imagebuttonbasket);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPageActivity.this, "Basket clicked", Toast.LENGTH_SHORT).show();
                frameLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new BasketFragment()).commit();
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.home_view){
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
            frameLayout.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
        if(menuItem.getItemId()==R.id.gallery){
            Toast.makeText(this, "Gallery Selected", Toast.LENGTH_SHORT).show();
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new GalleryFragment()).commit();
            button.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        }
        if(menuItem.getItemId()==R.id.menu){
            Toast.makeText(this, "Menu Selected", Toast.LENGTH_SHORT).show();
            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new MenuFragment()).commit();
            button.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);


        }
        if(menuItem.getItemId()==R.id.about){
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
        }
        if(menuItem.getItemId()==R.id.rate){
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
        }
        if(menuItem.getItemId()==R.id.aboutus){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        if(menuItem.getItemId()==R.id.contact){
            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        }

        if(menuItem.getItemId()==R.id.logout){
            Toast.makeText(this,"Sikeres kijelenkezés",Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}