// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.d;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import com.google.android.gms.common.internal.d$b;

final class ly$d extends d$b<LocationClient$OnRemoveGeofencesResultListener>
{
    private final int HF;
    private final String[] aeQ;
    final /* synthetic */ ly aeR;
    private final int aeV;
    private final PendingIntent mPendingIntent;
    
    public ly$d(final ly aeR, final int aeV, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener, final int n, final PendingIntent mPendingIntent) {
        boolean b = true;
        this.aeR = aeR;
        super(locationClient$OnRemoveGeofencesResultListener);
        if (aeV != 1) {
            b = false;
        }
        a.I(b);
        this.aeV = aeV;
        this.HF = LocationStatusCodes.ee(n);
        this.mPendingIntent = mPendingIntent;
        this.aeQ = null;
    }
    
    public ly$d(final ly aeR, final int aeV, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener, final int n, final String[] aeQ) {
        this.aeR = aeR;
        super(locationClient$OnRemoveGeofencesResultListener);
        a.I(aeV == 2);
        this.aeV = aeV;
        this.HF = LocationStatusCodes.ee(n);
        this.aeQ = aeQ;
        this.mPendingIntent = null;
    }
    
    protected void a(final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener) {
        if (locationClient$OnRemoveGeofencesResultListener != null) {
            switch (this.aeV) {
                default: {
                    Log.wtf("LocationClientImpl", "Unsupported action: " + this.aeV);
                    break;
                }
                case 1: {
                    locationClient$OnRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.HF, this.mPendingIntent);
                }
                case 2: {
                    locationClient$OnRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.HF, this.aeQ);
                }
            }
        }
    }
    
    @Override
    protected void gT() {
    }
}
