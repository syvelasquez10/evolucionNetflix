// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseUIActionSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "uiAction";
    protected UserActionLogging$CommandName mAction;
    protected IClientLogging$ModalView mView;
    
    public BaseUIActionSession(final UserActionLogging$CommandName mAction, final IClientLogging$ModalView mView) {
        this.mAction = mAction;
        this.mView = mView;
    }
    
    public UserActionLogging$CommandName getAction() {
        return this.mAction;
    }
    
    @Override
    public String getCategory() {
        return "uiAction";
    }
    
    public IClientLogging$ModalView getView() {
        return this.mView;
    }
}
