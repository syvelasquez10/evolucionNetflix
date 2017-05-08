// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;

class zza$1 extends zza$zza
{
    final /* synthetic */ long zzaEX;
    final /* synthetic */ PendingIntent zzaEY;
    final /* synthetic */ zza zzaEZ;
    
    zza$1(final zza zzaEZ, final GoogleApiClient googleApiClient, final long zzaEX, final PendingIntent zzaEY) {
        this.zzaEZ = zzaEZ;
        this.zzaEX = zzaEX;
        this.zzaEY = zzaEY;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zzl zzl) {
        zzl.zza(this.zzaEX, this.zzaEY);
        this.zzb((R)Status.zzabb);
    }
}
