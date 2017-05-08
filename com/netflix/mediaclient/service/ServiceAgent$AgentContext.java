// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pdslogging.PdsPlayInterface;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.NetflixApplication;

public interface ServiceAgent$AgentContext
{
    NetflixApplication getApplication();
    
    ServiceAgent$BrowseAgentInterface getBrowseAgent();
    
    ServiceAgent$ConfigurationAgentInterface getConfigurationAgent();
    
    IErrorHandler getErrorHandler();
    
    IClientLogging getLoggingAgent();
    
    IMSLClient getMSLClient();
    
    NrdController getNrdController();
    
    OfflineAgentInterface getOfflineAgent();
    
    PdsDownloadInterface getPdsAgentForDownload();
    
    PdsPlayInterface getPdsAgentForPlay();
    
    ServiceAgent$PreAppAgentInterface getPreAppAgent();
    
    ResourceFetcher getResourceFetcher();
    
    NetflixService getService();
    
    ServiceAgent$UserAgentInterface getUserAgent();
}
