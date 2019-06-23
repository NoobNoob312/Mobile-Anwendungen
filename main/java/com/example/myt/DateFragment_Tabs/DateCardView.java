package com.example.myt.DateFragment_Tabs;

import android.widget.RadioButton;

public class DateCardView {
    private String weekday;
    private String date;
    private String category;
    private String timeBegin;
    private String timeEnd;
    private int timeBeginNum;
    private int timeEndNum;
    private RadioButton check;
    private RadioButton unsure;
    private RadioButton notAttend;

    public DateCardView (String weekday, String date, String category, String timeBegin, String timeEnd, int timeBeginNum, int timeEndNum, RadioButton check, RadioButton unsure, RadioButton notAttend) {
        this.weekday = weekday;
        this.date = date;
        this.category = category;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.timeBeginNum = timeBeginNum;
        this.timeEndNum = timeEndNum;
        this.check = check;
        this.unsure = unsure;
        this.notAttend = notAttend;
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

    public String getTimeBegin() {
        return timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public int getTimeBeginNum() {
        return timeBeginNum;
    }

    public int getTimeEndNum() {
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
