// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationAvailability;
import android.os.Message;
import android.util.Log;
import android.os.Handler;
import com.google.android.gms.location.zzc$zza;

class zzk$zza extends zzc$zza
{
    private Handler zzaFy;
    
    private void zzb(final int what, final Object obj) {
        if (this.zzaFy == null) {
            Log.e("LocationClientHelper", "Received a data in client after calling removeLocationUpdates.");
            return;
        }
        final Message obtain = Message.obtain();
        obtain.what = what;
        obtain.obj = obj;
        this.zzaFy.sendMessage(obtain);
    }
    
    public void onLocationAvailability(final LocationAvailability locationAvailability) {
        this.zzb(1, locationAvailability);
    }
    
    public void onLocationResult(final LocationResult locationResult) {
        this.zzb(0, locationResult);
    }
}
