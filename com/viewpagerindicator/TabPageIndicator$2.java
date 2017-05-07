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
import android.view.View$OnClickListener;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.widget.HorizontalScrollView;
import android.view.View;

class TabPageIndicator$2 implements Runnable
{
    final /* synthetic */ TabPageIndicator this$0;
    final /* synthetic */ View val$tabView;
    
    TabPageIndicator$2(final TabPageIndicator this$0, final View val$tabView) {
        this.this$0 = this$0;
        this.val$tabView = val$tabView;
    }
    
    @Override
    public void run() {
        this.this$0.smoothScrollTo(this.val$tabView.getLeft() - (this.this$0.getWidth() - this.val$tabView.getWidth()) / 2, 0);
        this.this$0.mTabSelector = null;
    }
}
