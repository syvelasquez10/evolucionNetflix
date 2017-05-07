// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.WindowInsets;

class WindowInsetsCompatApi21 extends WindowInsetsCompat
{
    private final WindowInsets mSource;
    
    WindowInsetsCompatApi21(final WindowInsets mSource) {
        this.mSource = mSource;
    }
    
    @Override
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return new WindowInsetsCompatApi21(this.mSource.consumeSystemWindowInsets());
    }
    
    @Override
    public int getSystemWindowInsetBottom() {
        return this.mSource.getSystemWindowInsetBottom();
    }
    
    @Override
    public int getSystemWindowInsetLeft() {
        return this.mSource.getSystemWindowInsetLeft();
    }
    
    @Override
    public int getSystemWindowInsetRight() {
        return this.mSource.getSystemWindowInsetRight();
    }
    
    @Override
    public int getSystemWindowInsetTop() {
        return this.mSource.getSystemWindowInsetTop();
    }
    
    @Override
    public boolean isConsumed() {
        return this.mSource.isConsumed();
    }
    
    @Override
    public WindowInsetsCompat replaceSystemWindowInsets(final int n, final int n2, final int n3, final int n4) {
        return new WindowInsetsCompatApi21(this.mSource.replaceSystemWindowInsets(n, n2, n3, n4));
    }
    
    WindowInsets unwrap() {
        return this.mSource;
    }
}
