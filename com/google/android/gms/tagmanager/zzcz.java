// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.zzag$zza;

abstract class zzcz extends zzca
{
    public zzcz(final String s) {
        super(s);
    }
    
    @Override
    protected boolean zza(final zzag$zza zzag$zza, final zzag$zza zzag$zza2, final Map<String, zzag$zza> map) {
        final String zzg = zzdf.zzg(zzag$zza);
        final String zzg2 = zzdf.zzg(zzag$zza2);
        return zzg != zzdf.zzBf() && zzg2 != zzdf.zzBf() && this.zza(zzg, zzg2, map);
    }
    
    protected abstract boolean zza(final String p0, final String p1, final Map<String, zzag$zza> p2);
}
