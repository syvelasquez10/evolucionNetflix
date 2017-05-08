// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Message;
import android.util.Log;
import android.location.Location;
import android.os.Handler;
import com.google.android.gms.location.zzd$zza;

class zzk$zzc extends zzd$zza
{
    private Handler zzaFy;
    
    public void onLocationChanged(final Location obj) {
        if (this.zzaFy == null) {
            Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
            return;
        }
        final Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = obj;
        this.zzaFy.sendMessage(obtain);
    }
}
