// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.n;
import android.content.Context;

public abstract class g<T>
{
    private final String Sd;
    private T Se;
    
    protected g(final String sd) {
        this.Sd = sd;
    }
    
    protected final T L(Context remoteContext) throws a {
        Label_0058: {
            if (this.Se != null) {
                break Label_0058;
            }
            n.i(remoteContext);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new a("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.Se = this.d((IBinder)classLoader.loadClass(this.Sd).newInstance());
                return this.Se;
            }
            catch (ClassNotFoundException ex) {
                throw new a("Could not load creator class.", ex);
            }
            catch (InstantiationException ex2) {
                throw new a("Could not instantiate creator.", ex2);
            }
            catch (IllegalAccessException ex3) {
                throw new a("Could not access creator.", ex3);
            }
        }
    }
    
    protected abstract T d(final IBinder p0);
    
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
