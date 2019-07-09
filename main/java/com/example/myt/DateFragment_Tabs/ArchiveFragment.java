package com.example.myt.DateFragment_Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myt.R;

/**
 * One fragment of the three different tabs
 */
public class ArchiveFragment extends Fragment {

    public ArchiveFragment() {

    }

    /**
     * Loads layout
     * Uses layout fragment_date_archive
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_archive, container, false);

        return view;
    }
}
