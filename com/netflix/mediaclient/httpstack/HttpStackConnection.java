// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

public abstract class HttpStackConnection
{
    public HttpStackConnection getConnection() {
        return this;
    }
    
    public abstract void setCookie(final String p0, final String p1);
}
