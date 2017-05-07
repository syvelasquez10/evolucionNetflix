// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient;

public interface GeofencingApi
{
    PendingResult<Status> addGeofences(final GoogleApiClient p0, final List<Geofence> p1, final PendingIntent p2);
    
    PendingResult<Status> removeGeofences(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> removeGeofences(final GoogleApiClient p0, final List<String> p1);
}
