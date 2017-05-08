// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

public interface LoggingMessageDebugContext$HeaderOutput
{
    void error(final String p0);
    
    void error(final String p0, final Exception p1);
    
    void header(final String p0);
    
    boolean isEnabled();
}
