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

class zzi$1 extends zzt
{
    final /* synthetic */ zzi zzMa;
    
    zzi$1(final zzi zzMa, final zzf zzf) {
        this.zzMa = zzMa;
        super(zzf);
    }
    
    @Override
    public void run() {
        this.zzMa.zziC();
    }
}
