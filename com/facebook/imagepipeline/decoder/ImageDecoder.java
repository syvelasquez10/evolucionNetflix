// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.QualityInfo;
import java.io.InputStream;
import com.facebook.common.internal.Closeables;
import com.facebook.imageformat.GifFormatChecker;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;

public class ImageDecoder
{
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Bitmap$Config mBitmapConfig;
    private final PlatformDecoder mPlatformDecoder;
    
    public ImageDecoder(final AnimatedImageFactory mAnimatedImageFactory, final PlatformDecoder mPlatformDecoder, final Bitmap$Config mBitmapConfig) {
        this.mAnimatedImageFactory = mAnimatedImageFactory;
        this.mBitmapConfig = mBitmapConfig;
        this.mPlatformDecoder = mPlatformDecoder;
    }
    
    public CloseableImage decodeAnimatedWebp(final EncodedImage encodedImage, final ImageDecodeOptions imageDecodeOptions) {
        return this.mAnimatedImageFactory.decodeWebP(encodedImage, imageDecodeOptions, this.mBitmapConfig);
    }
    
    public CloseableImage decodeGif(final EncodedImage encodedImage, final ImageDecodeOptions imageDecodeOptions) {
        final InputStream inputStream = encodedImage.getInputStream();
        if (inputStream == null) {
            return null;
        }
        try {
            if (!imageDecodeOptions.forceStaticImage && GifFormatChecker.isAnimated(inputStream)) {
                return this.mAnimatedImageFactory.decodeGif(encodedImage, imageDecodeOptions, this.mBitmapConfig);
            }
            return this.decodeStaticImage(encodedImage);
        }
        finally {
            Closeables.closeQuietly(inputStream);
        }
    }
    
    public CloseableImage decodeImage(final EncodedImage encodedImage, final int n, final QualityInfo qualityInfo, final ImageDecodeOptions imageDecodeOptions) {
        final ImageFormat imageFormat = encodedImage.getImageFormat();
        ImageFormat imageFormat_WrapIOException;
        if (imageFormat == null || (imageFormat_WrapIOException = imageFormat) == ImageFormat.UNKNOWN) {
            imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        }
        switch (ImageDecoder$1.$SwitchMap$com$facebook$imageformat$ImageFormat[imageFormat_WrapIOException.ordinal()]) {
            default: {
                return this.decodeStaticImage(encodedImage);
            }
            case 1: {
                throw new IllegalArgumentException("unknown image format");
            }
            case 2: {
                return this.decodeJpeg(encodedImage, n, qualityInfo);
            }
            case 3: {
                return this.decodeGif(encodedImage, imageDecodeOptions);
            }
            case 4: {
                return this.decodeAnimatedWebp(encodedImage, imageDecodeOptions);
            }
        }
    }
    
    public CloseableStaticBitmap decodeJpeg(final EncodedImage encodedImage, final int n, final QualityInfo qualityInfo) {
        final CloseableReference<Bitmap> decodeJPEGFromEncodedImage = this.mPlatformDecoder.decodeJPEGFromEncodedImage(encodedImage, this.mBitmapConfig, n);
        try {
            return new CloseableStaticBitmap(decodeJPEGFromEncodedImage, qualityInfo, encodedImage.getRotationAngle());
        }
        finally {
            decodeJPEGFromEncodedImage.close();
        }
    }
    
    public CloseableStaticBitmap decodeStaticImage(final EncodedImage encodedImage) {
        final CloseableReference<Bitmap> decodeFromEncodedImage = this.mPlatformDecoder.decodeFromEncodedImage(encodedImage, this.mBitmapConfig);
        try {
            return new CloseableStaticBitmap(decodeFromEncodedImage, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle());
        }
        finally {
            decodeFromEncodedImage.close();
        }
    }
}
