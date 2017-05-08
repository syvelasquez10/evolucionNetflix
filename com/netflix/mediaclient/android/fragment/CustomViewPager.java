// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.viewpagerindicator.android.osp.ViewPager$ItemInfo;
import com.netflix.mediaclient.Log;
import android.widget.ListView;
import android.view.ViewParent;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import com.viewpagerindicator.android.osp.ViewPager;

public class CustomViewPager extends ViewPager
{
    private static final String TAG = "CustomViewPager";
    private final ViewPager$OnPageChangeListener onPageChangeListener;
    
    public CustomViewPager(final Context context) {
        super(context);
        this.onPageChangeListener = (ViewPager$OnPageChangeListener)new CustomViewPager$1(this);
        this.init();
    }
    
    public CustomViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.onPageChangeListener = (ViewPager$OnPageChangeListener)new CustomViewPager$1(this);
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
    
    protected ViewPager$ItemInfo addNewItem(final int position, final int n) {
        if (!LocalizationUtils.isCurrentLocaleRTL()) {
            return super.addNewItem(position, n);
        }
        final ViewPager$ItemInfo viewPager$ItemInfo = new ViewPager$ItemInfo();
        viewPager$ItemInfo.position = position;
        viewPager$ItemInfo.object = this.getAdapter().instantiateItem((ViewGroup)this, position);
        viewPager$ItemInfo.widthFactor = this.getAdapter().getPageWidth(position);
        if (viewPager$ItemInfo.object instanceof LoadingView && LocalizationUtils.isCurrentLocaleRTL()) {
            if (Log.isLoggable()) {
                Log.d("CustomViewPager", "Adding loading page for RTL locale, pos: " + position);
            }
            return null;
        }
        if (n < 0 || n >= this.getItems().size()) {
            Log.d("CustomViewPager", "Add to end");
            this.getItems().add(viewPager$ItemInfo);
            return viewPager$ItemInfo;
        }
        if (Log.isLoggable()) {
            Log.d("CustomViewPager", "Add to index " + n);
        }
        this.getItems().add(n, viewPager$ItemInfo);
        return viewPager$ItemInfo;
    }
    
    public ViewPager$OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }
    
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
