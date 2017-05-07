// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.HashSet;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.Message;
import android.content.ServiceConnection;
import android.content.Intent;
import android.os.Handler;
import android.content.Context;
import java.util.HashMap;
import android.os.Handler$Callback;

public final class fh implements Handler$Callback
{
    private static final Object Du;
    private static fh Dv;
    private final HashMap<String, a> Dw;
    private final Context lp;
    private final Handler mHandler;
    
    static {
        Du = new Object();
    }
    
    private fh(final Context context) {
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.Dw = new HashMap<String, a>();
        this.lp = context.getApplicationContext();
    }
    
    public static fh x(final Context context) {
        synchronized (fh.Du) {
            if (fh.Dv == null) {
                fh.Dv = new fh(context.getApplicationContext());
            }
            return fh.Dv;
        }
    }
    
    public boolean a(final String s, ff.f setPackage) {
        while (true) {
            while (true) {
                a a;
                synchronized (this.Dw) {
                    a = this.Dw.get(s);
                    if (a == null) {
                        a = new a(s);
                        a.a(setPackage);
                        setPackage = (ff.f)new Intent(s).setPackage("com.google.android.gms");
                        a.y(this.lp.bindService((Intent)setPackage, (ServiceConnection)a.eP(), 129));
                        this.Dw.put(s, a);
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
                        a.y(this.lp.bindService(new Intent(s2).setPackage("com.google.android.gms"), (ServiceConnection)a.eP(), 129));
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
    
    public void b(final String s, final ff.f f) {
        final a a;
        synchronized (this.Dw) {
            a = this.Dw.get(s);
            if (a == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + s);
            }
        }
        if (!a.c(f)) {
            final String s2;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + s2);
        }
        a.b(f);
        if (a.eR()) {
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
                synchronized (this.Dw) {
                    if (a.eR()) {
                        this.lp.unbindService((ServiceConnection)a.eP());
                        this.Dw.remove(a.eQ());
                    }
                    return true;
                }
                break;
            }
        }
    }
    
    final class a
    {
        private boolean DA;
        private IBinder DB;
        private ComponentName DC;
        private final String Dx;
        private final fh.a.a Dy;
        private final HashSet<ff.f> Dz;
        private int mState;
        
        public a(final String dx) {
            this.Dx = dx;
            this.Dy = new fh.a.a();
            this.Dz = new HashSet<ff.f>();
            this.mState = 0;
        }
        
        public void a(final ff.f f) {
            this.Dz.add(f);
        }
        
        public void b(final ff.f f) {
            this.Dz.remove(f);
        }
        
        public boolean c(final ff.f f) {
            return this.Dz.contains(f);
        }
        
        public fh.a.a eP() {
            return this.Dy;
        }
        
        public String eQ() {
            return this.Dx;
        }
        
        public boolean eR() {
            return this.Dz.isEmpty();
        }
        
        public IBinder getBinder() {
            return this.DB;
        }
        
        public ComponentName getComponentName() {
            return this.DC;
        }
        
        public int getState() {
            return this.mState;
        }
        
        public boolean isBound() {
            return this.DA;
        }
        
        public void y(final boolean da) {
            this.DA = da;
        }
        
        public class a implements ServiceConnection
        {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (fh.this.Dw) {
                    fh.a.this.DB = binder;
                    fh.a.this.DC = componentName;
                    final Iterator<ff.f> iterator = fh.a.this.Dz.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceConnected(componentName, binder);
                    }
                }
                fh.a.this.mState = 1;
            }
            // monitorexit(hashMap)
            
            public void onServiceDisconnected(final ComponentName componentName) {
                synchronized (fh.this.Dw) {
                    fh.a.this.DB = null;
                    fh.a.this.DC = componentName;
                    final Iterator<ff.f> iterator = fh.a.this.Dz.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceDisconnected(componentName);
                    }
                }
                fh.a.this.mState = 2;
            }
            // monitorexit(hashMap)
        }
    }
}
