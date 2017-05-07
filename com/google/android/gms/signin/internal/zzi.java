// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzt;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.zzp;
import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import com.google.android.gms.common.internal.zzj$zzf;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.zzqx;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.common.internal.zzj;

public class zzi extends zzj<zzf> implements zzqw
{
    private final boolean zzaVl;
    private final ExecutorService zzaVm;
    private final zzqx zzaaT;
    private final com.google.android.gms.common.internal.zzf zzabI;
    private Integer zzafj;
    
    public zzi(final Context context, final Looper looper, final boolean zzaVl, final com.google.android.gms.common.internal.zzf zzabI, final zzqx zzaaT, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final ExecutorService zzaVm) {
        super(context, looper, 44, zzabI, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzaVl = zzaVl;
        this.zzabI = zzabI;
        this.zzaaT = zzaaT;
        this.zzafj = zzabI.zzoR();
        this.zzaVm = zzaVm;
    }
    
    public static Bundle zza(final zzqx zzqx, final Integer n, final ExecutorService executorService) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzqx.zzCf());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzqx.zzlY());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzqx.zzmb());
        if (zzqx.zzCg() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", (Parcelable)new BinderWrapper(new zzi$zza(zzqx, executorService).asBinder()));
        }
        if (n != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)n);
        }
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", zzqx.zzCh());
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzqx.zzma());
        return bundle;
    }
    
    @Override
    public void connect() {
        this.zza(new zzj$zzf(this));
    }
    
    @Override
    public void zzCe() {
        try {
            this.zzpc().zzjq(this.zzafj);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
    
    @Override
    public void zza(final zzp ex, final Set<Scope> set, final zze zze) {
        zzx.zzb(zze, "Expecting a valid ISignInCallbacks");
        try {
            this.zzpc().zza(new AuthAccountRequest((zzp)ex, set), zze);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                zze.zza(new ConnectionResult(8, null), new AuthAccountResult());
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void zza(final zzp zzp, final boolean b) {
        try {
            this.zzpc().zza(zzp, this.zzafj, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public void zza(final zzt zzt) {
        zzx.zzb(zzt, "Expecting a valid IResolveAccountCallbacks");
        try {
            this.zzpc().zza(new ResolveAccountRequest(this.zzabI.zzoI(), this.zzafj), zzt);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                zzt.zzb(new ResolveAccountResponse(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.", (Throwable)ex);
            }
        }
    }
    
    protected zzf zzdO(final IBinder binder) {
        return zzf$zza.zzdN(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.signin.service.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    @Override
    public boolean zzlN() {
        return this.zzaVl;
    }
    
    @Override
    protected Bundle zzly() {
        final Bundle zza = zza(this.zzaaT, this.zzabI.zzoR(), this.zzaVm);
        if (!this.getContext().getPackageName().equals(this.zzabI.zzoN())) {
            zza.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzabI.zzoN());
        }
        return zza;
    }
}
