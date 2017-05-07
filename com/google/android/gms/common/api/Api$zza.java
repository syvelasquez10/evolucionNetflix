// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;

public abstract class Api$zza<T extends Api$zzb, O>
{
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    
    public abstract T zza(final Context p0, final Looper p1, final zzf p2, final O p3, final GoogleApiClient$ConnectionCallbacks p4, final GoogleApiClient$OnConnectionFailedListener p5);
    
    public List<Scope> zzm(final O o) {
        return Collections.emptyList();
    }
}
