// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

class zzl$5 implements Runnable
{
    final /* synthetic */ zzw zzLx;
    final /* synthetic */ zzl zzMx;
    final /* synthetic */ long zzMy;
    
    zzl$5(final zzl zzMx, final zzw zzLx, final long zzMy) {
        this.zzMx = zzMx;
        this.zzLx = zzLx;
        this.zzMy = zzMy;
    }
    
    @Override
    public void run() {
        this.zzMx.zza(this.zzLx, this.zzMy);
    }
}
