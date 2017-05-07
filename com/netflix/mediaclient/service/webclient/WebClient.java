// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public interface WebClient
{
    void init(final WebClientInitParameters p0);
    
    boolean isSynchronous();
    
    void setTimeout(final int p0);
}
