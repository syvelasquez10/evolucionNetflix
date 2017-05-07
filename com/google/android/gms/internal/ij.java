// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import java.util.Map;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicLong;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.internal.d;

public final class ij extends d<in>
{
    private static final Object GL;
    private static final Object GM;
    private static final ip Gr;
    private final Cast.Listener EO;
    private double FA;
    private boolean FB;
    private boolean GA;
    private int GB;
    private int GC;
    private final AtomicLong GD;
    private String GE;
    private String GF;
    private Bundle GG;
    private Map<Long, BaseImplementation.b<Status>> GH;
    private b GI;
    private BaseImplementation.b<Cast.ApplicationConnectionResult> GJ;
    private BaseImplementation.b<Status> GK;
    private ApplicationMetadata Gs;
    private final CastDevice Gt;
    private final Map<String, Cast.MessageReceivedCallback> Gu;
    private final long Gv;
    private c Gw;
    private String Gx;
    private boolean Gy;
    private boolean Gz;
    private final Handler mHandler;
    
    static {
        Gr = new ip("CastClientImpl");
        GL = new Object();
        GM = new Object();
    }
    
    public ij(final Context context, final Looper looper, final CastDevice gt, final long gv, final Cast.Listener eo, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[])null);
        this.Gt = gt;
        this.EO = eo;
        this.Gv = gv;
        this.mHandler = new Handler(looper);
        this.Gu = new HashMap<String, Cast.MessageReceivedCallback>();
        this.GD = new AtomicLong(0L);
        this.GH = new HashMap<Long, BaseImplementation.b<Status>>();
        this.fC();
        this.registerConnectionFailedListener(this.GI = new b());
    }
    
    private void a(final ig ig) {
        final String fz = ig.fz();
        boolean b;
        if (!ik.a(fz, this.Gx)) {
            this.Gx = fz;
            b = true;
        }
        else {
            b = false;
        }
        ij.Gr.b("hasChanged=%b, mFirstApplicationStatusUpdate=%b", b, this.Gy);
        if (this.EO != null && (b || this.Gy)) {
            this.EO.onApplicationStatusChanged();
        }
        this.Gy = false;
    }
    
    private void a(final il il) {
        this.Gs = il.getApplicationMetadata();
        final double ff = il.fF();
        boolean b;
        if (ff != Double.NaN && ff != this.FA) {
            this.FA = ff;
            b = true;
        }
        else {
            b = false;
        }
        final boolean fn = il.fN();
        if (fn != this.FB) {
            this.FB = fn;
            b = true;
        }
        ij.Gr.b("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", b, this.Gz);
        if (this.EO != null && (b || this.Gz)) {
            this.EO.onVolumeChanged();
        }
        final int fo = il.fO();
        boolean b2;
        if (fo != this.GB) {
            this.GB = fo;
            b2 = true;
        }
        else {
            b2 = false;
        }
        ij.Gr.b("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", b2, this.Gz);
        if (this.EO != null && (b2 || this.Gz)) {
            this.EO.W(this.GB);
        }
        final int fp = il.fP();
        boolean b3;
        if (fp != this.GC) {
            this.GC = fp;
            b3 = true;
        }
        else {
            b3 = false;
        }
        ij.Gr.b("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", b3, this.Gz);
        if (this.EO != null && (b3 || this.Gz)) {
            this.EO.X(this.GC);
        }
        this.Gz = false;
    }
    
    private void c(final BaseImplementation.b<Cast.ApplicationConnectionResult> gj) {
        synchronized (ij.GL) {
            if (this.GJ != null) {
                this.GJ.b(new a(new Status(2002)));
            }
            this.GJ = gj;
        }
    }
    
    private void e(final BaseImplementation.b<Status> gk) {
        synchronized (ij.GM) {
            if (this.GK != null) {
                gk.b(new Status(2001));
                return;
            }
            this.GK = gk;
        }
    }
    
    private void fC() {
        this.GA = false;
        this.GB = -1;
        this.GC = -1;
        this.Gs = null;
        this.Gx = null;
        this.FA = 0.0;
        this.FB = false;
    }
    
    private void fG() {
        ij.Gr.b("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.Gu) {
            this.Gu.clear();
        }
    }
    
    private void fH() throws IllegalStateException {
        if (!this.GA || this.Gw == null || this.Gw.fM()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }
    
    public void G(final boolean b) throws IllegalStateException, RemoteException {
        this.gS().a(b, this.FA, this.FB);
    }
    
    protected in L(final IBinder binder) {
        return in.a.M(binder);
    }
    
    public void a(final double n) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            throw new IllegalArgumentException("Volume cannot be " + n);
        }
        this.gS().a(n, this.FA, this.FB);
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        ij.Gr.b("in onPostInitHandler; statusCode=%d", n);
        if (n == 0 || n == 1001) {
            this.GA = true;
            this.Gy = true;
            this.Gz = true;
        }
        else {
            this.GA = false;
        }
        int n2 = n;
        if (n == 1001) {
            (this.GG = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            n2 = 0;
        }
        super.a(n2, binder, bundle);
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        final Bundle bundle = new Bundle();
        ij.Gr.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", this.GE, this.GF);
        this.Gt.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.Gv);
        if (this.GE != null) {
            bundle.putString("last_application_id", this.GE);
            if (this.GF != null) {
                bundle.putString("last_session_id", this.GF);
            }
        }
        this.Gw = new c();
        k.a(e, 6111000, this.getContext().getPackageName(), ((io.a)this.Gw).asBinder(), bundle);
    }
    
    public void a(final String s, final Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        ik.aF(s);
        this.aE(s);
        if (messageReceivedCallback == null) {
            return;
        }
        synchronized (this.Gu) {
            this.Gu.put(s, messageReceivedCallback);
            // monitorexit(this.Gu)
            this.gS().aI(s);
        }
    }
    
    public void a(final String s, final LaunchOptions launchOptions, final BaseImplementation.b<Cast.ApplicationConnectionResult> b) throws IllegalStateException, RemoteException {
        this.c(b);
        this.gS().a(s, launchOptions);
    }
    
    public void a(final String s, final BaseImplementation.b<Status> b) throws IllegalStateException, RemoteException {
        this.e(b);
        this.gS().aH(s);
    }
    
    public void a(final String s, final String s2, final BaseImplementation.b<Status> b) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        ik.aF(s);
        this.fH();
        final long incrementAndGet = this.GD.incrementAndGet();
        try {
            this.GH.put(incrementAndGet, b);
            this.gS().a(s, s2, incrementAndGet);
        }
        catch (Throwable t) {
            this.GH.remove(incrementAndGet);
            throw t;
        }
    }
    
    public void a(final String s, final boolean b, final BaseImplementation.b<Cast.ApplicationConnectionResult> b2) throws IllegalStateException, RemoteException {
        this.c(b2);
        this.gS().f(s, b);
    }
    
    public void aE(final String p0) throws IllegalArgumentException, RemoteException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            18
        //     7: new             Ljava/lang/IllegalArgumentException;
        //    10: dup            
        //    11: ldc_w           "Channel namespace cannot be null or empty"
        //    14: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/ij.Gu:Ljava/util/Map;
        //    22: astore_2       
        //    23: aload_2        
        //    24: monitorenter   
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/internal/ij.Gu:Ljava/util/Map;
        //    29: aload_1        
        //    30: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: checkcast       Lcom/google/android/gms/cast/Cast$MessageReceivedCallback;
        //    38: astore_3       
        //    39: aload_2        
        //    40: monitorexit    
        //    41: aload_3        
        //    42: ifnull          58
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/internal/ij.gS:()Landroid/os/IInterface;
        //    49: checkcast       Lcom/google/android/gms/internal/in;
        //    52: aload_1        
        //    53: invokeinterface com/google/android/gms/internal/in.aJ:(Ljava/lang/String;)V
        //    58: return         
        //    59: astore_1       
        //    60: aload_2        
        //    61: monitorexit    
        //    62: aload_1        
        //    63: athrow         
        //    64: astore_2       
        //    65: getstatic       com/google/android/gms/internal/ij.Gr:Lcom/google/android/gms/internal/ip;
        //    68: aload_2        
        //    69: ldc_w           "Error unregistering namespace (%s): %s"
        //    72: iconst_2       
        //    73: anewarray       Ljava/lang/Object;
        //    76: dup            
        //    77: iconst_0       
        //    78: aload_1        
        //    79: aastore        
        //    80: dup            
        //    81: iconst_1       
        //    82: aload_2        
        //    83: invokevirtual   java/lang/IllegalStateException.getMessage:()Ljava/lang/String;
        //    86: aastore        
        //    87: invokevirtual   com/google/android/gms/internal/ip.a:(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
        //    90: return         
        //    Exceptions:
        //  throws java.lang.IllegalArgumentException
        //  throws android.os.RemoteException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  25     41     59     64     Any
        //  45     58     64     91     Ljava/lang/IllegalStateException;
        //  60     62     59     64     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    public void b(final String s, final String s2, final BaseImplementation.b<Cast.ApplicationConnectionResult> b) throws IllegalStateException, RemoteException {
        this.c(b);
        this.gS().l(s, s2);
    }
    
    public void d(final BaseImplementation.b<Status> b) throws IllegalStateException, RemoteException {
        this.e(b);
        this.gS().fQ();
    }
    
    @Override
    public void disconnect() {
        ij.Gr.b("disconnect(); ServiceListener=%s, isConnected=%b", this.Gw, this.isConnected());
        final c gw = this.Gw;
        this.Gw = null;
        if (gw == null || !gw.fL()) {
            ij.Gr.b("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        this.fG();
        try {
            if (this.isConnected() || this.isConnecting()) {
                this.gS().disconnect();
            }
        }
        catch (RemoteException ex) {
            ij.Gr.a((Throwable)ex, "Error while disconnecting the controller interface: %s", ex.getMessage());
        }
        finally {
            super.disconnect();
        }
    }
    
    @Override
    public Bundle fD() {
        if (this.GG != null) {
            final Bundle gg = this.GG;
            this.GG = null;
            return gg;
        }
        return super.fD();
    }
    
    public void fE() throws IllegalStateException, RemoteException {
        this.gS().fE();
    }
    
    public double fF() throws IllegalStateException {
        this.fH();
        return this.FA;
    }
    
    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        this.fH();
        return this.Gs;
    }
    
    public String getApplicationStatus() throws IllegalStateException {
        this.fH();
        return this.Gx;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
    
    public boolean isMute() throws IllegalStateException {
        this.fH();
        return this.FB;
    }
    
    private static final class a implements ApplicationConnectionResult
    {
        private final Status CM;
        private final ApplicationMetadata GN;
        private final String GO;
        private final boolean GP;
        private final String vL;
        
        public a(final Status status) {
            this(status, null, null, null, false);
        }
        
        public a(final Status cm, final ApplicationMetadata gn, final String go, final String vl, final boolean gp) {
            this.CM = cm;
            this.GN = gn;
            this.GO = go;
            this.vL = vl;
            this.GP = gp;
        }
        
        @Override
        public ApplicationMetadata getApplicationMetadata() {
            return this.GN;
        }
        
        @Override
        public String getApplicationStatus() {
            return this.GO;
        }
        
        @Override
        public String getSessionId() {
            return this.vL;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public boolean getWasLaunched() {
            return this.GP;
        }
    }
    
    private class b implements OnConnectionFailedListener
    {
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            ij.this.fG();
        }
    }
    
    private class c extends io.a
    {
        private AtomicBoolean GR;
        
        private c() {
            this.GR = new AtomicBoolean(false);
        }
        
        private boolean ag(final int n) {
            synchronized (ij.GM) {
                if (ij.this.GK != null) {
                    ij.this.GK.b(new Status(n));
                    ij.this.GK = null;
                    return true;
                }
                return false;
            }
        }
        
        private void c(final long n, final int n2) {
            synchronized (ij.this.GH) {
                final BaseImplementation.b<Status> b = ij.this.GH.remove(n);
                // monitorexit(ij.i(this.GQ))
                if (b != null) {
                    b.b(new Status(n2));
                }
            }
        }
        
        public void a(final ApplicationMetadata applicationMetadata, final String s, final String s2, final boolean b) {
            if (this.GR.get()) {
                return;
            }
            ij.this.Gs = applicationMetadata;
            ij.this.GE = applicationMetadata.getApplicationId();
            ij.this.GF = s2;
            synchronized (ij.GL) {
                if (ij.this.GJ != null) {
                    ij.this.GJ.b(new ij.a(new Status(0), applicationMetadata, s, s2, b));
                    ij.this.GJ = null;
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
                    ij.this.aA(2);
                }
            }
        }
        
        public void ad(final int n) {
            if (this.GR.get()) {
                return;
            }
            synchronized (ij.GL) {
                if (ij.this.GJ != null) {
                    ij.this.GJ.b(new ij.a(new Status(n)));
                    ij.this.GJ = null;
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
            ij.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    ij.this.a(ig);
                }
            });
        }
        
        public void b(final il il) {
            if (this.GR.get()) {
                return;
            }
            ij.Gr.b("onDeviceStatusChanged", new Object[0]);
            ij.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    ij.this.a(il);
                }
            });
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
            ij.this.fC();
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
            ij.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    synchronized (ij.this.Gu) {
                        final Cast.MessageReceivedCallback messageReceivedCallback = ij.this.Gu.get(s);
                        // monitorexit(ij.g(this.GT.GQ))
                        if (messageReceivedCallback != null) {
                            messageReceivedCallback.onMessageReceived(ij.this.Gt, s, s2);
                            return;
                        }
                    }
                    ij.Gr.b("Discarded message for unknown namespace '%s'", s);
                }
            });
        }
        
        public void onApplicationDisconnected(final int n) {
            if (!this.GR.get()) {
                ij.this.GE = null;
                ij.this.GF = null;
                this.ag(n);
                if (ij.this.EO != null) {
                    ij.this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (ij.this.EO != null) {
                                ij.this.EO.onApplicationDisconnected(n);
                            }
                        }
                    });
                }
            }
        }
    }
}
