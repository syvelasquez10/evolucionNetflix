// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;

@zzgk
public class zzdp extends zzhq
{
    final zzip zzoL;
    final zzdr zzxr;
    private final String zzxs;
    
    zzdp(final zzip zzoL, final zzdr zzxr, final String zzxs) {
        this.zzoL = zzoL;
        this.zzxr = zzxr;
        this.zzxs = zzxs;
        zzp.zzbK().zza(this);
    }
    
    @Override
    public void zzdG() {
        try {
            this.zzxr.zzZ(this.zzxs);
        }
        finally {
            zzhu.zzHK.post((Runnable)new zzdp$1(this));
        }
    }
}
