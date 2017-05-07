// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.location.Location;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import com.google.android.gms.location.LocationListener;
import android.os.Handler;

class lx$a extends Handler
{
    private final LocationListener aeJ;
    
    public lx$a(final LocationListener aeJ) {
        this.aeJ = aeJ;
    }
    
    public lx$a(final LocationListener aeJ, final Looper looper) {
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
