// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.common.api.Api$zzb;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Set;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;

public class zzlg implements zzlj
{
    private final Context mContext;
    private final GoogleApiAvailability zzaaP;
    private final Api$zza<? extends zzqw, zzqx> zzaaQ;
    private final Set<Api$zzc> zzabA;
    private zzqw zzabB;
    private int zzabC;
    private boolean zzabD;
    private boolean zzabE;
    private zzp zzabF;
    private boolean zzabG;
    private boolean zzabH;
    private final zzf zzabI;
    private final Map<Api<?>, Integer> zzabJ;
    private ArrayList<Future<?>> zzabK;
    private final zzli zzabr;
    private final Lock zzabt;
    private ConnectionResult zzabu;
    private int zzabv;
    private int zzabw;
    private boolean zzabx;
    private int zzaby;
    private final Bundle zzabz;
    
    public zzlg(final zzli zzabr, final zzf zzabI, final Map<Api<?>, Integer> zzabJ, final GoogleApiAvailability zzaaP, final Api$zza<? extends zzqw, zzqx> zzaaQ, final Lock zzabt, final Context mContext) {
        this.zzabw = 0;
        this.zzabx = false;
        this.zzabz = new Bundle();
        this.zzabA = new HashSet<Api$zzc>();
        this.zzabK = new ArrayList<Future<?>>();
        this.zzabr = zzabr;
        this.zzabI = zzabI;
        this.zzabJ = zzabJ;
        this.zzaaP = zzaaP;
        this.zzaaQ = zzaaQ;
        this.zzabt = zzabt;
        this.mContext = mContext;
    }
    
    private void zzY(final boolean b) {
        if (this.zzabB != null) {
            if (this.zzabB.isConnected() && b) {
                this.zzabB.zzCe();
            }
            this.zzabB.disconnect();
            this.zzabF = null;
        }
    }
    
    private void zza(final ResolveAccountResponse resolveAccountResponse) {
        if (!this.zzbn(0)) {
            return;
        }
        final ConnectionResult zzpr = resolveAccountResponse.zzpr();
        if (zzpr.isSuccess()) {
            this.zzabF = resolveAccountResponse.zzpq();
            this.zzabE = true;
            this.zzabG = resolveAccountResponse.zzps();
            this.zzabH = resolveAccountResponse.zzpt();
            this.zznQ();
            return;
        }
        if (this.zze(zzpr)) {
            this.zznV();
            this.zznQ();
            return;
        }
        this.zzf(zzpr);
    }
    
    private boolean zza(final int n, final int n2, final ConnectionResult connectionResult) {
        return (n2 != 1 || this.zzd(connectionResult)) && (this.zzabu == null || n < this.zzabv);
    }
    
    private void zzb(final ConnectionResult zzabu, final Api<?> api, final int n) {
        if (n != 2) {
            final int priority = api.zznv().getPriority();
            if (this.zza(priority, n, zzabu)) {
                this.zzabu = zzabu;
                this.zzabv = priority;
            }
        }
        this.zzabr.zzach.put(api.zznx(), zzabu);
    }
    
    private boolean zzbn(final int n) {
        if (this.zzabw != n) {
            Log.i("GoogleApiClientConnecting", this.zzabr.zzog());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + this.zzbo(this.zzabw) + " but received callback for step " + this.zzbo(n), (Throwable)new Exception());
            this.zzf(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }
    
    private String zzbo(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 0: {
                return "STEP_GETTING_SERVICE_BINDINGS";
            }
            case 1: {
                return "STEP_VALIDATING_ACCOUNT";
            }
            case 2: {
                return "STEP_AUTHENTICATING";
            }
            case 3: {
                return "STEP_GETTING_REMOTE_SERVICE";
            }
        }
    }
    
    private void zzc(final ConnectionResult connectionResult) {
        if (!this.zzbn(2)) {
            return;
        }
        if (connectionResult.isSuccess()) {
            this.zznT();
            return;
        }
        if (this.zze(connectionResult)) {
            this.zznV();
            this.zznT();
            return;
        }
        this.zzf(connectionResult);
    }
    
    private boolean zzd(final ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzaaP.zzbi(connectionResult.getErrorCode()) != null;
    }
    
    private boolean zze(final ConnectionResult connectionResult) {
        return this.zzabC == 2 || (this.zzabC == 1 && !connectionResult.hasResolution());
    }
    
    private void zzf(final ConnectionResult connectionResult) {
        this.zznW();
        this.zzY(!connectionResult.hasResolution());
        this.zzabr.zzach.clear();
        this.zzabr.zzg(connectionResult);
        if (!this.zzaaP.zzd(this.mContext, connectionResult.getErrorCode())) {
            this.zzabr.zzof();
        }
        if (!this.zzabx && !this.zzabr.zzoc()) {
            this.zzabr.zzabZ.zzi(connectionResult);
        }
        this.zzabx = false;
        this.zzabr.zzabZ.zzpk();
    }
    
    private boolean zznP() {
        --this.zzaby;
        if (this.zzaby > 0) {
            return false;
        }
        if (this.zzaby < 0) {
            Log.i("GoogleApiClientConnecting", this.zzabr.zzog());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", (Throwable)new Exception());
            this.zzf(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzabu != null) {
            this.zzf(this.zzabu);
            return false;
        }
        return true;
    }
    
    private void zznQ() {
        if (this.zzaby == 0) {
            if (!this.zzabD) {
                this.zznT();
                return;
            }
            if (this.zzabE) {
                this.zznR();
            }
        }
    }
    
    private void zznR() {
        final ArrayList<Api$zzb> list = new ArrayList<Api$zzb>();
        this.zzabw = 1;
        this.zzaby = this.zzabr.zzacg.size();
        for (final Api$zzc<?> api$zzc : this.zzabr.zzacg.keySet()) {
            if (this.zzabr.zzach.containsKey(api$zzc)) {
                if (!this.zznP()) {
                    continue;
                }
                this.zznS();
            }
            else {
                list.add(this.zzabr.zzacg.get(api$zzc));
            }
        }
        if (!list.isEmpty()) {
            this.zzabK.add(zzlk.zzoj().submit(new zzlg$zzh(list)));
        }
    }
    
    private void zznS() {
        this.zzabw = 2;
        this.zzabr.zzaci = this.zznX();
        this.zzabK.add(zzlk.zzoj().submit(new zzlg$zzc(this, null)));
    }
    
    private void zznT() {
        final ArrayList<Api$zzb> list = new ArrayList<Api$zzb>();
        this.zzabw = 3;
        this.zzaby = this.zzabr.zzacg.size();
        for (final Api$zzc<?> api$zzc : this.zzabr.zzacg.keySet()) {
            if (this.zzabr.zzach.containsKey(api$zzc)) {
                if (!this.zznP()) {
                    continue;
                }
                this.zznU();
            }
            else {
                list.add(this.zzabr.zzacg.get(api$zzc));
            }
        }
        if (!list.isEmpty()) {
            this.zzabK.add(zzlk.zzoj().submit(new zzlg$zzf(list)));
        }
    }
    
    private void zznU() {
        this.zzabr.zzob();
        zzlk.zzoj().execute(new zzlg$1(this));
        if (this.zzabB != null) {
            if (this.zzabG) {
                this.zzabB.zza(this.zzabF, this.zzabH);
            }
            this.zzY(false);
        }
        final Iterator<Api$zzc<?>> iterator = this.zzabr.zzach.keySet().iterator();
        while (iterator.hasNext()) {
            this.zzabr.zzacg.get(iterator.next()).disconnect();
        }
        if (this.zzabx) {
            this.zzabx = false;
            this.disconnect();
            return;
        }
        Bundle zzabz;
        if (this.zzabz.isEmpty()) {
            zzabz = null;
        }
        else {
            zzabz = this.zzabz;
        }
        this.zzabr.zzabZ.zzh(zzabz);
    }
    
    private void zznV() {
        this.zzabD = false;
        this.zzabr.zzaci = Collections.emptySet();
        for (final Api$zzc<?> api$zzc : this.zzabA) {
            if (!this.zzabr.zzach.containsKey(api$zzc)) {
                this.zzabr.zzach.put(api$zzc, new ConnectionResult(17, null));
            }
        }
    }
    
    private void zznW() {
        final Iterator<Future<?>> iterator = this.zzabK.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel(true);
        }
        this.zzabK.clear();
    }
    
    private Set<Scope> zznX() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzabI.zzoK());
        final Map<Api<?>, zzf$zza> zzoM = this.zzabI.zzoM();
        for (final Api<?> api : zzoM.keySet()) {
            if (!this.zzabr.zzach.containsKey(api.zznx())) {
                set.addAll(zzoM.get(api).zzTm);
            }
        }
        return (Set<Scope>)set;
    }
    
    @Override
    public void begin() {
        this.zzabr.zzabZ.zzpl();
        this.zzabr.zzach.clear();
        this.zzabx = false;
        this.zzabD = false;
        this.zzabu = null;
        this.zzabw = 0;
        this.zzabC = 2;
        this.zzabE = false;
        this.zzabG = false;
        final HashMap<Api$zzb, zzlg$zzd> hashMap = new HashMap<Api$zzb, zzlg$zzd>();
        final Iterator<Api<?>> iterator = this.zzabJ.keySet().iterator();
        int n = false ? 1 : 0;
        while (iterator.hasNext()) {
            final Api<?> api = iterator.next();
            final Api$zzb api$zzb = this.zzabr.zzacg.get(api.zznx());
            final int intValue = this.zzabJ.get(api);
            boolean b;
            if (api.zznv().getPriority() == 1) {
                b = true;
            }
            else {
                b = false;
            }
            if (api$zzb.zzlN()) {
                this.zzabD = true;
                if (intValue < this.zzabC) {
                    this.zzabC = intValue;
                }
                if (intValue != 0) {
                    this.zzabA.add(api.zznx());
                }
            }
            hashMap.put(api$zzb, new zzlg$zzd(this, api, intValue));
            n |= (b ? 1 : 0);
        }
        if (n != 0) {
            this.zzabD = false;
        }
        if (this.zzabD) {
            this.zzabI.zza(this.zzabr.getSessionId());
            final zzlg$zzg zzlg$zzg = new zzlg$zzg(this, null);
            this.zzabB = (zzqw)this.zzaaQ.zza(this.mContext, this.zzabr.getLooper(), this.zzabI, this.zzabI.zzoQ(), zzlg$zzg, zzlg$zzg);
        }
        this.zzaby = this.zzabr.zzacg.size();
        this.zzabK.add(zzlk.zzoj().submit(new zzlg$zze((Map<Api$zzb, GoogleApiClient$zza>)hashMap)));
    }
    
    @Override
    public void connect() {
        this.zzabx = false;
    }
    
    @Override
    public void disconnect() {
        final Iterator<zzli$zzf> iterator = (Iterator<zzli$zzf>)this.zzabr.zzaca.iterator();
        while (iterator.hasNext()) {
            final zzli$zzf zzli$zzf = iterator.next();
            if (zzli$zzf.zznK() != 1) {
                zzli$zzf.cancel();
                iterator.remove();
            }
        }
        this.zzabr.zznY();
        if (this.zzabu == null && !this.zzabr.zzaca.isEmpty()) {
            this.zzabx = true;
            return;
        }
        this.zznW();
        this.zzY(true);
        this.zzabr.zzach.clear();
        this.zzabr.zzg(null);
        this.zzabr.zzabZ.zzpk();
    }
    
    @Override
    public String getName() {
        return "CONNECTING";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.zzbn(3)) {
            if (bundle != null) {
                this.zzabz.putAll(bundle);
            }
            if (this.zznP()) {
                this.zznU();
            }
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzf(new ConnectionResult(8, null));
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzlb$zza<R, A>> T zza(final T t) {
        this.zzabr.zzaca.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
        if (this.zzbn(3)) {
            this.zzb(connectionResult, api, n);
            if (this.zznP()) {
                this.zznU();
            }
        }
    }
    
    @Override
    public <A extends Api$zzb, T extends zzlb$zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
