// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.IBinder;
import android.content.Intent;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import android.os.Handler;
import android.app.Service;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzw;

class AnalyticsService$1 implements zzw
{
    final /* synthetic */ int zzKf;
    final /* synthetic */ zzf zzKg;
    final /* synthetic */ zzaf zzKh;
    final /* synthetic */ AnalyticsService zzKi;
    
    AnalyticsService$1(final AnalyticsService zzKi, final int zzKf, final zzf zzKg, final zzaf zzKh) {
        this.zzKi = zzKi;
        this.zzKf = zzKf;
        this.zzKg = zzKg;
        this.zzKh = zzKh;
    }
    
    @Override
    public void zzc(final Throwable t) {
        this.zzKi.mHandler.post((Runnable)new AnalyticsService$1$1(this));
    }
}
