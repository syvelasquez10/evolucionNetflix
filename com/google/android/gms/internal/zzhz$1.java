// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

class zzhz$1 implements Runnable
{
    final /* synthetic */ zzhz zzIn;
    
    zzhz$1(final zzhz zzIn) {
        this.zzIn = zzIn;
    }
    
    @Override
    public final void run() {
        this.zzIn.zzIl = Thread.currentThread();
        this.zzIn.zzbn();
    }
}
