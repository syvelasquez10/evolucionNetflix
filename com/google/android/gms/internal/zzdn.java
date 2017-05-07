// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import android.net.Uri;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.gms.ads.internal.zzp;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.ads.internal.zze;

@zzgk
public final class zzdn implements zzdg
{
    private final zze zzxj;
    private final zzew zzxk;
    private final zzdi zzxm;
    
    public zzdn(final zzdi zzxm, final zze zzxj, final zzew zzxk) {
        this.zzxm = zzxm;
        this.zzxj = zzxj;
        this.zzxk = zzxk;
    }
    
    private static void zza(final Context context, final Map<String, String> map) {
        if (TextUtils.isEmpty((CharSequence)map.get("u"))) {
            zzb.zzaE("Destination url cannot be empty.");
            return;
        }
        final Intent zzb = new zzdn$zzb().zzb(context, map);
        try {
            context.startActivity(zzb);
        }
        catch (ActivityNotFoundException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzaE(ex.getMessage());
        }
    }
    
    private static void zzb(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("u");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzaE("Destination url cannot be empty.");
            return;
        }
        new zzdn$zza(zzip, s).zzgn();
    }
    
    private static boolean zzc(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int zzd(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return zzp.zzbz().zzgw();
            }
            if ("l".equalsIgnoreCase(s)) {
                return zzp.zzbz().zzgv();
            }
            if ("c".equalsIgnoreCase(s)) {
                return zzp.zzbz().zzgx();
            }
        }
        return -1;
    }
    
    private void zzm(final boolean b) {
        if (this.zzxk != null) {
            this.zzxk.zzn(b);
        }
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            zzb.zzaE("Action missing from an open GMSG.");
        }
        else {
            if (this.zzxj != null && !this.zzxj.zzbe()) {
                this.zzxj.zzp(map.get("u"));
                return;
            }
            final zziq zzgS = zzip.zzgS();
            if ("expand".equalsIgnoreCase(s)) {
                if (zzip.zzgW()) {
                    zzb.zzaE("Cannot expand WebView that is already expanded.");
                    return;
                }
                this.zzm(false);
                zzgS.zza(zzc(map), zzd(map));
            }
            else if ("webapp".equalsIgnoreCase(s)) {
                final String s2 = map.get("u");
                this.zzm(false);
                if (s2 != null) {
                    zzgS.zza(zzc(map), zzd(map), s2);
                    return;
                }
                zzgS.zza(zzc(map), zzd(map), map.get("html"), map.get("baseurl"));
            }
            else if ("in_app_purchase".equalsIgnoreCase(s)) {
                final String s3 = map.get("product_id");
                final String s4 = map.get("report_urls");
                if (this.zzxm != null) {
                    if (s4 != null && !s4.isEmpty()) {
                        this.zzxm.zza(s3, new ArrayList<String>(Arrays.asList(s4.split(" "))));
                        return;
                    }
                    this.zzxm.zza(s3, new ArrayList<String>());
                }
            }
            else {
                if ("app".equalsIgnoreCase(s) && "true".equalsIgnoreCase(map.get("play_store"))) {
                    zzb(zzip, map);
                    return;
                }
                if ("app".equalsIgnoreCase(s) && "true".equalsIgnoreCase(map.get("system_browser"))) {
                    zza(zzip.getContext(), map);
                    return;
                }
                this.zzm(true);
                final zzan zzgU = zzip.zzgU();
                final String s5 = map.get("u");
                String zza;
                if (!TextUtils.isEmpty((CharSequence)s5)) {
                    String zzd = s5;
                    if (zzgU != null) {
                        zzd = s5;
                        if (zzgU.zzb(Uri.parse(s5))) {
                            zzd = zzp.zzbx().zzd(zzip.getContext(), s5, zzip.zzha());
                        }
                    }
                    zza = zzp.zzbx().zza(zzip, zzd);
                }
                else {
                    zza = s5;
                }
                zzgS.zza(new AdLauncherIntentInfoParcel(map.get("i"), zza, map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            }
        }
    }
}
