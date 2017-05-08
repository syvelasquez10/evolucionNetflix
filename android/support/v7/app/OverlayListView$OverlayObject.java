// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.animation.Interpolator;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class OverlayListView$OverlayObject
{
    private BitmapDrawable mBitmap;
    private float mCurrentAlpha;
    private Rect mCurrentBounds;
    private int mDeltaY;
    private long mDuration;
    private float mEndAlpha;
    private Interpolator mInterpolator;
    private boolean mIsAnimationEnded;
    private boolean mIsAnimationStarted;
    private OverlayListView$OverlayObject$OnAnimationEndListener mListener;
    private float mStartAlpha;
    private Rect mStartRect;
    private long mStartTime;
    
    public BitmapDrawable getBitmapDrawable() {
        return this.mBitmap;
    }
    
    public boolean update(final long n) {
        float n2 = 0.0f;
        if (this.mIsAnimationEnded) {
            return false;
        }
        final float max = Math.max(0.0f, Math.min(1.0f, (n - this.mStartTime) / this.mDuration));
        if (this.mIsAnimationStarted) {
            n2 = max;
        }
        float interpolation;
        if (this.mInterpolator == null) {
            interpolation = n2;
        }
        else {
            interpolation = this.mInterpolator.getInterpolation(n2);
        }
        final int n3 = (int)(this.mDeltaY * interpolation);
        this.mCurrentBounds.top = this.mStartRect.top + n3;
        this.mCurrentBounds.bottom = n3 + this.mStartRect.bottom;
        this.mCurrentAlpha = interpolation * (this.mEndAlpha - this.mStartAlpha) + this.mStartAlpha;
        if (this.mBitmap != null && this.mCurrentBounds != null) {
            this.mBitmap.setAlpha((int)(this.mCurrentAlpha * 255.0f));
            this.mBitmap.setBounds(this.mCurrentBounds);
        }
        if (this.mIsAnimationStarted && n2 >= 1.0f) {
            this.mIsAnimationEnded = true;
            if (this.mListener != null) {
                this.mListener.onAnimationEnd();
            }
        }
        return !this.mIsAnimationEnded;
    }
}
