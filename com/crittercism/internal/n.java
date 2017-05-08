// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import javax.net.ssl.SSLContextSpi;
import java.lang.reflect.Method;
import android.os.Build$VERSION;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.List;
import java.security.Provider;

public final class n extends Service
{
    public static final String[] a;
    private e b;
    private d c;
    private Service d;
    
    static {
        a = new String[] { "Default", "SSL", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1", "TLS" };
    }
    
    private n(final Service d, final e b, final d c) {
        super(d.getProvider(), d.getType(), d.getAlgorithm(), d.getClassName(), null, null);
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    private static n a(final Service service, final e e, final d d) {
        final n n = new n(service, e, d);
        n n3;
        try {
            final Field[] fields = Service.class.getFields();
            int n2 = 0;
            while (true) {
                n3 = n;
                if (n2 >= fields.length) {
                    break;
                }
                fields[n2].setAccessible(true);
                fields[n2].set(n, fields[n2].get(service));
                ++n2;
            }
        }
        catch (Exception ex) {
            n3 = null;
        }
        return n3;
    }
    
    private static Provider a() {
        Provider provider = null;
        try {
            final SSLContext instance = SSLContext.getInstance("TLS");
            if (instance != null) {
                provider = instance.getProvider();
            }
            return provider;
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static boolean a(final e e, final d d) {
        int i = 0;
        if (Build$VERSION.SDK_INT >= 17 && o.a()) {
            final Provider a = a();
            if (a != null) {
                boolean b = false;
                while (i < n.a.length) {
                    final Provider.Service service = a.getService("SSLContext", n.a[i]);
                    boolean b2 = b;
                    if (service != null) {
                        b2 = b;
                        if (!(service instanceof n)) {
                            final n a2 = a(service, e, d);
                            b2 = b;
                            if (a2 != null) {
                                b2 = (b | a2.b());
                            }
                        }
                    }
                    ++i;
                    b = b2;
                }
                return b;
            }
        }
        return false;
    }
    
    private boolean b() {
        final Provider provider = ((Provider.Service)this).getProvider();
        if (provider == null) {
            return false;
        }
        try {
            final Method declaredMethod = Provider.class.getDeclaredMethod("putService", Service.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(provider, this);
            provider.put("SSLContext.DummySSLAlgorithm", ((Provider.Service)this).getClassName());
            provider.remove(((Provider.Service)this).getType() + "." + ((Provider.Service)this).getAlgorithm());
            provider.remove("SSLContext.DummySSLAlgorithm");
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public final Object newInstance(Object instance) {
        final Object o = instance = super.newInstance(instance);
        try {
            if (o instanceof SSLContextSpi) {
                final o a = com.crittercism.internal.o.a((SSLContextSpi)o, this.b, this.c);
                instance = o;
                if (a != null) {
                    instance = a;
                }
            }
            return instance;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return o;
        }
    }
}
