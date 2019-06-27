package com.example.myt.DateFragment_Tabs;

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
