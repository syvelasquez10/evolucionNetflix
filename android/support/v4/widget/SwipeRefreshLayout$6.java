// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.res.Resources;
import android.view.View$MeasureSpec;
import android.util.Log;
import android.widget.AbsListView;
import android.os.Build$VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.util.DisplayMetrics;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.view.animation.Animation;

class SwipeRefreshLayout$6 extends Animation
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    
    SwipeRefreshLayout$6(final SwipeRefreshLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void applyTransformation(final float n, final Transformation transformation) {
        int n2;
        if (!this.this$0.mUsingCustomStart) {
            n2 = (int)(this.this$0.mSpinnerFinalOffset - Math.abs(this.this$0.mOriginalOffsetTop));
        }
        else {
            n2 = (int)this.this$0.mSpinnerFinalOffset;
        }
        this.this$0.setTargetOffsetTopAndBottom((int)((n2 - this.this$0.mFrom) * n) + this.this$0.mFrom - this.this$0.mCircleView.getTop(), false);
    }
}
