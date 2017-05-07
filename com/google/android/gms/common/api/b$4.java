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
import android.os.Bundle;
import android.os.Handler;
import java.util.Queue;
import com.google.android.gms.common.internal.e;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;

class b$4 implements GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ b Jj;
    final /* synthetic */ Api$b Jk;
    
    b$4(final b jj, final Api$b jk) {
        this.Jj = jj;
        this.Jk = jk;
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.Jj.IO.lock();
        try {
            if (this.Jj.IT == null || this.Jk.getPriority() < this.Jj.IU) {
                this.Jj.IT = connectionResult;
                this.Jj.IU = this.Jk.getPriority();
            }
            this.Jj.gn();
        }
        finally {
            this.Jj.IO.unlock();
        }
    }
}
