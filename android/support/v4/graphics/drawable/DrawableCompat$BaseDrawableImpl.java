// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class DrawableCompat$BaseDrawableImpl implements DrawableCompat$DrawableImpl
{
    @Override
    public boolean isAutoMirrored(final Drawable drawable) {
        return false;
    }
    
    @Override
    public void jumpToCurrentState(final Drawable drawable) {
    }
    
    @Override
    public void setAutoMirrored(final Drawable drawable, final boolean b) {
    }
    
    @Override
    public void setHotspot(final Drawable drawable, final float n, final float n2) {
    }
    
    @Override
    public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    public void setTint(final Drawable drawable, final int n) {
    }
    
    @Override
    public void setTintList(final Drawable drawable, final ColorStateList list) {
    }
    
    @Override
    public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
    }
}
