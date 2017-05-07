// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.location.LocationServices;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.location.LocationClient;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.location.Geofence;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingApi;

public class lu implements GeofencingApi
{
    @Override
    public PendingResult<Status> addGeofences(final GoogleApiClient googleApiClient, final List<Geofence> list, final PendingIntent pendingIntent) {
        ArrayList<mb> list3;
        if (list != null) {
            final ArrayList<mb> list2 = new ArrayList<mb>(list.size());
            for (final Geofence geofence : list) {
                n.b(geofence instanceof mb, (Object)"Geofence must be created using Geofence.Builder.");
                list2.add((mb)geofence);
            }
            list3 = list2;
        }
        else {
            list3 = null;
        }
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.addGeofences(list3, pendingIntent, new LocationClient.OnAddGeofencesResultListener() {
                    @Override
                    public void onAddGeofencesResult(final int n, final String[] array) {
                        ((BaseImplementation.AbstractPendingResult<R>)a.this).b((R)LocationStatusCodes.ef(n));
                    }
                });
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.removeGeofences(pendingIntent, new LocationClient.OnRemoveGeofencesResultListener() {
                    @Override
                    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
                        ((BaseImplementation.AbstractPendingResult<R>)a.this).b((R)LocationStatusCodes.ef(n));
                    }
                    
                    @Override
                    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
                        Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
                    }
                });
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final List<String> list) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.removeGeofences(list, new LocationClient.OnRemoveGeofencesResultListener() {
                    @Override
                    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
                        Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
                    }
                    
                    @Override
                    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
                        ((BaseImplementation.AbstractPendingResult<R>)a.this).b((R)LocationStatusCodes.ef(n));
                    }
                });
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
