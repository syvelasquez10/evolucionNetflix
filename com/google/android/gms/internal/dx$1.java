// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.content.ComponentName;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.Iterator;
import java.util.HashMap;
import android.os.SystemClock;
import java.util.List;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.content.Intent;

class dx$1 implements Runnable
{
    final /* synthetic */ ea sr;
    final /* synthetic */ Intent ss;
    final /* synthetic */ dx st;
    
    dx$1(final dx st, final ea sr, final Intent ss) {
        this.st = st;
        this.sr = sr;
        this.ss = ss;
    }
    
    @Override
    public void run() {
        try {
            if (this.st.sq.a(this.sr.sB, -1, this.ss)) {
                this.st.sm.a(new eb(this.st.mContext, this.sr.sC, true, -1, this.ss, this.sr));
                return;
            }
            this.st.sm.a(new eb(this.st.mContext, this.sr.sC, false, -1, this.ss, this.sr));
        }
        catch (RemoteException ex) {
            gs.W("Fail to verify and dispatch pending transaction");
        }
    }
}
