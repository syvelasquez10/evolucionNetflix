// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.HashSet;
import android.os.Message;
import android.content.Intent;
import android.os.Handler;
import android.content.Context;
import android.os.Handler$Callback;
import java.util.HashMap;
import java.util.Iterator;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public class f$a$a implements ServiceConnection
{
    final /* synthetic */ f$a LU;
    
    public f$a$a(final f$a lu) {
        this.LU = lu;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.LU.LT.LM) {
            this.LU.LR = binder;
            this.LU.LS = componentName;
            final Iterator<d$f> iterator = this.LU.LP.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(componentName, binder);
            }
        }
        this.LU.mState = 1;
    }
    // monitorexit(hashMap)
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (this.LU.LT.LM) {
            this.LU.LR = null;
            this.LU.LS = componentName;
            final Iterator<d$f> iterator = this.LU.LP.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(componentName);
            }
        }
        this.LU.mState = 2;
    }
    // monitorexit(hashMap)
}
