// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.view.View$MeasureSpec;
import android.support.v4.view.PagerAdapter;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.widget.HorizontalScrollView;
import android.view.View;
import android.view.View$OnClickListener;

class TabPageIndicator$1 implements View$OnClickListener
{
    final /* synthetic */ TabPageIndicator this$0;
    
    TabPageIndicator$1(final TabPageIndicator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final TabPageIndicator$TabView tabPageIndicator$TabView = (TabPageIndicator$TabView)view;
        final int currentItem = this.this$0.mViewPager.getCurrentItem();
        final int index = tabPageIndicator$TabView.getIndex();
        this.this$0.mViewPager.setCurrentItem(index);
        if (currentItem == index && this.this$0.mTabReselectedListener != null) {
            this.this$0.mTabReselectedListener.onTabReselected(index);
        }
    }
}
