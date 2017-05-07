// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

class zzc$1 implements Runnable
{
    final /* synthetic */ zzk zzm;
    final /* synthetic */ zzc zzn;
    
    zzc$1(final zzc zzn, final zzk zzm) {
        this.zzn = zzn;
        this.zzm = zzm;
    }
    
    @Override
    public void run() {
        try {
            this.zzn.zzi.put(this.zzm);
        }
        catch (InterruptedException ex) {}
    }
}
