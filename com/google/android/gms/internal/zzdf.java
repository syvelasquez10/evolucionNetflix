// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgr
public final class zzdf implements zzdk
{
    private final zzdg zzxn;
    
    public zzdf(final zzdg zzxn) {
        this.zzxn = zzxn;
    }
    
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            zzb.zzaH("App event with no name parameter.");
            return;
        }
        this.zzxn.onAppEvent(s, map.get("info"));
    }
}
