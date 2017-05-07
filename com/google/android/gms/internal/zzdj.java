// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

@zzgk
public class zzdj implements zzdg
{
    private final zzdk zzxh;
    
    public zzdj(final zzdk zzxh) {
        this.zzxh = zzxh;
    }
    
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        this.zzxh.zzd("1".equals(map.get("transparentBackground")));
    }
}
