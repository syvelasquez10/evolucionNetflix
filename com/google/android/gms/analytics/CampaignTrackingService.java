// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzaf;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzf;
import android.os.IBinder;
import android.content.Intent;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import android.os.Handler;
import android.app.Service;

public class CampaignTrackingService extends Service
{
    private static Boolean zzKe;
    private Handler mHandler;
    
    private Handler getHandler() {
        Handler mHandler;
        if ((mHandler = this.mHandler) == null) {
            mHandler = new Handler(this.getMainLooper());
            this.mHandler = mHandler;
        }
        return mHandler;
    }
    
    public static boolean zzW(final Context context) {
        zzx.zzv(context);
        if (CampaignTrackingService.zzKe != null) {
            return CampaignTrackingService.zzKe;
        }
        final boolean zza = zzam.zza(context, CampaignTrackingService.class);
        CampaignTrackingService.zzKe = zza;
        return zza;
    }
    
    private void zzhr() {
        try {
            synchronized (CampaignTrackingReceiver.zzpm) {
                final zzqd zzKc = CampaignTrackingReceiver.zzKc;
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
        zzf.zzX((Context)this).zzie().zzaY("CampaignTrackingService is starting up");
    }
    
    public void onDestroy() {
        zzf.zzX((Context)this).zzie().zzaY("CampaignTrackingService is shutting down");
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, int zzjo, final int n) {
        this.zzhr();
        final zzf zzX = zzf.zzX((Context)this);
        final zzaf zzie = zzX.zzie();
        final String s = null;
        String s2;
        if (zzX.zzif().zzjk()) {
            zzie.zzbc("Unexpected installation campaign (package side)");
            s2 = s;
        }
        else {
            s2 = intent.getStringExtra("referrer");
        }
        final Handler handler = this.getHandler();
        if (TextUtils.isEmpty((CharSequence)s2)) {
            if (!zzX.zzif().zzjk()) {
                zzie.zzbb("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            }
            zzX.zzig().zzf(new CampaignTrackingService$1(this, zzie, handler, n));
            return 2;
        }
        zzjo = zzX.zzif().zzjo();
        if (s2.length() > zzjo) {
            zzie.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", s2.length(), zzjo);
            s2 = s2.substring(0, zzjo);
        }
        zzie.zza("CampaignTrackingService called. startId, campaign", n, s2);
        zzX.zzhz().zza(s2, new CampaignTrackingService$2(this, zzie, handler, n));
        return 2;
    }
    
    protected void zza(final zzaf zzaf, final Handler handler, final int n) {
        handler.post((Runnable)new CampaignTrackingService$3(this, n, zzaf));
    }
}
