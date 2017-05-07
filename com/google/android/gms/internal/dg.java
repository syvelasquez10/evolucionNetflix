// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.HashMap;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;

public final class dg extends dw<di>
{
    private static final dk lA;
    private static final Object lQ;
    private static final Object lR;
    private final Cast.Listener kw;
    private ApplicationMetadata lB;
    private final CastDevice lC;
    private final dj lD;
    private final Map<String, Cast.MessageReceivedCallback> lE;
    private final long lF;
    private String lG;
    private boolean lH;
    private boolean lI;
    private final AtomicLong lJ;
    private String lK;
    private String lL;
    private Bundle lM;
    private Map<Long, com.google.android.gms.common.api.a.c<Status>> lN;
    private com.google.android.gms.common.api.a.c<Cast.ApplicationConnectionResult> lO;
    private com.google.android.gms.common.api.a.c<Status> lP;
    private double lb;
    private boolean lc;
    private final Handler mHandler;
    
    static {
        lA = new dk("CastClientImpl");
        lQ = new Object();
        lR = new Object();
    }
    
    public dg(final Context context, final CastDevice lc, final long lf, final Cast.Listener kw, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, connectionCallbacks, onConnectionFailedListener, (String[])null);
        this.lC = lc;
        this.kw = kw;
        this.lF = lf;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.lE = new HashMap<String, Cast.MessageReceivedCallback>();
        this.lI = false;
        this.lB = null;
        this.lG = null;
        this.lb = 0.0;
        this.lc = false;
        this.lJ = new AtomicLong(0L);
        this.lN = new HashMap<Long, com.google.android.gms.common.api.a.c<Status>>();
        this.lD = new dj.a() {
            private void b(final long n, final int n2) {
                synchronized (dg.this.lN) {
                    final com.google.android.gms.common.api.a.c<Status> c = dg.this.lN.remove(n);
                    // monitorexit(dg.g(this.lS))
                    if (c != null) {
                        c.a(new Status(n2));
                    }
                }
            }
            
            private boolean x(final int n) {
                synchronized (dg.lR) {
                    if (dg.this.lP != null) {
                        dg.this.lP.a(new Status(n));
                        dg.this.lP = null;
                        return true;
                    }
                    return false;
                }
            }
            
            public void a(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
                dg.this.lK = applicationMetadata.getApplicationId();
                dg.this.lL = s2;
                synchronized (dg.lQ) {
                    if (dg.this.lO != null) {
                        dg.this.lO.a(new dg.a(new Status(0), applicationMetadata, s, s2, b));
                        dg.this.lO = null;
                    }
                }
            }
            
            public void a(final String s, final long n) {
                this.b(n, 0);
            }
            
            public void a(final String s, final long n, final int n2) {
                this.b(n, n2);
            }
            
            public void a(final String s, final String s2) {
                dg.lA.b("Receive (type=text, ns=%s) %s", s, s2);
                dg.this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        synchronized (dg.this.lE) {
                            final Cast.MessageReceivedCallback messageReceivedCallback = dg.this.lE.get(s);
                            // monitorexit(dg.e(this.lU.lS))
                            if (messageReceivedCallback != null) {
                                messageReceivedCallback.onMessageReceived(dg.this.lC, s, s2);
                                return;
                            }
                        }
                        dg.lA.b("Discarded message for unknown namespace '%s'", s);
                    }
                });
            }
            
            public void b(final String s, final double n, final boolean b) {
                dg.this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        dg.this.a(s, n, b);
                    }
                });
            }
            
            public void b(final String s, final byte[] array) {
                dg.lA.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
            }
            
            public void onApplicationDisconnected(final int n) {
                dg.this.lK = null;
                dg.this.lL = null;
                if (!this.x(n) && dg.this.kw != null) {
                    dg.this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (dg.this.kw != null) {
                                dg.this.kw.onApplicationDisconnected(n);
                            }
                        }
                    });
                }
            }
            
            public void t(final int n) {
                dg.lA.b("ICastDeviceControllerListener.onDisconnected", new Object[0]);
                dg.this.lI = false;
                dg.this.lB = null;
                if (n != 0) {
                    dg.this.I(2);
                }
            }
            
            public void u(final int n) {
                synchronized (dg.lQ) {
                    if (dg.this.lO != null) {
                        dg.this.lO.a(new dg.a(new Status(n)));
                        dg.this.lO = null;
                    }
                }
            }
            
            public void v(final int n) {
                this.x(n);
            }
            
            public void w(final int n) {
                this.x(n);
            }
        };
    }
    
    private void a(final String lg, final double lb, final boolean lc) {
        boolean b;
        if (!dh.a(lg, this.lG)) {
            this.lG = lg;
            b = true;
        }
        else {
            b = false;
        }
        if (this.kw != null && (b || this.lH)) {
            this.kw.onApplicationStatusChanged();
        }
        boolean b2;
        if (lb != this.lb) {
            this.lb = lb;
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (lc != this.lc) {
            this.lc = lc;
            b2 = true;
        }
        dg.lA.b("hasChange=%b, mFirstStatusUpdate=%b", b2, this.lH);
        if (this.kw != null && (b2 || this.lH)) {
            this.kw.onVolumeChanged();
        }
        this.lH = false;
    }
    
    private void aX() throws IllegalStateException {
        if (!this.lI) {
            throw new IllegalStateException("not connected to a device");
        }
    }
    
    private void d(final com.google.android.gms.common.api.a.c<Cast.ApplicationConnectionResult> lo) {
        synchronized (dg.lQ) {
            if (this.lO != null) {
                this.lO.a(new a(new Status(2002)));
            }
            this.lO = lo;
        }
    }
    
    private void f(final com.google.android.gms.common.api.a.c<Status> lp) {
        synchronized (dg.lR) {
            if (this.lP != null) {
                lp.a(new Status(2001));
                return;
            }
            this.lP = lp;
        }
    }
    
    public void C(final String s) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.lE) {
            final Cast.MessageReceivedCallback messageReceivedCallback = this.lE.remove(s);
            // monitorexit(this.lE)
            if (messageReceivedCallback != null) {
                this.bQ().F(s);
            }
        }
    }
    
    public void a(final double n) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        this.bQ().a(n, this.lb, this.lc);
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 || n == 1001) {
            this.lI = true;
            this.lH = true;
        }
        else {
            this.lI = false;
        }
        int n2 = n;
        if (n == 1001) {
            (this.lM = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n2 = 0;
        }
        super.a(n2, binder, bundle);
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        dg.lA.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", this.lK, this.lL);
        this.lC.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.lF);
        if (this.lK != null) {
            bundle.putString("last_application_id", this.lK);
            if (this.lL != null) {
                bundle.putString("last_session_id", this.lL);
            }
        }
        ec.a(e, 4242000, this.getContext().getPackageName(), this.lD.asBinder(), bundle);
    }
    
    public void a(final String s, final Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        this.C(s);
        if (messageReceivedCallback == null) {
            return;
        }
        synchronized (this.lE) {
            this.lE.put(s, messageReceivedCallback);
            // monitorexit(this.lE)
            this.bQ().E(s);
        }
    }
    
    public void a(final String s, final com.google.android.gms.common.api.a.c<Status> c) throws IllegalStateException, RemoteException {
        this.f(c);
        this.bQ().D(s);
    }
    
    public void a(final String s, final String s2, final com.google.android.gms.common.api.a.c<Status> c) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s == null || s.length() > 128) {
            throw new IllegalArgumentException("Invalid namespace length");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        this.aX();
        final long incrementAndGet = this.lJ.incrementAndGet();
        this.bQ().a(s, s2, incrementAndGet);
        this.lN.put(incrementAndGet, c);
    }
    
    public void a(final String s, final boolean b, final com.google.android.gms.common.api.a.c<Cast.ApplicationConnectionResult> c) throws IllegalStateException, RemoteException {
        this.d(c);
        this.bQ().c(s, b);
    }
    
    @Override
    public Bundle aU() {
        if (this.lM != null) {
            final Bundle lm = this.lM;
            this.lM = null;
            return lm;
        }
        return super.aU();
    }
    
    public void aV() throws IllegalStateException, RemoteException {
        this.bQ().aV();
    }
    
    public double aW() throws IllegalStateException {
        this.aX();
        return this.lb;
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    public void b(final String s, final String s2, final com.google.android.gms.common.api.a.c<Cast.ApplicationConnectionResult> c) throws IllegalStateException, RemoteException {
        this.d(c);
        this.bQ().b(s, s2);
    }
    
    @Override
    public void disconnect() {
        try {
            if (!this.isConnected()) {
                return;
            }
            synchronized (this.lE) {
                this.lE.clear();
                // monitorexit(this.lE)
                this.bQ().disconnect();
            }
        }
        catch (RemoteException ex) {
            try {
                dg.lA.b("Error while disconnecting the controller interface: %s", ex.getMessage());
            }
            finally {
                super.disconnect();
            }
        }
    }
    
    public void e(final com.google.android.gms.common.api.a.c<Status> c) throws IllegalStateException, RemoteException {
        this.f(c);
        this.bQ().bb();
    }
    
    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        this.aX();
        return this.lB;
    }
    
    public String getApplicationStatus() throws IllegalStateException {
        this.aX();
        return this.lG;
    }
    
    public boolean isMute() throws IllegalStateException {
        this.aX();
        return this.lc;
    }
    
    public void n(final boolean b) throws IllegalStateException, RemoteException {
        this.bQ().a(b, this.lb, this.lc);
    }
    
    protected di u(final IBinder binder) {
        return di.a.v(binder);
    }
    
    private static final class a implements ApplicationConnectionResult
    {
        private final Status jY;
        private final ApplicationMetadata lX;
        private final String lY;
        private final String lZ;
        private final boolean ma;
        
        public a(final Status status) {
            this(status, null, null, null, false);
        }
        
        public a(final Status jy, final ApplicationMetadata lx, final String ly, final String lz, final boolean ma) {
            this.jY = jy;
            this.lX = lx;
            this.lY = ly;
            this.lZ = lz;
            this.ma = ma;
        }
        
        @Override
        public ApplicationMetadata getApplicationMetadata() {
            return this.lX;
        }
        
        @Override
        public String getApplicationStatus() {
            return this.lY;
        }
        
        @Override
        public String getSessionId() {
            return this.lZ;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
        
        @Override
        public boolean getWasLaunched() {
            return this.ma;
        }
    }
}
