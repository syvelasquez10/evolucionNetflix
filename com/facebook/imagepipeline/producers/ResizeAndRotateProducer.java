// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.EncodedImage;

public class ResizeAndRotateProducer implements Producer<EncodedImage>
{
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    public ResizeAndRotateProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final Producer<EncodedImage> producer) {
        this.mExecutor = Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = Preconditions.checkNotNull(producer);
    }
    
    static float determineResizeRatio(final ResizeOptions resizeOptions, final int n, final int n2) {
        float n3;
        if (resizeOptions == null) {
            n3 = 1.0f;
        }
        else {
            float max;
            if (n * (max = Math.max(resizeOptions.width / n, resizeOptions.height / n2)) > 2048.0f) {
                max = 2048.0f / n;
            }
            n3 = max;
            if (n2 * max > 2048.0f) {
                return 2048.0f / n2;
            }
        }
        return n3;
    }
    
    private static int getRotationAngle(final ImageRequest imageRequest, final EncodedImage encodedImage) {
        boolean b = false;
        if (!imageRequest.getAutoRotateEnabled()) {
            return 0;
        }
        final int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            b = true;
        }
        Preconditions.checkArgument(b);
        return rotationAngle;
    }
    
    private static int getScaleNumerator(final ImageRequest imageRequest, final EncodedImage encodedImage) {
        final ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        int n;
        if (resizeOptions == null) {
            n = 8;
        }
        else {
            final int rotationAngle = getRotationAngle(imageRequest, encodedImage);
            boolean b;
            if (rotationAngle == 90 || rotationAngle == 270) {
                b = true;
            }
            else {
                b = false;
            }
            int n2;
            if (b) {
                n2 = encodedImage.getHeight();
            }
            else {
                n2 = encodedImage.getWidth();
            }
            int n3;
            if (b) {
                n3 = encodedImage.getWidth();
            }
            else {
                n3 = encodedImage.getHeight();
            }
            final int roundNumerator = roundNumerator(determineResizeRatio(resizeOptions, n2, n3));
            if (roundNumerator > 8) {
                return 8;
            }
            if ((n = roundNumerator) < 1) {
                return 1;
            }
        }
        return n;
    }
    
    static int roundNumerator(final float n) {
        return (int)(0.6666667f + 8.0f * n);
    }
    
    private static boolean shouldResize(final int n) {
        return n < 8;
    }
    
    private static TriState shouldTransform(final ImageRequest imageRequest, final EncodedImage encodedImage) {
        if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        if (encodedImage.getImageFormat() != ImageFormat.JPEG) {
            return TriState.NO;
        }
        return TriState.valueOf(getRotationAngle(imageRequest, encodedImage) != 0 || shouldResize(getScaleNumerator(imageRequest, encodedImage)));
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        this.mInputProducer.produceResults((Consumer<EncodedImage>)new ResizeAndRotateProducer$TransformingConsumer(consumer, producerContext), producerContext);
    }
}
