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

class zzb$3 implements Runnable
{
    final /* synthetic */ zzb zzLs;
    final /* synthetic */ String zzLu;
    final /* synthetic */ Runnable zzLv;
    
    zzb$3(final zzb zzLs, final String zzLu, final Runnable zzLv) {
        this.zzLs = zzLs;
        this.zzLu = zzLu;
        this.zzLv = zzLv;
    }
    
    @Override
    public void run() {
        this.zzLs.zzLq.zzbg(this.zzLu);
        if (this.zzLv != null) {
            this.zzLv.run();
        }
    }
}
