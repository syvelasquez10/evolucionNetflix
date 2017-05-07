// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

final class zzic$4 implements Runnable
{
    final /* synthetic */ Future zzIv;
    final /* synthetic */ zzin zzrA;
    
    zzic$4(final zzin zzrA, final Future zzIv) {
        this.zzrA = zzrA;
        this.zzIv = zzIv;
    }
    
    @Override
    public void run() {
        if (this.zzrA.isCancelled()) {
            this.zzIv.cancel(true);
        }
    }
}
