package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.FragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<Item> itemList;
    private ArrayList<DateCardView> dateCardViewList;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        recyclerView = view.findViewById(R.id.recyle_view);
        //recyclerView.setHasFixedSize(true);

        setRecyclerViewData(); //adding data to array list

        adapter = new RecyclerAdapter(getContext(), itemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        /*
        fab.setOnClickListener(onAddingListener());
        */

        fab = view.findViewById(R.id.floatingActionButtonAddDate);
        //final CardView cv = view.findViewById(R.id.cardView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
                fab.hide();

                //cv.setVisibility(view.GONE);
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

    private void setRecyclerViewData() {

        itemList = new ArrayList<Item>();
        itemList.add(new Item("Item " + 1, Item.ItemType.ADDDATE_ITEM));

        //dateCardViewList = new ArrayList<DateCardView>();
        //dateCardViewList.add(new DateCardView(DateCardView.ItemType.ADDDATE_ITEM, "Mo", "29.07.","Training", "20:00", "21:45"));
    }

    /*private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add); //layout for dialog
                dialog.setTitle("Add a new friend");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                EditText name = (EditText) dialog.findViewById(R.id.name);
                EditText job = (EditText) dialog.findViewById(R.id.job);
                Spinner spnGender = (Spinner) dialog.findViewById(R.id.gender);
                View btnAdd = dialog.findViewById(R.id.btn_ok);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);

                //set spinner adapter
                ArrayList<String> gendersList = new ArrayList<>();
                gendersList.add("Male");
                gendersList.add("Female");
                ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_dropdown_item_1line, gendersList);
                spnGender.setAdapter(spnAdapter);

                //set handling event for 2 buttons and spinner
                spnGender.setOnItemSelectedListener(onItemSelectedListener());
                btnAdd.setOnClickListener(onConfirmListener(name, job, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                if (position == 0) {
                    gender = true;
                } else {
                    gender = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText name, final EditText job, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend(name.getText().toString().trim(), gender, job.getText().toString().trim());

                //adding new object to arraylist
                friendArrayList.add(friend);

                //notify data set changed in RecyclerView adapter
                adapter.notifyDataSetChanged();

                //close dialog after all
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }*/

}
