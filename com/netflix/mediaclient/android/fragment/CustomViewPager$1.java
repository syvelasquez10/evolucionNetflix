// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.fragment;

import com.netflix.mediaclient.Log;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;

class CustomViewPager$1 implements ViewPager$OnPageChangeListener
{
    final /* synthetic */ CustomViewPager this$0;
    
    CustomViewPager$1(final CustomViewPager this$0) {
        this.this$0 = this$0;
    }
    
    public void onPageScrollStateChanged(final int n) {
    }
    
    public void onPageScrolled(final int n, final float n2, final int n3) {
    }
    
    public void onPageSelected(final int n) {
        if (Log.isLoggable()) {
            Log.v("CustomViewPager", "onPageSelected, position: " + n);
        }
        this.this$0.onPageSelected(n);
    }
}
