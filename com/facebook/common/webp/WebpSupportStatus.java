// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.webp;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import android.os.Build$VERSION;

public class WebpSupportStatus
{
    private static final byte[] WEBP_NAME_BYTES;
    private static final byte[] WEBP_RIFF_BYTES;
    private static final byte[] WEBP_VP8L_BYTES;
    private static final byte[] WEBP_VP8X_BYTES;
    private static final byte[] WEBP_VP8_BYTES;
    public static final boolean sIsExtendedWebpSupported;
    public static final boolean sIsSimpleWebpSupported;
    public static final boolean sIsWebpSupportRequired;
    public static WebpBitmapFactory sWebpBitmapFactory;
    public static boolean sWebpLibraryPresent;
    
    static {
        final boolean b = true;
        while (true) {
            while (true) {
                boolean sIsWebpSupportRequired2 = false;
                Label_0012: {
                    if (Build$VERSION.SDK_INT <= 17) {
                        sIsWebpSupportRequired2 = true;
                        break Label_0012;
                    }
                    Label_0103: {
                        break Label_0103;
                        while (true) {
                            boolean sIsSimpleWebpSupported2 = false;
                            sIsSimpleWebpSupported = sIsSimpleWebpSupported2;
                            sIsExtendedWebpSupported = isExtendedWebpSupported();
                            WebpSupportStatus.sWebpBitmapFactory = null;
                            WebpSupportStatus.sWebpLibraryPresent = false;
                            while (true) {
                                try {
                                    WebpSupportStatus.sWebpBitmapFactory = (WebpBitmapFactory)Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
                                    WebpSupportStatus.sWebpLibraryPresent = true;
                                    WEBP_RIFF_BYTES = asciiBytes("RIFF");
                                    WEBP_NAME_BYTES = asciiBytes("WEBP");
                                    WEBP_VP8_BYTES = asciiBytes("VP8 ");
                                    WEBP_VP8L_BYTES = asciiBytes("VP8L");
                                    WEBP_VP8X_BYTES = asciiBytes("VP8X");
                                    return;
                                    sIsWebpSupportRequired2 = false;
                                    break;
                                    sIsSimpleWebpSupported2 = false;
                                }
                                catch (Throwable t) {
                                    WebpSupportStatus.sWebpLibraryPresent = false;
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
                sIsWebpSupportRequired = sIsWebpSupportRequired2;
                if (Build$VERSION.SDK_INT >= 14) {
                    final boolean sIsSimpleWebpSupported2 = b;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private static byte[] asciiBytes(final String s) {
        try {
            return s.getBytes("ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("ASCII not found!", ex);
        }
    }
    
    public static boolean isAnimatedWebpHeader(final byte[] array, int n) {
        final boolean matchBytePattern = matchBytePattern(array, n + 12, WebpSupportStatus.WEBP_VP8X_BYTES);
        if ((array[n + 20] & 0x2) == 0x2) {
            n = 1;
        }
        else {
            n = 0;
        }
        return matchBytePattern && n != 0;
    }
    
    public static boolean isExtendedWebpHeader(final byte[] array, final int n, final int n2) {
        return n2 >= 21 && matchBytePattern(array, n + 12, WebpSupportStatus.WEBP_VP8X_BYTES);
    }
    
    public static boolean isExtendedWebpHeaderWithAlpha(final byte[] array, int n) {
        final boolean matchBytePattern = matchBytePattern(array, n + 12, WebpSupportStatus.WEBP_VP8X_BYTES);
        if ((array[n + 20] & 0x10) == 0x10) {
            n = 1;
        }
        else {
            n = 0;
        }
        return matchBytePattern && n != 0;
    }
    
    private static boolean isExtendedWebpSupported() {
        if (Build$VERSION.SDK_INT >= 17) {
            if (Build$VERSION.SDK_INT == 17) {
                final byte[] decode = Base64.decode("UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==", 0);
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                bitmapFactory$Options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(decode, 0, decode.length, bitmapFactory$Options);
                if (bitmapFactory$Options.outHeight != 1 || bitmapFactory$Options.outWidth != 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean isLosslessWebpHeader(final byte[] array, final int n) {
        return matchBytePattern(array, n + 12, WebpSupportStatus.WEBP_VP8L_BYTES);
    }
    
    public static boolean isSimpleWebpHeader(final byte[] array, final int n) {
        return matchBytePattern(array, n + 12, WebpSupportStatus.WEBP_VP8_BYTES);
    }
    
    public static boolean isWebpHeader(final byte[] array, final int n, final int n2) {
        return n2 >= 20 && matchBytePattern(array, n, WebpSupportStatus.WEBP_RIFF_BYTES) && matchBytePattern(array, n + 8, WebpSupportStatus.WEBP_NAME_BYTES);
    }
    
    private static boolean matchBytePattern(final byte[] array, final int n, final byte[] array2) {
        if (array2 != null && array != null && array2.length + n <= array.length) {
            for (int i = 0; i < array2.length; ++i) {
                if (array[i + n] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
