// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.location.LocationStatusCodes;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;

class lu$2$1 implements LocationClient$OnRemoveGeofencesResultListener
{
    final /* synthetic */ lu$2 aeD;
    
    lu$2$1(final lu$2 aeD) {
        this.aeD = aeD;
    }
    
    @Override
    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
        this.aeD.b((R)LocationStatusCodes.ef(n));
    }
    
    @Override
    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
        Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
    }
}
