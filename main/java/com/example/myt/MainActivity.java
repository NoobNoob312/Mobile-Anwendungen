package com.example.myt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myt.BottomNavigationFragment.DateFragment;
import com.example.myt.BottomNavigationFragment.HomeFragment;
import com.example.myt.BottomNavigationFragment.OrganizationFragment;
import com.example.myt.DateFragment_Tabs.ArchiveFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Uses the layout activity_main.
 * The MainActivity is responsible for switching between the different bottom navigation items.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);

        loadFragment(new HomeFragment());
    }

    /**
     * Loads the chosen fragment, when you switch between the different bottom navigation items.
     *
     * @param fragment loads the chosen fragment
     * @return
     */
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /**
     * It is the bottom navigation bar
     * Switches between the chosen fragments and loads the chosen fragment.
     * {@link #loadFragment(Fragment)}
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_date:
                            selectedFragment = new DateFragment();
                            break;
                        case R.id.nav_organization:
                            selectedFragment = new OrganizationFragment();
                            break;
                        // chose ArchiveFragment for the two last fragments because the fragments
                        // are empty and not intended to be implemented for the exam
                        case R.id.nav_chat:
                            selectedFragment = new ArchiveFragment();
                            break;
                        case R.id.nav_stats:
                            selectedFragment = new ArchiveFragment();
                            break;
                    }
                    return loadFragment(selectedFragment);
                }
            };
}

