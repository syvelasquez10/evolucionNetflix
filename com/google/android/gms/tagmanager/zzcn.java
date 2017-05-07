// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.content.Context;

class zzcn implements zzp$zzf
{
    private final Context mContext;
    private final String zzaOS;
    private final ExecutorService zzaRv;
    
    zzcn(final Context mContext, final String zzaOS) {
        this.mContext = mContext;
        this.zzaOS = zzaOS;
        this.zzaRv = Executors.newSingleThreadExecutor();
    }
    
    @Override
    public void release() {
        synchronized (this) {
            this.zzaRv.shutdown();
        }
    }
}
