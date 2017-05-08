// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.request;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.UriUtil;
import android.net.Uri;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ImageDecodeOptions;

public class ImageRequestBuilder
{
    private boolean mAutoRotateEnabled;
    private boolean mDiskCacheEnabled;
    private ImageDecodeOptions mImageDecodeOptions;
    private ImageRequest$ImageType mImageType;
    private boolean mLocalThumbnailPreviewsEnabled;
    private ImageRequest$RequestLevel mLowestPermittedRequestLevel;
    private Postprocessor mPostprocessor;
    private boolean mProgressiveRenderingEnabled;
    private Priority mRequestPriority;
    private ResizeOptions mResizeOptions;
    private Uri mSourceUri;
    
    private ImageRequestBuilder() {
        this.mSourceUri = null;
        this.mLowestPermittedRequestLevel = ImageRequest$RequestLevel.FULL_FETCH;
        this.mAutoRotateEnabled = false;
        this.mResizeOptions = null;
        this.mImageDecodeOptions = ImageDecodeOptions.defaults();
        this.mImageType = ImageRequest$ImageType.DEFAULT;
        this.mProgressiveRenderingEnabled = false;
        this.mLocalThumbnailPreviewsEnabled = false;
        this.mRequestPriority = Priority.HIGH;
        this.mPostprocessor = null;
        this.mDiskCacheEnabled = true;
    }
    
    public static ImageRequestBuilder newBuilderWithSource(final Uri source) {
        return new ImageRequestBuilder().setSource(source);
    }
    
    public ImageRequest build() {
        this.validate();
        return new ImageRequest(this);
    }
    
    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }
    
    public ImageRequest$ImageType getImageType() {
        return this.mImageType;
    }
    
    public ImageRequest$RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }
    
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }
    
    public Priority getRequestPriority() {
        return this.mRequestPriority;
    }
    
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }
    
    public Uri getSourceUri() {
        return this.mSourceUri;
    }
    
    public boolean isAutoRotateEnabled() {
        return this.mAutoRotateEnabled;
    }
    
    public boolean isDiskCacheEnabled() {
        return this.mDiskCacheEnabled && UriUtil.isNetworkUri(this.mSourceUri);
    }
    
    public boolean isLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }
    
    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }
    
    public ImageRequestBuilder setAutoRotateEnabled(final boolean mAutoRotateEnabled) {
        this.mAutoRotateEnabled = mAutoRotateEnabled;
        return this;
    }
    
    public ImageRequestBuilder setPostprocessor(final Postprocessor mPostprocessor) {
        this.mPostprocessor = mPostprocessor;
        return this;
    }
    
    public ImageRequestBuilder setProgressiveRenderingEnabled(final boolean mProgressiveRenderingEnabled) {
        this.mProgressiveRenderingEnabled = mProgressiveRenderingEnabled;
        return this;
    }
    
    public ImageRequestBuilder setResizeOptions(final ResizeOptions mResizeOptions) {
        this.mResizeOptions = mResizeOptions;
        return this;
    }
    
    public ImageRequestBuilder setSource(final Uri mSourceUri) {
        Preconditions.checkNotNull(mSourceUri);
        this.mSourceUri = mSourceUri;
        return this;
    }
    
    protected void validate() {
        if (this.mSourceUri == null) {
            throw new ImageRequestBuilder$BuilderException("Source must be set!");
        }
        Label_0085: {
            if (!UriUtil.isLocalResourceUri(this.mSourceUri)) {
                break Label_0085;
            }
            if (!this.mSourceUri.isAbsolute()) {
                throw new ImageRequestBuilder$BuilderException("Resource URI path must be absolute.");
            }
            if (this.mSourceUri.getPath().isEmpty()) {
                throw new ImageRequestBuilder$BuilderException("Resource URI must not be empty");
            }
            try {
                Integer.parseInt(this.mSourceUri.getPath().substring(1));
                if (UriUtil.isLocalAssetUri(this.mSourceUri) && !this.mSourceUri.isAbsolute()) {
                    throw new ImageRequestBuilder$BuilderException("Asset URI path must be absolute.");
                }
            }
            catch (NumberFormatException ex) {
                throw new ImageRequestBuilder$BuilderException("Resource URI path must be a resource id.");
            }
        }
    }
}
