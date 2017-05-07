// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzq;
import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.util.Log;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.IAccountAccessor;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.zzi$zzf;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.common.internal.zzi;

public class zzh extends zzi<zzf> implements zzps
{
    private final zze zzWZ;
    private final zzpt zzZT;
    private Integer zzZU;
    private final boolean zzaJZ;
    private final ExecutorService zzaKa;
    
    public zzh(final Context context, final Looper looper, final boolean zzaJZ, final zze zzWZ, final zzpt zzZT, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final ExecutorService zzaKa) {
        super(context, looper, 44, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zzWZ);
        this.zzaJZ = zzaJZ;
        this.zzWZ = zzWZ;
        this.zzZT = zzZT;
        this.zzZU = zzWZ.zznA();
        this.zzaKa = zzaKa;
    }
    
    public static Bundle zza(final zzpt zzpt, final Integer n, final ExecutorService executorService) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzpt.zzxX());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzpt.zzxY());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzpt.zzxr());
        if (zzpt.zzxZ() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", (Parcelable)new BinderWrapper(new zzh$zza(zzpt, executorService).asBinder()));
        }
        if (n != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)n);
        }
        return bundle;
    }
    
    @Override
    public void connect() {
        this.connect(new zzi$zzf(this));
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }
    
    @Override
    public boolean requiresSignIn() {
        return this.zzaJZ;
    }
    
    @Override
    public void zza(final IAccountAccessor accountAccessor, final Set<Scope> set, final com.google.android.gms.signin.internal.zze zze) {
        zzu.zzb(zze, "Expecting a valid ISignInCallbacks");
        try {
            this.zznK().zza(new AuthAccountRequest(accountAccessor, set), zze);
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
    public void zza(final IAccountAccessor accountAccessor, final boolean b) {
        try {
            this.zznK().zza(accountAccessor, this.zzZU, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public void zza(final zzq zzq) {
        zzu.zzb(zzq, "Expecting a valid IResolveAccountCallbacks");
        try {
            this.zznK().zza(new ResolveAccountRequest(this.zzWZ.zznr(), this.zzZU), zzq);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                zzq.zzb(new ResolveAccountResponse(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.");
            }
        }
    }
    
    protected zzf zzdE(final IBinder binder) {
        return zzf$zza.zzdD(binder);
    }
    
    @Override
    protected Bundle zzkR() {
        final Bundle zza = zza(this.zzZT, this.zzWZ.zznA(), this.zzaKa);
        if (!this.getContext().getPackageName().equals(this.zzWZ.zznw())) {
            zza.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzWZ.zznw());
        }
        return zza;
    }
    
    @Override
    public void zzxW() {
        try {
            this.zznK().zziQ(this.zzZU);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
}
