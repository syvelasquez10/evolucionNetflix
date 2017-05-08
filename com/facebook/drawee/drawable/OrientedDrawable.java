// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Rect;
import android.graphics.Canvas;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.graphics.Matrix;

public class OrientedDrawable extends ForwardingDrawable
{
    private int mRotationAngle;
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;
    
    public OrientedDrawable(final Drawable drawable, final int mRotationAngle) {
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        Preconditions.checkArgument(mRotationAngle % 90 == 0);
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = mRotationAngle;
    }
    
    @Override
    public void draw(final Canvas canvas) {
        if (this.mRotationAngle <= 0) {
            super.draw(canvas);
            return;
        }
        final int save = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }
    
    @Override
    public int getIntrinsicHeight() {
        if (this.mRotationAngle % 180 == 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }
    
    @Override
    public int getIntrinsicWidth() {
        if (this.mRotationAngle % 180 == 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }
    
    @Override
    public void getTransform(final Matrix matrix) {
        this.getParentTransform(matrix);
        if (!this.mRotationMatrix.isIdentity()) {
            matrix.preConcat(this.mRotationMatrix);
        }
    }
    
    @Override
    protected void onBoundsChange(final Rect bounds) {
        final Drawable current = this.getCurrent();
        if (this.mRotationAngle > 0) {
            this.mRotationMatrix.setRotate((float)this.mRotationAngle, (float)bounds.centerX(), (float)bounds.centerY());
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(bounds);
            this.mTempMatrix.mapRect(this.mTempRectF);
            current.setBounds((int)this.mTempRectF.left, (int)this.mTempRectF.top, (int)this.mTempRectF.right, (int)this.mTempRectF.bottom);
            return;
        }
        current.setBounds(bounds);
    }
}
