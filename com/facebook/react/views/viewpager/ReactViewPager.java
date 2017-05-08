// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import java.util.List;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager$OnPageChangeListener;
import com.facebook.react.uimanager.UIManagerModule;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import android.support.v4.view.ViewPager;

public class ReactViewPager extends ViewPager
{
    private final EventDispatcher mEventDispatcher;
    private boolean mIsCurrentItemFromJs;
    private boolean mScrollEnabled;
    
    public ReactViewPager(final ReactContext reactContext) {
        super((Context)reactContext);
        this.mScrollEnabled = true;
        this.mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        this.mIsCurrentItemFromJs = false;
        this.setOnPageChangeListener(new ReactViewPager$PageChangeListener(this, null));
        this.setAdapter(new ReactViewPager$Adapter(this, null));
    }
    
    void addViewToAdapter(final View view, final int n) {
        this.getAdapter().addView(view, n);
    }
    
    @Override
    public ReactViewPager$Adapter getAdapter() {
        return (ReactViewPager$Adapter)super.getAdapter();
    }
    
    int getViewCountInAdapter() {
        return this.getAdapter().getCount();
    }
    
    View getViewFromAdapter(final int n) {
        return this.getAdapter().getViewAt(n);
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.mScrollEnabled && super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mScrollEnabled && super.onTouchEvent(motionEvent);
    }
    
    public void removeAllViewsFromAdapter() {
        this.getAdapter().removeAllViewsFromAdapter(this);
    }
    
    void removeViewFromAdapter(final int n) {
        this.getAdapter().removeViewAt(n);
    }
    
    public void setCurrentItemFromJs(final int n, final boolean b) {
        this.mIsCurrentItemFromJs = true;
        this.setCurrentItem(n, b);
        this.mIsCurrentItemFromJs = false;
    }
    
    public void setScrollEnabled(final boolean mScrollEnabled) {
        this.mScrollEnabled = mScrollEnabled;
    }
    
    public void setViews(final List<View> views) {
        this.getAdapter().setViews(views);
    }
}
