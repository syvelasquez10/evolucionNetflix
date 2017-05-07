// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import android.view.animation.Interpolator;

final class ViewPager$2 implements Interpolator
{
    public float getInterpolation(float n) {
        --n;
        return n * (n * n * n * n) + 1.0f;
    }
}
