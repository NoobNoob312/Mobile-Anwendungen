package com.example.myt.DateFragment_Tabs;

public class VoteCardItem implements ListItem {
    private String vote;

    public VoteCardItem (String vote) {
        this.vote = vote;
    }


    public String getVote() {
        return vote;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_VOTE;
    }
}
