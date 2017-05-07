// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
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
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.removeLocationUpdates(pendingIntent);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.removeLocationUpdates(locationListener);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.requestLocationUpdates(locationRequest, pendingIntent);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.requestLocationUpdates(locationRequest, locationListener, null);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.requestLocationUpdates(locationRequest, locationListener, looper);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> setMockLocation(final GoogleApiClient googleApiClient, final Location location) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.setMockLocation(location);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> setMockMode(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.setMockMode(b);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    private abstract static class a extends LocationServices.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
}
