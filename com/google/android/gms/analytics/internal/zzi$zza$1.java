// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.List;
import android.os.RemoteException;
import java.util.Map;
import java.util.Collections;
import com.google.android.gms.common.internal.zzx;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.zzb;
import android.content.ComponentName;

class zzi$zza$1 implements Runnable
{
    final /* synthetic */ zzac zzMd;
    final /* synthetic */ zzi$zza zzMe;
    
    zzi$zza$1(final zzi$zza zzMe, final zzac zzMd) {
        this.zzMe = zzMe;
        this.zzMd = zzMd;
    }
    
    @Override
    public void run() {
        if (!this.zzMe.zzMa.isConnected()) {
            this.zzMe.zzMa.zzaZ("Connected to service after a timeout");
            this.zzMe.zzMa.zza(this.zzMd);
        }
    }
}
