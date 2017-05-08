// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.PathEffect;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

final class DrawBorder extends AbstractDrawBorder
{
    private static final Paint PAINT;
    private static final float[] TMP_FLOAT_ARRAY;
    private int mBackgroundColor;
    private int mBorderBottomColor;
    private float mBorderBottomWidth;
    private int mBorderLeftColor;
    private float mBorderLeftWidth;
    private int mBorderRightColor;
    private float mBorderRightWidth;
    private int mBorderStyle;
    private int mBorderTopColor;
    private float mBorderTopWidth;
    private DashPathEffect mPathEffectForBorderStyle;
    private Path mPathForBorder;
    
    static {
        PAINT = new Paint(1);
        TMP_FLOAT_ARRAY = new float[4];
    }
    
    DrawBorder() {
        this.mBorderStyle = 0;
    }
    
    private static DashPathEffect createDashPathEffect(final float n) {
        for (int i = 0; i < 4; ++i) {
            DrawBorder.TMP_FLOAT_ARRAY[i] = n;
        }
        return new DashPathEffect(DrawBorder.TMP_FLOAT_ARRAY, 0.0f);
    }
    
    private void drawRectangularBorders(final Canvas canvas) {
        final int borderColor = this.getBorderColor();
        final float borderWidth = this.getBorderWidth();
        final float top = this.getTop();
        final float resolveWidth = resolveWidth(this.mBorderTopWidth, borderWidth);
        final float n = top + resolveWidth;
        final int resolveBorderColor = this.resolveBorderColor(4, this.mBorderTopColor, borderColor);
        final float bottom = this.getBottom();
        final float resolveWidth2 = resolveWidth(this.mBorderBottomWidth, borderWidth);
        final float n2 = bottom - resolveWidth2;
        final int resolveBorderColor2 = this.resolveBorderColor(16, this.mBorderBottomColor, borderColor);
        final float left = this.getLeft();
        final float resolveWidth3 = resolveWidth(this.mBorderLeftWidth, borderWidth);
        final float n3 = left + resolveWidth3;
        final int resolveBorderColor3 = this.resolveBorderColor(2, this.mBorderLeftColor, borderColor);
        final float right = this.getRight();
        final float resolveWidth4 = resolveWidth(this.mBorderRightWidth, borderWidth);
        final float n4 = right - resolveWidth4;
        final int resolveBorderColor4 = this.resolveBorderColor(8, this.mBorderRightColor, borderColor);
        final boolean borderColorDifferentAtIntersectionPoints = this.isBorderColorDifferentAtIntersectionPoints();
        if (borderColorDifferentAtIntersectionPoints && this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        if (Color.alpha(resolveBorderColor) != 0 && resolveWidth != 0.0f) {
            DrawBorder.PAINT.setColor(resolveBorderColor);
            if (borderColorDifferentAtIntersectionPoints) {
                this.updatePathForTopBorder(top, n, left, n3, right, n4);
                canvas.drawPath(this.mPathForBorder, DrawBorder.PAINT);
            }
            else {
                canvas.drawRect(left, top, right, n, DrawBorder.PAINT);
            }
        }
        if (Color.alpha(resolveBorderColor2) != 0 && resolveWidth2 != 0.0f) {
            DrawBorder.PAINT.setColor(resolveBorderColor2);
            if (borderColorDifferentAtIntersectionPoints) {
                this.updatePathForBottomBorder(bottom, n2, left, n3, right, n4);
                canvas.drawPath(this.mPathForBorder, DrawBorder.PAINT);
            }
            else {
                canvas.drawRect(left, n2, right, bottom, DrawBorder.PAINT);
            }
        }
        if (Color.alpha(resolveBorderColor3) != 0 && resolveWidth3 != 0.0f) {
            DrawBorder.PAINT.setColor(resolveBorderColor3);
            if (borderColorDifferentAtIntersectionPoints) {
                this.updatePathForLeftBorder(top, n, bottom, n2, left, n3);
                canvas.drawPath(this.mPathForBorder, DrawBorder.PAINT);
            }
            else {
                canvas.drawRect(left, n, n3, n2, DrawBorder.PAINT);
            }
        }
        if (Color.alpha(resolveBorderColor4) != 0 && resolveWidth4 != 0.0f) {
            DrawBorder.PAINT.setColor(resolveBorderColor4);
            if (borderColorDifferentAtIntersectionPoints) {
                this.updatePathForRightBorder(top, n, bottom, n2, right, n4);
                canvas.drawPath(this.mPathForBorder, DrawBorder.PAINT);
            }
            else {
                canvas.drawRect(n4, n, right, n2, DrawBorder.PAINT);
            }
        }
        if (Color.alpha(this.mBackgroundColor) != 0) {
            DrawBorder.PAINT.setColor(this.mBackgroundColor);
            canvas.drawRect(n3, n, n4, n2, DrawBorder.PAINT);
        }
    }
    
    private void drawRoundedBorders(final Canvas canvas) {
        if (this.mBackgroundColor != 0) {
            DrawBorder.PAINT.setColor(this.mBackgroundColor);
            canvas.drawPath(this.getPathForBorderRadius(), DrawBorder.PAINT);
        }
        this.drawBorders(canvas);
    }
    
    private boolean isBorderColorDifferentAtIntersectionPoints() {
        return this.isFlagSet(4) || this.isFlagSet(16) || this.isFlagSet(2) || this.isFlagSet(8);
    }
    
    private int resolveBorderColor(final int n, final int n2, final int n3) {
        if (this.isFlagSet(n)) {
            return n2;
        }
        return n3;
    }
    
    private static float resolveWidth(final float n, final float n2) {
        if (n != 0.0f) {
            final float n3 = n;
            if (n == n) {
                return n3;
            }
        }
        return n2;
    }
    
    private void updatePathForBottomBorder(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(n3, n);
        this.mPathForBorder.lineTo(n5, n);
        this.mPathForBorder.lineTo(n6, n2);
        this.mPathForBorder.lineTo(n4, n2);
        this.mPathForBorder.lineTo(n3, n);
    }
    
    private void updatePathForLeftBorder(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(n5, n);
        this.mPathForBorder.lineTo(n6, n2);
        this.mPathForBorder.lineTo(n6, n4);
        this.mPathForBorder.lineTo(n5, n3);
        this.mPathForBorder.lineTo(n5, n);
    }
    
    private void updatePathForRightBorder(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(n5, n);
        this.mPathForBorder.lineTo(n5, n3);
        this.mPathForBorder.lineTo(n6, n4);
        this.mPathForBorder.lineTo(n6, n2);
        this.mPathForBorder.lineTo(n5, n);
    }
    
    private void updatePathForTopBorder(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(n3, n);
        this.mPathForBorder.lineTo(n4, n2);
        this.mPathForBorder.lineTo(n6, n2);
        this.mPathForBorder.lineTo(n5, n);
        this.mPathForBorder.lineTo(n3, n);
    }
    
    protected DashPathEffect getPathEffectForBorderStyle() {
        if (this.isFlagSet(32)) {
            switch (this.mBorderStyle) {
                default: {
                    this.mPathEffectForBorderStyle = null;
                    break;
                }
                case 1: {
                    this.mPathEffectForBorderStyle = createDashPathEffect(this.getBorderWidth());
                    break;
                }
                case 2: {
                    this.mPathEffectForBorderStyle = createDashPathEffect(this.getBorderWidth() * 3.0f);
                    break;
                }
            }
            this.resetFlag(32);
        }
        return this.mPathEffectForBorderStyle;
    }
    
    @Override
    protected void onDraw(final Canvas canvas) {
        if (this.getBorderRadius() >= 0.5f || this.getPathEffectForBorderStyle() != null) {
            this.drawRoundedBorders(canvas);
            return;
        }
        this.drawRectangularBorders(canvas);
    }
    
    public void resetBorderColor(final int n) {
        switch (n) {
            default: {}
            case 0: {
                this.resetFlag(2);
            }
            case 1: {
                this.resetFlag(4);
            }
            case 2: {
                this.resetFlag(8);
            }
            case 3: {
                this.resetFlag(16);
            }
            case 8: {
                this.setBorderColor(-16777216);
            }
        }
    }
    
    public void setBackgroundColor(final int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }
    
    public void setBorderColor(final int n, final int borderColor) {
        switch (n) {
            default: {}
            case 0: {
                this.mBorderLeftColor = borderColor;
                this.setFlag(2);
            }
            case 1: {
                this.mBorderTopColor = borderColor;
                this.setFlag(4);
            }
            case 2: {
                this.mBorderRightColor = borderColor;
                this.setFlag(8);
            }
            case 3: {
                this.mBorderBottomColor = borderColor;
                this.setFlag(16);
            }
            case 8: {
                this.setBorderColor(borderColor);
            }
        }
    }
    
    public void setBorderStyle(final String s) {
        if ("dotted".equals(s)) {
            this.mBorderStyle = 1;
        }
        else if ("dashed".equals(s)) {
            this.mBorderStyle = 2;
        }
        else {
            this.mBorderStyle = 0;
        }
        this.setFlag(32);
    }
    
    public void setBorderWidth(final int n, final float borderWidth) {
        switch (n) {
            default: {}
            case 0: {
                this.mBorderLeftWidth = borderWidth;
            }
            case 1: {
                this.mBorderTopWidth = borderWidth;
            }
            case 2: {
                this.mBorderRightWidth = borderWidth;
            }
            case 3: {
                this.mBorderBottomWidth = borderWidth;
            }
            case 8: {
                this.setBorderWidth(borderWidth);
            }
        }
    }
}
