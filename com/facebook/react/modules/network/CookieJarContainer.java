// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okhttp3.CookieJar;

public interface CookieJarContainer extends CookieJar
{
    void removeCookieJar();
    
    void setCookieJar(final CookieJar p0);
}
