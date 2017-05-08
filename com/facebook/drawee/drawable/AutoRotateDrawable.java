// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Canvas;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;

public class AutoRotateDrawable extends ForwardingDrawable implements Runnable
{
    private boolean mClockwise;
    private int mInterval;
    private boolean mIsScheduled;
    float mRotationAngle;
    
    public AutoRotateDrawable(final Drawable drawable, final int n) {
        this(drawable, n, true);
    }
    
    public AutoRotateDrawable(final Drawable drawable, final int mInterval, final boolean mClockwise) {
        super(Preconditions.checkNotNull(drawable));
        this.mRotationAngle = 0.0f;
        this.mIsScheduled = false;
        this.mInterval = mInterval;
        this.mClockwise = mClockwise;
    }
    
    private int getIncrement() {
        return (int)(20.0f / this.mInterval * 360.0f);
    }
    
    private void scheduleNextFrame() {
        if (!this.mIsScheduled) {
            this.mIsScheduled = true;
            this.scheduleSelf((Runnable)this, SystemClock.uptimeMillis() + 20L);
        }
    }
    
    @Override
    public void draw(final Canvas canvas) {
        final int save = canvas.save();
        final Rect bounds = this.getBounds();
        final int right = bounds.right;
        final int left = bounds.left;
        final int bottom = bounds.bottom;
        final int top = bounds.top;
        float mRotationAngle = this.mRotationAngle;
        if (!this.mClockwise) {
            mRotationAngle = 360.0f - this.mRotationAngle;
        }
        canvas.rotate(mRotationAngle, (float)((right - left) / 2 + bounds.left), (float)(bounds.top + (bottom - top) / 2));
        super.draw(canvas);
        canvas.restoreToCount(save);
        this.scheduleNextFrame();
    }
    
    @Override
    public void run() {
        this.mIsScheduled = false;
        this.mRotationAngle += this.getIncrement();
        this.invalidateSelf();
    }
}
