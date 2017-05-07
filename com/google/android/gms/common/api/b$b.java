// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.DeadObjectException;
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
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Queue;
import com.google.android.gms.common.internal.e;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class b$b extends Handler
{
    final /* synthetic */ b Jj;
    
    b$b(final b jj, final Looper looper) {
        this.Jj = jj;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 1) {
            this.Jj.IO.lock();
            try {
                if (this.Jj.isConnected() || this.Jj.isConnecting() || !this.Jj.gp()) {
                    return;
                }
                this.Jj.IW--;
                this.Jj.connect();
                return;
            }
            finally {
                this.Jj.IO.unlock();
            }
        }
        Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
    }
}
