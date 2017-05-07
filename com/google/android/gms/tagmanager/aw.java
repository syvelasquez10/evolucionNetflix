// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;

class aw implements bl
{
    private HttpURLConnection Yh;
    
    private InputStream a(final HttpURLConnection httpURLConnection) throws IOException {
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection.getInputStream();
        }
        final String string = "Bad response: " + responseCode;
        if (responseCode == 404) {
            throw new FileNotFoundException(string);
        }
        throw new IOException(string);
    }
    
    private void b(final HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
    
    @Override
    public InputStream bD(final String s) throws IOException {
        this.Yh = this.bE(s);
        return this.a(this.Yh);
    }
    
    HttpURLConnection bE(final String s) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
    
    @Override
    public void close() {
        this.b(this.Yh);
    }
}
