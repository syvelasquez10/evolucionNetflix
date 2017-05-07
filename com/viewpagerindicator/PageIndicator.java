// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import com.viewpagerindicator.android.osp.ViewPager;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;

public interface PageIndicator extends ViewPager$OnPageChangeListener
{
    void notifyDataSetChanged();
    
    void setCurrentItem(final int p0);
    
    void setOnPageChangeListener(final ViewPager$OnPageChangeListener p0);
    
    void setViewPager(final ViewPager p0);
    
    void setViewPager(final ViewPager p0, final int p1);
}
