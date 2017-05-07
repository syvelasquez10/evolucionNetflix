// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;

public class zzlp extends Fragment implements DialogInterface$OnCancelListener
{
    private static final GoogleApiAvailability zzacJ;
    private boolean mStarted;
    private boolean zzacK;
    private int zzacL;
    private ConnectionResult zzacM;
    private final Handler zzacN;
    private zzll zzacO;
    private final SparseArray<zzlp$zza> zzacP;
    
    static {
        zzacJ = GoogleApiAvailability.getInstance();
    }
    
    public zzlp() {
        this.zzacL = -1;
        this.zzacN = new Handler(Looper.getMainLooper());
        this.zzacP = (SparseArray<zzlp$zza>)new SparseArray();
    }
    
    public static zzlp zza(final FragmentActivity fragmentActivity) {
        zzx.zzci("Must be called from main thread of process");
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            final zzlp zzlp = (zzlp)supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (zzlp != null) {
                final zzlp zzlp2 = zzlp;
                if (!zzlp.isRemoving()) {
                    return zzlp2;
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
        final zzlp$zza zzlp$zza = (zzlp$zza)this.zzacP.get(n);
        if (zzlp$zza != null) {
            this.zzbp(n);
            final GoogleApiClient$OnConnectionFailedListener zzacS = zzlp$zza.zzacS;
            if (zzacS != null) {
                zzacS.onConnectionFailed(connectionResult);
            }
        }
        this.zzok();
    }
    
    public static zzlp zzb(final FragmentActivity fragmentActivity) {
        final zzlp zza = zza(fragmentActivity);
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        zzlp zzlp = zza;
        if (zza == null) {
            zzlp = new zzlp();
            supportFragmentManager.beginTransaction().add(zzlp, "GmsSupportLifecycleFragment").commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }
        return zzlp;
    }
    
    private void zzok() {
        this.zzacK = false;
        this.zzacL = -1;
        this.zzacM = null;
        if (this.zzacO != null) {
            this.zzacO.unregister();
            this.zzacO = null;
        }
        for (int i = 0; i < this.zzacP.size(); ++i) {
            ((zzlp$zza)this.zzacP.valueAt(i)).zzacR.connect();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        for (int i = 0; i < this.zzacP.size(); ++i) {
            ((zzlp$zza)this.zzacP.valueAt(i)).dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public void onActivityResult(int n, final int n2, final Intent intent) {
        final int n3 = 1;
        Label_0030: {
            switch (n) {
                case 2: {
                    if (zzlp.zzacJ.isGooglePlayServicesAvailable((Context)this.getActivity()) == 0) {
                        n = n3;
                        break Label_0030;
                    }
                    break;
                }
                case 1: {
                    n = n3;
                    if (n2 == -1) {
                        break Label_0030;
                    }
                    if (n2 == 0) {
                        this.zzacM = new ConnectionResult(13, null);
                        break;
                    }
                    break;
                }
            }
            n = 0;
        }
        if (n != 0) {
            this.zzok();
            return;
        }
        this.zza(this.zzacL, this.zzacM);
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zza(this.zzacL, new ConnectionResult(13, null));
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzacK = bundle.getBoolean("resolving_error", false);
            this.zzacL = bundle.getInt("failed_client_id", -1);
            if (this.zzacL >= 0) {
                this.zzacM = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzacK);
        if (this.zzacL >= 0) {
            bundle.putInt("failed_client_id", this.zzacL);
            bundle.putInt("failed_status", this.zzacM.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.zzacM.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.mStarted = true;
        if (!this.zzacK) {
            for (int i = 0; i < this.zzacP.size(); ++i) {
                ((zzlp$zza)this.zzacP.valueAt(i)).zzacR.connect();
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.mStarted = false;
        for (int i = 0; i < this.zzacP.size(); ++i) {
            ((zzlp$zza)this.zzacP.valueAt(i)).zzacR.disconnect();
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzx.zza(this.zzacP.indexOfKey(n) < 0, "Already managing a GoogleApiClient with id " + n);
        this.zzacP.put(n, (Object)new zzlp$zza(this, n, googleApiClient, googleApiClient$OnConnectionFailedListener));
        if (this.mStarted && !this.zzacK) {
            googleApiClient.connect();
        }
    }
    
    public void zzbp(final int n) {
        final zzlp$zza zzlp$zza = (zzlp$zza)this.zzacP.get(n);
        this.zzacP.remove(n);
        if (zzlp$zza != null) {
            zzlp$zza.zzom();
        }
    }
}
