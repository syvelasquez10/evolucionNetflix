// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

class WindowInsetsCompat$WindowInsetsCompatApi20Impl extends WindowInsetsCompat$WindowInsetsCompatBaseImpl
{
    @Override
    public WindowInsetsCompat consumeSystemWindowInsets(final Object o) {
        return new WindowInsetsCompat(WindowInsetsCompatApi20.consumeSystemWindowInsets(o));
    }
    
    @Override
    public Object getSourceWindowInsets(final Object o) {
        return WindowInsetsCompatApi20.getSourceWindowInsets(o);
    }
    
    @Override
    public int getSystemWindowInsetBottom(final Object o) {
        return WindowInsetsCompatApi20.getSystemWindowInsetBottom(o);
    }
    
    @Override
    public int getSystemWindowInsetLeft(final Object o) {
        return WindowInsetsCompatApi20.getSystemWindowInsetLeft(o);
    }
    
    @Override
    public int getSystemWindowInsetRight(final Object o) {
        return WindowInsetsCompatApi20.getSystemWindowInsetRight(o);
    }
    
    @Override
    public int getSystemWindowInsetTop(final Object o) {
        return WindowInsetsCompatApi20.getSystemWindowInsetTop(o);
    }
    
    @Override
    public boolean hasInsets(final Object o) {
        return WindowInsetsCompatApi20.hasInsets(o);
    }
    
    @Override
    public boolean hasSystemWindowInsets(final Object o) {
        return WindowInsetsCompatApi20.hasSystemWindowInsets(o);
    }
    
    @Override
    public boolean isRound(final Object o) {
        return WindowInsetsCompatApi20.isRound(o);
    }
    
    @Override
    public WindowInsetsCompat replaceSystemWindowInsets(final Object o, final int n, final int n2, final int n3, final int n4) {
        return new WindowInsetsCompat(WindowInsetsCompatApi20.replaceSystemWindowInsets(o, n, n2, n3, n4));
    }
}
