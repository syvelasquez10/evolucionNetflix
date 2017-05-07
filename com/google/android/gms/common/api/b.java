// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.v4.app.FragmentActivity;
import java.util.concurrent.TimeUnit;
import android.app.PendingIntent;
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
import com.google.android.gms.common.ConnectionResult;
import java.util.Queue;
import com.google.android.gms.common.internal.e;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import android.os.Looper;

final class b implements GoogleApiClient
{
    private final Looper IB;
    private final Lock IO;
    private final Condition IP;
    private final e IQ;
    private final int IR;
    final Queue<b$c<?>> IS;
    private ConnectionResult IT;
    private int IU;
    private volatile int IV;
    private volatile int IW;
    private boolean IX;
    private int IY;
    private long IZ;
    private final b$a Iu;
    final Handler Ja;
    private final Bundle Jb;
    private final Map<Api$c<?>, Api$a> Jc;
    private final List<String> Jd;
    private boolean Je;
    private final Set<c<?>> Jf;
    final Set<b$c<?>> Jg;
    private final GoogleApiClient$ConnectionCallbacks Jh;
    private final e$b Ji;
    
    public b(final Context context, final Looper ib, final ClientSettings clientSettings, final Map<Api<?>, Api$ApiOptions> map, final Set<GoogleApiClient$ConnectionCallbacks> set, final Set<GoogleApiClient$OnConnectionFailedListener> set2, final int ir) {
        this.IO = new ReentrantLock();
        this.IP = this.IO.newCondition();
        this.IS = new LinkedList<b$c<?>>();
        this.IV = 4;
        this.IX = false;
        this.IZ = 5000L;
        this.Jb = new Bundle();
        this.Jc = new HashMap<Api$c<?>, Api$a>();
        this.Jf = Collections.newSetFromMap(new WeakHashMap<c<?>, Boolean>());
        this.Jg = Collections.newSetFromMap(new ConcurrentHashMap<b$c<?>, Boolean>());
        this.Iu = new b$1(this);
        this.Jh = new b$2(this);
        this.Ji = new b$3(this);
        this.IQ = new e(context, ib, this.Ji);
        this.IB = ib;
        this.Ja = new b$b(this, ib);
        this.IR = ir;
        final Iterator<GoogleApiClient$ConnectionCallbacks> iterator = set.iterator();
        while (iterator.hasNext()) {
            this.IQ.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<GoogleApiClient$OnConnectionFailedListener> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            this.IQ.registerConnectionFailedListener(iterator2.next());
        }
        for (final Api<O> api : map.keySet()) {
            final Api$b<?, O> gd = api.gd();
            this.Jc.put(api.gf(), a((Api$b<Api$a, Object>)gd, map.get(api), context, ib, clientSettings, this.Jh, new b$4(this, gd)));
        }
        this.Jd = Collections.unmodifiableList((List<? extends String>)clientSettings.getScopes());
    }
    
    private static <C extends Api$a, O> C a(final Api$b<C, O> api$b, final Object o, final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return api$b.a(context, looper, clientSettings, (O)o, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    private <A extends Api$a> void a(final b$c<A> b$c) {
        this.IO.lock();
        try {
            n.b(b$c.gf() != null, (Object)"This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.Jg.add(b$c);
            b$c.a(this.Iu);
            if (this.gp()) {
                b$c.m(new Status(8));
                return;
            }
            b$c.b(this.a(b$c.gf()));
        }
        finally {
            this.IO.unlock();
        }
    }
    
    private void aj(final int n) {
        this.IO.lock();
        Label_0374: {
            Label_0241: {
                Label_0113: {
                    try {
                        if (this.IV == 3) {
                            break Label_0374;
                        }
                        if (n != -1) {
                            break Label_0241;
                        }
                        if (this.isConnecting()) {
                            final Iterator<b$c> iterator = (Iterator<b$c>)this.IS.iterator();
                            while (iterator.hasNext()) {
                                final b$c b$c = iterator.next();
                                if (b$c.gk() != 1) {
                                    b$c.cancel();
                                    iterator.remove();
                                }
                            }
                            break Label_0113;
                        }
                    }
                    finally {
                        this.IO.unlock();
                    }
                    this.IS.clear();
                }
                final Iterator<b$c<?>> iterator2 = this.Jg.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().cancel();
                }
                this.Jg.clear();
                final Iterator<c<?>> iterator3 = this.Jf.iterator();
                while (iterator3.hasNext()) {
                    iterator3.next().clear();
                }
                this.Jf.clear();
                if (this.IT == null && !this.IS.isEmpty()) {
                    this.IX = true;
                    this.IO.unlock();
                    return;
                }
            }
            final boolean connecting = this.isConnecting();
            final boolean connected = this.isConnected();
            this.IV = 3;
            if (connecting) {
                if (n == -1) {
                    this.IT = null;
                }
                this.IP.signalAll();
            }
            this.Je = false;
            for (final Api$a api$a : this.Jc.values()) {
                if (api$a.isConnected()) {
                    api$a.disconnect();
                }
            }
            this.Je = true;
            this.IV = 4;
            if (connected) {
                if (n != -1) {
                    this.IQ.aB(n);
                }
                this.Je = false;
            }
        }
        this.IO.unlock();
    }
    
    private void gn() {
        --this.IY;
        if (this.IY == 0) {
            if (this.IT != null) {
                this.IX = false;
                this.aj(3);
                if (this.gp()) {
                    this.Ja.sendMessageDelayed(this.Ja.obtainMessage(1), this.IZ);
                }
                else {
                    this.IQ.b(this.IT);
                }
                this.Je = false;
            }
            else {
                this.IV = 2;
                this.gq();
                this.IP.signalAll();
                this.go();
                if (this.IX) {
                    this.IX = false;
                    this.aj(-1);
                    return;
                }
                Bundle jb;
                if (this.Jb.isEmpty()) {
                    jb = null;
                }
                else {
                    jb = this.Jb;
                }
                this.IQ.d(jb);
            }
        }
    }
    
    private void go() {
    Label_0026_Outer:
        while (true) {
            this.IO.lock();
            while (true) {
                Label_0108: {
                    Label_0093: {
                        try {
                            if (!this.isConnected() && !this.gp()) {
                                break Label_0093;
                            }
                            break Label_0108;
                            while (true) {
                                Block_5: {
                                    break Block_5;
                                    final boolean b;
                                    n.a(b, (Object)"GoogleApiClient is not connected yet.");
                                    continue Label_0026_Outer;
                                }
                                try {
                                    this.a((b$c<?>)this.IS.remove());
                                }
                                catch (DeadObjectException ex) {
                                    Log.w("GoogleApiClientImpl", "Service died while flushing queue", (Throwable)ex);
                                }
                                continue Label_0026_Outer;
                            }
                        }
                        // iftrue(Label_0098:, this.IS.isEmpty())
                        finally {
                            this.IO.unlock();
                        }
                    }
                    final boolean b = false;
                    continue;
                }
                final boolean b = true;
                continue;
            }
        }
        Label_0098: {
            this.IO.unlock();
        }
    }
    
    private boolean gp() {
        return this.IW != 0;
    }
    
    private void gq() {
        this.IO.lock();
        try {
            this.IW = 0;
            this.Ja.removeMessages(1);
        }
        finally {
            this.IO.unlock();
        }
    }
    
    @Override
    public <C extends Api$a> C a(final Api$c<C> api$c) {
        final Api$a api$a = this.Jc.get(api$c);
        n.b(api$a, "Appropriate Api was not requested.");
        return (C)api$a;
    }
    
    @Override
    public <A extends Api$a, R extends Result, T extends BaseImplementation$a<R, A>> T a(final T t) {
        this.IO.lock();
        try {
            t.a((BaseImplementation$CallbackHandler<R>)new BaseImplementation$CallbackHandler<Result>(this.getLooper()));
            if (this.isConnected()) {
                this.b(t);
            }
            else {
                this.IS.add(t);
            }
            return t;
        }
        finally {
            this.IO.unlock();
        }
    }
    
    @Override
    public boolean a(final Scope scope) {
        return this.Jd.contains(scope.gt());
    }
    
    @Override
    public <A extends Api$a, T extends BaseImplementation$a<? extends Result, A>> T b(final T t) {
        Label_0034: {
            if (!this.isConnected() && !this.gp()) {
                break Label_0034;
            }
            boolean b = true;
            while (true) {
                n.a(b, (Object)"GoogleApiClient is not connected yet.");
                this.go();
                try {
                    this.a((b$c<Api$a>)t);
                    return t;
                    b = false;
                }
                catch (DeadObjectException ex) {
                    this.aj(1);
                    return t;
                }
            }
        }
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        Label_0081: {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                break Label_0081;
            }
            boolean connecting = true;
            while (true) {
                n.a(connecting, (Object)"blockingConnect must not be called on the UI thread");
                this.IO.lock();
                try {
                    this.connect();
                    while (true) {
                        connecting = this.isConnecting();
                        if (connecting) {
                            try {
                                this.IP.await();
                                continue;
                            }
                            catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                                return new ConnectionResult(15, null);
                            }
                            connecting = false;
                            break;
                        }
                        if (this.isConnected()) {
                            return ConnectionResult.HE;
                        }
                        if (this.IT != null) {
                            return this.IT;
                        }
                        return new ConnectionResult(13, null);
                    }
                }
                finally {
                    this.IO.unlock();
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
                n.a(connecting, (Object)"blockingConnect must not be called on the UI thread");
                this.IO.lock();
                try {
                    this.connect();
                    n = timeUnit.toNanos(n);
                    while (true) {
                        connecting = this.isConnecting();
                        if (connecting) {
                            try {
                                if ((n = this.IP.awaitNanos(n)) <= 0L) {
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
                        return ConnectionResult.HE;
                    }
                    if (this.IT != null) {
                        return this.IT;
                    }
                    return new ConnectionResult(13, null);
                }
                finally {
                    this.IO.unlock();
                }
            }
        }
    }
    
    @Override
    public <L> c<L> c(final L l) {
        n.b(l, "Listener must not be null");
        this.IO.lock();
        try {
            final c<Object> c = new c<Object>(this.IB, l);
            this.Jf.add(c);
            return (c<L>)c;
        }
        finally {
            this.IO.unlock();
        }
    }
    
    @Override
    public void connect() {
        this.IO.lock();
        try {
            this.IX = false;
            if (this.isConnected() || this.isConnecting()) {
                return;
            }
            this.Je = true;
            this.IT = null;
            this.IV = 1;
            this.Jb.clear();
            this.IY = this.Jc.size();
            final Iterator<Api$a> iterator = this.Jc.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().connect();
            }
        }
        finally {
            this.IO.unlock();
        }
        this.IO.unlock();
    }
    
    @Override
    public void disconnect() {
        this.gq();
        this.aj(-1);
    }
    
    @Override
    public Looper getLooper() {
        return this.IB;
    }
    
    @Override
    public boolean isConnected() {
        return this.IV == 2;
    }
    
    @Override
    public boolean isConnecting() {
        return this.IV == 1;
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        return this.IQ.isConnectionCallbacksRegistered(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return this.IQ.isConnectionFailedListenerRegistered(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.IQ.registerConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.IQ.registerConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public void stopAutoManage(final FragmentActivity fragmentActivity) {
        n.a(this.IR >= 0, (Object)"Called stopAutoManage but automatic lifecycle management is not enabled.");
        d.a(fragmentActivity).al(this.IR);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.IQ.unregisterConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.IQ.unregisterConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
}
