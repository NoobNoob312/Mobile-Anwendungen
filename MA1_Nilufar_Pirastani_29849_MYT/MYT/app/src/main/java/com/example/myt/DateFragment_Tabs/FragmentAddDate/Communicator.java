package com.example.myt.DateFragment_Tabs.FragmentAddDate;

/**
 * Interface which helps to communicate between the two Fragments AddDateFragment and ListFragment
 */
public interface Communicator {
    void respond(String weekday, String dateFrom, String dateTo, String timeBegin, String timeEnd);
}
