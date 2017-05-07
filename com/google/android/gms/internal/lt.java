// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationServices;
import android.location.Location;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;

public class lt implements FusedLocationProviderApi
{
    @Override
    public Location getLastLocation(final GoogleApiClient googleApiClient) {
        final ly e = LocationServices.e(googleApiClient);
        try {
            return e.getLastLocation();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lt$6(this, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.b((PendingResult<Status>)new lt$5(this, locationListener));
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lt$4(this, locationRequest, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        return googleApiClient.b((PendingResult<Status>)new lt$1(this, locationRequest, locationListener));
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        return googleApiClient.b((PendingResult<Status>)new lt$3(this, locationRequest, locationListener, looper));
    }
    
    @Override
    public PendingResult<Status> setMockLocation(final GoogleApiClient googleApiClient, final Location location) {
        return googleApiClient.b((PendingResult<Status>)new lt$2(this, location));
    }
    
    @Override
    public PendingResult<Status> setMockMode(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.b((PendingResult<Status>)new lt$7(this, b));
    }
}
