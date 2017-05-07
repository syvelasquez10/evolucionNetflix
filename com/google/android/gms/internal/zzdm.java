// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import com.google.android.gms.ads.internal.zze;
import java.util.Map;

@zzgk
public class zzdm implements zzdg
{
    static final Map<String, Integer> zzxl;
    private final zze zzxj;
    private final zzew zzxk;
    
    static {
        (zzxl = new HashMap<String, Integer>()).put("resize", 1);
        zzdm.zzxl.put("playVideo", 2);
        zzdm.zzxl.put("storePicture", 3);
        zzdm.zzxl.put("createCalendarEvent", 4);
        zzdm.zzxl.put("setOrientationProperties", 5);
        zzdm.zzxl.put("closeResizedAd", 6);
    }
    
    public zzdm(final zze zzxj, final zzew zzxk) {
        this.zzxj = zzxj;
        this.zzxk = zzxk;
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final int intValue = zzdm.zzxl.get(map.get("a"));
        if (intValue != 5 && this.zzxj != null && !this.zzxj.zzbe()) {
            this.zzxj.zzp(null);
            return;
        }
        switch (intValue) {
            default: {
                zzb.zzaD("Unknown MRAID command called.");
            }
            case 1: {
                this.zzxk.zzg(map);
            }
            case 4: {
                new zzev(zzip, map).execute();
            }
            case 3: {
                new zzey(zzip, map).execute();
            }
            case 5: {
                new zzex(zzip, map).execute();
            }
            case 6: {
                this.zzxk.zzn(true);
            }
        }
    }
}
