// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdj$6 implements zzdk
{
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final zzd zzhc = zziz.zzhc();
        if (zzhc != null) {
            zzhc.close();
            return;
        }
        final zzd zzhd = zziz.zzhd();
        if (zzhd != null) {
            zzhd.close();
            return;
        }
        zzb.zzaH("A GMSG tried to close something that wasn't an overlay.");
    }
}
