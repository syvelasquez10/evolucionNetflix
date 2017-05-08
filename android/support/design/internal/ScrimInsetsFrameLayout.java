// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v4.view.WindowInsetsCompat;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class ScrimInsetsFrameLayout extends FrameLayout
{
    Drawable mInsetForeground;
    Rect mInsets;
    private Rect mTempRect;
    
    public ScrimInsetsFrameLayout(final Context context) {
        this(context, null);
    }
    
    public ScrimInsetsFrameLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ScrimInsetsFrameLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mTempRect = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ScrimInsetsFrameLayout, n, R$style.Widget_Design_ScrimInsetsFrameLayout);
        this.mInsetForeground = obtainStyledAttributes.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        obtainStyledAttributes.recycle();
        this.setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new ScrimInsetsFrameLayout$1(this));
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        final int width = this.getWidth();
        final int height = this.getHeight();
        if (this.mInsets != null && this.mInsetForeground != null) {
            final int save = canvas.save();
            canvas.translate((float)this.getScrollX(), (float)this.getScrollY());
            this.mTempRect.set(0, 0, width, this.mInsets.top);
            this.mInsetForeground.setBounds(this.mTempRect);
            this.mInsetForeground.draw(canvas);
            this.mTempRect.set(0, height - this.mInsets.bottom, width, height);
            this.mInsetForeground.setBounds(this.mTempRect);
            this.mInsetForeground.draw(canvas);
            this.mTempRect.set(0, this.mInsets.top, this.mInsets.left, height - this.mInsets.bottom);
            this.mInsetForeground.setBounds(this.mTempRect);
            this.mInsetForeground.draw(canvas);
            this.mTempRect.set(width - this.mInsets.right, this.mInsets.top, width, height - this.mInsets.bottom);
            this.mInsetForeground.setBounds(this.mTempRect);
            this.mInsetForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mInsetForeground != null) {
            this.mInsetForeground.setCallback((Drawable$Callback)this);
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mInsetForeground != null) {
            this.mInsetForeground.setCallback((Drawable$Callback)null);
        }
    }
    
    protected void onInsetsChanged(final WindowInsetsCompat windowInsetsCompat) {
    }
}
