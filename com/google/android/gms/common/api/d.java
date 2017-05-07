// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.n;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class d extends Fragment implements DialogInterface$OnCancelListener, LoaderManager$LoaderCallbacks<ConnectionResult>
{
    private boolean Ju;
    private int Jv;
    private ConnectionResult Jw;
    private final Handler Jx;
    private final SparseArray<d$b> Jy;
    
    public d() {
        this.Jv = -1;
        this.Jx = new Handler(Looper.getMainLooper());
        this.Jy = (SparseArray<d$b>)new SparseArray();
    }
    
    public static d a(final FragmentActivity fragmentActivity) {
        n.aT("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final d d = (d)supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (d != null) {
                final d d2 = d;
                if (!d.isRemoving()) {
                    return d2;
                }
            }
            final d d2 = new d();
            supportFragmentManager.beginTransaction().add(d2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return d2;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", ex);
        }
    }
    
    private void a(final int jv, final ConnectionResult jw) {
        if (!this.Ju) {
            this.Ju = true;
            this.Jv = jv;
            this.Jw = jw;
            this.Jx.post((Runnable)new d$c(this, jv, jw));
        }
    }
    
    private void an(final int n) {
        if (n == this.Jv) {
            this.gv();
        }
    }
    
    private void b(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        final d$b d$b = (d$b)this.Jy.get(n);
        if (d$b != null) {
            this.al(n);
            final GoogleApiClient$OnConnectionFailedListener jc = d$b.JC;
            if (jc != null) {
                jc.onConnectionFailed(connectionResult);
            }
        }
        this.gv();
    }
    
    private void gv() {
        int i = 0;
        this.Ju = false;
        this.Jv = -1;
        this.Jw = null;
        final LoaderManager loaderManager = this.getLoaderManager();
        while (i < this.Jy.size()) {
            final int key = this.Jy.keyAt(i);
            final d$a am = this.am(key);
            if (am != null) {
                am.gw();
            }
            loaderManager.initLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            ++i;
        }
    }
    
    public void a(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        n.b(googleApiClient, "GoogleApiClient instance cannot be null");
        n.a(this.Jy.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.Jy.put(n, (Object)new d$b(googleApiClient, googleApiClient$OnConnectionFailedListener, null));
        if (this.getActivity() != null) {
            this.getLoaderManager().initLoader(n, null, (LoaderManager$LoaderCallbacks<Object>)this);
        }
    }
    
    public void a(final Loader<ConnectionResult> loader, final ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.an(loader.getId());
            return;
        }
        this.a(loader.getId(), connectionResult);
    }
    
    public GoogleApiClient ak(final int n) {
        if (this.getActivity() != null) {
            final d$a am = this.am(n);
            if (am != null) {
                return am.Jz;
            }
        }
        return null;
    }
    
    public void al(final int n) {
        this.getLoaderManager().destroyLoader(n);
        this.Jy.remove(n);
    }
    
    d$a am(final int n) {
        try {
            return (d$a)this.getLoaderManager().getLoader(n);
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", ex);
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
            this.gv();
            return;
        }
        this.b(this.Jv, this.Jw);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        for (int i = 0; i < this.Jy.size(); ++i) {
            final int key = this.Jy.keyAt(i);
            final d$a am = this.am(key);
            if (am != null && ((d$b)this.Jy.valueAt(i)).Jz != am.Jz) {
                this.getLoaderManager().restartLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
            else {
                this.getLoaderManager().initLoader(key, null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.b(this.Jv, this.Jw);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.Ju = bundle.getBoolean("resolving_error", false);
            this.Jv = bundle.getInt("failed_client_id", -1);
            if (this.Jv >= 0) {
                this.Jw = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    public Loader<ConnectionResult> onCreateLoader(final int n, final Bundle bundle) {
        return new d$a((Context)this.getActivity(), ((d$b)this.Jy.get(n)).Jz);
    }
    
    public void onLoaderReset(final Loader<ConnectionResult> loader) {
        if (loader.getId() == this.Jv) {
            this.gv();
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.Ju);
        if (this.Jv >= 0) {
            bundle.putInt("failed_client_id", this.Jv);
            bundle.putInt("failed_status", this.Jw.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.Jw.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        if (!this.Ju) {
            for (int i = 0; i < this.Jy.size(); ++i) {
                this.getLoaderManager().initLoader(this.Jy.keyAt(i), null, (LoaderManager$LoaderCallbacks<Object>)this);
            }
        }
    }
}
