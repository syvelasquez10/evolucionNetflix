// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.content.Loader;

class d$a extends Loader<ConnectionResult> implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    private boolean JA;
    private ConnectionResult JB;
    public final GoogleApiClient Jz;
    
    public d$a(final Context context, final GoogleApiClient jz) {
        super(context);
        this.Jz = jz;
    }
    
    private void a(final ConnectionResult jb) {
        this.JB = jb;
        if (this.isStarted() && !this.isAbandoned()) {
            this.deliverResult(jb);
        }
    }
    
    public void gw() {
        if (this.JA) {
            this.JA = false;
            if (this.isStarted() && !this.isAbandoned()) {
                this.Jz.connect();
            }
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.JA = false;
        this.a(ConnectionResult.HE);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.JA = true;
        this.a(connectionResult);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    protected void onReset() {
        this.JB = null;
        this.JA = false;
        this.Jz.unregisterConnectionCallbacks(this);
        this.Jz.unregisterConnectionFailedListener(this);
        this.Jz.disconnect();
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        this.Jz.registerConnectionCallbacks(this);
        this.Jz.registerConnectionFailedListener(this);
        if (this.JB != null) {
            this.deliverResult(this.JB);
        }
        if (!this.Jz.isConnected() && !this.Jz.isConnecting() && !this.JA) {
            this.Jz.connect();
        }
    }
    
    @Override
    protected void onStopLoading() {
        this.Jz.disconnect();
    }
}
