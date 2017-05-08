// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.LinearLayout$LayoutParams;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.content.Context;
import android.graphics.Paint;
import android.widget.LinearLayout;

class TabLayout$SlidingTabStrip$2 extends ValueAnimatorCompat$AnimatorListenerAdapter
{
    final /* synthetic */ TabLayout$SlidingTabStrip this$1;
    final /* synthetic */ int val$position;
    
    TabLayout$SlidingTabStrip$2(final TabLayout$SlidingTabStrip this$1, final int val$position) {
        this.this$1 = this$1;
        this.val$position = val$position;
    }
    
    @Override
    public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$1.mSelectedPosition = this.val$position;
        this.this$1.mSelectionOffset = 0.0f;
    }
    
    @Override
    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        this.this$1.mSelectedPosition = this.val$position;
        this.this$1.mSelectionOffset = 0.0f;
    }
}
