// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface ErrorLogging
{
    String getLookupName();
    
    void logHandledException(final String p0);
    
    void logHandledException(final Throwable p0);
}
