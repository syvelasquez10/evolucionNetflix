// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.HttpClient;

public class zzw implements zzy
{
    protected final HttpClient zzaD;
    
    public zzw(final HttpClient zzaD) {
        this.zzaD = zzaD;
    }
    
    private static void zza(final HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, final zzk<?> zzk) {
        final byte[] zzq = zzk.zzq();
        if (zzq != null) {
            httpEntityEnclosingRequestBase.setEntity((HttpEntity)new ByteArrayEntity(zzq));
        }
    }
    
    private static void zza(final HttpUriRequest httpUriRequest, final Map<String, String> map) {
        for (final String s : map.keySet()) {
            httpUriRequest.setHeader(s, (String)map.get(s));
        }
    }
    
    static HttpUriRequest zzb(final zzk<?> zzk, final Map<String, String> map) {
        switch (zzk.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown request method.");
            }
            case -1: {
                final byte[] zzm = zzk.zzm();
                if (zzm != null) {
                    final HttpPost httpPost = new HttpPost(zzk.getUrl());
                    httpPost.addHeader("Content-Type", zzk.zzl());
                    httpPost.setEntity((HttpEntity)new ByteArrayEntity(zzm));
                    return (HttpUriRequest)httpPost;
                }
                return (HttpUriRequest)new HttpGet(zzk.getUrl());
            }
            case 0: {
                return (HttpUriRequest)new HttpGet(zzk.getUrl());
            }
            case 3: {
                return (HttpUriRequest)new HttpDelete(zzk.getUrl());
            }
            case 1: {
                final HttpPost httpPost2 = new HttpPost(zzk.getUrl());
                httpPost2.addHeader("Content-Type", zzk.zzp());
                zza((HttpEntityEnclosingRequestBase)httpPost2, zzk);
                return (HttpUriRequest)httpPost2;
            }
            case 2: {
                final HttpPut httpPut = new HttpPut(zzk.getUrl());
                httpPut.addHeader("Content-Type", zzk.zzp());
                zza((HttpEntityEnclosingRequestBase)httpPut, zzk);
                return (HttpUriRequest)httpPut;
            }
            case 4: {
                return (HttpUriRequest)new HttpHead(zzk.getUrl());
            }
            case 5: {
                return (HttpUriRequest)new HttpOptions(zzk.getUrl());
            }
            case 6: {
                return (HttpUriRequest)new HttpTrace(zzk.getUrl());
            }
            case 7: {
                final zzw$zza zzw$zza = new zzw$zza(zzk.getUrl());
                zzw$zza.addHeader("Content-Type", zzk.zzp());
                zza(zzw$zza, zzk);
                return (HttpUriRequest)zzw$zza;
            }
        }
    }
    
    @Override
    public HttpResponse zza(final zzk<?> zzk, final Map<String, String> map) {
        final HttpUriRequest zzb = zzb(zzk, map);
        zza(zzb, map);
        zza(zzb, zzk.getHeaders());
        this.zza(zzb);
        final HttpParams params = zzb.getParams();
        final int zzt = zzk.zzt();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, zzt);
        return this.zzaD.execute(zzb);
    }
    
    protected void zza(final HttpUriRequest httpUriRequest) {
    }
}
