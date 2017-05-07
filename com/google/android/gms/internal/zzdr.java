// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.ads.internal.zze;

@zzgr
public final class zzdr implements zzdk
{
    private final zze zzxQ;
    private final zzfc zzxR;
    private final zzdm zzxT;
    
    public zzdr(final zzdm zzxT, final zze zzxQ, final zzfc zzxR) {
        this.zzxT = zzxT;
        this.zzxQ = zzxQ;
        this.zzxR = zzxR;
    }
    
    private static void zza(final Context context, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)map.get("u"))) {
            zzb.zzaH("Destination url cannot be empty.");
            return;
        }
        final Intent zzb = new zzdr$zzb().zzb(context, map);
        try {
            zzp.zzbv().zzb(context, zzb);
        }
        catch (ActivityNotFoundException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaH(ex.getMessage());
        }
    }
    
    private static boolean zzc(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int zzd(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return zzp.zzbx().zzgH();
            }
            if ("l".equalsIgnoreCase(s)) {
                return zzp.zzbx().zzgG();
            }
            if ("c".equalsIgnoreCase(s)) {
                return zzp.zzbx().zzgI();
            }
        }
        return -1;
    }
    
    private static void zze(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("u");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzaH("Destination url cannot be empty.");
            return;
        }
        new zzdr$zza(zziz, s).zzgz();
    }
    
    private void zzm(final boolean b) {
        if (this.zzxR != null) {
            this.zzxR.zzn(b);
        }
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            zzb.zzaH("Action missing from an open GMSG.");
        }
        else {
            if (this.zzxQ != null && !this.zzxQ.zzbe()) {
                this.zzxQ.zzp(map.get("u"));
                return;
            }
            final zzja zzhe = zziz.zzhe();
            if ("expand".equalsIgnoreCase(s)) {
                if (zziz.zzhi()) {
                    zzb.zzaH("Cannot expand WebView that is already expanded.");
                    return;
                }
                this.zzm(false);
                zzhe.zza(zzc(map), zzd(map));
            }
            else if ("webapp".equalsIgnoreCase(s)) {
                final String s2 = map.get("u");
                this.zzm(false);
                if (s2 != null) {
                    zzhe.zza(zzc(map), zzd(map), s2);
                    return;
                }
                zzhe.zza(zzc(map), zzd(map), map.get("html"), map.get("baseurl"));
            }
            else if ("in_app_purchase".equalsIgnoreCase(s)) {
                final String s3 = map.get("product_id");
                final String s4 = map.get("report_urls");
                if (this.zzxT != null) {
                    if (s4 != null && !s4.isEmpty()) {
                        this.zzxT.zza(s3, new ArrayList<String>(Arrays.asList(s4.split(" "))));
                        return;
                    }
                    this.zzxT.zza(s3, new ArrayList<String>());
                }
            }
            else {
                if ("app".equalsIgnoreCase(s) && "true".equalsIgnoreCase(map.get("play_store"))) {
                    zze(zziz, map);
                    return;
                }
                if ("app".equalsIgnoreCase(s) && "true".equalsIgnoreCase(map.get("system_browser"))) {
                    zza(zziz.getContext(), map);
                    return;
                }
                this.zzm(true);
                zziz.zzhg();
                final String s5 = map.get("u");
                String zza;
                if (!TextUtils.isEmpty((CharSequence)s5)) {
                    zza = zzp.zzbv().zza(zziz, s5);
                }
                else {
                    zza = s5;
                }
                zzhe.zza(new AdLauncherIntentInfoParcel(map.get("i"), zza, map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            }
        }
    }
}
