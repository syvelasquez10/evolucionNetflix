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
import com.google.android.gms.common.internal.zzu;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class zzn extends Fragment implements DialogInterface$OnCancelListener, LoaderManager$LoaderCallbacks<ConnectionResult>
{
    private boolean zzXU;
    private int zzXV;
    private ConnectionResult zzXW;
    private final Handler zzXX;
    private final SparseArray<zzn$zzb> zzXY;
    
    public zzn() {
        this.zzXV = -1;
        this.zzXX = new Handler(Looper.getMainLooper());
        this.zzXY = (SparseArray<zzn$zzb>)new SparseArray();
    }
    
    private void zza(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLoaderLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        final zzn$zzb zzn$zzb = (zzn$zzb)this.zzXY.get(n);
        if (zzn$zzb != null) {
            this.zzbb(n);
            final GoogleApiClient$OnConnectionFailedListener zzYb = zzn$zzb.zzYb;
            if (zzYb != null) {
                zzYb.onConnectionFailed(connectionResult);
            }
        }
        this.zzmT();
    }
    
    public static zzn zzb(final FragmentActivity fragmentActivity) {
        zzu.zzbY("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final zzn zzn = (zzn)supportFragmentManager.findFragmentByTag("GmsSupportLoaderLifecycleFragment");
            if (zzn != null) {
                final zzn zzn2 = zzn;
                if (!zzn.isRemoving()) {
                    return zzn2;
                }
            }
            final zzn zzn2 = new zzn();
            supportFragmentManager.beginTransaction().add(zzn2, "GmsSupportLoaderLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return zzn2;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLoaderLifecycleFragment is not a SupportLoaderLifecycleFragment", ex);
        }
    }
    
    private void zzb(final int zzXV, final ConnectionResult zzXW) {
        if (!this.zzXU) {
            this.zzXU = true;
            this.zzXV = zzXV;
            this.zzXW = zzXW;
            this.zzXX.post((Runnable)new zzn$zzc(this, zzXV, zzXW));
        }
    }
    
    private void zzmT() {
        int i = 0;
        this.zzXU = false;
        this.zzXV = -1;
        this.zzXW = null;
        final LoaderManager loaderManager = this.getLoaderManager();
        while (i < this.zzXY.size()) {
            final int key = this.zzXY.keyAt(i);
            final zzn$zza zzbd = this.zzbd(key);
            if (zzbd != null && zzbd.zzmV()) {
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
            this.zzmT();
            return;
        }
        this.zza(this.zzXV, this.zzXW);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        for (int i = 0; i < this.zzXY.size(); ++i) {
            final int key = this.zzXY.keyAt(i);
            final zzn$zza zzbd = this.zzbd(key);
            if (zzbd != null && ((zzn$zzb)this.zzXY.valueAt(i)).zzYa != zzbd.zzYa) {
                this.getLoaderManager().restartLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
            else {
                this.getLoaderManager().initLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zza(this.zzXV, new ConnectionResult(13, null));
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzXU = bundle.getBoolean("resolving_error", false);
            this.zzXV = bundle.getInt("failed_client_id", -1);
            if (this.zzXV >= 0) {
                this.zzXW = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    public Loader<ConnectionResult> onCreateLoader(final int n, final Bundle bundle) {
        return new zzn$zza((Context)this.getActivity(), ((zzn$zzb)this.zzXY.get(n)).zzYa);
    }
    
    public void onLoaderReset(final Loader<ConnectionResult> loader) {
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzXU);
        if (this.zzXV >= 0) {
            bundle.putInt("failed_client_id", this.zzXV);
            bundle.putInt("failed_status", this.zzXW.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.zzXW.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        if (!this.zzXU) {
            for (int i = 0; i < this.zzXY.size(); ++i) {
                this.getLoaderManager().initLoader(this.zzXY.keyAt(i), null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzu.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzu.zza(this.zzXY.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.zzXY.put(n, (Object)new zzn$zzb(googleApiClient, googleApiClient$OnConnectionFailedListener, null));
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
    
    public void zzbb(final int n) {
        this.zzXY.remove(n);
        this.getLoaderManager().destroyLoader(n);
    }
    
    public GoogleApiClient zzbc(final int n) {
        if (this.getActivity() != null) {
            final zzn$zza zzbd = this.zzbd(n);
            if (zzbd != null) {
                return zzbd.zzYa;
            }
        }
        return null;
    }
    
    zzn$zza zzbd(final int n) {
        try {
            return (zzn$zza)this.getLoaderManager().getLoader(n);
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Unknown loader in SupportLoaderLifecycleFragment", ex);
        }
    }
}
