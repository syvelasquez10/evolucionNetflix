// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.Transformation;
import android.view.animation.Animation;

class SwipeRefreshLayout$2 extends Animation
{
    final /* synthetic */ SwipeRefreshLayout this$0;
    
    SwipeRefreshLayout$2(final SwipeRefreshLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void applyTransformation(final float animationProgress, final Transformation transformation) {
        this.this$0.setAnimationProgress(animationProgress);
    }
}
