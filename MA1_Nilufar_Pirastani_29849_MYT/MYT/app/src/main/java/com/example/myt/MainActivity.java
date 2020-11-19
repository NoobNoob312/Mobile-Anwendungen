package com.example.myt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myt.BottomNavigationFragment.DateFragment;
import com.example.myt.BottomNavigationFragment.HomeFragment;
import com.example.myt.BottomNavigationFragment.OrganizationFragment;
import com.example.myt.DateFragment_Tabs.ArchiveFragment;
import com.example.myt.DateFragment_Tabs.FragmentAddDate.Communicator;
import com.example.myt.DateFragment_Tabs.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.ContentValues.TAG;

/**
 * Uses the layout activity_main.
 * The MainActivity is responsible for switching between the different bottom navigation items.
 */
public class MainActivity extends AppCompatActivity implements Communicator {

    Fragment selectedFragment;

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
                    selectedFragment = null;
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


    /**
     * Communication between the AddateFragmnet and ListFragment by using the MainActivity
     * sends data to ListFragment, when AddDateFragment calls the respond function by the <br>
     * Interface Communicator
     *
     * @param weekday   defines which weekday has to be set between the @dateBegin and @dateEnd
     * @param dateFrom  defines when date starts
     * @param dateTo    defines when date ends
     * @param timeBegin when time starts
     * @param timeEnd   when time ends
     */
    @Override
    public void respond(String weekday, String dateFrom, String dateTo, String timeBegin, String timeEnd) {
        FragmentManager fm = getSupportFragmentManager();
        Log.e("TAG", "selected fragment" + " " + selectedFragment);

        ListFragment listFragment = (ListFragment) fm.findFragmentByTag("fragmentList");

        if (listFragment != null) {
            listFragment.createCardItem(weekday, dateFrom, dateTo, timeBegin, timeEnd);     // PROBLEM: should go there, but does not find my Fragment
        } else {
            Log.e(TAG, "listFragment" + " " + listFragment);
            ListFragment lf = new ListFragment();
            lf.createCardItem(weekday, dateFrom, dateTo, timeBegin, timeEnd);
        }
    }

}




