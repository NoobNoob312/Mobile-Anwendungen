package com.example.myt_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DateFragment extends Fragment {
    private FragmentTabHost mTabhost;
    public DateFragment () {
        // // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        mTabhost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabhost.setup(getActivity(), getChildFragmentManager(), R.id.tabcontent);
        return view;
    }

}
