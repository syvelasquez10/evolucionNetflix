// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import com.netflix.mediaclient.util.StringUtils;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.CookieStore;

public class HttpApacheStackConnection extends HttpStackConnection
{
    private static CookieStore mLocalCookieStore;
    private DefaultHttpClient defaultHttpClient;
    private HttpParams httpParams;
    private String uriString;
    
    public HttpApacheStackConnection(final HttpParams httpParams, final String uriString) {
        this.setHttpParams(httpParams);
        this.setUriString(uriString);
        this.setDefaultHttpClient(null);
        HttpApacheStackConnection.mLocalCookieStore = (CookieStore)new BasicCookieStore();
    }
    
    public DefaultHttpClient getDefaultHttpClient() {
        return this.defaultHttpClient;
    }
    
    public HttpParams getHttpParams() {
        return this.httpParams;
    }
    
    public HttpContext getLocalHttpContext() {
        final BasicHttpContext basicHttpContext = new BasicHttpContext();
        ((HttpContext)basicHttpContext).setAttribute("http.cookie-store", (Object)HttpApacheStackConnection.mLocalCookieStore);
        return (HttpContext)basicHttpContext;
    }
    
    public String getUriString() {
        return this.uriString;
    }
    
    @Override
    public void setCookie(final String s, final String s2) {
        if (StringUtils.isEmpty(s2)) {
            return;
        }
        final BasicClientCookie basicClientCookie = new BasicClientCookie(s, s2);
        basicClientCookie.setDomain(".netflix.com");
        basicClientCookie.setPath("/");
        basicClientCookie.setVersion(0);
        HttpApacheStackConnection.mLocalCookieStore.addCookie((Cookie)basicClientCookie);
    }
    
    public void setDefaultHttpClient(final DefaultHttpClient defaultHttpClient) {
        this.defaultHttpClient = defaultHttpClient;
    }
    
    public void setHttpParams(final HttpParams httpParams) {
        this.httpParams = httpParams;
    }
    
    public void setUriString(final String uriString) {
        this.uriString = uriString;
    }
}
