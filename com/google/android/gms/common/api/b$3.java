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
import com.google.android.gms.common.internal.e$b;

class b$3 implements e$b
{
    final /* synthetic */ b Jj;
    
    b$3(final b jj) {
        this.Jj = jj;
    }
    
    @Override
    public Bundle fD() {
        return null;
    }
    
    @Override
    public boolean gr() {
        return this.Jj.Je;
    }
    
    @Override
    public boolean isConnected() {
        return this.Jj.isConnected();
    }
}
