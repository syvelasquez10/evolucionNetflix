// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpHost;
import java.util.List;
import java.net.MalformedURLException;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.io.InputStream;
import org.apache.http.Header;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.HttpEntityEnclosingRequest;
import java.net.URL;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import org.apache.http.client.HttpClient;
import android.content.Context;

class da implements ab
{
    private final Context aac;
    private final String aat;
    private final HttpClient aau;
    private a aav;
    
    da(final HttpClient aau, final Context context, final a aav) {
        this.aac = context.getApplicationContext();
        this.aat = this.a("GoogleTagManager", "4.00", Build$VERSION.RELEASE, b(Locale.getDefault()), Build.MODEL, Build.ID);
        this.aau = aau;
        this.aav = aav;
    }
    
    private HttpEntityEnclosingRequest a(URL url) {
        try {
            final Object o;
            url = (URL)(o = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString()));
            final String s = "User-Agent";
            final da da = this;
            final String s2 = da.aat;
            ((HttpEntityEnclosingRequest)o).addHeader(s, s2);
            final URL url2 = url;
            return (HttpEntityEnclosingRequest)url2;
        }
        catch (URISyntaxException ex) {
            url = null;
        }
        while (true) {
            try {
                final Object o = url;
                final String s = "User-Agent";
                final da da = this;
                final String s2 = da.aat;
                ((HttpEntityEnclosingRequest)o).addHeader(s, s2);
                final URL url2 = url;
                return (HttpEntityEnclosingRequest)url2;
                final URISyntaxException ex;
                bh.z("Exception sending hit: " + ex.getClass().getSimpleName());
                bh.z(ex.getMessage());
                return (HttpEntityEnclosingRequest)url;
            }
            catch (URISyntaxException ex) {
                continue;
            }
            break;
        }
    }
    
    private void a(final HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        final StringBuffer sb = new StringBuffer();
        final Header[] allHeaders = httpEntityEnclosingRequest.getAllHeaders();
        for (int length = allHeaders.length, i = 0; i < length; ++i) {
            sb.append(allHeaders[i].toString()).append("\n");
        }
        sb.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        while (true) {
            if (httpEntityEnclosingRequest.getEntity() == null) {
                break Label_0147;
            }
            try {
                final InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null) {
                    final int available = content.available();
                    if (available > 0) {
                        final byte[] array = new byte[available];
                        content.read(array);
                        sb.append("POST:\n");
                        sb.append(new String(array)).append("\n");
                    }
                }
                bh.y(sb.toString());
            }
            catch (IOException ex) {
                bh.y("Error Writing hit to log...");
                continue;
            }
            break;
        }
    }
    
    static String b(final Locale locale) {
        if (locale != null && locale.getLanguage() != null && locale.getLanguage().length() != 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && locale.getCountry().length() != 0) {
                sb.append("-").append(locale.getCountry().toLowerCase());
            }
            return sb.toString();
        }
        return null;
    }
    
    String a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    @Override
    public boolean ch() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.aac.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            bh.y("...no network connectivity");
            return false;
        }
        return true;
    }
    
    URL d(final ap ap) {
        final String ke = ap.kE();
        try {
            return new URL(ke);
        }
        catch (MalformedURLException ex) {
            bh.w("Error trying to parse the GTM url.");
            return null;
        }
    }
    
    @Override
    public void d(final List<ap> list) {
        final int min = Math.min(list.size(), 40);
        int n = 1;
        for (int i = 0; i < min; ++i) {
            final ap ap = list.get(i);
            final URL d = this.d(ap);
            if (d == null) {
                bh.z("No destination: discarding hit.");
                this.aav.b(ap);
            }
            else {
                final HttpEntityEnclosingRequest a = this.a(d);
                if (a == null) {
                    this.aav.b(ap);
                }
                else {
                    final HttpHost httpHost = new HttpHost(d.getHost(), d.getPort(), d.getProtocol());
                    a.addHeader("Host", httpHost.toHostString());
                    this.a(a);
                    int n2 = 0;
                    Label_0401: {
                        Label_0167: {
                            if ((n2 = n) == 0) {
                                break Label_0167;
                            }
                            int n3 = n;
                            try {
                                bn.p(this.aac);
                                n2 = 0;
                                n3 = n2;
                                n = n2;
                                final HttpResponse execute = this.aau.execute(httpHost, (HttpRequest)a);
                                n3 = n2;
                                n = n2;
                                final int statusCode = execute.getStatusLine().getStatusCode();
                                n3 = n2;
                                n = n2;
                                final HttpEntity entity = execute.getEntity();
                                if (entity != null) {
                                    n3 = n2;
                                    n = n2;
                                    entity.consumeContent();
                                }
                                if (statusCode != 200) {
                                    n3 = n2;
                                    n = n2;
                                    bh.z("Bad response: " + execute.getStatusLine().getStatusCode());
                                    n3 = n2;
                                    n = n2;
                                    this.aav.c(ap);
                                    break Label_0401;
                                }
                                n3 = n2;
                                n = n2;
                                this.aav.a(ap);
                                break Label_0401;
                            }
                            catch (ClientProtocolException ex2) {
                                bh.z("ClientProtocolException sending hit; discarding hit...");
                                this.aav.b(ap);
                                n = n3;
                                continue;
                            }
                            catch (IOException ex) {
                                bh.z("Exception sending hit: " + ex.getClass().getSimpleName());
                                bh.z(ex.getMessage());
                                this.aav.c(ap);
                                continue;
                            }
                        }
                        break;
                    }
                    n = n2;
                }
            }
        }
    }
    
    public interface a
    {
        void a(final ap p0);
        
        void b(final ap p0);
        
        void c(final ap p0);
    }
}
