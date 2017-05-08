// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

public interface ConfigurationWebClient
{
    void fetchConfigData(final ConfigurationAgentWebCallback p0);
    
    boolean isSynchronous();
    
    void verifyLogin(final String p0, final String p1, final ConfigurationAgentWebCallback p2);
}
