// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;

class zza$2 extends zza$zza
{
    final /* synthetic */ PendingIntent zzaEY;
    final /* synthetic */ zza zzaEZ;
    
    zza$2(final zza zzaEZ, final GoogleApiClient googleApiClient, final PendingIntent zzaEY) {
        this.zzaEZ = zzaEZ;
        this.zzaEY = zzaEY;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zzl zzl) {
        zzl.zza(this.zzaEY);
        this.zzb((R)Status.zzabb);
    }
}
