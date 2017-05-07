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
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.os.IInterface;

public abstract class ff<T extends IInterface> implements GooglePlayServicesClient, Api.a, fg.b
{
    public static final String[] Dg;
    private final Looper AS;
    private final fg Bc;
    private T Da;
    private final ArrayList<b<?>> Db;
    private f Dc;
    private volatile int Dd;
    private final String[] De;
    boolean Df;
    private final Context mContext;
    final Handler mHandler;
    
    static {
        Dg = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected ff(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String... de) {
        this.Db = new ArrayList<b<?>>();
        this.Dd = 1;
        this.Df = false;
        this.mContext = fq.f(context);
        this.AS = fq.b(looper, "Looper must not be null");
        this.Bc = new fg(context, looper, (fg.b)this);
        this.mHandler = new a(looper);
        this.b(de);
        this.De = de;
        this.registerConnectionCallbacks(fq.f(connectionCallbacks));
        this.registerConnectionFailedListener(fq.f(onConnectionFailedListener));
    }
    
    protected ff(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String... array) {
        this(context, context.getMainLooper(), new c(connectionCallbacks), new g(onConnectionFailedListener), array);
    }
    
    private void M(final int dd) {
        final int dd2 = this.Dd;
        this.Dd = dd;
        if (dd2 != dd) {
            if (dd == 3) {
                this.onConnected();
            }
            else if (dd2 == 3 && dd == 1) {
                this.onDisconnected();
            }
        }
    }
    
    public void N(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)n));
    }
    
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)new h(n, binder, bundle)));
    }
    
    public final void a(final b<?> b) {
        synchronized (this.Db) {
            this.Db.add(b);
            // monitorexit(this.Db)
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, (Object)b));
        }
    }
    
    protected abstract void a(final fm p0, final e p1) throws RemoteException;
    
    protected void b(final String... array) {
    }
    
    protected final void bT() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    protected abstract String bg();
    
    protected abstract String bh();
    
    @Override
    public void connect() {
        this.Df = true;
        this.M(2);
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.M(1);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)googlePlayServicesAvailable));
        }
        else {
            if (this.Dc != null) {
                Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
                this.Da = null;
                fh.x(this.mContext).b(this.bg(), this.Dc);
            }
            this.Dc = new f();
            if (!fh.x(this.mContext).a(this.bg(), this.Dc)) {
                Log.e("GmsClient", "unable to connect to service: " + this.bg());
                this.mHandler.sendMessage(this.mHandler.obtainMessage(3, (Object)9));
            }
        }
    }
    
    @Override
    public Bundle dG() {
        return null;
    }
    
    @Override
    public void disconnect() {
        this.Df = false;
        synchronized (this.Db) {
            for (int size = this.Db.size(), i = 0; i < size; ++i) {
                this.Db.get(i).eO();
            }
            this.Db.clear();
            // monitorexit(this.Db)
            this.M(1);
            this.Da = null;
            if (this.Dc != null) {
                fh.x(this.mContext).b(this.bg(), this.Dc);
                this.Dc = null;
            }
        }
    }
    
    public final String[] eL() {
        return this.De;
    }
    
    protected final T eM() {
        this.bT();
        return this.Da;
    }
    
    @Override
    public boolean em() {
        return this.Df;
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public final Looper getLooper() {
        return this.AS;
    }
    
    @Override
    public boolean isConnected() {
        return this.Dd == 3;
    }
    
    @Override
    public boolean isConnecting() {
        return this.Dd == 2;
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.Bc.isConnectionCallbacksRegistered(new c(connectionCallbacks));
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.Bc.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    protected void onConnected() {
    }
    
    protected void onDisconnected() {
    }
    
    protected abstract T r(final IBinder p0);
    
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Bc.registerConnectionCallbacks(new c(connectionCallbacks));
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.Bc.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Bc.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.Bc.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Bc.unregisterConnectionCallbacks(new c(connectionCallbacks));
    }
    
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Bc.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    protected final void z(final IBinder binder) {
        try {
            this.a(fm.a.C(binder), new e(this));
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
            if (message.what == 1 && !ff.this.isConnecting()) {
                final b b = (b)message.obj;
                b.dx();
                b.unregister();
                return;
            }
            if (message.what == 3) {
                ff.this.Bc.a(new ConnectionResult((int)message.obj, null));
                return;
            }
            if (message.what == 4) {
                ff.this.M(1);
                ff.this.Da = null;
                ff.this.Bc.O((int)message.obj);
                return;
            }
            if (message.what == 2 && !ff.this.isConnected()) {
                final b b2 = (b)message.obj;
                b2.dx();
                b2.unregister();
                return;
            }
            if (message.what == 2 || message.what == 1) {
                ((b)message.obj).eN();
                return;
            }
            Log.wtf("GmsClient", "Don't know how to handle this message.");
        }
    }
    
    protected abstract class b<TListener>
    {
        private boolean Di;
        private TListener mListener;
        
        public b(final TListener mListener) {
            this.mListener = mListener;
            this.Di = false;
        }
        
        protected abstract void a(final TListener p0);
        
        protected abstract void dx();
        
        public void eN() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/internal/ff$b.mListener:Ljava/lang/Object;
            //     6: astore_1       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/internal/ff$b.Di:Z
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
            //    52: invokevirtual   com/google/android/gms/internal/ff$b.a:(Ljava/lang/Object;)V
            //    55: aload_0        
            //    56: monitorenter   
            //    57: aload_0        
            //    58: iconst_1       
            //    59: putfield        com/google/android/gms/internal/ff$b.Di:Z
            //    62: aload_0        
            //    63: monitorexit    
            //    64: aload_0        
            //    65: invokevirtual   com/google/android/gms/internal/ff$b.unregister:()V
            //    68: return         
            //    69: astore_1       
            //    70: aload_0        
            //    71: monitorexit    
            //    72: aload_1        
            //    73: athrow         
            //    74: astore_1       
            //    75: aload_0        
            //    76: invokevirtual   com/google/android/gms/internal/ff$b.dx:()V
            //    79: aload_1        
            //    80: athrow         
            //    81: aload_0        
            //    82: invokevirtual   com/google/android/gms/internal/ff$b.dx:()V
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
        
        public void eO() {
            synchronized (this) {
                this.mListener = null;
            }
        }
        
        public void unregister() {
            this.eO();
            synchronized (ff.this.Db) {
                ff.this.Db.remove(this);
            }
        }
    }
    
    public static final class c implements GoogleApiClient.ConnectionCallbacks
    {
        private final GooglePlayServicesClient.ConnectionCallbacks Dj;
        
        public c(final GooglePlayServicesClient.ConnectionCallbacks dj) {
            this.Dj = dj;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof c) {
                return this.Dj.equals(((c)o).Dj);
            }
            return this.Dj.equals(o);
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            this.Dj.onConnected(bundle);
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
            this.Dj.onDisconnected();
        }
    }
    
    public abstract class d<TListener> extends b<TListener>
    {
        private final DataHolder BB;
        
        public d(final TListener tListener, final DataHolder bb) {
            super(tListener);
            this.BB = bb;
        }
        
        @Override
        protected final void a(final TListener tListener) {
            this.a(tListener, this.BB);
        }
        
        protected abstract void a(final TListener p0, final DataHolder p1);
        
        @Override
        protected void dx() {
            if (this.BB != null) {
                this.BB.close();
            }
        }
    }
    
    public static final class e extends fl.a
    {
        private ff Dk;
        
        public e(final ff dk) {
            this.Dk = dk;
        }
        
        public void b(final int n, final IBinder binder, final Bundle bundle) {
            fq.b("onPostInitComplete can be called only once per call to getServiceFromBroker", (Object)this.Dk);
            this.Dk.a(n, binder, bundle);
            this.Dk = null;
        }
    }
    
    final class f implements ServiceConnection
    {
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            ff.this.z(binder);
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            ff.this.mHandler.sendMessage(ff.this.mHandler.obtainMessage(4, (Object)1));
        }
    }
    
    public static final class g implements GoogleApiClient.OnConnectionFailedListener
    {
        private final GooglePlayServicesClient.OnConnectionFailedListener Dl;
        
        public g(final GooglePlayServicesClient.OnConnectionFailedListener dl) {
            this.Dl = dl;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof g) {
                return this.Dl.equals(((g)o).Dl);
            }
            return this.Dl.equals(o);
        }
        
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            this.Dl.onConnectionFailed(connectionResult);
        }
    }
    
    protected final class h extends b<Boolean>
    {
        public final Bundle Dm;
        public final IBinder Dn;
        public final int statusCode;
        
        public h(final int statusCode, final IBinder dn, final Bundle dm) {
            super(true);
            this.statusCode = statusCode;
            this.Dn = dn;
            this.Dm = dm;
        }
        
        protected void b(final Boolean b) {
            if (b == null) {
                ff.this.M(1);
                return;
            }
            switch (this.statusCode) {
                default: {
                    PendingIntent pendingIntent;
                    if (this.Dm != null) {
                        pendingIntent = (PendingIntent)this.Dm.getParcelable("pendingIntent");
                    }
                    else {
                        pendingIntent = null;
                    }
                    if (ff.this.Dc != null) {
                        fh.x(ff.this.mContext).b(ff.this.bg(), ff.this.Dc);
                        ff.this.Dc = null;
                    }
                    ff.this.M(1);
                    ff.this.Da = null;
                    ff.this.Bc.a(new ConnectionResult(this.statusCode, pendingIntent));
                }
                case 0: {
                    try {
                        if (ff.this.bh().equals(this.Dn.getInterfaceDescriptor())) {
                            ff.this.Da = (T)ff.this.r(this.Dn);
                            if (ff.this.Da != null) {
                                ff.this.M(3);
                                ff.this.Bc.bV();
                                return;
                            }
                        }
                    }
                    catch (RemoteException ex) {}
                    fh.x(ff.this.mContext).b(ff.this.bg(), ff.this.Dc);
                    ff.this.Dc = null;
                    ff.this.M(1);
                    ff.this.Da = null;
                    ff.this.Bc.a(new ConnectionResult(8, null));
                }
                case 10: {
                    ff.this.M(1);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                }
            }
        }
        
        @Override
        protected void dx() {
        }
    }
}
