package com.example.yndlingsfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yndlingsfilm.NavigationBar.ChatFragment;
import com.example.yndlingsfilm.NavigationBar.HomeFragment;
import com.example.yndlingsfilm.NavigationBar.ProfileFragment;
import com.example.yndlingsfilm.NavigationBar.SearchFragment;
import com.example.yndlingsfilm.NavigationBar.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private int currentPosition = 3;
    private int newPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lav transition
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavn = findViewById(R.id.bottom_navigation);
        bottomNavn.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nagivation, new HomeFragment()).commit();
    }

    //navigationbaren bliver opsat, med de forskellige fragments.  R.id_nav = ikonerne + titel som er referert fra bottom_navigation.xml filen
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            newPosition = 1;
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_profile:
                            newPosition = 2;
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_home:
                            newPosition = 3;
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_chat:
                            newPosition = 4;
                            selectedFragment = new ChatFragment();
                            break;
                        case R.id.nav_settings:
                            newPosition = 5;
                            selectedFragment = new SettingFragment();
                            break;
                    }

                    if (newPosition > currentPosition) {
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                                .replace(R.id.fragment_nagivation, selectedFragment).addToBackStack(null).commit();
                    } else if (newPosition < currentPosition) {

                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,
                                R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                                .replace(R.id.fragment_nagivation, selectedFragment).addToBackStack(null).commit();

                    }
                    currentPosition = newPosition;
                    return true;
                }
            };
}