// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import java.util.List;
import com.google.android.gms.internal.zzqp$zza;
import java.util.Set;
import com.google.android.gms.internal.zzqp$zze;
import java.util.Map;

class zzcp$3 implements zzcp$zza
{
    final /* synthetic */ zzcp zzaRK;
    final /* synthetic */ Map zzaRL;
    final /* synthetic */ Map zzaRM;
    final /* synthetic */ Map zzaRN;
    final /* synthetic */ Map zzaRO;
    
    zzcp$3(final zzcp zzaRK, final Map zzaRL, final Map zzaRM, final Map zzaRN, final Map zzaRO) {
        this.zzaRK = zzaRK;
        this.zzaRL = zzaRL;
        this.zzaRM = zzaRM;
        this.zzaRN = zzaRN;
        this.zzaRO = zzaRO;
    }
    
    @Override
    public void zza(final zzqp$zze zzqp$zze, final Set<zzqp$zza> set, final Set<zzqp$zza> set2, final zzck zzck) {
        final List<? extends zzqp$zza> list = this.zzaRL.get(zzqp$zze);
        final List<String> list2 = this.zzaRM.get(zzqp$zze);
        if (list != null) {
            set.addAll(list);
            zzck.zzAk().zzc((List<zzqp$zza>)list, list2);
        }
        final List<? extends zzqp$zza> list3 = this.zzaRN.get(zzqp$zze);
        final List<String> list4 = this.zzaRO.get(zzqp$zze);
        if (list3 != null) {
            set2.addAll(list3);
            zzck.zzAl().zzc((List<zzqp$zza>)list3, list4);
        }
    }
}
