// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import android.os.IBinder;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import java.util.Map;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.d;
import com.google.android.gms.cast.Cast$MessageReceivedCallback;

class ij$c$4 implements Runnable
{
    final /* synthetic */ String EE;
    final /* synthetic */ ij$c GT;
    final /* synthetic */ String GW;
    
    ij$c$4(final ij$c gt, final String ee, final String gw) {
        this.GT = gt;
        this.EE = ee;
        this.GW = gw;
    }
    
    @Override
    public void run() {
        synchronized (this.GT.GQ.Gu) {
            final Cast$MessageReceivedCallback cast$MessageReceivedCallback = this.GT.GQ.Gu.get(this.EE);
            // monitorexit(ij.g(this.GT.GQ))
            if (cast$MessageReceivedCallback != null) {
                cast$MessageReceivedCallback.onMessageReceived(this.GT.GQ.Gt, this.EE, this.GW);
                return;
            }
        }
        ij.Gr.b("Discarded message for unknown namespace '%s'", this.EE);
    }
}
