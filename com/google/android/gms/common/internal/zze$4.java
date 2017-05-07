// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

final class zze$4 extends zze
{
    final /* synthetic */ char zzacY;
    final /* synthetic */ char zzacZ;
    
    zze$4(final char zzacY, final char zzacZ) {
        this.zzacY = zzacY;
        this.zzacZ = zzacZ;
    }
    
    @Override
    public boolean zzd(final char c) {
        return this.zzacY <= c && c <= this.zzacZ;
    }
}
