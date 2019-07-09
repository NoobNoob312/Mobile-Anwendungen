package com.example.myt.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import com.example.myt.DateFragment_Tabs.ArchiveFragment;
import com.example.myt.DateFragment_Tabs.CalendarFragment;
import com.example.myt.DateFragment_Tabs.ListFragment;
import com.example.myt.R;

/**
 * One fragment of the five bottom navigation items.
 * This class is responsible for dates, especially creating new dates.
 */
public class DateFragment extends Fragment {
    private FragmentTabHost mTabhost;

    public DateFragment() {

    }

    /**
     * Uses the layout fragment_fragment.
     * Create a Tabhost in fragment.
     * Tab 1: Liste --> shows all the created dates in a list. And the fab button is responsible<br>
     * for creating new dates.
     * Tab 2: Kalendar --> shows all the created dates as CalendarView. And the fab button is responsible<br>
     * for creating new dates.
     * Tab 3: Archive --> shows all at the past created dates as a list.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        mTabhost = new FragmentTabHost(getActivity());
        mTabhost.setup(getActivity(), getChildFragmentManager(), R.id.tabhost);

        mTabhost.addTab(mTabhost.newTabSpec("liste").setIndicator("Liste"), ListFragment.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("kalender").setIndicator("Kalender"), CalendarFragment.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("archiv").setIndicator("Archiv"), ArchiveFragment.class, null);

        return mTabhost;
    }
}