// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Looper;
import com.google.android.gms.internal.lz;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import android.app.PendingIntent;
import android.location.Location;
import java.util.Iterator;
import com.google.android.gms.internal.mb;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import android.content.Context;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class LocationClient implements GooglePlayServicesClient
{
    public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
    public static final String KEY_MOCK_LOCATION = "mockLocation";
    private final ly adP;
    
    public LocationClient(final Context context, final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks, final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP = new ly(context, googlePlayServicesClient$ConnectionCallbacks, googlePlayServicesClient$OnConnectionFailedListener, "location");
    }
    
    public static int getErrorCode(final Intent intent) {
        return intent.getIntExtra("gms_error_code", -1);
    }
    
    public static int getGeofenceTransition(final Intent intent) {
        final int intExtra = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra != -1 && (intExtra == 1 || intExtra == 2 || intExtra == 4)) {
            return intExtra;
        }
        return -1;
    }
    
    public static List<Geofence> getTriggeringGeofences(final Intent intent) {
        final ArrayList list = (ArrayList)intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (list == null) {
            return null;
        }
        final ArrayList list2 = new ArrayList<mb>(list.size());
        final Iterator<byte[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(mb.h(iterator.next()));
        }
        return (List<Geofence>)list2;
    }
    
    public static Location getTriggeringLocation(final Intent intent) {
        return (Location)intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location");
    }
    
    public static boolean hasError(final Intent intent) {
        return intent.hasExtra("gms_error_code");
    }
    
    public void addGeofences(final List<Geofence> list, final PendingIntent pendingIntent, final LocationClient$OnAddGeofencesResultListener locationClient$OnAddGeofencesResultListener) {
        List<mb> list2 = null;
        if (list != null) {
            list2 = new ArrayList<mb>();
            for (final Geofence geofence : list) {
                n.b(geofence instanceof mb, (Object)"Geofence must be created using Geofence.Builder.");
                list2.add((mb)geofence);
            }
        }
        try {
            this.adP.addGeofences(list2, pendingIntent, locationClient$OnAddGeofencesResultListener);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    @Override
    public void connect() {
        this.adP.connect();
    }
    
    @Override
    public void disconnect() {
        this.adP.disconnect();
    }
    
    public Location getLastLocation() {
        return this.adP.getLastLocation();
    }
    
    @Override
    public boolean isConnected() {
        return this.adP.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.adP.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        return this.adP.isConnectionCallbacksRegistered(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        return this.adP.isConnectionFailedListenerRegistered(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    @Override
    public void registerConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.adP.registerConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP.registerConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    public void removeGeofences(final PendingIntent pendingIntent, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener) {
        try {
            this.adP.removeGeofences(pendingIntent, locationClient$OnRemoveGeofencesResultListener);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeGeofences(final List<String> list, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener) {
        try {
            this.adP.removeGeofences(list, locationClient$OnRemoveGeofencesResultListener);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        try {
            this.adP.removeLocationUpdates(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        try {
            this.adP.removeLocationUpdates(locationListener);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        try {
            this.adP.b(lz.b(locationRequest), pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener) {
        try {
            this.adP.a(lz.b(locationRequest), locationListener);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        try {
            this.adP.a(lz.b(locationRequest), locationListener, looper);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void setMockLocation(final Location mockLocation) {
        try {
            this.adP.setMockLocation(mockLocation);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void setMockMode(final boolean mockMode) {
        try {
            this.adP.setMockMode(mockMode);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    @Override
    public void unregisterConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.adP.unregisterConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.adP.unregisterConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
    }
}
