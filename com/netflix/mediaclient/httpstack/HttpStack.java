// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;
import java.util.List;
import java.io.IOException;

public interface HttpStack
{
    public static final String COOKIE_SET_CHECK = "Set-Cookie";
    public static final int DEFAULT_HTTP_CONN_TIMEOUT_MS = 15000;
    public static final int DEFAULT_HTTP_SO_TIMEOUT_MS = 10000;
    public static final String DOMAIN = ".netflix.com";
    public static final String PATH = "/";
    
    void disconnect(final HttpStackConnection p0);
    
    HttpStackConnection getConnection(final String p0) throws IOException;
    
    List<Cookie> getResponseCookies(final HttpStackConnection p0);
    
    HttpResponse performGet(final HttpStackConnection p0) throws IOException;
}
