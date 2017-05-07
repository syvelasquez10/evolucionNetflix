// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.os.Bundle;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class zzp extends Fragment implements DialogInterface$OnCancelListener
{
    private boolean mStarted;
    private boolean zzaaJ;
    private int zzaaK;
    private ConnectionResult zzaaL;
    private final Handler zzaaM;
    private final SparseArray<zzp$zza> zzaaN;
    
    public zzp() {
        this.zzaaK = -1;
        this.zzaaM = new Handler(Looper.getMainLooper());
        this.zzaaN = (SparseArray<zzp$zza>)new SparseArray();
    }
    
    public static zzp zza(final FragmentActivity fragmentActivity) {
        zzx.zzch("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final zzp zzp = (zzp)supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (zzp != null) {
                final zzp zzp2 = zzp;
                if (!zzp.isRemoving()) {
                    return zzp2;
                }
            }
            return null;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", ex);
        }
    }
    
    private void zza(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        final zzp$zza zzp$zza = (zzp$zza)this.zzaaN.get(n);
        if (zzp$zza != null) {
            this.zzbi(n);
            final GoogleApiClient$OnConnectionFailedListener zzaaQ = zzp$zza.zzaaQ;
            if (zzaaQ != null) {
                zzaaQ.onConnectionFailed(connectionResult);
            }
        }
        this.zznJ();
    }
    
    public static zzp zzb(final FragmentActivity fragmentActivity) {
        final zzp zza = zza(fragmentActivity);
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        zzp zzp = zza;
        if (zza == null) {
            zzp = new zzp();
            supportFragmentManager.beginTransaction().add(zzp, "GmsSupportLifecycleFragment").commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }
        return zzp;
    }
    
    private void zznJ() {
        this.zzaaJ = false;
        this.zzaaK = -1;
        this.zzaaL = null;
        for (int i = 0; i < this.zzaaN.size(); ++i) {
            ((zzp$zza)this.zzaaN.valueAt(i)).zzaaP.connect();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        for (int i = 0; i < this.zzaaN.size(); ++i) {
            ((zzp$zza)this.zzaaN.valueAt(i)).dump(s, fileDescriptor, printWriter, array);
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
        this.mStarted = true;
        if (!this.zzaaJ) {
            for (int i = 0; i < this.zzaaN.size(); ++i) {
                ((zzp$zza)this.zzaaN.valueAt(i)).zzaaP.connect();
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.mStarted = false;
        for (int i = 0; i < this.zzaaN.size(); ++i) {
            ((zzp$zza)this.zzaaN.valueAt(i)).zzaaP.disconnect();
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzx.zza(this.zzaaN.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.zzaaN.put(n, (Object)new zzp$zza(this, n, googleApiClient, googleApiClient$OnConnectionFailedListener));
        if (this.mStarted && !this.zzaaJ) {
            googleApiClient.connect();
        }
    }
    
    public void zzbi(final int n) {
        final zzp$zza zzp$zza = (zzp$zza)this.zzaaN.get(n);
        this.zzaaN.remove(n);
        if (zzp$zza != null) {
            zzp$zza.zznK();
        }
    }
}
