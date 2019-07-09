package com.example.myt.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.CardItem.DateCardItem;
import com.example.myt.CardItem.ListItem;
import com.example.myt.CardItem.RememberCardView;
import com.example.myt.CardItem.VoteCardItem;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;

import java.util.ArrayList;

/**
 * One fragment of the five bottom navigation items.
 * The HomeFragment is responsible for showing the next date, current reminder, current vote, ...
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<RememberCardView> rememberCardViewArrayList;
    private ArrayList<ListItem> itemList;

    public HomeFragment() {

    }

    /**
     * Uses the layout fragment_home.
     * Set RecyclerAdapter to enable showing the CardViews as a list (RecyclerView)
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyle_view_home);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        setRecyclerViewData(); //adding data to array list

        // specify an adapter
        adapter = new RecyclerAdapter(getContext(), itemList);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Adding CardViews to ArrayList.
     */
    private void setRecyclerViewData() {

        itemList = new ArrayList<ListItem>();
        itemList.add(new RememberCardView("Du musst 12€ zahlen"));
        itemList.add(new DateCardItem("Mo", "29.07.", "Training", "20:00", "21:45"));
        itemList.add(new VoteCardItem("Welche Trikotfarbe ist besser?"));
    }
}
