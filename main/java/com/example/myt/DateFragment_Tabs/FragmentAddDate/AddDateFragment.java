package com.example.myt.DateFragment_Tabs.FragmentAddDate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;
import com.example.myt.DateFragment_Tabs.ListItem;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class AddDateFragment extends Fragment {

    private TextView dateFrom, dateTo;
    private TextView timeBegin, timeEnd;
    private TextView dateWeekday;

    private int year, month, day, weekday;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<ListItem> itemList;

    public AddDateFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adddate, container, false);

        final FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonConfirmDate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText information = view.findViewById(R.id.edit_information);
                final EditText meeting = view.findViewById(R.id.edit_meeting);
                final EditText address = view.findViewById(R.id.edit_address);

                changeFragment();
            }
        });

        dateFrom = view.findViewById(R.id.begin_date);
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFrom();
            }
        });

        dateTo = view.findViewById(R.id.end_date);
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dateTo();
            }
        });

        timeBegin = view.findViewById(R.id.begin_time);
        timeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBegin();
            }
        });

        timeEnd = view.findViewById(R.id.end_time);
        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeEnd();
            }
        });

        dateWeekday = view.findViewById(R.id.date_weekday);
        dateWeekday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /*TextView training = view.findViewById(R.id.name_training);
        training.setPaintFlags(training.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);*/

        return view;

    }

    private void changeFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_date_list, new ListFragment()).addToBackStack(null).commit();
    }

    /**
     * User's input when time begins
     */
    public void dateFrom() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //weekday = calendar.get(Calendar.DAY_OF_WEEK);

        datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateFrom.setText(day + "." + month + "." + year);
                    }
                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    /**
     * User's input when date ends
     */
    public void dateTo() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //weekday = calendar.get(Calendar.DAY_OF_WEEK);

        datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateTo.setText(day + "." + month + "." + year);
                    }
                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    /**
     * User's input when time begins
     */
    public void timeBegin() {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int se1ectedMinute) {
                timeBegin.setText(selectedHour + ":" + se1ectedMinute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    /**
     * User's input when time ends
     */
    public void timeEnd() {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int se1ectedMinute) {
                timeEnd.setText(selectedHour + ":" + se1ectedMinute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    /**
     * Show popup window with Monday to Sunday radiobuttons
     */
    public void dateWeekDay() {

    }

    private void setRecyclerViewData() {

        itemList = new ArrayList<ListItem>();
        itemList.add(new DateCardView("Mo", "29.07.","Training", "20:00", "21:45"));
    }
}

