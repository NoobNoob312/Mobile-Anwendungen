package com.example.myt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<DateCardView> dateCardView;
    private Context mContext;

    public RecyclerAdapter(Context mContext, List<DateCardView> dateCardView) {
        this.mContext = mContext;
        this.dateCardView = dateCardView;
        //this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        //setting data to view holder elements
        viewHolder.weekday.setText(dateCardView.get(position).getWeekday());
        viewHolder.date.setText(dateCardView.get(position).getDate());
        viewHolder.categoryOfDate.setText(dateCardView.get(position).getCategory());
        viewHolder.beginTime.setText(dateCardView.get(position).getTimeBeginNum());
        viewHolder.endTime.setText(dateCardView.get(position).getTimeEndNum());
        //viewHolder.check.setChecked(false).getCheck());
        //viewHolder.unsure.setText(dateCardView.get(position).getUnsure());
        //viewHolder.notAttend.setText(dateCardView.get(position).getNotAttend());

        //set on click listener for each element
        //viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return dateCardView.size();
    }

    /*private View.OnClickListener onClickListener(final int position) {
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
                TextView categoryOfDate = dialog.findViewById(R.id.categoryOfDate);
                TextView beginTime = dialog.findViewById(R.id.card_begin_time);
                TextView endTime = dialog.findViewById(R.id.card_end_time);
                //RadioButton check = view.findViewById(R.id.radioButton_check);
                //RadioButton unsure = view.findViewById(R.id.radioButton_unsure);
                //Radiobutton notAttend = view.findViewById(R.id.radioButton_notAttend);

                setDataToView(weekday, date, categoryOfDate, beginTime, endTime,  position);

                dialog.show();
            }
        };
    }*/

    /**
     * View holder to display each RecyclerView item
     */

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView weekday;
        private TextView date;
        private TextView categoryOfDate;
        private TextView beginTime;
        private TextView endTime;

        private RadioButton check;
        private RadioButton unsure;
        private RadioButton notAttend;

        private CardView container;

        public ViewHolder(View view) {
            super(view);
            weekday = view.findViewById(R.id.weekday);
            date = view.findViewById(R.id.date);
            categoryOfDate = view.findViewById(R.id.categoryOfDate);
            beginTime = view.findViewById(R.id.card_begin_time);
            endTime = view.findViewById(R.id.card_end_time);

            check = view.findViewById(R.id.radioButton_check);
            unsure = view.findViewById(R.id.radioButton_unsure);
            notAttend = view.findViewById(R.id.radioButton_notAttend);

            container = view.findViewById(R.id.card_view);
        }
    }
}
