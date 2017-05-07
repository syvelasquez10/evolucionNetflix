// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

class Cast$zza$1 implements Cast$ApplicationConnectionResult
{
    final /* synthetic */ Status zzOl;
    final /* synthetic */ Cast$zza zzQJ;
    
    Cast$zza$1(final Cast$zza zzQJ, final Status zzOl) {
        this.zzQJ = zzQJ;
        this.zzOl = zzOl;
    }
    
    @Override
    public Status getStatus() {
        return this.zzOl;
    }
}
