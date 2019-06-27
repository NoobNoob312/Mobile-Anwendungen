package com.example.myt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;
import com.example.myt.DateFragment_Tabs.Item;
import com.example.myt.DateFragment_Tabs.RememberCardView;

import java.util.ArrayList;

/**
 * One fragment of the five bottom navigation items.
 * This class is responsible for
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<RememberCardView> rememberCardViewArrayList;
    private ArrayList<Item> itemList;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        itemList = new ArrayList<Item>();
        itemList.add(new Item("Item " + 2, Item.ItemType.REMEMBER_ITEM));
        itemList.add(new Item("Item " + 1, Item.ItemType.ADDDATE_ITEM));
    }
}
