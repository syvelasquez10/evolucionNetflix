// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import com.google.android.gms.ads.internal.zze;
import java.util.Map;

@zzgr
public class zzdq implements zzdk
{
    static final Map<String, Integer> zzxS;
    private final zze zzxQ;
    private final zzfc zzxR;
    
    static {
        (zzxS = new HashMap<String, Integer>()).put("resize", 1);
        zzdq.zzxS.put("playVideo", 2);
        zzdq.zzxS.put("storePicture", 3);
        zzdq.zzxS.put("createCalendarEvent", 4);
        zzdq.zzxS.put("setOrientationProperties", 5);
        zzdq.zzxS.put("closeResizedAd", 6);
    }
    
    public zzdq(final zze zzxQ, final zzfc zzxR) {
        this.zzxQ = zzxQ;
        this.zzxR = zzxR;
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final int intValue = zzdq.zzxS.get(map.get("a"));
        if (intValue != 5 && this.zzxQ != null && !this.zzxQ.zzbe()) {
            this.zzxQ.zzp(null);
            return;
        }
        switch (intValue) {
            default: {
                zzb.zzaG("Unknown MRAID command called.");
            }
            case 1: {
                this.zzxR.zzg(map);
            }
            case 4: {
                new zzfb(zziz, map).execute();
            }
            case 3: {
                new zzfe(zziz, map).execute();
            }
            case 5: {
                new zzfd(zziz, map).execute();
            }
            case 6: {
                this.zzxR.zzn(true);
            }
        }
    }
}
