// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class zzqr$1 implements Runnable
{
    final /* synthetic */ String zzaUi;
    final /* synthetic */ Integer zzaUj;
    final /* synthetic */ zzql zzaUk;
    final /* synthetic */ zzqq zzaUl;
    final /* synthetic */ zzqr zzaUm;
    
    zzqr$1(final zzqr zzaUm, final String zzaUi, final Integer zzaUj, final zzql zzaUk, final zzqq zzaUl) {
        this.zzaUm = zzaUm;
        this.zzaUi = zzaUi;
        this.zzaUj = zzaUj;
        this.zzaUk = zzaUk;
        this.zzaUl = zzaUl;
    }
    
    @Override
    public void run() {
        this.zzaUm.zzb(this.zzaUi, this.zzaUj, this.zzaUk, this.zzaUl);
    }
}
