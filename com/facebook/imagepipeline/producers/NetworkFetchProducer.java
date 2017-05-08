// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import android.os.SystemClock;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import java.util.Map;
import java.io.InputStream;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.image.EncodedImage;

public class NetworkFetchProducer implements Producer<EncodedImage>
{
    private final ByteArrayPool mByteArrayPool;
    private final NetworkFetcher mNetworkFetcher;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    public NetworkFetchProducer(final PooledByteBufferFactory mPooledByteBufferFactory, final ByteArrayPool mByteArrayPool, final NetworkFetcher mNetworkFetcher) {
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
        this.mByteArrayPool = mByteArrayPool;
        this.mNetworkFetcher = mNetworkFetcher;
    }
    
    private static float calculateProgress(final int n, final int n2) {
        if (n2 > 0) {
            return n / n2;
        }
        return 1.0f - (float)Math.exp(-n / 50000.0);
    }
    
    private Map<String, String> getExtraMap(final FetchState fetchState, final int n) {
        if (!fetchState.getListener().requiresExtraMap(fetchState.getId())) {
            return null;
        }
        return this.mNetworkFetcher.getExtraMap(fetchState, n);
    }
    
    private void handleFinalResult(final PooledByteBufferOutputStream pooledByteBufferOutputStream, final FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithSuccess(fetchState.getId(), "NetworkFetchProducer", this.getExtraMap(fetchState, pooledByteBufferOutputStream.size()));
        this.notifyConsumer(pooledByteBufferOutputStream, true, fetchState.getConsumer());
    }
    
    private void maybeHandleIntermediateResult(final PooledByteBufferOutputStream pooledByteBufferOutputStream, final FetchState fetchState) {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (this.shouldPropagateIntermediateResults(fetchState) && uptimeMillis - fetchState.getLastIntermediateResultTimeMs() >= 100L) {
            fetchState.setLastIntermediateResultTimeMs(uptimeMillis);
            fetchState.getListener().onProducerEvent(fetchState.getId(), "NetworkFetchProducer", "intermediate_result");
            this.notifyConsumer(pooledByteBufferOutputStream, false, fetchState.getConsumer());
        }
    }
    
    private void notifyConsumer(final PooledByteBufferOutputStream pooledByteBufferOutputStream, final boolean b, final Consumer<EncodedImage> consumer) {
        final CloseableReference<PooledByteBuffer> of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
        EncodedImage encodedImage;
        try {
            final EncodedImage encodedImage2;
            encodedImage = (encodedImage2 = new EncodedImage(of));
            encodedImage2.parseMetaData();
            final Consumer<EncodedImage> consumer2 = consumer;
            final EncodedImage encodedImage3 = encodedImage;
            final boolean b2 = b;
            consumer2.onNewResult(encodedImage3, b2);
            final EncodedImage encodedImage4 = encodedImage;
            EncodedImage.closeSafely(encodedImage4);
            final CloseableReference<PooledByteBuffer> closeableReference = of;
            CloseableReference.closeSafely(closeableReference);
            return;
        }
        finally {
            final Object o2;
            final Object o = o2;
            final EncodedImage encodedImage5 = null;
        }
        while (true) {
            try {
                final EncodedImage encodedImage2 = encodedImage;
                encodedImage2.parseMetaData();
                final Consumer<EncodedImage> consumer2 = consumer;
                final EncodedImage encodedImage3 = encodedImage;
                final boolean b2 = b;
                consumer2.onNewResult(encodedImage3, b2);
                final EncodedImage encodedImage4 = encodedImage;
                EncodedImage.closeSafely(encodedImage4);
                final CloseableReference<PooledByteBuffer> closeableReference = of;
                CloseableReference.closeSafely(closeableReference);
                return;
                final EncodedImage encodedImage5;
                EncodedImage.closeSafely(encodedImage5);
                CloseableReference.closeSafely(of);
                throw;
            }
            finally {
                final EncodedImage encodedImage5 = encodedImage;
                continue;
            }
            break;
        }
    }
    
    private void onCancellation(final FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithCancellation(fetchState.getId(), "NetworkFetchProducer", null);
        fetchState.getConsumer().onCancellation();
    }
    
    private void onFailure(final FetchState fetchState, final Throwable t) {
        fetchState.getListener().onProducerFinishWithFailure(fetchState.getId(), "NetworkFetchProducer", t, null);
        fetchState.getConsumer().onFailure(t);
    }
    
    private void onResponse(final FetchState fetchState, final InputStream inputStream, final int n) {
        while (true) {
            Label_0112: {
                if (n <= 0) {
                    break Label_0112;
                }
                final PooledByteBufferOutputStream pooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream(n);
                final byte[] array = this.mByteArrayPool.get(16384);
                Label_0126: {
                    try {
                        while (true) {
                            final int read = inputStream.read(array);
                            if (read < 0) {
                                break Label_0126;
                            }
                            if (read <= 0) {
                                continue;
                            }
                            pooledByteBufferOutputStream.write(array, 0, read);
                            this.maybeHandleIntermediateResult(pooledByteBufferOutputStream, fetchState);
                            fetchState.getConsumer().onProgressUpdate(calculateProgress(pooledByteBufferOutputStream.size(), n));
                        }
                    }
                    finally {
                        this.mByteArrayPool.release(array);
                        pooledByteBufferOutputStream.close();
                    }
                    break Label_0112;
                }
                final FetchState fetchState2;
                this.mNetworkFetcher.onFetchCompletion(fetchState2, pooledByteBufferOutputStream.size());
                this.handleFinalResult(pooledByteBufferOutputStream, fetchState2);
                this.mByteArrayPool.release(array);
                pooledByteBufferOutputStream.close();
                return;
            }
            final PooledByteBufferOutputStream pooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream();
            continue;
        }
    }
    
    private boolean shouldPropagateIntermediateResults(final FetchState fetchState) {
        return fetchState.getContext().getImageRequest().getProgressiveRenderingEnabled() && this.mNetworkFetcher.shouldPropagate(fetchState);
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        producerContext.getListener().onProducerStart(producerContext.getId(), "NetworkFetchProducer");
        final FetchState fetchState = this.mNetworkFetcher.createFetchState(consumer, producerContext);
        this.mNetworkFetcher.fetch(fetchState, new NetworkFetchProducer$1(this, fetchState));
    }
}
