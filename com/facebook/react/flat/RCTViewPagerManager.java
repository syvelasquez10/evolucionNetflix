// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.views.viewpager.ReactViewPager;
import java.util.List;
import android.view.ViewGroup;
import com.facebook.react.views.viewpager.ReactViewPagerManager;

public class RCTViewPagerManager extends ReactViewPagerManager
{
    @Override
    public void addViews(final ReactViewPager reactViewPager, final List<View> views) {
        reactViewPager.setViews(views);
    }
    
    @Override
    public void removeAllViews(final ReactViewPager reactViewPager) {
        reactViewPager.removeAllViewsFromAdapter();
    }
}
