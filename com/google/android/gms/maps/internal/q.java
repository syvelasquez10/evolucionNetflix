// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Build$VERSION;
import com.google.android.gms.internal.eg;
import android.content.Context;

public class q
{
    private static Context Ci;
    private static c Cj;
    
    private static <T> T a(final ClassLoader classLoader, final String s) {
        try {
            return c(eg.f(classLoader).loadClass(s));
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
    
    private static boolean eB() {
        return false;
    }
    
    private static Class<?> eC() {
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
    
    private static Context getRemoteContext(final Context ci) {
        if (q.Ci == null) {
            if (eB()) {
                q.Ci = ci;
            }
            else {
                q.Ci = GooglePlayServicesUtil.getRemoteContext(ci);
            }
        }
        return q.Ci;
    }
    
    public static c u(final Context context) throws GooglePlayServicesNotAvailableException {
        eg.f(context);
        if (q.Cj != null) {
            return q.Cj;
        }
        v(context);
        q.Cj = w(context);
        try {
            q.Cj.a(com.google.android.gms.dynamic.c.h(getRemoteContext(context).getResources()), 4242000);
            return q.Cj;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static void v(final Context context) throws GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (googlePlayServicesAvailable) {
            default: {
                throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
            }
            case 0: {}
        }
    }
    
    private static c w(final Context context) {
        if (eB()) {
            Log.i(q.class.getSimpleName(), "Making Creator statically");
            return c(eC());
        }
        return c.a.Q(a(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }
}
