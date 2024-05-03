package com.example.beercycletest;
// LockableScrollView.java

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Egy egyedi ScrollView, amely lehetővé teszi a görgetés engedélyezését vagy letiltását programozottan.
 */
public class LockableScrollView extends ScrollView {
    private boolean mScrollable = true;

    /**
     * Instantiates a new Lockable scroll view.
     *
     * @param context the context
     */
    public LockableScrollView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Lockable scroll view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LockableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Lockable scroll view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public LockableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Beállítja, hogy a görgetés engedélyezett vagy letiltott-e.
     *
     * @param enabled true esetén engedélyezi a görgetést, false esetén letiltja.
     */
    public void setScrollingEnabled(boolean enabled) {
        mScrollable = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mScrollable) {
            // Ha a görgetés le van tiltva, elfogjuk a érintési eseményeket, hogy ne lehessen görgetni.
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mScrollable) {
            // Ha a görgetés le van tiltva, elfogjuk a érintési eseményeket, hogy ne lehessen görgetni.
            return false;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // Ezt a metódust felülírhatjuk, ha szükséges a érintési események elfogásának letiltása.
        // Ha nem szükséges, akkor hagyhatjuk üresen.
    }
}

