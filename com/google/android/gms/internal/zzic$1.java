// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzic$1 implements Callable<Void>
{
    final /* synthetic */ Runnable zzIt;
    
    zzic$1(final Runnable zzIt) {
        this.zzIt = zzIt;
    }
    
    public Void zzgA() {
        this.zzIt.run();
        return null;
    }
}
