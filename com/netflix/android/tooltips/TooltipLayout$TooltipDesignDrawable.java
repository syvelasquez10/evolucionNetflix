// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.support.v4.view.ViewCompat;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.KeyEvent;
import android.animation.Animator$AnimatorListener;
import android.graphics.Paint$Style;
import android.graphics.PathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Paint;
import android.widget.TextView;
import android.view.ViewPropertyAnimator;
import android.view.ViewGroup;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class TooltipLayout$TooltipDesignDrawable extends Drawable
{
    private TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable mAccentDrawable;
    private TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable mBackgroundDrawable;
    private final Rect mWhitePart;
    final /* synthetic */ TooltipLayout this$0;
    
    private TooltipLayout$TooltipDesignDrawable(final TooltipLayout this$0) {
        this.this$0 = this$0;
        this.mWhitePart = new Rect();
        this.mAccentDrawable = new TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable(this, this.this$0.mAccentColor, null);
        this.mBackgroundDrawable = new TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable(this, this.this$0.mBackgroundColor, null);
    }
    
    private void update() {
        if (this.mAccentDrawable.getColor() != this.this$0.mAccentColor) {
            this.mAccentDrawable = new TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable(this, this.this$0.mAccentColor, null);
        }
        if (this.mBackgroundDrawable.getColor() != this.this$0.mBackgroundColor) {
            this.mBackgroundDrawable = new TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable(this, this.this$0.mBackgroundColor, null);
        }
        this.mWhitePart.top = 0;
        this.mWhitePart.bottom = this.this$0.mTooltip.getMeasuredHeight();
        if (this.this$0.getLayoutDirection() == 1) {
            this.mWhitePart.left = 0;
            this.mWhitePart.right = this.this$0.mTooltip.getMeasuredWidth() - this.this$0.mTitle.getMeasuredWidth();
        }
        else {
            this.mWhitePart.left = this.this$0.mTitle.getMeasuredWidth();
            this.mWhitePart.right = this.this$0.mTooltip.getMeasuredWidth();
        }
        this.mAccentDrawable.update();
        this.mBackgroundDrawable.update();
    }
    
    public void draw(final Canvas canvas) {
        canvas.save();
        this.mAccentDrawable.draw(canvas);
        canvas.clipRect(this.mWhitePart);
        this.mBackgroundDrawable.draw(canvas);
        canvas.restore();
    }
    
    public int getOpacity() {
        return -1;
    }
    
    public void setAlpha(final int n) {
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.mAccentDrawable.setBounds(n, n2, n3, n4);
        this.mBackgroundDrawable.setBounds(n, n2, n3, n4);
    }
    
    public void setBounds(final Rect bounds) {
        super.setBounds(bounds);
        this.mAccentDrawable.setBounds(bounds);
        this.mBackgroundDrawable.setBounds(bounds);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
}
