// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Path$Direction;
import android.graphics.PathEffect;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint;

abstract class AbstractDrawBorder extends AbstractDrawCommand
{
    private static final Paint PAINT;
    private static final RectF TMP_RECT;
    private int mBorderColor;
    private float mBorderRadius;
    private float mBorderWidth;
    private Path mPathForBorderRadius;
    private int mSetPropertiesFlag;
    
    static {
        PAINT = new Paint(1);
        TMP_RECT = new RectF();
        AbstractDrawBorder.PAINT.setStyle(Paint$Style.STROKE);
    }
    
    AbstractDrawBorder() {
        this.mBorderColor = -16777216;
    }
    
    protected final void drawBorders(final Canvas canvas) {
        if (this.mBorderWidth >= 0.5f && this.mBorderColor != 0) {
            AbstractDrawBorder.PAINT.setColor(this.mBorderColor);
            AbstractDrawBorder.PAINT.setStrokeWidth(this.mBorderWidth);
            AbstractDrawBorder.PAINT.setPathEffect(this.getPathEffectForBorderStyle());
            canvas.drawPath(this.getPathForBorderRadius(), AbstractDrawBorder.PAINT);
        }
    }
    
    public final int getBorderColor() {
        return this.mBorderColor;
    }
    
    public final float getBorderRadius() {
        return this.mBorderRadius;
    }
    
    public final float getBorderWidth() {
        return this.mBorderWidth;
    }
    
    protected PathEffect getPathEffectForBorderStyle() {
        return null;
    }
    
    protected final Path getPathForBorderRadius() {
        if (this.isFlagSet(1)) {
            if (this.mPathForBorderRadius == null) {
                this.mPathForBorderRadius = new Path();
            }
            this.updatePath(this.mPathForBorderRadius, this.mBorderWidth * 0.5f);
            this.resetFlag(1);
        }
        return this.mPathForBorderRadius;
    }
    
    protected final boolean isFlagSet(final int n) {
        return (this.mSetPropertiesFlag & n) == n;
    }
    
    protected final void resetFlag(final int n) {
        this.mSetPropertiesFlag &= ~n;
    }
    
    public final void setBorderColor(final int mBorderColor) {
        this.mBorderColor = mBorderColor;
    }
    
    public void setBorderRadius(final float mBorderRadius) {
        this.mBorderRadius = mBorderRadius;
        this.setFlag(1);
    }
    
    public final void setBorderWidth(final float mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
        this.setFlag(1);
    }
    
    protected final void setFlag(final int n) {
        this.mSetPropertiesFlag |= n;
    }
    
    protected final void updatePath(final Path path, final float n) {
        path.reset();
        AbstractDrawBorder.TMP_RECT.set(this.getLeft() + n, this.getTop() + n, this.getRight() - n, this.getBottom() - n);
        path.addRoundRect(AbstractDrawBorder.TMP_RECT, this.mBorderRadius, this.mBorderRadius, Path$Direction.CW);
    }
}
