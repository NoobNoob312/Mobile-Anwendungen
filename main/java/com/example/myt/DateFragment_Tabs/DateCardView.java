package com.example.myt.DateFragment_Tabs;

import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class DateCardView {
    private List<DateCardView> dateCardView;
    private String weekday;
    private String date;
    private String category;
    private String timeBeginNum;
    private String timeEndNum;
    private RadioButton check;
    private RadioButton unsure;
    private RadioButton notAttend;

    public DateCardView (String weekday, String date, String category, String timeBeginNum, String timeEndNum/*, RadioButton check, RadioButton unsure, RadioButton notAttend*/) {
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

    public RadioButton getCheck() {
        return check;
    }

    public RadioButton getUnsure() {
        return unsure;
    }

    public RadioButton getNotAttend() {
        return notAttend;
    }
}
