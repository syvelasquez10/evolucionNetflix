// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.app.PendingIntent;

public interface LocationClient$OnRemoveGeofencesResultListener
{
    void onRemoveGeofencesByPendingIntentResult(final int p0, final PendingIntent p1);
    
    void onRemoveGeofencesByRequestIdsResult(final int p0, final String[] p1);
}
