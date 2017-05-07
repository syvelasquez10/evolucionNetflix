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
import com.google.android.gms.common.internal.zzu;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class zzm extends Fragment implements DialogInterface$OnCancelListener
{
    private boolean mStarted;
    private boolean zzXU;
    private int zzXV;
    private ConnectionResult zzXW;
    private final Handler zzXX;
    private final SparseArray<zzm$zza> zzXY;
    
    public zzm() {
        this.zzXV = -1;
        this.zzXX = new Handler(Looper.getMainLooper());
        this.zzXY = (SparseArray<zzm$zza>)new SparseArray();
    }
    
    public static zzm zza(final FragmentActivity fragmentActivity) {
        zzu.zzbY("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final zzm zzm = (zzm)supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (zzm != null) {
                final zzm zzm2 = zzm;
                if (!zzm.isRemoving()) {
                    return zzm2;
                }
            }
            final zzm zzm2 = new zzm();
            supportFragmentManager.beginTransaction().add(zzm2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return zzm2;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", ex);
        }
    }
    
    private void zza(final int n, final ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        final zzm$zza zzm$zza = (zzm$zza)this.zzXY.get(n);
        if (zzm$zza != null) {
            this.zzbb(n);
            final GoogleApiClient$OnConnectionFailedListener zzYb = zzm$zza.zzYb;
            if (zzYb != null) {
                zzYb.onConnectionFailed(connectionResult);
            }
        }
        this.zzmT();
    }
    
    private void zzmT() {
        this.zzXU = false;
        this.zzXV = -1;
        this.zzXW = null;
        for (int i = 0; i < this.zzXY.size(); ++i) {
            ((zzm$zza)this.zzXY.valueAt(i)).zzYa.connect();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        for (int i = 0; i < this.zzXY.size(); ++i) {
            ((zzm$zza)this.zzXY.valueAt(i)).dump(s, fileDescriptor, printWriter, array);
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
        this.mStarted = true;
        if (!this.zzXU) {
            for (int i = 0; i < this.zzXY.size(); ++i) {
                ((zzm$zza)this.zzXY.valueAt(i)).zzYa.connect();
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.mStarted = false;
        for (int i = 0; i < this.zzXY.size(); ++i) {
            ((zzm$zza)this.zzXY.valueAt(i)).zzYa.disconnect();
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzu.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzu.zza(this.zzXY.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.zzXY.put(n, (Object)new zzm$zza(this, n, googleApiClient, googleApiClient$OnConnectionFailedListener));
        if (this.mStarted && !this.zzXU) {
            googleApiClient.connect();
        }
    }
    
    public void zzbb(final int n) {
        final zzm$zza zzm$zza = (zzm$zza)this.zzXY.get(n);
        this.zzXY.remove(n);
        if (zzm$zza != null) {
            zzm$zza.zzmU();
        }
    }
}
