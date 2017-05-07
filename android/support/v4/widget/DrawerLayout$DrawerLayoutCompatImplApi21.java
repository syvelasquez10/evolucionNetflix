// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;

class DrawerLayout$DrawerLayoutCompatImplApi21 implements DrawerLayout$DrawerLayoutCompatImpl
{
    @Override
    public void applyMarginInsets(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final Object o, final int n) {
        DrawerLayoutCompatApi21.applyMarginInsets(viewGroup$MarginLayoutParams, o, n);
    }
    
    @Override
    public void configureApplyInsets(final View view) {
        DrawerLayoutCompatApi21.configureApplyInsets(view);
    }
    
    @Override
    public void dispatchChildInsets(final View view, final Object o, final int n) {
        DrawerLayoutCompatApi21.dispatchChildInsets(view, o, n);
    }
    
    @Override
    public int getTopInset(final Object o) {
        return DrawerLayoutCompatApi21.getTopInset(o);
    }
}
