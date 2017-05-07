// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.internal.n;
import android.content.Context;

public class u
{
    private static Context ajm;
    private static c ajn;
    
    public static c R(final Context context) {
        n.i(context);
        if (u.ajn != null) {
            return u.ajn;
        }
        S(context);
        u.ajn = T(context);
        try {
            u.ajn.a(e.k(getRemoteContext(context).getResources()), 6111000);
            return u.ajn;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static void S(final Context context) {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (googlePlayServicesAvailable) {
            default: {
                throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
            }
            case 0: {}
        }
    }
    
    private static c T(final Context context) {
        if (mI()) {
            Log.i(u.class.getSimpleName(), "Making Creator statically");
            return c(mJ());
        }
        Log.i(u.class.getSimpleName(), "Making Creator dynamically");
        return c$a.aP(a(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }
    
    private static <T> T a(final ClassLoader classLoader, final String s) {
        try {
            return c(n.i(classLoader).loadClass(s));
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Unable to find dynamic class " + s);
        }
    }
    
    private static <T> T c(final Class<?> clazz) {
        try {
            return (T)clazz.newInstance();
        }
        catch (InstantiationException ex) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + clazz.getName());
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalStateException("Unable to call the default constructor of " + clazz.getName());
        }
    }
    
    private static Context getRemoteContext(final Context context) {
        if (u.ajm == null) {
            if (mI()) {
                u.ajm = context.getApplicationContext();
            }
            else {
                u.ajm = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return u.ajm;
    }
    
    private static boolean mI() {
        return false;
    }
    
    private static Class<?> mJ() {
        try {
            if (Build$VERSION.SDK_INT < 15) {
                return Class.forName("com.google.android.gms.maps.internal.CreatorImplGmm6");
            }
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
