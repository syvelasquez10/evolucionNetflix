// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.graphics.Canvas;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public class WrapperView extends ViewGroup
{
    Drawable mDivider;
    int mDividerHeight;
    View mHeader;
    View mItem;
    int mItemTop;
    
    WrapperView(final Context context) {
        super(context);
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeader == null && this.mDivider != null) {
            if (Build$VERSION.SDK_INT < 11) {
                canvas.clipRect(0, 0, this.getWidth(), this.mDividerHeight);
            }
            this.mDivider.draw(canvas);
        }
    }
    
    public View getHeader() {
        return this.mHeader;
    }
    
    public View getItem() {
        return this.mItem;
    }
    
    public boolean hasHeader() {
        return this.mHeader != null;
    }
    
    protected void onLayout(final boolean b, int width, int height, int measuredHeight, final int n) {
        width = this.getWidth();
        height = this.getHeight();
        if (this.mHeader != null) {
            measuredHeight = this.mHeader.getMeasuredHeight();
            this.mHeader.layout(0, 0, width, measuredHeight);
            this.mItemTop = measuredHeight;
            this.mItem.layout(0, measuredHeight, width, height);
            return;
        }
        if (this.mDivider != null) {
            this.mDivider.setBounds(0, 0, width, this.mDividerHeight);
            this.mItemTop = this.mDividerHeight;
            this.mItem.layout(0, this.mDividerHeight, width, height);
            return;
        }
        this.mItemTop = 0;
        this.mItem.layout(0, 0, width, height);
    }
    
    protected void onMeasure(int n, int size) {
        size = View$MeasureSpec.getSize(n);
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(size, 1073741824);
        if (this.mHeader != null) {
            final ViewGroup$LayoutParams layoutParams = this.mHeader.getLayoutParams();
            if (layoutParams != null && layoutParams.height > 0) {
                this.mHeader.measure(measureSpec, View$MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
            }
            else {
                this.mHeader.measure(measureSpec, View$MeasureSpec.makeMeasureSpec(0, 0));
            }
            n = this.mHeader.getMeasuredHeight() + 0;
        }
        else if (this.mDivider != null) {
            n = this.mDividerHeight + 0;
        }
        else {
            n = 0;
        }
        final ViewGroup$LayoutParams layoutParams2 = this.mItem.getLayoutParams();
        if (layoutParams2 != null && layoutParams2.height > 0) {
            this.mItem.measure(measureSpec, View$MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824));
        }
        else {
            this.mItem.measure(measureSpec, View$MeasureSpec.makeMeasureSpec(0, 0));
        }
        this.setMeasuredDimension(size, n + this.mItem.getMeasuredHeight());
    }
    
    void update(final View mItem, final View mHeader, final Drawable mDivider, final int mDividerHeight) {
        if (mItem == null) {
            throw new NullPointerException("List view item must not be null.");
        }
        if (this.mItem != mItem) {
            this.removeView(this.mItem);
            this.mItem = mItem;
            final ViewParent parent = mItem.getParent();
            if (parent != null && parent != this && parent instanceof ViewGroup) {
                ((WrapperView)parent).removeView(mItem);
            }
            this.addView(mItem);
        }
        if (this.mHeader != mHeader) {
            if (this.mHeader != null) {
                this.removeView(this.mHeader);
            }
            if ((this.mHeader = mHeader) != null) {
                this.addView(mHeader);
            }
        }
        if (this.mDivider != mDivider) {
            this.mDivider = mDivider;
            this.mDividerHeight = mDividerHeight;
            this.invalidate();
        }
    }
}
