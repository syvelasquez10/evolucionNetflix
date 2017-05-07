// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.UnsupportedEncodingException;

public final class ag
{
    private static final byte[] a;
    private static final byte[] b;
    private static final byte[] c;
    
    static {
        b = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        while (true) {
            try {
                final byte[] a2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
                a = a2;
                c = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
            }
            catch (UnsupportedEncodingException ex) {
                final byte[] a2 = ag.b;
                continue;
            }
            break;
        }
    }
    
    public static String a(final byte[] array) {
        return a(array, array.length);
    }
    
    private static String a(final byte[] array, final int n) {
        final int n2 = n * 4 / 3;
        int n3;
        if (n % 3 > 0) {
            n3 = 4;
        }
        else {
            n3 = 0;
        }
        final byte[] array2 = new byte[n3 + n2 + n2 / 76];
        int n4 = 0;
        int n5 = 0;
        int i;
        int n6;
        for (i = 0; i < n - 2; i += 3, n5 = n6 + 4) {
            a(array, i + 0, 3, array2, n5);
            n4 += 4;
            n6 = n5;
            if (n4 == 76) {
                array2[n5 + 4] = 10;
                n6 = n5 + 1;
                n4 = 0;
            }
        }
        int n7 = n5;
        if (i < n) {
            a(array, i + 0, n - i, array2, n5);
            n7 = n5 + 4;
        }
        try {
            return new String(array2, 0, n7, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return new String(array2, 0, n7);
        }
    }
    
    private static byte[] a(final byte[] array, int n, final int n2, final byte[] array2, final int n3) {
        int n4 = 0;
        int n5;
        if (n2 > 0) {
            n5 = array[n] << 24 >>> 8;
        }
        else {
            n5 = 0;
        }
        int n6;
        if (n2 > 1) {
            n6 = array[n + 1] << 24 >>> 16;
        }
        else {
            n6 = 0;
        }
        if (n2 > 2) {
            n4 = array[n + 2] << 24 >>> 24;
        }
        n = (n4 | (n6 | n5));
        switch (n2) {
            default: {
                return array2;
            }
            case 3: {
                array2[n3] = ag.a[n >>> 18];
                array2[n3 + 1] = ag.a[n >>> 12 & 0x3F];
                array2[n3 + 2] = ag.a[n >>> 6 & 0x3F];
                array2[n3 + 3] = ag.a[n & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = ag.a[n >>> 18];
                array2[n3 + 1] = ag.a[n >>> 12 & 0x3F];
                array2[n3 + 2] = ag.a[n >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = ag.a[n >>> 18];
                array2[n3 + 1] = ag.a[n >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
        }
    }
}
