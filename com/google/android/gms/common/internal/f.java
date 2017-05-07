// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import android.content.ComponentName;
import android.os.IBinder;
import java.util.HashSet;
import android.os.Message;
import android.content.ServiceConnection;
import android.content.Intent;
import android.os.Handler;
import android.content.Context;
import java.util.HashMap;
import android.os.Handler$Callback;

public final class f implements Handler$Callback
{
    private static final Object LK;
    private static f LL;
    private final HashMap<String, a> LM;
    private final Context mD;
    private final Handler mHandler;
    
    static {
        LK = new Object();
    }
    
    private f(final Context context) {
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.LM = new HashMap<String, a>();
        this.mD = context.getApplicationContext();
    }
    
    public static f J(final Context context) {
        synchronized (f.LK) {
            if (f.LL == null) {
                f.LL = new f(context.getApplicationContext());
            }
            return f.LL;
        }
    }
    
    public boolean a(final String s, d.f setPackage) {
        while (true) {
            while (true) {
                a a;
                synchronized (this.LM) {
                    a = this.LM.get(s);
                    if (a == null) {
                        a = new a(s);
                        a.a(setPackage);
                        setPackage = (d.f)new Intent(s).setPackage("com.google.android.gms");
                        a.J(this.mD.bindService((Intent)setPackage, (ServiceConnection)a.gW(), 129));
                        this.LM.put(s, a);
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
                        a.J(this.mD.bindService(new Intent(s2).setPackage("com.google.android.gms"), (ServiceConnection)a.gW(), 129));
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
    
    public void b(final String s, final d.f f) {
        final a a;
        synchronized (this.LM) {
            a = this.LM.get(s);
            if (a == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + s);
            }
        }
        if (!a.c(f)) {
            final String s2;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + s2);
        }
        a.b(f);
        if (a.gY()) {
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
                synchronized (this.LM) {
                    if (a.gY()) {
                        this.mD.unbindService((ServiceConnection)a.gW());
                        this.LM.remove(a.gX());
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    final class a
    {
        private final String LN;
        private final f.a.a LO;
        private final HashSet<d.f> LP;
        private boolean LQ;
        private IBinder LR;
        private ComponentName LS;
        private int mState;
        
        public a(final String ln) {
            this.LN = ln;
            this.LO = new f.a.a();
            this.LP = new HashSet<d.f>();
            this.mState = 0;
        }
        
        public void J(final boolean lq) {
            this.LQ = lq;
        }
        
        public void a(final d.f f) {
            this.LP.add(f);
        }
        
        public void b(final d.f f) {
            this.LP.remove(f);
        }
        
        public boolean c(final d.f f) {
            return this.LP.contains(f);
        }
        
        public f.a.a gW() {
            return this.LO;
        }
        
        public String gX() {
            return this.LN;
        }
        
        public boolean gY() {
            return this.LP.isEmpty();
        }
        
        public IBinder getBinder() {
            return this.LR;
        }
        
        public ComponentName getComponentName() {
            return this.LS;
        }
        
        public int getState() {
            return this.mState;
        }
        
        public boolean isBound() {
            return this.LQ;
        }
        
        public class a implements ServiceConnection
        {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (f.this.LM) {
                    f.a.this.LR = binder;
                    f.a.this.LS = componentName;
                    final Iterator<d.f> iterator = f.a.this.LP.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceConnected(componentName, binder);
                    }
                }
                f.a.this.mState = 1;
            }
            // monitorexit(hashMap)
            
            public void onServiceDisconnected(final ComponentName componentName) {
                synchronized (f.this.LM) {
                    f.a.this.LR = null;
                    f.a.this.LS = componentName;
                    final Iterator<d.f> iterator = f.a.this.LP.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceDisconnected(componentName);
                    }
                }
                f.a.this.mState = 2;
            }
            // monitorexit(hashMap)
        }
    }
}
