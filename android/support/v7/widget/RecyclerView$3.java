// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.Interpolator;

final class RecyclerView$3 implements Interpolator
{
    public float getInterpolation(float n) {
        --n;
        return n * (n * n * n * n) + 1.0f;
    }
}
