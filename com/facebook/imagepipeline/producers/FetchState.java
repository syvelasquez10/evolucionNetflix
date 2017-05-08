// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.image.EncodedImage;

public class FetchState
{
    private final Consumer<EncodedImage> mConsumer;
    private final ProducerContext mContext;
    private long mLastIntermediateResultTimeMs;
    
    public FetchState(final Consumer<EncodedImage> mConsumer, final ProducerContext mContext) {
        this.mConsumer = mConsumer;
        this.mContext = mContext;
        this.mLastIntermediateResultTimeMs = 0L;
    }
    
    public Consumer<EncodedImage> getConsumer() {
        return this.mConsumer;
    }
    
    public ProducerContext getContext() {
        return this.mContext;
    }
    
    public String getId() {
        return this.mContext.getId();
    }
    
    public long getLastIntermediateResultTimeMs() {
        return this.mLastIntermediateResultTimeMs;
    }
    
    public ProducerListener getListener() {
        return this.mContext.getListener();
    }
    
    public Uri getUri() {
        return this.mContext.getImageRequest().getSourceUri();
    }
    
    public void setLastIntermediateResultTimeMs(final long mLastIntermediateResultTimeMs) {
        this.mLastIntermediateResultTimeMs = mLastIntermediateResultTimeMs;
    }
}
