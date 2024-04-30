package com.example.beercycletest;
// LockableScrollView.java

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LockableScrollView extends ScrollView {
    private boolean mScrollable = true;

    public LockableScrollView(Context context) {
        super(context);
    }

    public LockableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LockableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollingEnabled(boolean enabled) {
        mScrollable = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mScrollable) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mScrollable) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

