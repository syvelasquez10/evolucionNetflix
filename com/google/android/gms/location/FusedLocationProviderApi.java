// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import android.location.Location;
import com.google.android.gms.common.api.GoogleApiClient;

public interface FusedLocationProviderApi
{
    public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
    public static final String KEY_MOCK_LOCATION = "mockLocation";
    
    Location getLastLocation(final GoogleApiClient p0);
    
    PendingResult<Status> removeLocationUpdates(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> removeLocationUpdates(final GoogleApiClient p0, final LocationListener p1);
    
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final PendingIntent p2);
    
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final LocationListener p2);
    
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final LocationListener p2, final Looper p3);
    
    PendingResult<Status> setMockLocation(final GoogleApiClient p0, final Location p1);
    
    PendingResult<Status> setMockMode(final GoogleApiClient p0, final boolean p1);
}
