// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.progressbar;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.FrameLayout;

class ProgressBarContainerView extends FrameLayout
{
    private boolean mAnimating;
    private Integer mColor;
    private boolean mIndeterminate;
    private double mProgress;
    private ProgressBar mProgressBar;
    
    public ProgressBarContainerView(final Context context) {
        super(context);
        this.mIndeterminate = true;
        this.mAnimating = true;
    }
    
    private void setColor(final ProgressBar progressBar) {
        Drawable drawable;
        if (progressBar.isIndeterminate()) {
            drawable = progressBar.getIndeterminateDrawable();
        }
        else {
            drawable = progressBar.getProgressDrawable();
        }
        if (drawable == null) {
            return;
        }
        if (this.mColor != null) {
            drawable.setColorFilter((int)this.mColor, PorterDuff$Mode.SRC_IN);
            return;
        }
        drawable.clearColorFilter();
    }
    
    public void apply() {
        if (this.mProgressBar == null) {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
        this.mProgressBar.setIndeterminate(this.mIndeterminate);
        this.setColor(this.mProgressBar);
        this.mProgressBar.setProgress((int)(this.mProgress * 1000.0));
        if (this.mAnimating) {
            this.mProgressBar.setVisibility(0);
            return;
        }
        this.mProgressBar.setVisibility(8);
    }
    
    public void setAnimating(final boolean mAnimating) {
        this.mAnimating = mAnimating;
    }
    
    public void setColor(final Integer mColor) {
        this.mColor = mColor;
    }
    
    public void setIndeterminate(final boolean mIndeterminate) {
        this.mIndeterminate = mIndeterminate;
    }
    
    public void setProgress(final double mProgress) {
        this.mProgress = mProgress;
    }
    
    public void setStyle(final String s) {
        (this.mProgressBar = ReactProgressBarViewManager.createProgressBar(this.getContext(), ReactProgressBarViewManager.getStyleFromString(s))).setMax(1000);
        this.removeAllViews();
        this.addView((View)this.mProgressBar, new ViewGroup$LayoutParams(-1, -1));
    }
}
