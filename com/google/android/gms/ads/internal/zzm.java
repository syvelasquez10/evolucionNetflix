// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzoj;
import com.google.android.gms.internal.zzqf$zza;
import com.google.android.gms.internal.zzqg;
import java.util.regex.Pattern;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.MobileAdsSettingsParcel;
import com.google.android.gms.ads.purchase.InAppPurchaseActivity;
import com.google.android.gms.ads.AdActivity;
import android.app.Activity;
import com.google.android.gms.internal.zzoq;
import com.google.android.gms.internal.zzqf;
import android.content.Context;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.internal.zzqg$zza;
import com.google.android.gms.internal.zzoj$zza;
import com.google.android.gms.ads.internal.client.zzv$zza;

@zzgk
public class zzm extends zzv$zza implements zzoj$zza, zzqg$zza
{
    private static final Object zzpm;
    private static zzm zzpn;
    private final Context mContext;
    zzqf zzpo;
    String zzpp;
    String zzpq;
    private boolean zzpr;
    private boolean zzps;
    
    static {
        zzpm = new Object();
    }
    
    zzm(final Context mContext) {
        this.mContext = mContext;
        this.zzpr = false;
    }
    
    public static zzm zzq(final Context context) {
        synchronized (zzm.zzpm) {
            if (zzm.zzpn == null) {
                zzm.zzpn = new zzm(context.getApplicationContext());
            }
            return zzm.zzpn;
        }
    }
    
    @Override
    public void zza(final zzoq zzoq) {
    }
    
    @Override
    public void zza(final zzoq zzoq, final Activity activity) {
        if (zzoq != null && activity != null) {
            if (activity instanceof AdActivity) {
                final int zzk = zzp.zzbx().zzk(activity);
                if (zzk == 1) {
                    zzoq.zzam(true);
                    zzoq.setScreenName("Interstitial Ad");
                    return;
                }
                if (zzk == 2 || zzk == 3) {
                    zzoq.setScreenName("Expanded Ad");
                    return;
                }
                zzoq.setScreenName(null);
            }
            else if (activity instanceof InAppPurchaseActivity) {
                zzoq.setScreenName(null);
            }
        }
    }
    
    public void zza(final String s, final MobileAdsSettingsParcel mobileAdsSettingsParcel) {
        synchronized (zzm.zzpm) {
            if (this.zzpr) {
                zzb.zzaE("Mobile ads is initialized already.");
                return;
            }
            if (this.mContext == null) {
                zzb.zzaE("Fail to initialize mobile ads because context is null.");
                return;
            }
        }
        final String s2;
        if (TextUtils.isEmpty((CharSequence)s2)) {
            zzb.zzaE("Fail to initialize mobile ads because ApplicationCode is empty.");
            // monitorexit(o)
            return;
        }
        this.zzpr = true;
        this.zzb(s2, mobileAdsSettingsParcel);
    }
    // monitorexit(o)
    
    void zzb(final String zzpp, final MobileAdsSettingsParcel mobileAdsSettingsParcel) {
        if (mobileAdsSettingsParcel != null && mobileAdsSettingsParcel.zzty) {
            if (!zzp.zzbx().zza(this.mContext.getPackageManager(), this.mContext.getPackageName(), "android.permission.INTERNET")) {
                zzb.e("Missing permission android.permission.INTERNET");
            }
            else {
                if (!zzp.zzbx().zza(this.mContext.getPackageManager(), this.mContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
                    zzb.e("Missing permission android.permission.ACCESS_NETWORK_STATE");
                    return;
                }
                if (!Pattern.matches("ca-app-[a-z0-9_-]+~[a-z0-9_-]+", zzpp)) {
                    throw new IllegalArgumentException("Please provide a valid application code");
                }
                this.zzps = true;
                this.zzpp = zzpp;
                this.zzpq = mobileAdsSettingsParcel.zztz;
                final zzqg zzaR = zzqg.zzaR(this.mContext);
                final zzqf$zza zzqf$zza = new zzqf$zza(this.zzpp);
                if (!TextUtils.isEmpty((CharSequence)this.zzpq)) {
                    zzqf$zza.zzfh(this.zzpq);
                }
                zzaR.zza(zzqf$zza.zzBm());
                zzaR.zza(this);
                zzoj.zzaJ(this.mContext).zza(this);
                zzaR.start();
            }
        }
    }
    
    public boolean zzbn() {
        synchronized (zzm.zzpm) {
            return this.zzps;
        }
    }
    
    @Override
    public void zzbo() {
        this.zzpo = zzqg.zzaR(this.mContext).zzBn();
    }
    
    public int zzbp() {
        synchronized (zzm.zzpm) {
            if (!this.zzps) {
                return -1;
            }
            final zzoq zzxw = zzoj.zzaJ(this.mContext).zzxw();
            if (zzxw != null) {
                return zzxw.zzbp();
            }
        }
        // monitorexit(o)
        return -1;
    }
}
