// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;

public abstract class PServiceAgent
{
    private static final String TAG = "nf_preapp_serviceagent";
    private PServiceAgent$AgentContext agentContext;
    private PServiceAgent$InitCallback initCallback;
    private boolean initCalled;
    private Status initErrorResult;
    private Handler mainHandler;
    
    public PServiceAgent() {
        this.initErrorResult = CommonStatus.UNKNOWN;
    }
    
    public void destroy() {
        Log.d("nf_preapp_serviceagent", "Destroying " + this.getClass().getSimpleName());
        this.agentContext = null;
    }
    
    protected abstract void doInit();
    
    protected Context getContext() {
        final PServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return (Context)agentContext.getService();
        }
        return null;
    }
    
    public Handler getMainHandler() {
        return this.mainHandler;
    }
    
    protected PService getService() {
        final PServiceAgent$AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getService();
        }
        return null;
    }
    
    public final void init(final PServiceAgent$AgentContext pServiceAgent$AgentContext, final PServiceAgent$InitCallback initCallback) {
        synchronized (this) {
            ThreadUtils.assertOnMain();
            Log.d("nf_preapp_serviceagent", "Request to init " + this.getClass().getSimpleName());
            if (this.initCalled) {
                throw new IllegalStateException("PServiceAgent init already called");
            }
        }
        final PServiceAgent$AgentContext agentContext;
        if (agentContext == null) {
            throw new NullPointerException("PreAppAgentContext can not be null");
        }
        this.agentContext = agentContext;
        this.initCalled = true;
        this.initCallback = initCallback;
        this.mainHandler = new Handler();
        new BackgroundTask().execute(new PServiceAgent$1(this));
    }
    // monitorexit(this)
    
    protected final void initCompleted(final Status initErrorResult) {
        synchronized (this) {
            this.initErrorResult = initErrorResult;
            if (Log.isLoggable("nf_preapp_serviceagent", 3)) {
                Log.d("nf_preapp_serviceagent", "InitComplete with errorCode " + this.initErrorResult + " for " + this.getClass().getSimpleName());
            }
            if (this.initCallback != null) {
                this.mainHandler.post((Runnable)new PServiceAgent$2(this));
            }
        }
    }
    
    public boolean isReady() {
        synchronized (this) {
            return this.initErrorResult.isSucces();
        }
    }
}
