// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.animation.LinearInterpolator;
import android.graphics.drawable.Drawable$Callback;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.animation.Transformation;
import android.view.animation.Interpolator;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.View;

public class ProgressBarCompat extends View
{
    private static final int[] android_R_styleable_ProgressBar;
    private AlphaAnimation mAnimation;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private int mDuration;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private Interpolator mInterpolator;
    private long mLastDrawTime;
    private int mMax;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private int mProgress;
    private Drawable mProgressDrawable;
    private ProgressBarCompat$RefreshProgressRunnable mRefreshProgressRunnable;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;
    
    static {
        android_R_styleable_ProgressBar = new int[] { 16843062, 16843063, 16843064, 16843065, 16843066, 16843067, 16843068, 16843069, 16843070, 16843071, 16843039, 16843072, 16843040, 16843073 };
    }
    
    private void doRefreshProgress(int level, final int n, final boolean b, final boolean b2) {
        while (true) {
            while (true) {
                float n2 = 0.0f;
                Label_0085: {
                    synchronized (this) {
                        if (this.mMax > 0) {
                            n2 = n / this.mMax;
                        }
                        else {
                            n2 = 0.0f;
                        }
                        final Drawable mCurrentDrawable = this.mCurrentDrawable;
                        if (mCurrentDrawable == null) {
                            this.invalidate();
                            return;
                        }
                        if (mCurrentDrawable instanceof LayerDrawable) {
                            ((LayerDrawable)mCurrentDrawable).findDrawableByLayerId(level);
                        }
                        break Label_0085;
                        mCurrentDrawable.setLevel(level);
                        return;
                    }
                }
                level = (int)(n2 * 10000.0f);
                final Drawable drawable;
                if (drawable != null) {
                    final Drawable mCurrentDrawable = drawable;
                    continue;
                }
                continue;
            }
        }
    }
    
    private void refreshProgress(final int n, final int n2, final boolean b) {
        while (true) {
            while (true) {
                Label_0070: {
                    synchronized (this) {
                        if (this.mUiThreadId == Thread.currentThread().getId()) {
                            this.doRefreshProgress(n, n2, b, true);
                        }
                        else {
                            if (this.mRefreshProgressRunnable == null) {
                                break Label_0070;
                            }
                            final ProgressBarCompat$RefreshProgressRunnable mRefreshProgressRunnable = this.mRefreshProgressRunnable;
                            this.mRefreshProgressRunnable = null;
                            mRefreshProgressRunnable.setup(n, n2, b);
                            this.post((Runnable)mRefreshProgressRunnable);
                        }
                        return;
                    }
                }
                final ProgressBarCompat$RefreshProgressRunnable mRefreshProgressRunnable = new ProgressBarCompat$RefreshProgressRunnable(this, n, n2, b);
                continue;
            }
        }
    }
    
    private void updateDrawableBounds(int n, int n2) {
        final int n3 = n - this.getPaddingRight() - this.getPaddingLeft();
        final int n4 = n2 - this.getPaddingBottom() - this.getPaddingTop();
        Label_0138: {
            if (this.mIndeterminateDrawable != null) {
                while (true) {
                    Label_0194: {
                        if (!this.mOnlyIndeterminate || this.mIndeterminateDrawable instanceof AnimationDrawable) {
                            break Label_0194;
                        }
                        final float n5 = this.mIndeterminateDrawable.getIntrinsicWidth() / this.mIndeterminateDrawable.getIntrinsicHeight();
                        final float n6 = n / n2;
                        if (n5 == n6) {
                            break Label_0194;
                        }
                        int n8;
                        int n9;
                        if (n6 > n5) {
                            n2 *= (int)n5;
                            final int n7 = (n - n2) / 2;
                            n2 += n7;
                            n = n4;
                            n8 = 0;
                            n9 = n7;
                        }
                        else {
                            n *= (int)(1.0f / n5);
                            final int n10 = (n2 - n) / 2;
                            n2 = n3;
                            n += n10;
                            n8 = n10;
                            n9 = 0;
                        }
                        this.mIndeterminateDrawable.setBounds(n9, n8, n2, n);
                        break Label_0138;
                    }
                    final int n11 = 0;
                    n2 = n3;
                    n = n4;
                    int n8 = 0;
                    int n9 = n11;
                    continue;
                }
            }
            n = n4;
            n2 = n3;
        }
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.setBounds(0, 0, n2, n);
        }
    }
    
    private void updateDrawableState() {
        final int[] drawableState = this.getDrawableState();
        if (this.mProgressDrawable != null && this.mProgressDrawable.isStateful()) {
            this.mProgressDrawable.setState(drawableState);
        }
        if (this.mIndeterminateDrawable != null && this.mIndeterminateDrawable.isStateful()) {
            this.mIndeterminateDrawable.setState(drawableState);
        }
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.updateDrawableState();
    }
    
    Shape getDrawableShape() {
        return (Shape)new RoundRectShape(new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f }, (RectF)null, (float[])null);
    }
    
    public Drawable getIndeterminateDrawable() {
        return this.mIndeterminateDrawable;
    }
    
    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }
    
    public int getMax() {
        synchronized (this) {
            return this.mMax;
        }
    }
    
    public int getProgress() {
        synchronized (this) {
            int mProgress;
            if (this.mIndeterminate) {
                mProgress = 0;
            }
            else {
                mProgress = this.mProgress;
            }
            return mProgress;
        }
    }
    
    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }
    
    public int getSecondaryProgress() {
        synchronized (this) {
            int mSecondaryProgress;
            if (this.mIndeterminate) {
                mSecondaryProgress = 0;
            }
            else {
                mSecondaryProgress = this.mSecondaryProgress;
            }
            return mSecondaryProgress;
        }
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        if (!this.mInDrawing) {
            if (!this.verifyDrawable(drawable)) {
                super.invalidateDrawable(drawable);
                return;
            }
            final Rect bounds = drawable.getBounds();
            final int n = this.getScrollX() + this.getPaddingLeft();
            final int n2 = this.getScrollY() + this.getPaddingTop();
            this.invalidate(bounds.left + n, bounds.top + n2, n + bounds.right, bounds.bottom + n2);
        }
    }
    
    public boolean isIndeterminate() {
        synchronized (this) {
            return this.mIndeterminate;
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIndeterminate) {
            this.startAnimation();
        }
    }
    
    protected void onDetachedFromWindow() {
        if (this.mIndeterminate) {
            this.stopAnimation();
        }
        if (this.mRefreshProgressRunnable != null) {
            this.removeCallbacks((Runnable)this.mRefreshProgressRunnable);
        }
        super.onDetachedFromWindow();
    }
    
    protected void onDraw(final Canvas canvas) {
        synchronized (this) {
            super.onDraw(canvas);
            final Drawable mCurrentDrawable = this.mCurrentDrawable;
            if (mCurrentDrawable == null) {
                return;
            }
            canvas.save();
            canvas.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
            final long drawingTime = this.getDrawingTime();
            Label_0120: {
                if (this.mAnimation == null) {
                    break Label_0120;
                }
                this.mAnimation.getTransformation(drawingTime, this.mTransformation);
                final float alpha = this.mTransformation.getAlpha();
                try {
                    this.mInDrawing = true;
                    mCurrentDrawable.setLevel((int)(alpha * 10000.0f));
                    this.mInDrawing = false;
                    if (SystemClock.uptimeMillis() - this.mLastDrawTime >= 200L) {
                        this.mLastDrawTime = SystemClock.uptimeMillis();
                        this.postInvalidateDelayed(200L);
                    }
                    mCurrentDrawable.draw(canvas);
                    canvas.restore();
                    if (this.mShouldStartAnimationDrawable && mCurrentDrawable instanceof Animatable) {
                        ((Animatable)mCurrentDrawable).start();
                        this.mShouldStartAnimationDrawable = false;
                    }
                }
                finally {
                    this.mInDrawing = false;
                }
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        while (true) {
            int max = 0;
            while (true) {
                synchronized (this) {
                    final Drawable mCurrentDrawable = this.mCurrentDrawable;
                    if (mCurrentDrawable != null) {
                        final int max2 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, mCurrentDrawable.getIntrinsicWidth()));
                        max = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, mCurrentDrawable.getIntrinsicHeight()));
                        this.updateDrawableState();
                        this.setMeasuredDimension(resolveSize(max2 + (this.getPaddingLeft() + this.getPaddingRight()), n), resolveSize(max + (this.getPaddingTop() + this.getPaddingBottom()), n2));
                        return;
                    }
                }
                final int max2 = 0;
                continue;
            }
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final ProgressBarCompat$SavedState progressBarCompat$SavedState = (ProgressBarCompat$SavedState)parcelable;
        super.onRestoreInstanceState(progressBarCompat$SavedState.getSuperState());
        this.setProgress(progressBarCompat$SavedState.progress);
        this.setSecondaryProgress(progressBarCompat$SavedState.secondaryProgress);
    }
    
    public Parcelable onSaveInstanceState() {
        final ProgressBarCompat$SavedState progressBarCompat$SavedState = new ProgressBarCompat$SavedState(super.onSaveInstanceState());
        progressBarCompat$SavedState.progress = this.mProgress;
        progressBarCompat$SavedState.secondaryProgress = this.mSecondaryProgress;
        return (Parcelable)progressBarCompat$SavedState;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        this.updateDrawableBounds(n, n2);
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        if (Build$VERSION.SDK_INT >= 8) {
            super.onVisibilityChanged(view, n);
        }
        if (this.mIndeterminate) {
            if (n != 8 && n != 4) {
                this.startAnimation();
                return;
            }
            this.stopAnimation();
        }
    }
    
    public void postInvalidate() {
        if (!this.mNoInvalidate) {
            super.postInvalidate();
        }
    }
    
    public void setIndeterminate(final boolean mIndeterminate) {
        synchronized (this) {
            if ((!this.mOnlyIndeterminate || !this.mIndeterminate) && mIndeterminate != this.mIndeterminate) {
                this.mIndeterminate = mIndeterminate;
                if (mIndeterminate) {
                    this.mCurrentDrawable = this.mIndeterminateDrawable;
                    this.startAnimation();
                }
                else {
                    this.mCurrentDrawable = this.mProgressDrawable;
                    this.stopAnimation();
                }
            }
        }
    }
    
    public void setIndeterminateDrawable(final Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback((Drawable$Callback)this);
        }
        this.mIndeterminateDrawable = drawable;
        if (this.mIndeterminate) {
            this.mCurrentDrawable = drawable;
            this.postInvalidate();
        }
    }
    
    public void setInterpolator(final Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }
    
    public void setMax(final int n) {
        // monitorenter(this)
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        try {
            if (n2 != this.mMax) {
                this.mMax = n2;
                this.postInvalidate();
                if (this.mProgress > n2) {
                    this.mProgress = n2;
                }
                this.refreshProgress(16908301, this.mProgress, false);
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public void setProgress(final int n) {
        synchronized (this) {
            this.setProgress(n, false);
        }
    }
    
    void setProgress(int n, final boolean b) {
        while (true) {
            while (true) {
                Label_0072: {
                    synchronized (this) {
                        if (!this.mIndeterminate) {
                            if (n >= 0) {
                                break Label_0072;
                            }
                            n = 0;
                            int mMax = n;
                            if (n > this.mMax) {
                                mMax = this.mMax;
                            }
                            if (mMax != this.mProgress) {
                                this.refreshProgress(16908301, this.mProgress = mMax, b);
                            }
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public void setProgressDrawable(final Drawable drawable) {
        int n;
        if (this.mProgressDrawable != null && drawable != this.mProgressDrawable) {
            this.mProgressDrawable.setCallback((Drawable$Callback)null);
            n = 1;
        }
        else {
            n = 0;
        }
        if (drawable != null) {
            drawable.setCallback((Drawable$Callback)this);
            final int minimumHeight = drawable.getMinimumHeight();
            if (this.mMaxHeight < minimumHeight) {
                this.mMaxHeight = minimumHeight;
                this.requestLayout();
            }
        }
        this.mProgressDrawable = drawable;
        if (!this.mIndeterminate) {
            this.mCurrentDrawable = drawable;
            this.postInvalidate();
        }
        if (n != 0) {
            this.updateDrawableBounds(this.getWidth(), this.getHeight());
            this.updateDrawableState();
            this.doRefreshProgress(16908301, this.mProgress, false, false);
            this.doRefreshProgress(16908303, this.mSecondaryProgress, false, false);
        }
    }
    
    public void setSecondaryProgress(int n) {
        while (true) {
            final int n2 = 0;
            while (true) {
                Label_0072: {
                    synchronized (this) {
                        if (!this.mIndeterminate) {
                            if (n >= 0) {
                                break Label_0072;
                            }
                            n = n2;
                            int mMax = n;
                            if (n > this.mMax) {
                                mMax = this.mMax;
                            }
                            if (mMax != this.mSecondaryProgress) {
                                this.refreshProgress(16908303, this.mSecondaryProgress = mMax, false);
                            }
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public void setVisibility(final int visibility) {
        if (this.getVisibility() != visibility) {
            super.setVisibility(visibility);
            if (this.mIndeterminate) {
                if (visibility != 8 && visibility != 4) {
                    this.startAnimation();
                    return;
                }
                this.stopAnimation();
            }
        }
    }
    
    void startAnimation() {
        if (this.getVisibility() != 0) {
            return;
        }
        if (this.mIndeterminateDrawable instanceof Animatable) {
            this.mShouldStartAnimationDrawable = true;
            this.mAnimation = null;
        }
        else {
            if (this.mInterpolator == null) {
                this.mInterpolator = (Interpolator)new LinearInterpolator();
            }
            this.mTransformation = new Transformation();
            (this.mAnimation = new AlphaAnimation(0.0f, 1.0f)).setRepeatMode(this.mBehavior);
            this.mAnimation.setRepeatCount(-1);
            this.mAnimation.setDuration((long)this.mDuration);
            this.mAnimation.setInterpolator(this.mInterpolator);
            this.mAnimation.setStartTime(-1L);
        }
        this.postInvalidate();
    }
    
    void stopAnimation() {
        this.mAnimation = null;
        this.mTransformation = null;
        if (this.mIndeterminateDrawable instanceof Animatable) {
            ((Animatable)this.mIndeterminateDrawable).stop();
            this.mShouldStartAnimationDrawable = false;
        }
        this.postInvalidate();
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return drawable == this.mProgressDrawable || drawable == this.mIndeterminateDrawable || super.verifyDrawable(drawable);
    }
}
