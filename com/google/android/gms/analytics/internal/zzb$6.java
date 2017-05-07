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

class zzb$6 implements Runnable
{
    final /* synthetic */ zzb zzLs;
    final /* synthetic */ zzw zzLx;
    
    zzb$6(final zzb zzLs, final zzw zzLx) {
        this.zzLs = zzLs;
        this.zzLx = zzLx;
    }
    
    @Override
    public void run() {
        this.zzLs.zzLq.zzb(this.zzLx);
    }
}
