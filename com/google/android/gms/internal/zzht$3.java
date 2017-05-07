// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import android.os.Process;
import java.util.concurrent.Callable;

final class zzht$3 implements Runnable
{
    final /* synthetic */ Callable zzHB;
    final /* synthetic */ zzie zzrp;
    
    zzht$3(final zzie zzrp, final Callable zzHB) {
        this.zzrp = zzrp;
        this.zzHB = zzHB;
    }
    
    @Override
    public void run() {
        try {
            Process.setThreadPriority(10);
            this.zzrp.zzf(this.zzHB.call());
        }
        catch (Exception ex) {
            zzp.zzbA().zzc(ex, true);
            this.zzrp.cancel(true);
        }
    }
}
