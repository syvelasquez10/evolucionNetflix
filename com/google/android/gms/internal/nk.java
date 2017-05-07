// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient;

public class nk implements ConnectionCallbacks, OnConnectionFailedListener
{
    private final nf.a akE;
    private boolean akF;
    private nn aku;
    
    public nk(final nf.a akE) {
        this.akE = akE;
        this.aku = null;
        this.akF = true;
    }
    
    public void R(final boolean akF) {
        this.akF = akF;
    }
    
    public void a(final nn aku) {
        this.aku = aku;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.aku.S(false);
        if (this.akF && this.akE != null) {
            this.akE.mS();
        }
        this.akF = false;
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.aku.S(true);
        if (this.akF && this.akE != null) {
            if (connectionResult.hasResolution()) {
                this.akE.b(connectionResult.getResolution());
            }
            else {
                this.akE.mT();
            }
        }
        this.akF = false;
    }
    
    @Override
    public void onDisconnected() {
        this.aku.S(true);
    }
}
