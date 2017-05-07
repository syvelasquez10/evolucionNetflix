// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdj$10 implements zzdk
{
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("tx");
        final String s2 = map.get("ty");
        final String s3 = map.get("td");
        try {
            final int int1 = Integer.parseInt(s);
            final int int2 = Integer.parseInt(s2);
            final int int3 = Integer.parseInt(s3);
            final zzan zzhg = zziz.zzhg();
            if (zzhg != null) {
                zzhg.zzab().zza(int1, int2, int3);
            }
        }
        catch (NumberFormatException ex) {
            zzb.zzaH("Could not parse touch parameters from gmsg.");
        }
    }
}
