// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.database.DataSetObserver;

class PagerTitleStrip$PageListener extends DataSetObserver implements ViewPager$OnAdapterChangeListener, ViewPager$OnPageChangeListener
{
    private int mScrollState;
    final /* synthetic */ PagerTitleStrip this$0;
    
    PagerTitleStrip$PageListener(final PagerTitleStrip this$0) {
        this.this$0 = this$0;
    }
    
    public void onAdapterChanged(final ViewPager viewPager, final PagerAdapter pagerAdapter, final PagerAdapter pagerAdapter2) {
        this.this$0.updateAdapter(pagerAdapter, pagerAdapter2);
    }
    
    public void onChanged() {
        float mLastKnownPositionOffset = 0.0f;
        this.this$0.updateText(this.this$0.mPager.getCurrentItem(), this.this$0.mPager.getAdapter());
        if (this.this$0.mLastKnownPositionOffset >= 0.0f) {
            mLastKnownPositionOffset = this.this$0.mLastKnownPositionOffset;
        }
        this.this$0.updateTextPositions(this.this$0.mPager.getCurrentItem(), mLastKnownPositionOffset, true);
    }
    
    public void onPageScrollStateChanged(final int mScrollState) {
        this.mScrollState = mScrollState;
    }
    
    public void onPageScrolled(final int n, final float n2, int n3) {
        n3 = n;
        if (n2 > 0.5f) {
            n3 = n + 1;
        }
        this.this$0.updateTextPositions(n3, n2, false);
    }
    
    public void onPageSelected(final int n) {
        float mLastKnownPositionOffset = 0.0f;
        if (this.mScrollState == 0) {
            this.this$0.updateText(this.this$0.mPager.getCurrentItem(), this.this$0.mPager.getAdapter());
            if (this.this$0.mLastKnownPositionOffset >= 0.0f) {
                mLastKnownPositionOffset = this.this$0.mLastKnownPositionOffset;
            }
            this.this$0.updateTextPositions(this.this$0.mPager.getCurrentItem(), mLastKnownPositionOffset, true);
        }
    }
}
