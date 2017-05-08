// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.graphics.drawable;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class DrawerArrowDrawable extends Drawable
{
    private static final float ARROW_HEAD_ANGLE;
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;
    
    static {
        ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0);
    }
    
    private static float lerp(final float n, final float n2, final float n3) {
        return (n2 - n) * n3 + n;
    }
    
    public void draw(final Canvas canvas) {
        final Rect bounds = this.getBounds();
        boolean b = false;
        switch (this.mDirection) {
            default: {
                b = (DrawableCompat.getLayoutDirection(this) == 1);
                break;
            }
            case 0: {
                b = false;
                break;
            }
            case 1: {
                b = true;
                break;
            }
            case 3: {
                b = (DrawableCompat.getLayoutDirection(this) == 0);
                break;
            }
        }
        final float lerp = lerp(this.mBarLength, (float)Math.sqrt(this.mArrowHeadLength * this.mArrowHeadLength * 2.0f), this.mProgress);
        final float lerp2 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        final float n = Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        final float lerp3 = lerp(0.0f, DrawerArrowDrawable.ARROW_HEAD_ANGLE, this.mProgress);
        float n2;
        if (b) {
            n2 = 0.0f;
        }
        else {
            n2 = -180.0f;
        }
        float n3;
        if (b) {
            n3 = 180.0f;
        }
        else {
            n3 = 0.0f;
        }
        final float lerp4 = lerp(n2, n3, this.mProgress);
        final float n4 = Math.round(lerp * Math.cos(lerp3));
        final float n5 = Math.round(lerp * Math.sin(lerp3));
        this.mPath.rewind();
        final float lerp5 = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        final float n6 = -lerp2 / 2.0f;
        this.mPath.moveTo(n6 + n, 0.0f);
        this.mPath.rLineTo(lerp2 - n * 2.0f, 0.0f);
        this.mPath.moveTo(n6, lerp5);
        this.mPath.rLineTo(n4, n5);
        this.mPath.moveTo(n6, -lerp5);
        this.mPath.rLineTo(n4, -n5);
        this.mPath.close();
        canvas.save();
        final float strokeWidth = this.mPaint.getStrokeWidth();
        canvas.translate((float)bounds.centerX(), (float)((int)(bounds.height() - 3.0f * strokeWidth - this.mBarGap * 2.0f) / 4 * 2 + (strokeWidth * 1.5 + this.mBarGap)));
        if (this.mSpin) {
            int n7;
            if (b ^ this.mVerticalMirror) {
                n7 = -1;
            }
            else {
                n7 = 1;
            }
            canvas.rotate(n7 * lerp4);
        }
        else if (b) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }
    
    public int getIntrinsicHeight() {
        return this.mSize;
    }
    
    public int getIntrinsicWidth() {
        return this.mSize;
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void setAlpha(final int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            this.invalidateSelf();
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
    
    public void setProgress(final float mProgress) {
        if (this.mProgress != mProgress) {
            this.mProgress = mProgress;
            this.invalidateSelf();
        }
    }
    
    public void setVerticalMirror(final boolean mVerticalMirror) {
        if (this.mVerticalMirror != mVerticalMirror) {
            this.mVerticalMirror = mVerticalMirror;
            this.invalidateSelf();
        }
    }
}
