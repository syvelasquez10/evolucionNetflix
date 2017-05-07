// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.AccelerateDecelerateInterpolator;

class MaterialProgressDrawable$EndCurveInterpolator extends AccelerateDecelerateInterpolator
{
    public float getInterpolation(final float n) {
        return super.getInterpolation(Math.max(0.0f, (n - 0.5f) * 2.0f));
    }
}
