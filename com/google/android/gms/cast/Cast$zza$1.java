// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

class Cast$zza$1 implements Cast$ApplicationConnectionResult
{
    final /* synthetic */ Status zzVb;
    final /* synthetic */ Cast$zza zzVc;
    
    Cast$zza$1(final Cast$zza zzVc, final Status zzVb) {
        this.zzVc = zzVc;
        this.zzVb = zzVb;
    }
    
    @Override
    public Status getStatus() {
        return this.zzVb;
    }
}
