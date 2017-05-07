// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import com.google.android.gms.common.data.DataHolder;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.Message;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.os.IInterface;

public abstract class dw<T extends IInterface> implements GooglePlayServicesClient, Api.a, dx.b
{
    public static final String[] pk;
    private final String[] jF;
    private final Context mContext;
    final Handler mHandler;
    private final dx ne;
    private T pe;
    private final ArrayList<b<?>> pf;
    private f pg;
    boolean ph;
    boolean pi;
    private final Object pj;
    
    static {
        pk = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected dw(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String... array) {
        this(context, new c(connectionCallbacks), new g(onConnectionFailedListener), array);
    }
    
    protected dw(final Context context, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String... jf) {
        this.pf = new ArrayList<b<?>>();
        this.ph = false;
        this.pi = false;
        this.pj = new Object();
        this.mContext = eg.f(context);
        this.ne = new dx(context, (dx.b)this, null);
        this.mHandler = new a(context.getMainLooper());
        this.a(jf);
        this.jF = jf;
        this.registerConnectionCallbacks(eg.f(connectionCallbacks));
        this.registerConnectionFailedListener(eg.f(onConnectionFailedListener));
    }
    
    public void I(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)n));
    }
    
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)new h(n, binder, bundle)));
    }
    
    public final void a(final b<?> b) {
        synchronized (this.pf) {
            this.pf.add(b);
            // monitorexit(this.pf)
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, (Object)b));
        }
    }
    
    protected abstract void a(final ec p0, final e p1) throws RemoteException;
    
    protected void a(final String... array) {
    }
    
    @Override
    public Bundle aU() {
        return null;
    }
    
    protected abstract String am();
    
    protected abstract String an();
    
    public final String[] bO() {
        return this.jF;
    }
    
    protected final void bP() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    protected final T bQ() {
        this.bP();
        return this.pe;
    }
    
    @Override
    public boolean bp() {
        return this.ph;
    }
    
    @Override
    public void connect() {
        while (true) {
            this.ph = true;
            synchronized (this.pj) {
                this.pi = true;
                // monitorexit(this.pj)
                final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
                if (googlePlayServicesAvailable != 0) {
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)googlePlayServicesAvailable));
                    return;
                }
            }
            if (this.pg != null) {
                Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
                this.pe = null;
                dy.s(this.mContext).b(this.am(), this.pg);
            }
            this.pg = new f();
            if (!dy.s(this.mContext).a(this.am(), this.pg)) {
                break;
            }
            return;
        }
        Log.e("GmsClient", "unable to connect to service: " + this.am());
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)9));
    }
    
    @Override
    public void disconnect() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_0       
        //     2: putfield        com/google/android/gms/internal/dw.ph:Z
        //     5: aload_0        
        //     6: getfield        com/google/android/gms/internal/dw.pj:Ljava/lang/Object;
        //     9: astore_3       
        //    10: aload_3        
        //    11: monitorenter   
        //    12: aload_0        
        //    13: iconst_0       
        //    14: putfield        com/google/android/gms/internal/dw.pi:Z
        //    17: aload_3        
        //    18: monitorexit    
        //    19: aload_0        
        //    20: getfield        com/google/android/gms/internal/dw.pf:Ljava/util/ArrayList;
        //    23: astore_3       
        //    24: aload_3        
        //    25: monitorenter   
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/internal/dw.pf:Ljava/util/ArrayList;
        //    30: invokevirtual   java/util/ArrayList.size:()I
        //    33: istore_2       
        //    34: iconst_0       
        //    35: istore_1       
        //    36: iload_1        
        //    37: iload_2        
        //    38: if_icmpge       69
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/internal/dw.pf:Ljava/util/ArrayList;
        //    45: iload_1        
        //    46: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    49: checkcast       Lcom/google/android/gms/internal/dw$b;
        //    52: invokevirtual   com/google/android/gms/internal/dw$b.bS:()V
        //    55: iload_1        
        //    56: iconst_1       
        //    57: iadd           
        //    58: istore_1       
        //    59: goto            36
        //    62: astore          4
        //    64: aload_3        
        //    65: monitorexit    
        //    66: aload           4
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/google/android/gms/internal/dw.pf:Ljava/util/ArrayList;
        //    73: invokevirtual   java/util/ArrayList.clear:()V
        //    76: aload_3        
        //    77: monitorexit    
        //    78: aload_0        
        //    79: aconst_null    
        //    80: putfield        com/google/android/gms/internal/dw.pe:Landroid/os/IInterface;
        //    83: aload_0        
        //    84: getfield        com/google/android/gms/internal/dw.pg:Lcom/google/android/gms/internal/dw$f;
        //    87: ifnull          121
        //    90: aload_0        
        //    91: getfield        com/google/android/gms/internal/dw.mContext:Landroid/content/Context;
        //    94: invokestatic    com/google/android/gms/internal/dy.s:(Landroid/content/Context;)Lcom/google/android/gms/internal/dy;
        //    97: aload_0        
        //    98: invokevirtual   com/google/android/gms/internal/dw.am:()Ljava/lang/String;
        //   101: aload_0        
        //   102: getfield        com/google/android/gms/internal/dw.pg:Lcom/google/android/gms/internal/dw$f;
        //   105: invokevirtual   com/google/android/gms/internal/dy.b:(Ljava/lang/String;Lcom/google/android/gms/internal/dw$f;)V
        //   108: aload_0        
        //   109: aconst_null    
        //   110: putfield        com/google/android/gms/internal/dw.pg:Lcom/google/android/gms/internal/dw$f;
        //   113: aload_0        
        //   114: getfield        com/google/android/gms/internal/dw.ne:Lcom/google/android/gms/internal/dx;
        //   117: iconst_m1      
        //   118: invokevirtual   com/google/android/gms/internal/dx.J:(I)V
        //   121: return         
        //   122: astore          4
        //   124: aload_3        
        //   125: monitorexit    
        //   126: aload           4
        //   128: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  12     19     62     69     Any
        //  26     34     122    129    Any
        //  41     55     122    129    Any
        //  64     66     62     69     Any
        //  69     78     122    129    Any
        //  124    126    122    129    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public boolean isConnected() {
        return this.pe != null;
    }
    
    @Override
    public boolean isConnecting() {
        synchronized (this.pj) {
            return this.pi;
        }
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.ne.isConnectionCallbacksRegistered(new c(connectionCallbacks));
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.ne.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    protected abstract T p(final IBinder p0);
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.ne.registerConnectionCallbacks(new c(connectionCallbacks));
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.ne.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.ne.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.ne.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.ne.unregisterConnectionCallbacks(new c(connectionCallbacks));
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.ne.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    protected final void w(final IBinder binder) {
        try {
            this.a(ec.a.y(binder), new e(this));
        }
        catch (RemoteException ex) {
            Log.w("GmsClient", "service died");
        }
    }
    
    final class a extends Handler
    {
        public a(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            if (message.what == 1 && !dw.this.isConnecting()) {
                final b b = (b)message.obj;
                b.aL();
                b.unregister();
                return;
            }
            synchronized (dw.this.pj) {
                dw.this.pi = false;
                // monitorexit(dw.b(this.pl))
                if (message.what == 3) {
                    dw.this.ne.a(new ConnectionResult((int)message.obj, null));
                    return;
                }
            }
            final Message message2;
            if (message2.what == 4) {
                dw.this.ne.J((int)message2.obj);
                return;
            }
            if (message2.what == 2 && !dw.this.isConnected()) {
                final b b2 = (b)message2.obj;
                b2.aL();
                b2.unregister();
                return;
            }
            if (message2.what == 2 || message2.what == 1) {
                ((b)message2.obj).bR();
                return;
            }
            Log.wtf("GmsClient", "Don't know how to handle this message.");
        }
    }
    
    protected abstract class b<TListener>
    {
        private TListener mListener;
        private boolean pm;
        
        public b(final TListener mListener) {
            this.mListener = mListener;
            this.pm = false;
        }
        
        protected abstract void aL();
        
        protected abstract void b(final TListener p0);
        
        public void bR() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/internal/dw$b.mListener:Ljava/lang/Object;
            //     6: astore_1       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/internal/dw$b.pm:Z
            //    11: ifeq            44
            //    14: ldc             "GmsClient"
            //    16: new             Ljava/lang/StringBuilder;
            //    19: dup            
            //    20: invokespecial   java/lang/StringBuilder.<init>:()V
            //    23: ldc             "Callback proxy "
            //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    28: aload_0        
            //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //    32: ldc             " being reused. This is not safe."
            //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    40: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //    43: pop            
            //    44: aload_0        
            //    45: monitorexit    
            //    46: aload_1        
            //    47: ifnull          81
            //    50: aload_0        
            //    51: aload_1        
            //    52: invokevirtual   com/google/android/gms/internal/dw$b.b:(Ljava/lang/Object;)V
            //    55: aload_0        
            //    56: monitorenter   
            //    57: aload_0        
            //    58: iconst_1       
            //    59: putfield        com/google/android/gms/internal/dw$b.pm:Z
            //    62: aload_0        
            //    63: monitorexit    
            //    64: aload_0        
            //    65: invokevirtual   com/google/android/gms/internal/dw$b.unregister:()V
            //    68: return         
            //    69: astore_1       
            //    70: aload_0        
            //    71: monitorexit    
            //    72: aload_1        
            //    73: athrow         
            //    74: astore_1       
            //    75: aload_0        
            //    76: invokevirtual   com/google/android/gms/internal/dw$b.aL:()V
            //    79: aload_1        
            //    80: athrow         
            //    81: aload_0        
            //    82: invokevirtual   com/google/android/gms/internal/dw$b.aL:()V
            //    85: goto            55
            //    88: astore_1       
            //    89: aload_0        
            //    90: monitorexit    
            //    91: aload_1        
            //    92: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  2      44     69     74     Any
            //  44     46     69     74     Any
            //  50     55     74     81     Ljava/lang/RuntimeException;
            //  57     64     88     93     Any
            //  70     72     69     74     Any
            //  89     91     88     93     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 56, Size: 56
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public void bS() {
            synchronized (this) {
                this.mListener = null;
            }
        }
        
        public void unregister() {
            this.bS();
            synchronized (dw.this.pf) {
                dw.this.pf.remove(this);
            }
        }
    }
    
    public static final class c implements GoogleApiClient.ConnectionCallbacks
    {
        private final GooglePlayServicesClient.ConnectionCallbacks pn;
        
        public c(final GooglePlayServicesClient.ConnectionCallbacks pn) {
            this.pn = pn;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof c) {
                return this.pn.equals(((c)o).pn);
            }
            return this.pn.equals(o);
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            this.pn.onConnected(bundle);
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
            this.pn.onDisconnected();
        }
    }
    
    public abstract class d<TListener> extends b<TListener>
    {
        private final DataHolder nE;
        
        public d(final TListener tListener, final DataHolder ne) {
            super(tListener);
            this.nE = ne;
        }
        
        protected abstract void a(final TListener p0, final DataHolder p1);
        
        @Override
        protected void aL() {
            if (this.nE != null) {
                this.nE.close();
            }
        }
        
        @Override
        protected final void b(final TListener tListener) {
            this.a(tListener, this.nE);
        }
    }
    
    public static final class e extends eb.a
    {
        private dw po;
        
        public e(final dw po) {
            this.po = po;
        }
        
        public void b(final int n, final IBinder binder, final Bundle bundle) {
            eg.b("onPostInitComplete can be called only once per call to getServiceFromBroker", this.po);
            this.po.a(n, binder, bundle);
            this.po = null;
        }
    }
    
    final class f implements ServiceConnection
    {
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            dw.this.w(binder);
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            dw.this.pe = null;
            dw.this.ne.J(1);
        }
    }
    
    public static final class g implements GoogleApiClient.OnConnectionFailedListener
    {
        private final GooglePlayServicesClient.OnConnectionFailedListener pp;
        
        public g(final GooglePlayServicesClient.OnConnectionFailedListener pp) {
            this.pp = pp;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof g) {
                return this.pp.equals(((g)o).pp);
            }
            return this.pp.equals(o);
        }
        
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            this.pp.onConnectionFailed(connectionResult);
        }
    }
    
    protected final class h extends b<Boolean>
    {
        public final Bundle pq;
        public final IBinder pr;
        public final int statusCode;
        
        public h(final int statusCode, final IBinder pr, final Bundle pq) {
            super(true);
            this.statusCode = statusCode;
            this.pr = pr;
            this.pq = pq;
        }
        
        @Override
        protected void aL() {
        }
        
        protected void b(final Boolean b) {
            if (b == null) {
                return;
            }
            switch (this.statusCode) {
                default: {
                    PendingIntent pendingIntent;
                    if (this.pq != null) {
                        pendingIntent = (PendingIntent)this.pq.getParcelable("pendingIntent");
                    }
                    else {
                        pendingIntent = null;
                    }
                    if (dw.this.pg != null) {
                        dy.s(dw.this.mContext).b(dw.this.am(), dw.this.pg);
                        dw.this.pg = null;
                    }
                    dw.this.pe = null;
                    dw.this.ne.a(new ConnectionResult(this.statusCode, pendingIntent));
                }
                case 0: {
                    try {
                        if (dw.this.an().equals(this.pr.getInterfaceDescriptor())) {
                            dw.this.pe = (T)dw.this.p(this.pr);
                            if (dw.this.pe != null) {
                                dw.this.ne.bT();
                                return;
                            }
                        }
                    }
                    catch (RemoteException ex) {}
                    dy.s(dw.this.mContext).b(dw.this.am(), dw.this.pg);
                    dw.this.pg = null;
                    dw.this.pe = null;
                    dw.this.ne.a(new ConnectionResult(8, null));
                }
                case 10: {
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                }
            }
        }
    }
}
