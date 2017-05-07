// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.IntentFilter;
import com.google.android.gms.common.internal.zzx;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzk$zza;
import java.util.Set;
import com.google.android.gms.common.ConnectionResult;
import android.content.BroadcastReceiver;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.signin.zzd;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.Queue;
import com.google.android.gms.common.internal.zzk;
import java.util.concurrent.locks.Condition;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;

final class zzi implements GoogleApiClient
{
    private final Context mContext;
    private final Looper zzYV;
    final zzf zzZH;
    final Map<Api<?>, Integer> zzZI;
    private final Condition zzZX;
    final zzk zzZY;
    final Queue<zzi$zze<?>> zzZZ;
    private final int zzZf;
    private final int zzZg;
    private final GoogleApiAvailability zzZi;
    final Api$zza<? extends zzd, zze> zzZj;
    private final Lock zzZs;
    private volatile boolean zzaaa;
    private long zzaab;
    private long zzaac;
    private final zzi$zza zzaad;
    BroadcastReceiver zzaae;
    final Map<Api$zzc<?>, Api$zzb> zzaaf;
    final Map<Api$zzc<?>, ConnectionResult> zzaag;
    Set<Scope> zzaah;
    private volatile zzj zzaai;
    private ConnectionResult zzaaj;
    private final Set<zzl<?>> zzaak;
    final Set<zzi$zze<?>> zzaal;
    private final zzi$zzd zzaam;
    private final GoogleApiClient$ConnectionCallbacks zzaan;
    private final zzk$zza zzaao;
    
    public zzi(final Context mContext, final Looper zzYV, final zzf zzZH, final GoogleApiAvailability zzZi, final Api$zza<? extends zzd, zze> zzZj, final Map<Api<?>, Api$ApiOptions> map, final ArrayList<GoogleApiClient$ConnectionCallbacks> list, final ArrayList<GoogleApiClient$OnConnectionFailedListener> list2, int zzZf, final int zzZg) {
        this.zzZs = new ReentrantLock();
        this.zzZZ = new LinkedList<zzi$zze<?>>();
        this.zzaab = 120000L;
        this.zzaac = 5000L;
        this.zzaaf = new HashMap<Api$zzc<?>, Api$zzb>();
        this.zzaag = new HashMap<Api$zzc<?>, ConnectionResult>();
        this.zzaah = new HashSet<Scope>();
        this.zzaaj = null;
        this.zzaak = Collections.newSetFromMap(new WeakHashMap<zzl<?>, Boolean>());
        this.zzaal = Collections.newSetFromMap(new ConcurrentHashMap<zzi$zze<?>, Boolean>(16, 0.75f, 2));
        this.zzaam = new zzi$1(this);
        this.zzaan = new zzi$2(this);
        this.zzaao = new zzi$3(this);
        this.mContext = mContext;
        this.zzZY = new zzk(zzYV, this.zzaao);
        this.zzYV = zzYV;
        this.zzaad = new zzi$zza(this, zzYV);
        this.zzZi = zzZi;
        this.zzZf = zzZf;
        this.zzZg = zzZg;
        this.zzZI = new HashMap<Api<?>, Integer>();
        this.zzZX = this.zzZs.newCondition();
        this.zzaai = new zzh(this);
        final Iterator<GoogleApiClient$ConnectionCallbacks> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.zzZY.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<GoogleApiClient$OnConnectionFailedListener> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.zzZY.registerConnectionFailedListener(iterator2.next());
        }
        final Map<Api<?>, zzf$zza> zzok = zzZH.zzok();
        for (final Api<?> api : map.keySet()) {
            final Api$ApiOptions value = map.get(api);
            if (zzok.get(api) != null) {
                if (zzok.get(api).zzadg) {
                    zzZf = 1;
                }
                else {
                    zzZf = 2;
                }
            }
            else {
                zzZf = 0;
            }
            this.zzZI.put(api, zzZf);
            zzac zzac;
            if (api.zzne()) {
                zzac = zza(api.zznc(), value, mContext, zzYV, zzZH, this.zzaan, this.zza(api, zzZf));
            }
            else {
                zzac = zza(api.zznb(), value, mContext, zzYV, zzZH, this.zzaan, this.zza(api, zzZf));
            }
            this.zzaaf.put(api.zznd(), zzac);
        }
        this.zzZH = zzZH;
        this.zzZj = zzZj;
    }
    
    private void resume() {
        this.zzZs.lock();
        try {
            if (this.zznB()) {
                this.connect();
            }
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    private static <C extends Api$zzb, O> C zza(final Api$zza<C, O> api$zza, final Object o, final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return api$zza.zza(context, looper, zzf, (O)o, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    private final GoogleApiClient$OnConnectionFailedListener zza(final Api<?> api, final int n) {
        return new zzi$4(this, api, n);
    }
    
    private static <C extends Api$zzd, O> zzac zza(final Api$zze<C, O> api$zze, final Object o, final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzac(context, looper, api$zze.zznf(), googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zzf, api$zze.zzm((O)o));
    }
    
    private void zznC() {
        this.zzZs.lock();
        try {
            if (this.zznE()) {
                this.connect();
            }
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    @Override
    public void connect() {
        this.zzZs.lock();
        try {
            this.zzaai.connect();
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    @Override
    public void disconnect() {
        this.zzZs.lock();
        try {
            this.zznE();
            this.zzaai.disconnect();
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mState=").append(this.zzaai.getName());
        printWriter.append(" mResuming=").print(this.zzaaa);
        printWriter.append(" mWorkQueue.size()=").print(this.zzZZ.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.zzaal.size());
        final String string = s + "  ";
        for (final Api<?> api : this.zzZI.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.zzaaf.get(api.zznd()).dump(string, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public Looper getLooper() {
        return this.zzYV;
    }
    
    public int getSessionId() {
        return System.identityHashCode(this);
    }
    
    @Override
    public boolean isConnected() {
        return this.zzaai instanceof com.google.android.gms.common.api.zzf;
    }
    
    @Override
    public boolean isConnecting() {
        return this.zzaai instanceof zzg;
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzZY.registerConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzZY.registerConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzZY.unregisterConnectionCallbacks(googleApiClient$ConnectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzZY.unregisterConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public <C extends Api$zzb> C zza(final Api$zzc<C> api$zzc) {
        final Api$zzb api$zzb = this.zzaaf.get(api$zzc);
        zzx.zzb(api$zzb, "Appropriate Api was not requested.");
        return (C)api$zzb;
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzc$zza<R, A>> T zza(final T t) {
        Label_0066: {
            if (t.zznd() == null) {
                break Label_0066;
            }
            boolean b = true;
            while (true) {
                zzx.zzb(b, "This task can not be enqueued (it's probably a Batch or malformed)");
                zzx.zzb(this.zzaaf.containsKey(t.zznd()), "GoogleApiClient is not configured to use the API required for this call.");
                this.zzZs.lock();
                try {
                    return this.zzaai.zza(t);
                    b = false;
                }
                finally {
                    this.zzZs.unlock();
                }
            }
        }
    }
    
    void zza(final zzi$zzb zzi$zzb) {
        this.zzaad.sendMessage(this.zzaad.obtainMessage(3, (Object)zzi$zzb));
    }
    
    void zza(final RuntimeException ex) {
        this.zzaad.sendMessage(this.zzaad.obtainMessage(4, (Object)ex));
    }
    
    @Override
    public <A extends Api$zzb, T extends zzc$zza<? extends Result, A>> T zzb(final T t) {
        while (true) {
            Label_0097: {
                if (t.zznd() == null) {
                    break Label_0097;
                }
                final boolean b = true;
                zzx.zzb(b, "This task can not be executed (it's probably a Batch or malformed)");
                this.zzZs.lock();
                final T t2;
                Label_0113: {
                    Label_0102: {
                        try {
                            if (this.zznB()) {
                                this.zzZZ.add(t);
                                while (!this.zzZZ.isEmpty()) {
                                    final zzi$zze<?> zzi$zze = this.zzZZ.remove();
                                    this.zzb((zzi$zze<Api$zzb>)zzi$zze);
                                    zzi$zze.zzx(Status.zzaaF);
                                }
                                break Label_0102;
                            }
                            break Label_0113;
                        }
                        finally {
                            this.zzZs.unlock();
                        }
                        break Label_0097;
                    }
                    this.zzZs.unlock();
                    return t2;
                }
                final zzc$zza<? extends Result, A> zzb = this.zzaai.zzb(t2);
                this.zzZs.unlock();
                return (T)zzb;
            }
            final boolean b = false;
            continue;
        }
    }
    
     <A extends Api$zzb> void zzb(final zzi$zze<A> zzi$zze) {
        this.zzaal.add(zzi$zze);
        zzi$zze.zza(this.zzaam);
    }
    
    void zzg(final ConnectionResult zzaaj) {
        this.zzZs.lock();
        try {
            this.zzaaj = zzaaj;
            (this.zzaai = new zzh(this)).begin();
            this.zzZX.signalAll();
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    void zznA() {
        this.zzZs.lock();
        try {
            this.zznE();
            (this.zzaai = new com.google.android.gms.common.api.zzf(this)).begin();
            this.zzZX.signalAll();
        }
        finally {
            this.zzZs.unlock();
        }
    }
    
    boolean zznB() {
        return this.zzaaa;
    }
    
    void zznD() {
        if (this.zznB()) {
            return;
        }
        this.zzaaa = true;
        if (this.zzaae == null) {
            this.zzaae = new zzi$zzc(this);
            final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            this.mContext.getApplicationContext().registerReceiver(this.zzaae, intentFilter);
        }
        this.zzaad.sendMessageDelayed(this.zzaad.obtainMessage(1), this.zzaab);
        this.zzaad.sendMessageDelayed(this.zzaad.obtainMessage(2), this.zzaac);
    }
    
    boolean zznE() {
        if (!this.zznB()) {
            return false;
        }
        this.zzaaa = false;
        this.zzaad.removeMessages(2);
        this.zzaad.removeMessages(1);
        if (this.zzaae != null) {
            this.mContext.getApplicationContext().unregisterReceiver(this.zzaae);
            this.zzaae = null;
        }
        return true;
    }
    
    void zznx() {
        for (final zzi$zze<?> zzi$zze : this.zzaal) {
            zzi$zze.zza(null);
            zzi$zze.cancel();
        }
        this.zzaal.clear();
        final Iterator<zzl<?>> iterator2 = this.zzaak.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().clear();
        }
        this.zzaak.clear();
    }
    
    void zzny() {
        final Iterator<Api$zzb> iterator = this.zzaaf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
    }
    
    void zznz() {
        this.zzZs.lock();
        try {
            (this.zzaai = new zzg(this, this.zzZH, this.zzZI, this.zzZi, this.zzZj, this.zzZs, this.mContext)).begin();
            this.zzZX.signalAll();
        }
        finally {
            this.zzZs.unlock();
        }
    }
}
