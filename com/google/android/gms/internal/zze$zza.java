// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class zze$zza implements Runnable
{
    final /* synthetic */ zze zzu;
    private final zzk zzv;
    private final zzm zzw;
    private final Runnable zzx;
    
    public zze$zza(final zze zzu, final zzk zzv, final zzm zzw, final Runnable zzx) {
        this.zzu = zzu;
        this.zzv = zzv;
        this.zzw = zzw;
        this.zzx = zzx;
    }
    
    @Override
    public void run() {
        if (this.zzv.isCanceled()) {
            this.zzv.zzd("canceled-at-delivery");
        }
        else {
            if (this.zzw.isSuccess()) {
                this.zzv.zza(this.zzw.result);
            }
            else {
                this.zzv.zzc(this.zzw.zzah);
            }
            if (this.zzw.zzai) {
                this.zzv.zzc("intermediate-response");
            }
            else {
                this.zzv.zzd("done");
            }
            if (this.zzx != null) {
                this.zzx.run();
            }
        }
    }
}
