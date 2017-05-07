// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

public final class d$g implements GoogleApiClient$OnConnectionFailedListener
{
    private final GooglePlayServicesClient$OnConnectionFailedListener LB;
    
    public d$g(final GooglePlayServicesClient$OnConnectionFailedListener lb) {
        this.LB = lb;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof d$g) {
            return this.LB.equals(((d$g)o).LB);
        }
        return this.LB.equals(o);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.LB.onConnectionFailed(connectionResult);
    }
}
