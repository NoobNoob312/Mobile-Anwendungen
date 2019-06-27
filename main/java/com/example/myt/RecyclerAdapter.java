package com.example.myt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;
import com.example.myt.DateFragment_Tabs.ListItem;
import com.example.myt.DateFragment_Tabs.RememberCardView;
import com.example.myt.DateFragment_Tabs.VoteCardItem;
import com.example.myt.RecyclerAdapter.ViewHolder;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<DateCardView> dateCardView;
    private Context mContext;
    private List<ListItem> itemList;

    public RecyclerAdapter(Context mContext, List<ListItem> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
        //this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;

        switch (viewType) {
            case ListItem.TYPE_ADDDATE:
                view = LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.card_item_adddate, viewGroup, false);
                return new ViewHolderAdddDate(view);
            case ListItem.TYPE_REMEMBER:
                view = LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.card_item_remember, viewGroup, false);
                return new ViewHolderRemember(view);
            case ListItem.TYPE_VOTE:
                view = LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.card_item_vote, viewGroup, false);
                return new ViewHolderVote(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int listPosition) {

        ListItem item = itemList.get(listPosition);
        viewHolder.bindType(item);
    }

    /*private void initLayoutAddDate(ViewHolderAdddDate viewHolder, int position) {
        viewHolder.weekday.setText(itemList.get(position).getName());
        viewHolder.date.setText(itemList.get(position).getName());
        viewHolder.categoryOfDate.setText(itemList.get(position).getName());
        viewHolder.beginTime.setText(itemList.get(position).getName());
        viewHolder.endTime.setText(itemList.get(position).getName());
    }

    private void initLayoutRemember(ViewHolderRemember ViewHolder, int position) {
        ViewHolder.rememberText.setText(itemList.get(position).getName());
    }


    /*
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getListItemType();
    }

    /*private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.card_item_adddate);
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

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindType(ListItem item);
    }

    protected class ViewHolderAdddDate extends ViewHolder {
        private TextView weekday;
        private TextView date;
        private TextView categoryOfDate;
        private TextView beginTime;
        private TextView endTime;

        private RadioButton check;
        private RadioButton unsure;
        private RadioButton notAttend;

        private CardView container;

        public ViewHolderAdddDate(View view) {
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

        public void bindType(ListItem item) {
            weekday.setText(((DateCardView) item).getWeekday());
            date.setText(((DateCardView) item).getDate());
            categoryOfDate.setText(((DateCardView) item).getCategory());
            beginTime.setText(((DateCardView) item).getTimeBeginNum());
            endTime.setText(((DateCardView) item).getTimeEndNum());
        }
    }

    protected class ViewHolderRemember extends ViewHolder {
        private TextView rememberText;

        public ViewHolderRemember(View view) {
            super(view);
            rememberText = view.findViewById(R.id.card_rememberText);
        }

        public void bindType(ListItem item) {
            rememberText.setText(((RememberCardView) item).getRemember());
        }
    }

    protected class ViewHolderVote extends ViewHolder {
        private TextView voteText;

        public ViewHolderVote(View view) {
            super(view);
            voteText = view.findViewById(R.id.card_voteQuestionText);
        }

        public void bindType(ListItem item) {
            voteText.setText(((VoteCardItem) item).getVote());
        }
    }
}
