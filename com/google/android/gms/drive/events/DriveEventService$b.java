// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.Message;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.concurrent.CountDownLatch;
import android.app.Service;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.drive.internal.OnEventResponse;
import com.google.android.gms.drive.internal.ad$a;

final class DriveEventService$b extends ad$a
{
    final /* synthetic */ DriveEventService NR;
    
    DriveEventService$b(final DriveEventService nr) {
        this.NR = nr;
    }
    
    public void c(final OnEventResponse onEventResponse) {
        synchronized (this.NR) {
            v.n("DriveEventService", "onEvent: " + onEventResponse);
            n.i(this.NR.NO);
            this.NR.hV();
            this.NR.NO.sendMessage(this.NR.NO.b(onEventResponse));
        }
    }
}
