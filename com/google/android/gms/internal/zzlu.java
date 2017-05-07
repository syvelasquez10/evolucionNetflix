// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzlu
{
    public static int zza(final byte[] array, int n, final int n2, int i) {
        final int n3 = n + (n2 & 0xFFFFFFFC);
        final int n4 = i;
        i = n;
        n = n4;
        while (i < n3) {
            final int n5 = ((array[i] & 0xFF) | (array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF) << 16 | array[i + 3] << 24) * -862048943;
            n ^= (n5 >>> 17 | n5 << 15) * 461845907;
            n = -430675100 + (n >>> 19 | n << 13) * 5;
            i += 4;
        }
        int n6 = 0;
        i = 0;
        switch (n2 & 0x3) {
            case 3: {
                i = (array[n3 + 2] & 0xFF) << 16;
            }
            case 2: {
                n6 = (i | (array[n3 + 1] & 0xFF) << 8);
            }
            case 1: {
                i = (n6 | (array[n3] & 0xFF)) * -862048943;
                n ^= (i >>> 17 | i << 15) * 461845907;
                break;
            }
        }
        n ^= n2;
        n = (n ^ n >>> 16) * -2048144789;
        n = (n ^ n >>> 13) * -1028477387;
        return n ^ n >>> 16;
    }
}
