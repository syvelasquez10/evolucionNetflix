// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class zzqr$2 implements Runnable
{
    final /* synthetic */ byte[] zzQu;
    final /* synthetic */ String zzaUi;
    final /* synthetic */ zzqr zzaUm;
    
    zzqr$2(final zzqr zzaUm, final String zzaUi, final byte[] zzQu) {
        this.zzaUm = zzaUm;
        this.zzaUi = zzaUi;
        this.zzQu = zzQu;
    }
    
    @Override
    public void run() {
        this.zzaUm.zzf(this.zzaUi, this.zzQu);
    }
}
