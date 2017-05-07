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
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.net.MalformedURLException;
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

class db implements ab
{
    private final Context arf;
    private final String arw;
    private final HttpClient arx;
    private db$a ary;
    
    db(final HttpClient arx, final Context context, final db$a ary) {
        this.arf = context.getApplicationContext();
        this.arw = this.a("GoogleTagManager", "4.00", Build$VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
        this.arx = arx;
        this.ary = ary;
    }
    
    private HttpEntityEnclosingRequest a(URL url) {
        try {
            final Object o;
            url = (URL)(o = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString()));
            final String s = "User-Agent";
            final db db = this;
            final String s2 = db.arw;
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
                final db db = this;
                final String s2 = db.arw;
                ((HttpEntityEnclosingRequest)o).addHeader(s, s2);
                final URL url2 = url;
                return (HttpEntityEnclosingRequest)url2;
                final URISyntaxException ex;
                bh.W("Exception sending hit: " + ex.getClass().getSimpleName());
                bh.W(ex.getMessage());
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
                bh.V(sb.toString());
            }
            catch (IOException ex) {
                bh.V("Error Writing hit to log...");
                continue;
            }
            break;
        }
    }
    
    static String c(final Locale locale) {
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
    
    URL d(final ap ap) {
        final String os = ap.os();
        try {
            return new URL(os);
        }
        catch (MalformedURLException ex) {
            bh.T("Error trying to parse the GTM url.");
            return null;
        }
    }
    
    @Override
    public boolean dY() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.arf.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            bh.V("...no network connectivity");
            return false;
        }
        return true;
    }
    
    @Override
    public void j(final List<ap> list) {
        final int min = Math.min(list.size(), 40);
        int n = 1;
        for (int i = 0; i < min; ++i) {
            final ap ap = list.get(i);
            final URL d = this.d(ap);
            if (d == null) {
                bh.W("No destination: discarding hit.");
                this.ary.b(ap);
            }
            else {
                final HttpEntityEnclosingRequest a = this.a(d);
                if (a == null) {
                    this.ary.b(ap);
                }
                else {
                    final HttpHost httpHost = new HttpHost(d.getHost(), d.getPort(), d.getProtocol());
                    a.addHeader("Host", httpHost.toHostString());
                    this.a(a);
                    int n2 = 0;
                    Label_0400: {
                        Label_0166: {
                            if ((n2 = n) == 0) {
                                break Label_0166;
                            }
                            int n3 = n;
                            try {
                                bo.A(this.arf);
                                n2 = 0;
                                n3 = n2;
                                n = n2;
                                final HttpResponse execute = this.arx.execute(httpHost, (HttpRequest)a);
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
                                    bh.W("Bad response: " + execute.getStatusLine().getStatusCode());
                                    n3 = n2;
                                    n = n2;
                                    this.ary.c(ap);
                                    break Label_0400;
                                }
                                n3 = n2;
                                n = n2;
                                this.ary.a(ap);
                                break Label_0400;
                            }
                            catch (ClientProtocolException ex2) {
                                bh.W("ClientProtocolException sending hit; discarding hit...");
                                this.ary.b(ap);
                                n = n3;
                                continue;
                            }
                            catch (IOException ex) {
                                bh.W("Exception sending hit: " + ex.getClass().getSimpleName());
                                bh.W(ex.getMessage());
                                this.ary.c(ap);
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
}
