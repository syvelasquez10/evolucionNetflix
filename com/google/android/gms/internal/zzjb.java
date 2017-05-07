// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

@zzgr
public class zzjb
{
    public zziz zza(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, final zzan zzan, final VersionInfoParcel versionInfoParcel) {
        return this.zza(context, adSizeParcel, b, b2, zzan, versionInfoParcel, null, null);
    }
    
    public zziz zza(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, final zzan zzan, final VersionInfoParcel versionInfoParcel, final zzcg zzcg, final zzd zzd) {
        final zzjc zzjc = new zzjc(zzjd.zzb(context, adSizeParcel, b, b2, zzan, versionInfoParcel, zzcg, zzd));
        zzjc.setWebViewClient(zzp.zzbx().zzb(zzjc, b2));
        zzjc.setWebChromeClient(zzp.zzbx().zzf(zzjc));
        return zzjc;
    }
}
