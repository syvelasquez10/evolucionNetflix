// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zze;
import android.os.Looper;
import android.content.Context;

public interface Api$zza<T extends Api$Client, O>
{
    int getPriority();
    
    T zza(final Context p0, final Looper p1, final zze p2, final O p3, final GoogleApiClient$ConnectionCallbacks p4, final GoogleApiClient$OnConnectionFailedListener p5);
}
