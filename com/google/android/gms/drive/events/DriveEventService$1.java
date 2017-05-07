// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import java.util.concurrent.TimeUnit;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.drive.internal.OnEventResponse;
import android.app.Service;
import com.google.android.gms.drive.internal.v;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;

class DriveEventService$1 extends Thread
{
    final /* synthetic */ CountDownLatch NQ;
    final /* synthetic */ DriveEventService NR;
    
    DriveEventService$1(final DriveEventService nr, final CountDownLatch nq) {
        this.NR = nr;
        this.NQ = nq;
    }
    
    @Override
    public void run() {
        try {
            Looper.prepare();
            this.NR.NO = new DriveEventService$a(this.NR);
            this.NQ.countDown();
            v.n("DriveEventService", "Bound and starting loop");
            Looper.loop();
            v.n("DriveEventService", "Finished loop");
        }
        finally {
            this.NR.NN.countDown();
        }
    }
}
