// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.zzag$zza;

abstract class zzbv extends zzca
{
    public zzbv(final String s) {
        super(s);
    }
    
    @Override
    protected boolean zza(final zzag$zza zzag$zza, final zzag$zza zzag$zza2, final Map<String, zzag$zza> map) {
        final zzde zzh = zzdf.zzh(zzag$zza);
        final zzde zzh2 = zzdf.zzh(zzag$zza2);
        return zzh != zzdf.zzBe() && zzh2 != zzdf.zzBe() && this.zza(zzh, zzh2, map);
    }
    
    protected abstract boolean zza(final zzde p0, final zzde p1, final Map<String, zzag$zza> p2);
}
