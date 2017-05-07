// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

final class d$f implements ServiceConnection
{
    final /* synthetic */ d Lx;
    
    d$f(final d lx) {
        this.Lx = lx;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.Lx.N(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.Lx.mHandler.sendMessage(this.Lx.mHandler.obtainMessage(4, (Object)1));
    }
}
