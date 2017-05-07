// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzf;
import java.util.Collection;
import com.google.android.gms.common.internal.zzx;
import android.os.Handler;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.internal.zzme;
import java.util.HashSet;
import java.util.ArrayList;
import com.google.android.gms.internal.zzqx;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.accounts.Account;
import android.content.Context;

public final class GoogleApiClient$Builder
{
    private final Context mContext;
    private Account zzQd;
    private String zzRq;
    private final Set<Scope> zzaaF;
    private int zzaaG;
    private View zzaaH;
    private String zzaaI;
    private final Map<Api<?>, zzf$zza> zzaaJ;
    private final Map<Api<?>, Api$ApiOptions> zzaaK;
    private FragmentActivity zzaaL;
    private int zzaaM;
    private GoogleApiClient$OnConnectionFailedListener zzaaN;
    private Looper zzaaO;
    private GoogleApiAvailability zzaaP;
    private Api$zza<? extends zzqw, zzqx> zzaaQ;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> zzaaR;
    private final ArrayList<GoogleApiClient$OnConnectionFailedListener> zzaaS;
    private zzqx zzaaT;
    
    public GoogleApiClient$Builder(final Context mContext) {
        this.zzaaF = new HashSet<Scope>();
        this.zzaaJ = new zzme<Api<?>, zzf$zza>();
        this.zzaaK = new zzme<Api<?>, Api$ApiOptions>();
        this.zzaaM = -1;
        this.zzaaP = GoogleApiAvailability.getInstance();
        this.zzaaQ = zzqu.zzRl;
        this.zzaaR = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzaaS = new ArrayList<GoogleApiClient$OnConnectionFailedListener>();
        this.mContext = mContext;
        this.zzaaO = mContext.getMainLooper();
        this.zzRq = mContext.getPackageName();
        this.zzaaI = mContext.getClass().getName();
    }
    
    private void zza(final zzlp zzlp, final GoogleApiClient googleApiClient) {
        zzlp.zza(this.zzaaM, googleApiClient, this.zzaaN);
    }
    
    private GoogleApiClient zznC() {
        final zzli zzli = new zzli(this.mContext.getApplicationContext(), this.zzaaO, this.zznB(), this.zzaaP, this.zzaaQ, this.zzaaK, this.zzaaR, this.zzaaS, this.zzaaM);
        final zzlp zza = zzlp.zza(this.zzaaL);
        if (zza == null) {
            new Handler(this.mContext.getMainLooper()).post((Runnable)new GoogleApiClient$Builder$1(this, zzli));
            return zzli;
        }
        this.zza(zza, zzli);
        return zzli;
    }
    
    public GoogleApiClient$Builder addApi(final Api<? extends Api$ApiOptions$NotRequiredOptions> api) {
        zzx.zzb(api, "Api must not be null");
        this.zzaaK.put(api, null);
        this.zzaaF.addAll(api.zznv().zzm((Api$ApiOptions$NotRequiredOptions)null));
        return this;
    }
    
    public <O extends Api$ApiOptions$HasOptions> GoogleApiClient$Builder addApi(final Api<O> api, final O o) {
        zzx.zzb(api, "Api must not be null");
        zzx.zzb(o, "Null options are not permitted for this Api");
        this.zzaaK.put((Api<?>)api, (Object)o);
        this.zzaaF.addAll(api.zznv().zzm(o));
        return this;
    }
    
    public GoogleApiClient$Builder addConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzx.zzb(googleApiClient$ConnectionCallbacks, "Listener must not be null");
        this.zzaaR.add(googleApiClient$ConnectionCallbacks);
        return this;
    }
    
    public GoogleApiClient$Builder addOnConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzb(googleApiClient$OnConnectionFailedListener, "Listener must not be null");
        this.zzaaS.add(googleApiClient$OnConnectionFailedListener);
        return this;
    }
    
    public GoogleApiClient build() {
        zzx.zzb(!this.zzaaK.isEmpty(), "must call addApi() to add at least one API");
        if (this.zzaaM >= 0) {
            return this.zznC();
        }
        return new zzli(this.mContext, this.zzaaO, this.zznB(), this.zzaaP, this.zzaaQ, this.zzaaK, this.zzaaR, this.zzaaS, -1);
    }
    
    public zzf zznB() {
        if (this.zzaaK.containsKey(zzqu.API)) {
            zzx.zza(this.zzaaT == null, "SignIn.API can't be used in conjunction with requestServerAuthCode.");
            this.zzaaT = this.zzaaK.get(zzqu.API);
        }
        final Account zzQd = this.zzQd;
        final Set<Scope> zzaaF = this.zzaaF;
        final Map<Api<?>, zzf$zza> zzaaJ = this.zzaaJ;
        final int zzaaG = this.zzaaG;
        final View zzaaH = this.zzaaH;
        final String zzRq = this.zzRq;
        final String zzaaI = this.zzaaI;
        zzqx zzqx;
        if (this.zzaaT != null) {
            zzqx = this.zzaaT;
        }
        else {
            zzqx = com.google.android.gms.internal.zzqx.zzaUZ;
        }
        return new zzf(zzQd, zzaaF, zzaaJ, zzaaG, zzaaH, zzRq, zzaaI, zzqx);
    }
}
