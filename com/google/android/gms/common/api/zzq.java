// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.support.v4.content.Loader;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class zzq extends Fragment implements DialogInterface$OnCancelListener, LoaderManager$LoaderCallbacks<ConnectionResult>
{
    private boolean zzaaJ;
    private int zzaaK;
    private ConnectionResult zzaaL;
    private final Handler zzaaM;
    private final SparseArray<zzq$zzb> zzaaN;
    
    public zzq() {
        this.zzaaK = -1;
        this.zzaaM = new Handler(Looper.getMainLooper());
        this.zzaaN = (SparseArray<zzq$zzb>)new SparseArray();
    }
    
    private void zza(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLoaderLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        final zzq$zzb zzq$zzb = (zzq$zzb)this.zzaaN.get(n);
        if (zzq$zzb != null) {
            this.zzbi(n);
            final GoogleApiClient$OnConnectionFailedListener zzaaQ = zzq$zzb.zzaaQ;
            if (zzaaQ != null) {
                zzaaQ.onConnectionFailed(connectionResult);
            }
        }
        this.zznJ();
    }
    
    private void zzb(final int zzaaK, final ConnectionResult zzaaL) {
        if (!this.zzaaJ) {
            this.zzaaJ = true;
            this.zzaaK = zzaaK;
            this.zzaaL = zzaaL;
            this.zzaaM.post((Runnable)new zzq$zzc(this, zzaaK, zzaaL));
        }
    }
    
    public static zzq zzc(final FragmentActivity fragmentActivity) {
        zzx.zzch("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final zzq zzq = (zzq)supportFragmentManager.findFragmentByTag("GmsSupportLoaderLifecycleFragment");
            if (zzq != null) {
                final zzq zzq2 = zzq;
                if (!zzq.isRemoving()) {
                    return zzq2;
                }
            }
            final zzq zzq2 = new zzq();
            supportFragmentManager.beginTransaction().add(zzq2, "GmsSupportLoaderLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return zzq2;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLoaderLifecycleFragment is not a SupportLoaderLifecycleFragment", ex);
        }
    }
    
    private void zznJ() {
        int i = 0;
        this.zzaaJ = false;
        this.zzaaK = -1;
        this.zzaaL = null;
        final LoaderManager loaderManager = this.getLoaderManager();
        while (i < this.zzaaN.size()) {
            final int key = this.zzaaN.keyAt(i);
            final zzq$zza zzbk = this.zzbk(key);
            if (zzbk != null && zzbk.zznL()) {
                loaderManager.destroyLoader(key);
                loaderManager.initLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
            ++i;
        }
    }
    
    @Override
    public void onActivityResult(int n, final int n2, final Intent intent) {
        final int n3 = 1;
        Label_0030: {
            switch (n) {
                case 2: {
                    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.getActivity()) == 0) {
                        n = n3;
                        break Label_0030;
                    }
                    break;
                }
                case 1: {
                    if (n2 == -1) {
                        n = n3;
                        break Label_0030;
                    }
                    break;
                }
            }
            n = 0;
        }
        if (n != 0) {
            this.zznJ();
            return;
        }
        this.zza(this.zzaaK, this.zzaaL);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        for (int i = 0; i < this.zzaaN.size(); ++i) {
            final int key = this.zzaaN.keyAt(i);
            final zzq$zza zzbk = this.zzbk(key);
            if (zzbk != null && ((zzq$zzb)this.zzaaN.valueAt(i)).zzaaP != zzbk.zzaaP) {
                this.getLoaderManager().restartLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
            else {
                this.getLoaderManager().initLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zza(this.zzaaK, new ConnectionResult(13, null));
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzaaJ = bundle.getBoolean("resolving_error", false);
            this.zzaaK = bundle.getInt("failed_client_id", -1);
            if (this.zzaaK >= 0) {
                this.zzaaL = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    public Loader<ConnectionResult> onCreateLoader(final int n, final Bundle bundle) {
        return new zzq$zza((Context)this.getActivity(), ((zzq$zzb)this.zzaaN.get(n)).zzaaP);
    }
    
    public void onLoaderReset(final Loader<ConnectionResult> loader) {
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzaaJ);
        if (this.zzaaK >= 0) {
            bundle.putInt("failed_client_id", this.zzaaK);
            bundle.putInt("failed_status", this.zzaaL.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.zzaaL.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        if (!this.zzaaJ) {
            for (int i = 0; i < this.zzaaN.size(); ++i) {
                this.getLoaderManager().initLoader(this.zzaaN.keyAt(i), null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzx.zza(this.zzaaN.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.zzaaN.put(n, (Object)new zzq$zzb(googleApiClient, googleApiClient$OnConnectionFailedListener, null));
        if (this.getActivity() != null) {
            LoaderManager.enableDebugLogging(false);
            this.getLoaderManager().initLoader(n, null, (LoaderManager$LoaderCallbacks<Object>)this);
        }
    }
    
    public void zza(final Loader<ConnectionResult> loader, final ConnectionResult connectionResult) {
        if (!connectionResult.isSuccess()) {
            this.zzb(loader.getId(), connectionResult);
        }
    }
    
    public void zzbi(final int n) {
        this.zzaaN.remove(n);
        this.getLoaderManager().destroyLoader(n);
    }
    
    public GoogleApiClient zzbj(final int n) {
        if (this.getActivity() != null) {
            final zzq$zza zzbk = this.zzbk(n);
            if (zzbk != null) {
                return zzbk.zzaaP;
            }
        }
        return null;
    }
    
    zzq$zza zzbk(final int n) {
        try {
            return (zzq$zza)this.getLoaderManager().getLoader(n);
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Unknown loader in SupportLoaderLifecycleFragment", ex);
        }
    }
}
