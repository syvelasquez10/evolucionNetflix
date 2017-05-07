// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Bundle;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.content.Loader;

class zzn$zza extends Loader<ConnectionResult> implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    public final GoogleApiClient zzYa;
    private boolean zzYf;
    private ConnectionResult zzYg;
    
    public zzn$zza(final Context context, final GoogleApiClient zzYa) {
        super(context);
        this.zzYa = zzYa;
    }
    
    private void zzf(final ConnectionResult zzYg) {
        this.zzYg = zzYg;
        if (this.isStarted() && !this.isAbandoned()) {
            this.deliverResult(zzYg);
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        this.zzYa.dump(s, fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzYf = false;
        this.zzf(ConnectionResult.zzVF);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzYf = true;
        this.zzf(connectionResult);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    protected void onReset() {
        this.zzYg = null;
        this.zzYf = false;
        this.zzYa.unregisterConnectionCallbacks(this);
        this.zzYa.unregisterConnectionFailedListener(this);
        this.zzYa.disconnect();
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        this.zzYa.registerConnectionCallbacks(this);
        this.zzYa.registerConnectionFailedListener(this);
        if (this.zzYg != null) {
            this.deliverResult(this.zzYg);
        }
        if (!this.zzYa.isConnected() && !this.zzYa.isConnecting() && !this.zzYf) {
            this.zzYa.connect();
        }
    }
    
    @Override
    protected void onStopLoading() {
        this.zzYa.disconnect();
    }
    
    public boolean zzmV() {
        return this.zzYf;
    }
}
