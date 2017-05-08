// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import java.util.Collections;
import okhttp3.Cookie;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.CookieJar;

public class ReactCookieJarContainer implements CookieJarContainer
{
    private CookieJar cookieJar;
    
    public ReactCookieJarContainer() {
        this.cookieJar = null;
    }
    
    public List<Cookie> loadForRequest(final HttpUrl httpUrl) {
        if (this.cookieJar != null) {
            return (List<Cookie>)this.cookieJar.loadForRequest(httpUrl);
        }
        return Collections.emptyList();
    }
    
    @Override
    public void removeCookieJar() {
        this.cookieJar = null;
    }
    
    public void saveFromResponse(final HttpUrl httpUrl, final List<Cookie> list) {
        if (this.cookieJar != null) {
            this.cookieJar.saveFromResponse(httpUrl, (List)list);
        }
    }
    
    @Override
    public void setCookieJar(final CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }
}
