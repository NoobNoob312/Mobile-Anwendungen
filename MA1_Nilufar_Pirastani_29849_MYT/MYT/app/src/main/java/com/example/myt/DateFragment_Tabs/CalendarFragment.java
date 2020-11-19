package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myt.DateFragment_Tabs.FragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * One fragment of the three different tabs
 */
public class CalendarFragment extends Fragment {

    public CalendarFragment() {

    }

    /**
     * Loads layout
     * Uses layout fragment_date_calendar
     * Creates CalendarView, so you have an overview about the dates (not implemented)
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
        View view = inflater.inflate(R.layout.fragment_date_calendar, container, false);

        final CalendarView calenderView = view.findViewById(R.id.calendarView);
        final FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonAddDate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
