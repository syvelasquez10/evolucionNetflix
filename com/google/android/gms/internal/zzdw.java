// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

@zzgr
public class zzdw implements zzdk
{
    @Override
    public void zza(final zziz zziz, Map<String, String> s) {
        final zzdu zzbI = zzp.zzbI();
        if (((Map)s).containsKey("abort")) {
            if (!zzbI.zza(zziz)) {
                zzb.zzaH("Precache abort but no preload task running.");
            }
            return;
        }
        final String s2 = ((Map<String, String>)s).get("src");
        if (s2 == null) {
            zzb.zzaH("Precache video action is missing the src parameter.");
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
                            if (zzbI.zzb(zziz)) {
                                zzb.zzaH("Precache task already running.");
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
        com.google.android.gms.common.internal.zzb.zzs(zziz.zzhb());
        new zzdt(zziz, zziz.zzhb().zzoG.zza(zziz, int1, s), s2).zzgz();
    }
}
