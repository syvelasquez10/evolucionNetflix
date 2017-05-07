// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

@zzgk
public class zzds implements zzdg
{
    @Override
    public void zza(final zzip zzip, Map<String, String> s) {
        final zzdq zzbK = zzp.zzbK();
        if (((Map)s).containsKey("abort")) {
            if (!zzbK.zza(zzip)) {
                zzb.zzaE("Precache abort but no preload task running.");
            }
            return;
        }
        final String s2 = ((Map<String, String>)s).get("src");
        if (s2 == null) {
            zzb.zzaE("Precache video action is missing the src parameter.");
            return;
        }
        int int1 = 0;
    Label_0093_Outer:
        while (true) {
            while (true) {
                while (true) {
                    try {
                        int1 = Integer.parseInt(((Map<String, String>)s).get((Object)"player"));
                        if (((Map)s).containsKey("mimetype")) {
                            s = ((Map<String, String>)s).get("mimetype");
                            if (zzbK.zzb(zzip)) {
                                zzb.zzaE("Precache task already running.");
                                return;
                            }
                            break;
                        }
                    }
                    catch (NumberFormatException ex) {
                        int1 = 0;
                        continue Label_0093_Outer;
                    }
                    break;
                }
                s = "";
                continue;
            }
        }
        com.google.android.gms.common.internal.zzb.zzr(zzip.zzgP());
        new zzdp(zzip, zzip.zzgP().zzoF.zza(zzip, int1, s), s2).zzgn();
    }
}
