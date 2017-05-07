// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLEngine;
import java.security.KeyManagementException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.AccessibleObject;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import java.lang.reflect.Method;
import javax.net.ssl.SSLContextSpi;

public final class z extends SSLContextSpi
{
    private static Method[] a;
    private static boolean b;
    private SSLContextSpi c;
    private e d;
    private d e;
    
    static {
        z.a = new Method[7];
        z.b = false;
        try {
            z.a[0] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", (Class<?>[])new Class[0]);
            z.a[1] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", String.class, Integer.TYPE);
            z.a[2] = SSLContextSpi.class.getDeclaredMethod("engineGetClientSessionContext", (Class<?>[])new Class[0]);
            z.a[3] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSessionContext", (Class<?>[])new Class[0]);
            z.a[4] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSocketFactory", (Class<?>[])new Class[0]);
            z.a[5] = SSLContextSpi.class.getDeclaredMethod("engineGetSocketFactory", (Class<?>[])new Class[0]);
            z.a[6] = SSLContextSpi.class.getDeclaredMethod("engineInit", KeyManager[].class, TrustManager[].class, SecureRandom.class);
            j.a(z.a);
            final z z = new z(new z(), null, null);
            z.engineCreateSSLEngine();
            z.engineCreateSSLEngine(null, 0);
            z.engineGetClientSessionContext();
            z.engineGetServerSessionContext();
            z.engineGetServerSocketFactory();
            z.engineGetSocketFactory();
            z.engineInit(null, null, null);
            crittercism.android.z.b = true;
        }
        catch (Throwable t) {
            dy.c();
            z.b = false;
        }
    }
    
    private z() {
    }
    
    private z(final SSLContextSpi c, final e d, final d e) {
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public static z a(final SSLContextSpi sslContextSpi, final e e, final d d) {
        if (!z.b) {
            return null;
        }
        return new z(sslContextSpi, e, d);
    }
    
    private Object a(final int n, final Object... array) {
        if (this.c == null) {
            return null;
        }
        try {
            return z.a[n].invoke(this.c, array);
        }
        catch (IllegalArgumentException ex) {
            throw new ck(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new ck(ex2);
        }
        catch (InvocationTargetException ex3) {
            final Throwable targetException = ex3.getTargetException();
            if (targetException == null) {
                throw new ck(ex3);
            }
            if (targetException instanceof Exception) {
                throw (Exception)targetException;
            }
            if (targetException instanceof Error) {
                throw (Error)targetException;
            }
            throw new ck(ex3);
        }
        catch (ClassCastException ex4) {
            throw new ck(ex4);
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
            throw new ck(ex3);
        }
    }
    
    public static boolean a() {
        return z.b;
    }
    
    private Object b(final int n, final Object... array) {
        try {
            return this.a(n, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ck(ex2);
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
        SSLSocketFactory sslSocketFactory2;
        final SSLSocketFactory sslSocketFactory = sslSocketFactory2 = (SSLSocketFactory)this.b(5, new Object[0]);
        if (sslSocketFactory == null) {
            return sslSocketFactory2;
        }
        try {
            sslSocketFactory2 = new ab(sslSocketFactory, this.d, this.e);
            return sslSocketFactory2;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            return sslSocketFactory;
        }
    }
    
    @Override
    protected final void engineInit(final KeyManager[] array, final TrustManager[] array2, final SecureRandom secureRandom) {
        this.a(array, array2, secureRandom);
    }
    
    @Override
    public final boolean equals(final Object o) {
        final SSLContextSpi c = this.c;
        return this.c.equals(o);
    }
    
    @Override
    public final int hashCode() {
        final SSLContextSpi c = this.c;
        return this.c.hashCode();
    }
    
    @Override
    public final String toString() {
        final SSLContextSpi c = this.c;
        return this.c.toString();
    }
}
