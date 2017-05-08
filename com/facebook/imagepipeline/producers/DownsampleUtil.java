// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.common.logging.FLog;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class DownsampleUtil
{
    static float determineDownsampleRatio(final ImageRequest imageRequest, final EncodedImage encodedImage) {
        Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
        final ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        }
        final int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        boolean b;
        if (rotationAngle == 90 || rotationAngle == 270) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (b) {
            n = encodedImage.getHeight();
        }
        else {
            n = encodedImage.getWidth();
        }
        int n2;
        if (b) {
            n2 = encodedImage.getWidth();
        }
        else {
            n2 = encodedImage.getHeight();
        }
        final float n3 = resizeOptions.width / n;
        final float n4 = resizeOptions.height / n2;
        final float max = Math.max(n3, n4);
        FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", resizeOptions.width, resizeOptions.height, n, n2, n3, n4, max, imageRequest.getSourceUri().toString());
        return max;
    }
    
    public static int determineSampleSize(final ImageRequest imageRequest, final EncodedImage encodedImage) {
        int n;
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            n = 1;
        }
        else {
            final float determineDownsampleRatio = determineDownsampleRatio(imageRequest, encodedImage);
            int n2;
            if (encodedImage.getImageFormat() == ImageFormat.JPEG) {
                n2 = ratioToSampleSizeJPEG(determineDownsampleRatio);
            }
            else {
                n2 = ratioToSampleSize(determineDownsampleRatio);
            }
            final int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
            while (true) {
                n = n2;
                if (max / n2 <= 2048.0f) {
                    break;
                }
                if (encodedImage.getImageFormat() == ImageFormat.JPEG) {
                    n2 *= 2;
                }
                else {
                    ++n2;
                }
            }
        }
        return n;
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
    
    static int ratioToSampleSize(final float n) {
        if (n > 0.6666667f) {
            return 1;
        }
        int n2;
        for (n2 = 2; 1.0 / (Math.pow(n2, 2.0) - n2) * 0.3333333432674408 + 1.0 / n2 > n; ++n2) {}
        return n2 - 1;
    }
    
    static int ratioToSampleSizeJPEG(final float n) {
        int n2;
        if (n > 0.6666667f) {
            n2 = 1;
        }
        else {
            int n3 = 2;
            while (true) {
                n2 = n3;
                if (1.0 / (n3 * 2) * 0.3333333432674408 + 1.0 / (n3 * 2) <= n) {
                    break;
                }
                n3 *= 2;
            }
        }
        return n2;
    }
}
