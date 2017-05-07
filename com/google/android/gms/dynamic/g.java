// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.fq;
import android.content.Context;
import android.os.IBinder;

public abstract class g<T>
{
    private final String Hx;
    private T Hy;
    
    protected g(final String hx) {
        this.Hx = hx;
    }
    
    protected abstract T d(final IBinder p0);
    
    protected final T z(Context remoteContext) throws a {
        Label_0058: {
            if (this.Hy != null) {
                break Label_0058;
            }
            fq.f(remoteContext);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new a("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.Hy = this.d((IBinder)classLoader.loadClass(this.Hx).newInstance());
                return this.Hy;
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
        
        public a(final String s, final Throwable t) {
            super(s, t);
        }
    }
}
