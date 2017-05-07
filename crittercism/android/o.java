// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.Proxy;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;

public final class o extends m
{
    private static final String[] f;
    
    static {
        f = new String[] { "libcore.net.http.HttpURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection" };
    }
    
    public o(final e e, final d d) {
        super(e, d, o.f);
    }
    
    @Override
    protected final String a() {
        return "http";
    }
    
    @Override
    protected final int getDefaultPort() {
        return 80;
    }
    
    @Override
    protected final URLConnection openConnection(URL url) {
        url = (URL)super.openConnection(url);
        try {
            return new r((HttpURLConnection)url, super.c, super.d);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            return (URLConnection)url;
        }
    }
    
    @Override
    protected final URLConnection openConnection(URL url, final Proxy proxy) {
        url = (URL)super.openConnection(url, proxy);
        try {
            return new r((HttpURLConnection)url, super.c, super.d);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
            return (URLConnection)url;
        }
    }
}
