package com.example.myt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

public class DateFragment extends Fragment {
    private FragmentTabHost mTabhost;

    public DateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        mTabhost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabhost.setup(getActivity(), getChildFragmentManager(), R.id.tabcontent);
        mTabhost.addTab(mTabhost.newTabSpec("liste").setIndicator("Liste"), ListFragment.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("kalender").setIndicator("Kalender"), CalendarFragment.class, null);
        mTabhost.addTab(mTabhost.newTabSpec("archiv").setIndicator("Archiv"), ArchiveFragment.class, null);
        return view;
    }
}
