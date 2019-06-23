package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myt.DateFragment_Tabs.ListFragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_calendar, container, false);

        final CalendarView calenderView = view.findViewById(R.id.calendarView);
        final FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonAddDate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
                fab.hide();
                calenderView.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_calendar, new CategoryFragment()).addToBackStack(null).commit();
    }

}
