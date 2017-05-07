// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import java.util.Collection;
import android.os.Handler;
import com.google.android.gms.signin.zzb;
import com.google.android.gms.internal.zzld;
import java.util.HashSet;
import com.google.android.gms.signin.zze$zza;
import java.util.ArrayList;
import com.google.android.gms.signin.zze;
import com.google.android.gms.signin.zzd;
import com.google.android.gms.common.GoogleApiAvailability;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.os.Looper;
import android.accounts.Account;
import android.content.Context;

public final class GoogleApiClient$Builder
{
    private final Context mContext;
    private Account zzOY;
    private String zzQl;
    private Looper zzYV;
    private final Set<Scope> zzYY;
    private int zzYZ;
    private View zzZa;
    private String zzZb;
    private final Map<Api<?>, zzf$zza> zzZc;
    private final Map<Api<?>, Api$ApiOptions> zzZd;
    private FragmentActivity zzZe;
    private int zzZf;
    private int zzZg;
    private GoogleApiClient$OnConnectionFailedListener zzZh;
    private GoogleApiAvailability zzZi;
    private Api$zza<? extends zzd, zze> zzZj;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> zzZk;
    private final ArrayList<GoogleApiClient$OnConnectionFailedListener> zzZl;
    private zze$zza zzZm;
    
    public GoogleApiClient$Builder(final Context mContext) {
        this.zzYY = new HashSet<Scope>();
        this.zzZc = new zzld<Api<?>, zzf$zza>();
        this.zzZd = new zzld<Api<?>, Api$ApiOptions>();
        this.zzZf = -1;
        this.zzZg = -1;
        this.zzZi = GoogleApiAvailability.getInstance();
        this.zzZj = zzb.zzQg;
        this.zzZk = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzZl = new ArrayList<GoogleApiClient$OnConnectionFailedListener>();
        this.zzZm = new zze$zza();
        this.mContext = mContext;
        this.zzYV = mContext.getMainLooper();
        this.zzQl = mContext.getPackageName();
        this.zzZb = mContext.getClass().getName();
    }
    
    private void zza(final zzp zzp, final GoogleApiClient googleApiClient) {
        zzp.zza(this.zzZf, googleApiClient, this.zzZh);
    }
    
    private GoogleApiClient zznj() {
        final zzi zzi = new zzi(this.mContext.getApplicationContext(), this.zzYV, this.zzni(), this.zzZi, this.zzZj, this.zzZd, this.zzZk, this.zzZl, this.zzZf, -1);
        final zzp zza = zzp.zza(this.zzZe);
        if (zza == null) {
            new Handler(this.mContext.getMainLooper()).post((Runnable)new GoogleApiClient$Builder$1(this, zzi));
            return zzi;
        }
        this.zza(zza, zzi);
        return zzi;
    }
    
    private GoogleApiClient zznk() {
        final zzq zzc = zzq.zzc(this.zzZe);
        GoogleApiClient zzbj;
        if ((zzbj = zzc.zzbj(this.zzZg)) == null) {
            zzbj = new zzi(this.mContext.getApplicationContext(), this.zzYV, this.zzni(), this.zzZi, this.zzZj, this.zzZd, this.zzZk, this.zzZl, -1, this.zzZg);
        }
        zzc.zza(this.zzZg, zzbj, this.zzZh);
        return zzbj;
    }
    
    public GoogleApiClient$Builder addApi(final Api<? extends Api$ApiOptions$NotRequiredOptions> api) {
        this.zzZd.put(api, null);
        this.zzYY.addAll(api.zznb().zzl((Api$ApiOptions$NotRequiredOptions)null));
        return this;
    }
    
    public <O extends Api$ApiOptions$HasOptions> GoogleApiClient$Builder addApi(final Api<O> api, final O o) {
        zzx.zzb(o, "Null options are not permitted for this Api");
        this.zzZd.put((Api<?>)api, (Object)o);
        this.zzYY.addAll(api.zznb().zzl(o));
        return this;
    }
    
    public GoogleApiClient$Builder addConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        this.zzZk.add(googleApiClient$ConnectionCallbacks);
        return this;
    }
    
    public GoogleApiClient$Builder addOnConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        this.zzZl.add(googleApiClient$OnConnectionFailedListener);
        return this;
    }
    
    public GoogleApiClient build() {
        zzx.zzb(!this.zzZd.isEmpty(), "must call addApi() to add at least one API");
        if (this.zzZf >= 0) {
            return this.zznj();
        }
        if (this.zzZg >= 0) {
            return this.zznk();
        }
        return new zzi(this.mContext, this.zzYV, this.zzni(), this.zzZi, this.zzZj, this.zzZd, this.zzZk, this.zzZl, -1, -1);
    }
    
    public zzf zzni() {
        return new zzf(this.zzOY, this.zzYY, this.zzZc, this.zzYZ, this.zzZa, this.zzQl, this.zzZb, this.zzZm.zzzr());
    }
}
