// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import com.google.android.gms.internal.zzqp$zza;
import java.util.Set;
import com.google.android.gms.internal.zzqp$zze;

class zzcp$4 implements zzcp$zza
{
    final /* synthetic */ zzcp zzaRK;
    
    zzcp$4(final zzcp zzaRK) {
        this.zzaRK = zzaRK;
    }
    
    @Override
    public void zza(final zzqp$zze zzqp$zze, final Set<zzqp$zza> set, final Set<zzqp$zza> set2, final zzck zzck) {
        set.addAll(zzqp$zze.zzBM());
        set2.addAll(zzqp$zze.zzBN());
        zzck.zzAm().zzc(zzqp$zze.zzBM(), zzqp$zze.zzBR());
        zzck.zzAn().zzc(zzqp$zze.zzBN(), zzqp$zze.zzBS());
    }
}
