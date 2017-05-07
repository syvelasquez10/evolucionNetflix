// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.transport;

import com.netflix.mediaclient.javabridge.invoke.Invoke;

public interface Transport
{
    void close();
    
    void connect();
    
    void disconnect();
    
    void invokeMethod(final Invoke p0);
    
    void invokeMethod(final String p0, final String p1, final String p2);
    
    void setProperty(final String p0, final String p1, final String p2);
    
    void uiLoaded();
    
    void uiUnloaded();
}
