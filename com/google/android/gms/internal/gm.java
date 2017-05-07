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
import com.google.android.gms.location.c;
import android.os.RemoteException;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import java.util.HashMap;
import android.content.ContentProviderClient;
import android.content.Context;

public class gm
{
    private final Context mContext;
    private final gq<gl> xP;
    private ContentProviderClient xQ;
    private boolean xR;
    private HashMap<LocationListener, b> xS;
    
    public gm(final Context mContext, final gq<gl> xp) {
        this.xQ = null;
        this.xR = false;
        this.xS = new HashMap<LocationListener, b>();
        this.mContext = mContext;
        this.xP = xp;
    }
    
    public void dI() {
        if (this.xR) {
            this.setMockMode(false);
        }
    }
    
    public Location getLastLocation() {
        this.xP.bP();
        try {
            return this.xP.bQ().an(this.mContext.getPackageName());
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeAllListeners() {
        try {
            synchronized (this.xS) {
                for (final b b : this.xS.values()) {
                    if (b != null) {
                        this.xP.bQ().a(b);
                    }
                }
            }
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
        this.xS.clear();
    }
    // monitorexit(hashMap)
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        this.xP.bP();
        try {
            this.xP.bQ().a(pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.xP.bP();
        eg.b(locationListener, "Invalid null listener");
        synchronized (this.xS) {
            final b b = this.xS.remove(locationListener);
            if (this.xQ != null && this.xS.isEmpty()) {
                this.xQ.release();
                this.xQ = null;
            }
            if (b == null) {
                return;
            }
            b.release();
            try {
                this.xP.bQ().a(b);
            }
            catch (RemoteException ex) {
                throw new IllegalStateException((Throwable)ex);
            }
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        this.xP.bP();
        try {
            this.xP.bQ().a(locationRequest, pendingIntent);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        this.xP.bP();
        if (looper == null) {
            eg.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
        }
        while (true) {
            while (true) {
                final b b;
                synchronized (this.xS) {
                    b = this.xS.get(locationListener);
                    if (b == null) {
                        final b b2 = new b(locationListener, looper);
                        this.xS.put(locationListener, b2);
                        try {
                            this.xP.bQ().a(locationRequest, b2, this.mContext.getPackageName());
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
        this.xP.bP();
        try {
            this.xP.bQ().setMockLocation(mockLocation);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void setMockMode(final boolean b) {
        this.xP.bP();
        try {
            this.xP.bQ().setMockMode(b);
            this.xR = b;
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    private static class a extends Handler
    {
        private final LocationListener xT;
        
        public a(final LocationListener xt) {
            this.xT = xt;
        }
        
        public a(final LocationListener xt, final Looper looper) {
            super(looper);
            this.xT = xt;
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                }
                case 1: {
                    this.xT.onLocationChanged(new Location((Location)message.obj));
                }
            }
        }
    }
    
    private static class b extends c.a
    {
        private Handler xU;
        
        b(final LocationListener locationListener, final Looper looper) {
            gm.a xu;
            if (looper == null) {
                xu = new gm.a(locationListener);
            }
            else {
                xu = new gm.a(locationListener, looper);
            }
            this.xU = xu;
        }
        
        public void onLocationChanged(final Location obj) {
            if (this.xU == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            final Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = obj;
            this.xU.sendMessage(obtain);
        }
        
        public void release() {
            this.xU = null;
        }
    }
}
