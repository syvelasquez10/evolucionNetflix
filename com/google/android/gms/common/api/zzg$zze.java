// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

interface zzg$zze<A extends Api$Client>
{
    void cancel();
    
    void forceFailureUnlessReady(final Status p0);
    
    void zza(final zzg$zzc p0);
    
    void zzb(final A p0);
    
    Api$ClientKey<A> zzmq();
    
    int zzmt();
    
    void zzr(final Status p0);
}
