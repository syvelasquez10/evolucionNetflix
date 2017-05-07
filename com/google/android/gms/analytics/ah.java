// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.util.List;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntityEnclosingRequest;
import java.net.MalformedURLException;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpHost;
import android.text.TextUtils;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import java.net.URL;
import org.apache.http.client.HttpClient;
import android.content.Context;

class ah implements n
{
    private final Context mContext;
    private GoogleAnalytics sX;
    private final String vI;
    private final HttpClient vJ;
    private URL vK;
    
    ah(final HttpClient httpClient, final Context context) {
        this(httpClient, GoogleAnalytics.getInstance(context), context);
    }
    
    ah(final HttpClient vj, final GoogleAnalytics sx, final Context context) {
        this.mContext = context.getApplicationContext();
        this.vI = this.a("GoogleAnalytics", "3.0", Build$VERSION.RELEASE, ak.a(Locale.getDefault()), Build.MODEL, Build.ID);
        this.vJ = vj;
        this.sX = sx;
    }
    
    private void a(final ab ab, URL vk, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)ab.cU()) && this.db()) {
            while (true) {
                Label_0250: {
                    if (vk != null) {
                        break Label_0250;
                    }
                    try {
                        if (this.vK == null) {
                            goto Label_0198;
                        }
                        vk = this.vK;
                        final HttpHost httpHost = new HttpHost(vk.getHost(), vk.getPort(), vk.getProtocol());
                        try {
                            final HttpEntityEnclosingRequest c = this.c(ab.cU(), vk.getPath());
                            if (c == null) {
                                return;
                            }
                            c.addHeader("Host", httpHost.toHostString());
                            if (aa.cT()) {
                                this.a(c);
                            }
                            if (b) {
                                q.p(this.mContext);
                            }
                            final HttpResponse execute = this.vJ.execute(httpHost, (HttpRequest)c);
                            final int statusCode = execute.getStatusLine().getStatusCode();
                            final HttpEntity entity = execute.getEntity();
                            if (entity != null) {
                                entity.consumeContent();
                            }
                            if (statusCode != 200) {
                                aa.z("Bad response: " + execute.getStatusLine().getStatusCode());
                            }
                            return;
                        }
                        catch (ClientProtocolException ex2) {
                            aa.z("ClientProtocolException sending monitoring hit.");
                            return;
                        }
                        catch (IOException ex) {
                            aa.z("Exception sending monitoring hit: " + ex.getClass().getSimpleName());
                            aa.z(ex.getMessage());
                            return;
                        }
                    }
                    catch (MalformedURLException ex3) {}
                }
                continue;
            }
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
                aa.y(sb.toString());
            }
            catch (IOException ex) {
                aa.y("Error Writing hit to log...");
                continue;
            }
            break;
        }
    }
    
    private HttpEntityEnclosingRequest c(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            aa.z("Empty hit, discarding.");
            return null;
        }
        final String string = s2 + "?" + s;
        Object o;
        if (string.length() < 2036) {
            o = new BasicHttpEntityEnclosingRequest("GET", string);
        }
        else {
            final BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("POST", s2);
            try {
                ((HttpEntityEnclosingRequest)basicHttpEntityEnclosingRequest).setEntity((HttpEntity)new StringEntity(s));
                o = basicHttpEntityEnclosingRequest;
            }
            catch (UnsupportedEncodingException ex) {
                aa.z("Encoding error, discarding hit");
                return null;
            }
        }
        ((HttpEntityEnclosingRequest)o).addHeader("User-Agent", this.vI);
        return (HttpEntityEnclosingRequest)o;
    }
    
    @Override
    public void F(final String s) {
        try {
            this.vK = new URL(s);
        }
        catch (MalformedURLException ex) {
            this.vK = null;
        }
    }
    
    @Override
    public int a(final List<x> list, final ab ab, final boolean b) {
        int n = 0;
        final int min = Math.min(list.size(), 40);
        ab.c("_hr", list.size());
        int n2 = 0;
        URL url = null;
        boolean b2 = true;
        int n3;
        for (int i = 0; i < min; ++i, n = n3) {
            final x x = list.get(i);
            final URL a = this.a(x);
            if (a == null) {
                if (aa.cT()) {
                    aa.z("No destination: discarding hit: " + x.cO());
                }
                else {
                    aa.z("No destination: discarding hit.");
                }
                ++n2;
                n3 = n + 1;
            }
            else {
                final HttpHost httpHost = new HttpHost(a.getHost(), a.getPort(), a.getProtocol());
                final String path = a.getPath();
                String a2;
                if (TextUtils.isEmpty((CharSequence)x.cO())) {
                    a2 = "";
                }
                else {
                    a2 = y.a(x, System.currentTimeMillis());
                }
                final HttpEntityEnclosingRequest c = this.c(a2, path);
                if (c == null) {
                    ++n2;
                    n3 = n + 1;
                    url = a;
                }
                else {
                    c.addHeader("Host", httpHost.toHostString());
                    if (aa.cT()) {
                        this.a(c);
                    }
                    int n4 = 0;
                    Label_0284: {
                        if (a2.length() > 8192) {
                            aa.z("Hit too long (> 8192 bytes)--not sent");
                            n4 = n2 + 1;
                        }
                        else if (this.sX.isDryRunEnabled()) {
                            aa.x("Dry run enabled. Hit not actually sent.");
                            n4 = n2;
                        }
                        else {
                            boolean b3 = b2;
                            while (true) {
                                if (b2) {
                                    boolean b4 = b2;
                                    try {
                                        q.p(this.mContext);
                                        b3 = false;
                                        b4 = b3;
                                        b2 = b3;
                                        final HttpResponse execute = this.vJ.execute(httpHost, (HttpRequest)c);
                                        b4 = b3;
                                        b2 = b3;
                                        final int statusCode = execute.getStatusLine().getStatusCode();
                                        b4 = b3;
                                        b2 = b3;
                                        final HttpEntity entity = execute.getEntity();
                                        if (entity != null) {
                                            b4 = b3;
                                            b2 = b3;
                                            entity.consumeContent();
                                        }
                                        b2 = b3;
                                        n4 = n2;
                                        if (statusCode != 200) {
                                            b4 = b3;
                                            b2 = b3;
                                            aa.z("Bad response: " + execute.getStatusLine().getStatusCode());
                                            b2 = b3;
                                            n4 = n2;
                                        }
                                        break Label_0284;
                                    }
                                    catch (ClientProtocolException ex2) {
                                        aa.z("ClientProtocolException sending hit; discarding hit...");
                                        ab.c("_hd", n2);
                                        b2 = b4;
                                        n4 = n2;
                                        break Label_0284;
                                    }
                                    catch (IOException ex) {
                                        aa.z("Exception sending hit: " + ex.getClass().getSimpleName());
                                        aa.z(ex.getMessage());
                                        ab.c("_de", 1);
                                        ab.c("_hd", n2);
                                        ab.c("_hs", n);
                                        this.a(ab, a, b2);
                                        return n;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                    }
                    ab.c("_td", a2.getBytes().length);
                    final int n5 = n + 1;
                    url = a;
                    n2 = n4;
                    n3 = n5;
                }
            }
        }
        ab.c("_hd", n2);
        ab.c("_hs", n);
        if (b) {
            this.a(ab, url, b2);
        }
        return n;
    }
    
    String a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    URL a(final x x) {
        if (this.vK != null) {
            return this.vK;
        }
        while (true) {
            final String cr = x.cR();
            while (true) {
                try {
                    if ("http:".equals(cr)) {
                        final String s = "http://www.google-analytics.com/collect";
                        return new URL(s);
                    }
                }
                catch (MalformedURLException ex) {
                    aa.w("Error trying to parse the hardcoded host url. This really shouldn't happen.");
                    return null;
                }
                final String s = "https://ssl.google-analytics.com/collect";
                continue;
            }
        }
    }
    
    @Override
    public boolean ch() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            aa.y("...no network connectivity");
            return false;
        }
        return true;
    }
    
    boolean db() {
        return Math.random() * 100.0 <= 1.0;
    }
}
