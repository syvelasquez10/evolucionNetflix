// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

final class zze$11 extends zze
{
    final /* synthetic */ char zzada;
    
    zze$11(final char zzada) {
        this.zzada = zzada;
    }
    
    @Override
    public zze zza(final zze zze) {
        if (zze.zzd(this.zzada)) {
            return zze;
        }
        return super.zza(zze);
    }
    
    @Override
    public boolean zzd(final char c) {
        return c == this.zzada;
    }
}
