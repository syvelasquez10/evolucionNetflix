// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import com.netflix.android.widgetry.R$dimen;
import android.view.View$MeasureSpec;
import com.netflix.android.widgetry.R$id;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import com.netflix.android.widgetry.R$layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;

public class BuffetBar$BuffetLayout extends LinearLayout
{
    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;
    private BuffetBar$BuffetLayout$OnAttachStateChangeListener mOnAttachStateChangeListener;
    private BuffetBar$BuffetLayout$OnLayoutChangeListener mOnLayoutChangeListener;
    
    public BuffetBar$BuffetLayout(final Context context) {
        this(context, null);
    }
    
    public BuffetBar$BuffetLayout(final Context context, final AttributeSet set) {
        super(context, set);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.SnackbarLayout);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_android_maxWidth, -1);
        this.mMaxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_maxActionInlineWidth, -1);
        if (obtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation((View)this, obtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_elevation, 0));
        }
        obtainStyledAttributes.recycle();
        this.setClickable(true);
        LayoutInflater.from(context).inflate(R$layout.design_layout_buffet_include, (ViewGroup)this);
        ViewCompat.setAccessibilityLiveRegion((View)this, 1);
        ViewCompat.setImportantForAccessibility((View)this, 1);
        ViewCompat.setFitsSystemWindows((View)this, true);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new BuffetBar$BuffetLayout$1(this));
    }
    
    private static void updateTopBottomPadding(final View view, final int n, final int n2) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), n, ViewCompat.getPaddingEnd(view), n2);
            return;
        }
        view.setPadding(view.getPaddingLeft(), n, view.getPaddingRight(), n2);
    }
    
    private boolean updateViewsWithinLayout(final int orientation, final int n, final int n2) {
        boolean b = false;
        if (orientation != this.getOrientation()) {
            this.setOrientation(orientation);
            b = true;
        }
        if (this.mMessageView.getPaddingTop() != n || this.mMessageView.getPaddingBottom() != n2) {
            updateTopBottomPadding((View)this.mMessageView, n, n2);
            b = true;
        }
        return b;
    }
    
    void animateChildrenIn(final int n, final int n2) {
        ViewCompat.setAlpha((View)this.mMessageView, 0.0f);
        ViewCompat.animate((View)this.mMessageView).alpha(1.0f).setDuration(n2).setStartDelay(n).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha((View)this.mActionView, 0.0f);
            ViewCompat.animate((View)this.mActionView).alpha(1.0f).setDuration(n2).setStartDelay(n).start();
        }
    }
    
    void animateChildrenOut(final int n, final int n2) {
        ViewCompat.setAlpha((View)this.mMessageView, 1.0f);
        ViewCompat.animate((View)this.mMessageView).alpha(0.0f).setDuration(n2).setStartDelay(n).start();
        if (this.mActionView.getVisibility() == 0) {
            ViewCompat.setAlpha((View)this.mActionView, 1.0f);
            ViewCompat.animate((View)this.mActionView).alpha(0.0f).setDuration(n2).setStartDelay(n).start();
        }
    }
    
    Button getActionView() {
        return this.mActionView;
    }
    
    TextView getMessageView() {
        return this.mMessageView;
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
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMessageView = (TextView)this.findViewById(R$id.snackbar_text);
        this.mActionView = (Button)this.findViewById(R$id.snackbar_action);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (this.mOnLayoutChangeListener != null) {
            this.mOnLayoutChangeListener.onLayoutChange((View)this, n, n2, n3, n4);
        }
    }
    
    protected void onMeasure(int n, final int n2) {
        super.onMeasure(n, n2);
        int measureSpec = n;
        if (this.mMaxWidth > 0) {
            measureSpec = n;
            if (this.getMeasuredWidth() > this.mMaxWidth) {
                measureSpec = View$MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
                super.onMeasure(measureSpec, n2);
            }
        }
        final int dimensionPixelSize = this.getResources().getDimensionPixelSize(R$dimen.buffetbar_padding_vertical_2lines);
        final int dimensionPixelSize2 = this.getResources().getDimensionPixelSize(R$dimen.buffetbar_padding_vertical);
        if (this.mMessageView.getLayout().getLineCount() > 1) {
            n = 1;
        }
        else {
            n = 0;
        }
        while (true) {
            Label_0170: {
                if (n != 0 && this.mMaxInlineActionWidth > 0 && this.mActionView.getMeasuredWidth() > this.mMaxInlineActionWidth) {
                    if (!this.updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
                        break Label_0170;
                    }
                    n = 1;
                }
                else {
                    if (n != 0) {
                        n = dimensionPixelSize;
                    }
                    else {
                        n = dimensionPixelSize2;
                    }
                    if (!this.updateViewsWithinLayout(0, n, n)) {
                        break Label_0170;
                    }
                    n = 1;
                }
                if (n != 0) {
                    super.onMeasure(measureSpec, n2);
                }
                return;
            }
            n = 0;
            continue;
        }
    }
    
    void setOnAttachStateChangeListener(final BuffetBar$BuffetLayout$OnAttachStateChangeListener mOnAttachStateChangeListener) {
        this.mOnAttachStateChangeListener = mOnAttachStateChangeListener;
    }
    
    void setOnLayoutChangeListener(final BuffetBar$BuffetLayout$OnLayoutChangeListener mOnLayoutChangeListener) {
        this.mOnLayoutChangeListener = mOnLayoutChangeListener;
    }
}
