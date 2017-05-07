// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzw;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzf;
import android.os.IBinder;
import android.content.Intent;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import android.os.Handler;
import android.app.Service;

public final class AnalyticsService extends Service
{
    private static Boolean zzKe;
    private final Handler mHandler;
    
    public AnalyticsService() {
        this.mHandler = new Handler();
    }
    
    public static boolean zzW(final Context context) {
        zzx.zzv(context);
        if (AnalyticsService.zzKe != null) {
            return AnalyticsService.zzKe;
        }
        final boolean zza = zzam.zza(context, AnalyticsService.class);
        AnalyticsService.zzKe = zza;
        return zza;
    }
    
    private void zzhr() {
        try {
            synchronized (AnalyticsReceiver.zzpm) {
                final zzqd zzKc = AnalyticsReceiver.zzKc;
                if (zzKc != null && zzKc.isHeld()) {
                    zzKc.release();
                }
            }
        }
        catch (SecurityException ex) {}
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        final zzf zzX = zzf.zzX((Context)this);
        final zzaf zzie = zzX.zzie();
        if (zzX.zzif().zzjk()) {
            zzie.zzaY("Device AnalyticsService is starting up");
            return;
        }
        zzie.zzaY("Local AnalyticsService is starting up");
    }
    
    public void onDestroy() {
        final zzf zzX = zzf.zzX((Context)this);
        final zzaf zzie = zzX.zzie();
        if (zzX.zzif().zzjk()) {
            zzie.zzaY("Device AnalyticsService is shutting down");
        }
        else {
            zzie.zzaY("Local AnalyticsService is shutting down");
        }
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.zzhr();
        final zzf zzX = zzf.zzX((Context)this);
        final zzaf zzie = zzX.zzie();
        final String action = intent.getAction();
        if (zzX.zzif().zzjk()) {
            zzie.zza("Device AnalyticsService called. startId, action", n2, action);
        }
        else {
            zzie.zza("Local AnalyticsService called. startId, action", n2, action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzX.zzhz().zza(new AnalyticsService$1(this, n2, zzX, zzie));
        }
        return 2;
    }
}
