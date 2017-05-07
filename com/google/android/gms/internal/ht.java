// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.plus.PlusOneDummyView;
import android.view.View;
import android.content.Context;

public final class ht
{
    private static Context Ci;
    private static hp DQ;
    
    public static View a(final Context context, final int n, final int n2, final String s, final int n3) {
        if (s == null) {
            try {
                throw new NullPointerException();
            }
            catch (Exception ex) {
                return (View)new PlusOneDummyView(context, n);
            }
        }
        return c.b(x(context).a(c.h(context), n, n2, s, n3));
    }
    
    public static View a(final Context context, final int n, final int n2, final String s, final String s2) {
        if (s == null) {
            try {
                throw new NullPointerException();
            }
            catch (Exception ex) {
                return (View)new PlusOneDummyView(context, n);
            }
        }
        return c.b(x(context).a(c.h(context), n, n2, s, s2));
    }
    
    private static hp x(final Context context) throws a {
        eg.f(context);
        Label_0065: {
            if (ht.DQ != null) {
                break Label_0065;
            }
            if (ht.Ci == null) {
                ht.Ci = GooglePlayServicesUtil.getRemoteContext(context);
                if (ht.Ci == null) {
                    throw new a("Could not get remote context.");
                }
            }
            final ClassLoader classLoader = ht.Ci.getClassLoader();
            try {
                ht.DQ = hp.a.av((IBinder)classLoader.loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
                return ht.DQ;
            }
            catch (ClassNotFoundException ex) {
                throw new a("Could not load creator class.");
            }
            catch (InstantiationException ex2) {
                throw new a("Could not instantiate creator.");
            }
            catch (IllegalAccessException ex3) {
                throw new a("Could not access creator.");
            }
        }
    }
    
    public static class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
}
