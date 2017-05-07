// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Message;
import android.util.Log;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.location.LocationListener;
import android.os.Handler;
import com.google.android.gms.location.a$a;

class lx$b extends a$a
{
    private Handler aeK;
    
    lx$b(final LocationListener locationListener, final Looper looper) {
        lx$a aeK;
        if (looper == null) {
            aeK = new lx$a(locationListener);
        }
        else {
            aeK = new lx$a(locationListener, looper);
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
