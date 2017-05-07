// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.os.Message;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import android.app.PendingIntent;
import java.util.Iterator;
import com.google.android.gms.location.a;
import android.os.RemoteException;
import android.location.Location;
import android.content.Context;
import com.google.android.gms.location.LocationListener;
import java.util.HashMap;
import android.content.ContentProviderClient;

public class hb
{
    private final hf<ha> Ok;
    private ContentProviderClient Ol;
    private boolean Om;
    private HashMap<LocationListener, b> On;
    private final Context mContext;
    
    public hb(final Context mContext, final hf<ha> ok) {
        this.Ol = null;
        this.Om = false;
        this.On = new HashMap<LocationListener, b>();
        this.mContext = mContext;
        this.Ok = ok;
    }
    
    public Location getLastLocation() {
        this.Ok.bT();
        try {
            return this.Ok.eM().aW(this.mContext.getPackageName());
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void hQ() {
        if (this.Om) {
            this.setMockMode(false);
        }
    }
    
    public void removeAllListeners() {
        try {
            synchronized (this.On) {
                for (final b b : this.On.values()) {
                    if (b != null) {
                        this.Ok.eM().a(b);
                    }
                }
            }
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
        this.On.clear();
    }
    // monitorexit(hashMap)
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        this.Ok.bT();
        try {
            this.Ok.eM().a(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.Ok.bT();
        fq.b(locationListener, "Invalid null listener");
        synchronized (this.On) {
            final b b = this.On.remove(locationListener);
            if (this.Ol != null && this.On.isEmpty()) {
                this.Ol.release();
                this.Ol = null;
            }
            if (b == null) {
                return;
            }
            b.release();
            try {
                this.Ok.eM().a(b);
            }
            catch (RemoteException ex) {
                throw new IllegalStateException((Throwable)ex);
            }
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        this.Ok.bT();
        try {
            this.Ok.eM().a(locationRequest, pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        this.Ok.bT();
        if (looper == null) {
            fq.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
        }
        while (true) {
            while (true) {
                final b b;
                synchronized (this.On) {
                    b = this.On.get(locationListener);
                    if (b == null) {
                        final b b2 = new b(locationListener, looper);
                        this.On.put(locationListener, b2);
                        try {
                            this.Ok.eM().a(locationRequest, b2, this.mContext.getPackageName());
                            return;
                        }
                        catch (RemoteException ex) {
                            throw new IllegalStateException((Throwable)ex);
                        }
                    }
                }
                final b b2 = b;
                continue;
            }
        }
    }
    
    public void setMockLocation(final Location mockLocation) {
        this.Ok.bT();
        try {
            this.Ok.eM().setMockLocation(mockLocation);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void setMockMode(final boolean b) {
        this.Ok.bT();
        try {
            this.Ok.eM().setMockMode(b);
            this.Om = b;
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    private static class a extends Handler
    {
        private final LocationListener Oo;
        
        public a(final LocationListener oo) {
            this.Oo = oo;
        }
        
        public a(final LocationListener oo, final Looper looper) {
            super(looper);
            this.Oo = oo;
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                }
                case 1: {
                    this.Oo.onLocationChanged(new Location((Location)message.obj));
                }
            }
        }
    }
    
    private static class b extends a.a
    {
        private Handler Op;
        
        b(final LocationListener locationListener, final Looper looper) {
            hb.a op;
            if (looper == null) {
                op = new hb.a(locationListener);
            }
            else {
                op = new hb.a(locationListener, looper);
            }
            this.Op = op;
        }
        
        public void onLocationChanged(final Location obj) {
            if (this.Op == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            final Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = obj;
            this.Op.sendMessage(obtain);
        }
        
        public void release() {
            this.Op = null;
        }
    }
}
