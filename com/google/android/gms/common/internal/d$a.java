// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.content.Context;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api$a;
import android.util.Log;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class d$a extends Handler
{
    final /* synthetic */ d Lx;
    
    public d$a(final d lx, final Looper looper) {
        this.Lx = lx;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 1 && !this.Lx.isConnecting()) {
            final d$b d$b = (d$b)message.obj;
            d$b.gT();
            d$b.unregister();
            return;
        }
        if (message.what == 3) {
            this.Lx.IQ.b(new ConnectionResult((int)message.obj, null));
            return;
        }
        if (message.what == 4) {
            this.Lx.az(1);
            this.Lx.Lr = null;
            this.Lx.IQ.aB((int)message.obj);
            return;
        }
        if (message.what == 2 && !this.Lx.isConnected()) {
            final d$b d$b2 = (d$b)message.obj;
            d$b2.gT();
            d$b2.unregister();
            return;
        }
        if (message.what == 2 || message.what == 1) {
            ((d$b)message.obj).gU();
            return;
        }
        Log.wtf("GmsClient", "Don't know how to handle this message.");
    }
}
