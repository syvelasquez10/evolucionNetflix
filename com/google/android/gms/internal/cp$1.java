// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.os.SystemClock;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.content.Context;

class cp$1 implements Runnable
{
    final /* synthetic */ co qv;
    final /* synthetic */ cp qw;
    
    cp$1(final cp qw, final co qv) {
        this.qw = qw;
        this.qv = qv;
    }
    
    @Override
    public void run() {
        synchronized (this.qw.mw) {
            if (this.qw.qu != -2) {
                return;
            }
            this.qw.qt = this.qw.bF();
            if (this.qw.qt == null) {
                this.qw.j(4);
                return;
            }
        }
        this.qv.a(this.qw);
        this.qw.a(this.qv);
    }
    // monitorexit(o)
}
