// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import java.util.Arrays;
import android.graphics.ColorFilter;
import java.util.Locale;
import android.os.Build$VERSION;
import android.graphics.Outline;
import android.graphics.Path$Direction;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.yoga.YogaConstants;
import android.graphics.Rect;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Paint;
import com.facebook.react.uimanager.Spacing;
import android.graphics.drawable.Drawable;

public class ReactViewBackgroundDrawable extends Drawable
{
    private int mAlpha;
    private Spacing mBorderAlpha;
    private float[] mBorderCornerRadii;
    private Spacing mBorderRGB;
    private float mBorderRadius;
    private ReactViewBackgroundDrawable$BorderStyle mBorderStyle;
    private Spacing mBorderWidth;
    private int mColor;
    private boolean mNeedUpdatePathForBorderRadius;
    private final Paint mPaint;
    private PathEffect mPathEffectForBorderStyle;
    private Path mPathForBorder;
    private Path mPathForBorderRadius;
    private Path mPathForBorderRadiusOutline;
    private RectF mTempRectForBorderRadius;
    private RectF mTempRectForBorderRadiusOutline;
    
    public ReactViewBackgroundDrawable() {
        this.mNeedUpdatePathForBorderRadius = false;
        this.mBorderRadius = Float.NaN;
        this.mPaint = new Paint(1);
        this.mColor = 0;
        this.mAlpha = 255;
    }
    
    private static int colorFromAlphaAndRGBComponents(final float n, final float n2) {
        return (0xFFFFFF & (int)n2) | (0xFF000000 & (int)n << 24);
    }
    
    private void drawRectangularBackgroundWithBorders(final Canvas canvas) {
        final int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (multiplyColorAlpha >>> 24 != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            this.mPaint.setStyle(Paint$Style.FILL);
            canvas.drawRect(this.getBounds(), this.mPaint);
        }
        if (this.getBorderWidth(0) > 0 || this.getBorderWidth(1) > 0 || this.getBorderWidth(2) > 0 || this.getBorderWidth(3) > 0) {
            final Rect bounds = this.getBounds();
            final int borderWidth = this.getBorderWidth(0);
            final int borderWidth2 = this.getBorderWidth(1);
            final int borderWidth3 = this.getBorderWidth(2);
            final int borderWidth4 = this.getBorderWidth(3);
            final int borderColor = this.getBorderColor(0);
            final int borderColor2 = this.getBorderColor(1);
            final int borderColor3 = this.getBorderColor(2);
            final int borderColor4 = this.getBorderColor(3);
            final int top = bounds.top;
            final int left = bounds.left;
            final int width = bounds.width();
            final int height = bounds.height();
            this.mPaint.setAntiAlias(false);
            if (this.mPathForBorder == null) {
                this.mPathForBorder = new Path();
            }
            if (borderWidth > 0 && borderColor != 0) {
                this.mPaint.setColor(borderColor);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float)left, (float)top);
                this.mPathForBorder.lineTo((float)(left + borderWidth), (float)(top + borderWidth2));
                this.mPathForBorder.lineTo((float)(left + borderWidth), (float)(top + height - borderWidth4));
                this.mPathForBorder.lineTo((float)left, (float)(top + height));
                this.mPathForBorder.lineTo((float)left, (float)top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderWidth2 > 0 && borderColor2 != 0) {
                this.mPaint.setColor(borderColor2);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float)left, (float)top);
                this.mPathForBorder.lineTo((float)(left + borderWidth), (float)(top + borderWidth2));
                this.mPathForBorder.lineTo((float)(left + width - borderWidth3), (float)(top + borderWidth2));
                this.mPathForBorder.lineTo((float)(left + width), (float)top);
                this.mPathForBorder.lineTo((float)left, (float)top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderWidth3 > 0 && borderColor3 != 0) {
                this.mPaint.setColor(borderColor3);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float)(left + width), (float)top);
                this.mPathForBorder.lineTo((float)(left + width), (float)(top + height));
                this.mPathForBorder.lineTo((float)(left + width - borderWidth3), (float)(top + height - borderWidth4));
                this.mPathForBorder.lineTo((float)(left + width - borderWidth3), (float)(borderWidth2 + top));
                this.mPathForBorder.lineTo((float)(left + width), (float)top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderWidth4 > 0 && borderColor4 != 0) {
                this.mPaint.setColor(borderColor4);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float)left, (float)(top + height));
                this.mPathForBorder.lineTo((float)(left + width), (float)(top + height));
                this.mPathForBorder.lineTo((float)(left + width - borderWidth3), (float)(top + height - borderWidth4));
                this.mPathForBorder.lineTo((float)(borderWidth + left), (float)(top + height - borderWidth4));
                this.mPathForBorder.lineTo((float)left, (float)(height + top));
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            this.mPaint.setAntiAlias(true);
        }
    }
    
    private void drawRoundedBackgroundWithBorders(final Canvas canvas) {
        this.updatePath();
        final int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (multiplyColorAlpha >>> 24 != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            this.mPaint.setStyle(Paint$Style.FILL);
            canvas.drawPath(this.mPathForBorderRadius, this.mPaint);
        }
        final float fullBorderWidth = this.getFullBorderWidth();
        if (fullBorderWidth > 0.0f) {
            this.mPaint.setColor(ColorUtil.multiplyColorAlpha(this.getFullBorderColor(), this.mAlpha));
            this.mPaint.setStyle(Paint$Style.STROKE);
            this.mPaint.setStrokeWidth(fullBorderWidth);
            canvas.drawPath(this.mPathForBorderRadius, this.mPaint);
        }
    }
    
    private int getBorderColor(final int n) {
        float value;
        if (this.mBorderRGB != null) {
            value = this.mBorderRGB.get(n);
        }
        else {
            value = 0.0f;
        }
        float value2;
        if (this.mBorderAlpha != null) {
            value2 = this.mBorderAlpha.get(n);
        }
        else {
            value2 = 255.0f;
        }
        return colorFromAlphaAndRGBComponents(value2, value);
    }
    
    private int getBorderWidth(final int n) {
        if (this.mBorderWidth != null) {
            return Math.round(this.mBorderWidth.get(n));
        }
        return 0;
    }
    
    private int getFullBorderColor() {
        float raw;
        if (this.mBorderRGB != null && !YogaConstants.isUndefined(this.mBorderRGB.getRaw(8))) {
            raw = this.mBorderRGB.getRaw(8);
        }
        else {
            raw = 0.0f;
        }
        float raw2;
        if (this.mBorderAlpha != null && !YogaConstants.isUndefined(this.mBorderAlpha.getRaw(8))) {
            raw2 = this.mBorderAlpha.getRaw(8);
        }
        else {
            raw2 = 255.0f;
        }
        return colorFromAlphaAndRGBComponents(raw2, raw);
    }
    
    private float getFullBorderWidth() {
        if (this.mBorderWidth != null && !YogaConstants.isUndefined(this.mBorderWidth.getRaw(8))) {
            return this.mBorderWidth.getRaw(8);
        }
        return 0.0f;
    }
    
    private void setBorderAlpha(final int n, final float n2) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(n), n2)) {
            this.mBorderAlpha.set(n, n2);
            this.invalidateSelf();
        }
    }
    
    private void setBorderRGB(final int n, final float n2) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderRGB.getRaw(n), n2)) {
            this.mBorderRGB.set(n, n2);
            this.invalidateSelf();
        }
    }
    
    private void updatePath() {
        final float n = 0.0f;
        if (!this.mNeedUpdatePathForBorderRadius) {
            return;
        }
        this.mNeedUpdatePathForBorderRadius = false;
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
            this.mPathForBorderRadiusOutline = new Path();
            this.mTempRectForBorderRadiusOutline = new RectF();
        }
        this.mPathForBorderRadius.reset();
        this.mPathForBorderRadiusOutline.reset();
        this.mTempRectForBorderRadius.set(this.getBounds());
        this.mTempRectForBorderRadiusOutline.set(this.getBounds());
        final float fullBorderWidth = this.getFullBorderWidth();
        if (fullBorderWidth > 0.0f) {
            this.mTempRectForBorderRadius.inset(0.5f * fullBorderWidth, fullBorderWidth * 0.5f);
        }
        float mBorderRadius;
        if (!YogaConstants.isUndefined(this.mBorderRadius)) {
            mBorderRadius = this.mBorderRadius;
        }
        else {
            mBorderRadius = 0.0f;
        }
        float n2;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[0])) {
            n2 = this.mBorderCornerRadii[0];
        }
        else {
            n2 = mBorderRadius;
        }
        float n3;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[1])) {
            n3 = this.mBorderCornerRadii[1];
        }
        else {
            n3 = mBorderRadius;
        }
        float n4;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[2])) {
            n4 = this.mBorderCornerRadii[2];
        }
        else {
            n4 = mBorderRadius;
        }
        float n5 = mBorderRadius;
        if (this.mBorderCornerRadii != null) {
            n5 = mBorderRadius;
            if (!YogaConstants.isUndefined(this.mBorderCornerRadii[3])) {
                n5 = this.mBorderCornerRadii[3];
            }
        }
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, new float[] { n2, n2, n3, n3, n4, n4, n5, n5 }, Path$Direction.CW);
        float n6 = n;
        if (this.mBorderWidth != null) {
            n6 = this.mBorderWidth.get(8) / 2.0f;
        }
        this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[] { n2 + n6, n2 + n6, n3 + n6, n3 + n6, n4 + n6, n4 + n6, n5 + n6, n5 + n6 }, Path$Direction.CW);
    }
    
    private void updatePathEffect() {
        PathEffect pathEffect;
        if (this.mBorderStyle != null) {
            pathEffect = this.mBorderStyle.getPathEffect(this.getFullBorderWidth());
        }
        else {
            pathEffect = null;
        }
        this.mPathEffectForBorderStyle = pathEffect;
        this.mPaint.setPathEffect(this.mPathEffectForBorderStyle);
    }
    
    public void draw(final Canvas canvas) {
        this.updatePathEffect();
        boolean b;
        if (this.mBorderCornerRadii != null || (!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f)) {
            b = true;
        }
        else {
            b = false;
        }
        if ((this.mBorderStyle == null || this.mBorderStyle == ReactViewBackgroundDrawable$BorderStyle.SOLID) && !b) {
            this.drawRectangularBackgroundWithBorders(canvas);
            return;
        }
        this.drawRoundedBackgroundWithBorders(canvas);
    }
    
    public int getAlpha() {
        return this.mAlpha;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }
    
    public void getOutline(final Outline outline) {
        if (Build$VERSION.SDK_INT < 21) {
            super.getOutline(outline);
            return;
        }
        if ((!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) || this.mBorderCornerRadii != null) {
            this.updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
            return;
        }
        outline.setRect(this.getBounds());
    }
    
    protected void onBoundsChange(final Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }
    
    public void setAlpha(final int mAlpha) {
        if (mAlpha != this.mAlpha) {
            this.mAlpha = mAlpha;
            this.invalidateSelf();
        }
    }
    
    public void setBorderColor(final int n, final float n2, final float n3) {
        this.setBorderRGB(n, n2);
        this.setBorderAlpha(n, n3);
    }
    
    public void setBorderStyle(final String s) {
        ReactViewBackgroundDrawable$BorderStyle value;
        if (s == null) {
            value = null;
        }
        else {
            value = ReactViewBackgroundDrawable$BorderStyle.valueOf(s.toUpperCase(Locale.US));
        }
        if (this.mBorderStyle != value) {
            this.mBorderStyle = value;
            this.mNeedUpdatePathForBorderRadius = true;
            this.invalidateSelf();
        }
    }
    
    public void setBorderWidth(final int n, final float n2) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (!FloatUtil.floatsEqual(this.mBorderWidth.getRaw(n), n2)) {
            this.mBorderWidth.set(n, n2);
            if (n == 8) {
                this.mNeedUpdatePathForBorderRadius = true;
            }
            this.invalidateSelf();
        }
    }
    
    public void setColor(final int mColor) {
        this.mColor = mColor;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
    
    public void setRadius(final float mBorderRadius) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, mBorderRadius)) {
            this.mBorderRadius = mBorderRadius;
            this.mNeedUpdatePathForBorderRadius = true;
            this.invalidateSelf();
        }
    }
    
    public void setRadius(final float n, final int n2) {
        if (this.mBorderCornerRadii == null) {
            Arrays.fill(this.mBorderCornerRadii = new float[4], Float.NaN);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[n2], n)) {
            this.mBorderCornerRadii[n2] = n;
            this.mNeedUpdatePathForBorderRadius = true;
            this.invalidateSelf();
        }
    }
}
