// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import java.util.List;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import java.util.Map;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;

public final class dd
{
    private static SSLSocketFactory a;
    private URL b;
    private Map c;
    private int d;
    private boolean e;
    private boolean f;
    private String g;
    private boolean h;
    private int i;
    
    static {
        dd.a = null;
        try {
            final SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            final SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (socketFactory != null) {
                if (socketFactory instanceof ab) {
                    dd.a = ((ab)socketFactory).a();
                    return;
                }
                dd.a = socketFactory;
            }
        }
        catch (GeneralSecurityException ex) {
            dd.a = null;
        }
    }
    
    public dd(final URL b) {
        this.c = new HashMap();
        this.d = 0;
        this.e = true;
        this.f = true;
        this.g = "POST";
        this.h = false;
        this.i = 2500;
        this.b = b;
        this.c.put("User-Agent", Arrays.asList("5.0.6"));
        this.c.put("Content-Type", Arrays.asList("application/json"));
        this.c.put("Accept", Arrays.asList("text/plain", "application/json"));
    }
    
    public final HttpURLConnection a() {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)this.b.openConnection();
        for (final Map.Entry<K, List> entry : this.c.entrySet()) {
            final Iterator<String> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                httpURLConnection.addRequestProperty((String)entry.getKey(), iterator2.next());
            }
        }
        httpURLConnection.setConnectTimeout(this.i);
        httpURLConnection.setReadTimeout(this.i);
        httpURLConnection.setDoInput(this.e);
        httpURLConnection.setDoOutput(this.f);
        if (this.h) {
            httpURLConnection.setChunkedStreamingMode(this.d);
        }
        httpURLConnection.setRequestMethod(this.g);
        if (httpURLConnection instanceof HttpsURLConnection) {
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)httpURLConnection;
            if (dd.a == null) {
                throw new GeneralSecurityException();
            }
            httpsURLConnection.setSSLSocketFactory(dd.a);
        }
        return httpURLConnection;
    }
}
