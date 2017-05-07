// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgk
public final class zzdc implements zzdg
{
    private final zzdd zzwH;
    
    public zzdc(final zzdd zzwH) {
        this.zzwH = zzwH;
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            zzb.zzaE("App event with no name parameter.");
            return;
        }
        this.zzwH.onAppEvent(s, map.get("info"));
    }
}
