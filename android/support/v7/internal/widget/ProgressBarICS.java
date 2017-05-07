// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.view.animation.LinearInterpolator;
import android.view.animation.AnimationUtils;
import android.graphics.drawable.Drawable$Callback;
import android.os.Parcelable;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Transformation;
import android.graphics.Bitmap;
import android.view.animation.Interpolator;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.View;

public class ProgressBarICS extends View
{
    private static final int ANIMATION_RESOLUTION = 200;
    private static final int MAX_LEVEL = 10000;
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
    private RefreshProgressRunnable mRefreshProgressRunnable;
    Bitmap mSampleTile;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;
    
    static {
        android_R_styleable_ProgressBar = new int[] { 16843062, 16843063, 16843064, 16843065, 16843066, 16843067, 16843068, 16843069, 16843070, 16843071, 16843039, 16843072, 16843040, 16843073 };
    }
    
    public ProgressBarICS(final Context context, final AttributeSet set, int resourceId, final int n) {
        boolean indeterminate = false;
        super(context, set, resourceId);
        this.mUiThreadId = Thread.currentThread().getId();
        this.initProgressBar();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, ProgressBarICS.android_R_styleable_ProgressBar, resourceId, n);
        this.mNoInvalidate = true;
        this.setMax(obtainStyledAttributes.getInt(0, this.mMax));
        this.setProgress(obtainStyledAttributes.getInt(1, this.mProgress));
        this.setSecondaryProgress(obtainStyledAttributes.getInt(2, this.mSecondaryProgress));
        final boolean boolean1 = obtainStyledAttributes.getBoolean(3, this.mIndeterminate);
        this.mOnlyIndeterminate = obtainStyledAttributes.getBoolean(4, this.mOnlyIndeterminate);
        final Drawable drawable = obtainStyledAttributes.getDrawable(5);
        if (drawable != null) {
            this.setIndeterminateDrawable(this.tileifyIndeterminate(drawable));
        }
        final Drawable drawable2 = obtainStyledAttributes.getDrawable(6);
        if (drawable2 != null) {
            this.setProgressDrawable(this.tileify(drawable2, false));
        }
        this.mDuration = obtainStyledAttributes.getInt(7, this.mDuration);
        this.mBehavior = obtainStyledAttributes.getInt(8, this.mBehavior);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(9, this.mMinWidth);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(10, this.mMaxWidth);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(11, this.mMinHeight);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(12, this.mMaxHeight);
        resourceId = obtainStyledAttributes.getResourceId(13, 17432587);
        if (resourceId > 0) {
            this.setInterpolator(context, resourceId);
        }
        obtainStyledAttributes.recycle();
        this.mNoInvalidate = false;
        if (this.mOnlyIndeterminate || boolean1) {
            indeterminate = true;
        }
        this.setIndeterminate(indeterminate);
    }
    
    private void doRefreshProgress(int level, final int n, final boolean b, final boolean b2) {
        while (true) {
        Label_0055_Outer:
            while (true) {
                while (true) {
                    float n2 = 0.0f;
                    final Drawable drawable;
                    Label_0092: {
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
                            break Label_0092;
                            drawable = mCurrentDrawable;
                            drawable.setLevel(level);
                            return;
                        }
                    }
                    level = (int)(10000.0f * n2);
                    if (drawable != null) {
                        continue;
                    }
                    break;
                }
                continue Label_0055_Outer;
            }
        }
    }
    
    private void initProgressBar() {
        this.mMax = 100;
        this.mProgress = 0;
        this.mSecondaryProgress = 0;
        this.mIndeterminate = false;
        this.mOnlyIndeterminate = false;
        this.mDuration = 4000;
        this.mBehavior = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
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
                            final RefreshProgressRunnable mRefreshProgressRunnable = this.mRefreshProgressRunnable;
                            this.mRefreshProgressRunnable = null;
                            mRefreshProgressRunnable.setup(n, n2, b);
                            this.post((Runnable)mRefreshProgressRunnable);
                        }
                        return;
                    }
                }
                final RefreshProgressRunnable mRefreshProgressRunnable = new RefreshProgressRunnable(n, n2, b);
                continue;
            }
        }
    }
    
    private Drawable tileify(final Drawable drawable, final boolean b) {
        Object o;
        if (drawable instanceof LayerDrawable) {
            final LayerDrawable layerDrawable = (LayerDrawable)drawable;
            final int numberOfLayers = layerDrawable.getNumberOfLayers();
            final Drawable[] array = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; ++i) {
                final int id = layerDrawable.getId(i);
                array[i] = this.tileify(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            final LayerDrawable layerDrawable2 = new LayerDrawable(array);
            int n = 0;
            while (true) {
                o = layerDrawable2;
                if (n >= numberOfLayers) {
                    break;
                }
                layerDrawable2.setId(n, layerDrawable.getId(n));
                ++n;
            }
        }
        else {
            if (!(drawable instanceof BitmapDrawable)) {
                return drawable;
            }
            final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = bitmap;
            }
            final ShapeDrawable shapeDrawable = new ShapeDrawable(this.getDrawableShape());
            shapeDrawable.getPaint().setShader((Shader)new BitmapShader(bitmap, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
            o = shapeDrawable;
            if (b) {
                o = new ClipDrawable((Drawable)shapeDrawable, 3, 1);
            }
        }
        return (Drawable)o;
    }
    
    private Drawable tileifyIndeterminate(final Drawable drawable) {
        Object o = drawable;
        if (drawable instanceof AnimationDrawable) {
            final AnimationDrawable animationDrawable = (AnimationDrawable)drawable;
            final int numberOfFrames = animationDrawable.getNumberOfFrames();
            o = new AnimationDrawable();
            ((AnimationDrawable)o).setOneShot(animationDrawable.isOneShot());
            for (int i = 0; i < numberOfFrames; ++i) {
                final Drawable tileify = this.tileify(animationDrawable.getFrame(i), true);
                tileify.setLevel(10000);
                ((AnimationDrawable)o).addFrame(tileify, animationDrawable.getDuration(i));
            }
            ((AnimationDrawable)o).setLevel(10000);
        }
        return (Drawable)o;
    }
    
    private void updateDrawableBounds(int n, int n2) {
        final int n3 = n - this.getPaddingRight() - this.getPaddingLeft();
        final int n4 = n2 - this.getPaddingBottom() - this.getPaddingTop();
        final boolean b = false;
        final boolean b2 = false;
        int n5 = n4;
        int n6 = n3;
        if (this.mIndeterminateDrawable != null) {
            n5 = n4;
            int n7 = b2 ? 1 : 0;
            n6 = n3;
            int n8 = b ? 1 : 0;
            if (this.mOnlyIndeterminate) {
                n5 = n4;
                n7 = (b2 ? 1 : 0);
                n6 = n3;
                n8 = (b ? 1 : 0);
                if (!(this.mIndeterminateDrawable instanceof AnimationDrawable)) {
                    final float n9 = this.mIndeterminateDrawable.getIntrinsicWidth() / this.mIndeterminateDrawable.getIntrinsicHeight();
                    final float n10 = n / n2;
                    n5 = n4;
                    n7 = (b2 ? 1 : 0);
                    n6 = n3;
                    n8 = (b ? 1 : 0);
                    if (n9 != n10) {
                        if (n10 > n9) {
                            n2 *= (int)n9;
                            n7 = (n - n2) / 2;
                            n6 = n7 + n2;
                            n8 = (b ? 1 : 0);
                            n5 = n4;
                        }
                        else {
                            n *= (int)(1.0f / n9);
                            n8 = (n2 - n) / 2;
                            n5 = n8 + n;
                            n7 = (b2 ? 1 : 0);
                            n6 = n3;
                        }
                    }
                }
            }
            this.mIndeterminateDrawable.setBounds(n7, n8, n6, n5);
        }
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.setBounds(0, 0, n6, n5);
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
    
    public final void incrementProgressBy(final int n) {
        synchronized (this) {
            this.setProgress(this.mProgress + n);
        }
    }
    
    public final void incrementSecondaryProgressBy(final int n) {
        synchronized (this) {
            this.setSecondaryProgress(this.mSecondaryProgress + n);
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
            this.invalidate(bounds.left + n, bounds.top + n2, bounds.right + n, bounds.bottom + n2);
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
                    mCurrentDrawable.setLevel((int)(10000.0f * alpha));
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
        synchronized (this) {
            final Drawable mCurrentDrawable = this.mCurrentDrawable;
            int max = 0;
            int max2 = 0;
            if (mCurrentDrawable != null) {
                max = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, mCurrentDrawable.getIntrinsicWidth()));
                max2 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, mCurrentDrawable.getIntrinsicHeight()));
            }
            this.updateDrawableState();
            this.setMeasuredDimension(resolveSize(max + (this.getPaddingLeft() + this.getPaddingRight()), n), resolveSize(max2 + (this.getPaddingTop() + this.getPaddingBottom()), n2));
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.setProgress(savedState.progress);
        this.setSecondaryProgress(savedState.secondaryProgress);
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.progress = this.mProgress;
        savedState.secondaryProgress = this.mSecondaryProgress;
        return (Parcelable)savedState;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        this.updateDrawableBounds(n, n2);
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
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
    
    public void setInterpolator(final Context context, final int n) {
        this.setInterpolator(AnimationUtils.loadInterpolator(context, n));
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
    
    void setProgress(int mMax, final boolean b) {
        synchronized (this) {
            if (!this.mIndeterminate) {
                int n;
                if ((n = mMax) < 0) {
                    n = 0;
                }
                if ((mMax = n) > this.mMax) {
                    mMax = this.mMax;
                }
                if (mMax != this.mProgress) {
                    this.refreshProgress(16908301, this.mProgress = mMax, b);
                }
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
    
    public void setSecondaryProgress(int mMax) {
        synchronized (this) {
            if (!this.mIndeterminate) {
                int n;
                if ((n = mMax) < 0) {
                    n = 0;
                }
                if ((mMax = n) > this.mMax) {
                    mMax = this.mMax;
                }
                if (mMax != this.mSecondaryProgress) {
                    this.refreshProgress(16908303, this.mSecondaryProgress = mMax, false);
                }
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
    
    private class RefreshProgressRunnable implements Runnable
    {
        private boolean mFromUser;
        private int mId;
        private int mProgress;
        
        RefreshProgressRunnable(final int mId, final int mProgress, final boolean mFromUser) {
            this.mId = mId;
            this.mProgress = mProgress;
            this.mFromUser = mFromUser;
        }
        
        @Override
        public void run() {
            ProgressBarICS.this.doRefreshProgress(this.mId, this.mProgress, this.mFromUser, true);
            ProgressBarICS.this.mRefreshProgressRunnable = this;
        }
        
        public void setup(final int mId, final int mProgress, final boolean mFromUser) {
            this.mId = mId;
            this.mProgress = mProgress;
            this.mFromUser = mFromUser;
        }
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int progress;
        int secondaryProgress;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        private SavedState(final Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.secondaryProgress = parcel.readInt();
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.secondaryProgress);
        }
    }
}
