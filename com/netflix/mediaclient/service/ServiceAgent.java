// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.logging.perf.AgentPerfHelper;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pdslogging.PdsPlayInterface;
import com.netflix.mediaclient.service.pdslogging.PdsDownloadInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;

public abstract class ServiceAgent
{
    public static final String CATEGORY_DEBUG = "com.netflix.mediaclient.intent.category.DEBUG";
    private static final String TAG = "nf_service_ServiceAgent";
    private ServiceAgent$AgentContext agentContext;
    private ServiceAgent$InitCallback initCallback;
    private boolean initCalled;
    private Status initErrorResult;
    private Handler mainHandler;
    
    public ServiceAgent() {
        this.initErrorResult = CommonStatus.UNKNOWN;
    }
    
    public void destroy() {
        Log.d("nf_service_ServiceAgent", "Destroying " + this.getClass().getSimpleName());
        this.agentContext = null;
    }
    
    protected abstract void doInit();
    
    public NetflixApplication getApplication() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getApplication();
        }
        return null;
    }
    
    public ServiceAgent$BrowseAgentInterface getBrowseAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getBrowseAgent();
        }
        return null;
    }
    
    public ServiceAgent$ConfigurationAgentInterface getConfigurationAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getConfigurationAgent();
        }
        return null;
    }
    
    public Context getContext() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return (Context)agentContext.getService();
        }
        return null;
    }
    
    public IErrorHandler getErrorHandler() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getErrorHandler();
        }
        return null;
    }
    
    public IClientLogging getLoggingAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getLoggingAgent();
        }
        return null;
    }
    
    public IMSLClient getMSLClient() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getMSLClient();
        }
        return null;
    }
    
    public Handler getMainHandler() {
        return this.mainHandler;
    }
    
    public NrdController getNrdController() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getNrdController();
        }
        return null;
    }
    
    public OfflineAgentInterface getOfflineAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getOfflineAgent();
        }
        return null;
    }
    
    public PdsDownloadInterface getPdsAgentForDownload() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getPdsAgentForDownload();
        }
        return null;
    }
    
    public PdsPlayInterface getPdsAgentForPlay() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getPdsAgentForPlay();
        }
        return null;
    }
    
    public ServiceAgent$PreAppAgentInterface getPreAppAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getPreAppAgent();
        }
        return null;
    }
    
    public ResourceFetcher getResourceFetcher() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getResourceFetcher();
        }
        return null;
    }
    
    protected NetflixService getService() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getService();
        }
        return null;
    }
    
    protected ServiceAgent$UserAgentInterface getUserAgent() {
        final ServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getUserAgent();
        }
        return null;
    }
    
    public final void init(final ServiceAgent$AgentContext serviceAgent$AgentContext, final ServiceAgent$InitCallback initCallback) {
        while (true) {
            synchronized (this) {
                AgentPerfHelper.startSession(this);
                ThreadUtils.assertOnMain();
                Log.d("nf_service_ServiceAgent", "Request to init %s", this.getClass().getSimpleName());
                if (this.initCalled) {
                    ErrorLoggingManager.logHandledException(new IllegalStateException(this.getClass().getSimpleName() + " init already called!"));
                    return;
                }
                if (serviceAgent$AgentContext == null) {
                    throw new NullPointerException("AgentContext can not be null");
                }
            }
            final ServiceAgent$AgentContext agentContext;
            this.agentContext = agentContext;
            this.initCalled = true;
            this.initCallback = initCallback;
            this.mainHandler = new Handler();
            new BackgroundTask().execute(new ServiceAgent$1(this));
        }
    }
    
    protected final void initCompleted(final Status initErrorResult) {
        synchronized (this) {
            AgentPerfHelper.endSession(this);
            this.initErrorResult = initErrorResult;
            if (Log.isLoggable()) {
                Log.d("nf_service_ServiceAgent", "InitComplete with errorCode " + this.initErrorResult + " for " + this.getClass().getSimpleName());
            }
            if (this.initCallback != null) {
                this.mainHandler.post((Runnable)new ServiceAgent$2(this));
            }
        }
    }
    
    public boolean isInitCalled() {
        return this.initCalled;
    }
    
    public boolean isReady() {
        synchronized (this) {
            return this.initErrorResult.isSucces();
        }
    }
    
    public void onTrimMemory(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_service_ServiceAgent", "onTrimMemory " + this.getClass().getSimpleName());
        }
    }
    
    public void reportHandledException(final Exception ex) {
        this.getService().getClientLogging().getErrorLogging().logHandledException(ex);
    }
}
