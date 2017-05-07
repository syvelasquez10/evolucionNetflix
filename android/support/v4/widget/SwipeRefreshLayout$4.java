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

class SwipeRefreshLayout$4 extends Animation
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    final /* synthetic */ int val$endingAlpha;
    final /* synthetic */ int val$startingAlpha;
    
    SwipeRefreshLayout$4(final SwipeRefreshLayout this$0, final int val$startingAlpha, final int val$endingAlpha) {
        this.this$0 = this$0;
        this.val$startingAlpha = val$startingAlpha;
        this.val$endingAlpha = val$endingAlpha;
    }
    
    public void applyTransformation(final float n, final Transformation transformation) {
        this.this$0.mProgress.setAlpha((int)(this.val$startingAlpha + (this.val$endingAlpha - this.val$startingAlpha) * n));
    }
}
