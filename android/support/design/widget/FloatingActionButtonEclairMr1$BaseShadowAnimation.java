// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Transformation;
import android.view.animation.Animation;

abstract class FloatingActionButtonEclairMr1$BaseShadowAnimation extends Animation
{
    private float mShadowSizeDiff;
    private float mShadowSizeStart;
    final /* synthetic */ FloatingActionButtonEclairMr1 this$0;
    
    private FloatingActionButtonEclairMr1$BaseShadowAnimation(final FloatingActionButtonEclairMr1 this$0) {
        this.this$0 = this$0;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        this.this$0.mShadowDrawable.setShadowSize(this.mShadowSizeStart + this.mShadowSizeDiff * n);
    }
    
    protected abstract float getTargetShadowSize();
    
    public void reset() {
        super.reset();
        this.mShadowSizeStart = this.this$0.mShadowDrawable.getShadowSize();
        this.mShadowSizeDiff = this.getTargetShadowSize() - this.mShadowSizeStart;
    }
}
