// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.internal.kk;
import com.google.android.gms.internal.ki;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.kh;
import android.os.HandlerThread;
import android.util.Log;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.app.Service;

public abstract class WearableListenerService extends Service
{
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private IBinder DB;
    private volatile int adu;
    private String adv;
    private Handler adw;
    
    public WearableListenerService() {
        this.adu = -1;
    }
    
    private boolean cM(int n) {
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
    
    private void md() throws SecurityException {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.adu) {
            return;
        }
        if (GooglePlayServicesUtil.b(this.getPackageManager(), "com.google.android.gms") && this.cM(callingUid)) {
            this.adu = callingUid;
            return;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
    
    public IBinder onBind(final Intent intent) {
        if ("com.google.android.gms.wearable.BIND_LISTENER".equals(intent.getAction())) {
            return this.DB;
        }
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onCreate: " + this.getPackageName());
        }
        this.adv = this.getPackageName();
        final HandlerThread handlerThread = new HandlerThread("WearableListenerService");
        handlerThread.start();
        this.adw = new Handler(handlerThread.getLooper());
        this.DB = (IBinder)new a();
    }
    
    public void onDataChanged(final b b) {
    }
    
    public void onDestroy() {
        this.adw.getLooper().quit();
        super.onDestroy();
    }
    
    public void onMessageReceived(final e e) {
    }
    
    public void onPeerConnected(final f f) {
    }
    
    public void onPeerDisconnected(final f f) {
    }
    
    private class a extends kh.a
    {
        public void M(final DataHolder dataHolder) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.this.adv + ": " + dataHolder);
            }
            WearableListenerService.this.md();
            WearableListenerService.this.adw.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final b b = new b(dataHolder);
                    try {
                        WearableListenerService.this.onDataChanged(b);
                    }
                    finally {
                        b.close();
                    }
                }
            });
        }
        
        public void a(final ki ki) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onMessageReceived: " + ki);
            }
            WearableListenerService.this.md();
            WearableListenerService.this.adw.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    WearableListenerService.this.onMessageReceived(ki);
                }
            });
        }
        
        public void a(final kk kk) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.this.adv + ": " + kk);
            }
            WearableListenerService.this.md();
            WearableListenerService.this.adw.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    WearableListenerService.this.onPeerConnected(kk);
                }
            });
        }
        
        public void b(final kk kk) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.this.adv + ": " + kk);
            }
            WearableListenerService.this.md();
            WearableListenerService.this.adw.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    WearableListenerService.this.onPeerDisconnected(kk);
                }
            });
        }
    }
}
