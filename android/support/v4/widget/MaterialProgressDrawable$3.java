// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.animation.Animation$AnimationListener;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.content.res.Resources;
import android.view.View;
import android.graphics.drawable.Drawable$Callback;
import java.util.ArrayList;
import android.view.animation.Interpolator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Transformation;
import android.view.animation.Animation;

class MaterialProgressDrawable$3 extends Animation
{
    final /* synthetic */ MaterialProgressDrawable this$0;
    final /* synthetic */ MaterialProgressDrawable$Ring val$ring;
    
    MaterialProgressDrawable$3(final MaterialProgressDrawable this$0, final MaterialProgressDrawable$Ring val$ring) {
        this.this$0 = this$0;
        this.val$ring = val$ring;
    }
    
    public void applyTransformation(final float n, final Transformation transformation) {
        final float n2 = (float)Math.toRadians(this.val$ring.getStrokeWidth() / (6.283185307179586 * this.val$ring.getCenterRadius()));
        final float startingEndTrim = this.val$ring.getStartingEndTrim();
        final float startingStartTrim = this.val$ring.getStartingStartTrim();
        final float startingRotation = this.val$ring.getStartingRotation();
        this.val$ring.setEndTrim((0.8f - n2) * MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(n) + startingEndTrim);
        this.val$ring.setStartTrim(MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(n) * 0.8f + startingStartTrim);
        this.val$ring.setRotation(0.25f * n + startingRotation);
        this.this$0.setRotation(144.0f * n + 720.0f * (this.this$0.mRotationCount / 5.0f));
    }
}
