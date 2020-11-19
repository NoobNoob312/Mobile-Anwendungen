package com.example.myt.CardItem;

import java.util.List;

/**
 * Extends from interface ListItem (can choose between different layouts)
 * Layout: card_item_adddate
 * Properties which can be changed
 */
public class DateCardItem implements ListItem {

    private List<DateCardItem> dateCardItem;
    private String weekday;
    private String date;
    private String category;
    private String timeBeginNum;
    private String timeEndNum;
    // private RadioButton check;
    // private RadioButton unsure;
    // private RadioButton notAttend;

    public DateCardItem(String weekday, String date, String category, String timeBeginNum, String timeEndNum/*, RadioButton check, RadioButton unsure, RadioButton notAttend*/) {
        this.weekday = weekday;
        this.date = date;
        this.category = category;
        this.timeBeginNum = timeBeginNum;
        this.timeEndNum = timeEndNum;
        //this.check = check;
        //this.unsure = unsure;
        //this.notAttend = notAttend;
    }


    public String getWeekday() {
        return weekday;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getTimeBeginNum() {
        return timeBeginNum;
    }

    public String getTimeEndNum() {
        return timeEndNum;
    }

    /* public RadioButton getCheck() {
        return check;
    }

    public RadioButton getUnsure() { return unsure; }

    public RadioButton getNotAttend() {
        return notAttend;
    } */

    @Override
    public int getListItemType() {
        return ListItem.TYPE_ADDDATE;
    }
}
