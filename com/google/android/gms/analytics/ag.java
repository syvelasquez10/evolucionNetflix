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
import android.content.Context;
import java.net.URL;
import org.apache.http.client.HttpClient;

class ag implements m
{
    private final HttpClient Bj;
    private URL Bk;
    private final Context mContext;
    private final String vW;
    private GoogleAnalytics yu;
    
    ag(final HttpClient httpClient, final Context context) {
        this(httpClient, GoogleAnalytics.getInstance(context), context);
    }
    
    ag(final HttpClient bj, final GoogleAnalytics yu, final Context context) {
        this.mContext = context.getApplicationContext();
        this.vW = this.a("GoogleAnalytics", "3.0", Build$VERSION.RELEASE, aj.a(Locale.getDefault()), Build.MODEL, Build.ID);
        this.Bj = bj;
        this.yu = yu;
    }
    
    private void a(final aa aa, URL bk, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)aa.eM()) && this.eT()) {
            while (true) {
                Label_0250: {
                    if (bk != null) {
                        break Label_0250;
                    }
                    try {
                        if (this.Bk == null) {
                            goto Label_0198;
                        }
                        bk = this.Bk;
                        final HttpHost httpHost = new HttpHost(bk.getHost(), bk.getPort(), bk.getProtocol());
                        try {
                            final HttpEntityEnclosingRequest h = this.h(aa.eM(), bk.getPath());
                            if (h == null) {
                                return;
                            }
                            h.addHeader("Host", httpHost.toHostString());
                            if (z.eL()) {
                                this.a(h);
                            }
                            if (b) {
                                p.A(this.mContext);
                            }
                            final HttpResponse execute = this.Bj.execute(httpHost, (HttpRequest)h);
                            final int statusCode = execute.getStatusLine().getStatusCode();
                            final HttpEntity entity = execute.getEntity();
                            if (entity != null) {
                                entity.consumeContent();
                            }
                            if (statusCode != 200) {
                                z.W("Bad response: " + execute.getStatusLine().getStatusCode());
                            }
                            return;
                        }
                        catch (ClientProtocolException ex2) {
                            z.W("ClientProtocolException sending monitoring hit.");
                            return;
                        }
                        catch (IOException ex) {
                            z.W("Exception sending monitoring hit: " + ex.getClass().getSimpleName());
                            z.W(ex.getMessage());
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
                z.V(sb.toString());
            }
            catch (IOException ex) {
                z.V("Error Writing hit to log...");
                continue;
            }
            break;
        }
    }
    
    private HttpEntityEnclosingRequest h(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            z.W("Empty hit, discarding.");
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
                z.W("Encoding error, discarding hit");
                return null;
            }
        }
        ((HttpEntityEnclosingRequest)o).addHeader("User-Agent", this.vW);
        return (HttpEntityEnclosingRequest)o;
    }
    
    @Override
    public int a(final List<w> list, final aa aa, final boolean b) {
        int n = 0;
        final int min = Math.min(list.size(), 40);
        aa.e("_hr", list.size());
        int n2 = 0;
        URL url = null;
        boolean b2 = true;
        int n3;
        for (int i = 0; i < min; ++i, n = n3) {
            final w w = list.get(i);
            final URL a = this.a(w);
            if (a == null) {
                if (z.eL()) {
                    z.W("No destination: discarding hit: " + w.eG());
                }
                else {
                    z.W("No destination: discarding hit.");
                }
                ++n2;
                n3 = n + 1;
            }
            else {
                final HttpHost httpHost = new HttpHost(a.getHost(), a.getPort(), a.getProtocol());
                final String path = a.getPath();
                String a2;
                if (TextUtils.isEmpty((CharSequence)w.eG())) {
                    a2 = "";
                }
                else {
                    a2 = x.a(w, System.currentTimeMillis());
                }
                final HttpEntityEnclosingRequest h = this.h(a2, path);
                if (h == null) {
                    ++n2;
                    n3 = n + 1;
                    url = a;
                }
                else {
                    h.addHeader("Host", httpHost.toHostString());
                    if (z.eL()) {
                        this.a(h);
                    }
                    int n4 = 0;
                    Label_0284: {
                        if (a2.length() > 8192) {
                            z.W("Hit too long (> 8192 bytes)--not sent");
                            n4 = n2 + 1;
                        }
                        else if (this.yu.isDryRunEnabled()) {
                            z.U("Dry run enabled. Hit not actually sent.");
                            n4 = n2;
                        }
                        else {
                            boolean b3 = b2;
                            while (true) {
                                if (b2) {
                                    boolean b4 = b2;
                                    try {
                                        p.A(this.mContext);
                                        b3 = false;
                                        b4 = b3;
                                        b2 = b3;
                                        final HttpResponse execute = this.Bj.execute(httpHost, (HttpRequest)h);
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
                                            z.W("Bad response: " + execute.getStatusLine().getStatusCode());
                                            b2 = b3;
                                            n4 = n2;
                                        }
                                        break Label_0284;
                                    }
                                    catch (ClientProtocolException ex2) {
                                        z.W("ClientProtocolException sending hit; discarding hit...");
                                        aa.e("_hd", n2);
                                        b2 = b4;
                                        n4 = n2;
                                        break Label_0284;
                                    }
                                    catch (IOException ex) {
                                        z.W("Exception sending hit: " + ex.getClass().getSimpleName());
                                        z.W(ex.getMessage());
                                        aa.e("_de", 1);
                                        aa.e("_hd", n2);
                                        aa.e("_hs", n);
                                        this.a(aa, a, b2);
                                        return n;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                    }
                    aa.e("_td", a2.getBytes().length);
                    final int n5 = n + 1;
                    url = a;
                    n2 = n4;
                    n3 = n5;
                }
            }
        }
        aa.e("_hd", n2);
        aa.e("_hs", n);
        if (b) {
            this.a(aa, url, b2);
        }
        return n;
    }
    
    String a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    URL a(final w w) {
        if (this.Bk != null) {
            return this.Bk;
        }
        while (true) {
            final String ej = w.eJ();
            while (true) {
                try {
                    if ("http:".equals(ej)) {
                        final String s = "http://www.google-analytics.com/collect";
                        return new URL(s);
                    }
                }
                catch (MalformedURLException ex) {
                    z.T("Error trying to parse the hardcoded host url. This really shouldn't happen.");
                    return null;
                }
                final String s = "https://ssl.google-analytics.com/collect";
                continue;
            }
        }
    }
    
    @Override
    public void af(final String s) {
        try {
            this.Bk = new URL(s);
        }
        catch (MalformedURLException ex) {
            this.Bk = null;
        }
    }
    
    @Override
    public boolean dY() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            z.V("...no network connectivity");
            return false;
        }
        return true;
    }
    
    boolean eT() {
        return Math.random() * 100.0 <= 1.0;
    }
}
