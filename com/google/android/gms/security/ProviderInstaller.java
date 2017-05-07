// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.security;

import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.n;
import android.content.Context;
import java.lang.reflect.Method;

public class ProviderInstaller
{
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static Method anz;
    private static final Object uf;
    
    static {
        uf = new Object();
        ProviderInstaller.anz = null;
    }
    
    private static void U(final Context context) throws ClassNotFoundException, NoSuchMethodException {
        ProviderInstaller.anz = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
    }
    
    public static void installIfNeeded(Context uf) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        n.b(uf, "Context must not be null");
        GooglePlayServicesUtil.D(uf);
        final Context remoteContext = GooglePlayServicesUtil.getRemoteContext(uf);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        uf = (Context)ProviderInstaller.uf;
        // monitorenter(uf)
        try {
            if (ProviderInstaller.anz == null) {
                U(remoteContext);
            }
            ProviderInstaller.anz.invoke(null, remoteContext);
        }
        catch (Exception ex) {
            Log.e("ProviderInstaller", "Failed to install provider: " + ex.getMessage());
            throw new GooglePlayServicesNotAvailableException(8);
            try {}
            finally {
            }
            // monitorexit(uf)
        }
    }
    
    public static void installIfNeededAsync(final Context context, final ProviderInstallListener providerInstallListener) {
        n.b(context, "Context must not be null");
        n.b(providerInstallListener, "Listener must not be null");
        n.aT("Must be called on the UI thread");
        new AsyncTask<Void, Void, Integer>() {
            protected Integer b(final Void... array) {
                try {
                    ProviderInstaller.installIfNeeded(context);
                    return 0;
                }
                catch (GooglePlayServicesRepairableException ex) {
                    return ex.getConnectionStatusCode();
                }
                catch (GooglePlayServicesNotAvailableException ex2) {
                    return ex2.errorCode;
                }
            }
            
            protected void d(final Integer n) {
                if (n == 0) {
                    providerInstallListener.onProviderInstalled();
                    return;
                }
                providerInstallListener.onProviderInstallFailed(n, GooglePlayServicesUtil.ai(n));
            }
        }.execute((Object[])new Void[0]);
    }
    
    public interface ProviderInstallListener
    {
        void onProviderInstallFailed(final int p0, final Intent p1);
        
        void onProviderInstalled();
    }
}
