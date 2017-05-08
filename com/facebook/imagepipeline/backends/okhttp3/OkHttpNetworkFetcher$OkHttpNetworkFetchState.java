// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.backends.okhttp3;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;

public class OkHttpNetworkFetcher$OkHttpNetworkFetchState extends FetchState
{
    public long fetchCompleteTime;
    public long responseTime;
    public long submitTime;
    
    public OkHttpNetworkFetcher$OkHttpNetworkFetchState(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        super(consumer, producerContext);
    }
}
