// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;
import android.text.TextUtils;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import android.os.Handler;

public final class en extends ff<ep>
{
    private static final er zb;
    private static final Object zs;
    private static final Object zt;
    private final Handler mHandler;
    private final Cast.Listener xX;
    private double yC;
    private boolean yD;
    private ApplicationMetadata zc;
    private final CastDevice zd;
    private final eq ze;
    private final Map<String, Cast.MessageReceivedCallback> zf;
    private final long zg;
    private String zh;
    private boolean zi;
    private boolean zj;
    private final AtomicLong zk;
    private String zl;
    private String zm;
    private Bundle zn;
    private Map<Long, com.google.android.gms.common.api.a.d<Status>> zo;
    private b zp;
    private com.google.android.gms.common.api.a.d<Cast.ApplicationConnectionResult> zq;
    private com.google.android.gms.common.api.a.d<Status> zr;
    
    static {
        zb = new er("CastClientImpl");
        zs = new Object();
        zt = new Object();
    }
    
    public en(final Context context, final Looper looper, final CastDevice zd, final long zg, final Cast.Listener xx, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[])null);
        this.zd = zd;
        this.xX = xx;
        this.zg = zg;
        this.mHandler = new Handler(looper);
        this.zf = new HashMap<String, Cast.MessageReceivedCallback>();
        this.zj = false;
        this.zc = null;
        this.zh = null;
        this.yC = 0.0;
        this.yD = false;
        this.zk = new AtomicLong(0L);
        this.zo = new HashMap<Long, com.google.android.gms.common.api.a.d<Status>>();
        this.registerConnectionFailedListener(this.zp = new b());
        this.ze = new eq.a() {
            private boolean D(final int n) {
                synchronized (en.zt) {
                    if (en.this.zr != null) {
                        en.this.zr.b(new Status(n));
                        en.this.zr = null;
                        return true;
                    }
                    return false;
                }
            }
            
            private void b(final long n, final int n2) {
                synchronized (en.this.zo) {
                    final com.google.android.gms.common.api.a.d<Status> d = en.this.zo.remove(n);
                    // monitorexit(en.g(this.zu))
                    if (d != null) {
                        d.b(new Status(n2));
                    }
                }
            }
            
            public void A(final int n) {
                synchronized (en.zs) {
                    if (en.this.zq != null) {
                        en.this.zq.b(new en.a(new Status(n)));
                        en.this.zq = null;
                    }
                }
            }
            
            public void B(final int n) {
                this.D(n);
            }
            
            public void C(final int n) {
                this.D(n);
            }
            
            public void a(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
                en.this.zc = applicationMetadata;
                en.this.zl = applicationMetadata.getApplicationId();
                en.this.zm = s2;
                synchronized (en.zs) {
                    if (en.this.zq != null) {
                        en.this.zq.b(new en.a(new Status(0), applicationMetadata, s, s2, b));
                        en.this.zq = null;
                    }
                }
            }
            
            public void a(final String s, final long n) {
                this.b(n, 0);
            }
            
            public void a(final String s, final long n, final int n2) {
                this.b(n, n2);
            }
            
            public void b(final String s, final double n, final boolean b) {
                en.this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        en.this.a(s, n, b);
                    }
                });
            }
            
            public void b(final String s, final byte[] array) {
                en.zb.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", s, array.length);
            }
            
            public void d(final String s, final String s2) {
                en.zb.b("Receive (type=text, ns=%s) %s", s, s2);
                en.this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        synchronized (en.this.zf) {
                            final Cast.MessageReceivedCallback messageReceivedCallback = en.this.zf.get(s);
                            // monitorexit(en.e(this.zw.zu))
                            if (messageReceivedCallback != null) {
                                messageReceivedCallback.onMessageReceived(en.this.zd, s, s2);
                                return;
                            }
                        }
                        en.zb.b("Discarded message for unknown namespace '%s'", s);
                    }
                });
            }
            
            public void onApplicationDisconnected(final int n) {
                en.this.zl = null;
                en.this.zm = null;
                if (!this.D(n) && en.this.xX != null) {
                    en.this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (en.this.xX != null) {
                                en.this.xX.onApplicationDisconnected(n);
                            }
                        }
                    });
                }
            }
            
            public void z(final int n) {
                en.zb.b("ICastDeviceControllerListener.onDisconnected: %d", n);
                en.this.zj = false;
                en.this.zc = null;
                if (n != 0) {
                    en.this.N(2);
                }
            }
        };
    }
    
    private void a(final String zh, final double yc, final boolean yd) {
        boolean b;
        if (!eo.a(zh, this.zh)) {
            this.zh = zh;
            b = true;
        }
        else {
            b = false;
        }
        if (this.xX != null && (b || this.zi)) {
            this.xX.onApplicationStatusChanged();
        }
        boolean b2;
        if (yc != this.yC) {
            this.yC = yc;
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (yd != this.yD) {
            this.yD = yd;
            b2 = true;
        }
        en.zb.b("hasChange=%b, mFirstStatusUpdate=%b", b2, this.zi);
        if (this.xX != null && (b2 || this.zi)) {
            this.xX.onVolumeChanged();
        }
        this.zi = false;
    }
    
    private void d(final com.google.android.gms.common.api.a.d<Cast.ApplicationConnectionResult> zq) {
        synchronized (en.zs) {
            if (this.zq != null) {
                this.zq.b(new a(new Status(2002)));
            }
            this.zq = zq;
        }
    }
    
    private void dJ() {
        en.zb.b("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zf) {
            this.zf.clear();
        }
    }
    
    private void dK() throws IllegalStateException {
        if (!this.zj) {
            throw new IllegalStateException("not connected to a device");
        }
    }
    
    private void f(final com.google.android.gms.common.api.a.d<Status> zr) {
        synchronized (en.zt) {
            if (this.zr != null) {
                zr.b(new Status(2001));
                return;
            }
            this.zr = zr;
        }
    }
    
    public void V(final String p0) throws IllegalArgumentException, RemoteException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            17
        //     7: new             Ljava/lang/IllegalArgumentException;
        //    10: dup            
        //    11: ldc             "Channel namespace cannot be null or empty"
        //    13: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/en.zf:Ljava/util/Map;
        //    21: astore_2       
        //    22: aload_2        
        //    23: monitorenter   
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/internal/en.zf:Ljava/util/Map;
        //    28: aload_1        
        //    29: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    34: checkcast       Lcom/google/android/gms/cast/Cast$MessageReceivedCallback;
        //    37: astore_3       
        //    38: aload_2        
        //    39: monitorexit    
        //    40: aload_3        
        //    41: ifnull          57
        //    44: aload_0        
        //    45: invokevirtual   com/google/android/gms/internal/en.eM:()Landroid/os/IInterface;
        //    48: checkcast       Lcom/google/android/gms/internal/ep;
        //    51: aload_1        
        //    52: invokeinterface com/google/android/gms/internal/ep.aa:(Ljava/lang/String;)V
        //    57: return         
        //    58: astore_1       
        //    59: aload_2        
        //    60: monitorexit    
        //    61: aload_1        
        //    62: athrow         
        //    63: astore_2       
        //    64: getstatic       com/google/android/gms/internal/en.zb:Lcom/google/android/gms/internal/er;
        //    67: aload_2        
        //    68: ldc_w           "Error unregistering namespace (%s): %s"
        //    71: iconst_2       
        //    72: anewarray       Ljava/lang/Object;
        //    75: dup            
        //    76: iconst_0       
        //    77: aload_1        
        //    78: aastore        
        //    79: dup            
        //    80: iconst_1       
        //    81: aload_2        
        //    82: invokevirtual   java/lang/IllegalStateException.getMessage:()Ljava/lang/String;
        //    85: aastore        
        //    86: invokevirtual   com/google/android/gms/internal/er.a:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //    89: return         
        //    Exceptions:
        //  throws java.lang.IllegalArgumentException
        //  throws android.os.RemoteException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  24     40     58     63     Any
        //  44     57     63     90     Ljava/lang/IllegalStateException;
        //  59     61     58     63     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void a(final double n) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        this.eM().a(n, this.yC, this.yD);
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 || n == 1001) {
            this.zj = true;
            this.zi = true;
        }
        else {
            this.zj = false;
        }
        int n2 = n;
        if (n == 1001) {
            (this.zn = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n2 = 0;
        }
        super.a(n2, binder, bundle);
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        en.zb.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", this.zl, this.zm);
        this.zd.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zg);
        if (this.zl != null) {
            bundle.putString("last_application_id", this.zl);
            if (this.zm != null) {
                bundle.putString("last_session_id", this.zm);
            }
        }
        fm.a(e, 4452000, this.getContext().getPackageName(), this.ze.asBinder(), bundle);
    }
    
    public void a(final String s, final Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        eo.W(s);
        this.V(s);
        if (messageReceivedCallback == null) {
            return;
        }
        synchronized (this.zf) {
            this.zf.put(s, messageReceivedCallback);
            // monitorexit(this.zf)
            this.eM().Z(s);
        }
    }
    
    public void a(final String s, final com.google.android.gms.common.api.a.d<Status> d) throws IllegalStateException, RemoteException {
        this.f(d);
        this.eM().Y(s);
    }
    
    public void a(final String s, final String s2, final com.google.android.gms.common.api.a.d<Status> d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        eo.W(s);
        this.dK();
        final long incrementAndGet = this.zk.incrementAndGet();
        this.eM().a(s, s2, incrementAndGet);
        this.zo.put(incrementAndGet, d);
    }
    
    public void a(final String s, final boolean b, final com.google.android.gms.common.api.a.d<Cast.ApplicationConnectionResult> d) throws IllegalStateException, RemoteException {
        this.d(d);
        this.eM().e(s, b);
    }
    
    public void b(final String s, final String s2, final com.google.android.gms.common.api.a.d<Cast.ApplicationConnectionResult> d) throws IllegalStateException, RemoteException {
        this.d(d);
        this.eM().e(s, s2);
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    @Override
    public Bundle dG() {
        if (this.zn != null) {
            final Bundle zn = this.zn;
            this.zn = null;
            return zn;
        }
        return super.dG();
    }
    
    public void dH() throws IllegalStateException, RemoteException {
        this.eM().dH();
    }
    
    public double dI() throws IllegalStateException {
        this.dK();
        return this.yC;
    }
    
    @Override
    public void disconnect() {
        this.dJ();
        try {
            if (this.isConnected()) {
                this.eM().disconnect();
            }
        }
        catch (RemoteException ex) {
            en.zb.b("Error while disconnecting the controller interface: %s", ex.getMessage());
        }
        finally {
            super.disconnect();
        }
    }
    
    public void e(final com.google.android.gms.common.api.a.d<Status> d) throws IllegalStateException, RemoteException {
        this.f(d);
        this.eM().dO();
    }
    
    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        this.dK();
        return this.zc;
    }
    
    public String getApplicationStatus() throws IllegalStateException {
        this.dK();
        return this.zh;
    }
    
    public boolean isMute() throws IllegalStateException {
        this.dK();
        return this.yD;
    }
    
    public void v(final boolean b) throws IllegalStateException, RemoteException {
        this.eM().a(b, this.yC, this.yD);
    }
    
    protected ep x(final IBinder binder) {
        return ep.a.y(binder);
    }
    
    private static final class a implements ApplicationConnectionResult
    {
        private final String qL;
        private final Status wJ;
        private final String zA;
        private final boolean zB;
        private final ApplicationMetadata zz;
        
        public a(final Status status) {
            this(status, null, null, null, false);
        }
        
        public a(final Status wj, final ApplicationMetadata zz, final String za, final String ql, final boolean zb) {
            this.wJ = wj;
            this.zz = zz;
            this.zA = za;
            this.qL = ql;
            this.zB = zb;
        }
        
        @Override
        public ApplicationMetadata getApplicationMetadata() {
            return this.zz;
        }
        
        @Override
        public String getApplicationStatus() {
            return this.zA;
        }
        
        @Override
        public String getSessionId() {
            return this.qL;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public boolean getWasLaunched() {
            return this.zB;
        }
    }
    
    private class b implements GoogleApiClient.OnConnectionFailedListener
    {
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            en.this.dJ();
        }
    }
}
