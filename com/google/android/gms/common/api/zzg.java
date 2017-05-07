// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;

public class zzg implements zzj
{
    private final Context mContext;
    private zzd zzZA;
    private int zzZB;
    private boolean zzZC;
    private boolean zzZD;
    private zzp zzZE;
    private boolean zzZF;
    private boolean zzZG;
    private final zzf zzZH;
    private final Map<Api<?>, Integer> zzZI;
    private ArrayList<Future<?>> zzZJ;
    private final GoogleApiAvailability zzZi;
    private final Api$zza<? extends zzd, zze> zzZj;
    private final zzi zzZq;
    private final Lock zzZs;
    private ConnectionResult zzZt;
    private int zzZu;
    private int zzZv;
    private boolean zzZw;
    private int zzZx;
    private final Bundle zzZy;
    private final Set<Api$zzc> zzZz;
    
    public zzg(final zzi zzZq, final zzf zzZH, final Map<Api<?>, Integer> zzZI, final GoogleApiAvailability zzZi, final Api$zza<? extends zzd, zze> zzZj, final Lock zzZs, final Context mContext) {
        this.zzZv = 0;
        this.zzZw = false;
        this.zzZy = new Bundle();
        this.zzZz = new HashSet<Api$zzc>();
        this.zzZJ = new ArrayList<Future<?>>();
        this.zzZq = zzZq;
        this.zzZH = zzZH;
        this.zzZI = zzZI;
        this.zzZi = zzZi;
        this.zzZj = zzZj;
        this.zzZs = zzZs;
        this.mContext = mContext;
    }
    
    private void zzX(final boolean b) {
        if (this.zzZA != null) {
            if (this.zzZA.isConnected() && b) {
                this.zzZA.zzzn();
            }
            this.zzZA.disconnect();
            this.zzZE = null;
        }
    }
    
    private void zza(final ResolveAccountResponse resolveAccountResponse) {
        if (!this.zzbe(0)) {
            return;
        }
        final ConnectionResult zzoP = resolveAccountResponse.zzoP();
        if (zzoP.isSuccess()) {
            this.zzZE = resolveAccountResponse.zzoO();
            this.zzZD = true;
            this.zzZF = resolveAccountResponse.zzoQ();
            this.zzZG = resolveAccountResponse.zzoR();
            this.zznp();
            return;
        }
        if (this.zze(zzoP)) {
            this.zznu();
            this.zznp();
            return;
        }
        this.zzf(zzoP);
    }
    
    private boolean zza(final int n, final int n2, final ConnectionResult connectionResult) {
        return (n2 != 1 || this.zzd(connectionResult)) && (this.zzZt == null || n < this.zzZu);
    }
    
    private void zzb(final ConnectionResult zzZt, final Api<?> api, final int n) {
        if (n != 2) {
            final int priority = api.zznb().getPriority();
            if (this.zza(priority, n, zzZt)) {
                this.zzZt = zzZt;
                this.zzZu = priority;
            }
        }
        this.zzZq.zzaag.put(api.zznd(), zzZt);
    }
    
    private boolean zzbe(final int n) {
        if (this.zzZv != n) {
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + this.zzbf(this.zzZv) + " but received callback for step " + this.zzbf(n));
            this.zzf(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }
    
    private String zzbf(final int n) {
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
        if (!this.zzbe(2)) {
            return;
        }
        if (connectionResult.isSuccess()) {
            this.zzns();
            return;
        }
        if (this.zze(connectionResult)) {
            this.zznu();
            this.zzns();
            return;
        }
        this.zzf(connectionResult);
    }
    
    private boolean zzd(final ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzZi.zzbb(connectionResult.getErrorCode()) != null;
    }
    
    private boolean zze(final ConnectionResult connectionResult) {
        return this.zzZB == 2 || (this.zzZB == 1 && !connectionResult.hasResolution());
    }
    
    private void zzf(final ConnectionResult connectionResult) {
        boolean b = false;
        this.zzZw = false;
        this.zznv();
        if (!connectionResult.hasResolution()) {
            b = true;
        }
        this.zzX(b);
        this.zzZq.zzaag.clear();
        this.zzZq.zzg(connectionResult);
        if (!this.zzZq.zznB() || !this.zzZi.zzd(this.mContext, connectionResult.getErrorCode())) {
            this.zzZq.zznE();
            this.zzZq.zzZY.zzj(connectionResult);
        }
        this.zzZq.zzZY.zzoI();
    }
    
    private boolean zzno() {
        --this.zzZx;
        if (this.zzZx > 0) {
            return false;
        }
        if (this.zzZx < 0) {
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.");
            this.zzf(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzZt != null) {
            this.zzf(this.zzZt);
            return false;
        }
        return true;
    }
    
    private void zznp() {
        if (this.zzZx == 0) {
            if (!this.zzZC) {
                this.zzns();
                return;
            }
            if (this.zzZD) {
                this.zznq();
            }
        }
    }
    
    private void zznq() {
        final ArrayList<Api$zzb> list = new ArrayList<Api$zzb>();
        this.zzZv = 1;
        this.zzZx = this.zzZq.zzaaf.size();
        for (final Api$zzc<?> api$zzc : this.zzZq.zzaaf.keySet()) {
            if (this.zzZq.zzaag.containsKey(api$zzc)) {
                if (!this.zzno()) {
                    continue;
                }
                this.zznr();
            }
            else {
                list.add(this.zzZq.zzaaf.get(api$zzc));
            }
        }
        if (!list.isEmpty()) {
            this.zzZJ.add(zzk.zznF().submit(new zzg$zzh(list)));
        }
    }
    
    private void zznr() {
        this.zzZv = 2;
        this.zzZq.zzaah = this.zznw();
        this.zzZJ.add(zzk.zznF().submit(new zzg$zzc(this, null)));
    }
    
    private void zzns() {
        final ArrayList<Api$zzb> list = new ArrayList<Api$zzb>();
        this.zzZv = 3;
        this.zzZx = this.zzZq.zzaaf.size();
        for (final Api$zzc<?> api$zzc : this.zzZq.zzaaf.keySet()) {
            if (this.zzZq.zzaag.containsKey(api$zzc)) {
                if (!this.zzno()) {
                    continue;
                }
                this.zznt();
            }
            else {
                list.add(this.zzZq.zzaaf.get(api$zzc));
            }
        }
        if (!list.isEmpty()) {
            this.zzZJ.add(zzk.zznF().submit(new zzg$zzf(list)));
        }
    }
    
    private void zznt() {
        this.zzZq.zznA();
        zzk.zznF().execute(new zzg$1(this));
        if (this.zzZA != null) {
            if (this.zzZF) {
                this.zzZA.zza(this.zzZE, this.zzZG);
            }
            this.zzX(false);
        }
        final Iterator<Api$zzc<?>> iterator = this.zzZq.zzaag.keySet().iterator();
        while (iterator.hasNext()) {
            this.zzZq.zzaaf.get(iterator.next()).disconnect();
        }
        if (this.zzZw) {
            this.zzZw = false;
            this.disconnect();
            return;
        }
        Bundle zzZy;
        if (this.zzZy.isEmpty()) {
            zzZy = null;
        }
        else {
            zzZy = this.zzZy;
        }
        this.zzZq.zzZY.zzh(zzZy);
    }
    
    private void zznu() {
        this.zzZC = false;
        this.zzZq.zzaah = Collections.emptySet();
        for (final Api$zzc<?> api$zzc : this.zzZz) {
            if (!this.zzZq.zzaag.containsKey(api$zzc)) {
                this.zzZq.zzaag.put(api$zzc, new ConnectionResult(17, null));
            }
        }
    }
    
    private void zznv() {
        final Iterator<Future<?>> iterator = this.zzZJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel(true);
        }
        this.zzZJ.clear();
    }
    
    private Set<Scope> zznw() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzZH.zzoi());
        final Map<Api<?>, zzf$zza> zzok = this.zzZH.zzok();
        for (final Api<?> api : zzok.keySet()) {
            if (!this.zzZq.zzaag.containsKey(api.zznd())) {
                set.addAll(zzok.get(api).zzZp);
            }
        }
        return (Set<Scope>)set;
    }
    
    @Override
    public void begin() {
        this.zzZq.zzZY.zzoJ();
        this.zzZq.zzaag.clear();
        this.zzZw = false;
        this.zzZC = false;
        this.zzZt = null;
        this.zzZv = 0;
        this.zzZB = 2;
        this.zzZD = false;
        this.zzZF = false;
        final HashMap<Api$zzb, zzg$zzd> hashMap = new HashMap<Api$zzb, zzg$zzd>();
        final Iterator<Api<?>> iterator = this.zzZI.keySet().iterator();
        int n = false ? 1 : 0;
        while (iterator.hasNext()) {
            final Api<?> api = iterator.next();
            final Api$zzb api$zzb = this.zzZq.zzaaf.get(api.zznd());
            final int intValue = this.zzZI.get(api);
            boolean b;
            if (api.zznb().getPriority() == 1) {
                b = true;
            }
            else {
                b = false;
            }
            if (api$zzb.zzlm()) {
                this.zzZC = true;
                if (intValue < this.zzZB) {
                    this.zzZB = intValue;
                }
                if (intValue != 0) {
                    this.zzZz.add(api.zznd());
                }
            }
            hashMap.put(api$zzb, new zzg$zzd(this, api, intValue));
            n |= (b ? 1 : 0);
        }
        if (n != 0) {
            this.zzZC = false;
        }
        if (this.zzZC) {
            this.zzZH.zza(this.zzZq.getSessionId());
            final zzg$zzg zzg$zzg = new zzg$zzg(this, null);
            this.zzZA = (zzd)this.zzZj.zza(this.mContext, this.zzZq.getLooper(), this.zzZH, this.zzZH.zzoo(), zzg$zzg, zzg$zzg);
        }
        this.zzZx = this.zzZq.zzaaf.size();
        this.zzZJ.add(zzk.zznF().submit(new zzg$zze((Map<Api$zzb, GoogleApiClient$zza>)hashMap)));
    }
    
    @Override
    public void connect() {
        this.zzZw = false;
    }
    
    @Override
    public void disconnect() {
        final Iterator<zzi$zze> iterator = (Iterator<zzi$zze>)this.zzZq.zzZZ.iterator();
        while (iterator.hasNext()) {
            final zzi$zze zzi$zze = iterator.next();
            if (zzi$zze.zzng() != 1) {
                zzi$zze.cancel();
                iterator.remove();
            }
        }
        this.zzZq.zznx();
        if (this.zzZt == null && !this.zzZq.zzZZ.isEmpty()) {
            this.zzZw = true;
            return;
        }
        this.zznv();
        this.zzX(true);
        this.zzZq.zzaag.clear();
        this.zzZq.zzg(null);
        this.zzZq.zzZY.zzoI();
    }
    
    @Override
    public String getName() {
        return "CONNECTING";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.zzbe(3)) {
            if (bundle != null) {
                this.zzZy.putAll(bundle);
            }
            if (this.zzno()) {
                this.zznt();
            }
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzf(new ConnectionResult(8, null));
    }
    
    @Override
    public <A extends Api$zzb, R extends Result, T extends zzc$zza<R, A>> T zza(final T t) {
        this.zzZq.zzZZ.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
        if (this.zzbe(3)) {
            this.zzb(connectionResult, api, n);
            if (this.zzno()) {
                this.zznt();
            }
        }
    }
    
    @Override
    public <A extends Api$zzb, T extends zzc$zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
