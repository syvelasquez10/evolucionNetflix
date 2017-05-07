// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$LollipopMr1DrawableImpl extends DrawableCompat$LollipopDrawableImpl
{
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatApi22.wrapForTinting(drawable);
    }
}
