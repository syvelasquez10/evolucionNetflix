// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Build$VERSION;

final class ViewUtils$1 implements ValueAnimatorCompat$Creator
{
    @Override
    public ValueAnimatorCompat createAnimator() {
        ValueAnimatorCompat$Impl valueAnimatorCompat$Impl;
        if (Build$VERSION.SDK_INT >= 12) {
            valueAnimatorCompat$Impl = new ValueAnimatorCompatImplHoneycombMr1();
        }
        else {
            valueAnimatorCompat$Impl = new ValueAnimatorCompatImplGingerbread();
        }
        return new ValueAnimatorCompat(valueAnimatorCompat$Impl);
    }
}
