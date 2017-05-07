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

class zzb$2 implements Runnable
{
    final /* synthetic */ zzb zzLs;
    final /* synthetic */ boolean zzLt;
    
    zzb$2(final zzb zzLs, final boolean zzLt) {
        this.zzLs = zzLs;
        this.zzLt = zzLt;
    }
    
    @Override
    public void run() {
        this.zzLs.zzLq.zzI(this.zzLt);
    }
}
