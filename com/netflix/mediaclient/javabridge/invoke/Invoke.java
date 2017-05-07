// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke;

import com.netflix.mediaclient.javabridge.transport.Transport;

public interface Invoke
{
    void execute(final Transport p0);
    
    String getArguments();
    
    String getMethod();
    
    String getObject();
}
