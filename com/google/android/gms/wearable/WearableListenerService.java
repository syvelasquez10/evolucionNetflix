// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.ak;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ae;
import android.os.HandlerThread;
import android.util.Log;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.app.Service;

public abstract class WearableListenerService extends Service implements DataListener, MessageListener, NodeListener
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
    
    private void pS() throws SecurityException {
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
        this.LR = (IBinder)new a();
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
    
    private class a extends ae.a
    {
        public void Z(final DataHolder dataHolder) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.this.BZ + ": " + dataHolder);
            }
            WearableListenerService.this.pS();
            synchronized (WearableListenerService.this.auS) {
                if (WearableListenerService.this.auT) {
                    dataHolder.close();
                    return;
                }
                WearableListenerService.this.auR.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        final DataEventBuffer dataEventBuffer = new DataEventBuffer(dataHolder);
                        try {
                            WearableListenerService.this.onDataChanged(dataEventBuffer);
                        }
                        finally {
                            dataEventBuffer.release();
                        }
                    }
                });
            }
        }
        
        public void a(final ah ah) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onMessageReceived: " + ah);
            }
            WearableListenerService.this.pS();
            synchronized (WearableListenerService.this.auS) {
                if (WearableListenerService.this.auT) {
                    return;
                }
                WearableListenerService.this.auR.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        WearableListenerService.this.onMessageReceived(ah);
                    }
                });
            }
        }
        
        public void a(final ak ak) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.this.BZ + ": " + ak);
            }
            WearableListenerService.this.pS();
            synchronized (WearableListenerService.this.auS) {
                if (WearableListenerService.this.auT) {
                    return;
                }
                WearableListenerService.this.auR.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        WearableListenerService.this.onPeerConnected(ak);
                    }
                });
            }
        }
        
        public void b(final ak ak) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.this.BZ + ": " + ak);
            }
            WearableListenerService.this.pS();
            synchronized (WearableListenerService.this.auS) {
                if (WearableListenerService.this.auT) {
                    return;
                }
                WearableListenerService.this.auR.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        WearableListenerService.this.onPeerDisconnected(ak);
                    }
                });
            }
        }
    }
}
