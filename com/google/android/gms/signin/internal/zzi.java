// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzt;
import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.util.Log;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.zzp;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import com.google.android.gms.common.internal.zzj$zzf;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.signin.zze;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.signin.zzd;
import com.google.android.gms.common.internal.zzj;

public class zzi extends zzj<zzf> implements zzd
{
    private final com.google.android.gms.common.internal.zzf zzZH;
    private final boolean zzaOn;
    private final ExecutorService zzaOo;
    private final zze zzade;
    private Integer zzadf;
    
    public zzi(final Context context, final Looper looper, final boolean zzaOn, final com.google.android.gms.common.internal.zzf zzZH, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final ExecutorService zzaOo) {
        super(context, looper, 44, zzZH, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzaOn = zzaOn;
        this.zzZH = zzZH;
        this.zzade = zzZH.zzoo();
        this.zzadf = zzZH.zzop();
        this.zzaOo = zzaOo;
    }
    
    public static Bundle zza(final zze zze, final Integer n, final ExecutorService executorService) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zze.zzzo());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zze.zzzp());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", zze.zzlG());
        if (zze.zzzq() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", (Parcelable)new BinderWrapper(new zzi$zza(zze, executorService).asBinder()));
        }
        if (n != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)n);
        }
        return bundle;
    }
    
    @Override
    public void connect() {
        this.zza(new zzj$zzf(this));
    }
    
    @Override
    public void zza(final zzp zzp, final Set<Scope> set, final com.google.android.gms.signin.internal.zze zze) {
        zzx.zzb(zze, "Expecting a valid ISignInCallbacks");
        try {
            this.zzoA().zza(new AuthAccountRequest(zzp, set), zze);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                zze.zza(new ConnectionResult(8, null), new AuthAccountResult());
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.");
            }
        }
    }
    
    @Override
    public void zza(final zzp zzp, final boolean b) {
        try {
            this.zzoA().zza(zzp, this.zzadf, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public void zza(final zzt zzt) {
        zzx.zzb(zzt, "Expecting a valid IResolveAccountCallbacks");
        try {
            this.zzoA().zza(new ResolveAccountRequest(this.zzZH.zzog(), this.zzadf), zzt);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                zzt.zzb(new ResolveAccountResponse(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.");
            }
        }
    }
    
    protected zzf zzdI(final IBinder binder) {
        return zzf$zza.zzdH(binder);
    }
    
    @Override
    protected String zzfA() {
        return "com.google.android.gms.signin.service.START";
    }
    
    @Override
    protected String zzfB() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    @Override
    protected Bundle zzli() {
        final Bundle zza = zza(this.zzade, this.zzZH.zzop(), this.zzaOo);
        if (!this.getContext().getPackageName().equals(this.zzZH.zzol())) {
            zza.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzZH.zzol());
        }
        return zza;
    }
    
    @Override
    public boolean zzlm() {
        return this.zzaOn;
    }
    
    @Override
    public void zzzn() {
        try {
            this.zzoA().zzja(this.zzadf);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
}
