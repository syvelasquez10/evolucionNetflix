// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import android.view.MotionEvent;
import com.netflix.mediaclient.Log;
import android.widget.ListView;
import android.view.ViewParent;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import com.viewpagerindicator.android.osp.ViewPager;

public class CustomViewPager extends ViewPager
{
    public static final boolean DO_NOT_OVERLAP_PAGES_CONST = false;
    public static final boolean OVERLAP_PAGES_CONST = true;
    private static final String TAG = "CustomViewPager";
    private final ViewPager$OnPageChangeListener onPageChangeListener;
    
    public CustomViewPager(final Context context) {
        super(context);
        this.onPageChangeListener = new CustomViewPager$1(this);
        this.init();
    }
    
    public CustomViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.onPageChangeListener = new CustomViewPager$1(this);
        this.init();
    }
    
    private ViewParent getListViewParent() {
        ViewParent viewParent;
        for (viewParent = this.getParent(); viewParent != null && !(viewParent instanceof ListView); viewParent = viewParent.getParent()) {}
        return viewParent;
    }
    
    private void init() {
        Log.v("CustomViewPager", "Created view pager");
    }
    
    public ViewPager$OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (onInterceptTouchEvent) {
            final ViewParent listViewParent = this.getListViewParent();
            if (listViewParent != null) {
                listViewParent.requestDisallowInterceptTouchEvent(true);
            }
        }
        return onInterceptTouchEvent;
    }
    
    protected void onPageSelected(final int n) {
    }
}
