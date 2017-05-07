// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.a;
import android.util.Log;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.location.LocationRequest;
import android.os.IInterface;
import android.location.Location;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.location.LocationClient;
import android.app.PendingIntent;
import java.util.List;
import android.os.IBinder;
import com.google.android.gms.location.LocationListener;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public class ly extends com.google.android.gms.common.internal.d<lw>
{
    private final md<lw> Dh;
    private final lx aeL;
    private final mv aeM;
    private final lo aeN;
    private final ie aeO;
    private final String aeP;
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s2) {
        this(context, looper, s, connectionCallbacks, onConnectionFailedListener, s2, null);
    }
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s2, final String s3) {
        this(context, looper, s, connectionCallbacks, onConnectionFailedListener, s2, s3, null);
    }
    
    public ly(final Context context, final Looper looper, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String aeP, final String s2, final String s3) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.Dh = new c();
        this.aeL = new lx(context, this.Dh);
        this.aeP = aeP;
        this.aeM = new mv(s, this.Dh, s2);
        this.aeN = lo.a(context, s2, s3, this.Dh);
        this.aeO = ie.a(context, this.Dh);
    }
    
    public ly(final Context context, final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, final String aeP) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.Dh = new c();
        this.aeL = new lx(context, this.Dh);
        this.aeP = aeP;
        this.aeM = new mv(context.getPackageName(), this.Dh, null);
        this.aeN = lo.a(context, null, null, this.Dh);
        this.aeO = ie.a(context, this.Dh);
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.aeP);
        k.e(e, 6111000, this.getContext().getPackageName(), bundle);
    }
    
    public void a(final lz lz, final LocationListener locationListener) throws RemoteException {
        this.a(lz, locationListener, null);
    }
    
    public void a(final lz lz, final LocationListener locationListener, final Looper looper) throws RemoteException {
        synchronized (this.aeL) {
            this.aeL.a(lz, locationListener, looper);
        }
    }
    
    protected lw aL(final IBinder binder) {
        return lw.a.aK(binder);
    }
    
    public void addGeofences(final List<mb> list, final PendingIntent pendingIntent, final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) throws RemoteException {
        this.dK();
        n.b(list != null && list.size() > 0, (Object)"At least one geofence must be specified.");
        n.b(pendingIntent, "PendingIntent must be specified.");
        n.b(onAddGeofencesResultListener, "OnAddGeofencesResultListener not provided.");
        lv lv;
        if (onAddGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new b(onAddGeofencesResultListener, this);
        }
        this.gS().a(list, pendingIntent, lv, this.getContext().getPackageName());
    }
    
    public void b(final lz lz, final PendingIntent pendingIntent) throws RemoteException {
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
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) throws RemoteException {
        this.dK();
        n.i(pendingIntent);
        this.gS().removeActivityUpdates(pendingIntent);
    }
    
    public void removeGeofences(final PendingIntent pendingIntent, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) throws RemoteException {
        this.dK();
        n.b(pendingIntent, "PendingIntent must be specified.");
        n.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        lv lv;
        if (onRemoveGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new b(onRemoveGeofencesResultListener, this);
        }
        this.gS().a(pendingIntent, lv, this.getContext().getPackageName());
    }
    
    public void removeGeofences(final List<String> list, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) throws RemoteException {
        this.dK();
        n.b(list != null && list.size() > 0, (Object)"geofenceRequestIds can't be null nor empty.");
        n.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        final String[] array = list.toArray(new String[0]);
        lv lv;
        if (onRemoveGeofencesResultListener == null) {
            lv = null;
        }
        else {
            lv = new b(onRemoveGeofencesResultListener, this);
        }
        this.gS().a(array, lv, this.getContext().getPackageName());
    }
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) throws RemoteException {
        this.aeL.removeLocationUpdates(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) throws RemoteException {
        this.aeL.removeLocationUpdates(locationListener);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) throws RemoteException {
        this.dK();
        n.i(pendingIntent);
        n.b(n >= 0L, (Object)"detectionIntervalMillis must be >= 0");
        this.gS().a(n, true, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) throws RemoteException {
        this.aeL.requestLocationUpdates(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) throws RemoteException {
        synchronized (this.aeL) {
            this.aeL.requestLocationUpdates(locationRequest, locationListener, looper);
        }
    }
    
    public void setMockLocation(final Location mockLocation) throws RemoteException {
        this.aeL.setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean mockMode) throws RemoteException {
        this.aeL.setMockMode(mockMode);
    }
    
    private final class a extends b<LocationClient.OnAddGeofencesResultListener>
    {
        private final int HF;
        private final String[] aeQ;
        
        public a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener, final int n, final String[] aeQ) {
            super(onAddGeofencesResultListener);
            this.HF = LocationStatusCodes.ee(n);
            this.aeQ = aeQ;
        }
        
        protected void a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
            if (onAddGeofencesResultListener != null) {
                onAddGeofencesResultListener.onAddGeofencesResult(this.HF, this.aeQ);
            }
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private static final class b extends lv.a
    {
        private LocationClient.OnAddGeofencesResultListener aeS;
        private LocationClient.OnRemoveGeofencesResultListener aeT;
        private ly aeU;
        
        public b(final LocationClient.OnAddGeofencesResultListener aeS, final ly aeU) {
            this.aeS = aeS;
            this.aeT = null;
            this.aeU = aeU;
        }
        
        public b(final LocationClient.OnRemoveGeofencesResultListener aeT, final ly aeU) {
            this.aeT = aeT;
            this.aeS = null;
            this.aeU = aeU;
        }
        
        public void onAddGeofencesResult(final int n, final String[] array) throws RemoteException {
            if (this.aeU == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            final ly aeU = this.aeU;
            final ly aeU2 = this.aeU;
            aeU2.getClass();
            aeU.a((com.google.android.gms.common.internal.d.b<?>)aeU2.new a(this.aeS, n, array));
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
            aeU.a((com.google.android.gms.common.internal.d.b<?>)aeU2.new d(1, this.aeT, n, pendingIntent));
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
            aeU.a((com.google.android.gms.common.internal.d.b<?>)aeU2.new d(2, this.aeT, n, array));
            this.aeU = null;
            this.aeS = null;
            this.aeT = null;
        }
    }
    
    private final class c implements md<lw>
    {
        @Override
        public void dK() {
            ly.this.dK();
        }
        
        public lw lX() {
            return ly.this.gS();
        }
    }
    
    private final class d extends com.google.android.gms.common.internal.d.b<LocationClient.OnRemoveGeofencesResultListener>
    {
        private final int HF;
        private final String[] aeQ;
        private final int aeV;
        private final PendingIntent mPendingIntent;
        
        public d(final int aeV, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final PendingIntent mPendingIntent) {
            boolean b = true;
            super(onRemoveGeofencesResultListener);
            if (aeV != 1) {
                b = false;
            }
            a.I(b);
            this.aeV = aeV;
            this.HF = LocationStatusCodes.ee(n);
            this.mPendingIntent = mPendingIntent;
            this.aeQ = null;
        }
        
        public d(final int aeV, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final String[] aeQ) {
            super(onRemoveGeofencesResultListener);
            a.I(aeV == 2);
            this.aeV = aeV;
            this.HF = LocationStatusCodes.ee(n);
            this.aeQ = aeQ;
            this.mPendingIntent = null;
        }
        
        protected void a(final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
            if (onRemoveGeofencesResultListener != null) {
                switch (this.aeV) {
                    default: {
                        Log.wtf("LocationClientImpl", "Unsupported action: " + this.aeV);
                        break;
                    }
                    case 1: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.HF, this.mPendingIntent);
                    }
                    case 2: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.HF, this.aeQ);
                    }
                }
            }
        }
        
        @Override
        protected void gT() {
        }
    }
}
