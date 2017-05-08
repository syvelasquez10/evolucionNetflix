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
    static final int COLLAPSIBLE_FLAGS = 10;
    static final int FLAG_QUICK_RETURN = 5;
    static final int FLAG_SNAP = 17;
    public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
    public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
    public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
    public static final int SCROLL_FLAG_SCROLL = 1;
    public static final int SCROLL_FLAG_SNAP = 16;
    int mScrollFlags;
    Interpolator mScrollInterpolator;
    
    public AppBarLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mScrollFlags = 1;
    }
    
    public AppBarLayout$LayoutParams(final int n, final int n2, final float n3) {
        super(n, n2, n3);
        this.mScrollFlags = 1;
    }
    
    public AppBarLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mScrollFlags = 1;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.AppBarLayout_Layout);
        this.mScrollFlags = obtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
        if (obtainStyledAttributes.hasValue(R$styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
            this.mScrollInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R$styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
        }
        obtainStyledAttributes.recycle();
    }
    
    public AppBarLayout$LayoutParams(final AppBarLayout$LayoutParams appBarLayout$LayoutParams) {
        super((LinearLayout$LayoutParams)appBarLayout$LayoutParams);
        this.mScrollFlags = 1;
        this.mScrollFlags = appBarLayout$LayoutParams.mScrollFlags;
        this.mScrollInterpolator = appBarLayout$LayoutParams.mScrollInterpolator;
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
    
    public int getScrollFlags() {
        return this.mScrollFlags;
    }
    
    public Interpolator getScrollInterpolator() {
        return this.mScrollInterpolator;
    }
    
    boolean isCollapsible() {
        return (this.mScrollFlags & 0x1) == 0x1 && (this.mScrollFlags & 0xA) != 0x0;
    }
    
    public void setScrollFlags(final int mScrollFlags) {
        this.mScrollFlags = mScrollFlags;
    }
    
    public void setScrollInterpolator(final Interpolator mScrollInterpolator) {
        this.mScrollInterpolator = mScrollInterpolator;
    }
}
