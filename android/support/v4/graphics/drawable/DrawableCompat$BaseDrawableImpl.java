// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.content.res.Resources$Theme;
import android.graphics.drawable.Drawable;

class DrawableCompat$BaseDrawableImpl implements DrawableCompat$DrawableImpl
{
    @Override
    public void applyTheme(final Drawable drawable, final Resources$Theme resources$Theme) {
    }
    
    @Override
    public boolean canApplyTheme(final Drawable drawable) {
        return false;
    }
    
    @Override
    public void clearColorFilter(final Drawable drawable) {
        drawable.clearColorFilter();
    }
    
    @Override
    public int getAlpha(final Drawable drawable) {
        return 0;
    }
    
    @Override
    public ColorFilter getColorFilter(final Drawable drawable) {
        return null;
    }
    
    @Override
    public int getLayoutDirection(final Drawable drawable) {
        return 0;
    }
    
    @Override
    public void inflate(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        DrawableCompatBase.inflate(drawable, resources, xmlPullParser, set, resources$Theme);
    }
    
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
    public boolean setLayoutDirection(final Drawable drawable, final int n) {
        return false;
    }
    
    @Override
    public void setTint(final Drawable drawable, final int n) {
        DrawableCompatBase.setTint(drawable, n);
    }
    
    @Override
    public void setTintList(final Drawable drawable, final ColorStateList list) {
        DrawableCompatBase.setTintList(drawable, list);
    }
    
    @Override
    public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompatBase.setTintMode(drawable, porterDuff$Mode);
    }
    
    @Override
    public Drawable wrap(final Drawable drawable) {
        return DrawableCompatBase.wrapForTinting(drawable);
    }
}
