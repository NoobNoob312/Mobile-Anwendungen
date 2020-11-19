package com.example.myt.CardItem;

/**
 * Chose between different types of layouts
 */
public interface ListItem {
    int TYPE_ADDDATE = 1;
    int TYPE_REMEMBER = 2;
    int TYPE_VOTE = 3;

    int getListItemType();
}
