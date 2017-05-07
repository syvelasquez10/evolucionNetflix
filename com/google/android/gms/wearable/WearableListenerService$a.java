// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.os.HandlerThread;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.app.Service;
import com.google.android.gms.wearable.internal.ak;
import com.google.android.gms.wearable.internal.ah;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ae$a;

class WearableListenerService$a extends ae$a
{
    final /* synthetic */ WearableListenerService auU;
    
    private WearableListenerService$a(final WearableListenerService auU) {
        this.auU = auU;
    }
    
    public void Z(final DataHolder dataHolder) {
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onDataItemChanged: " + this.auU.BZ + ": " + dataHolder);
        }
        this.auU.pS();
        synchronized (this.auU.auS) {
            if (this.auU.auT) {
                dataHolder.close();
                return;
            }
            this.auU.auR.post((Runnable)new WearableListenerService$a$1(this, dataHolder));
        }
    }
    
    public void a(final ah ah) {
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onMessageReceived: " + ah);
        }
        this.auU.pS();
        synchronized (this.auU.auS) {
            if (this.auU.auT) {
                return;
            }
            this.auU.auR.post((Runnable)new WearableListenerService$a$2(this, ah));
        }
    }
    
    public void a(final ak ak) {
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onPeerConnected: " + this.auU.BZ + ": " + ak);
        }
        this.auU.pS();
        synchronized (this.auU.auS) {
            if (this.auU.auT) {
                return;
            }
            this.auU.auR.post((Runnable)new WearableListenerService$a$3(this, ak));
        }
    }
    
    public void b(final ak ak) {
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onPeerDisconnected: " + this.auU.BZ + ": " + ak);
        }
        this.auU.pS();
        synchronized (this.auU.auS) {
            if (this.auU.auT) {
                return;
            }
            this.auU.auR.post((Runnable)new WearableListenerService$a$4(this, ak));
        }
    }
}
