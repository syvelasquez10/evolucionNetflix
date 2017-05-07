// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationStatusCodes;
import android.util.Log;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;

class lu$3$1 implements LocationClient$OnRemoveGeofencesResultListener
{
    final /* synthetic */ lu$3 aeF;
    
    lu$3$1(final lu$3 aeF) {
        this.aeF = aeF;
    }
    
    @Override
    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
        Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
    }
    
    @Override
    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
        this.aeF.b((R)LocationStatusCodes.ef(n));
    }
}
