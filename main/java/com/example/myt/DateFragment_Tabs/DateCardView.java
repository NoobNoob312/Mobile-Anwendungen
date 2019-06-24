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

    private void initializeData(){
        dateCardView = new ArrayList<>();
        dateCardView.add(new DateCardView("Mo", "29.07.","Training", "20:00", "21:45"));
        dateCardView.add(new DateCardView("Mi", "31.07.","Training", "20:00", "21:45"));
        dateCardView.add(new DateCardView("Fr", "02.08.","Training", "18:00", "20:00"));
        dateCardView.add(new DateCardView("So", "04.08.","Spiel", "14:00", "15:15"));
        dateCardView.add(new DateCardView("Sa", "17.08.","Event", "20:00", "00:00"));
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
