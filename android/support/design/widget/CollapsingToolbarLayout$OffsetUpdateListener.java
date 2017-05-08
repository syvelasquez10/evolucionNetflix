// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;

class CollapsingToolbarLayout$OffsetUpdateListener implements AppBarLayout$OnOffsetChangedListener
{
    final /* synthetic */ CollapsingToolbarLayout this$0;
    
    CollapsingToolbarLayout$OffsetUpdateListener(final CollapsingToolbarLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onOffsetChanged(final AppBarLayout appBarLayout, final int mCurrentOffset) {
        this.this$0.mCurrentOffset = mCurrentOffset;
        int systemWindowInsetTop;
        if (this.this$0.mLastInsets != null) {
            systemWindowInsetTop = this.this$0.mLastInsets.getSystemWindowInsetTop();
        }
        else {
            systemWindowInsetTop = 0;
        }
        for (int childCount = this.this$0.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.this$0.getChildAt(i);
            final CollapsingToolbarLayout$LayoutParams collapsingToolbarLayout$LayoutParams = (CollapsingToolbarLayout$LayoutParams)child.getLayoutParams();
            final ViewOffsetHelper viewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(child);
            switch (collapsingToolbarLayout$LayoutParams.mCollapseMode) {
                case 1: {
                    viewOffsetHelper.setTopAndBottomOffset(MathUtils.constrain(-mCurrentOffset, 0, this.this$0.getMaxOffsetForPinChild(child)));
                    break;
                }
                case 2: {
                    viewOffsetHelper.setTopAndBottomOffset(Math.round(collapsingToolbarLayout$LayoutParams.mParallaxMult * -mCurrentOffset));
                    break;
                }
            }
        }
        this.this$0.updateScrimVisibility();
        if (this.this$0.mStatusBarScrim != null && systemWindowInsetTop > 0) {
            ViewCompat.postInvalidateOnAnimation((View)this.this$0);
        }
        this.this$0.mCollapsingTextHelper.setExpansionFraction(Math.abs(mCurrentOffset) / (this.this$0.getHeight() - ViewCompat.getMinimumHeight((View)this.this$0) - systemWindowInsetTop));
    }
}
