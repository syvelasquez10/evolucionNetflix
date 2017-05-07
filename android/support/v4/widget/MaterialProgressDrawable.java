// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.animation.Animation$AnimationListener;
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
    boolean mFinishing;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final MaterialProgressDrawable$Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;
    
    static {
        LINEAR_INTERPOLATOR = (Interpolator)new LinearInterpolator();
        END_CURVE_INTERPOLATOR = (Interpolator)new MaterialProgressDrawable$EndCurveInterpolator(null);
        START_CURVE_INTERPOLATOR = (Interpolator)new MaterialProgressDrawable$StartCurveInterpolator(null);
        EASE_INTERPOLATOR = (Interpolator)new AccelerateDecelerateInterpolator();
    }
    
    public MaterialProgressDrawable(final Context context, final View mParent) {
        this.COLORS = new int[] { -16777216 };
        this.mAnimators = new ArrayList<Animation>();
        this.mCallback = (Drawable$Callback)new MaterialProgressDrawable$3(this);
        this.mParent = mParent;
        this.mResources = context.getResources();
        (this.mRing = new MaterialProgressDrawable$Ring(this.mCallback)).setColors(this.COLORS);
        this.updateSizes(1);
        this.setupAnimators();
    }
    
    private void applyFinishTranslation(final float n, final MaterialProgressDrawable$Ring materialProgressDrawable$Ring) {
        final float n2 = (float)(Math.floor(materialProgressDrawable$Ring.getStartingRotation() / 0.8f) + 1.0);
        materialProgressDrawable$Ring.setStartTrim(materialProgressDrawable$Ring.getStartingStartTrim() + (materialProgressDrawable$Ring.getStartingEndTrim() - materialProgressDrawable$Ring.getStartingStartTrim()) * n);
        materialProgressDrawable$Ring.setRotation((n2 - materialProgressDrawable$Ring.getStartingRotation()) * n + materialProgressDrawable$Ring.getStartingRotation());
    }
    
    private float getRotation() {
        return this.mRotation;
    }
    
    private void setSizeParameters(final double n, final double n2, final double n3, final double n4, final float n5, final float n6) {
        final MaterialProgressDrawable$Ring mRing = this.mRing;
        final float density = this.mResources.getDisplayMetrics().density;
        this.mWidth = density * n;
        this.mHeight = density * n2;
        mRing.setStrokeWidth((float)n4 * density);
        mRing.setCenterRadius(density * n3);
        mRing.setColorIndex(0);
        mRing.setArrowDimensions(n5 * density, density * n6);
        mRing.setInsets((int)this.mWidth, (int)this.mHeight);
    }
    
    private void setupAnimators() {
        final MaterialProgressDrawable$Ring mRing = this.mRing;
        final MaterialProgressDrawable$1 mAnimation = new MaterialProgressDrawable$1(this, mRing);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(1);
        mAnimation.setInterpolator(MaterialProgressDrawable.LINEAR_INTERPOLATOR);
        mAnimation.setAnimationListener((Animation$AnimationListener)new MaterialProgressDrawable$2(this, mRing));
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
            this.mFinishing = true;
            this.mAnimation.setDuration(666L);
            this.mParent.startAnimation(this.mAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimation.setDuration(1333L);
        this.mParent.startAnimation(this.mAnimation);
    }
    
    public void stop() {
        this.mParent.clearAnimation();
        this.setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
    }
    
    public void updateSizes(@MaterialProgressDrawable$ProgressDrawableSize final int n) {
        if (n == 0) {
            this.setSizeParameters(56.0, 56.0, 12.5, 3.0, 12.0f, 6.0f);
            return;
        }
        this.setSizeParameters(40.0, 40.0, 8.75, 2.5, 10.0f, 5.0f);
    }
}
