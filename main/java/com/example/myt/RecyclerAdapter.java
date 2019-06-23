package com.example.myt;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<DateCardView> dateCardView;
    private Activity activity; // eigentlich activity laut tutorial
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public RecyclerAdapter(Activity activity, List<DateCardView> dateCardView) {
        this.dateCardView = dateCardView;
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

        //setting data to view holder elements
        viewHolder.weekday.setText(dateCardView.get(position).getWeekday());
        viewHolder.date.setText(dateCardView.get(position).getDate());
        viewHolder.categoryOfDate.setText(dateCardView.get(position).getCategory());
        viewHolder.begin.setText(dateCardView.get(position).getTimeBegin());
        viewHolder.end.setText(dateCardView.get(position).getTimeEnd());
        viewHolder.beginTime.setText(dateCardView.get(position).getTimeBeginNum());
        viewHolder.endTime.setText(dateCardView.get(position).getTimeEndNum());
        //viewHolder.check.setChecked(false).getCheck());
        //viewHolder.unsure.setText(dateCardView.get(position).getUnsure());
        //viewHolder.notAttend.setText(dateCardView.get(position).getNotAttend());

        //set on click listener for each element
        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {return position == 0;}

    private void setDataToView(TextView weekday, TextView date, TextView categoryOfDate, TextView begin, TextView end, TextView beginTime, TextView endTime, int position) {
        weekday.setText(dateCardView.get(position).getWeekday());
        date.setText(dateCardView.get(position).getDate());
        categoryOfDate.setText(dateCardView.get(position).getCategory());
        begin.setText(dateCardView.get(position).getTimeBegin());
        end.setText(dateCardView.get(position).getTimeEnd());
        beginTime.setText(dateCardView.get(position).getTimeBeginNum());
        endTime.setText(dateCardView.get(position).getTimeEndNum());
        // RadioButton --> check
        // RadioButton --> unsure
        // RadioButton --> notAttend

    }

    @Override
    public int getItemCount() {return (null != dateCardView ? dateCardView.size() : 0);}

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.card_layout);
                dialog.setTitle("Position " + position);
                dialog.setCancelable(true); // dismiss when touching outside Dialog

                // set the custom dialog components - texts and image
                TextView weekday = dialog.findViewById(R.id.weekday);
                TextView date = dialog.findViewById(R.id.date);
                TextView  categoryOfDate = dialog.findViewById(R.id.categoryOfDate);
                TextView begin = dialog.findViewById(R.id.card_begin);
                TextView end = dialog.findViewById(R.id.card_end);
                TextView beginTime = dialog.findViewById(R.id.card_begin_time);
                TextView endTime = dialog.findViewById(R.id.card_end_time);
                //RadioButton check = view.findViewById(R.id.radioButton_check);
                //RadioButton unsure = view.findViewById(R.id.radioButton_unsure);
                //Radiobutton notAttend = view.findViewById(R.id.radioButton_notAttend);

                setDataToView(weekday, date, categoryOfDate, begin, end, beginTime, endTime,  position);

                dialog.show();
            }
        };
    }

    /**
     * View holder to display each RecyclerView item
     */

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView weekday;
        private TextView date;
        private TextView categoryOfDate;
        private TextView begin;
        private TextView end;
        private TextView beginTime;
        private TextView endTime;
        private RadioButton check;
        private RadioButton unsure;
        private RadioButton notAttend;
        private View container;

        public ViewHolder(View view) {
            super(view);
            weekday = view.findViewById(R.id.weekday);
            date = view.findViewById(R.id.date);
            categoryOfDate = view.findViewById(R.id.categoryOfDate);
            begin = view.findViewById(R.id.card_begin);
            end = view.findViewById(R.id.card_end);
            beginTime = view.findViewById(R.id.card_begin_time);
            endTime = view.findViewById(R.id.card_end_time);
            check = view.findViewById(R.id.radioButton_check);
            unsure = view.findViewById(R.id.radioButton_unsure);
            notAttend = view.findViewById(R.id.radioButton_notAttend);
            container = view.findViewById(R.id.card_view);
        }
    }
}
