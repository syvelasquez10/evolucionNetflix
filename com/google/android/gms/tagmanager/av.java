// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

class av implements bl
{
    private HttpClient Yg;
    
    private InputStream a(final HttpClient httpClient, final HttpResponse httpResponse) throws IOException {
        final int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            bh.y("Success response");
            return httpResponse.getEntity().getContent();
        }
        final String string = "Bad response: " + statusCode;
        if (statusCode == 404) {
            throw new FileNotFoundException(string);
        }
        throw new IOException(string);
    }
    
    private void a(final HttpClient httpClient) {
        if (httpClient != null && httpClient.getConnectionManager() != null) {
            httpClient.getConnectionManager().shutdown();
        }
    }
    
    @Override
    public InputStream bD(final String s) throws IOException {
        this.Yg = this.kF();
        return this.a(this.Yg, this.Yg.execute((HttpUriRequest)new HttpGet(s)));
    }
    
    @Override
    public void close() {
        this.a(this.Yg);
    }
    
    HttpClient kF() {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 20000);
        return (HttpClient)new DefaultHttpClient((HttpParams)basicHttpParams);
    }
}
