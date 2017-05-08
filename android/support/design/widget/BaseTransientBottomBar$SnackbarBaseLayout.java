// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.content.res.TypedArray;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout
{
    private BaseTransientBottomBar$OnAttachStateChangeListener mOnAttachStateChangeListener;
    private BaseTransientBottomBar$OnLayoutChangeListener mOnLayoutChangeListener;
    
    BaseTransientBottomBar$SnackbarBaseLayout(final Context context) {
        this(context, null);
    }
    
    BaseTransientBottomBar$SnackbarBaseLayout(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.SnackbarLayout);
        if (obtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation((View)this, obtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_elevation, 0));
        }
        obtainStyledAttributes.recycle();
        this.setClickable(true);
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewAttachedToWindow((View)this);
        }
        ViewCompat.requestApplyInsets((View)this);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewDetachedFromWindow((View)this);
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mOnLayoutChangeListener != null) {
            this.mOnLayoutChangeListener.onLayoutChange((View)this, n, n2, n3, n4);
        }
    }
    
    void setOnAttachStateChangeListener(final BaseTransientBottomBar$OnAttachStateChangeListener mOnAttachStateChangeListener) {
        this.mOnAttachStateChangeListener = mOnAttachStateChangeListener;
    }
    
    void setOnLayoutChangeListener(final BaseTransientBottomBar$OnLayoutChangeListener mOnLayoutChangeListener) {
        this.mOnLayoutChangeListener = mOnLayoutChangeListener;
    }
}
