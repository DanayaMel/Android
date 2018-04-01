package com.example.danaya.tabplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;


    public PagerAdapter(FragmentManager fragMan, int numTabs) {
        // Required empty public constructor
        super(fragMan);
        numOfTabs = numTabs;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
            default:
                return null;
        }
    }
}
