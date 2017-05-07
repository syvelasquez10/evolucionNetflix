// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;

@ez
public final class fg$b extends fg implements GooglePlayServicesClient$ConnectionCallbacks, GooglePlayServicesClient$OnConnectionFailedListener
{
    private final Object mw;
    private final ff$a tu;
    private final fh tv;
    
    public fg$b(final Context context, final fi fi, final ff$a tu) {
        super(fi, tu);
        this.mw = new Object();
        this.tu = tu;
        (this.tv = new fh(context, this, this, fi.lD.wF)).connect();
    }
    
    @Override
    public void cD() {
        synchronized (this.mw) {
            if (this.tv.isConnected() || this.tv.isConnecting()) {
                this.tv.disconnect();
            }
        }
    }
    
    @Override
    public fm cE() {
        synchronized (this.mw) {
            try {
                return this.tv.cF();
            }
            catch (IllegalStateException ex) {
                return null;
            }
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.start();
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.tu.a(new fk(0));
    }
    
    @Override
    public void onDisconnected() {
        gs.S("Disconnected from remote ad request service.");
    }
}
