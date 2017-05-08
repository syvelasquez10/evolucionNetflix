// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Supplier;
import java.io.File;
import java.io.FileInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.internal.Closeables;
import com.facebook.common.references.CloseableReference;
import java.io.InputStream;
import android.os.Build$VERSION;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.EncodedImage;

public abstract class LocalFetchProducer implements Producer<EncodedImage>
{
    private final boolean mDecodeFileDescriptorEnabledForKitKat;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    protected LocalFetchProducer(final Executor mExecutor, final PooledByteBufferFactory mPooledByteBufferFactory, final boolean b) {
        this.mExecutor = mExecutor;
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
        this.mDecodeFileDescriptorEnabledForKitKat = (b && Build$VERSION.SDK_INT == 19);
    }
    
    protected EncodedImage getByteBufferBackedEncodedImage(final InputStream inputStream, final int n) {
        CloseableReference<?> closeableReference = null;
        Label_0045: {
            if (n >= 0) {
                break Label_0045;
            }
            try {
                CloseableReference<PooledByteBuffer> closeableReference2 = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
                while (true) {
                    closeableReference = closeableReference2;
                    return new EncodedImage(closeableReference2);
                    closeableReference2 = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, n));
                    continue;
                }
            }
            finally {
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely(closeableReference);
            }
        }
    }
    
    protected abstract EncodedImage getEncodedImage(final ImageRequest p0);
    
    protected EncodedImage getEncodedImage(final InputStream inputStream, final int n) {
        final Runtime runtime = Runtime.getRuntime();
        final long maxMemory = runtime.maxMemory();
        final long min = Math.min(maxMemory - (runtime.totalMemory() - runtime.freeMemory()), 8388608L);
        if (this.mDecodeFileDescriptorEnabledForKitKat && inputStream instanceof FileInputStream && maxMemory >= min * 64L) {
            return this.getInputStreamBackedEncodedImage(new File(inputStream.toString()), n);
        }
        return this.getByteBufferBackedEncodedImage(inputStream, n);
    }
    
    protected EncodedImage getInputStreamBackedEncodedImage(final File file, final int n) {
        return new EncodedImage(new LocalFetchProducer$3(this, file), n);
    }
    
    protected abstract String getProducerName();
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final LocalFetchProducer$1 localFetchProducer$1 = new LocalFetchProducer$1(this, consumer, producerContext.getListener(), this.getProducerName(), producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new LocalFetchProducer$2(this, localFetchProducer$1));
        this.mExecutor.execute(localFetchProducer$1);
    }
}
