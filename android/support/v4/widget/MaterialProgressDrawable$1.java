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
        if (this.this$0.mFinishing) {
            this.this$0.applyFinishTranslation(n, this.val$ring);
            return;
        }
        final float minProgressArc = this.this$0.getMinProgressArc(this.val$ring);
        final float startingEndTrim = this.val$ring.getStartingEndTrim();
        final float startingStartTrim = this.val$ring.getStartingStartTrim();
        final float startingRotation = this.val$ring.getStartingRotation();
        this.this$0.updateRingColor(n, this.val$ring);
        if (n <= 0.5f) {
            this.val$ring.setStartTrim(startingStartTrim + MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(n / 0.5f) * (0.8f - minProgressArc));
        }
        if (n > 0.5f) {
            this.val$ring.setEndTrim((0.8f - minProgressArc) * MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation((n - 0.5f) / 0.5f) + startingEndTrim);
        }
        this.val$ring.setRotation(0.25f * n + startingRotation);
        this.this$0.setRotation(216.0f * n + 1080.0f * (this.this$0.mRotationCount / 5.0f));
    }
}
