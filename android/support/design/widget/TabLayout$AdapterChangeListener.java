// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager$OnAdapterChangeListener;

class TabLayout$AdapterChangeListener implements ViewPager$OnAdapterChangeListener
{
    private boolean mAutoRefresh;
    final /* synthetic */ TabLayout this$0;
    
    TabLayout$AdapterChangeListener(final TabLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAdapterChanged(final ViewPager viewPager, final PagerAdapter pagerAdapter, final PagerAdapter pagerAdapter2) {
        if (this.this$0.mViewPager == viewPager) {
            this.this$0.setPagerAdapter(pagerAdapter2, this.mAutoRefresh);
        }
    }
    
    void setAutoRefresh(final boolean mAutoRefresh) {
        this.mAutoRefresh = mAutoRefresh;
    }
}
