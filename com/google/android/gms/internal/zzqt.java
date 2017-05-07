// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.BasicHttpParams;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.google.android.gms.tagmanager.zzbg;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

class zzqt implements zzqv
{
    private HttpClient zzaD;
    
    private InputStream zza(final HttpClient httpClient, final HttpResponse httpResponse) {
        final int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            zzbg.v("Success response");
            return httpResponse.getEntity().getContent();
        }
        final String string = "Bad response: " + statusCode;
        if (statusCode == 404) {
            throw new FileNotFoundException(string);
        }
        throw new IOException(string);
    }
    
    private void zza(final HttpClient httpClient) {
        if (httpClient != null && httpClient.getConnectionManager() != null) {
            httpClient.getConnectionManager().shutdown();
        }
    }
    
    @Override
    public void close() {
        this.zza(this.zzaD);
    }
    
    HttpClient zzBV() {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 20000);
        return (HttpClient)new DefaultHttpClient((HttpParams)basicHttpParams);
    }
    
    @Override
    public InputStream zzfs(final String s) {
        this.zzaD = this.zzBV();
        return this.zza(this.zzaD, this.zzaD.execute((HttpUriRequest)new HttpGet(s)));
    }
}
