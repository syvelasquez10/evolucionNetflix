// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

class zzhq$1 implements Runnable
{
    final /* synthetic */ zzhq zzHu;
    
    zzhq$1(final zzhq zzHu) {
        this.zzHu = zzHu;
    }
    
    @Override
    public final void run() {
        this.zzHu.zzHt = Thread.currentThread();
        this.zzHu.zzdG();
    }
}
