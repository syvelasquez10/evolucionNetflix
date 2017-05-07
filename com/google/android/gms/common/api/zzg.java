// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.IntentFilter;
import com.google.android.gms.common.internal.zzu;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zze$zza;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Set;
import com.google.android.gms.common.ConnectionResult;
import android.content.BroadcastReceiver;
import java.util.Queue;
import com.google.android.gms.common.internal.zzj;
import java.util.concurrent.locks.Condition;
import java.util.Map;
import com.google.android.gms.common.internal.zzj$zza;
import android.os.Looper;
import com.google.android.gms.common.internal.zze;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;

final class zzg implements GoogleApiClient
{
    private final Context mContext;
    private final int zzWA;
    private final int zzWB;
    final Api$zza<? extends zzps, zzpt> zzWD;
    private final Lock zzWK;
    final zze zzWZ;
    private final Looper zzWs;
    private final zzg$zzc zzXA;
    private final GoogleApiClient$ConnectionCallbacks zzXB;
    private final zzj$zza zzXC;
    final Map<Api<?>, Integer> zzXa;
    private final Condition zzXl;
    final zzj zzXm;
    final Queue<zzg$zze<?>> zzXn;
    private volatile boolean zzXo;
    private long zzXp;
    private long zzXq;
    final zzg$zza zzXr;
    BroadcastReceiver zzXs;
    final Map<Api$ClientKey<?>, Api$Client> zzXt;
    final Map<Api$ClientKey<?>, ConnectionResult> zzXu;
    Set<Scope> zzXv;
    private volatile zzh zzXw;
    private ConnectionResult zzXx;
    private final Set<zzi<?>> zzXy;
    final Set<zzg$zze<?>> zzXz;
    
    public zzg(final Context mContext, final Looper zzWs, final zze zzWZ, final Api$zza<? extends zzps, zzpt> zzWD, final Map<Api<?>, Api$ApiOptions> map, final Set<GoogleApiClient$ConnectionCallbacks> set, final Set<GoogleApiClient$OnConnectionFailedListener> set2, int zzWA, final int zzWB) {
        this.zzWK = new ReentrantLock();
        this.zzXn = new LinkedList<zzg$zze<?>>();
        this.zzXp = 120000L;
        this.zzXq = 5000L;
        this.zzXt = new HashMap<Api$ClientKey<?>, Api$Client>();
        this.zzXu = new HashMap<Api$ClientKey<?>, ConnectionResult>();
        this.zzXv = new HashSet<Scope>();
        this.zzXx = null;
        this.zzXy = Collections.newSetFromMap(new WeakHashMap<zzi<?>, Boolean>());
        this.zzXz = Collections.newSetFromMap(new ConcurrentHashMap<zzg$zze<?>, Boolean>(16, 0.75f, 2));
        this.zzXA = new zzg$1(this);
        this.zzXB = new zzg$2(this);
        this.zzXC = new zzg$3(this);
        this.mContext = mContext;
        this.zzXm = new zzj(zzWs, this.zzXC);
        this.zzWs = zzWs;
        this.zzXr = new zzg$zza(this, zzWs);
        this.zzWA = zzWA;
        this.zzWB = zzWB;
        this.zzXa = new HashMap<Api<?>, Integer>();
        this.zzXl = this.zzWK.newCondition();
        this.zzXw = new zzf(this);
        final Iterator<GoogleApiClient$ConnectionCallbacks> iterator = set.iterator();
        while (iterator.hasNext()) {
            this.zzXm.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<GoogleApiClient$OnConnectionFailedListener> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            this.zzXm.registerConnectionFailedListener(iterator2.next());
        }
        final Map<Api<?>, zze$zza> zznv = zzWZ.zznv();
        for (final Api<?> api : map.keySet()) {
            final Api$ApiOptions value = map.get(api);
            if (zznv.get(api) != null) {
                if (zznv.get(api).zzZV) {
                    zzWA = 1;
                }
                else {
                    zzWA = 2;
                }
            }
            else {
                zzWA = 0;
            }
            this.zzXa.put(api, zzWA);
            zzaa zzaa;
            if (api.zzmr()) {
                zzaa = zza(api.zzmo(), value, mContext, zzWs, zzWZ, this.zzXB, this.zza(api, zzWA));
            }
            else {
                zzaa = zza(api.zzmn(), value, mContext, zzWs, zzWZ, this.zzXB, this.zza(api, zzWA));
            }
            this.zzXt.put(api.zzmq(), zzaa);
        }
        this.zzWZ = zzWZ;
        this.zzWD = zzWD;
    }
    
    private void resume() {
        this.zzWK.lock();
        try {
            if (this.zzmM()) {
                this.connect();
            }
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    private static <C extends Api$Client, O> C zza(final Api$zza<C, O> api$zza, final Object o, final Context context, final Looper looper, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return api$zza.zza(context, looper, zze, (O)o, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    private final GoogleApiClient$OnConnectionFailedListener zza(final Api<?> api, final int n) {
        return new zzg$4(this, api, n);
    }
    
    private static <C extends Api$zzb, O> zzaa zza(final Api$zzc<C, O> api$zzc, final Object o, final Context context, final Looper looper, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzaa(context, looper, api$zzc.zzms(), googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zze, api$zzc.zzl((O)o));
    }
    
    private void zzaY(final int n) {
        this.zzWK.lock();
        try {
            this.zzXw.zzaV(n);
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    private void zzmN() {
        this.zzWK.lock();
        try {
            if (this.zzmP()) {
                this.connect();
            }
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    @Override
    public void connect() {
        this.zzWK.lock();
        try {
            this.zzXw.connect();
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    @Override
    public void disconnect() {
        this.zzmP();
        this.zzaY(-1);
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mState=").append(this.zzXw.getName());
        printWriter.append(" mResuming=").print(this.zzXo);
        printWriter.append(" mWorkQueue.size()=").print(this.zzXn.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.zzXz.size());
        final String string = s + "  ";
        for (final Api<?> api : this.zzXa.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.zzXt.get(api.zzmq()).dump(string, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public Looper getLooper() {
        return this.zzWs;
    }
    
    public int getSessionId() {
        return System.identityHashCode(this);
    }
    
    @Override
    public boolean isConnected() {
        return this.zzXw instanceof zzd;
    }
    
    @Override
    public boolean isConnecting() {
        return this.zzXw instanceof com.google.android.gms.common.api.zze;
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzXm.registerConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzXm.registerConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzXm.unregisterConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzXm.unregisterConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public <C extends Api$Client> C zza(final Api$ClientKey<C> api$ClientKey) {
        final Api$Client api$Client = this.zzXt.get(api$ClientKey);
        zzu.zzb(api$Client, "Appropriate Api was not requested.");
        return (C)api$Client;
    }
    
    @Override
    public <A extends Api$Client, R extends Result, T extends zza$zza<R, A>> T zza(final T t) {
        Label_0066: {
            if (t.zzmq() == null) {
                break Label_0066;
            }
            boolean b = true;
            while (true) {
                zzu.zzb(b, "This task can not be enqueued (it's probably a Batch or malformed)");
                zzu.zzb(this.zzXt.containsKey(t.zzmq()), "GoogleApiClient is not configured to use the API required for this call.");
                this.zzWK.lock();
                try {
                    return this.zzXw.zza(t);
                    b = false;
                }
                finally {
                    this.zzWK.unlock();
                }
            }
        }
    }
    
    @Override
    public <A extends Api$Client, T extends zza$zza<? extends Result, A>> T zzb(final T t) {
        while (true) {
            Label_0097: {
                if (t.zzmq() == null) {
                    break Label_0097;
                }
                final boolean b = true;
                zzu.zzb(b, "This task can not be executed (it's probably a Batch or malformed)");
                this.zzWK.lock();
                final T t2;
                Label_0113: {
                    Label_0102: {
                        try {
                            if (this.zzmM()) {
                                this.zzXn.add(t);
                                while (!this.zzXn.isEmpty()) {
                                    final zzg$zze<?> zzg$zze = this.zzXn.remove();
                                    this.zzb((zzg$zze<Api$Client>)zzg$zze);
                                    zzg$zze.zzr(Status.zzXQ);
                                }
                                break Label_0102;
                            }
                            break Label_0113;
                        }
                        finally {
                            this.zzWK.unlock();
                        }
                        break Label_0097;
                    }
                    this.zzWK.unlock();
                    return t2;
                }
                final zza$zza<? extends Result, A> zzb = this.zzXw.zzb(t2);
                this.zzWK.unlock();
                return (T)zzb;
            }
            final boolean b = false;
            continue;
        }
    }
    
     <A extends Api$Client> void zzb(final zzg$zze<A> zzg$zze) {
        this.zzXz.add(zzg$zze);
        zzg$zze.zza(this.zzXA);
    }
    
    void zze(final ConnectionResult zzXx) {
        this.zzWK.lock();
        try {
            this.zzXx = zzXx;
            (this.zzXw = new zzf(this)).begin();
            this.zzXl.signalAll();
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    void zzmI() {
        for (final zzg$zze<?> zzg$zze : this.zzXz) {
            zzg$zze.zza(null);
            zzg$zze.cancel();
        }
        this.zzXz.clear();
        final Iterator<zzi<?>> iterator2 = this.zzXy.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().clear();
        }
        this.zzXy.clear();
        this.zzXv.clear();
    }
    
    void zzmJ() {
        final Iterator<Api$Client> iterator = this.zzXt.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
    }
    
    void zzmK() {
        this.zzWK.lock();
        try {
            (this.zzXw = new com.google.android.gms.common.api.zze(this, this.zzWZ, this.zzXa, this.zzWD, this.zzWK, this.mContext)).begin();
            this.zzXl.signalAll();
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    void zzmL() {
        this.zzWK.lock();
        try {
            this.zzmP();
            (this.zzXw = new zzd(this)).begin();
            this.zzXl.signalAll();
        }
        finally {
            this.zzWK.unlock();
        }
    }
    
    boolean zzmM() {
        return this.zzXo;
    }
    
    void zzmO() {
        if (this.zzmM()) {
            return;
        }
        this.zzXo = true;
        if (this.zzXs == null) {
            this.zzXs = new zzg$zzb(this);
            final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            this.mContext.getApplicationContext().registerReceiver(this.zzXs, intentFilter);
        }
        this.zzXr.sendMessageDelayed(this.zzXr.obtainMessage(1), this.zzXp);
        this.zzXr.sendMessageDelayed(this.zzXr.obtainMessage(2), this.zzXq);
    }
    
    boolean zzmP() {
        this.zzWK.lock();
        try {
            if (!this.zzmM()) {
                return false;
            }
            this.zzXo = false;
            this.zzXr.removeMessages(2);
            this.zzXr.removeMessages(1);
            if (this.zzXs != null) {
                this.mContext.getApplicationContext().unregisterReceiver(this.zzXs);
                this.zzXs = null;
            }
            return true;
        }
        finally {
            this.zzWK.unlock();
        }
    }
}
