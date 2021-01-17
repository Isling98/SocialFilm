package com.example.yndlingsfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.yndlingsfilm.NavigationBar.FriendsFragment;
import com.example.yndlingsfilm.NavigationBar.HomeFragment;
import com.example.yndlingsfilm.NavigationBar.ProfileFragment;
import com.example.yndlingsfilm.NavigationBar.SearchFragment;
import com.example.yndlingsfilm.NavigationBar.SettingFragment;
import com.example.yndlingsfilm.viewModels.MovieListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private int currentPosition = 3;
    private int newPosition;
    MovieListViewModel movieListViewModel;
    String tag = "";


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lav transition
        setContentView(R.layout.activity_main);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        BottomNavigationView bottomNavn = findViewById(R.id.bottom_navigation);
        bottomNavn.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nagivation, new HomeFragment(),"home").commit();
        bottomNavn.setSelectedItemId(R.id.nav_home);
    }

    //navigationbaren bliver opsat, med de forskellige fragments.  R.id_nav = ikonerne + titel som er referert fra bottom_navigation.xml filen
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            newPosition = 1;
                            Fragment searchFragment = fragmentManager.findFragmentByTag("search");
                            if(searchFragment == null){
                                searchFragment = new SearchFragment();
                            }
                            selectedFragment = searchFragment;
                            tag = "search";
                            break;
                        case R.id.nav_profile:
                            newPosition = 2;
                            Fragment profileFragment = fragmentManager.findFragmentByTag("profile");
                            if(profileFragment == null){
                                profileFragment = new ProfileFragment();
                            }
                            selectedFragment = profileFragment;
                            tag = "profile";
                            break;
                        case R.id.nav_home:
                            newPosition = 3;
                            Fragment homeFragment = fragmentManager.findFragmentByTag("home");
                            if(homeFragment == null){
                                homeFragment = new HomeFragment();
                            }
                            selectedFragment = homeFragment;
                            tag = "home";
                            break;
                        case R.id.nav_chat:
                            newPosition = 4;
                            Fragment friendsFragment = fragmentManager.findFragmentByTag("friends");
                            if(friendsFragment == null){
                                friendsFragment = new FriendsFragment();
                            }
                            selectedFragment = friendsFragment;
                            tag = "friends";
                            break;
                        case R.id.nav_settings:
                            newPosition = 5;
                            Fragment settingsFragment = fragmentManager.findFragmentByTag("settings");
                            if(settingsFragment == null){
                                settingsFragment = new SettingFragment();
                            }
                            selectedFragment = settingsFragment;
                            tag = "settings";
                            break;
                    }

                    if (newPosition > currentPosition) {
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,
                                R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                                .replace(R.id.fragment_nagivation, selectedFragment, tag).addToBackStack(tag).commit();
                    } else if (newPosition < currentPosition) {
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,
                                R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                                .replace(R.id.fragment_nagivation, selectedFragment, tag).addToBackStack(tag).commit();
                    }
                    currentPosition = newPosition;
                    return true;
                }
            };

}