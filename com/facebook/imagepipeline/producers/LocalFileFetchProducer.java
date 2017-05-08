// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.InputStream;
import java.io.FileInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;

public class LocalFileFetchProducer extends LocalFetchProducer
{
    public LocalFileFetchProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final boolean b) {
        super(executor, pooledByteBufferFactory, b);
    }
    
    @Override
    protected EncodedImage getEncodedImage(final ImageRequest imageRequest) {
        return this.getEncodedImage(new FileInputStream(imageRequest.getSourceFile().toString()), (int)imageRequest.getSourceFile().length());
    }
    
    @Override
    protected String getProducerName() {
        return "LocalFileFetchProducer";
    }
}
