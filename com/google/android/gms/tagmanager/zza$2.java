// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;

class zza$2 implements Runnable
{
    final /* synthetic */ zza zzaOH;
    
    zza$2(final zza zzaOH) {
        this.zzaOH = zzaOH;
    }
    
    @Override
    public void run() {
        this.zzaOH.zzzu();
    }
}
