// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.request;

import com.facebook.common.internal.Objects;
import android.net.Uri;
import java.io.File;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ImageDecodeOptions;

public class ImageRequest
{
    private final boolean mAutoRotateEnabled;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final ImageRequest$ImageType mImageType;
    private final boolean mIsDiskCacheEnabled;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final ImageRequest$RequestLevel mLowestPermittedRequestLevel;
    private final Postprocessor mPostprocessor;
    private final boolean mProgressiveRenderingEnabled;
    private final Priority mRequestPriority;
    ResizeOptions mResizeOptions;
    private File mSourceFile;
    private final Uri mSourceUri;
    
    protected ImageRequest(final ImageRequestBuilder imageRequestBuilder) {
        this.mResizeOptions = null;
        this.mImageType = imageRequestBuilder.getImageType();
        this.mSourceUri = imageRequestBuilder.getSourceUri();
        this.mProgressiveRenderingEnabled = imageRequestBuilder.isProgressiveRenderingEnabled();
        this.mLocalThumbnailPreviewsEnabled = imageRequestBuilder.isLocalThumbnailPreviewsEnabled();
        this.mImageDecodeOptions = imageRequestBuilder.getImageDecodeOptions();
        this.mResizeOptions = imageRequestBuilder.getResizeOptions();
        this.mAutoRotateEnabled = imageRequestBuilder.isAutoRotateEnabled();
        this.mRequestPriority = imageRequestBuilder.getRequestPriority();
        this.mLowestPermittedRequestLevel = imageRequestBuilder.getLowestPermittedRequestLevel();
        this.mIsDiskCacheEnabled = imageRequestBuilder.isDiskCacheEnabled();
        this.mPostprocessor = imageRequestBuilder.getPostprocessor();
    }
    
    public static ImageRequest fromUri(final Uri uri) {
        if (uri == null) {
            return null;
        }
        return ImageRequestBuilder.newBuilderWithSource(uri).build();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof ImageRequest) {
            final ImageRequest imageRequest = (ImageRequest)o;
            if (Objects.equal(this.mSourceUri, imageRequest.mSourceUri) && Objects.equal(this.mImageType, imageRequest.mImageType) && Objects.equal(this.mSourceFile, imageRequest.mSourceFile)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getAutoRotateEnabled() {
        return this.mAutoRotateEnabled;
    }
    
    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }
    
    public ImageRequest$ImageType getImageType() {
        return this.mImageType;
    }
    
    public boolean getLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }
    
    public ImageRequest$RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }
    
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }
    
    public int getPreferredHeight() {
        if (this.mResizeOptions != null) {
            return this.mResizeOptions.height;
        }
        return 2048;
    }
    
    public int getPreferredWidth() {
        if (this.mResizeOptions != null) {
            return this.mResizeOptions.width;
        }
        return 2048;
    }
    
    public Priority getPriority() {
        return this.mRequestPriority;
    }
    
    public boolean getProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }
    
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }
    
    public File getSourceFile() {
        synchronized (this) {
            if (this.mSourceFile == null) {
                this.mSourceFile = new File(this.mSourceUri.getPath());
            }
            return this.mSourceFile;
        }
    }
    
    public Uri getSourceUri() {
        return this.mSourceUri;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.mImageType, this.mSourceUri, this.mSourceFile);
    }
    
    public boolean isDiskCacheEnabled() {
        return this.mIsDiskCacheEnabled;
    }
}
