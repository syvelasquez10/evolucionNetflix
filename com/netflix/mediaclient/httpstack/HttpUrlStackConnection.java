// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.util.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.net.HttpURLConnection;

public class HttpUrlStackConnection extends HttpStackConnection
{
    private static final String COOKIE_VALUE_DELIMITER = ";";
    private static final String NAME_VALUE_SEPERATOR = "=";
    private StringBuilder cookieBuilder;
    private String uriString;
    private HttpURLConnection urlConnection;
    
    public HttpUrlStackConnection(final HttpURLConnection urlConnection, final String uriString) {
        this.setUrlConnection(urlConnection);
        this.setUriString(uriString);
        this.cookieBuilder = new StringBuilder();
    }
    
    public String getCookies() {
        return this.cookieBuilder.toString();
    }
    
    public String getUriString() {
        return this.uriString;
    }
    
    public HttpURLConnection getUrlConnection() {
        return this.urlConnection;
    }
    
    @Override
    public void setCookie(final String s, final String s2) {
        if (StringUtils.isEmpty(s2)) {
            Log.d("HttpUrlStackConnection", "cookieValue null for " + s);
        }
        if (this.cookieBuilder.length() > 0) {
            this.cookieBuilder.append(";");
        }
        this.cookieBuilder.append(s);
        this.cookieBuilder.append("=");
        this.cookieBuilder.append(s2);
    }
    
    public void setUriString(final String uriString) {
        this.uriString = uriString;
    }
    
    public void setUrlConnection(final HttpURLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }
}
