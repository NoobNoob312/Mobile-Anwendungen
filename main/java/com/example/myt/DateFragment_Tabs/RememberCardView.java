package com.example.myt.DateFragment_Tabs;

import android.widget.RadioButton;

import java.util.List;

public class RememberCardView {
    private List<DateCardView> dateCardView;
    private String remember;

    public RememberCardView (String remember) {
        this.remember = remember;
    }


    public String getRemember() {
        return remember;
    }
}
