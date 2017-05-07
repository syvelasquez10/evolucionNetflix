// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

class zzhy$3 extends zzab
{
    final /* synthetic */ zzhy zzIi;
    final /* synthetic */ Map zzIj;
    
    zzhy$3(final zzhy zzIi, final String s, final zzm$zzb zzm$zzb, final zzm$zza zzm$zza, final Map zzIj) {
        this.zzIi = zzIi;
        this.zzIj = zzIj;
        super(s, zzm$zzb, zzm$zza);
    }
    
    @Override
    public Map<String, String> getHeaders() {
        if (this.zzIj == null) {
            return super.getHeaders();
        }
        return (Map<String, String>)this.zzIj;
    }
}
