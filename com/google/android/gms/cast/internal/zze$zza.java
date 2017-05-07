// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;

final class zze$zza implements Cast$ApplicationConnectionResult
{
    private final String zzHP;
    private final Status zzSC;
    private final ApplicationMetadata zzYZ;
    private final String zzZa;
    private final boolean zzZb;
    
    public zze$zza(final Status status) {
        this(status, null, null, null, false);
    }
    
    public zze$zza(final Status zzSC, final ApplicationMetadata zzYZ, final String zzZa, final String zzHP, final boolean zzZb) {
        this.zzSC = zzSC;
        this.zzYZ = zzYZ;
        this.zzZa = zzZa;
        this.zzHP = zzHP;
        this.zzZb = zzZb;
    }
    
    @Override
    public Status getStatus() {
        return this.zzSC;
    }
}
