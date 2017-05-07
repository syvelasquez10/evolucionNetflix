// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.location.LocationStatusCodes;
import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import android.location.Location;
import com.google.android.gms.location.LocationClient;
import android.app.PendingIntent;
import java.util.List;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;
import java.util.HashMap;

public class gn extends dw<gl>
{
    private final String jG;
    private final gq<gl> xP;
    private final gm xV;
    private final HashMap xW;
    private final String xX;
    
    public gn(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String xx) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.xP = new c();
        this.xW = new HashMap();
        this.xV = new gm(context, this.xP);
        this.xX = xx;
        this.jG = null;
    }
    
    protected gl M(final IBinder binder) {
        return gl.a.L(binder);
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.xX);
        ec.e(e, 4242000, this.getContext().getPackageName(), bundle);
    }
    
    public void addGeofences(final List<go> list, final PendingIntent pendingIntent, final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
        this.bP();
        while (true) {
            Label_0093: {
                if (list == null || list.size() <= 0) {
                    break Label_0093;
                }
                final boolean b = true;
                eg.b(b, "At least one geofence must be specified.");
                eg.b(pendingIntent, "PendingIntent must be specified.");
                eg.b(onAddGeofencesResultListener, "OnAddGeofencesResultListener not provided.");
                Label_0070: {
                    if (onAddGeofencesResultListener != null) {
                        break Label_0070;
                    }
                    gk gk = null;
                    try {
                        while (true) {
                            this.bQ().a(list, pendingIntent, gk, this.getContext().getPackageName());
                            return;
                            gk = new b(onAddGeofencesResultListener, this);
                            continue;
                        }
                    }
                    catch (RemoteException ex) {
                        throw new IllegalStateException((Throwable)ex);
                    }
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    @Override
    protected String am() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
    
    @Override
    public void disconnect() {
        synchronized (this.xV) {
            if (this.isConnected()) {
                this.xV.removeAllListeners();
                this.xV.dI();
            }
            super.disconnect();
        }
    }
    
    public Location getLastLocation() {
        return this.xV.getLastLocation();
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        this.bP();
        eg.f(pendingIntent);
        try {
            this.bQ().removeActivityUpdates(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeGeofences(final PendingIntent pendingIntent, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
        this.bP();
        eg.b(pendingIntent, "PendingIntent must be specified.");
        eg.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        Label_0046: {
            if (onRemoveGeofencesResultListener != null) {
                break Label_0046;
            }
            gk gk = null;
            try {
                while (true) {
                    this.bQ().a(pendingIntent, gk, this.getContext().getPackageName());
                    return;
                    gk = new b(onRemoveGeofencesResultListener, this);
                    continue;
                }
            }
            catch (RemoteException ex) {
                throw new IllegalStateException((Throwable)ex);
            }
        }
    }
    
    public void removeGeofences(final List<String> list, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
        this.bP();
        while (true) {
            Label_0099: {
                if (list == null || list.size() <= 0) {
                    break Label_0099;
                }
                final boolean b = true;
                eg.b(b, "geofenceRequestIds can't be null nor empty.");
                eg.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
                final String[] array = list.toArray(new String[0]);
                Label_0076: {
                    if (onRemoveGeofencesResultListener != null) {
                        break Label_0076;
                    }
                    gk gk = null;
                    try {
                        while (true) {
                            this.bQ().a(array, gk, this.getContext().getPackageName());
                            return;
                            gk = new b(onRemoveGeofencesResultListener, this);
                            continue;
                        }
                    }
                    catch (RemoteException ex) {
                        throw new IllegalStateException((Throwable)ex);
                    }
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        this.xV.removeLocationUpdates(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.xV.removeLocationUpdates(locationListener);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        boolean b = true;
        this.bP();
        eg.f(pendingIntent);
        Label_0041: {
            if (n < 0L) {
                break Label_0041;
            }
            while (true) {
                eg.b(b, "detectionIntervalMillis must be >= 0");
                try {
                    this.bQ().a(n, true, pendingIntent);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    throw new IllegalStateException((Throwable)ex);
                }
            }
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        this.xV.requestLocationUpdates(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener) {
        this.requestLocationUpdates(locationRequest, locationListener, null);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        synchronized (this.xV) {
            this.xV.requestLocationUpdates(locationRequest, locationListener, looper);
        }
    }
    
    public void setMockLocation(final Location mockLocation) {
        this.xV.setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean mockMode) {
        this.xV.setMockMode(mockMode);
    }
    
    private final class a extends dw.b<LocationClient.OnAddGeofencesResultListener>
    {
        private final int mC;
        private final String[] xY;
        
        public a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener, final int n, final String[] xy) {
            super(onAddGeofencesResultListener);
            this.mC = LocationStatusCodes.aR(n);
            this.xY = xy;
        }
        
        protected void a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
            if (onAddGeofencesResultListener != null) {
                onAddGeofencesResultListener.onAddGeofencesResult(this.mC, this.xY);
            }
        }
        
        @Override
        protected void aL() {
        }
    }
    
    private static final class b extends gk.a
    {
        private LocationClient.OnAddGeofencesResultListener ya;
        private LocationClient.OnRemoveGeofencesResultListener yb;
        private gn yc;
        
        public b(final LocationClient.OnAddGeofencesResultListener ya, final gn yc) {
            this.ya = ya;
            this.yb = null;
            this.yc = yc;
        }
        
        public b(final LocationClient.OnRemoveGeofencesResultListener yb, final gn yc) {
            this.yb = yb;
            this.ya = null;
            this.yc = yc;
        }
        
        public void onAddGeofencesResult(final int n, final String[] array) throws RemoteException {
            if (this.yc == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            final gn yc = this.yc;
            final gn yc2 = this.yc;
            yc2.getClass();
            yc.a((dw.b<?>)yc2.new a(this.ya, n, array));
            this.yc = null;
            this.ya = null;
            this.yb = null;
        }
        
        public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
            if (this.yc == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
                return;
            }
            final gn yc = this.yc;
            final gn yc2 = this.yc;
            yc2.getClass();
            yc.a((dw.b<?>)yc2.new d(1, this.yb, n, pendingIntent));
            this.yc = null;
            this.ya = null;
            this.yb = null;
        }
        
        public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
            if (this.yc == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
                return;
            }
            final gn yc = this.yc;
            final gn yc2 = this.yc;
            yc2.getClass();
            yc.a((dw.b<?>)yc2.new d(2, this.yb, n, array));
            this.yc = null;
            this.ya = null;
            this.yb = null;
        }
    }
    
    private final class c implements gq<gl>
    {
        @Override
        public void bP() {
            gn.this.bP();
        }
        
        public gl dJ() {
            return (gl)((dw<IInterface>)gn.this).bQ();
        }
    }
    
    private final class d extends dw.b<LocationClient.OnRemoveGeofencesResultListener>
    {
        private final int mC;
        private final PendingIntent mPendingIntent;
        private final String[] xY;
        private final int yd;
        
        public d(final int yd, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final PendingIntent mPendingIntent) {
            boolean b = true;
            super(onRemoveGeofencesResultListener);
            if (yd != 1) {
                b = false;
            }
            ds.p(b);
            this.yd = yd;
            this.mC = LocationStatusCodes.aR(n);
            this.mPendingIntent = mPendingIntent;
            this.xY = null;
        }
        
        public d(final int yd, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final String[] xy) {
            super(onRemoveGeofencesResultListener);
            ds.p(yd == 2);
            this.yd = yd;
            this.mC = LocationStatusCodes.aR(n);
            this.xY = xy;
            this.mPendingIntent = null;
        }
        
        protected void a(final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
            if (onRemoveGeofencesResultListener != null) {
                switch (this.yd) {
                    default: {
                        Log.wtf("LocationClientImpl", "Unsupported action: " + this.yd);
                        break;
                    }
                    case 1: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.mC, this.mPendingIntent);
                    }
                    case 2: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.mC, this.xY);
                    }
                }
            }
        }
        
        @Override
        protected void aL() {
        }
    }
}
