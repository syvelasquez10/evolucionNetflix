// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdj$8 implements zzdk
{
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("u");
        if (s == null) {
            zzb.zzaH("URL missing from httpTrack GMSG.");
            return;
        }
        new zzij(zziz.getContext(), zziz.zzhh().zzJu, s).zzgz();
    }
}
