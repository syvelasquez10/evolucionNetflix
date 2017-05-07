// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$KitKatDrawableImpl extends DrawableCompat$HoneycombDrawableImpl
{
    @Override
    public boolean isAutoMirrored(final Drawable drawable) {
        return DrawableCompatKitKat.isAutoMirrored(drawable);
    }
    
    @Override
    public void setAutoMirrored(final Drawable drawable, final boolean b) {
        DrawableCompatKitKat.setAutoMirrored(drawable, b);
    }
}
