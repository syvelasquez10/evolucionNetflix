// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.List;
import android.content.Context;
import java.util.concurrent.Future;

class fn$1 implements Runnable
{
    final /* synthetic */ fn tW;
    final /* synthetic */ fz ts;
    
    fn$1(final fn tw, final fz ts) {
        this.tW = tw;
        this.ts = ts;
    }
    
    @Override
    public void run() {
        this.tW.tm.a(this.ts);
    }
}
