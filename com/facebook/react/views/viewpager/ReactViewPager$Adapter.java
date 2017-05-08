// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewPager$OnPageChangeListener;
import com.facebook.react.uimanager.UIManagerModule;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.Collection;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.support.v4.view.PagerAdapter;

class ReactViewPager$Adapter extends PagerAdapter
{
    private boolean mIsViewPagerInIntentionallyInconsistentState;
    private final List<View> mViews;
    final /* synthetic */ ReactViewPager this$0;
    
    private ReactViewPager$Adapter(final ReactViewPager this$0) {
        this.this$0 = this$0;
        this.mViews = new ArrayList<View>();
        this.mIsViewPagerInIntentionallyInconsistentState = false;
    }
    
    void addView(final View view, final int n) {
        this.mViews.add(n, view);
        this.notifyDataSetChanged();
        this.this$0.setOffscreenPageLimit(this.mViews.size());
    }
    
    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        viewGroup.removeView((View)o);
    }
    
    @Override
    public int getCount() {
        return this.mViews.size();
    }
    
    @Override
    public int getItemPosition(final Object o) {
        if (this.mIsViewPagerInIntentionallyInconsistentState || !this.mViews.contains(o)) {
            return -2;
        }
        return this.mViews.indexOf(o);
    }
    
    View getViewAt(final int n) {
        return this.mViews.get(n);
    }
    
    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        final View view = this.mViews.get(n);
        viewGroup.addView(view, 0, this.this$0.generateDefaultLayoutParams());
        return view;
    }
    
    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
    
    void removeAllViewsFromAdapter(final ViewPager viewPager) {
        this.mViews.clear();
        viewPager.removeAllViews();
        this.mIsViewPagerInIntentionallyInconsistentState = true;
    }
    
    void removeViewAt(final int n) {
        this.mViews.remove(n);
        this.notifyDataSetChanged();
        this.this$0.setOffscreenPageLimit(this.mViews.size());
    }
    
    void setViews(final List<View> list) {
        this.mViews.clear();
        this.mViews.addAll(list);
        this.notifyDataSetChanged();
        this.mIsViewPagerInIntentionallyInconsistentState = false;
    }
}
