// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
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
        return googleApiClient.b((PendingResult<Status>)new lu$1(this, list3, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lu$2(this, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final List<String> list) {
        return googleApiClient.b((PendingResult<Status>)new lu$3(this, list));
    }
}
