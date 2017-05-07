// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import android.os.RemoteException;
import android.util.Log;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.ServiceConnection;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import com.google.android.gms.internal.s$a;
import com.google.android.gms.common.internal.n;
import android.content.Context;
import com.google.android.gms.internal.s;
import com.google.android.gms.common.a;

public final class AdvertisingIdClient
{
    a lk;
    s ll;
    boolean lm;
    final Context mContext;
    
    public AdvertisingIdClient(final Context mContext) {
        n.i(mContext);
        this.mContext = mContext;
        this.lm = false;
    }
    
    static s a(final Context context, final a a) {
        try {
            return s$a.b(a.fX());
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
    }
    
    public static AdvertisingIdClient$Info getAdvertisingIdInfo(Context context) {
        context = (Context)new AdvertisingIdClient(context);
        try {
            ((AdvertisingIdClient)context).start();
            return ((AdvertisingIdClient)context).W();
        }
        finally {
            ((AdvertisingIdClient)context).finish();
        }
    }
    
    static a i(final Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            final Context context2 = context;
            GooglePlayServicesUtil.D(context2);
            final a a = new a();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = a;
            final int n = 1;
            final boolean b = context3.bindService(intent4, (ServiceConnection)o, n);
            if (b) {
                return a;
            }
            throw new IOException("Connection failure");
        }
        catch (PackageManager$NameNotFoundException ex2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        try {
            final Context context2 = context;
            GooglePlayServicesUtil.D(context2);
            final a a = new a();
            final String s = "com.google.android.gms.ads.identifier.service.START";
            final Intent intent = new Intent(s);
            final Intent intent3;
            final Intent intent2 = intent3 = intent;
            final String s2 = "com.google.android.gms";
            intent3.setPackage(s2);
            final Context context3 = context;
            final Intent intent4 = intent2;
            final Object o = a;
            final int n = 1;
            final boolean b = context3.bindService(intent4, (ServiceConnection)o, n);
            if (b) {
                return a;
            }
        }
        catch (GooglePlayServicesNotAvailableException ex) {
            throw new IOException(ex);
        }
        throw new IOException("Connection failure");
    }
    
    public AdvertisingIdClient$Info W() {
        n.aU("Calling this from your main thread can lead to deadlock");
        n.i(this.lk);
        n.i(this.ll);
        if (!this.lm) {
            throw new IOException("AdvertisingIdService is not connected.");
        }
        try {
            return new AdvertisingIdClient$Info(this.ll.getId(), this.ll.a(true));
        }
        catch (RemoteException ex) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex);
            throw new IOException("Remote exception");
        }
    }
    
    public void finish() {
        n.aU("Calling this from your main thread can lead to deadlock");
        if (this.mContext == null || this.lk == null) {
            return;
        }
        while (true) {
            try {
                if (this.lm) {
                    this.mContext.unbindService((ServiceConnection)this.lk);
                }
                this.lm = false;
                this.ll = null;
                this.lk = null;
            }
            catch (IllegalArgumentException ex) {
                Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void start() {
        n.aU("Calling this from your main thread can lead to deadlock");
        if (this.lm) {
            this.finish();
        }
        this.lk = i(this.mContext);
        this.ll = a(this.mContext, this.lk);
        this.lm = true;
    }
}
