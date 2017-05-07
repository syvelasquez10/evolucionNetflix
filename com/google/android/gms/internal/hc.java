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

public class hc extends ff<ha>
{
    private final hf<ha> Ok;
    private final hb Oq;
    private final hr Or;
    private final String Os;
    private final Context mContext;
    private final String wG;
    
    public hc(final Context mContext, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String os) {
        super(mContext, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.Ok = new c();
        this.mContext = mContext;
        this.Oq = new hb(mContext, this.Ok);
        this.Os = os;
        this.wG = null;
        this.Or = new hr(this.getContext(), mContext.getPackageName(), this.Ok);
    }
    
    protected ha X(final IBinder binder) {
        return ha.a.W(binder);
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.Os);
        fm.e(e, 4452000, this.getContext().getPackageName(), bundle);
    }
    
    public void addGeofences(final List<hd> list, final PendingIntent pendingIntent, final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
        this.bT();
        while (true) {
            Label_0093: {
                if (list == null || list.size() <= 0) {
                    break Label_0093;
                }
                final boolean b = true;
                fq.b(b, "At least one geofence must be specified.");
                fq.b(pendingIntent, "PendingIntent must be specified.");
                fq.b(onAddGeofencesResultListener, "OnAddGeofencesResultListener not provided.");
                Label_0070: {
                    if (onAddGeofencesResultListener != null) {
                        break Label_0070;
                    }
                    gz gz = null;
                    try {
                        while (true) {
                            this.eM().a(list, pendingIntent, gz, this.getContext().getPackageName());
                            return;
                            gz = new b(onAddGeofencesResultListener, this);
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
    protected String bg() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
    
    @Override
    public void disconnect() {
        synchronized (this.Oq) {
            if (this.isConnected()) {
                this.Oq.removeAllListeners();
                this.Oq.hQ();
            }
            super.disconnect();
        }
    }
    
    public Location getLastLocation() {
        return this.Oq.getLastLocation();
    }
    
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        this.bT();
        fq.f(pendingIntent);
        try {
            this.eM().removeActivityUpdates(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeGeofences(final PendingIntent pendingIntent, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
        this.bT();
        fq.b(pendingIntent, "PendingIntent must be specified.");
        fq.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
        Label_0046: {
            if (onRemoveGeofencesResultListener != null) {
                break Label_0046;
            }
            gz gz = null;
            try {
                while (true) {
                    this.eM().a(pendingIntent, gz, this.getContext().getPackageName());
                    return;
                    gz = new b(onRemoveGeofencesResultListener, this);
                    continue;
                }
            }
            catch (RemoteException ex) {
                throw new IllegalStateException((Throwable)ex);
            }
        }
    }
    
    public void removeGeofences(final List<String> list, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
        this.bT();
        while (true) {
            Label_0099: {
                if (list == null || list.size() <= 0) {
                    break Label_0099;
                }
                final boolean b = true;
                fq.b(b, "geofenceRequestIds can't be null nor empty.");
                fq.b(onRemoveGeofencesResultListener, "OnRemoveGeofencesResultListener not provided.");
                final String[] array = list.toArray(new String[0]);
                Label_0076: {
                    if (onRemoveGeofencesResultListener != null) {
                        break Label_0076;
                    }
                    gz gz = null;
                    try {
                        while (true) {
                            this.eM().a(array, gz, this.getContext().getPackageName());
                            return;
                            gz = new b(onRemoveGeofencesResultListener, this);
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
        this.Oq.removeLocationUpdates(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.Oq.removeLocationUpdates(locationListener);
    }
    
    public void requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        boolean b = true;
        this.bT();
        fq.f(pendingIntent);
        Label_0041: {
            if (n < 0L) {
                break Label_0041;
            }
            while (true) {
                fq.b(b, "detectionIntervalMillis must be >= 0");
                try {
                    this.eM().a(n, true, pendingIntent);
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
        this.Oq.requestLocationUpdates(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener) {
        this.requestLocationUpdates(locationRequest, locationListener, null);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        synchronized (this.Oq) {
            this.Oq.requestLocationUpdates(locationRequest, locationListener, looper);
        }
    }
    
    public void setMockLocation(final Location mockLocation) {
        this.Oq.setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean mockMode) {
        this.Oq.setMockMode(mockMode);
    }
    
    private final class a extends ff.b<LocationClient.OnAddGeofencesResultListener>
    {
        private final int Ah;
        private final String[] Ot;
        
        public a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener, final int n, final String[] ot) {
            super(onAddGeofencesResultListener);
            this.Ah = LocationStatusCodes.bz(n);
            this.Ot = ot;
        }
        
        protected void a(final LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
            if (onAddGeofencesResultListener != null) {
                onAddGeofencesResultListener.onAddGeofencesResult(this.Ah, this.Ot);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    private static final class b extends gz.a
    {
        private LocationClient.OnAddGeofencesResultListener Ov;
        private LocationClient.OnRemoveGeofencesResultListener Ow;
        private hc Ox;
        
        public b(final LocationClient.OnAddGeofencesResultListener ov, final hc ox) {
            this.Ov = ov;
            this.Ow = null;
            this.Ox = ox;
        }
        
        public b(final LocationClient.OnRemoveGeofencesResultListener ow, final hc ox) {
            this.Ow = ow;
            this.Ov = null;
            this.Ox = ox;
        }
        
        public void onAddGeofencesResult(final int n, final String[] array) throws RemoteException {
            if (this.Ox == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            final hc ox = this.Ox;
            final hc ox2 = this.Ox;
            ox2.getClass();
            ox.a((ff.b<?>)ox2.new a(this.Ov, n, array));
            this.Ox = null;
            this.Ov = null;
            this.Ow = null;
        }
        
        public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
            if (this.Ox == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
                return;
            }
            final hc ox = this.Ox;
            final hc ox2 = this.Ox;
            ox2.getClass();
            ox.a((ff.b<?>)ox2.new d(1, this.Ow, n, pendingIntent));
            this.Ox = null;
            this.Ov = null;
            this.Ow = null;
        }
        
        public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
            if (this.Ox == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
                return;
            }
            final hc ox = this.Ox;
            final hc ox2 = this.Ox;
            ox2.getClass();
            ox.a((ff.b<?>)ox2.new d(2, this.Ow, n, array));
            this.Ox = null;
            this.Ov = null;
            this.Ow = null;
        }
    }
    
    private final class c implements hf<ha>
    {
        @Override
        public void bT() {
            hc.this.bT();
        }
        
        public ha hR() {
            return (ha)((ff<IInterface>)hc.this).eM();
        }
    }
    
    private final class d extends ff.b<LocationClient.OnRemoveGeofencesResultListener>
    {
        private final int Ah;
        private final String[] Ot;
        private final int Oy;
        private final PendingIntent mPendingIntent;
        
        public d(final int oy, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final PendingIntent mPendingIntent) {
            boolean b = true;
            super(onRemoveGeofencesResultListener);
            if (oy != 1) {
                b = false;
            }
            fb.x(b);
            this.Oy = oy;
            this.Ah = LocationStatusCodes.bz(n);
            this.mPendingIntent = mPendingIntent;
            this.Ot = null;
        }
        
        public d(final int oy, final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, final int n, final String[] ot) {
            super(onRemoveGeofencesResultListener);
            fb.x(oy == 2);
            this.Oy = oy;
            this.Ah = LocationStatusCodes.bz(n);
            this.Ot = ot;
            this.mPendingIntent = null;
        }
        
        protected void a(final LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
            if (onRemoveGeofencesResultListener != null) {
                switch (this.Oy) {
                    default: {
                        Log.wtf("LocationClientImpl", "Unsupported action: " + this.Oy);
                        break;
                    }
                    case 1: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.Ah, this.mPendingIntent);
                    }
                    case 2: {
                        onRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.Ah, this.Ot);
                    }
                }
            }
        }
        
        @Override
        protected void dx() {
        }
    }
}
