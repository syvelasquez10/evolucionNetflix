// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.Writer;
import java.io.StringWriter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzx;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.RemoteException;
import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import com.google.android.gms.common.api.Api$zzd;
import com.google.android.gms.common.api.Api$zze;
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
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.internal.zzk$zza;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.common.internal.zzk;
import java.util.concurrent.locks.Condition;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zzli extends GoogleApiClient
{
    private final Context mContext;
    private final int zzaaM;
    private final Looper zzaaO;
    private final GoogleApiAvailability zzaaP;
    final Api$zza<? extends zzqw, zzqx> zzaaQ;
    final zzf zzabI;
    final Map<Api<?>, Integer> zzabJ;
    private final Condition zzabY;
    final zzk zzabZ;
    private final Lock zzabt;
    final Queue<zzli$zzf<?>> zzaca;
    private volatile boolean zzacb;
    private long zzacc;
    private long zzacd;
    private final zzli$zza zzace;
    zzli$zzd zzacf;
    final Map<Api$zzc<?>, Api$zzb> zzacg;
    final Map<Api$zzc<?>, ConnectionResult> zzach;
    Set<Scope> zzaci;
    private volatile zzlj zzacj;
    private ConnectionResult zzack;
    private final Set<zzlm<?>> zzacl;
    final Set<zzli$zzf<?>> zzacm;
    private zza zzacn;
    private final zzli$zze zzaco;
    private final GoogleApiClient$ConnectionCallbacks zzacp;
    private final zzk$zza zzacq;
    
    public zzli(final Context mContext, final Looper zzaaO, final zzf zzabI, final GoogleApiAvailability zzaaP, final Api$zza<? extends zzqw, zzqx> zzaaQ, final Map<Api<?>, Api$ApiOptions> map, final ArrayList<GoogleApiClient$ConnectionCallbacks> list, final ArrayList<GoogleApiClient$OnConnectionFailedListener> list2, int zzaaM) {
        this.zzabt = new ReentrantLock();
        this.zzaca = new LinkedList<zzli$zzf<?>>();
        this.zzacc = 120000L;
        this.zzacd = 5000L;
        this.zzacg = new HashMap<Api$zzc<?>, Api$zzb>();
        this.zzach = new HashMap<Api$zzc<?>, ConnectionResult>();
        this.zzaci = new HashSet<Scope>();
        this.zzack = null;
        this.zzacl = Collections.newSetFromMap(new WeakHashMap<zzlm<?>, Boolean>());
        this.zzacm = Collections.newSetFromMap(new ConcurrentHashMap<zzli$zzf<?>, Boolean>(16, 0.75f, 2));
        this.zzaco = new zzli$1(this);
        this.zzacp = new zzli$2(this);
        this.zzacq = new zzli$3(this);
        this.mContext = mContext;
        this.zzabZ = new zzk(zzaaO, this.zzacq);
        this.zzaaO = zzaaO;
        this.zzace = new zzli$zza(this, zzaaO);
        this.zzaaP = zzaaP;
        this.zzaaM = zzaaM;
        this.zzabJ = new HashMap<Api<?>, Integer>();
        this.zzabY = this.zzabt.newCondition();
        this.zzacj = new zzlh(this);
        final Iterator<GoogleApiClient$ConnectionCallbacks> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.zzabZ.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<GoogleApiClient$OnConnectionFailedListener> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.zzabZ.registerConnectionFailedListener(iterator2.next());
        }
        final Map<Api<?>, zzf$zza> zzoM = zzabI.zzoM();
        for (final Api<?> api : map.keySet()) {
            final Api$ApiOptions value = map.get(api);
            if (zzoM.get(api) != null) {
                if (zzoM.get(api).zzafk) {
                    zzaaM = 1;
                }
                else {
                    zzaaM = 2;
                }
            }
            else {
                zzaaM = 0;
            }
            this.zzabJ.put(api, zzaaM);
            zzac zzac;
            if (api.zzny()) {
                zzac = zza(api.zznw(), value, mContext, zzaaO, zzabI, this.zzacp, this.zza(api, zzaaM));
            }
            else {
                zzac = zza(api.zznv(), value, mContext, zzaaO, zzabI, this.zzacp, this.zza(api, zzaaM));
            }
            this.zzacg.put(api.zznx(), zzac);
        }
        this.zzabI = zzabI;
        this.zzaaQ = zzaaQ;
    }
    
    private void resume() {
        this.zzabt.lock();
        try {
            if (this.zzoc()) {
                this.connect();
            }
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    private static <C extends Api$zzb, O> C zza(final Api$zza<C, O> api$zza, final Object o, final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return api$zza.zza(context, looper, zzf, (O)o, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    private GoogleApiClient$OnConnectionFailedListener zza(final Api<?> api, final int n) {
        return new zzli$4(this, api, n);
    }
    
    private static <C extends Api$zzd, O> zzac zza(final Api$zze<C, O> api$zze, final Object o, final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzac(context, looper, api$zze.zznA(), googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zzf, api$zze.zzn((O)o));
    }
    
    private static void zza(final zzli$zzf<?> zzli$zzf, final zza zza, final IBinder binder) {
        if (zzli$zzf.isReady()) {
            zzli$zzf.zza(new zzli$zzc(zzli$zzf, zza, binder, null));
            return;
        }
        if (binder != null && binder.isBinderAlive()) {
            final zzli$zzc zzli$zzc = new zzli$zzc(zzli$zzf, zza, binder, null);
            zzli$zzf.zza(zzli$zzc);
            try {
                binder.linkToDeath((IBinder$DeathRecipient)zzli$zzc, 0);
                return;
            }
            catch (RemoteException ex) {
                zzli$zzf.cancel();
                zza.remove(zzli$zzf.zznF());
                return;
            }
        }
        zzli$zzf.zza(null);
        zzli$zzf.cancel();
        zza.remove(zzli$zzf.zznF());
    }
    
    private void zzod() {
        this.zzabt.lock();
        try {
            if (this.zzof()) {
                this.connect();
            }
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    @Override
    public void connect() {
        this.zzabt.lock();
        try {
            this.zzacj.connect();
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    @Override
    public void disconnect() {
        this.zzabt.lock();
        try {
            this.zzof();
            this.zzacj.disconnect();
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mState=").append(this.zzacj.getName());
        printWriter.append(" mResuming=").print(this.zzacb);
        printWriter.append(" mWorkQueue.size()=").print(this.zzaca.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.zzacm.size());
        final String string = s + "  ";
        for (final Api<?> api : this.zzabJ.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.zzacg.get(api.zznx()).dump(string, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public Looper getLooper() {
        return this.zzaaO;
    }
    
    @Override
    public int getSessionId() {
        return System.identityHashCode(this);
    }
    
    @Override
    public boolean isConnected() {
        return this.zzacj instanceof zzlf;
    }
    
    @Override
    public boolean isConnecting() {
        return this.zzacj instanceof zzlg;
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzabZ.registerConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzabZ.unregisterConnectionFailedListener(googleApiClient$OnConnectionFailedListener);
    }
    
    @Override
    public <C extends Api$zzb> C zza(final Api$zzc<C> api$zzc) {
        final Api$zzb api$zzb = this.zzacg.get(api$zzc);
        zzx.zzb(api$zzb, "Appropriate Api was not requested.");
        return (C)api$zzb;
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzlb$zza<R, A>> T zza(final T t) {
        Label_0066: {
            if (t.zznx() == null) {
                break Label_0066;
            }
            boolean b = true;
            while (true) {
                zzx.zzb(b, "This task can not be enqueued (it's probably a Batch or malformed)");
                zzx.zzb(this.zzacg.containsKey(t.zznx()), "GoogleApiClient is not configured to use the API required for this call.");
                this.zzabt.lock();
                try {
                    return this.zzacj.zza(t);
                    b = false;
                }
                finally {
                    this.zzabt.unlock();
                }
            }
        }
    }
    
    void zza(final zzli$zzb zzli$zzb) {
        this.zzace.sendMessage(this.zzace.obtainMessage(3, (Object)zzli$zzb));
    }
    
    void zza(final RuntimeException ex) {
        this.zzace.sendMessage(this.zzace.obtainMessage(4, (Object)ex));
    }
    
    @Override
    public <A extends Api$zzb, T extends zzlb$zza<? extends Result, A>> T zzb(final T t) {
        while (true) {
            Label_0097: {
                if (t.zznx() == null) {
                    break Label_0097;
                }
                final boolean b = true;
                zzx.zzb(b, "This task can not be executed (it's probably a Batch or malformed)");
                this.zzabt.lock();
                final T t2;
                Label_0113: {
                    Label_0102: {
                        try {
                            if (this.zzoc()) {
                                this.zzaca.add(t);
                                while (!this.zzaca.isEmpty()) {
                                    final zzli$zzf<?> zzli$zzf = this.zzaca.remove();
                                    this.zzb((zzli$zzf<Api$zzb>)zzli$zzf);
                                    zzli$zzf.zzv(Status.zzabd);
                                }
                                break Label_0102;
                            }
                            break Label_0113;
                        }
                        finally {
                            this.zzabt.unlock();
                        }
                        break Label_0097;
                    }
                    this.zzabt.unlock();
                    return t2;
                }
                final zzlb$zza<? extends Result, A> zzb = this.zzacj.zzb(t2);
                this.zzabt.unlock();
                return (T)zzb;
            }
            final boolean b = false;
            continue;
        }
    }
    
     <A extends Api$zzb> void zzb(final zzli$zzf<A> zzli$zzf) {
        this.zzacm.add(zzli$zzf);
        zzli$zzf.zza(this.zzaco);
    }
    
    void zzg(final ConnectionResult zzack) {
        this.zzabt.lock();
        try {
            this.zzack = zzack;
            (this.zzacj = new zzlh(this)).begin();
            this.zzabY.signalAll();
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    void zznY() {
        for (final zzli$zzf<?> zzli$zzf : this.zzacm) {
            zzli$zzf.zza(null);
            if (zzli$zzf.zznF() == null) {
                zzli$zzf.cancel();
            }
            else {
                zzli$zzf.zznJ();
                zza(zzli$zzf, this.zzacn, this.zza((Api$zzc<Api$zzb>)zzli$zzf.zznx()).zznz());
            }
        }
        this.zzacm.clear();
        final Iterator<zzlm<?>> iterator2 = this.zzacl.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().clear();
        }
        this.zzacl.clear();
    }
    
    void zznZ() {
        final Iterator<Api$zzb> iterator = this.zzacg.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
    }
    
    void zzoa() {
        this.zzabt.lock();
        try {
            (this.zzacj = new zzlg(this, this.zzabI, this.zzabJ, this.zzaaP, this.zzaaQ, this.zzabt, this.mContext)).begin();
            this.zzabY.signalAll();
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    void zzob() {
        this.zzabt.lock();
        try {
            this.zzof();
            (this.zzacj = new zzlf(this)).begin();
            this.zzabY.signalAll();
        }
        finally {
            this.zzabt.unlock();
        }
    }
    
    boolean zzoc() {
        return this.zzacb;
    }
    
    void zzoe() {
        if (this.zzoc()) {
            return;
        }
        this.zzacb = true;
        if (this.zzacf == null) {
            this.zzacf = zzll.zza(this.mContext.getApplicationContext(), new zzli$zzd(this), this.zzaaP);
        }
        this.zzace.sendMessageDelayed(this.zzace.obtainMessage(1), this.zzacc);
        this.zzace.sendMessageDelayed(this.zzace.obtainMessage(2), this.zzacd);
    }
    
    boolean zzof() {
        if (!this.zzoc()) {
            return false;
        }
        this.zzacb = false;
        this.zzace.removeMessages(2);
        this.zzace.removeMessages(1);
        if (this.zzacf != null) {
            this.zzacf.unregister();
            this.zzacf = null;
        }
        return true;
    }
    
    String zzog() {
        final StringWriter stringWriter = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
}
