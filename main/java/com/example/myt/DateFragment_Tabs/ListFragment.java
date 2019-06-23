package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myt.DateFragment_Tabs.ListFragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListFragment extends Fragment {

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        final FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonAddDate);
        //final CardView cv = view.findViewById(R.id.cardView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
                fab.hide();
                //cv.setVisibility(view.GONE);
            }
        });

        return view;
    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new CategoryFragment()).addToBackStack(null).commit();
    }

}
