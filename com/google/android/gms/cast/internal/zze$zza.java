// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;

final class zze$zza implements Cast$ApplicationConnectionResult
{
    private final String zzGY;
    private final Status zzQA;
    private final ApplicationMetadata zzXh;
    private final String zzXi;
    private final boolean zzXj;
    
    public zze$zza(final Status status) {
        this(status, null, null, null, false);
    }
    
    public zze$zza(final Status zzQA, final ApplicationMetadata zzXh, final String zzXi, final String zzGY, final boolean zzXj) {
        this.zzQA = zzQA;
        this.zzXh = zzXh;
        this.zzXi = zzXi;
        this.zzGY = zzGY;
        this.zzXj = zzXj;
    }
    
    @Override
    public Status getStatus() {
        return this.zzQA;
    }
}
