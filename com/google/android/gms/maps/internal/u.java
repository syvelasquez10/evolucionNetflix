// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.fq;
import android.content.Context;

public class u
{
    private static c SA;
    private static Context Sz;
    
    public static c A(final Context context) throws GooglePlayServicesNotAvailableException {
        fq.f(context);
        if (u.SA != null) {
            return u.SA;
        }
        B(context);
        u.SA = C(context);
        try {
            u.SA.a(e.h(getRemoteContext(context).getResources()), 4452000);
            return u.SA;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static void B(final Context context) throws GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (googlePlayServicesAvailable) {
            default: {
                throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
            }
            case 0: {}
        }
    }
    
    private static c C(final Context context) {
        if (iz()) {
            Log.i(u.class.getSimpleName(), "Making Creator statically");
            return c(iA());
        }
        Log.i(u.class.getSimpleName(), "Making Creator dynamically");
        return c.a.ab(a(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }
    
    private static <T> T a(final ClassLoader classLoader, final String s) {
        try {
            return c(fq.f(classLoader).loadClass(s));
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
        if (u.Sz == null) {
            if (iz()) {
                u.Sz = context.getApplicationContext();
            }
            else {
                u.Sz = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return u.Sz;
    }
    
    private static Class<?> iA() {
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
    
    private static boolean iz() {
        return false;
    }
}
