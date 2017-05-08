// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.infer.annotation.Assertions;
import android.graphics.Paint$FontMetricsInt;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

final class InlineImageSpanWithPipeline extends ReplacementSpan implements AttachDetachListener, BitmapUpdateListener
{
    private static final RectF TMP_RECT;
    private FlatViewGroup$InvalidateCallback mCallback;
    private boolean mFrozen;
    private float mHeight;
    private PipelineRequestHelper mRequestHelper;
    private float mWidth;
    
    static {
        TMP_RECT = new RectF();
    }
    
    InlineImageSpanWithPipeline() {
        this(null, Float.NaN, Float.NaN);
    }
    
    private InlineImageSpanWithPipeline(final PipelineRequestHelper mRequestHelper, final float mWidth, final float mHeight) {
        this.mRequestHelper = mRequestHelper;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public void draw(final Canvas canvas, final CharSequence charSequence, final int n, final int n2, final float n3, final int n4, final int n5, final int n6, final Paint paint) {
        if (this.mRequestHelper != null) {
            final Bitmap bitmap = this.mRequestHelper.getBitmap();
            if (bitmap != null) {
                final float n7 = n6 - paint.getFontMetricsInt().descent;
                InlineImageSpanWithPipeline.TMP_RECT.set(n3, n7 - this.mHeight, this.mWidth + n3, n7);
                canvas.drawBitmap(bitmap, (Rect)null, InlineImageSpanWithPipeline.TMP_RECT, paint);
            }
        }
    }
    
    void freeze() {
        this.mFrozen = true;
    }
    
    float getHeight() {
        return this.mHeight;
    }
    
    public int getSize(final Paint paint, final CharSequence charSequence, final int n, final int n2, final Paint$FontMetricsInt paint$FontMetricsInt) {
        if (paint$FontMetricsInt != null) {
            paint$FontMetricsInt.ascent = -Math.round(this.mHeight);
            paint$FontMetricsInt.descent = 0;
            paint$FontMetricsInt.top = paint$FontMetricsInt.ascent;
            paint$FontMetricsInt.bottom = 0;
        }
        return Math.round(this.mWidth);
    }
    
    float getWidth() {
        return this.mWidth;
    }
    
    boolean isFrozen() {
        return this.mFrozen;
    }
    
    InlineImageSpanWithPipeline mutableCopy() {
        return new InlineImageSpanWithPipeline(this.mRequestHelper, this.mWidth, this.mHeight);
    }
    
    public void onAttached(final FlatViewGroup$InvalidateCallback mCallback) {
        this.mCallback = mCallback;
        if (this.mRequestHelper != null) {
            this.mRequestHelper.attach(this);
        }
    }
    
    public void onBitmapReady(final Bitmap bitmap) {
        Assertions.assumeNotNull(this.mCallback).invalidate();
    }
    
    public void onDetached() {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.detach();
            if (this.mRequestHelper.isDetached()) {
                this.mCallback = null;
            }
        }
    }
    
    public void onImageLoadEvent(final int n) {
    }
    
    public void onSecondaryAttach(final Bitmap bitmap) {
        Assertions.assumeNotNull(this.mCallback).invalidate();
    }
    
    void setHeight(final float mHeight) {
        this.mHeight = mHeight;
    }
    
    void setImageRequest(final ImageRequest imageRequest) {
        if (imageRequest == null) {
            this.mRequestHelper = null;
            return;
        }
        this.mRequestHelper = new PipelineRequestHelper(imageRequest);
    }
    
    void setWidth(final float mWidth) {
        this.mWidth = mWidth;
    }
}
