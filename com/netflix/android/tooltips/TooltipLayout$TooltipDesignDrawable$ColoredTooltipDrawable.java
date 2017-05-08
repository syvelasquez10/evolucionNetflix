// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.support.v4.view.ViewCompat;
import android.graphics.Typeface;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.KeyEvent;
import android.animation.Animator$AnimatorListener;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewPropertyAnimator;
import android.view.ViewGroup;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.PathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.Paint$Style;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

class TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable extends Drawable
{
    private final int mPaddingStart;
    private final Paint mPaint;
    private final Path mPath;
    final /* synthetic */ TooltipLayout$TooltipDesignDrawable this$1;
    
    private TooltipLayout$TooltipDesignDrawable$ColoredTooltipDrawable(final TooltipLayout$TooltipDesignDrawable this$1, final int color) {
        this.this$1 = this$1;
        this.mPath = new Path();
        (this.mPaint = new Paint()).setColor(color);
        this.mPaint.setStyle(Paint$Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setPathEffect((PathEffect)new CornerPathEffect((float)this$1.this$0.getResources().getDimensionPixelSize(R$dimen.tooltip_radius)));
        this.mPaddingStart = this$1.this$0.getResources().getDimensionPixelSize(R$dimen.tooltip_horizontal_padding);
    }
    
    public void draw(final Canvas canvas) {
        canvas.drawPath(this.mPath, this.mPaint);
    }
    
    public int getColor() {
        return this.mPaint.getColor();
    }
    
    public int getOpacity() {
        return -1;
    }
    
    public void setAlpha(final int alpha) {
        this.mPaint.setAlpha(alpha);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }
    
    public void update() {
        this.mPath.reset();
        if (this.this$1.this$0.mGravity == 48) {
            final int n = this.this$1.this$0.mTooltip.getMeasuredHeight() - this.this$1.this$0.mArrow.height();
            this.mPath.moveTo(0.0f, (float)0);
            this.mPath.lineTo((float)this.this$1.this$0.mTooltip.getMeasuredWidth(), (float)0);
            this.mPath.lineTo((float)this.this$1.this$0.mTooltip.getMeasuredWidth(), (float)n);
            this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds + this.this$1.this$0.mArrow.width() / 2 - this.mPaddingStart), (float)n);
            this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds - this.mPaddingStart), (float)(this.this$1.this$0.mArrow.height() + n));
            this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds - this.this$1.this$0.mArrow.width() / 2 - this.mPaddingStart), (float)n);
            this.mPath.lineTo(0.0f, (float)n);
            this.mPath.lineTo(0.0f, (float)0);
            this.mPath.close();
            this.this$1.this$0.mTooltip.setPadding(0, 0, 0, this.this$1.this$0.mArrow.height());
            return;
        }
        final int height = this.this$1.this$0.mArrow.height();
        final int measuredHeight = this.this$1.this$0.mTooltip.getMeasuredHeight();
        this.mPath.moveTo(0.0f, (float)height);
        this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds - this.this$1.this$0.mArrow.width() / 2 - this.mPaddingStart), (float)height);
        this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds - this.mPaddingStart), (float)(height - this.this$1.this$0.mArrow.height()));
        this.mPath.lineTo((float)(this.this$1.this$0.mArrowCenterXInTooltipBounds + this.this$1.this$0.mArrow.width() / 2 - this.mPaddingStart), (float)height);
        this.mPath.lineTo((float)this.this$1.this$0.mTooltip.getMeasuredWidth(), (float)height);
        this.mPath.lineTo((float)this.this$1.this$0.mTooltip.getMeasuredWidth(), (float)measuredHeight);
        this.mPath.lineTo(0.0f, (float)measuredHeight);
        this.mPath.lineTo(0.0f, (float)height);
        this.mPath.close();
        this.this$1.this$0.mTooltip.setPadding(0, this.this$1.this$0.mArrow.height(), 0, 0);
    }
}
