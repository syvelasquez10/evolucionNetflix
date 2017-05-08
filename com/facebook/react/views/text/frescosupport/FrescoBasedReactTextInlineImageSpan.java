// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text.frescosupport;

import android.graphics.Paint$FontMetricsInt;
import android.graphics.drawable.Drawable$Callback;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import android.graphics.Paint;
import android.graphics.Canvas;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import android.content.res.Resources;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import android.graphics.drawable.Drawable;
import com.facebook.react.views.text.TextInlineImageSpan;

public class FrescoBasedReactTextInlineImageSpan extends TextInlineImageSpan
{
    private final Object mCallerContext;
    private Drawable mDrawable;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private final DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    private int mHeight;
    private TextView mTextView;
    private Uri mUri;
    private int mWidth;
    
    public FrescoBasedReactTextInlineImageSpan(final Resources resources, final int mHeight, final int mWidth, Uri empty, final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        this.mDraweeHolder = new DraweeHolder<GenericDraweeHierarchy>(GenericDraweeHierarchyBuilder.newInstance(resources).build());
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mCallerContext = mCallerContext;
        this.mHeight = mHeight;
        this.mWidth = mWidth;
        if (empty == null) {
            empty = Uri.EMPTY;
        }
        this.mUri = empty;
    }
    
    public void draw(final Canvas canvas, final CharSequence charSequence, final int n, final int n2, final float n3, final int n4, final int n5, final int n6, final Paint paint) {
        if (this.mDrawable == null) {
            this.mDraweeHolder.setController(this.mDraweeControllerBuilder.reset().setOldController(this.mDraweeHolder.getController()).setCallerContext(this.mCallerContext).setImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mUri).build()).build());
            (this.mDrawable = this.mDraweeHolder.getTopLevelDrawable()).setBounds(0, 0, this.mWidth, this.mHeight);
            this.mDrawable.setCallback((Drawable$Callback)this.mTextView);
        }
        canvas.save();
        canvas.translate(n3, (float)(n5 - this.mDrawable.getBounds().bottom));
        this.mDrawable.draw(canvas);
        canvas.restore();
    }
    
    @Override
    public Drawable getDrawable() {
        return this.mDrawable;
    }
    
    @Override
    public int getHeight() {
        return this.mHeight;
    }
    
    public int getSize(final Paint paint, final CharSequence charSequence, final int n, final int n2, final Paint$FontMetricsInt paint$FontMetricsInt) {
        if (paint$FontMetricsInt != null) {
            paint$FontMetricsInt.ascent = -this.mHeight;
            paint$FontMetricsInt.descent = 0;
            paint$FontMetricsInt.top = paint$FontMetricsInt.ascent;
            paint$FontMetricsInt.bottom = 0;
        }
        return this.mWidth;
    }
    
    @Override
    public void onAttachedToWindow() {
        this.mDraweeHolder.onAttach();
    }
    
    @Override
    public void onDetachedFromWindow() {
        this.mDraweeHolder.onDetach();
    }
    
    @Override
    public void onFinishTemporaryDetach() {
        this.mDraweeHolder.onAttach();
    }
    
    @Override
    public void onStartTemporaryDetach() {
        this.mDraweeHolder.onDetach();
    }
    
    @Override
    public void setTextView(final TextView mTextView) {
        this.mTextView = mTextView;
    }
}
