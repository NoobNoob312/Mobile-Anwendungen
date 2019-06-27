package com.example.myt;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;
import com.example.myt.DateFragment_Tabs.Item;
import com.example.myt.DateFragment_Tabs.ListItem;
import com.example.myt.DateFragment_Tabs.RememberCardView;
import com.example.myt.DateFragment_Tabs.VoteCardItem;

import java.util.ArrayList;

/**
 * One fragment of the five bottom navigation items.
 * This class is responsible for
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<RememberCardView> rememberCardViewArrayList;
    private ArrayList<ListItem> itemList;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyle_view_home);
        //recyclerView.setHasFixedSize(true);

        setRecyclerViewData(); //adding data to array list

        adapter = new RecyclerAdapter(getContext(), itemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setRecyclerViewData() {

        itemList = new ArrayList<ListItem>();
        itemList.add(new RememberCardView("Du musst 12€ zahlen"));
        itemList.add(new DateCardView("Mo", "29.07.","Training", "20:00", "21:45"));
        itemList.add(new VoteCardItem("Welche Trikotfarbe ist besser?"));
    }
}
