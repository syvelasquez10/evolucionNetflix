// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import java.util.Arrays;
import android.graphics.Rect;
import android.graphics.Paint$Style;
import android.graphics.Path$FillType;
import android.graphics.Canvas;
import android.graphics.Path$Direction;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;

public class RoundedCornersDrawable extends ForwardingDrawable implements Rounded
{
    private int mBorderColor;
    private final Path mBorderPath;
    final float[] mBorderRadii;
    private float mBorderWidth;
    private boolean mIsCircle;
    private int mOverlayColor;
    private float mPadding;
    final Paint mPaint;
    private final Path mPath;
    private final float[] mRadii;
    private final RectF mTempRectangle;
    RoundedCornersDrawable$Type mType;
    
    public RoundedCornersDrawable(final Drawable drawable) {
        super(Preconditions.checkNotNull(drawable));
        this.mType = RoundedCornersDrawable$Type.OVERLAY_COLOR;
        this.mRadii = new float[8];
        this.mBorderRadii = new float[8];
        this.mPaint = new Paint(1);
        this.mIsCircle = false;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mOverlayColor = 0;
        this.mPadding = 0.0f;
        this.mPath = new Path();
        this.mBorderPath = new Path();
        this.mTempRectangle = new RectF();
    }
    
    private void updatePath() {
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRectangle.set(this.getBounds());
        this.mTempRectangle.inset(this.mPadding, this.mPadding);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path$Direction.CW);
        }
        else {
            this.mPath.addRoundRect(this.mTempRectangle, this.mRadii, Path$Direction.CW);
        }
        this.mTempRectangle.inset(-this.mPadding, -this.mPadding);
        this.mTempRectangle.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Path$Direction.CW);
        }
        else {
            for (int i = 0; i < this.mBorderRadii.length; ++i) {
                this.mBorderRadii[i] = this.mRadii[i] + this.mPadding - this.mBorderWidth / 2.0f;
            }
            this.mBorderPath.addRoundRect(this.mTempRectangle, this.mBorderRadii, Path$Direction.CW);
        }
        this.mTempRectangle.inset(-this.mBorderWidth / 2.0f, -this.mBorderWidth / 2.0f);
    }
    
    @Override
    public void draw(final Canvas canvas) {
        final Rect bounds = this.getBounds();
        switch (RoundedCornersDrawable$1.$SwitchMap$com$facebook$drawee$drawable$RoundedCornersDrawable$Type[this.mType.ordinal()]) {
            case 1: {
                final int save = canvas.save();
                this.mPath.setFillType(Path$FillType.EVEN_ODD);
                canvas.clipPath(this.mPath);
                super.draw(canvas);
                canvas.restoreToCount(save);
                break;
            }
            case 2: {
                super.draw(canvas);
                this.mPaint.setColor(this.mOverlayColor);
                this.mPaint.setStyle(Paint$Style.FILL);
                this.mPath.setFillType(Path$FillType.INVERSE_EVEN_ODD);
                canvas.drawPath(this.mPath, this.mPaint);
                if (!this.mIsCircle) {
                    break;
                }
                final float n = (bounds.width() - bounds.height() + this.mBorderWidth) / 2.0f;
                final float n2 = (bounds.height() - bounds.width() + this.mBorderWidth) / 2.0f;
                if (n > 0.0f) {
                    canvas.drawRect((float)bounds.left, (float)bounds.top, bounds.left + n, (float)bounds.bottom, this.mPaint);
                    canvas.drawRect(bounds.right - n, (float)bounds.top, (float)bounds.right, (float)bounds.bottom, this.mPaint);
                }
                if (n2 > 0.0f) {
                    canvas.drawRect((float)bounds.left, (float)bounds.top, (float)bounds.right, bounds.top + n2, this.mPaint);
                    canvas.drawRect((float)bounds.left, bounds.bottom - n2, (float)bounds.right, (float)bounds.bottom, this.mPaint);
                    break;
                }
                break;
            }
        }
        if (this.mBorderColor != 0) {
            this.mPaint.setStyle(Paint$Style.STROKE);
            this.mPaint.setColor(this.mBorderColor);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            this.mPath.setFillType(Path$FillType.EVEN_ODD);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.updatePath();
    }
    
    @Override
    public void setBorder(final int mBorderColor, final float mBorderWidth) {
        this.mBorderColor = mBorderColor;
        this.mBorderWidth = mBorderWidth;
        this.updatePath();
        this.invalidateSelf();
    }
    
    @Override
    public void setCircle(final boolean mIsCircle) {
        this.mIsCircle = mIsCircle;
        this.updatePath();
        this.invalidateSelf();
    }
    
    public void setOverlayColor(final int mOverlayColor) {
        this.mOverlayColor = mOverlayColor;
        this.invalidateSelf();
    }
    
    @Override
    public void setPadding(final float mPadding) {
        this.mPadding = mPadding;
        this.updatePath();
        this.invalidateSelf();
    }
    
    @Override
    public void setRadii(final float[] array) {
        if (array == null) {
            Arrays.fill(this.mRadii, 0.0f);
        }
        else {
            Preconditions.checkArgument(array.length == 8, (Object)"radii should have exactly 8 values");
            System.arraycopy(array, 0, this.mRadii, 0, 8);
        }
        this.updatePath();
        this.invalidateSelf();
    }
    
    @Override
    public void setRadius(final float n) {
        Arrays.fill(this.mRadii, n);
        this.updatePath();
        this.invalidateSelf();
    }
}
