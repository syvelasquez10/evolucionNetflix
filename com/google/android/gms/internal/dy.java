// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.content.ComponentName;
import android.os.IBinder;
import java.util.HashSet;
import android.os.Message;
import android.content.ServiceConnection;
import android.content.Intent;
import java.util.HashMap;
import android.os.Handler;
import android.content.Context;
import android.os.Handler$Callback;

public final class dy implements Handler$Callback
{
    private static dy pA;
    private static final Object pz;
    private final Context iT;
    private final Handler mHandler;
    private final HashMap<String, a> pB;
    
    static {
        pz = new Object();
    }
    
    private dy(final Context context) {
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.pB = new HashMap<String, a>();
        this.iT = context.getApplicationContext();
    }
    
    public static dy s(final Context context) {
        synchronized (dy.pz) {
            if (dy.pA == null) {
                dy.pA = new dy(context.getApplicationContext());
            }
            return dy.pA;
        }
    }
    
    public boolean a(final String s, dw.f setPackage) {
        while (true) {
            while (true) {
                a a;
                synchronized (this.pB) {
                    a = this.pB.get(s);
                    if (a == null) {
                        a = new a(s);
                        a.a(setPackage);
                        setPackage = (dw.f)new Intent(s).setPackage("com.google.android.gms");
                        a.q(this.iT.bindService((Intent)setPackage, (ServiceConnection)a.bU(), 129));
                        this.pB.put(s, a);
                        final a a2 = a;
                        return a2.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)a);
                    if (a.c(setPackage)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + s);
                    }
                }
                a.a(setPackage);
                switch (a.getState()) {
                    case 1: {
                        setPackage.onServiceConnected(a.getComponentName(), a.getBinder());
                        final a a2 = a;
                        continue;
                    }
                    case 2: {
                        final String s2;
                        a.q(this.iT.bindService(new Intent(s2).setPackage("com.google.android.gms"), (ServiceConnection)a.bU(), 129));
                        final a a2 = a;
                        continue;
                    }
                    default: {
                        final a a2 = a;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    public void b(final String s, final dw.f f) {
        final a a;
        synchronized (this.pB) {
            a = this.pB.get(s);
            if (a == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + s);
            }
        }
        if (!a.c(f)) {
            final String s2;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + s2);
        }
        a.b(f);
        if (a.bW()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)a), 5000L);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                final a a = (a)message.obj;
                synchronized (this.pB) {
                    if (a.bW()) {
                        this.iT.unbindService((ServiceConnection)a.bU());
                        this.pB.remove(a.bV());
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    final class a
    {
        private int mState;
        private final String pC;
        private final dy.a.a pD;
        private final HashSet<dw.f> pE;
        private boolean pF;
        private IBinder pG;
        private ComponentName pH;
        
        public a(final String pc) {
            this.pC = pc;
            this.pD = new dy.a.a();
            this.pE = new HashSet<dw.f>();
            this.mState = 0;
        }
        
        public void a(final dw.f f) {
            this.pE.add(f);
        }
        
        public void b(final dw.f f) {
            this.pE.remove(f);
        }
        
        public dy.a.a bU() {
            return this.pD;
        }
        
        public String bV() {
            return this.pC;
        }
        
        public boolean bW() {
            return this.pE.isEmpty();
        }
        
        public boolean c(final dw.f f) {
            return this.pE.contains(f);
        }
        
        public IBinder getBinder() {
            return this.pG;
        }
        
        public ComponentName getComponentName() {
            return this.pH;
        }
        
        public int getState() {
            return this.mState;
        }
        
        public boolean isBound() {
            return this.pF;
        }
        
        public void q(final boolean pf) {
            this.pF = pf;
        }
        
        public class a implements ServiceConnection
        {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (dy.this.pB) {
                    dy.a.this.pG = binder;
                    dy.a.this.pH = componentName;
                    final Iterator<dw.f> iterator = dy.a.this.pE.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceConnected(componentName, binder);
                    }
                }
                dy.a.this.mState = 1;
            }
            // monitorexit(hashMap)
            
            public void onServiceDisconnected(final ComponentName componentName) {
                synchronized (dy.this.pB) {
                    dy.a.this.pG = null;
                    dy.a.this.pH = componentName;
                    final Iterator<dw.f> iterator = dy.a.this.pE.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceDisconnected(componentName);
                    }
                }
                dy.a.this.mState = 2;
            }
            // monitorexit(hashMap)
        }
    }
}
