// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import com.google.android.gms.common.internal.ClientSettings;
import android.content.Context;
import com.google.android.gms.common.internal.e$b;
import java.util.Set;
import java.util.List;
import java.util.Map;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import java.util.Queue;
import com.google.android.gms.common.internal.e;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import android.os.Looper;
import android.os.Bundle;

class b$2 implements GoogleApiClient$ConnectionCallbacks
{
    final /* synthetic */ b Jj;
    
    b$2(final b jj) {
        this.Jj = jj;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.Jj.IO.lock();
        try {
            if (this.Jj.IV == 1) {
                if (bundle != null) {
                    this.Jj.Jb.putAll(bundle);
                }
                this.Jj.gn();
            }
        }
        finally {
            this.Jj.IO.unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        while (true) {
            this.Jj.IO.lock();
            Label_0082: {
                try {
                    this.Jj.aj(n);
                    switch (n) {
                        case 2: {
                            this.Jj.connect();
                            break;
                        }
                        case 1: {
                            break Label_0082;
                        }
                    }
                    return;
                }
                finally {
                    this.Jj.IO.unlock();
                }
            }
            if (this.Jj.gp()) {
                break;
            }
            this.Jj.IW = 2;
            this.Jj.Ja.sendMessageDelayed(this.Jj.Ja.obtainMessage(1), this.Jj.IZ);
            return;
        }
        this.Jj.IO.unlock();
    }
}
