// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLEngine;
import java.security.KeyManagementException;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import java.lang.reflect.Method;
import javax.net.ssl.SSLContextSpi;

public final class o extends SSLContextSpi
{
    private static Method[] a;
    private static boolean b;
    private SSLContextSpi c;
    private e d;
    private d e;
    
    static {
        while (true) {
            o.a = new Method[7];
            o.b = false;
            while (true) {
                int n;
                try {
                    o.a[0] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", (Class<?>[])new Class[0]);
                    o.a[1] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", String.class, Integer.TYPE);
                    o.a[2] = SSLContextSpi.class.getDeclaredMethod("engineGetClientSessionContext", (Class<?>[])new Class[0]);
                    o.a[3] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSessionContext", (Class<?>[])new Class[0]);
                    o.a[4] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSocketFactory", (Class<?>[])new Class[0]);
                    o.a[5] = SSLContextSpi.class.getDeclaredMethod("engineGetSocketFactory", (Class<?>[])new Class[0]);
                    o.a[6] = SSLContextSpi.class.getDeclaredMethod("engineInit", KeyManager[].class, TrustManager[].class, SecureRandom.class);
                    final Method[] a = o.a;
                    n = 0;
                    if (n >= a.length) {
                        final o o = new o(new o(), null, null);
                        o.engineCreateSSLEngine();
                        o.engineCreateSSLEngine(null, 0);
                        o.engineGetClientSessionContext();
                        o.engineGetServerSessionContext();
                        o.engineGetServerSocketFactory();
                        o.engineGetSocketFactory();
                        o.engineInit(null, null, null);
                        com.crittercism.internal.o.b = true;
                        return;
                    }
                    final Method method = a[n];
                    if (method != null) {
                        method.setAccessible(true);
                    }
                }
                catch (Throwable t) {
                    dw.a(t);
                    o.b = false;
                    return;
                }
                ++n;
                continue;
            }
        }
    }
    
    private o() {
    }
    
    private o(final SSLContextSpi c, final e d, final d e) {
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public static o a(final SSLContextSpi sslContextSpi, final e e, final d d) {
        if (!o.b) {
            return null;
        }
        return new o(sslContextSpi, e, d);
    }
    
    private Object a(final int n, final Object... array) {
        if (this.c == null) {
            return null;
        }
        try {
            return o.a[n].invoke(this.c, array);
        }
        catch (IllegalArgumentException ex) {
            throw new ci(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new ci(ex2);
        }
        catch (InvocationTargetException ex3) {
            final Throwable targetException = ex3.getTargetException();
            if (targetException == null) {
                throw new ci(ex3);
            }
            if (targetException instanceof Exception) {
                throw (Exception)targetException;
            }
            if (targetException instanceof Error) {
                throw (Error)targetException;
            }
            throw new ci(ex3);
        }
        catch (ClassCastException ex4) {
            throw new ci(ex4);
        }
    }
    
    private Object a(final Object... array) {
        try {
            return this.a(6, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (KeyManagementException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new ci(ex3);
        }
    }
    
    public static boolean a() {
        return o.b;
    }
    
    private Object b(final int n, final Object... array) {
        try {
            return this.a(n, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ci(ex2);
        }
    }
    
    @Override
    protected final SSLEngine engineCreateSSLEngine() {
        return (SSLEngine)this.b(0, new Object[0]);
    }
    
    @Override
    protected final SSLEngine engineCreateSSLEngine(final String s, final int n) {
        return (SSLEngine)this.b(1, s, n);
    }
    
    @Override
    protected final SSLSessionContext engineGetClientSessionContext() {
        return (SSLSessionContext)this.b(2, new Object[0]);
    }
    
    @Override
    protected final SSLSessionContext engineGetServerSessionContext() {
        return (SSLSessionContext)this.b(3, new Object[0]);
    }
    
    @Override
    protected final SSLServerSocketFactory engineGetServerSocketFactory() {
        return (SSLServerSocketFactory)this.b(4, new Object[0]);
    }
    
    @Override
    protected final SSLSocketFactory engineGetSocketFactory() {
        final SSLSocketFactory sslSocketFactory = (SSLSocketFactory)this.b(5, new Object[0]);
        if (sslSocketFactory != null) {
            try {
                if (Build$VERSION.SDK_INT >= 19) {
                    return new r(sslSocketFactory, this.d, this.e);
                }
                if (Build$VERSION.SDK_INT >= 14) {
                    return new q(sslSocketFactory, this.d, this.e);
                }
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dw.b(t);
            }
        }
        return sslSocketFactory;
    }
    
    @Override
    protected final void engineInit(final KeyManager[] array, final TrustManager[] array2, final SecureRandom secureRandom) {
        this.a(array, array2, secureRandom);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this.c.equals(o);
    }
    
    @Override
    public final int hashCode() {
        return this.c.hashCode();
    }
    
    @Override
    public final String toString() {
        return this.c.toString();
    }
}
