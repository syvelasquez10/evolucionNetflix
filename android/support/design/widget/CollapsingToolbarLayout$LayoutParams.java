// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout$LayoutParams;

public class CollapsingToolbarLayout$LayoutParams extends FrameLayout$LayoutParams
{
    public static final int COLLAPSE_MODE_OFF = 0;
    public static final int COLLAPSE_MODE_PARALLAX = 2;
    public static final int COLLAPSE_MODE_PIN = 1;
    private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
    int mCollapseMode;
    float mParallaxMult;
    
    public CollapsingToolbarLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public CollapsingToolbarLayout$LayoutParams(final int n, final int n2, final int n3) {
        super(n, n2, n3);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public CollapsingToolbarLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CollapsingToolbarLayout_Layout);
        this.mCollapseMode = obtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
        this.setParallaxMultiplier(obtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
        obtainStyledAttributes.recycle();
    }
    
    public CollapsingToolbarLayout$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public CollapsingToolbarLayout$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public CollapsingToolbarLayout$LayoutParams(final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        super(frameLayout$LayoutParams);
        this.mCollapseMode = 0;
        this.mParallaxMult = 0.5f;
    }
    
    public int getCollapseMode() {
        return this.mCollapseMode;
    }
    
    public float getParallaxMultiplier() {
        return this.mParallaxMult;
    }
    
    public void setCollapseMode(final int mCollapseMode) {
        this.mCollapseMode = mCollapseMode;
    }
    
    public void setParallaxMultiplier(final float mParallaxMult) {
        this.mParallaxMult = mParallaxMult;
    }
}
