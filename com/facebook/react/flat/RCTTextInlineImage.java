// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import android.content.Context;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.bridge.ReadableArray;
import android.text.SpannableStringBuilder;

class RCTTextInlineImage extends FlatTextShadowNode
{
    private InlineImageSpanWithPipeline mInlineImageSpan;
    
    RCTTextInlineImage() {
        this.mInlineImageSpan = new InlineImageSpanWithPipeline();
    }
    
    private InlineImageSpanWithPipeline getMutableSpan() {
        if (this.mInlineImageSpan.isFrozen()) {
            this.mInlineImageSpan = this.mInlineImageSpan.mutableCopy();
        }
        return this.mInlineImageSpan;
    }
    
    @Override
    protected void performApplySpans(final SpannableStringBuilder spannableStringBuilder, final int n, final int n2, final boolean b) {
        this.mInlineImageSpan.freeze();
        spannableStringBuilder.setSpan((Object)this.mInlineImageSpan, n, n2, 17);
    }
    
    @Override
    protected void performCollectText(final SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder.append((CharSequence)"I");
    }
    
    @ReactProp(name = "src")
    public void setSource(final ReadableArray readableArray) {
        final ImageRequest imageRequest = null;
        String string;
        if (readableArray == null || readableArray.size() == 0) {
            string = null;
        }
        else {
            string = readableArray.getMap(0).getString("uri");
        }
        ImageSource imageSource;
        if (string == null) {
            imageSource = null;
        }
        else {
            imageSource = new ImageSource((Context)this.getThemedContext(), string);
        }
        final InlineImageSpanWithPipeline mutableSpan = this.getMutableSpan();
        ImageRequest build;
        if (imageSource == null) {
            build = imageRequest;
        }
        else {
            build = ImageRequestBuilder.newBuilderWithSource(imageSource.getUri()).build();
        }
        mutableSpan.setImageRequest(build);
    }
    
    @Override
    public void setStyleHeight(final float n) {
        super.setStyleHeight(n);
        if (this.mInlineImageSpan.getHeight() != n) {
            this.getMutableSpan().setHeight(n);
            this.notifyChanged(true);
        }
    }
    
    @Override
    public void setStyleWidth(final float n) {
        super.setStyleWidth(n);
        if (this.mInlineImageSpan.getWidth() != n) {
            this.getMutableSpan().setWidth(n);
            this.notifyChanged(true);
        }
    }
}
