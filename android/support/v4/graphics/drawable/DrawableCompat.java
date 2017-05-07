// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;

public class DrawableCompat
{
    static final DrawableImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (DrawableImpl)new LDrawableImpl();
            return;
        }
        if (sdk_INT >= 19) {
            IMPL = (DrawableImpl)new KitKatDrawableImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = (DrawableImpl)new HoneycombDrawableImpl();
            return;
        }
        IMPL = (DrawableImpl)new BaseDrawableImpl();
    }
    
    public static boolean isAutoMirrored(final Drawable drawable) {
        return DrawableCompat.IMPL.isAutoMirrored(drawable);
    }
    
    public static void jumpToCurrentState(final Drawable drawable) {
        DrawableCompat.IMPL.jumpToCurrentState(drawable);
    }
    
    public static void setAutoMirrored(final Drawable drawable, final boolean b) {
        DrawableCompat.IMPL.setAutoMirrored(drawable, b);
    }
    
    public static void setHotspot(final Drawable drawable, final float n, final float n2) {
        DrawableCompat.IMPL.setHotspot(drawable, n, n2);
    }
    
    public static void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        DrawableCompat.IMPL.setHotspotBounds(drawable, n, n2, n3, n4);
    }
    
    public static void setTint(final Drawable drawable, final int n) {
        DrawableCompat.IMPL.setTint(drawable, n);
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList list) {
        DrawableCompat.IMPL.setTintList(drawable, list);
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompat.IMPL.setTintMode(drawable, porterDuff$Mode);
    }
    
    static class BaseDrawableImpl implements DrawableImpl
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
    
    interface DrawableImpl
    {
        boolean isAutoMirrored(final Drawable p0);
        
        void jumpToCurrentState(final Drawable p0);
        
        void setAutoMirrored(final Drawable p0, final boolean p1);
        
        void setHotspot(final Drawable p0, final float p1, final float p2);
        
        void setHotspotBounds(final Drawable p0, final int p1, final int p2, final int p3, final int p4);
        
        void setTint(final Drawable p0, final int p1);
        
        void setTintList(final Drawable p0, final ColorStateList p1);
        
        void setTintMode(final Drawable p0, final PorterDuff$Mode p1);
    }
    
    static class HoneycombDrawableImpl extends BaseDrawableImpl
    {
        @Override
        public void jumpToCurrentState(final Drawable drawable) {
            DrawableCompatHoneycomb.jumpToCurrentState(drawable);
        }
    }
    
    static class KitKatDrawableImpl extends HoneycombDrawableImpl
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
    
    static class LDrawableImpl extends KitKatDrawableImpl
    {
        @Override
        public void setHotspot(final Drawable drawable, final float n, final float n2) {
            DrawableCompatL.setHotspot(drawable, n, n2);
        }
        
        @Override
        public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
            DrawableCompatL.setHotspotBounds(drawable, n, n2, n3, n4);
        }
        
        @Override
        public void setTint(final Drawable drawable, final int n) {
            DrawableCompatL.setTint(drawable, n);
        }
        
        @Override
        public void setTintList(final Drawable drawable, final ColorStateList list) {
            DrawableCompatL.setTintList(drawable, list);
        }
        
        @Override
        public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            DrawableCompatL.setTintMode(drawable, porterDuff$Mode);
        }
    }
}
