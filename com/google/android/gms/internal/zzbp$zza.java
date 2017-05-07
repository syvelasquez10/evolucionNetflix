// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzbp$zza
{
    final long value;
    final String zzsx;
    
    zzbp$zza(final long value, final String zzsx) {
        this.value = value;
        this.zzsx = zzsx;
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
