// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge;

import com.netflix.mediaclient.error.CrashListener;
import com.netflix.mediaclient.error.CrashReport;
import com.netflix.mediaclient.javabridge.invoke.Invoke;

public interface NrdProxy
{
    void connect();
    
    void destroy();
    
    void disconnect();
    
    NrdpObject findObjectCache(final String p0);
    
    void init(final String p0);
    
    void invokeMethod(final Invoke p0);
    
    void invokeMethod(final String p0, final String p1, final String p2);
    
    void postCrashReport(final CrashReport p0);
    
    void processUpdate(final String p0);
    
    void removeCrashListener();
    
    void setCrashListener(final CrashListener p0);
    
    void setProperty(final String p0, final String p1, final String p2);
}
