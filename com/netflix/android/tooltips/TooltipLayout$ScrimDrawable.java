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
import android.widget.FrameLayout;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Paint;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewPropertyAnimator;
import android.view.ViewGroup;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

class TooltipLayout$ScrimDrawable extends Drawable
{
    final /* synthetic */ TooltipLayout this$0;
    
    private TooltipLayout$ScrimDrawable(final TooltipLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void draw(final Canvas canvas) {
        canvas.save();
        canvas.drawRect(0.0f, 0.0f, (float)this.this$0.getMeasuredWidth(), (float)this.this$0.getMeasuredHeight(), this.this$0.mScrimPaint);
        canvas.drawRect(this.this$0.mTargetBounds, this.this$0.mHighlightPaint);
        canvas.restore();
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void setAlpha(final int n) {
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
}
