// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzu;

abstract class zzc$zza
{
    private int zzVM;
    
    protected zzc$zza(final byte[] array) {
        zzu.zzb(array.length == 25, "cert hash data has incorrect length");
        this.zzVM = Arrays.hashCode(array);
    }
    
    protected static byte[] zzbO(final String s) {
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
        return this.zzVM;
    }
}
