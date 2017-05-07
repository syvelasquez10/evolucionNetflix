// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.Proxy;
import javax.net.ssl.HttpsURLConnection;
import java.net.URLConnection;
import java.net.URL;

public final class q extends m
{
    private static final String[] f;
    
    static {
        f = new String[] { "libcore.net.http.HttpsURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnectionImpl", "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnection" };
    }
    
    public q(final e e, final d d) {
        super(e, d, q.f);
    }
    
    @Override
    protected final String a() {
        return "https";
    }
    
    @Override
    protected final int getDefaultPort() {
        return 443;
    }
    
    @Override
    protected final URLConnection openConnection(URL url) {
        url = (URL)super.openConnection(url);
        try {
            return new s((HttpsURLConnection)url, super.c, super.d);
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
            return new s((HttpsURLConnection)url, super.c, super.d);
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
