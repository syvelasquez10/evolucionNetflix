// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzx;

abstract class zzc$zza
{
    private int zzYp;
    
    protected zzc$zza(final byte[] array) {
        zzx.zzb(array.length == 25, "cert hash data has incorrect length");
        this.zzYp = Arrays.hashCode(array);
    }
    
    protected static byte[] zzbW(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof zzc$zza && Arrays.equals(this.getBytes(), ((zzc$zza)o).getBytes());
    }
    
    abstract byte[] getBytes();
    
    @Override
    public int hashCode() {
        return this.zzYp;
    }
}
