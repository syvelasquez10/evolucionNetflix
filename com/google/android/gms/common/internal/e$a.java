// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.util.Collection;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import java.util.ArrayList;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class e$a extends Handler
{
    final /* synthetic */ e LJ;
    
    public e$a(final e lj, final Looper looper) {
        this.LJ = lj;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 1) {
            synchronized (this.LJ.LF) {
                if (this.LJ.LE.gr() && this.LJ.LE.isConnected() && this.LJ.LF.contains(message.obj)) {
                    ((GoogleApiClient$ConnectionCallbacks)message.obj).onConnected(this.LJ.LE.fD());
                }
                return;
            }
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
    }
}
