// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import java.io.IOException;
import com.facebook.common.internal.Throwables;
import java.io.InputStream;
import com.facebook.common.webp.WebpSupportStatus;
import java.io.UnsupportedEncodingException;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Ints;

public class ImageFormatChecker
{
    private static final byte[] BMP_HEADER;
    private static final byte[] GIF_HEADER_87A;
    private static final byte[] GIF_HEADER_89A;
    private static final byte[] JPEG_HEADER;
    private static final int MAX_HEADER_LENGTH;
    private static final byte[] PNG_HEADER;
    
    static {
        JPEG_HEADER = new byte[] { -1, -40, -1 };
        PNG_HEADER = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
        GIF_HEADER_87A = asciiBytes("GIF87a");
        GIF_HEADER_89A = asciiBytes("GIF89a");
        BMP_HEADER = asciiBytes("BM");
        MAX_HEADER_LENGTH = Ints.max(21, 20, ImageFormatChecker.JPEG_HEADER.length, ImageFormatChecker.PNG_HEADER.length, 6, ImageFormatChecker.BMP_HEADER.length);
    }
    
    private static byte[] asciiBytes(final String s) {
        Preconditions.checkNotNull(s);
        try {
            return s.getBytes("ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("ASCII not found!", ex);
        }
    }
    
    private static ImageFormat doGetImageFormat(final byte[] array, final int n) {
        Preconditions.checkNotNull(array);
        if (WebpSupportStatus.isWebpHeader(array, 0, n)) {
            return getWebpFormat(array, n);
        }
        if (isJpegHeader(array, n)) {
            return ImageFormat.JPEG;
        }
        if (isPngHeader(array, n)) {
            return ImageFormat.PNG;
        }
        if (isGifHeader(array, n)) {
            return ImageFormat.GIF;
        }
        if (isBmpHeader(array, n)) {
            return ImageFormat.BMP;
        }
        return ImageFormat.UNKNOWN;
    }
    
    public static ImageFormat getImageFormat(final InputStream inputStream) {
        Preconditions.checkNotNull(inputStream);
        final byte[] array = new byte[ImageFormatChecker.MAX_HEADER_LENGTH];
        return doGetImageFormat(array, readHeaderFromStream(inputStream, array));
    }
    
    public static ImageFormat getImageFormat_WrapIOException(final InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        }
        catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }
    
    private static ImageFormat getWebpFormat(final byte[] array, final int n) {
        Preconditions.checkArgument(WebpSupportStatus.isWebpHeader(array, 0, n));
        if (WebpSupportStatus.isSimpleWebpHeader(array, 0)) {
            return ImageFormat.WEBP_SIMPLE;
        }
        if (WebpSupportStatus.isLosslessWebpHeader(array, 0)) {
            return ImageFormat.WEBP_LOSSLESS;
        }
        if (!WebpSupportStatus.isExtendedWebpHeader(array, 0, n)) {
            return ImageFormat.UNKNOWN;
        }
        if (WebpSupportStatus.isAnimatedWebpHeader(array, 0)) {
            return ImageFormat.WEBP_ANIMATED;
        }
        if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(array, 0)) {
            return ImageFormat.WEBP_EXTENDED_WITH_ALPHA;
        }
        return ImageFormat.WEBP_EXTENDED;
    }
    
    private static boolean isBmpHeader(final byte[] array, final int n) {
        return n >= ImageFormatChecker.BMP_HEADER.length && matchBytePattern(array, 0, ImageFormatChecker.BMP_HEADER);
    }
    
    private static boolean isGifHeader(final byte[] array, final int n) {
        return n >= 6 && (matchBytePattern(array, 0, ImageFormatChecker.GIF_HEADER_87A) || matchBytePattern(array, 0, ImageFormatChecker.GIF_HEADER_89A));
    }
    
    private static boolean isJpegHeader(final byte[] array, final int n) {
        boolean b = false;
        if (n >= ImageFormatChecker.JPEG_HEADER.length) {
            b = b;
            if (matchBytePattern(array, 0, ImageFormatChecker.JPEG_HEADER)) {
                b = true;
            }
        }
        return b;
    }
    
    private static boolean isPngHeader(final byte[] array, final int n) {
        boolean b = false;
        if (n >= ImageFormatChecker.PNG_HEADER.length) {
            b = b;
            if (matchBytePattern(array, 0, ImageFormatChecker.PNG_HEADER)) {
                b = true;
            }
        }
        return b;
    }
    
    private static boolean matchBytePattern(final byte[] array, final int n, final byte[] array2) {
        Preconditions.checkNotNull(array);
        Preconditions.checkNotNull(array2);
        Preconditions.checkArgument(n >= 0);
        if (array2.length + n <= array.length) {
            for (int i = 0; i < array2.length; ++i) {
                if (array[i + n] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static int readHeaderFromStream(final InputStream inputStream, final byte[] array) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(array);
        Label_0054: {
            if (array.length < ImageFormatChecker.MAX_HEADER_LENGTH) {
                break Label_0054;
            }
            boolean b = true;
            while (true) {
                Preconditions.checkArgument(b);
                if (!inputStream.markSupported()) {
                    return ByteStreams.read(inputStream, array, 0, ImageFormatChecker.MAX_HEADER_LENGTH);
                }
                try {
                    inputStream.mark(ImageFormatChecker.MAX_HEADER_LENGTH);
                    return ByteStreams.read(inputStream, array, 0, ImageFormatChecker.MAX_HEADER_LENGTH);
                    b = false;
                    continue;
                }
                finally {
                    inputStream.reset();
                }
                break;
            }
        }
        return ByteStreams.read(inputStream, array, 0, ImageFormatChecker.MAX_HEADER_LENGTH);
    }
}
