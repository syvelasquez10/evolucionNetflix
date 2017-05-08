// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.common.ResizeOptions;

public final class ThumbnailSizeChecker
{
    public static int getAcceptableSize(final int n) {
        return (int)(n * 1.3333334f);
    }
    
    public static boolean isImageBigEnough(final int n, final int n2, final ResizeOptions resizeOptions) {
        if (resizeOptions == null) {
            if (getAcceptableSize(n) < 2048.0f || getAcceptableSize(n2) < 2048) {
                return false;
            }
        }
        else if (getAcceptableSize(n) < resizeOptions.width || getAcceptableSize(n2) < resizeOptions.height) {
            return false;
        }
        return true;
    }
    
    public static boolean isImageBigEnough(final EncodedImage encodedImage, final ResizeOptions resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        switch (encodedImage.getRotationAngle()) {
            default: {
                return isImageBigEnough(encodedImage.getWidth(), encodedImage.getHeight(), resizeOptions);
            }
            case 90:
            case 270: {
                return isImageBigEnough(encodedImage.getHeight(), encodedImage.getWidth(), resizeOptions);
            }
        }
    }
}
