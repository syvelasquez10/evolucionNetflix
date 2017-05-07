// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;

public final class d$c implements GoogleApiClient$ConnectionCallbacks
{
    private final GooglePlayServicesClient$ConnectionCallbacks Lz;
    
    public d$c(final GooglePlayServicesClient$ConnectionCallbacks lz) {
        this.Lz = lz;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof d$c) {
            return this.Lz.equals(((d$c)o).Lz);
        }
        return this.Lz.equals(o);
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.Lz.onConnected(bundle);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.Lz.onDisconnected();
    }
}
