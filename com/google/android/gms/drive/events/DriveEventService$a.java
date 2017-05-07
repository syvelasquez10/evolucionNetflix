// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import java.util.concurrent.TimeUnit;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.concurrent.CountDownLatch;
import android.app.Service;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.drive.internal.OnEventResponse;
import android.os.Message;
import android.os.Handler;

final class DriveEventService$a extends Handler
{
    final /* synthetic */ DriveEventService NR;
    
    DriveEventService$a(final DriveEventService nr) {
        this.NR = nr;
    }
    
    private Message b(final OnEventResponse onEventResponse) {
        return this.obtainMessage(1, (Object)onEventResponse);
    }
    
    private Message hW() {
        return this.obtainMessage(2);
    }
    
    public void handleMessage(final Message message) {
        v.n("DriveEventService", "handleMessage message type:" + message.what);
        switch (message.what) {
            default: {
                v.p("DriveEventService", "Unexpected message type:" + message.what);
            }
            case 1: {
                this.NR.a((OnEventResponse)message.obj);
            }
            case 2: {
                this.getLooper().quit();
            }
        }
    }
}
