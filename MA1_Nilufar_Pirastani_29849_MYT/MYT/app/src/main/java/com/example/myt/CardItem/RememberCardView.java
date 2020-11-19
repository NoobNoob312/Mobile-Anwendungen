package com.example.myt.CardItem;

/**
 * Extends from interface ListItem (can choose between different layouts)
 * Layout: card_item_remember
 * Properties which can be changed
 */
public class RememberCardView implements ListItem {
    private String remember;

    public RememberCardView (String remember) {
        this.remember = remember;
    }


    public String getRemember() {
        return remember;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_REMEMBER;
    }
}
