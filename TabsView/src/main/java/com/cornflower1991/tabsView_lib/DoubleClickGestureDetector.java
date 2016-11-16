package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 双击手势识别器
 */
public class DoubleClickGestureDetector extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    private View currentView;
    private OnDoubleClickListener onDoubleClickListener;

    public DoubleClickGestureDetector(Context context, OnDoubleClickListener onDoubleClickListener) {
        gestureDetector = new GestureDetector(context, this);
        gestureDetector.setOnDoubleTapListener(this);
        this.onDoubleClickListener = onDoubleClickListener;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (onDoubleClickListener != null) {
            onDoubleClickListener.onDoubleClick(currentView);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        currentView = v;
        return gestureDetector.onTouchEvent(event);
    }

    public interface OnDoubleClickListener {
        void onDoubleClick(View view);
    }
}
