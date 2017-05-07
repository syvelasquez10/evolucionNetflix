// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

final class zze$2 extends zze
{
    final /* synthetic */ char zzacV;
    final /* synthetic */ char zzacW;
    
    zze$2(final char zzacV, final char zzacW) {
        this.zzacV = zzacV;
        this.zzacW = zzacW;
    }
    
    @Override
    public boolean zzd(final char c) {
        return c == this.zzacV || c == this.zzacW;
    }
}
