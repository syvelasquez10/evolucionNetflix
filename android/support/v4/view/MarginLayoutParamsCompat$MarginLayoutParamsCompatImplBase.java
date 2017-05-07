// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup$MarginLayoutParams;

class MarginLayoutParamsCompat$MarginLayoutParamsCompatImplBase implements MarginLayoutParamsCompat$MarginLayoutParamsCompatImpl
{
    @Override
    public int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return viewGroup$MarginLayoutParams.rightMargin;
    }
    
    @Override
    public int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return viewGroup$MarginLayoutParams.leftMargin;
    }
}
