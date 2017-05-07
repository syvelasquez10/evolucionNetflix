// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdf$8 implements zzdg
{
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("u");
        if (s == null) {
            zzb.zzaE("URL missing from httpTrack GMSG.");
            return;
        }
        new zzia(zzip.getContext(), zzip.zzgV().zzIz, s).zzgn();
    }
}
