// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import android.view.MotionEvent;
import android.widget.ListView;
import android.view.ViewParent;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;

public class CustomViewPager extends ViewPager
{
    private static final boolean ALLOW_OVERLAPPED_PAGES = true;
    private static final String TAG = "CustomViewPager";
    private final OnPageChangeListener onPageChangeListener;
    private int pageMarginOffsetPx;
    private final boolean shouldOverlapPagesByDefault;
    
    public CustomViewPager(final Context context) {
        super(context);
        this.shouldOverlapPagesByDefault = true;
        this.onPageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(final int n) {
            }
            
            @Override
            public void onPageScrolled(final int n, final float n2, final int n3) {
            }
            
            @Override
            public void onPageSelected(final int n) {
                Log.v("CustomViewPager", "onPageSelected, position: " + n);
                CustomViewPager.this.onPageSelected(n);
            }
        };
        this.init();
    }
    
    public CustomViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.shouldOverlapPagesByDefault = true;
        this.onPageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(final int n) {
            }
            
            @Override
            public void onPageScrolled(final int n, final float n2, final int n3) {
            }
            
            @Override
            public void onPageSelected(final int n) {
                Log.v("CustomViewPager", "onPageSelected, position: " + n);
                CustomViewPager.this.onPageSelected(n);
            }
        };
        this.init();
    }
    
    public static void applyContentOverlapPadding(final NetflixActivity netflixActivity, final View view) {
        view.setPadding(LoMoUtils.getLomoFragOffsetLeftPx(netflixActivity), 0, LoMoUtils.getLomoFragOffsetRightPx(netflixActivity), 0);
    }
    
    private ViewParent getListViewParent() {
        ViewParent viewParent;
        for (viewParent = this.getParent(); viewParent != null && !(viewParent instanceof ListView); viewParent = viewParent.getParent()) {}
        return viewParent;
    }
    
    private void init() {
        Log.v("CustomViewPager", "Created view pager");
        this.pageMarginOffsetPx = (LoMoUtils.getLomoFragOffsetRightPx((NetflixActivity)this.getContext()) + LoMoUtils.getLomoFragOffsetLeftPx((NetflixActivity)this.getContext())) * -1;
        this.setPagesToOverlap(true);
    }
    
    public OnPageChangeListener getOnPageChangeListener() {
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
    
    protected void setPagesToOverlap(final boolean b) {
        int pageMarginOffsetPx;
        if (b) {
            pageMarginOffsetPx = this.pageMarginOffsetPx;
        }
        else {
            pageMarginOffsetPx = 0;
        }
        this.setPageMargin(pageMarginOffsetPx);
    }
}
