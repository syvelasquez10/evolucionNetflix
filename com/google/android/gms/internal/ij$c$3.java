// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
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
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
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

class ij$c$3 implements Runnable
{
    final /* synthetic */ ij$c GT;
    final /* synthetic */ ig GV;
    
    ij$c$3(final ij$c gt, final ig gv) {
        this.GT = gt;
        this.GV = gv;
    }
    
    @Override
    public void run() {
        this.GT.GQ.a(this.GV);
    }
}
