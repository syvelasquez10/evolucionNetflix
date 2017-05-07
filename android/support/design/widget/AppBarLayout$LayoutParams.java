// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.view.animation.AnimationUtils;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.LinearLayout$LayoutParams;

public class AppBarLayout$LayoutParams extends LinearLayout$LayoutParams
{
    int mScrollFlags;
    Interpolator mScrollInterpolator;
    
    public AppBarLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mScrollFlags = 1;
    }
    
    public AppBarLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mScrollFlags = 1;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.AppBarLayout_LayoutParams);
        this.mScrollFlags = obtainStyledAttributes.getInt(R$styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
        if (obtainStyledAttributes.hasValue(R$styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
            this.mScrollInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R$styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
        }
        obtainStyledAttributes.recycle();
    }
    
    public AppBarLayout$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mScrollFlags = 1;
    }
    
    public AppBarLayout$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mScrollFlags = 1;
    }
    
    public AppBarLayout$LayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        super(linearLayout$LayoutParams);
        this.mScrollFlags = 1;
    }
    
    public Interpolator getScrollInterpolator() {
        return this.mScrollInterpolator;
    }
}
