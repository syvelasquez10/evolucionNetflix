// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import android.graphics.Canvas;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.Rect;
import android.graphics.Path$FillType;
import android.graphics.Path$Direction;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.Paint$Style;
import android.content.res.Resources;
import android.graphics.Shader;
import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.RectF;

public class RoundedLightBitmapDrawable extends LightBitmapDrawable implements Rounded, TransformAwareDrawable
{
    final RectF mBitmapBounds;
    private int mBorderColor;
    private final Paint mBorderPaint;
    private final Path mBorderPath;
    final float[] mBorderRadii;
    private float mBorderWidth;
    final Matrix mBoundsTransform;
    private final float[] mCornerRadii;
    final RectF mDrawableBounds;
    final Matrix mInverseParentTransform;
    private boolean mIsCircle;
    private boolean mIsPathDirty;
    private boolean mIsShaderTransformDirty;
    private WeakReference<Bitmap> mLastBitmap;
    private float mPadding;
    final Matrix mParentTransform;
    private final Path mPath;
    final Matrix mPrevBoundsTransform;
    final Matrix mPrevParentTransform;
    final RectF mPrevRootBounds;
    private boolean mRadiiNonZero;
    final RectF mRootBounds;
    private Shader mShader;
    final Matrix mTransform;
    private TransformCallback mTransformCallback;
    
    public RoundedLightBitmapDrawable(final Resources resources, final Bitmap bitmap, final Paint paint) {
        super(resources, bitmap, paint);
        this.mIsCircle = false;
        this.mRadiiNonZero = false;
        this.mCornerRadii = new float[8];
        this.mBorderRadii = new float[8];
        this.mRootBounds = new RectF();
        this.mPrevRootBounds = new RectF();
        this.mBitmapBounds = new RectF();
        this.mDrawableBounds = new RectF();
        this.mBoundsTransform = new Matrix();
        this.mPrevBoundsTransform = new Matrix();
        this.mParentTransform = new Matrix();
        this.mPrevParentTransform = new Matrix();
        this.mInverseParentTransform = new Matrix();
        this.mTransform = new Matrix();
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mPadding = 0.0f;
        this.mPath = new Path();
        this.mBorderPath = new Path();
        this.mIsPathDirty = true;
        this.mBorderPaint = new Paint(1);
        this.mIsShaderTransformDirty = true;
        this.getPaint().setFlags(1);
        this.mBorderPaint.setStyle(Paint$Style.STROKE);
    }
    
    private void updatePaint(final Bitmap bitmap) {
        final Paint paint = this.getPaint();
        if (this.mLastBitmap == null || this.mLastBitmap.get() != bitmap) {
            this.mLastBitmap = new WeakReference<Bitmap>(bitmap);
            this.mShader = (Shader)new BitmapShader(bitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
            this.mIsShaderTransformDirty = true;
        }
        if (this.mIsShaderTransformDirty) {
            this.mShader.setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
        if (paint.getShader() != this.mShader) {
            paint.setShader(this.mShader);
        }
    }
    
    private void updatePath() {
        if (this.mIsPathDirty) {
            this.mBorderPath.reset();
            this.mRootBounds.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
            if (this.mIsCircle) {
                this.mBorderPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path$Direction.CW);
            }
            else {
                for (int i = 0; i < this.mBorderRadii.length; ++i) {
                    this.mBorderRadii[i] = this.mCornerRadii[i] + this.mPadding - this.mBorderWidth / 2.0f;
                }
                this.mBorderPath.addRoundRect(this.mRootBounds, this.mBorderRadii, Path$Direction.CW);
            }
            this.mRootBounds.inset(-this.mBorderWidth / 2.0f, -this.mBorderWidth / 2.0f);
            this.mPath.reset();
            this.mRootBounds.inset(this.mPadding, this.mPadding);
            if (this.mIsCircle) {
                this.mPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path$Direction.CW);
            }
            else {
                this.mPath.addRoundRect(this.mRootBounds, this.mCornerRadii, Path$Direction.CW);
            }
            this.mRootBounds.inset(-this.mPadding, -this.mPadding);
            this.mPath.setFillType(Path$FillType.WINDING);
            this.mIsPathDirty = false;
        }
    }
    
    private void updateTransform(final Bitmap bitmap, final Rect rect) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(this.mParentTransform);
            this.mTransformCallback.getRootBounds(this.mRootBounds);
        }
        else {
            this.mParentTransform.reset();
            this.mRootBounds.set(rect);
        }
        this.mBitmapBounds.set(0.0f, 0.0f, (float)bitmap.getWidth(), (float)bitmap.getHeight());
        this.mDrawableBounds.set(rect);
        this.mBoundsTransform.setRectToRect(this.mBitmapBounds, this.mDrawableBounds, Matrix$ScaleToFit.FILL);
        if (!this.mParentTransform.equals((Object)this.mPrevParentTransform) || !this.mBoundsTransform.equals((Object)this.mPrevBoundsTransform)) {
            this.mIsShaderTransformDirty = true;
            this.mParentTransform.invert(this.mInverseParentTransform);
            this.mTransform.set(this.mParentTransform);
            this.mTransform.preConcat(this.mBoundsTransform);
            this.mPrevParentTransform.set(this.mParentTransform);
            this.mPrevBoundsTransform.set(this.mBoundsTransform);
        }
        if (!this.mRootBounds.equals((Object)this.mPrevRootBounds)) {
            this.mIsPathDirty = true;
            this.mPrevRootBounds.set(this.mRootBounds);
        }
    }
    
    @Override
    public void draw(final Canvas canvas) {
        final Rect bounds = this.getBounds();
        final Bitmap bitmap = this.getBitmap();
        final Paint paint = this.getPaint();
        if (bitmap == null) {
            return;
        }
        if (!this.shouldRound()) {
            paint.setShader((Shader)null);
            canvas.drawBitmap(bitmap, (Rect)null, bounds, paint);
            return;
        }
        this.updateTransform(bitmap, bounds);
        this.updatePaint(bitmap);
        this.updatePath();
        final int save = canvas.save();
        canvas.concat(this.mInverseParentTransform);
        canvas.drawPath(this.mPath, this.getPaint());
        if (this.mBorderWidth > 0.0f) {
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.getPaint().getAlpha()));
            canvas.drawPath(this.mBorderPath, this.mBorderPaint);
        }
        canvas.restoreToCount(save);
    }
    
    @Override
    public void setBorder(final int mBorderColor, final float mBorderWidth) {
        if (this.mBorderColor != mBorderColor || this.mBorderWidth != mBorderWidth) {
            this.mBorderColor = mBorderColor;
            this.mBorderWidth = mBorderWidth;
            this.mIsPathDirty = true;
            this.invalidateSelf();
        }
    }
    
    @Override
    public void setCircle(final boolean mIsCircle) {
        this.mIsCircle = mIsCircle;
        this.mIsPathDirty = true;
        this.invalidateSelf();
    }
    
    @Override
    public void setPadding(final float mPadding) {
        if (this.mPadding != mPadding) {
            this.mPadding = mPadding;
            this.mIsPathDirty = true;
            this.invalidateSelf();
        }
    }
    
    @Override
    public void setRadii(final float[] array) {
        if (array == null) {
            Arrays.fill(this.mCornerRadii, 0.0f);
            this.mRadiiNonZero = false;
        }
        else {
            Preconditions.checkArgument(array.length == 8, (Object)"radii should have exactly 8 values");
            System.arraycopy(array, 0, this.mCornerRadii, 0, 8);
            this.mRadiiNonZero = false;
            for (int i = 0; i < 8; ++i) {
                this.mRadiiNonZero |= (array[i] > 0.0f);
            }
        }
        this.mIsPathDirty = true;
        this.invalidateSelf();
    }
    
    @Override
    public void setRadius(final float n) {
        final boolean b = false;
        Preconditions.checkState(n >= 0.0f);
        Arrays.fill(this.mCornerRadii, n);
        boolean mRadiiNonZero = b;
        if (n != 0.0f) {
            mRadiiNonZero = true;
        }
        this.mRadiiNonZero = mRadiiNonZero;
        this.mIsPathDirty = true;
        this.invalidateSelf();
    }
    
    @Override
    public void setTransformCallback(final TransformCallback mTransformCallback) {
        this.mTransformCallback = mTransformCallback;
    }
    
    boolean shouldRound() {
        return this.mIsCircle || this.mRadiiNonZero || this.mBorderWidth > 0.0f;
    }
}
