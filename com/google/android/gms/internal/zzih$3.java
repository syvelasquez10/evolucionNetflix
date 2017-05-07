// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

class zzih$3 extends zzab
{
    final /* synthetic */ zzih zzJc;
    final /* synthetic */ Map zzJd;
    
    zzih$3(final zzih zzJc, final String s, final zzm$zzb zzm$zzb, final zzm$zza zzm$zza, final Map zzJd) {
        this.zzJc = zzJc;
        this.zzJd = zzJd;
        super(s, zzm$zzb, zzm$zza);
    }
    
    @Override
    public Map<String, String> getHeaders() {
        if (this.zzJd == null) {
            return super.getHeaders();
        }
        return (Map<String, String>)this.zzJd;
    }
}
