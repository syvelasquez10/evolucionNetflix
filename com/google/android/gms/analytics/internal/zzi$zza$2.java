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

class zzi$zza$2 implements Runnable
{
    final /* synthetic */ zzi$zza zzMe;
    final /* synthetic */ ComponentName zzMf;
    
    zzi$zza$2(final zzi$zza zzMe, final ComponentName zzMf) {
        this.zzMe = zzMe;
        this.zzMf = zzMf;
    }
    
    @Override
    public void run() {
        this.zzMe.zzMa.onServiceDisconnected(this.zzMf);
    }
}
