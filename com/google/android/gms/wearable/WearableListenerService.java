// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.os.HandlerThread;
import android.util.Log;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.app.Service;

public abstract class WearableListenerService extends Service implements DataApi$DataListener, MessageApi$MessageListener, NodeApi$NodeListener
{
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private String BZ;
    private IBinder LR;
    private volatile int NP;
    private Handler auR;
    private Object auS;
    private boolean auT;
    
    public WearableListenerService() {
        this.NP = -1;
        this.auS = new Object();
    }
    
    private boolean bc(int n) {
        final boolean b = false;
        final String[] packagesForUid = this.getPackageManager().getPackagesForUid(n);
        boolean b2 = b;
        if (packagesForUid != null) {
            n = 0;
            while (true) {
                b2 = b;
                if (n >= packagesForUid.length) {
                    break;
                }
                if ("com.google.android.gms".equals(packagesForUid[n])) {
                    b2 = true;
                    break;
                }
                ++n;
            }
        }
        return b2;
    }
    
    private void pS() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.NP) {
            return;
        }
        if (GooglePlayServicesUtil.b(this.getPackageManager(), "com.google.android.gms") && this.bc(callingUid)) {
            this.NP = callingUid;
            return;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
    
    public final IBinder onBind(final Intent intent) {
        if ("com.google.android.gms.wearable.BIND_LISTENER".equals(intent.getAction())) {
            return this.LR;
        }
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onCreate: " + this.getPackageName());
        }
        this.BZ = this.getPackageName();
        final HandlerThread handlerThread = new HandlerThread("WearableListenerService");
        handlerThread.start();
        this.auR = new Handler(handlerThread.getLooper());
        this.LR = (IBinder)new WearableListenerService$a(this, null);
    }
    
    public void onDataChanged(final DataEventBuffer dataEventBuffer) {
    }
    
    public void onDestroy() {
        synchronized (this.auS) {
            this.auT = true;
            this.auR.getLooper().quit();
            // monitorexit(this.auS)
            super.onDestroy();
        }
    }
    
    public void onMessageReceived(final MessageEvent messageEvent) {
    }
    
    public void onPeerConnected(final Node node) {
    }
    
    public void onPeerDisconnected(final Node node) {
    }
}
