// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.lang.ref.WeakReference;
import android.support.v4.view.ViewPager$OnPageChangeListener;

public class TabLayout$TabLayoutOnPageChangeListener implements ViewPager$OnPageChangeListener
{
    private int mPreviousScrollState;
    private int mScrollState;
    private final WeakReference<TabLayout> mTabLayoutRef;
    
    public TabLayout$TabLayoutOnPageChangeListener(final TabLayout tabLayout) {
        this.mTabLayoutRef = new WeakReference<TabLayout>(tabLayout);
    }
    
    @Override
    public void onPageScrollStateChanged(final int mScrollState) {
        this.mPreviousScrollState = this.mScrollState;
        this.mScrollState = mScrollState;
    }
    
    @Override
    public void onPageScrolled(final int n, final float n2, final int n3) {
        boolean b = false;
        final TabLayout tabLayout = this.mTabLayoutRef.get();
        if (tabLayout != null) {
            final boolean b2 = this.mScrollState != 2 || this.mPreviousScrollState == 1;
            if (this.mScrollState != 2 || this.mPreviousScrollState != 0) {
                b = true;
            }
            tabLayout.setScrollPosition(n, n2, b2, b);
        }
    }
    
    @Override
    public void onPageSelected(final int n) {
        final TabLayout tabLayout = this.mTabLayoutRef.get();
        if (tabLayout != null && tabLayout.getSelectedTabPosition() != n && n < tabLayout.getTabCount()) {
            tabLayout.selectTab(tabLayout.getTabAt(n), this.mScrollState == 0 || (this.mScrollState == 2 && this.mPreviousScrollState == 0));
        }
    }
    
    void reset() {
        this.mScrollState = 0;
        this.mPreviousScrollState = 0;
    }
}
