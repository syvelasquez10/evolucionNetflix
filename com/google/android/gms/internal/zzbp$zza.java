// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzbp$zza
{
    final long value;
    final String zzsm;
    
    zzbp$zza(final long value, final String zzsm) {
        this.value = value;
        this.zzsm = zzsm;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof zzbp$zza && ((zzbp$zza)o).value == this.value;
    }
    
    @Override
    public int hashCode() {
        return (int)this.value;
    }
}
