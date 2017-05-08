// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.Path$FillType;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint$Cap;
import android.graphics.RectF;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.Paint;
import android.graphics.Path;

class MaterialProgressDrawable$Ring
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
    private int mCurrentColor;
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
    
    MaterialProgressDrawable$Ring(final Drawable$Callback mCallback) {
        this.mTempBounds = new RectF();
        this.mPaint = new Paint();
        this.mArrowPaint = new Paint();
        this.mStartTrim = 0.0f;
        this.mEndTrim = 0.0f;
        this.mRotation = 0.0f;
        this.mStrokeWidth = 5.0f;
        this.mStrokeInset = 2.5f;
        this.mCirclePaint = new Paint(1);
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
            this.mArrowPaint.setColor(this.mCurrentColor);
            canvas.rotate(n + n2 - 5.0f, rect.exactCenterX(), rect.exactCenterY());
            canvas.drawPath(this.mArrow, this.mArrowPaint);
        }
    }
    
    private int getNextColorIndex() {
        return (this.mColorIndex + 1) % this.mColors.length;
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
        this.mPaint.setColor(this.mCurrentColor);
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
    
    public int getNextColor() {
        return this.mColors[this.getNextColorIndex()];
    }
    
    public float getRotation() {
        return this.mRotation;
    }
    
    public float getStartTrim() {
        return this.mStartTrim;
    }
    
    public int getStartingColor() {
        return this.mColors[this.mColorIndex];
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
        this.setColorIndex(this.getNextColorIndex());
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
    
    public void setColor(final int mCurrentColor) {
        this.mCurrentColor = mCurrentColor;
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
    
    public void setColorIndex(final int mColorIndex) {
        this.mColorIndex = mColorIndex;
        this.mCurrentColor = this.mColors[this.mColorIndex];
    }
    
    public void setColors(final int[] mColors) {
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
