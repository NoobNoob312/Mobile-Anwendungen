package com.example.myt.DateFragment_Tabs.FragmentAddDate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myt.R;

/**
 * In this case you can just use as user the category 'Training' if you want to create a new date
 */
public class CategoryFragment extends Fragment {
    public CategoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        CardView vcCatTraining = view.findViewById(R.id.cat_training);
        vcCatTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
            }
        });

        CardView vcCatGame = view.findViewById(R.id.cat_game);
        vcCatGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcCatCompetition = view.findViewById(R.id.cat_competition);
        vcCatCompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcCatEvent = view.findViewById(R.id.cat_event);
        vcCatEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new AddDateFragment()).addToBackStack(null).commit();
    }
}

