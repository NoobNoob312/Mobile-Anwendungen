package com.example.myt;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.DateFragment_Tabs.DateCardView;
import com.example.myt.DateFragment_Tabs.Item;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ADDDATE = 1;
    private static final int TYPE_REMEMBER = 2;

    private List<DateCardView> dateCardView;
    private Context mContext;
    private List<Item> itemList;

    public RecyclerAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
        //this.activity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ADDDATE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_item_adddate, viewGroup, false);
            return new ViewHolderAdddDate(view);
        } else if (viewType == TYPE_REMEMBER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_item_remember, viewGroup, false);
            return new ViewHolderRemember(view);
        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int listPosition) {

        switch (viewHolder.getItemViewType()) {
            case TYPE_ADDDATE:
                initLayoutAddDate((ViewHolderAdddDate)viewHolder, listPosition);
                break;
            case TYPE_REMEMBER:
                initLayoutRemember((ViewHolderRemember) viewHolder, listPosition);
                break;
            default:
                break;
        }
    }

    private void initLayoutAddDate(ViewHolderAdddDate viewHolder, int position) {
        viewHolder.weekday.setText(itemList.get(position).getName());
        viewHolder.date.setText(itemList.get(position).getName());
        viewHolder.categoryOfDate.setText(itemList.get(position).getName());
        viewHolder.beginTime.setText(itemList.get(position).getName());
        viewHolder.endTime.setText(itemList.get(position).getName());
    }

    private void initLayoutRemember(ViewHolderRemember ViewHolder, int position) {
        ViewHolder.rememberText.setText(itemList.get(position).getName());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Item item = itemList.get(position);
        if (item.getType() == Item.ItemType.ADDDATE_ITEM) {
            return TYPE_ADDDATE;
        } else if (item.getType() == Item.ItemType.REMEMBER_ITEM) {
            return TYPE_REMEMBER;
        } else {
            return -1;
        }
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

    protected class ViewHolderAdddDate extends RecyclerView.ViewHolder {
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
    }

    protected class ViewHolderRemember extends RecyclerView.ViewHolder {
        private TextView rememberText;

        private CardView container;

        public ViewHolderRemember(View view) {
            super(view);
            rememberText = view.findViewById(R.id.card_rememberText);

            container = view.findViewById(R.id.card_view_remember);
        }
    }
}
