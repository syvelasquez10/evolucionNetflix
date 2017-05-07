// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import com.google.android.gms.common.data.DataHolder;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.Message;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import android.os.IInterface;

public abstract class d<T extends IInterface> implements Api.a, com.google.android.gms.common.internal.e.b
{
    public static final String[] Lw;
    private final String[] Ds;
    private final Looper IB;
    private final com.google.android.gms.common.internal.e IQ;
    private T Lr;
    private final ArrayList<b<?>> Ls;
    private f Lt;
    private volatile int Lu;
    boolean Lv;
    private final Context mContext;
    final Handler mHandler;
    
    static {
        Lw = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected d(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String... ds) {
        this.Ls = new ArrayList<b<?>>();
        this.Lu = 1;
        this.Lv = false;
        this.mContext = n.i(context);
        this.IB = n.b(looper, "Looper must not be null");
        this.IQ = new com.google.android.gms.common.internal.e(context, looper, (com.google.android.gms.common.internal.e.b)this);
        this.mHandler = new a(looper);
        this.c(ds);
        this.Ds = ds;
        this.registerConnectionCallbacks(n.i(connectionCallbacks));
        this.registerConnectionFailedListener(n.i(onConnectionFailedListener));
    }
    
    protected d(final Context context, final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, final String... array) {
        this(context, context.getMainLooper(), new c(connectionCallbacks), new g(onConnectionFailedListener), array);
    }
    
    private void az(final int lu) {
        final int lu2 = this.Lu;
        this.Lu = lu;
        if (lu2 != lu) {
            if (lu == 3) {
                this.onConnected();
            }
            else if (lu2 == 3 && lu == 1) {
                this.onDisconnected();
            }
        }
    }
    
    protected final void N(final IBinder binder) {
        try {
            this.a(k.a.Q(binder), new e(this));
        }
        catch (RemoteException ex) {
            Log.w("GmsClient", "service died");
        }
    }
    
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)new h(n, binder, bundle)));
    }
    
    @Deprecated
    public final void a(final b<?> b) {
        synchronized (this.Ls) {
            this.Ls.add(b);
            // monitorexit(this.Ls)
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, (Object)b));
        }
    }
    
    protected abstract void a(final k p0, final e p1) throws RemoteException;
    
    public void aA(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)n));
    }
    
    protected void c(final String... array) {
    }
    
    @Override
    public void connect() {
        this.Lv = true;
        this.az(2);
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.az(1);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)googlePlayServicesAvailable));
        }
        else {
            if (this.Lt != null) {
                Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
                this.Lr = null;
                com.google.android.gms.common.internal.f.J(this.mContext).b(this.getStartServiceAction(), this.Lt);
            }
            this.Lt = new f();
            if (!com.google.android.gms.common.internal.f.J(this.mContext).a(this.getStartServiceAction(), this.Lt)) {
                Log.e("GmsClient", "unable to connect to service: " + this.getStartServiceAction());
                this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)9));
            }
        }
    }
    
    protected final void dK() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    @Override
    public void disconnect() {
        this.Lv = false;
        synchronized (this.Ls) {
            for (int size = this.Ls.size(), i = 0; i < size; ++i) {
                this.Ls.get(i).gV();
            }
            this.Ls.clear();
            // monitorexit(this.Ls)
            this.az(1);
            this.Lr = null;
            if (this.Lt != null) {
                com.google.android.gms.common.internal.f.J(this.mContext).b(this.getStartServiceAction(), this.Lt);
                this.Lt = null;
            }
        }
    }
    
    @Override
    public Bundle fD() {
        return null;
    }
    
    public final String[] gR() {
        return this.Ds;
    }
    
    public final T gS() {
        this.dK();
        return this.Lr;
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public final Looper getLooper() {
        return this.IB;
    }
    
    protected abstract String getServiceDescriptor();
    
    protected abstract String getStartServiceAction();
    
    @Override
    public boolean gr() {
        return this.Lv;
    }
    
    @Override
    public boolean isConnected() {
        return this.Lu == 3;
    }
    
    public boolean isConnecting() {
        return this.Lu == 2;
    }
    
    @Deprecated
    public boolean isConnectionCallbacksRegistered(final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
        return this.IQ.isConnectionCallbacksRegistered(new c(connectionCallbacks));
    }
    
    @Deprecated
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.IQ.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    protected abstract T j(final IBinder p0);
    
    protected void onConnected() {
    }
    
    protected void onDisconnected() {
    }
    
    @Deprecated
    public void registerConnectionCallbacks(final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
        this.IQ.registerConnectionCallbacks(new c(connectionCallbacks));
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.IQ.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    public void registerConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.IQ.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.IQ.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void unregisterConnectionCallbacks(final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
        this.IQ.unregisterConnectionCallbacks(new c(connectionCallbacks));
    }
    
    @Deprecated
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.IQ.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    final class a extends Handler
    {
        public a(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            if (message.what == 1 && !d.this.isConnecting()) {
                final b b = (b)message.obj;
                b.gT();
                b.unregister();
                return;
            }
            if (message.what == 3) {
                d.this.IQ.b(new ConnectionResult((int)message.obj, null));
                return;
            }
            if (message.what == 4) {
                d.this.az(1);
                d.this.Lr = null;
                d.this.IQ.aB((int)message.obj);
                return;
            }
            if (message.what == 2 && !d.this.isConnected()) {
                final b b2 = (b)message.obj;
                b2.gT();
                b2.unregister();
                return;
            }
            if (message.what == 2 || message.what == 1) {
                ((b)message.obj).gU();
                return;
            }
            Log.wtf("GmsClient", "Don't know how to handle this message.");
        }
    }
    
    protected abstract class b<TListener>
    {
        private boolean Ly;
        private TListener mListener;
        
        public b(final TListener mListener) {
            this.mListener = mListener;
            this.Ly = false;
        }
        
        protected abstract void g(final TListener p0);
        
        protected abstract void gT();
        
        public void gU() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/common/internal/d$b.mListener:Ljava/lang/Object;
            //     6: astore_1       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/common/internal/d$b.Ly:Z
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
            //    52: invokevirtual   com/google/android/gms/common/internal/d$b.g:(Ljava/lang/Object;)V
            //    55: aload_0        
            //    56: monitorenter   
            //    57: aload_0        
            //    58: iconst_1       
            //    59: putfield        com/google/android/gms/common/internal/d$b.Ly:Z
            //    62: aload_0        
            //    63: monitorexit    
            //    64: aload_0        
            //    65: invokevirtual   com/google/android/gms/common/internal/d$b.unregister:()V
            //    68: return         
            //    69: astore_1       
            //    70: aload_0        
            //    71: monitorexit    
            //    72: aload_1        
            //    73: athrow         
            //    74: astore_1       
            //    75: aload_0        
            //    76: invokevirtual   com/google/android/gms/common/internal/d$b.gT:()V
            //    79: aload_1        
            //    80: athrow         
            //    81: aload_0        
            //    82: invokevirtual   com/google/android/gms/common/internal/d$b.gT:()V
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
        
        public void gV() {
            synchronized (this) {
                this.mListener = null;
            }
        }
        
        public void unregister() {
            this.gV();
            synchronized (d.this.Ls) {
                d.this.Ls.remove(this);
            }
        }
    }
    
    public static final class c implements ConnectionCallbacks
    {
        private final GooglePlayServicesClient.ConnectionCallbacks Lz;
        
        public c(final GooglePlayServicesClient.ConnectionCallbacks lz) {
            this.Lz = lz;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof c) {
                return this.Lz.equals(((c)o).Lz);
            }
            return this.Lz.equals(o);
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            this.Lz.onConnected(bundle);
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
            this.Lz.onDisconnected();
        }
    }
    
    public abstract class d<TListener> extends b<TListener>
    {
        private final DataHolder IC;
        
        public d(final TListener tListener, final DataHolder ic) {
            super(tListener);
            this.IC = ic;
        }
        
        protected abstract void a(final TListener p0, final DataHolder p1);
        
        @Override
        protected final void g(final TListener tListener) {
            this.a(tListener, this.IC);
        }
        
        @Override
        protected void gT() {
            if (this.IC != null) {
                this.IC.close();
            }
        }
    }
    
    public static final class e extends j.a
    {
        private d LA;
        
        public e(final d la) {
            this.LA = la;
        }
        
        public void b(final int n, final IBinder binder, final Bundle bundle) {
            n.b("onPostInitComplete can be called only once per call to getServiceFromBroker", (Object)this.LA);
            this.LA.a(n, binder, bundle);
            this.LA = null;
        }
    }
    
    final class f implements ServiceConnection
    {
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            d.this.N(binder);
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            d.this.mHandler.sendMessage(d.this.mHandler.obtainMessage(4, (Object)1));
        }
    }
    
    public static final class g implements OnConnectionFailedListener
    {
        private final GooglePlayServicesClient.OnConnectionFailedListener LB;
        
        public g(final GooglePlayServicesClient.OnConnectionFailedListener lb) {
            this.LB = lb;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof g) {
                return this.LB.equals(((g)o).LB);
            }
            return this.LB.equals(o);
        }
        
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            this.LB.onConnectionFailed(connectionResult);
        }
    }
    
    protected final class h extends b<Boolean>
    {
        public final Bundle LC;
        public final IBinder LD;
        public final int statusCode;
        
        public h(final int statusCode, final IBinder ld, final Bundle lc) {
            super(true);
            this.statusCode = statusCode;
            this.LD = ld;
            this.LC = lc;
        }
        
        protected void b(final Boolean b) {
            if (b == null) {
                d.this.az(1);
                return;
            }
            switch (this.statusCode) {
                default: {
                    PendingIntent pendingIntent;
                    if (this.LC != null) {
                        pendingIntent = (PendingIntent)this.LC.getParcelable("pendingIntent");
                    }
                    else {
                        pendingIntent = null;
                    }
                    if (d.this.Lt != null) {
                        com.google.android.gms.common.internal.f.J(d.this.mContext).b(d.this.getStartServiceAction(), d.this.Lt);
                        d.this.Lt = null;
                    }
                    d.this.az(1);
                    d.this.Lr = null;
                    d.this.IQ.b(new ConnectionResult(this.statusCode, pendingIntent));
                }
                case 0: {
                    try {
                        if (d.this.getServiceDescriptor().equals(this.LD.getInterfaceDescriptor())) {
                            d.this.Lr = (T)d.this.j(this.LD);
                            if (d.this.Lr != null) {
                                d.this.az(3);
                                d.this.IQ.dM();
                                return;
                            }
                        }
                    }
                    catch (RemoteException ex) {}
                    com.google.android.gms.common.internal.f.J(d.this.mContext).b(d.this.getStartServiceAction(), d.this.Lt);
                    d.this.Lt = null;
                    d.this.az(1);
                    d.this.Lr = null;
                    d.this.IQ.b(new ConnectionResult(8, null));
                }
                case 10: {
                    d.this.az(1);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                }
            }
        }
        
        @Override
        protected void gT() {
        }
    }
}
