// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.graphics.Path$FillType;
import android.graphics.Paint$Style;
import android.graphics.Paint$Cap;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.IntDef;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Transformation;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.content.res.Resources;
import android.view.View;
import android.graphics.drawable.Drawable$Callback;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

class MaterialProgressDrawable extends Drawable implements Animatable
{
    private static final int ANIMATION_DURATION = 1333;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 5.0f;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    static final int DEFAULT = 1;
    private static final Interpolator EASE_INTERPOLATOR;
    private static final Interpolator END_CURVE_INTERPOLATOR;
    static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR;
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float NUM_POINTS = 5.0f;
    private static final Interpolator START_CURVE_INTERPOLATOR;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private final int[] COLORS;
    private Animation mAnimation;
    private final ArrayList<Animation> mAnimators;
    private final Drawable$Callback mCallback;
    private Animation mFinishAnimation;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;
    
    static {
        LINEAR_INTERPOLATOR = (Interpolator)new LinearInterpolator();
        END_CURVE_INTERPOLATOR = (Interpolator)new EndCurveInterpolator();
        START_CURVE_INTERPOLATOR = (Interpolator)new StartCurveInterpolator();
        EASE_INTERPOLATOR = (Interpolator)new AccelerateDecelerateInterpolator();
    }
    
    public MaterialProgressDrawable(final Context context, final View mParent) {
        this.COLORS = new int[] { -16777216 };
        this.mAnimators = new ArrayList<Animation>();
        this.mCallback = (Drawable$Callback)new Drawable$Callback() {
            public void invalidateDrawable(final Drawable drawable) {
                MaterialProgressDrawable.this.invalidateSelf();
            }
            
            public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
                MaterialProgressDrawable.this.scheduleSelf(runnable, n);
            }
            
            public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
                MaterialProgressDrawable.this.unscheduleSelf(runnable);
            }
        };
        this.mParent = mParent;
        this.mResources = context.getResources();
        (this.mRing = new Ring(this.mCallback)).setColors(this.COLORS);
        this.updateSizes(1);
        this.setupAnimators();
    }
    
    private float getRotation() {
        return this.mRotation;
    }
    
    private void setSizeParameters(final double n, final double n2, final double n3, final double n4, final float n5, final float n6) {
        final Ring mRing = this.mRing;
        final float density = this.mResources.getDisplayMetrics().density;
        this.mWidth = density * n;
        this.mHeight = density * n2;
        mRing.setStrokeWidth((float)n4 * density);
        mRing.setCenterRadius(density * n3);
        mRing.setColorIndex(0);
        mRing.setArrowDimensions(n5 * density, n6 * density);
        mRing.setInsets((int)this.mWidth, (int)this.mHeight);
    }
    
    private void setupAnimators() {
        final Ring mRing = this.mRing;
        final Animation mFinishAnimation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                final float n2 = (float)(Math.floor(mRing.getStartingRotation() / 0.8f) + 1.0);
                mRing.setStartTrim(mRing.getStartingStartTrim() + (mRing.getStartingEndTrim() - mRing.getStartingStartTrim()) * n);
                mRing.setRotation(mRing.getStartingRotation() + (n2 - mRing.getStartingRotation()) * n);
                mRing.setArrowScale(1.0f - n);
            }
        };
        mFinishAnimation.setInterpolator(MaterialProgressDrawable.EASE_INTERPOLATOR);
        mFinishAnimation.setDuration(666L);
        mFinishAnimation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                mRing.goToNextColor();
                mRing.storeOriginals();
                mRing.setShowArrow(false);
                MaterialProgressDrawable.this.mParent.startAnimation(MaterialProgressDrawable.this.mAnimation);
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        final Animation mAnimation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                final float n2 = (float)Math.toRadians(mRing.getStrokeWidth() / (6.283185307179586 * mRing.getCenterRadius()));
                final float startingEndTrim = mRing.getStartingEndTrim();
                final float startingStartTrim = mRing.getStartingStartTrim();
                final float startingRotation = mRing.getStartingRotation();
                mRing.setEndTrim(startingEndTrim + MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(n) * (0.8f - n2));
                mRing.setStartTrim(startingStartTrim + 0.8f * MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(n));
                mRing.setRotation(startingRotation + 0.25f * n);
                MaterialProgressDrawable.this.setRotation(144.0f * n + 720.0f * (MaterialProgressDrawable.this.mRotationCount / 5.0f));
            }
        };
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(1);
        mAnimation.setInterpolator(MaterialProgressDrawable.LINEAR_INTERPOLATOR);
        mAnimation.setDuration(1333L);
        mAnimation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
            }
            
            public void onAnimationRepeat(final Animation animation) {
                mRing.storeOriginals();
                mRing.goToNextColor();
                mRing.setStartTrim(mRing.getEndTrim());
                MaterialProgressDrawable.this.mRotationCount = (MaterialProgressDrawable.this.mRotationCount + 1.0f) % 5.0f;
            }
            
            public void onAnimationStart(final Animation animation) {
                MaterialProgressDrawable.this.mRotationCount = 0.0f;
            }
        });
        this.mFinishAnimation = mFinishAnimation;
        this.mAnimation = mAnimation;
    }
    
    public void draw(final Canvas canvas) {
        final Rect bounds = this.getBounds();
        final int save = canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(canvas, bounds);
        canvas.restoreToCount(save);
    }
    
    public int getAlpha() {
        return this.mRing.getAlpha();
    }
    
    public int getIntrinsicHeight() {
        return (int)this.mHeight;
    }
    
    public int getIntrinsicWidth() {
        return (int)this.mWidth;
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public boolean isRunning() {
        final ArrayList<Animation> mAnimators = this.mAnimators;
        for (int size = mAnimators.size(), i = 0; i < size; ++i) {
            final Animation animation = mAnimators.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }
    
    public void setAlpha(final int alpha) {
        this.mRing.setAlpha(alpha);
    }
    
    public void setArrowScale(final float arrowScale) {
        this.mRing.setArrowScale(arrowScale);
    }
    
    public void setBackgroundColor(final int backgroundColor) {
        this.mRing.setBackgroundColor(backgroundColor);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
    }
    
    public void setColorSchemeColors(final int... colors) {
        this.mRing.setColors(colors);
        this.mRing.setColorIndex(0);
    }
    
    public void setProgressRotation(final float rotation) {
        this.mRing.setRotation(rotation);
    }
    
    void setRotation(final float mRotation) {
        this.mRotation = mRotation;
        this.invalidateSelf();
    }
    
    public void setStartEndTrim(final float startTrim, final float endTrim) {
        this.mRing.setStartTrim(startTrim);
        this.mRing.setEndTrim(endTrim);
    }
    
    public void showArrow(final boolean showArrow) {
        this.mRing.setShowArrow(showArrow);
    }
    
    public void start() {
        this.mAnimation.reset();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mParent.startAnimation(this.mFinishAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mParent.startAnimation(this.mAnimation);
    }
    
    public void stop() {
        this.mParent.clearAnimation();
        this.setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
    }
    
    public void updateSizes(@ProgressDrawableSize final int n) {
        if (n == 0) {
            this.setSizeParameters(56.0, 56.0, 12.5, 3.0, 12.0f, 6.0f);
            return;
        }
        this.setSizeParameters(40.0, 40.0, 8.75, 2.5, 10.0f, 5.0f);
    }
    
    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator
    {
        public float getInterpolation(final float n) {
            return super.getInterpolation(Math.max(0.0f, (n - 0.5f) * 2.0f));
        }
    }
    
    @Retention(RetentionPolicy.CLASS)
    @IntDef({ 0L, 1L })
    public @interface ProgressDrawableSize {
    }
    
    private static class Ring
    {
        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint;
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final Drawable$Callback mCallback;
        private final Paint mCirclePaint;
        private int mColorIndex;
        private int[] mColors;
        private float mEndTrim;
        private final Paint mPaint;
        private double mRingCenterRadius;
        private float mRotation;
        private boolean mShowArrow;
        private float mStartTrim;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset;
        private float mStrokeWidth;
        private final RectF mTempBounds;
        
        public Ring(final Drawable$Callback mCallback) {
            this.mTempBounds = new RectF();
            this.mPaint = new Paint();
            this.mArrowPaint = new Paint();
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
            this.mStrokeWidth = 5.0f;
            this.mStrokeInset = 2.5f;
            this.mCirclePaint = new Paint();
            this.mCallback = mCallback;
            this.mPaint.setStrokeCap(Paint$Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint$Style.STROKE);
            this.mArrowPaint.setStyle(Paint$Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
        }
        
        private void drawTriangle(final Canvas canvas, final float n, final float n2, final Rect rect) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    (this.mArrow = new Path()).setFillType(Path$FillType.EVEN_ODD);
                }
                else {
                    this.mArrow.reset();
                }
                final float n3 = (int)this.mStrokeInset / 2;
                final float mArrowScale = this.mArrowScale;
                final float n4 = (float)(this.mRingCenterRadius * Math.cos(0.0) + rect.exactCenterX());
                final float n5 = (float)(this.mRingCenterRadius * Math.sin(0.0) + rect.exactCenterY());
                this.mArrow.moveTo(0.0f, 0.0f);
                this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale, 0.0f);
                this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale / 2.0f, this.mArrowHeight * this.mArrowScale);
                this.mArrow.offset(n4 - n3 * mArrowScale, n5);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mColors[this.mColorIndex]);
                canvas.rotate(n + n2 - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.mArrow, this.mArrowPaint);
            }
        }
        
        private void invalidateSelf() {
            this.mCallback.invalidateDrawable((Drawable)null);
        }
        
        public void draw(final Canvas canvas, final Rect rect) {
            final RectF mTempBounds = this.mTempBounds;
            mTempBounds.set(rect);
            mTempBounds.inset(this.mStrokeInset, this.mStrokeInset);
            final float n = (this.mStartTrim + this.mRotation) * 360.0f;
            final float n2 = (this.mEndTrim + this.mRotation) * 360.0f - n;
            this.mPaint.setColor(this.mColors[this.mColorIndex]);
            canvas.drawArc(mTempBounds, n, n2, false, this.mPaint);
            this.drawTriangle(canvas, n, n2, rect);
            if (this.mAlpha < 255) {
                this.mCirclePaint.setColor(this.mBackgroundColor);
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float)(rect.width() / 2), this.mCirclePaint);
            }
        }
        
        public int getAlpha() {
            return this.mAlpha;
        }
        
        public double getCenterRadius() {
            return this.mRingCenterRadius;
        }
        
        public float getEndTrim() {
            return this.mEndTrim;
        }
        
        public float getInsets() {
            return this.mStrokeInset;
        }
        
        public float getRotation() {
            return this.mRotation;
        }
        
        public float getStartTrim() {
            return this.mStartTrim;
        }
        
        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }
        
        public float getStartingRotation() {
            return this.mStartingRotation;
        }
        
        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }
        
        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }
        
        public void goToNextColor() {
            this.mColorIndex = (this.mColorIndex + 1) % this.mColors.length;
        }
        
        public void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.setStartTrim(this.mStartingRotation = 0.0f);
            this.setEndTrim(0.0f);
            this.setRotation(0.0f);
        }
        
        public void setAlpha(final int mAlpha) {
            this.mAlpha = mAlpha;
        }
        
        public void setArrowDimensions(final float n, final float n2) {
            this.mArrowWidth = (int)n;
            this.mArrowHeight = (int)n2;
        }
        
        public void setArrowScale(final float mArrowScale) {
            if (mArrowScale != this.mArrowScale) {
                this.mArrowScale = mArrowScale;
                this.invalidateSelf();
            }
        }
        
        public void setBackgroundColor(final int mBackgroundColor) {
            this.mBackgroundColor = mBackgroundColor;
        }
        
        public void setCenterRadius(final double mRingCenterRadius) {
            this.mRingCenterRadius = mRingCenterRadius;
        }
        
        public void setColorFilter(final ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            this.invalidateSelf();
        }
        
        public void setColorIndex(final int mColorIndex) {
            this.mColorIndex = mColorIndex;
        }
        
        public void setColors(@NonNull final int[] mColors) {
            this.mColors = mColors;
            this.setColorIndex(0);
        }
        
        public void setEndTrim(final float mEndTrim) {
            this.mEndTrim = mEndTrim;
            this.invalidateSelf();
        }
        
        public void setInsets(final int n, final int n2) {
            final float n3 = Math.min(n, n2);
            float mStrokeInset;
            if (this.mRingCenterRadius <= 0.0 || n3 < 0.0f) {
                mStrokeInset = (float)Math.ceil(this.mStrokeWidth / 2.0f);
            }
            else {
                mStrokeInset = (float)(n3 / 2.0f - this.mRingCenterRadius);
            }
            this.mStrokeInset = mStrokeInset;
        }
        
        public void setRotation(final float mRotation) {
            this.mRotation = mRotation;
            this.invalidateSelf();
        }
        
        public void setShowArrow(final boolean mShowArrow) {
            if (this.mShowArrow != mShowArrow) {
                this.mShowArrow = mShowArrow;
                this.invalidateSelf();
            }
        }
        
        public void setStartTrim(final float mStartTrim) {
            this.mStartTrim = mStartTrim;
            this.invalidateSelf();
        }
        
        public void setStrokeWidth(final float n) {
            this.mStrokeWidth = n;
            this.mPaint.setStrokeWidth(n);
            this.invalidateSelf();
        }
        
        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }
    
    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator
    {
        public float getInterpolation(final float n) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * n));
        }
    }
}
