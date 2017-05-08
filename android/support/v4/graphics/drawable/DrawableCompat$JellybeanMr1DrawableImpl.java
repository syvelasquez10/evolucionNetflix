// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$JellybeanMr1DrawableImpl extends DrawableCompat$HoneycombDrawableImpl
{
    @Override
    public int getLayoutDirection(final Drawable drawable) {
        final int layoutDirection = DrawableCompatJellybeanMr1.getLayoutDirection(drawable);
        if (layoutDirection >= 0) {
            return layoutDirection;
        }
        return 0;
    }
    
    @Override
    public boolean setLayoutDirection(final Drawable drawable, final int n) {
        return DrawableCompatJellybeanMr1.setLayoutDirection(drawable, n);
    }
}
