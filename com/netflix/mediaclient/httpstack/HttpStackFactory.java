// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

public final class HttpStackFactory
{
    public static final HttpStack getHttpStack() {
        return new HttpUrlStack();
    }
}
