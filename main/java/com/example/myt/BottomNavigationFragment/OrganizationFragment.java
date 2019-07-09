package com.example.myt.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myt.R;

/**
 * One fragment of the five bottom navigation items.
 * The OrganizationFragment is responsible for the organization in your team like services.
 */
public class OrganizationFragment extends Fragment {

    public OrganizationFragment() {

    }

    /**
     * Uses the layout fragment_organization.
     * There are different categories which you can choose.
     * For now if you click on them it shows that these parts are still in development
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organization, container, false);

        CardView vcSquad = view.findViewById(R.id.squad);
        vcSquad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcMoney = view.findViewById(R.id.money);
        vcMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcService = view.findViewById(R.id.service);
        vcService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcDriving = view.findViewById(R.id.driving);
        vcDriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcAbsence = view.findViewById(R.id.absence);
        vcAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        CardView vcVote = view.findViewById(R.id.vote);
        vcVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(), "in Entwicklung", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
