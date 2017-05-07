// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

class zze$1 implements Executor
{
    final /* synthetic */ Handler zzt;
    final /* synthetic */ zze zzu;
    
    zze$1(final zze zzu, final Handler zzt) {
        this.zzu = zzu;
        this.zzt = zzt;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        this.zzt.post(runnable);
    }
}
