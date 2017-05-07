// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;

public class zzbo extends zzbl
{
    private MessageDigest zzsw;
    
    byte[] zza(final String[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = this.zzj(zzbn.zzC(array[i]));
        }
        return array2;
    }
    
    byte zzj(final int n) {
        return (byte)((n & 0xFF) ^ (0xFF00 & n) >> 8 ^ (0xFF0000 & n) >> 16 ^ (0xFF000000 & n) >> 24);
    }
    
    public byte[] zzz(final String s) {
        while (true) {
            final byte[] zza = this.zza(s.split(" "));
            this.zzsw = this.zzcy();
            while (true) {
                final byte[] digest;
                synchronized (this.zzpd) {
                    if (this.zzsw == null) {
                        // monitorexit(this.zzpd)
                        return new byte[0];
                    }
                    this.zzsw.reset();
                    this.zzsw.update(zza);
                    digest = this.zzsw.digest();
                    final int length = 4;
                    if (digest.length > 4) {
                        final byte[] array = new byte[length];
                        System.arraycopy(digest, 0, array, 0, array.length);
                        return array;
                    }
                }
                final int length = digest.length;
                continue;
            }
        }
    }
}
