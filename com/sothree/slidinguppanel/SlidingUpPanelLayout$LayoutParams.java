// 
// Decompiled by Procyon v0.5.30
// 

package com.sothree.slidinguppanel;

import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.view.ViewGroup$MarginLayoutParams;

public class SlidingUpPanelLayout$LayoutParams extends ViewGroup$MarginLayoutParams
{
    private static final int[] ATTRS;
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;
    
    static {
        ATTRS = new int[] { 16843137 };
    }
    
    public SlidingUpPanelLayout$LayoutParams() {
        super(-1, -1);
    }
    
    public SlidingUpPanelLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
    }
    
    public SlidingUpPanelLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        context.obtainStyledAttributes(set, SlidingUpPanelLayout$LayoutParams.ATTRS).recycle();
    }
    
    public SlidingUpPanelLayout$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
    }
    
    public SlidingUpPanelLayout$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
    }
    
    public SlidingUpPanelLayout$LayoutParams(final SlidingUpPanelLayout$LayoutParams slidingUpPanelLayout$LayoutParams) {
        super((ViewGroup$MarginLayoutParams)slidingUpPanelLayout$LayoutParams);
    }
}
