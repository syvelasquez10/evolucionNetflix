// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.eg;
import android.content.Context;
import android.os.IBinder;

public abstract class e<T>
{
    private final String sF;
    private T sG;
    
    protected e(final String sf) {
        this.sF = sf;
    }
    
    protected abstract T d(final IBinder p0);
    
    protected final T t(Context remoteContext) throws a {
        Label_0058: {
            if (this.sG != null) {
                break Label_0058;
            }
            eg.f(remoteContext);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new a("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.sG = this.d((IBinder)classLoader.loadClass(this.sF).newInstance());
                return this.sG;
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
