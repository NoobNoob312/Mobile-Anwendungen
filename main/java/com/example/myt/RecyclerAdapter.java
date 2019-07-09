package com.example.myt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myt.CardItem.DateCardItem;
import com.example.myt.CardItem.ListItem;
import com.example.myt.CardItem.RememberCardView;
import com.example.myt.CardItem.VoteCardItem;
import com.example.myt.RecyclerAdapter.ViewHolder;

import java.util.List;

/**
 * Used for the different types of CardViews, which are set in a list
 * Use recyclerView
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<ListItem> itemList;

    public RecyclerAdapter(Context mContext, List<ListItem> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    /**
     * Create new views
     */
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

    /**
     * // Replace the contents of a view (invoked by the layout manager)
     *
     * @param viewHolder replace the contents of the view with that element
     * @param position   get element from dataset at this position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        ListItem item = itemList.get(position);
        viewHolder.bindType(item);
    }

    /**
     * // Return the size of dataset (invoked by the layout manager)
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * gets type of my item (CardView) which has to be created
     *
     * @param position get element from dataset at this position
     * @return
     */
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

        // private RadioButton check;
        // private RadioButton unsure;
        // private RadioButton notAttend;

        // private CardView container;

        public ViewHolderAdddDate(View view) {
            super(view);
            weekday = view.findViewById(R.id.weekday);
            date = view.findViewById(R.id.date);
            categoryOfDate = view.findViewById(R.id.categoryOfDate);
            beginTime = view.findViewById(R.id.card_begin_time);
            endTime = view.findViewById(R.id.card_end_time);

            // check = view.findViewById(R.id.radioButton_check);
            // unsure = view.findViewById(R.id.radioButton_unsure);
            // notAttend = view.findViewById(R.id.radioButton_notAttend);

            // container = view.findViewById(R.id.card_view);
        }

        public void bindType(ListItem item) {
            weekday.setText(((DateCardItem) item).getWeekday());
            date.setText(((DateCardItem) item).getDate());
            categoryOfDate.setText(((DateCardItem) item).getCategory());
            beginTime.setText(((DateCardItem) item).getTimeBeginNum());
            endTime.setText(((DateCardItem) item).getTimeEndNum());
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
