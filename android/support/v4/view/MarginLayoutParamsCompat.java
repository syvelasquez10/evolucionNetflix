// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup$MarginLayoutParams;
import android.os.Build$VERSION;

public class MarginLayoutParamsCompat
{
    static final MarginLayoutParamsCompat$MarginLayoutParamsCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = new MarginLayoutParamsCompat$MarginLayoutParamsCompatImplJbMr1();
            return;
        }
        IMPL = new MarginLayoutParamsCompat$MarginLayoutParamsCompatImplBase();
    }
    
    public static int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompat.IMPL.getMarginEnd(viewGroup$MarginLayoutParams);
    }
    
    public static int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompat.IMPL.getMarginStart(viewGroup$MarginLayoutParams);
    }
}
