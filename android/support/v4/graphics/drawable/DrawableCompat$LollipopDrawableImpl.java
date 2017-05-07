// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class DrawableCompat$LollipopDrawableImpl extends DrawableCompat$KitKatDrawableImpl
{
    @Override
    public void setHotspot(final Drawable drawable, final float n, final float n2) {
        DrawableCompatLollipop.setHotspot(drawable, n, n2);
    }
    
    @Override
    public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        DrawableCompatLollipop.setHotspotBounds(drawable, n, n2, n3, n4);
    }
    
    @Override
    public void setTint(final Drawable drawable, final int n) {
        DrawableCompatLollipop.setTint(drawable, n);
    }
    
    @Override
    public void setTintList(final Drawable drawable, final ColorStateList list) {
        DrawableCompatLollipop.setTintList(drawable, list);
    }
    
    @Override
    public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompatLollipop.setTintMode(drawable, porterDuff$Mode);
    }
    
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatLollipop.wrapForTinting(drawable);
    }
}
