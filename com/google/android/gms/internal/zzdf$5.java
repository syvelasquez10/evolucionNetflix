// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdf$5 implements zzdg
{
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("u");
        if (s == null) {
            zzb.zzaE("URL missing from click GMSG.");
            return;
        }
        final String zzd = zzp.zzbx().zzd(zzip.getContext(), s, zzip.zzha());
        final Uri parse = Uri.parse(zzd);
        while (true) {
            try {
                final zzan zzgU = zzip.zzgU();
                Uri zza = parse;
                if (zzgU != null) {
                    zza = parse;
                    if (zzgU.zzb(parse)) {
                        zza = zzgU.zza(parse, zzip.getContext());
                    }
                }
                new zzia(zzip.getContext(), zzip.zzgV().zzIz, zza.toString()).zzgn();
            }
            catch (zzao zzao) {
                zzb.zzaE("Unable to append parameter to URL: " + zzd);
                final Uri zza = parse;
                continue;
            }
            break;
        }
    }
}
