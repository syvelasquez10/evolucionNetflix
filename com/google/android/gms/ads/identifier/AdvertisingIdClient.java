// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.p;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import android.content.ServiceConnection;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.a;
import android.content.Context;

public final class AdvertisingIdClient
{
    private static a g(final Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            final Context context2 = context;
            GooglePlayServicesUtil.m(context2);
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
            GooglePlayServicesUtil.m(context2);
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
    
    public static Info getAdvertisingIdInfo(final Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        eg.O("Calling this from your main thread can lead to deadlock");
        final a g = g(context);
        try {
            final p b = p.a.b(g.bg());
            return new Info(b.getId(), b.a(true));
        }
        catch (RemoteException ex) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex);
            throw new IOException("Remote exception");
        }
        catch (InterruptedException ex2) {
            throw new IOException("Interrupted exception");
        }
        finally {
            context.unbindService((ServiceConnection)g);
        }
    }
    
    public static final class Info
    {
        private final String eb;
        private final boolean ec;
        
        Info(final String eb, final boolean ec) {
            this.eb = eb;
            this.ec = ec;
        }
        
        public String getId() {
            return this.eb;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.ec;
        }
    }
}
