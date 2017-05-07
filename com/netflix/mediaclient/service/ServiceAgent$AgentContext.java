// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.NetflixApplication;

public interface ServiceAgent$AgentContext
{
    NetflixApplication getApplication();
    
    ServiceAgent$BrowseAgentInterface getBrowseAgent();
    
    ServiceAgent$ConfigurationAgentInterface getConfigurationAgent();
    
    IErrorHandler getErrorHandler();
    
    NrdController getNrdController();
    
    ServiceAgent$PreAppAgentInterface getPreAppAgent();
    
    ResourceFetcher getResourceFetcher();
    
    NetflixService getService();
    
    ServiceAgent$UserAgentInterface getUserAgent();
}
