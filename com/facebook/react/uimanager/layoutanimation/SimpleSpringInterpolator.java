// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Interpolator;

class SimpleSpringInterpolator implements Interpolator
{
    public float getInterpolation(final float n) {
        return (float)(1.0 + Math.pow(2.0, -10.0f * n) * Math.sin((n - 0.125f) * 3.141592653589793 * 2.0 / 0.5));
    }
}
