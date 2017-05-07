// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.widget.ImageView;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.widget.HorizontalScrollView;
import android.view.View;

class IconPageIndicator$1 implements Runnable
{
    final /* synthetic */ IconPageIndicator this$0;
    final /* synthetic */ View val$iconView;
    
    IconPageIndicator$1(final IconPageIndicator this$0, final View val$iconView) {
        this.this$0 = this$0;
        this.val$iconView = val$iconView;
    }
    
    @Override
    public void run() {
        this.this$0.smoothScrollTo(this.val$iconView.getLeft() - (this.this$0.getWidth() - this.val$iconView.getWidth()) / 2, 0);
        this.this$0.mIconSelector = null;
    }
}
