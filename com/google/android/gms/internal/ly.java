// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import android.os.IInterface;
import android.location.Location;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;
import android.app.PendingIntent;
import java.util.List;
import android.os.IBinder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public class ly extends d<lw>
{
    private final md<lw> Dh;
    private final lx aeL;
    private final mv aeM;
    private final lo aeN;
    private final ie aeO;
    private final String aeP;
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s2) {
        this(context, looper, s, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s2, null);
    }
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s2, final String s3) {
        this(context, looper, s, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s2, s3, null);
    }
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String aeP, final String s2, final String s3) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, new String[0]);
        this.Dh = new ly$c(this, null);
        this.aeL = new lx(context, this.Dh);
        this.aeP = aeP;
        this.aeM = new mv(s, this.Dh, s2);
        this.aeN = lo.a(context, s2, s3, this.Dh);
        this.aeO = ie.a(context, this.Dh);
    }
    
    public ly(final Context context, final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks, final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener, final String aeP) {
        super(context, googlePlayServicesClient$ConnectionCallbacks, googlePlayServicesClient$OnConnectionFailedListener, new String[0]);
        this.Dh = new ly$c(this, null);
        this.aeL = new lx(context, this.Dh);
        this.aeP = aeP;
        this.aeM = new mv(context.getPackageName(), this.Dh, null);
        this.aeN = lo.a(context, null, null, this.Dh);
        this.aeO = ie.a(context, this.Dh);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.aeP);
        k.e(d$e, 6111000, this.getContext().getPackageName(), bundle);
    }
    
    public void a(final lz lz, final LocationListener locationListener) {
        this.a(lz, locationListener, null);
    }
    
    public void a(final lz lz, final LocationListener locationListener, final Looper looper) {
        synchronized (this.aeL) {
            this.aeL.a(lz, locationListener, looper);
        }
    }
    
    protected lw aL(final IBinder binder) {
        return lw$a.aK(binder);
    }
    
    public void addGeofences(final List<mb> list, final PendingIntent pendingIntent, final LocationClient$OnAddGeofencesResultListener locationClient$OnAddGeofencesResultListener) {
        this.dK();
        n.b(list != null && list.size() > 0, (Object)"At least one geofence must be specified.");
        n.b(pendingIntent, "PendingIntent must be specified.");
        n.b(locationClient$OnAddGeofencesResultListener, "OnAddGeofencesResultListener not provided.");
        lv lv;
        if (locationClient$OnAddGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new ly$b(locationClient$OnAddGeofencesResultListener, this);
        }
        this.gS().a(list, pendingIntent, lv, this.getContext().getPackageName());
    }
    
    public void b(final lz lz, final PendingIntent pendingIntent) {
        this.aeL.b(lz, pendingIntent);
    }
    
    @Override
    public void disconnect() {
        synchronized (this.aeL) {
            if (this.isConnected()) {
                this.aeL.removeAllListeners();
                this.aeL.lW();
            }
            super.disconnect();
        }
    }
    
    public Location getLastLocation() {
        return this.aeL.getLastLocation();
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        this.dK();
        n.i(pendingIntent);
        this.gS().removeActivityUpdates(pendingIntent);
    }
    
    public void removeGeofences(final PendingIntent pendingIntent, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener) {
        this.dK();
        n.b(pendingIntent, "PendingIntent must be specified.");
        n.b(locationClient$OnRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        lv lv;
        if (locationClient$OnRemoveGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new ly$b(locationClient$OnRemoveGeofencesResultListener, this);
        }
        this.gS().a(pendingIntent, lv, this.getContext().getPackageName());
    }
    
    public void removeGeofences(final List<String> list, final LocationClient$OnRemoveGeofencesResultListener locationClient$OnRemoveGeofencesResultListener) {
        this.dK();
        n.b(list != null && list.size() > 0, (Object)"geofenceRequestIds can't be null nor empty.");
        n.b(locationClient$OnRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        final String[] array = list.toArray(new String[0]);
        lv lv;
        if (locationClient$OnRemoveGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new ly$b(locationClient$OnRemoveGeofencesResultListener, this);
        }
        this.gS().a(array, lv, this.getContext().getPackageName());
    }
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        this.aeL.removeLocationUpdates(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.aeL.removeLocationUpdates(locationListener);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        this.dK();
        n.i(pendingIntent);
        n.b(n >= 0L, (Object)"detectionIntervalMillis must be >= 0");
        this.gS().a(n, true, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        this.aeL.requestLocationUpdates(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        synchronized (this.aeL) {
            this.aeL.requestLocationUpdates(locationRequest, locationListener, looper);
        }
    }
    
    public void setMockLocation(final Location mockLocation) {
        this.aeL.setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean mockMode) {
        this.aeL.setMockMode(mockMode);
    }
}
