// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.List;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import java.util.Map;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.IOException;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpEntity;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;

public class zzz implements zzy
{
    private final zzz$zza zzaE;
    private final SSLSocketFactory zzaF;
    
    public zzz() {
        this(null);
    }
    
    public zzz(final zzz$zza zzz$zza) {
        this(zzz$zza, null);
    }
    
    public zzz(final zzz$zza zzaE, final SSLSocketFactory zzaF) {
        this.zzaE = zzaE;
        this.zzaF = zzaF;
    }
    
    private HttpURLConnection zza(final URL url, final zzk<?> zzk) {
        final HttpURLConnection zza = this.zza(url);
        final int zzt = zzk.zzt();
        zza.setConnectTimeout(zzt);
        zza.setReadTimeout(zzt);
        zza.setUseCaches(false);
        zza.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.zzaF != null) {
            ((HttpsURLConnection)zza).setSSLSocketFactory(this.zzaF);
        }
        return zza;
    }
    
    private static HttpEntity zza(final HttpURLConnection httpURLConnection) {
        final BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        while (true) {
            try {
                final InputStream content = httpURLConnection.getInputStream();
                basicHttpEntity.setContent(content);
                basicHttpEntity.setContentLength((long)httpURLConnection.getContentLength());
                basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
                basicHttpEntity.setContentType(httpURLConnection.getContentType());
                return (HttpEntity)basicHttpEntity;
            }
            catch (IOException ex) {
                final InputStream content = httpURLConnection.getErrorStream();
                continue;
            }
            break;
        }
    }
    
    static void zza(final HttpURLConnection httpURLConnection, final zzk<?> zzk) {
        switch (zzk.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown method type.");
            }
            case -1: {
                final byte[] zzm = zzk.zzm();
                if (zzm != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", zzk.zzl());
                    final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(zzm);
                    dataOutputStream.close();
                }
            }
            case 0: {
                httpURLConnection.setRequestMethod("GET");
            }
            case 3: {
                httpURLConnection.setRequestMethod("DELETE");
            }
            case 1: {
                httpURLConnection.setRequestMethod("POST");
                zzb(httpURLConnection, zzk);
            }
            case 2: {
                httpURLConnection.setRequestMethod("PUT");
                zzb(httpURLConnection, zzk);
            }
            case 4: {
                httpURLConnection.setRequestMethod("HEAD");
            }
            case 5: {
                httpURLConnection.setRequestMethod("OPTIONS");
            }
            case 6: {
                httpURLConnection.setRequestMethod("TRACE");
            }
            case 7: {
                httpURLConnection.setRequestMethod("PATCH");
                zzb(httpURLConnection, zzk);
            }
        }
    }
    
    private static void zzb(final HttpURLConnection httpURLConnection, final zzk<?> zzk) {
        final byte[] zzq = zzk.zzq();
        if (zzq != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzk.zzp());
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzq);
            dataOutputStream.close();
        }
    }
    
    protected HttpURLConnection zza(final URL url) {
        return (HttpURLConnection)url.openConnection();
    }
    
    @Override
    public HttpResponse zza(final zzk<?> zzk, final Map<String, String> map) {
        final String url = zzk.getUrl();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.putAll(zzk.getHeaders());
        hashMap.putAll(map);
        String zzh;
        if (this.zzaE != null) {
            if ((zzh = this.zzaE.zzh(url)) == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        }
        else {
            zzh = url;
        }
        final HttpURLConnection zza = this.zza(new URL(zzh), zzk);
        for (final String s : hashMap.keySet()) {
            zza.addRequestProperty(s, hashMap.get(s));
        }
        zza(zza, zzk);
        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (zza.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(protocolVersion, zza.getResponseCode(), zza.getResponseMessage()));
        basicHttpResponse.setEntity(zza(zza));
        for (final Map.Entry<String, List<String>> entry : zza.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), (String)entry.getValue().get(0)));
            }
        }
        return (HttpResponse)basicHttpResponse;
    }
}
