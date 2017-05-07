// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

class Cast$zza$1 implements Cast$ApplicationConnectionResult
{
    final /* synthetic */ Status zzQs;
    final /* synthetic */ Cast$zza zzTm;
    
    Cast$zza$1(final Cast$zza zzTm, final Status zzQs) {
        this.zzTm = zzTm;
        this.zzQs = zzQs;
    }
    
    @Override
    public Status getStatus() {
        return this.zzQs;
    }
}
