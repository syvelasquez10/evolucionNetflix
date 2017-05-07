// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzaf;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzf;
import android.content.Intent;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import com.google.android.gms.internal.zzqd;
import android.content.BroadcastReceiver;

public class CampaignTrackingReceiver extends BroadcastReceiver
{
    static zzqd zzKc;
    static Boolean zzKd;
    static Object zzpm;
    
    static {
        CampaignTrackingReceiver.zzpm = new Object();
    }
    
    public static boolean zzV(final Context context) {
        zzx.zzv(context);
        if (CampaignTrackingReceiver.zzKd != null) {
            return CampaignTrackingReceiver.zzKd;
        }
        final boolean zza = zzam.zza(context, CampaignTrackingReceiver.class, true);
        CampaignTrackingReceiver.zzKd = zza;
        return zza;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final zzf zzX = zzf.zzX(context);
        final zzaf zzie = zzX.zzie();
        final String stringExtra = intent.getStringExtra("referrer");
        final String action = intent.getAction();
        zzie.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty((CharSequence)stringExtra)) {
            zzie.zzbb("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        final boolean zzW = CampaignTrackingService.zzW(context);
        if (!zzW) {
            zzie.zzbb("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        this.zzaQ(stringExtra);
        if (zzX.zzif().zzjk()) {
            zzie.zzbc("Received unexpected installation campaign on package side");
            return;
        }
        final Class<? extends CampaignTrackingService> zzht = this.zzht();
        zzx.zzv(zzht);
        final Intent intent2 = new Intent(context, (Class)zzht);
        intent2.putExtra("referrer", stringExtra);
        synchronized (CampaignTrackingReceiver.zzpm) {
            context.startService(intent2);
            if (!zzW) {
                return;
            }
        }
        while (true) {
            try {
                if (CampaignTrackingReceiver.zzKc == null) {
                    final Context context2;
                    (CampaignTrackingReceiver.zzKc = new zzqd(context2, 1, "Analytics campaign WakeLock")).setReferenceCounted(false);
                }
                CampaignTrackingReceiver.zzKc.acquire(1000L);
            }
            // monitorexit(intent)
            catch (SecurityException ex) {
                zzie.zzbb("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                continue;
            }
            break;
        }
    }
    
    protected void zzaQ(final String s) {
    }
    
    protected Class<? extends CampaignTrackingService> zzht() {
        return CampaignTrackingService.class;
    }
}
