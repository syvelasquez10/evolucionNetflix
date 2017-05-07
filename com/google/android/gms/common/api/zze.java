// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Map;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;

public class zze implements zzh
{
    private final Context mContext;
    private final Api$zza<? extends zzps, zzpt> zzWD;
    private final zzg zzWJ;
    private final Lock zzWK;
    private ConnectionResult zzWL;
    private int zzWM;
    private int zzWN;
    private boolean zzWO;
    private int zzWP;
    private final Bundle zzWQ;
    private final Set<Api$ClientKey> zzWR;
    private zzps zzWS;
    private int zzWT;
    private boolean zzWU;
    private boolean zzWV;
    private IAccountAccessor zzWW;
    private boolean zzWX;
    private boolean zzWY;
    private final com.google.android.gms.common.internal.zze zzWZ;
    private final Map<Api<?>, Integer> zzXa;
    
    public zze(final zzg zzWJ, final com.google.android.gms.common.internal.zze zzWZ, final Map<Api<?>, Integer> zzXa, final Api$zza<? extends zzps, zzpt> zzWD, final Lock zzWK, final Context mContext) {
        this.zzWN = 0;
        this.zzWO = false;
        this.zzWQ = new Bundle();
        this.zzWR = new HashSet<Api$ClientKey>();
        this.zzWJ = zzWJ;
        this.zzWZ = zzWZ;
        this.zzXa = zzXa;
        this.zzWD = zzWD;
        this.zzWK = zzWK;
        this.mContext = mContext;
    }
    
    private void zzT(final boolean b) {
        if (this.zzWS != null) {
            if (this.zzWS.isConnected()) {
                if (b) {
                    this.zzWS.zzxW();
                }
                this.zzWS.disconnect();
            }
            this.zzWW = null;
        }
    }
    
    private void zza(final ConnectionResult connectionResult) {
        while (true) {
            this.zzWK.lock();
            Label_0081: {
                try {
                    if (!this.zzaW(2)) {
                        return;
                    }
                    if (connectionResult.isSuccess()) {
                        this.zzmE();
                    }
                    else {
                        if (!this.zzc(connectionResult)) {
                            break Label_0081;
                        }
                        this.zzmG();
                        this.zzmE();
                    }
                    return;
                }
                finally {
                    this.zzWK.unlock();
                }
            }
            final ConnectionResult connectionResult2;
            this.zzd(connectionResult2);
        }
    }
    
    private void zza(final ResolveAccountResponse resolveAccountResponse) {
        while (true) {
            final ConnectionResult zznY = resolveAccountResponse.zznY();
            this.zzWK.lock();
            Label_0122: {
                try {
                    if (!this.zzaW(0)) {
                        return;
                    }
                    if (zznY.isSuccess()) {
                        this.zzWW = resolveAccountResponse.zznX();
                        this.zzWV = true;
                        this.zzWX = resolveAccountResponse.zznZ();
                        this.zzWY = resolveAccountResponse.zzoa();
                        this.zzmC();
                    }
                    else {
                        if (!this.zzc(zznY)) {
                            break Label_0122;
                        }
                        this.zzmG();
                        if (this.zzWP == 0) {
                            this.zzmE();
                        }
                    }
                    return;
                }
                finally {
                    this.zzWK.unlock();
                }
            }
            this.zzd(zznY);
        }
    }
    
    private boolean zza(final int n, final int n2, final ConnectionResult connectionResult) {
        return (n2 != 1 || zzb(connectionResult)) && (this.zzWL == null || n < this.zzWM);
    }
    
    private boolean zzaW(final int n) {
        if (this.zzWN != n) {
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + this.zzaX(this.zzWN) + " but received callback for step " + this.zzaX(n));
            this.zzd(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }
    
    private String zzaX(final int n) {
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
    
    private void zzb(final ConnectionResult zzWL, final Api<?> api, final int n) {
        if (n != 2) {
            final int priority = api.zzmn().getPriority();
            if (this.zza(priority, n, zzWL)) {
                this.zzWL = zzWL;
                this.zzWM = priority;
            }
        }
        this.zzWJ.zzXu.put(api.zzmq(), zzWL);
    }
    
    private static boolean zzb(final ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || GooglePlayServicesUtil.zzaT(connectionResult.getErrorCode()) != null;
    }
    
    private boolean zzc(final ConnectionResult connectionResult) {
        return this.zzWT == 2 || (this.zzWT == 1 && !connectionResult.hasResolution());
    }
    
    private void zzd(final ConnectionResult zzWL) {
        boolean b = false;
        this.zzWO = false;
        this.zzWJ.zzXv.clear();
        this.zzWL = zzWL;
        if (!zzWL.hasResolution()) {
            b = true;
        }
        this.zzT(b);
        this.zzaV(3);
        if (!this.zzWJ.zzmM() || !GooglePlayServicesUtil.zze(this.mContext, zzWL.getErrorCode())) {
            this.zzWJ.zzmP();
            this.zzWJ.zzXm.zzh(zzWL);
        }
        this.zzWJ.zzXm.zznR();
    }
    
    private boolean zzmA() {
        --this.zzWP;
        if (this.zzWP > 0) {
            return false;
        }
        if (this.zzWP < 0) {
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.");
            this.zzd(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzWL != null) {
            this.zzd(this.zzWL);
            return false;
        }
        return true;
    }
    
    private void zzmB() {
        if (this.zzWU) {
            this.zzmC();
            return;
        }
        this.zzmE();
    }
    
    private void zzmC() {
        if (this.zzWV && this.zzWP == 0) {
            this.zzWN = 1;
            this.zzWP = this.zzWJ.zzXt.size();
            for (final Api$ClientKey<?> api$ClientKey : this.zzWJ.zzXt.keySet()) {
                if (this.zzWJ.zzXu.containsKey(api$ClientKey)) {
                    if (!this.zzmA()) {
                        continue;
                    }
                    this.zzmD();
                }
                else {
                    this.zzWJ.zzXt.get(api$ClientKey).validateAccount(this.zzWW);
                }
            }
        }
    }
    
    private void zzmD() {
        this.zzWN = 2;
        this.zzWJ.zzXv = this.zzmH();
        this.zzWS.zza(this.zzWW, this.zzWJ.zzXv, new zze$zza(this));
    }
    
    private void zzmE() {
        Set<Scope> set = this.zzWJ.zzXv;
        if (set.isEmpty()) {
            set = this.zzmH();
        }
        this.zzWN = 3;
        this.zzWP = this.zzWJ.zzXt.size();
        for (final Api$ClientKey<?> api$ClientKey : this.zzWJ.zzXt.keySet()) {
            if (this.zzWJ.zzXu.containsKey(api$ClientKey)) {
                if (!this.zzmA()) {
                    continue;
                }
                this.zzmF();
            }
            else {
                this.zzWJ.zzXt.get(api$ClientKey).getRemoteService(this.zzWW, set);
            }
        }
    }
    
    private void zzmF() {
        this.zzWJ.zzmL();
        if (this.zzWS != null) {
            if (this.zzWX) {
                this.zzWS.zza(this.zzWW, this.zzWY);
            }
            this.zzT(false);
        }
        final Iterator<Api$ClientKey<?>> iterator = this.zzWJ.zzXu.keySet().iterator();
        while (iterator.hasNext()) {
            this.zzWJ.zzXt.get(iterator.next()).disconnect();
        }
        if (this.zzWO) {
            this.zzWO = false;
            this.zzaV(-1);
            return;
        }
        Bundle zzWQ;
        if (this.zzWQ.isEmpty()) {
            zzWQ = null;
        }
        else {
            zzWQ = this.zzWQ;
        }
        this.zzWJ.zzXm.zzg(zzWQ);
    }
    
    private void zzmG() {
        this.zzWU = false;
        this.zzWJ.zzXv.clear();
        for (final Api$ClientKey<?> api$ClientKey : this.zzWR) {
            if (!this.zzWJ.zzXu.containsKey(api$ClientKey)) {
                this.zzWJ.zzXu.put(api$ClientKey, new ConnectionResult(17, null));
            }
        }
    }
    
    private Set<Scope> zzmH() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzWZ.zznt());
        final Map<Api<?>, com.google.android.gms.common.internal.zze$zza> zznv = this.zzWZ.zznv();
        for (final Api<?> api : zznv.keySet()) {
            if (!this.zzWJ.zzXu.containsKey(api.zzmq())) {
                set.addAll(zznv.get(api).zzWI);
            }
        }
        return (Set<Scope>)set;
    }
    
    @Override
    public void begin() {
        this.zzWJ.zzXm.zznS();
        this.zzWJ.zzXu.clear();
        this.zzWO = false;
        this.zzWU = false;
        this.zzWL = null;
        this.zzWN = 0;
        this.zzWT = 2;
        this.zzWV = false;
        this.zzWX = false;
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.zzWJ.zzXr.post((Runnable)new zze$1(this, new ConnectionResult(googlePlayServicesAvailable, null)));
        }
        else {
            final HashMap<Api$Client, zze$zzc> hashMap = (HashMap<Api$Client, zze$zzc>)new HashMap<Object, zze$zzc>();
            final Iterator<Api<?>> iterator = this.zzXa.keySet().iterator();
            int n = false ? 1 : 0;
            while (iterator.hasNext()) {
                final Api<?> api = iterator.next();
                final Api$Client api$Client = this.zzWJ.zzXt.get(api.zzmq());
                final int intValue = this.zzXa.get(api);
                boolean b;
                if (api.zzmn().getPriority() == 1) {
                    b = true;
                }
                else {
                    b = false;
                }
                if (api$Client.requiresSignIn()) {
                    this.zzWU = true;
                    if (intValue < this.zzWT) {
                        this.zzWT = intValue;
                    }
                    if (intValue != 0) {
                        this.zzWR.add(api.zzmq());
                    }
                }
                hashMap.put(api$Client, new zze$zzc(this, api, intValue));
                n |= (b ? 1 : 0);
            }
            if (n != 0) {
                this.zzWU = false;
            }
            if (this.zzWU) {
                this.zzWZ.zza(this.zzWJ.getSessionId());
                final zze$zzd zze$zzd = new zze$zzd(this, null);
                (this.zzWS = (zzps)this.zzWD.zza(this.mContext, this.zzWJ.getLooper(), this.zzWZ, this.zzWZ.zznz(), zze$zzd, zze$zzd)).connect();
            }
            this.zzWP = this.zzWJ.zzXt.size();
            for (final Api$Client api$Client2 : this.zzWJ.zzXt.values()) {
                api$Client2.connect(hashMap.get(api$Client2));
            }
        }
    }
    
    @Override
    public void connect() {
        this.zzWO = false;
    }
    
    @Override
    public String getName() {
        return "CONNECTING";
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.zzaW(3)) {
            if (bundle != null) {
                this.zzWQ.putAll(bundle);
            }
            if (this.zzmA()) {
                this.zzmF();
            }
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzd(new ConnectionResult(8, null));
    }
    
    @Override
    public <A extends Api$Client, R extends Result, T extends zza$zza<R, A>> T zza(final T t) {
        this.zzWJ.zzXn.add(t);
        return t;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
        if (this.zzaW(3)) {
            this.zzb(connectionResult, api, n);
            if (this.zzmA()) {
                this.zzmF();
            }
        }
    }
    
    @Override
    public void zzaV(final int n) {
        if (n == -1) {
            final Iterator<zzg$zze> iterator = (Iterator<zzg$zze>)this.zzWJ.zzXn.iterator();
            while (iterator.hasNext()) {
                final zzg$zze zzg$zze = iterator.next();
                if (zzg$zze.zzmt() != 1) {
                    zzg$zze.cancel();
                    iterator.remove();
                }
            }
            this.zzWJ.zzmI();
            if (this.zzWL == null && !this.zzWJ.zzXn.isEmpty()) {
                this.zzWO = true;
                return;
            }
            this.zzWJ.zzXu.clear();
            this.zzWL = null;
            this.zzT(true);
        }
        this.zzWJ.zze(this.zzWL);
    }
    
    @Override
    public <A extends Api$Client, T extends zza$zza<? extends Result, A>> T zzb(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
