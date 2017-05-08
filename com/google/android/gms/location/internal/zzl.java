// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.internal.zzx;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;

public class zzl extends zzb
{
    private final zzk zzaFB;
    
    public zzl(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s) {
        this(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s, zzf.zzak(context));
    }
    
    public zzl(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s, final zzf zzf) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s, zzf);
        this.zzaFB = new zzk(context, this.zzaFb);
    }
    
    @Override
    public void disconnect() {
        synchronized (this.zzaFB) {
            Label_0030: {
                if (!this.isConnected()) {
                    break Label_0030;
                }
                try {
                    this.zzaFB.removeAllListeners();
                    this.zzaFB.zzwE();
                    super.disconnect();
                }
                catch (Exception ex) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", (Throwable)ex);
                }
            }
        }
    }
    
    public void zza(final long n, final PendingIntent pendingIntent) {
        this.zzpb();
        zzx.zzw(pendingIntent);
        zzx.zzb(n >= 0L, "detectionIntervalMillis must be >= 0");
        this.zzpc().zza(n, true, pendingIntent);
    }
    
    public void zza(final PendingIntent pendingIntent) {
        this.zzpb();
        zzx.zzw(pendingIntent);
        this.zzpc().zza(pendingIntent);
    }
    
    @Override
    public boolean zzpe() {
        return true;
    }
}
