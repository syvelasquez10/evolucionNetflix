// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.os.Bundle;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzx;
import android.app.Activity;
import java.util.Map;
import android.app.Application$ActivityLifecycleCallbacks;

public final class zzot implements Application$ActivityLifecycleCallbacks
{
    private final zzoj zzaIR;
    private final Map<Activity, zzoq> zzaIS;
    
    public zzot(final zzoj zzaIR) {
        zzx.zzv(zzaIR);
        this.zzaIR = zzaIR;
        this.zzaIS = new HashMap<Activity, zzoq>();
    }
    
    public void onActivityCreated(final Activity activity, Bundle bundle) {
        if (bundle != null) {
            bundle = bundle.getBundle("com.google.android.gms.measurement.screen_view");
            if (bundle != null) {
                final int int1 = bundle.getInt("id");
                if (int1 <= 0) {
                    Log.w("com.google.android.gms.measurement.internal.ActivityLifecycleTracker", "Invalid screenId in saved activity state");
                    return;
                }
                final zzoq zza = this.zza(activity, int1);
                zza.setScreenName(bundle.getString("name"));
                zza.zzhT(bundle.getInt("referrer_id"));
                zza.zzdU(bundle.getString("referrer_name"));
                zza.zzam(bundle.getBoolean("interstitial"));
                zza.zzxX();
            }
        }
    }
    
    public void onActivityDestroyed(final Activity activity) {
        this.zzaIS.remove(activity);
    }
    
    public void onActivityPaused(final Activity activity) {
    }
    
    public void onActivityResumed(final Activity activity) {
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        if (bundle != null) {
            final zzoq zzoq = this.zzaIS.get(activity);
            if (zzoq != null) {
                final Bundle bundle2 = new Bundle();
                bundle2.putInt("id", zzoq.zzbp());
                bundle2.putString("name", zzoq.zzxT());
                bundle2.putInt("referrer_id", zzoq.zzxU());
                bundle2.putString("referrer_name", zzoq.zzxV());
                bundle2.putBoolean("interstitial", zzoq.zzxY());
                bundle.putBundle("com.google.android.gms.measurement.screen_view", bundle2);
            }
        }
    }
    
    public void onActivityStarted(final Activity activity) {
        this.zzaIR.zzb(this.zza(activity, 0), activity);
    }
    
    public void onActivityStopped(final Activity activity) {
    }
    
    zzoq zza(final Activity activity, final int n) {
        zzx.zzv(activity);
        zzoq zzoq;
        if ((zzoq = this.zzaIS.get(activity)) == null) {
            if (n == 0) {
                zzoq = new zzoq(true);
            }
            else {
                zzoq = new zzoq(true, n);
            }
            zzoq.setScreenName(activity.getClass().getCanonicalName());
            this.zzaIS.put(activity, zzoq);
        }
        return zzoq;
    }
}
