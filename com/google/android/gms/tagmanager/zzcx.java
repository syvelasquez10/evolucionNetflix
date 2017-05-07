// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpHost;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import java.net.URL;
import java.io.InputStream;
import org.apache.http.Header;
import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import org.apache.http.client.HttpClient;
import android.content.Context;

class zzcx implements zzac
{
    private final Context zzaRY;
    private final String zzaSp;
    private final HttpClient zzaSq;
    private zzcx$zza zzaSr;
    
    zzcx(final HttpClient zzaSq, final Context context, final zzcx$zza zzaSr) {
        this.zzaRY = context.getApplicationContext();
        this.zzaSp = this.zza("GoogleTagManager", "4.00", Build$VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
        this.zzaSq = zzaSq;
        this.zzaSr = zzaSr;
    }
    
    private void zza(final HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
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
                zzbg.v(sb.toString());
            }
            catch (IOException ex) {
                zzbg.v("Error Writing hit to log...");
                continue;
            }
            break;
        }
    }
    
    static String zzc(final Locale locale) {
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
    
    private HttpEntityEnclosingRequest zzd(URL url) {
        try {
            final Object o;
            url = (URL)(o = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString()));
            final String s = "User-Agent";
            final zzcx zzcx = this;
            final String s2 = zzcx.zzaSp;
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
                final zzcx zzcx = this;
                final String s2 = zzcx.zzaSp;
                ((HttpEntityEnclosingRequest)o).addHeader(s, s2);
                final URL url2 = url;
                return (HttpEntityEnclosingRequest)url2;
                final URISyntaxException ex;
                zzbg.zzaE("Exception sending hit: " + ex.getClass().getSimpleName());
                zzbg.zzaE(ex.getMessage());
                return (HttpEntityEnclosingRequest)url;
            }
            catch (URISyntaxException ex) {
                continue;
            }
            break;
        }
    }
    
    String zza(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", s, s2, s3, s4, s5, s6);
    }
    
    URL zzd(final zzaq zzaq) {
        final String zzAg = zzaq.zzAg();
        try {
            return new URL(zzAg);
        }
        catch (MalformedURLException ex) {
            zzbg.e("Error trying to parse the GTM url.");
            return null;
        }
    }
    
    @Override
    public void zzr(final List<zzaq> list) {
        final int min = Math.min(list.size(), 40);
        int n = 1;
        for (int i = 0; i < min; ++i) {
            final zzaq zzaq = list.get(i);
            final URL zzd = this.zzd(zzaq);
            if (zzd == null) {
                zzbg.zzaE("No destination: discarding hit.");
                this.zzaSr.zzb(zzaq);
            }
            else {
                final HttpEntityEnclosingRequest zzd2 = this.zzd(zzd);
                if (zzd2 == null) {
                    this.zzaSr.zzb(zzaq);
                }
                else {
                    final HttpHost httpHost = new HttpHost(zzd.getHost(), zzd.getPort(), zzd.getProtocol());
                    zzd2.addHeader("Host", httpHost.toHostString());
                    this.zza(zzd2);
                    int n2 = 0;
                    Label_0400: {
                        Label_0166: {
                            if ((n2 = n) == 0) {
                                break Label_0166;
                            }
                            int n3 = n;
                            try {
                                zzbl.zzaQ(this.zzaRY);
                                n2 = 0;
                                n3 = n2;
                                n = n2;
                                final HttpResponse execute = this.zzaSq.execute(httpHost, (HttpRequest)zzd2);
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
                                    zzbg.zzaE("Bad response: " + execute.getStatusLine().getStatusCode());
                                    n3 = n2;
                                    n = n2;
                                    this.zzaSr.zzc(zzaq);
                                    break Label_0400;
                                }
                                n3 = n2;
                                n = n2;
                                this.zzaSr.zza(zzaq);
                                break Label_0400;
                            }
                            catch (ClientProtocolException ex2) {
                                zzbg.zzaE("ClientProtocolException sending hit; discarding hit...");
                                this.zzaSr.zzb(zzaq);
                                n = n3;
                                continue;
                            }
                            catch (IOException ex) {
                                zzbg.zzaE("Exception sending hit: " + ex.getClass().getSimpleName());
                                zzbg.zzaE(ex.getMessage());
                                this.zzaSr.zzc(zzaq);
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
    
    @Override
    public boolean zzzX() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.zzaRY.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzbg.v("...no network connectivity");
            return false;
        }
        return true;
    }
}
