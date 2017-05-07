// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.Transformation;
import android.view.animation.Animation;

class MaterialProgressDrawable$1 extends Animation
{
    final /* synthetic */ MaterialProgressDrawable this$0;
    final /* synthetic */ MaterialProgressDrawable$Ring val$ring;
    
    MaterialProgressDrawable$1(final MaterialProgressDrawable this$0, final MaterialProgressDrawable$Ring val$ring) {
        this.this$0 = this$0;
        this.val$ring = val$ring;
    }
    
    public void applyTransformation(final float n, final Transformation transformation) {
        final float n2 = (float)(Math.floor(this.val$ring.getStartingRotation() / 0.8f) + 1.0);
        this.val$ring.setStartTrim(this.val$ring.getStartingStartTrim() + (this.val$ring.getStartingEndTrim() - this.val$ring.getStartingStartTrim()) * n);
        this.val$ring.setRotation((n2 - this.val$ring.getStartingRotation()) * n + this.val$ring.getStartingRotation());
        this.val$ring.setArrowScale(1.0f - n);
    }
}
