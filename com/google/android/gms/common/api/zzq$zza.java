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

class zzq$zza extends Loader<ConnectionResult> implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    public final GoogleApiClient zzaaP;
    private boolean zzaaU;
    private ConnectionResult zzaaV;
    
    public zzq$zza(final Context context, final GoogleApiClient zzaaP) {
        super(context);
        this.zzaaP = zzaaP;
    }
    
    private void zzh(final ConnectionResult zzaaV) {
        this.zzaaV = zzaaV;
        if (this.isStarted() && !this.isAbandoned()) {
            this.deliverResult(zzaaV);
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        this.zzaaP.dump(s, fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzaaU = false;
        this.zzh(ConnectionResult.zzYi);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzaaU = true;
        this.zzh(connectionResult);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    protected void onReset() {
        this.zzaaV = null;
        this.zzaaU = false;
        this.zzaaP.unregisterConnectionCallbacks(this);
        this.zzaaP.unregisterConnectionFailedListener(this);
        this.zzaaP.disconnect();
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        this.zzaaP.registerConnectionCallbacks(this);
        this.zzaaP.registerConnectionFailedListener(this);
        if (this.zzaaV != null) {
            this.deliverResult(this.zzaaV);
        }
        if (!this.zzaaP.isConnected() && !this.zzaaP.isConnecting() && !this.zzaaU) {
            this.zzaaP.connect();
        }
    }
    
    @Override
    protected void onStopLoading() {
        this.zzaaP.disconnect();
    }
    
    public boolean zznL() {
        return this.zzaaU;
    }
}
