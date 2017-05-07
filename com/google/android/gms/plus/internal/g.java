// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.dynamic.e;
import com.google.android.gms.plus.PlusOneDummyView;
import android.view.View;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.fq;
import android.content.Context;

public final class g
{
    private static Context Sz;
    private static c Uj;
    
    private static c D(final Context context) throws a {
        fq.f(context);
        Label_0065: {
            if (g.Uj != null) {
                break Label_0065;
            }
            if (g.Sz == null) {
                g.Sz = GooglePlayServicesUtil.getRemoteContext(context);
                if (g.Sz == null) {
                    throw new a("Could not get remote context.");
                }
            }
            final ClassLoader classLoader = g.Sz.getClassLoader();
            try {
                g.Uj = c.a.aP((IBinder)classLoader.loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
                return g.Uj;
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
    
    public static View a(final Context context, final int n, final int n2, final String s, final int n3) {
        if (s == null) {
            try {
                throw new NullPointerException();
            }
            catch (Exception ex) {
                return (View)new PlusOneDummyView(context, n);
            }
        }
        return e.d(D(context).a(e.h(context), n, n2, s, n3));
    }
    
    public static class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
}
