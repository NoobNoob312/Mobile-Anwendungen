package com.example.myt.DateFragment_Tabs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.ListFragmentAddDate.CategoryFragment;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<DateCardView> dateCardViewArrayList;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        //recyclerView = view.findViewById(R.id.recyle_view);
        //recyclerView.setHasFixedSize(true);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(layoutManager);

        /*
        setRecyclerViewData(); //adding data to array list
        adapter = new RecyclerAdapter(getActivity(), dateCardViewArrayList);
        */

        //adapter = new RecyclerAdapter(dateCardViewArrayList);
        //recyclerView.setAdapter(adapter);

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

        return view;
    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new CategoryFragment()).addToBackStack(null).commit();
    }

    /*private void setRecyclerViewData() {
        dateCardViewArrayList.add(new DateCardView("Mo", "29.07.","Training", "20:00", "21:45"));
        dateCardViewArrayList.add(new DateCardView("Mi", "31.07.","Training", "20:00", "21:45"));
        dateCardViewArrayList.add(new DateCardView("Fr", "02.08.","Training", "18:00", "20:00"));
        dateCardViewArrayList.add(new DateCardView("So", "04.08.","Spiel", "14:00", "15:15"));
        dateCardViewArrayList.add(new DateCardView("Sa", "17.08.","Event", "20:00", "00:00"));
    }*/

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
