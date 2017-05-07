// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzht$2 implements Callable<Void>
{
    final /* synthetic */ Runnable zzHA;
    
    zzht$2(final Runnable zzHA) {
        this.zzHA = zzHA;
    }
    
    public Void zzgp() {
        this.zzHA.run();
        return null;
    }
}
