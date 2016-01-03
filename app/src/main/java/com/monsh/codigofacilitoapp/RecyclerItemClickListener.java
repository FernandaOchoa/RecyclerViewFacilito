package com.monsh.codigofacilitoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by monsh on 28/12/2015.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    protected OnItemClickListener listener;
    private GestureDetector gestureDetector;

    private View childView;
    private int childViewPosition;

    public RecyclerItemClickListener(Context context,OnItemClickListener listener){
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.listener = listener;

    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener{

    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener{

    }
}
