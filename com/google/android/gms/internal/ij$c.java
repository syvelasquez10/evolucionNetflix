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
import com.google.android.gms.cast.Cast$MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;
import java.util.Map;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast$Listener;
import com.google.android.gms.common.internal.d;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.atomic.AtomicBoolean;

class ij$c extends io$a
{
    final /* synthetic */ ij GQ;
    private AtomicBoolean GR;
    
    private ij$c(final ij gq) {
        this.GQ = gq;
        this.GR = new AtomicBoolean(false);
    }
    
    private boolean ag(final int n) {
        synchronized (ij.GM) {
            if (this.GQ.GK != null) {
                this.GQ.GK.b(new Status(n));
                this.GQ.GK = null;
                return true;
            }
            return false;
        }
    }
    
    private void c(final long n, final int n2) {
        synchronized (this.GQ.GH) {
            final BaseImplementation$b<Status> baseImplementation$b = this.GQ.GH.remove(n);
            // monitorexit(ij.i(this.GQ))
            if (baseImplementation$b != null) {
                baseImplementation$b.b(new Status(n2));
            }
        }
    }
    
    public void a(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
        if (this.GR.get()) {
            return;
        }
        this.GQ.Gs = applicationMetadata;
        this.GQ.GE = applicationMetadata.getApplicationId();
        this.GQ.GF = s2;
        synchronized (ij.GL) {
            if (this.GQ.GJ != null) {
                this.GQ.GJ.b(new ij$a(new Status(0), applicationMetadata, s, s2, b));
                this.GQ.GJ = null;
            }
        }
    }
    
    public void a(final String s, final double n, final boolean b) {
        ij.Gr.b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void a(final String s, final long n) {
        if (this.GR.get()) {
            return;
        }
        this.c(n, 0);
    }
    
    public void a(final String s, final long n, final int n2) {
        if (this.GR.get()) {
            return;
        }
        this.c(n, n2);
    }
    
    public void ac(final int n) {
        if (this.fL()) {
            ij.Gr.b("ICastDeviceControllerListener.onDisconnected: %d", n);
            if (n != 0) {
                this.GQ.aA(2);
            }
        }
    }
    
    public void ad(final int n) {
        if (this.GR.get()) {
            return;
        }
        synchronized (ij.GL) {
            if (this.GQ.GJ != null) {
                this.GQ.GJ.b(new ij$a(new Status(n)));
                this.GQ.GJ = null;
            }
        }
    }
    
    public void ae(final int n) {
        if (this.GR.get()) {
            return;
        }
        this.ag(n);
    }
    
    public void af(final int n) {
        if (this.GR.get()) {
            return;
        }
        this.ag(n);
    }
    
    public void b(final ig ig) {
        if (this.GR.get()) {
            return;
        }
        ij.Gr.b("onApplicationStatusChanged", new Object[0]);
        this.GQ.mHandler.post((Runnable)new ij$c$3(this, ig));
    }
    
    public void b(final il il) {
        if (this.GR.get()) {
            return;
        }
        ij.Gr.b("onDeviceStatusChanged", new Object[0]);
        this.GQ.mHandler.post((Runnable)new ij$c$2(this, il));
    }
    
    public void b(final String s, final byte[] array) {
        if (this.GR.get()) {
            return;
        }
        ij.Gr.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
    }
    
    public boolean fL() {
        if (this.GR.getAndSet(true)) {
            return false;
        }
        this.GQ.fC();
        return true;
    }
    
    public boolean fM() {
        return this.GR.get();
    }
    
    public void k(final String s, final String s2) {
        if (this.GR.get()) {
            return;
        }
        ij.Gr.b("Receive (type=text, ns=%s) %s", s, s2);
        this.GQ.mHandler.post((Runnable)new ij$c$4(this, s, s2));
    }
    
    public void onApplicationDisconnected(final int n) {
        if (!this.GR.get()) {
            this.GQ.GE = null;
            this.GQ.GF = null;
            this.ag(n);
            if (this.GQ.EO != null) {
                this.GQ.mHandler.post((Runnable)new ij$c$1(this, n));
            }
        }
    }
}
