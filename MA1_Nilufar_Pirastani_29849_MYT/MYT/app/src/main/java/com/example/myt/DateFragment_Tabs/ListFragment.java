package com.example.myt.DateFragment_Tabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.CardItem.DateCardItem;
import com.example.myt.CardItem.ListItem;
import com.example.myt.DateFragment_Tabs.FragmentAddDate.CategoryFragment;
import com.example.myt.DateFragment_Tabs.FragmentAddDate.Communicator;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<ListItem> itemList = new ArrayList<ListItem>();
    private Communicator comm;

    public ListFragment() {
    }

    /**
     * Use RecyclerAdapter() to use recyclerView abd show the created cardItems
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        recyclerView = view.findViewById(R.id.recyle_view);
        //recyclerView.setHasFixedSize(true);

        setRecyclerViewData(); //adding data to array list

        adapter = new RecyclerAdapter(getContext(), itemList);

        layoutManager = new LinearLayoutManager(getActivity());
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

    /**
     * used for testing the problem of updating the recyclerView when cardView is added dynamically
     */
    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG, "onResume: Adapter " + adapter.toString());

        Log.e(TAG, "onResume: list" + itemList.toString() + " " + itemList.size());
        adapter = new RecyclerAdapter(getContext(), itemList);
        adapter.notifyDataSetChanged();

    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new CategoryFragment(), "fragmentCategory").addToBackStack("fragmentList").commit();
    }

    /**
     * Create CardView in Fragment 'Date'
     */
    private void setRecyclerViewData() {
        itemList.add(new DateCardItem("Mo", "29.07.", "Training", "20:00", "21:45"));
        itemList.add(new DateCardItem("Mi", "31.07.", "Training", "20:00", "21:45"));
        itemList.add(new DateCardItem("Sa", "03.08.", "Spiel", "14:00", "15:15"));
        itemList.add(new DateCardItem("Sa", "10.08.", "Essen gehen", "20:00", "23:00"));
    }

    /**
     * Create dynamically  anew cardView --> does not work 100%
     *
     * @param weekday   defines which weekday has to be set between the @dateBegin and @dateEnd
     * @param dateBegin defines when date starts
     * @param dateEnd   defines when date ends
     * @param timeFrom  when time starts
     * @param timeTo    when time ends
     */
    public void createCardItem(String weekday, String dateBegin, String dateEnd, String timeFrom, String timeTo) {
        itemList.add(new DateCardItem("Mi", "31.07.", "Training", "20:00", "21:45"));

        itemList.add(new DateCardItem(weekday, dateBegin, dateEnd, timeFrom, timeTo));

        //adapter.notifyDataSetChanged();       // PROBLEM: adapter = null

        Log.e(TAG, "createCardItem: " + itemList.size());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "createCardItem: " + context);
        if (context instanceof Communicator) {
            comm = (Communicator) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CommunicatorListFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        comm = null;
    }
}
