// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class gi$1 implements Callable<Void>
{
    final /* synthetic */ Runnable wj;
    
    gi$1(final Runnable wj) {
        this.wj = wj;
    }
    
    public Void dk() {
        this.wj.run();
        return null;
    }
}
