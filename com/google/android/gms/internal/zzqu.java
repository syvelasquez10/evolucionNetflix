// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;

class zzqu implements zzqv
{
    private HttpURLConnection zzaUn;
    
    private InputStream zzc(final HttpURLConnection httpURLConnection) {
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
    
    private void zzd(final HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
    
    @Override
    public void close() {
        this.zzd(this.zzaUn);
    }
    
    @Override
    public InputStream zzfs(final String s) {
        this.zzaUn = this.zzft(s);
        return this.zzc(this.zzaUn);
    }
    
    HttpURLConnection zzft(final String s) {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
}
