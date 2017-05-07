// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.d$b;
import android.util.Log;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;

final class ly$b extends lv$a
{
    private LocationClient$OnAddGeofencesResultListener aeS;
    private LocationClient$OnRemoveGeofencesResultListener aeT;
    private ly aeU;
    
    public ly$b(final LocationClient$OnAddGeofencesResultListener aeS, final ly aeU) {
        this.aeS = aeS;
        this.aeT = null;
        this.aeU = aeU;
    }
    
    public ly$b(final LocationClient$OnRemoveGeofencesResultListener aeT, final ly aeU) {
        this.aeT = aeT;
        this.aeS = null;
        this.aeU = aeU;
    }
    
    public void onAddGeofencesResult(final int n, final String[] array) {
        if (this.aeU == null) {
            Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
            return;
        }
        final ly aeU = this.aeU;
        final ly aeU2 = this.aeU;
        aeU2.getClass();
        aeU.a(new ly$a(aeU2, this.aeS, n, array));
        this.aeU = null;
        this.aeS = null;
        this.aeT = null;
    }
    
    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
        if (this.aeU == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
            return;
        }
        final ly aeU = this.aeU;
        final ly aeU2 = this.aeU;
        aeU2.getClass();
        aeU.a(new ly$d(aeU2, 1, this.aeT, n, pendingIntent));
        this.aeU = null;
        this.aeS = null;
        this.aeT = null;
    }
    
    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
        if (this.aeU == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
            return;
        }
        final ly aeU = this.aeU;
        final ly aeU2 = this.aeU;
        aeU2.getClass();
        aeU.a(new ly$d(aeU2, 2, this.aeT, n, array));
        this.aeU = null;
        this.aeS = null;
        this.aeT = null;
    }
}
