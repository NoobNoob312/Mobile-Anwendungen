package com.example.myt.DateFragment_Tabs.FragmentAddDate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.CardItem.DateCardItem;
import com.example.myt.CardItem.ListItem;
import com.example.myt.R;
import com.example.myt.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Kind of formular to create a new date --> user input
 * start of the Communication between this Fragment with the MainActivity to communicate with <br>
 * the ListFragment (create with users input new cardItem)
 */
public class AddDateFragment extends Fragment {

    private TextView dateFrom, dateTo;
    private TextView timeBegin, timeEnd;
    private Spinner dateWeekday;

    private int year, month, day;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    Communicator comm;

    public AddDateFragment() {

    }

    /**
     * Creates Calendar and Time and you are able to write some texts --> user input
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adddate, container, false);

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

        dateWeekday = view.findViewById(R.id.date_weekday_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.weekday_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateWeekday.setAdapter(adapter);

        final FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonConfirmDate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String weekday = dateWeekday.getSelectedItem().toString();
                String dateBegin = dateFrom.getText().toString();
                String dateEnd = dateTo.getText().toString();
                String timeFrom = timeBegin.getText().toString();
                String timeTo = timeEnd.getText().toString();

                Log.e("TAG", "Communicator " + comm);
                if (comm != null) {
                    comm.respond(weekday, dateBegin, dateEnd, timeFrom, timeTo);
                }

                // go three fragments back to arrive to the ListFragment
                FragmentManager mgr = getFragmentManager();
                mgr.popBackStack();
                mgr.popBackStack();
                mgr.popBackStack();

            }
        });

        return view;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Communicator) {
            comm = (Communicator) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CommunicatorAddDateFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        comm = null;
    }
}

