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
    private AgentContext agentContext;
    private InitCallback initCallback;
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
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return (Context)agentContext.getService();
        }
        return null;
    }
    
    public Handler getMainHandler() {
        return this.mainHandler;
    }
    
    protected PService getService() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getService();
        }
        return null;
    }
    
    public final void init(final AgentContext agentContext, final InitCallback initCallback) {
        synchronized (this) {
            ThreadUtils.assertOnMain();
            Log.d("nf_preapp_serviceagent", "Request to init " + this.getClass().getSimpleName());
            if (this.initCalled) {
                throw new IllegalStateException("PServiceAgent init already called");
            }
        }
        final AgentContext agentContext2;
        if (agentContext2 == null) {
            throw new NullPointerException("PreAppAgentContext can not be null");
        }
        this.agentContext = agentContext2;
        this.initCalled = true;
        this.initCallback = initCallback;
        this.mainHandler = new Handler();
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_preapp_serviceagent", "Initing " + PServiceAgent.this.getClass().getSimpleName());
                PServiceAgent.this.doInit();
            }
        });
    }
    // monitorexit(this)
    
    protected final void initCompleted(final Status initErrorResult) {
        synchronized (this) {
            this.initErrorResult = initErrorResult;
            if (Log.isLoggable("nf_preapp_serviceagent", 3)) {
                Log.d("nf_preapp_serviceagent", "InitComplete with errorCode " + this.initErrorResult + " for " + this.getClass().getSimpleName());
            }
            if (this.initCallback != null) {
                this.mainHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        PServiceAgent.this.initCallback.onInitComplete(PServiceAgent.this, PServiceAgent.this.initErrorResult);
                    }
                });
            }
        }
    }
    
    public boolean isReady() {
        synchronized (this) {
            return this.initErrorResult.isSucces();
        }
    }
    
    public interface AgentContext
    {
        PService getService();
    }
    
    public interface InitCallback
    {
        void onInitComplete(final PServiceAgent p0, final Status p1);
    }
}
