// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzic$2 implements Callable<Void>
{
    final /* synthetic */ Runnable zzIt;
    
    zzic$2(final Runnable zzIt) {
        this.zzIt = zzIt;
    }
    
    public Void zzgA() {
        this.zzIt.run();
        return null;
    }
}
