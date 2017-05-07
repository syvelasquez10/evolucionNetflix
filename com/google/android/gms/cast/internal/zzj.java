// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import android.os.IInterface;

public interface zzj extends IInterface
{
    void onApplicationDisconnected(final int p0);
    
    void zza(final ApplicationMetadata p0, final String p1, final String p2, final boolean p3);
    
    void zza(final String p0, final double p1, final boolean p2);
    
    void zza(final String p0, final long p1, final int p2);
    
    void zzb(final ApplicationStatus p0);
    
    void zzb(final DeviceStatus p0);
    
    void zzb(final String p0, final byte[] p1);
    
    void zzbb(final int p0);
    
    void zzbc(final int p0);
    
    void zzbd(final int p0);
    
    void zzbe(final int p0);
    
    void zzd(final String p0, final long p1);
    
    void zzs(final String p0, final String p1);
}
