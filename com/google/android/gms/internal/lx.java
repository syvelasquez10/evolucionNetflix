// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationRequest;
import java.util.Iterator;
import android.os.RemoteException;
import android.location.Location;
import android.app.PendingIntent;
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
    private HashMap<LocationListener, lx$b> aeI;
    private final Context mContext;
    
    public lx(final Context mContext, final md<lw> dh) {
        this.aeG = null;
        this.aeH = false;
        this.aeI = new HashMap<LocationListener, lx$b>();
        this.mContext = mContext;
        this.Dh = dh;
    }
    
    private lx$b a(final LocationListener locationListener, final Looper looper) {
        if (looper == null) {
            n.b(Looper.myLooper(), "Can't create handler inside thread that has not called Looper.prepare()");
        }
        synchronized (this.aeI) {
            lx$b lx$b;
            if ((lx$b = this.aeI.get(locationListener)) == null) {
                lx$b = new lx$b(locationListener, looper);
            }
            this.aeI.put(locationListener, lx$b);
            return lx$b;
        }
    }
    
    public void a(final lz lz, final LocationListener locationListener, final Looper looper) {
        this.Dh.dK();
        this.Dh.gS().a(lz, this.a(locationListener, looper));
    }
    
    public void b(final lz lz, final PendingIntent pendingIntent) {
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
                for (final lx$b lx$b : this.aeI.values()) {
                    if (lx$b != null) {
                        this.Dh.gS().a(lx$b);
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
    
    public void removeLocationUpdates(final PendingIntent pendingIntent) {
        this.Dh.dK();
        this.Dh.gS().a(pendingIntent);
    }
    
    public void removeLocationUpdates(final LocationListener locationListener) {
        this.Dh.dK();
        n.b(locationListener, "Invalid null listener");
        synchronized (this.aeI) {
            final lx$b lx$b = this.aeI.remove(locationListener);
            if (this.aeG != null && this.aeI.isEmpty()) {
                this.aeG.release();
                this.aeG = null;
            }
            if (lx$b != null) {
                lx$b.release();
                this.Dh.gS().a(lx$b);
            }
        }
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        this.Dh.dK();
        this.Dh.gS().a(locationRequest, pendingIntent);
    }
    
    public void requestLocationUpdates(final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        this.Dh.dK();
        this.Dh.gS().a(locationRequest, this.a(locationListener, looper));
    }
    
    public void setMockLocation(final Location mockLocation) {
        this.Dh.dK();
        this.Dh.gS().setMockLocation(mockLocation);
    }
    
    public void setMockMode(final boolean b) {
        this.Dh.dK();
        this.Dh.gS().setMockMode(b);
        this.aeH = b;
    }
}
