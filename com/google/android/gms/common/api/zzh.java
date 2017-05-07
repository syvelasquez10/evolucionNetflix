// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;

public interface zzh
{
    void begin();
    
    void connect();
    
    String getName();
    
    void onConnected(final Bundle p0);
    
    void onConnectionSuspended(final int p0);
    
     <A extends Api$Client, R extends Result, T extends zza$zza<R, A>> T zza(final T p0);
    
    void zza(final ConnectionResult p0, final Api<?> p1, final int p2);
    
    void zzaV(final int p0);
    
     <A extends Api$Client, T extends zza$zza<? extends Result, A>> T zzb(final T p0);
}
