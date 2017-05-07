// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;

class zzof$zzc extends Thread
{
    zzof$zzc(final Runnable runnable, final String s) {
        super(runnable, s);
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        super.run();
    }
}
