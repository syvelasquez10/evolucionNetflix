// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzof;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.common.internal.zzx;

class zzb$4 implements Runnable
{
    final /* synthetic */ zzb zzLs;
    final /* synthetic */ zzab zzLw;
    
    zzb$4(final zzb zzLs, final zzab zzLw) {
        this.zzLs = zzLs;
        this.zzLw = zzLw;
    }
    
    @Override
    public void run() {
        this.zzLs.zzLq.zza(this.zzLw);
    }
}
