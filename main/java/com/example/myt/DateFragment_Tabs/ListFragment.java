package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.CardItem.DateCardItem;
import com.example.myt.CardItem.ListItem;
import com.example.myt.DateFragment_Tabs.FragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<ListItem> itemList;
    private ArrayList<DateCardItem> dateCardItemList;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        recyclerView = view.findViewById(R.id.recyle_view);
        //recyclerView.setHasFixedSize(true);

        setRecyclerViewData(); //adding data to array list

        adapter = new RecyclerAdapter(getContext(), itemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        fab = view.findViewById(R.id.floatingActionButtonAddDate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
                fab.hide();
            }
        });

        /**
         * Fab button hides if user scrolls.
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

        return view;
    }


    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new CategoryFragment()).addToBackStack(null).commit();
    }

    /**
     * Create CardView in Fragment 'Date'
     */
    private void setRecyclerViewData() {

        itemList = new ArrayList<ListItem>();
        itemList.add(new DateCardItem("Mo", "29.07.", "Training", "20:00", "21:45"));
        itemList.add(new DateCardItem("Mi", "31.07.", "Training", "20:00", "21:45"));
        itemList.add(new DateCardItem("Sa", "03.08.", "Spiel", "14:00", "15:15"));
        itemList.add(new DateCardItem("Sa", "10.08.", "Essen gehen", "20:00", "23:00"));
    }
}
