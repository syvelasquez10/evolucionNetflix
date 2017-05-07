// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

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
    private final HashMap<String, f$a> LM;
    private final Context mD;
    private final Handler mHandler;
    
    static {
        LK = new Object();
    }
    
    private f(final Context context) {
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.LM = new HashMap<String, f$a>();
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
    
    public boolean a(final String s, d$f setPackage) {
        while (true) {
            while (true) {
                f$a f$a;
                synchronized (this.LM) {
                    f$a = this.LM.get(s);
                    if (f$a == null) {
                        f$a = new f$a(this, s);
                        f$a.a(setPackage);
                        setPackage = (d$f)new Intent(s).setPackage("com.google.android.gms");
                        f$a.J(this.mD.bindService((Intent)setPackage, (ServiceConnection)f$a.gW(), 129));
                        this.LM.put(s, f$a);
                        final f$a f$a2 = f$a;
                        return f$a2.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)f$a);
                    if (f$a.c(setPackage)) {
                        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + s);
                    }
                }
                f$a.a(setPackage);
                switch (f$a.getState()) {
                    case 1: {
                        setPackage.onServiceConnected(f$a.getComponentName(), f$a.getBinder());
                        final f$a f$a2 = f$a;
                        continue;
                    }
                    case 2: {
                        final String s2;
                        f$a.J(this.mD.bindService(new Intent(s2).setPackage("com.google.android.gms"), (ServiceConnection)f$a.gW(), 129));
                        final f$a f$a2 = f$a;
                        continue;
                    }
                    default: {
                        final f$a f$a2 = f$a;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    public void b(final String s, final d$f d$f) {
        final f$a f$a;
        synchronized (this.LM) {
            f$a = this.LM.get(s);
            if (f$a == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + s);
            }
        }
        if (!f$a.c(d$f)) {
            final String s2;
            throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + s2);
        }
        f$a.b(d$f);
        if (f$a.gY()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)f$a), 5000L);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                final f$a f$a = (f$a)message.obj;
                synchronized (this.LM) {
                    if (f$a.gY()) {
                        this.mD.unbindService((ServiceConnection)f$a.gW());
                        this.LM.remove(f$a.gX());
                    }
                    return true;
                }
                break;
            }
        }
    }
}
