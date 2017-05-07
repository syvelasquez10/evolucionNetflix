// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.support.v4.view.PagerAdapter;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.viewpagerindicator.android.osp.ViewPager;
import android.view.View$OnClickListener;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.widget.HorizontalScrollView;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

class TabPageIndicator$TabView extends TextView
{
    private int mIndex;
    final /* synthetic */ TabPageIndicator this$0;
    
    public TabPageIndicator$TabView(final TabPageIndicator this$0, final Context context) {
        this.this$0 = this$0;
        super(context, (AttributeSet)null, R$attr.vpiTabPageIndicatorStyle);
    }
    
    public int getIndex() {
        return this.mIndex;
    }
    
    public void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.this$0.mMaxTabWidth > 0 && this.getMeasuredWidth() > this.this$0.mMaxTabWidth) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(this.this$0.mMaxTabWidth, 1073741824), n2);
        }
    }
}
