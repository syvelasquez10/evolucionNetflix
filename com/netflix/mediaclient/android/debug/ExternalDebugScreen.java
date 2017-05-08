// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.debug;

import android.graphics.ColorFilter;
import java.util.Iterator;
import android.graphics.RectF;
import android.view.View;
import java.util.WeakHashMap;
import android.graphics.drawable.Drawable;

public abstract class ExternalDebugScreen extends Drawable
{
    private final WeakHashMap<View, String> mAttachedViews;
    private final RectF mMeasuredBounds;
    
    public ExternalDebugScreen() {
        this.mMeasuredBounds = new RectF();
        this.mAttachedViews = new WeakHashMap<View, String>();
    }
    
    protected int getBackgroundTint() {
        return -16777216;
    }
    
    final RectF getMeasuredBounds() {
        return this.mMeasuredBounds;
    }
    
    public final int getOpacity() {
        return -3;
    }
    
    protected abstract String getTag();
    
    public final void invalidateSelf() {
        for (final View view : this.mAttachedViews.keySet()) {
            if (view != null) {
                view.invalidate();
            }
        }
    }
    
    public void onAttachedToWindow() {
    }
    
    final void onAttachedToWindow(final View view) {
        this.mAttachedViews.put(view, view.toString());
        this.onAttachedToWindow();
    }
    
    public void onDetachedFromWindow() {
    }
    
    final void onDetachedFromWindow(final View view) {
        this.mAttachedViews.remove(view);
        this.onDetachedFromWindow();
    }
    
    protected abstract void onMeasure(final View p0);
    
    public final void setAlpha(final int n) {
    }
    
    public final void setColorFilter(final ColorFilter colorFilter) {
    }
    
    protected void setMeasuredBounds(final float right, final float bottom) {
        this.mMeasuredBounds.right = right;
        this.mMeasuredBounds.bottom = bottom;
    }
}
