// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;

public class ar extends ao
{
    private MessageDigest nP;
    
    byte[] a(final String[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(aq.o(array[i]) & 0xFF);
        }
        return array2;
    }
    
    public byte[] l(final String s) {
        while (true) {
            final byte[] a = this.a(s.split(" "));
            this.nP = this.ba();
            while (true) {
                final byte[] digest;
                synchronized (this.mw) {
                    if (this.nP == null) {
                        // monitorexit(this.mw)
                        return new byte[0];
                    }
                    this.nP.reset();
                    this.nP.update(a);
                    digest = this.nP.digest();
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
