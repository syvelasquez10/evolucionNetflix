// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewPager;

public class TabLayout$ViewPagerOnTabSelectedListener implements TabLayout$OnTabSelectedListener
{
    private final ViewPager mViewPager;
    
    public TabLayout$ViewPagerOnTabSelectedListener(final ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }
    
    @Override
    public void onTabReselected(final TabLayout$Tab tabLayout$Tab) {
    }
    
    @Override
    public void onTabSelected(final TabLayout$Tab tabLayout$Tab) {
        this.mViewPager.setCurrentItem(tabLayout$Tab.getPosition());
    }
    
    @Override
    public void onTabUnselected(final TabLayout$Tab tabLayout$Tab) {
    }
}
