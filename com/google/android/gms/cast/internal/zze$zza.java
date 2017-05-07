// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;

final class zze$zza implements Cast$ApplicationConnectionResult
{
    private final String zzFE;
    private final Status zzOt;
    private final ApplicationMetadata zzUE;
    private final String zzUF;
    private final boolean zzUG;
    
    public zze$zza(final Status status) {
        this(status, null, null, null, false);
    }
    
    public zze$zza(final Status zzOt, final ApplicationMetadata zzUE, final String zzUF, final String zzFE, final boolean zzUG) {
        this.zzOt = zzOt;
        this.zzUE = zzUE;
        this.zzUF = zzUF;
        this.zzFE = zzFE;
        this.zzUG = zzUG;
    }
    
    @Override
    public Status getStatus() {
        return this.zzOt;
    }
}
