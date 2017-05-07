// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;

@zzgr
public final class zzdi implements zzdk
{
    private void zzb(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("label");
        final String s2 = map.get("start_label");
        final String s3 = map.get("timestamp");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzaH("No label given for CSI tick.");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s3)) {
            zzb.zzaH("No timestamp given for CSI tick.");
            return;
        }
        try {
            final long zzc = this.zzc(Long.parseLong(s3));
            String s4 = s2;
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s4 = "native:view_load";
            }
            zziz.zzhn().zza(s, s4, zzc);
        }
        catch (NumberFormatException ex) {
            zzb.zzd("Malformed timestamp for CSI tick.", ex);
        }
    }
    
    private long zzc(final long n) {
        return n - zzp.zzbz().currentTimeMillis() + zzp.zzbz().elapsedRealtime();
    }
    
    private void zzc(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("value");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzaH("No value given for CSI experiment.");
            return;
        }
        final zzcg zzdm = zziz.zzhn().zzdm();
        if (zzdm == null) {
            zzb.zzaH("No ticker for WebView, dropping experiment ID.");
            return;
        }
        zzdm.zze("e", s);
    }
    
    private void zzd(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("name");
        final String s2 = map.get("value");
        if (TextUtils.isEmpty((CharSequence)s2)) {
            zzb.zzaH("No value given for CSI extra.");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzaH("No name given for CSI extra.");
            return;
        }
        final zzcg zzdm = zziz.zzhn().zzdm();
        if (zzdm == null) {
            zzb.zzaH("No ticker for WebView, dropping extra parameter.");
            return;
        }
        zzdm.zze(s, s2);
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("action");
        if ("tick".equals(s)) {
            this.zzb(zziz, map);
        }
        else {
            if ("experiment".equals(s)) {
                this.zzc(zziz, map);
                return;
            }
            if ("extra".equals(s)) {
                this.zzd(zziz, map);
            }
        }
    }
}
