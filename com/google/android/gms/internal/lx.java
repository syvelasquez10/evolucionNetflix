// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.os.Message;
import android.os.Handler;
import com.google.android.gms.location.LocationRequest;
import java.util.Iterator;
import android.location.Location;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.location.a;
import com.google.android.gms.common.internal.n;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.location.LocationListener;
import java.util.HashMap;
import android.content.ContentProviderClient;

public class lx
{
    private final md<lw> Dh;
    private ContentProviderClient aeG;
    private boolean aeH;
    private HashMap<LocationListener, b> aeI;
    private final Context mContext;
    
    public lx(final Context mContext, final md<lw> dh) {
        this.aeG = null;
        this.aeH = false;
        this.aeI = new HashMap<LocationListener, b>();
        this.mContext = mContext;
        this.Dh = dh;
    }
    
    private b a(final LocationListener locationListener, final Looper looper) {
        if (looper == null) {
            n.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
        }
        synchronized (this.aeI) {
            b b;
            if ((b = this.aeI.get(locationListener)) == null) {
                b = new b(locationListener, looper);
            }
            this.aeI.put(locationListener, b);
            return b;
        }
    }
    
    public void a(final lz lz, final LocationListener locationListener, final Looper looper) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().a(lz, this.a(locationListener, looper));
    }
    
    public void b(final lz lz, final PendingIntent pendingIntent) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().a(lz, pendingIntent);
    }
    
    public Location getLastLocation() {
        this.Dh.dK();
        try {
            return this.Dh.gS().bT(this.mContext.getPackageName());
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void lW() {
        if (!this.aeH) {
            return;
        }
        try {
            this.setMockMode(false);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
    
    public void removeAllListeners() {
        try {
            synchronized (this.aeI) {
                for (final b b : this.aeI.values()) {
                    if (b != null) {
                        this.Dh.gS().a(b);
                    }
                }
            }
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
        this.aeI.clear();
    }
    // monitorexit(hashMap)
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().a(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) throws RemoteException {
        this.Dh.dK();
        n.b(locationListener, "Invalid null listener");
        synchronized (this.aeI) {
            final b b = this.aeI.remove(locationListener);
            if (this.aeG != null && this.aeI.isEmpty()) {
                this.aeG.release();
                this.aeG = null;
            }
            if (b != null) {
                b.release();
                this.Dh.gS().a(b);
            }
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().a(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().a(locationRequest, this.a(locationListener, looper));
    }
    
    public void setMockLocation(final Location mockLocation) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean b) throws RemoteException {
        this.Dh.dK();
        this.Dh.gS().setMockMode(b);
        this.aeH = b;
    }
    
    private static class a extends Handler
    {
        private final LocationListener aeJ;
        
        public a(final LocationListener aeJ) {
            this.aeJ = aeJ;
        }
        
        public a(final LocationListener aeJ, final Looper looper) {
            super(looper);
            this.aeJ = aeJ;
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                }
                case 1: {
                    this.aeJ.onLocationChanged(new Location((Location)message.obj));
                }
            }
        }
    }
    
    private static class b extends a.a
    {
        private Handler aeK;
        
        b(final LocationListener locationListener, final Looper looper) {
            lx.a aeK;
            if (looper == null) {
                aeK = new lx.a(locationListener);
            }
            else {
                aeK = new lx.a(locationListener, looper);
            }
            this.aeK = aeK;
        }
        
        public void onLocationChanged(final Location obj) {
            if (this.aeK == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            final Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = obj;
            this.aeK.sendMessage(obtain);
        }
        
        public void release() {
            this.aeK = null;
        }
    }
}
