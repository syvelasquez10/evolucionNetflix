// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Message;
import android.app.PendingIntent;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import android.os.DeadObjectException;
import com.google.android.gms.internal.fq;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesClient;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import com.google.android.gms.internal.fc;
import android.content.Context;
import java.util.Set;
import java.util.Map;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import java.util.Queue;
import com.google.android.gms.internal.fg;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import android.os.Looper;

final class b implements GoogleApiClient
{
    private final a AL;
    private final Looper AS;
    private final Lock Ba;
    private final Condition Bb;
    private final fg Bc;
    final Queue<c<?>> Bd;
    private ConnectionResult Be;
    private int Bf;
    private int Bg;
    private int Bh;
    private boolean Bi;
    private int Bj;
    private long Bk;
    final Handler Bl;
    private final Bundle Bm;
    private final Map<Api.c<?>, Api.a> Bn;
    private boolean Bo;
    final Set<c<?>> Bp;
    final ConnectionCallbacks Bq;
    private final fg.b Br;
    
    public b(final Context context, final Looper as, final fc fc, final Map<Api<?>, Api.ApiOptions> map, final Set<ConnectionCallbacks> set, final Set<OnConnectionFailedListener> set2) {
        this.Ba = new ReentrantLock();
        this.Bb = this.Ba.newCondition();
        this.Bd = new LinkedList<c<?>>();
        this.Bg = 4;
        this.Bh = 0;
        this.Bi = false;
        this.Bk = 5000L;
        this.Bm = new Bundle();
        this.Bn = new HashMap<Api.c<?>, Api.a>();
        this.Bp = new HashSet<c<?>>();
        this.AL = (a)new a() {
            @Override
            public void b(final c<?> c) {
                b.this.Ba.lock();
                try {
                    b.this.Bp.remove(c);
                }
                finally {
                    b.this.Ba.unlock();
                }
            }
        };
        this.Bq = new ConnectionCallbacks() {
            @Override
            public void onConnected(final Bundle bundle) {
                b.this.Ba.lock();
                try {
                    if (b.this.Bg == 1) {
                        if (bundle != null) {
                            b.this.Bm.putAll(bundle);
                        }
                        b.this.ei();
                    }
                }
                finally {
                    b.this.Ba.unlock();
                }
            }
            
            @Override
            public void onConnectionSuspended(final int n) {
                while (true) {
                    b.this.Ba.lock();
                    Label_0082: {
                        try {
                            b.this.E(n);
                            switch (n) {
                                case 2: {
                                    b.this.connect();
                                    break;
                                }
                                case 1: {
                                    break Label_0082;
                                }
                            }
                            return;
                        }
                        finally {
                            b.this.Ba.unlock();
                        }
                    }
                    if (b.this.ek()) {
                        break;
                    }
                    b.this.Bh = 2;
                    b.this.Bl.sendMessageDelayed(b.this.Bl.obtainMessage(1), b.this.Bk);
                    return;
                }
                b.this.Ba.unlock();
            }
        };
        this.Br = new fg.b() {
            @Override
            public Bundle dG() {
                return null;
            }
            
            @Override
            public boolean em() {
                return b.this.Bo;
            }
            
            @Override
            public boolean isConnected() {
                return b.this.isConnected();
            }
        };
        this.Bc = new fg(context, as, this.Br);
        this.AS = as;
        this.Bl = new b(as);
        final Iterator<ConnectionCallbacks> iterator = set.iterator();
        while (iterator.hasNext()) {
            this.Bc.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<OnConnectionFailedListener> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            this.Bc.registerConnectionFailedListener(iterator2.next());
        }
        for (final Api<O> api : map.keySet()) {
            final Api.b<?, O> dy = api.dY();
            this.Bn.put(api.ea(), a((Api.b<Api.a, Object>)dy, map.get(api), context, as, fc, this.Bq, new OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(final ConnectionResult connectionResult) {
                    b.this.Ba.lock();
                    try {
                        if (b.this.Be == null || dy.getPriority() < b.this.Bf) {
                            b.this.Be = connectionResult;
                            b.this.Bf = dy.getPriority();
                        }
                        b.this.ei();
                    }
                    finally {
                        b.this.Ba.unlock();
                    }
                }
            }));
        }
    }
    
    private void E(final int n) {
        this.Ba.lock();
        Label_0328: {
            Label_0195: {
                Label_0113: {
                    try {
                        if (this.Bg == 3) {
                            break Label_0328;
                        }
                        if (n != -1) {
                            break Label_0195;
                        }
                        if (this.isConnecting()) {
                            final Iterator<c> iterator = (Iterator<c>)this.Bd.iterator();
                            while (iterator.hasNext()) {
                                final c c = iterator.next();
                                if (c.ef() != 1) {
                                    c.cancel();
                                    iterator.remove();
                                }
                            }
                            break Label_0113;
                        }
                    }
                    finally {
                        this.Ba.unlock();
                    }
                    this.Bd.clear();
                }
                final Iterator<c<?>> iterator2 = this.Bp.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().cancel();
                }
                this.Bp.clear();
                if (this.Be == null && !this.Bd.isEmpty()) {
                    this.Bi = true;
                    this.Ba.unlock();
                    return;
                }
            }
            final boolean connecting = this.isConnecting();
            final boolean connected = this.isConnected();
            this.Bg = 3;
            if (connecting) {
                if (n == -1) {
                    this.Be = null;
                }
                this.Bb.signalAll();
            }
            this.Bo = false;
            for (final Api.a a : this.Bn.values()) {
                if (a.isConnected()) {
                    a.disconnect();
                }
            }
            this.Bo = true;
            this.Bg = 4;
            if (connected) {
                if (n != -1) {
                    this.Bc.O(n);
                }
                this.Bo = false;
            }
        }
        this.Ba.unlock();
    }
    
    private static <C extends Api.a, O> C a(final Api.b<C, O> b, final Object o, final Context context, final Looper looper, final fc fc, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        return b.a(context, looper, fc, (O)o, connectionCallbacks, onConnectionFailedListener);
    }
    
    private <A extends Api.a> void a(final c<A> c) throws DeadObjectException {
        while (true) {
            final boolean b = true;
            this.Ba.lock();
            while (true) {
                try {
                    if (!this.isConnected()) {
                        if (!this.ek()) {
                            final boolean b2 = false;
                            fq.a(b2, (Object)"GoogleApiClient is not connected yet.");
                            fq.b(c.ea() != null && b, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
                            this.Bp.add(c);
                            c.a(this.AL);
                            if (this.ek()) {
                                c.k(new Status(8));
                                return;
                            }
                            c.b(this.a((Api.c<?>)c.ea()));
                            return;
                        }
                    }
                }
                finally {
                    this.Ba.unlock();
                }
                final boolean b2 = true;
                continue;
            }
        }
    }
    
    private void ei() {
        while (true) {
            this.Ba.lock();
            Label_0128: {
                try {
                    --this.Bj;
                    if (this.Bj == 0) {
                        if (this.Be == null) {
                            break Label_0128;
                        }
                        this.Bi = false;
                        this.E(3);
                        if (this.ek()) {
                            --this.Bh;
                        }
                        if (this.ek()) {
                            this.Bl.sendMessageDelayed(this.Bl.obtainMessage(1), this.Bk);
                        }
                        else {
                            this.Bc.a(this.Be);
                        }
                        this.Bo = false;
                    }
                    return;
                }
                finally {
                    this.Ba.unlock();
                }
            }
            this.Bg = 2;
            this.el();
            this.Bb.signalAll();
            this.ej();
            if (this.Bi) {
                this.Bi = false;
                this.E(-1);
                return;
            }
            Bundle bm;
            if (this.Bm.isEmpty()) {
                bm = null;
            }
            else {
                bm = this.Bm;
            }
            this.Bc.b(bm);
        }
    }
    
    private void ej() {
        while (true) {
            Label_0092: {
                if (!this.isConnected() && !this.ek()) {
                    break Label_0092;
                }
                final boolean b = true;
                fq.a(b, (Object)"GoogleApiClient is not connected yet.");
                this.Ba.lock();
                Label_0097: {
                    try {
                        while (!this.Bd.isEmpty()) {
                            try {
                                this.a((c<?>)this.Bd.remove());
                            }
                            catch (DeadObjectException ex) {
                                Log.w("GoogleApiClientImpl", "Service died while flushing queue", (Throwable)ex);
                            }
                        }
                        break Label_0097;
                    }
                    finally {
                        this.Ba.unlock();
                    }
                    break Label_0092;
                }
                this.Ba.unlock();
                return;
            }
            final boolean b = false;
            continue;
        }
    }
    
    private boolean ek() {
        this.Ba.lock();
        try {
            return this.Bh != 0;
        }
        finally {
            this.Ba.unlock();
        }
    }
    
    private void el() {
        this.Ba.lock();
        try {
            this.Bh = 0;
            this.Bl.removeMessages(1);
        }
        finally {
            this.Ba.unlock();
        }
    }
    
    @Override
    public <C extends Api.a> C a(final Api.c<C> c) {
        final Api.a a = this.Bn.get(c);
        fq.b(a, "Appropriate Api was not requested.");
        return (C)a;
    }
    
    @Override
    public <A extends Api.a, T extends com.google.android.gms.common.api.a.b<? extends Result, A>> T a(final T t) {
        this.Ba.lock();
        try {
            if (this.isConnected()) {
                this.b((com.google.android.gms.common.api.a.b)t);
            }
            else {
                this.Bd.add((c<?>)t);
            }
            return t;
        }
        finally {
            this.Ba.unlock();
        }
    }
    
    @Override
    public <A extends Api.a, T extends com.google.android.gms.common.api.a.b<? extends Result, A>> T b(final T t) {
        Label_0034: {
            if (!this.isConnected() && !this.ek()) {
                break Label_0034;
            }
            boolean b = true;
            while (true) {
                fq.a(b, (Object)"GoogleApiClient is not connected yet.");
                this.ej();
                try {
                    this.a((c<Api.a>)t);
                    return t;
                    b = false;
                }
                catch (DeadObjectException ex) {
                    this.E(1);
                    return t;
                }
            }
        }
    }
    
    @Override
    public ConnectionResult blockingConnect(long n, final TimeUnit timeUnit) {
        Label_0094: {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                break Label_0094;
            }
            boolean connecting = true;
        Label_0012:
            while (true) {
                fq.a(connecting, (Object)"blockingConnect must not be called on the UI thread");
                this.Ba.lock();
                try {
                    this.connect();
                    n = timeUnit.toNanos(n);
                    while (true) {
                        connecting = this.isConnecting();
                        if (connecting) {
                            try {
                                if ((n = this.Bb.awaitNanos(n)) <= 0L) {
                                    return new ConnectionResult(14, null);
                                }
                                continue;
                                connecting = false;
                                continue Label_0012;
                            }
                            catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                                return new ConnectionResult(15, null);
                            }
                            break;
                        }
                        break;
                    }
                    if (this.isConnected()) {
                        return ConnectionResult.Ag;
                    }
                    if (this.Be != null) {
                        return this.Be;
                    }
                    return new ConnectionResult(13, null);
                }
                finally {
                    this.Ba.unlock();
                }
            }
        }
    }
    
    @Override
    public void connect() {
        this.Ba.lock();
        try {
            this.Bi = false;
            if (this.isConnected() || this.isConnecting()) {
                return;
            }
            this.Bo = true;
            this.Be = null;
            this.Bg = 1;
            this.Bm.clear();
            this.Bj = this.Bn.size();
            final Iterator<Api.a> iterator = this.Bn.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().connect();
            }
        }
        finally {
            this.Ba.unlock();
        }
        this.Ba.unlock();
    }
    
    @Override
    public void disconnect() {
        this.el();
        this.E(-1);
    }
    
    @Override
    public Looper getLooper() {
        return this.AS;
    }
    
    @Override
    public boolean isConnected() {
        this.Ba.lock();
        try {
            return this.Bg == 2;
        }
        finally {
            this.Ba.unlock();
        }
    }
    
    @Override
    public boolean isConnecting() {
        boolean b = true;
        this.Ba.lock();
        try {
            if (this.Bg != 1) {
                b = false;
            }
            return b;
        }
        finally {
            this.Ba.unlock();
        }
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.Bc.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.Bc.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Bc.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Bc.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Bc.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Bc.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    interface a
    {
        void b(final c<?> p0);
    }
    
    class b extends Handler
    {
        b(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            if (message.what == 1) {
                com.google.android.gms.common.api.b.this.Ba.lock();
                try {
                    if (!com.google.android.gms.common.api.b.this.isConnected() && !com.google.android.gms.common.api.b.this.isConnecting()) {
                        com.google.android.gms.common.api.b.this.connect();
                    }
                    return;
                }
                finally {
                    com.google.android.gms.common.api.b.this.Ba.unlock();
                }
            }
            Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
        }
    }
    
    interface c<A extends Api.a>
    {
        void a(final a p0);
        
        void b(final A p0) throws DeadObjectException;
        
        void cancel();
        
        Api.c<A> ea();
        
        int ef();
        
        void k(final Status p0);
    }
}
