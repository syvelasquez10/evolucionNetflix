// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.net.URLConnection;
import java.net.Proxy;
import java.net.URL;
import java.lang.reflect.Constructor;
import java.net.URLStreamHandler;

public abstract class m extends URLStreamHandler
{
    public static final String[] a;
    public static final String[] b;
    e c;
    d d;
    boolean e;
    private Constructor f;
    private Constructor g;
    
    static {
        a = new String[] { "java.net.URL", "int", "java.net.Proxy" };
        b = new String[] { "java.net.URL", "int" };
    }
    
    public m(final e e, final d d, final String[] array) {
        this(e, d, array, m.a, m.b);
    }
    
    private m(final e c, final d d, final String[] array, final String[] array2, final String[] array3) {
        this.f = null;
        this.g = null;
        this.c = c;
        this.d = d;
        this.e = true;
        int n = 0;
    Label_0081_Outer:
        while (true) {
            while (true) {
                if (n < array.length) {
                    try {
                        this.f = l.a(array[n], array3);
                        this.g = l.a(array[n], array2);
                        this.f.setAccessible(true);
                        this.g.setAccessible(true);
                        if (this.f == null || this.g == null) {
                            throw new ClassNotFoundException("Couldn't find suitable connection implementations");
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        this.f = null;
                        this.f = null;
                        ++n;
                        continue Label_0081_Outer;
                    }
                    break;
                }
                continue;
            }
        }
        if (!this.b()) {
            throw new ClassNotFoundException("Unable to open test connections");
        }
    }
    
    private URLConnection a(final URL url, final Proxy proxy) {
    Label_0101_Outer:
        while (true) {
            URLConnection urlConnection = null;
            final String string = "Unable to setup network statistics on a " + this.a() + " connection due to ";
            while (true) {
            Label_0319:
                while (true) {
                    try {
                        final eb e = eb.e;
                        Object o;
                        if (proxy == null) {
                            urlConnection = this.f.newInstance(url, this.getDefaultPort());
                            o = null;
                        }
                        else {
                            urlConnection = this.g.newInstance(url, this.getDefaultPort(), proxy);
                            o = null;
                        }
                        if (o == null) {
                            return urlConnection;
                        }
                        if (!this.e) {
                            throw o;
                        }
                        this.e = false;
                        final v a = v.a();
                        if (a == null) {
                            break Label_0319;
                        }
                        final int c = a.c() ? 1 : 0;
                        dy.b("Stopping network statistics monitoring");
                        if (c != 0) {
                            return new URL(url.toExternalForm()).openConnection();
                        }
                        return urlConnection;
                    }
                    catch (IllegalArgumentException ex) {
                        new StringBuilder().append(string).append("bad arguments");
                        dy.b();
                        final Object o = new IOException(ex.getMessage());
                        continue Label_0101_Outer;
                    }
                    catch (InstantiationException ex2) {
                        new StringBuilder().append(string).append("an instantiation problem");
                        dy.b();
                        final Object o = new IOException(ex2.getMessage());
                        continue Label_0101_Outer;
                    }
                    catch (IllegalAccessException ex3) {
                        new StringBuilder().append(string).append("security restrictions");
                        dy.b();
                        final Object o = new IOException(ex3.getMessage());
                        continue Label_0101_Outer;
                    }
                    catch (InvocationTargetException ex4) {
                        new StringBuilder().append(string).append("an invocation problem");
                        dy.b();
                        final Object o = new IOException(ex4.getMessage());
                        continue Label_0101_Outer;
                    }
                    continue Label_0101_Outer;
                }
                final int c = 0;
                continue;
            }
        }
    }
    
    private boolean b() {
        this.e = false;
        try {
            this.openConnection(new URL("http://www.google.com"));
            return true;
        }
        catch (IOException ex) {
            return false;
        }
        finally {
            this.e = true;
        }
    }
    
    protected abstract String a();
    
    @Override
    protected abstract int getDefaultPort();
    
    @Override
    protected URLConnection openConnection(final URL url) {
        return this.a(url, null);
    }
    
    @Override
    protected URLConnection openConnection(final URL url, final Proxy proxy) {
        if (url == null || proxy == null) {
            throw new IllegalArgumentException("url == null || proxy == null");
        }
        return this.a(url, proxy);
    }
}
