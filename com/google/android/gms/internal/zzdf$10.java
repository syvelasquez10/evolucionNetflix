// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdf$10 implements zzdg
{
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("tx");
        final String s2 = map.get("ty");
        final String s3 = map.get("td");
        try {
            final int int1 = Integer.parseInt(s);
            final int int2 = Integer.parseInt(s2);
            final int int3 = Integer.parseInt(s3);
            final zzan zzgU = zzip.zzgU();
            if (zzgU != null) {
                zzgU.zzab().zza(int1, int2, int3);
            }
        }
        catch (NumberFormatException ex) {
            zzb.zzaE("Could not parse touch parameters from gmsg.");
        }
    }
}
