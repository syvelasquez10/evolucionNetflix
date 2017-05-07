// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$MDrawableImpl extends DrawableCompat$LollipopMr1DrawableImpl
{
    @Override
    public void setLayoutDirection(final Drawable drawable, final int n) {
        DrawableCompatApi23.setLayoutDirection(drawable, n);
    }
}
