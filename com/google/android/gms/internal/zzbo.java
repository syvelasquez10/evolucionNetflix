// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;

public class zzbo extends zzbl
{
    private MessageDigest zzsl;
    
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
            this.zzsl = this.zzcy();
            while (true) {
                final byte[] digest;
                synchronized (this.zzpc) {
                    if (this.zzsl == null) {
                        // monitorexit(this.zzpc)
                        return new byte[0];
                    }
                    this.zzsl.reset();
                    this.zzsl.update(zza);
                    digest = this.zzsl.digest();
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
