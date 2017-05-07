// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class zzcm$1 implements zzcm$zzb
{
    final /* synthetic */ zzcm zzaRu;
    
    zzcm$1(final zzcm zzaRu) {
        this.zzaRu = zzaRu;
    }
    
    @Override
    public ScheduledExecutorService zzAC() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
