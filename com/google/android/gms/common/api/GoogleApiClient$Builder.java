// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzu;
import java.util.Collection;
import com.google.android.gms.internal.zzpq;
import java.util.HashMap;
import java.util.HashSet;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zze$zza;
import java.util.Map;
import android.view.View;
import android.os.Looper;
import com.google.android.gms.internal.zzpt$zza;
import java.util.Set;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.accounts.Account;
import android.content.Context;

public final class GoogleApiClient$Builder
{
    private final Context mContext;
    private Account zzMY;
    private String zzOd;
    private String zzOe;
    private int zzWA;
    private int zzWB;
    private GoogleApiClient$OnConnectionFailedListener zzWC;
    private Api$zza<? extends zzps, zzpt> zzWD;
    private final Set<GoogleApiClient$ConnectionCallbacks> zzWE;
    private final Set<GoogleApiClient$OnConnectionFailedListener> zzWF;
    private zzpt$zza zzWG;
    private Looper zzWs;
    private final Set<Scope> zzWu;
    private int zzWv;
    private View zzWw;
    private final Map<Api<?>, zze$zza> zzWx;
    private final Map<Api<?>, Api$ApiOptions> zzWy;
    private FragmentActivity zzWz;
    
    public GoogleApiClient$Builder(final Context mContext) {
        this.zzWu = new HashSet<Scope>();
        this.zzWx = new HashMap<Api<?>, zze$zza>();
        this.zzWy = new HashMap<Api<?>, Api$ApiOptions>();
        this.zzWA = -1;
        this.zzWB = -1;
        this.zzWE = new HashSet<GoogleApiClient$ConnectionCallbacks>();
        this.zzWF = new HashSet<GoogleApiClient$OnConnectionFailedListener>();
        this.zzWG = new zzpt$zza();
        this.mContext = mContext;
        this.zzWs = mContext.getMainLooper();
        this.zzOe = mContext.getPackageName();
        this.zzOd = mContext.getClass().getName();
        this.zzWD = zzpq.zzNY;
    }
    
    private GoogleApiClient zzmw() {
        final zzm zza = zzm.zza(this.zzWz);
        final zzg zzg = new zzg(this.mContext.getApplicationContext(), this.zzWs, this.zzmv(), this.zzWD, this.zzWy, this.zzWE, this.zzWF, this.zzWA, -1);
        zza.zza(this.zzWA, zzg, this.zzWC);
        return zzg;
    }
    
    private GoogleApiClient zzmx() {
        final zzn zzb = zzn.zzb(this.zzWz);
        GoogleApiClient zzbc;
        if ((zzbc = zzb.zzbc(this.zzWB)) == null) {
            zzbc = new zzg(this.mContext.getApplicationContext(), this.zzWs, this.zzmv(), this.zzWD, this.zzWy, this.zzWE, this.zzWF, -1, this.zzWB);
        }
        zzb.zza(this.zzWB, zzbc, this.zzWC);
        return zzbc;
    }
    
    public GoogleApiClient$Builder addApi(final Api<? extends Api$ApiOptions$NotRequiredOptions> api) {
        this.zzWy.put(api, null);
        this.zzWu.addAll(api.zzmp());
        return this;
    }
    
    public <O extends Api$ApiOptions$HasOptions> GoogleApiClient$Builder addApi(final Api<O> api, final O o) {
        zzu.zzb(o, "Null options are not permitted for this Api");
        this.zzWy.put((Api<?>)api, (Object)o);
        this.zzWu.addAll(api.zzmp());
        return this;
    }
    
    public GoogleApiClient$Builder addConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzWE.add(googleApiClient$ConnectionCallbacks);
        return this;
    }
    
    public GoogleApiClient$Builder addOnConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzWF.add(googleApiClient$OnConnectionFailedListener);
        return this;
    }
    
    public GoogleApiClient build() {
        zzu.zzb(!this.zzWy.isEmpty(), "must call addApi() to add at least one API");
        if (this.zzWA >= 0) {
            return this.zzmw();
        }
        if (this.zzWB >= 0) {
            return this.zzmx();
        }
        return new zzg(this.mContext, this.zzWs, this.zzmv(), this.zzWD, this.zzWy, this.zzWE, this.zzWF, -1, -1);
    }
    
    public zze zzmv() {
        return new zze(this.zzMY, this.zzWu, this.zzWx, this.zzWv, this.zzWw, this.zzOe, this.zzOd, this.zzWG.zzya());
    }
}
