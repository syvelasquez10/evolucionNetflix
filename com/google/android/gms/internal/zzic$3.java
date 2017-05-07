// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import android.os.Process;
import java.util.concurrent.Callable;

final class zzic$3 implements Runnable
{
    final /* synthetic */ Callable zzIu;
    final /* synthetic */ zzin zzrA;
    
    zzic$3(final zzin zzrA, final Callable zzIu) {
        this.zzrA = zzrA;
        this.zzIu = zzIu;
    }
    
    @Override
    public void run() {
        try {
            Process.setThreadPriority(10);
            this.zzrA.zzf(this.zzIu.call());
        }
        catch (Exception ex) {
            zzp.zzby().zzc(ex, true);
            this.zzrA.cancel(true);
        }
    }
}
