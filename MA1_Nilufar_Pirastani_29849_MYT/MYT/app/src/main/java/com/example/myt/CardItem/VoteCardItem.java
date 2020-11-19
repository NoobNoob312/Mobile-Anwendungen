package com.example.myt.CardItem;

/**
 * Extends from interface ListItem (can choose between different layouts)
 * Layout: card_item_vote
 * Properties which can be changed
 */
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
