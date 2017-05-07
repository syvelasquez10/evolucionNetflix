// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

class zzf$1 implements UncaughtExceptionHandler
{
    final /* synthetic */ zzf zzLQ;
    
    zzf$1(final zzf zzLQ) {
        this.zzLQ = zzLQ;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        final zzaf zzir = this.zzLQ.zzir();
        if (zzir != null) {
            zzir.zze("Job execution failed", t);
        }
    }
}
