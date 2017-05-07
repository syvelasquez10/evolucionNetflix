// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;

interface DrawerLayout$DrawerLayoutCompatImpl
{
    void applyMarginInsets(final ViewGroup$MarginLayoutParams p0, final Object p1, final int p2);
    
    void configureApplyInsets(final View p0);
    
    void dispatchChildInsets(final View p0, final Object p1, final int p2);
    
    int getTopInset(final Object p0);
}
