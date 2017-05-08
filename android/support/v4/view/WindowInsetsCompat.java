// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class WindowInsetsCompat
{
    private static final WindowInsetsCompat$WindowInsetsCompatImpl IMPL;
    private final Object mInsets;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = new WindowInsetsCompat$WindowInsetsCompatApi21Impl();
            return;
        }
        if (sdk_INT >= 20) {
            IMPL = new WindowInsetsCompat$WindowInsetsCompatApi20Impl();
            return;
        }
        IMPL = new WindowInsetsCompat$WindowInsetsCompatBaseImpl();
    }
    
    public WindowInsetsCompat(final WindowInsetsCompat windowInsetsCompat) {
        Object sourceWindowInsets;
        if (windowInsetsCompat == null) {
            sourceWindowInsets = null;
        }
        else {
            sourceWindowInsets = WindowInsetsCompat.IMPL.getSourceWindowInsets(windowInsetsCompat.mInsets);
        }
        this.mInsets = sourceWindowInsets;
    }
    
    WindowInsetsCompat(final Object mInsets) {
        this.mInsets = mInsets;
    }
    
    static Object unwrap(final WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat == null) {
            return null;
        }
        return windowInsetsCompat.mInsets;
    }
    
    static WindowInsetsCompat wrap(final Object o) {
        if (o == null) {
            return null;
        }
        return new WindowInsetsCompat(o);
    }
    
    public WindowInsetsCompat consumeStableInsets() {
        return WindowInsetsCompat.IMPL.consumeStableInsets(this.mInsets);
    }
    
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return WindowInsetsCompat.IMPL.consumeSystemWindowInsets(this.mInsets);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final WindowInsetsCompat windowInsetsCompat = (WindowInsetsCompat)o;
            if (this.mInsets != null) {
                return this.mInsets.equals(windowInsetsCompat.mInsets);
            }
            if (windowInsetsCompat.mInsets != null) {
                return false;
            }
        }
        return true;
    }
    
    public int getStableInsetBottom() {
        return WindowInsetsCompat.IMPL.getStableInsetBottom(this.mInsets);
    }
    
    public int getStableInsetLeft() {
        return WindowInsetsCompat.IMPL.getStableInsetLeft(this.mInsets);
    }
    
    public int getStableInsetRight() {
        return WindowInsetsCompat.IMPL.getStableInsetRight(this.mInsets);
    }
    
    public int getStableInsetTop() {
        return WindowInsetsCompat.IMPL.getStableInsetTop(this.mInsets);
    }
    
    public int getSystemWindowInsetBottom() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetBottom(this.mInsets);
    }
    
    public int getSystemWindowInsetLeft() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetLeft(this.mInsets);
    }
    
    public int getSystemWindowInsetRight() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetRight(this.mInsets);
    }
    
    public int getSystemWindowInsetTop() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetTop(this.mInsets);
    }
    
    public boolean hasInsets() {
        return WindowInsetsCompat.IMPL.hasInsets(this.mInsets);
    }
    
    public boolean hasStableInsets() {
        return WindowInsetsCompat.IMPL.hasStableInsets(this.mInsets);
    }
    
    public boolean hasSystemWindowInsets() {
        return WindowInsetsCompat.IMPL.hasSystemWindowInsets(this.mInsets);
    }
    
    @Override
    public int hashCode() {
        if (this.mInsets == null) {
            return 0;
        }
        return this.mInsets.hashCode();
    }
    
    public boolean isConsumed() {
        return WindowInsetsCompat.IMPL.isConsumed(this.mInsets);
    }
    
    public boolean isRound() {
        return WindowInsetsCompat.IMPL.isRound(this.mInsets);
    }
    
    public WindowInsetsCompat replaceSystemWindowInsets(final int n, final int n2, final int n3, final int n4) {
        return WindowInsetsCompat.IMPL.replaceSystemWindowInsets(this.mInsets, n, n2, n3, n4);
    }
    
    public WindowInsetsCompat replaceSystemWindowInsets(final Rect rect) {
        return WindowInsetsCompat.IMPL.replaceSystemWindowInsets(this.mInsets, rect);
    }
}
