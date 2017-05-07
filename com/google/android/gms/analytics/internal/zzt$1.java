// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import android.os.Handler;
import android.os.Looper;

class zzt$1 implements Runnable
{
    final /* synthetic */ zzt zzMU;
    
    zzt$1(final zzt zzMU) {
        this.zzMU = zzMU;
    }
    
    @Override
    public void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzMU.zzLy.zzig().zzf(this);
        }
        else {
            final boolean zzbr = this.zzMU.zzbr();
            this.zzMU.zzMS = 0L;
            if (zzbr && !this.zzMU.zzMT) {
                this.zzMU.run();
            }
        }
    }
}
