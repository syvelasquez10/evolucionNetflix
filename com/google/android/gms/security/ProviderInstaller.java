// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.security;

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
    
    private static void U(final Context context) {
        ProviderInstaller.anz = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
    }
    
    public static void installIfNeeded(Context uf) {
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
    
    public static void installIfNeededAsync(final Context context, final ProviderInstaller$ProviderInstallListener providerInstaller$ProviderInstallListener) {
        n.b(context, "Context must not be null");
        n.b(providerInstaller$ProviderInstallListener, "Listener must not be null");
        n.aT("Must be called on the UI thread");
        new ProviderInstaller$1(context, providerInstaller$ProviderInstallListener).execute((Object[])new Void[0]);
    }
}
