// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

abstract class ConfigurationAgent$FetchTask implements Runnable
{
    private final ConfigurationAgentWebCallback callback;
    
    public ConfigurationAgent$FetchTask(final ConfigurationAgentWebCallback callback) {
        this.callback = callback;
    }
    
    protected ConfigurationAgentWebCallback getCallback() {
        return this.callback;
    }
}
