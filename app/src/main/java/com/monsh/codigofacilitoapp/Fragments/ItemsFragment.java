package com.monsh.codigofacilitoapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsh.codigofacilitoapp.R;

/**
 * Created by monsh on 28/12/2015.
 */
public class ItemsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.items_fragment,container,false);
        return rootView;
    }
}
