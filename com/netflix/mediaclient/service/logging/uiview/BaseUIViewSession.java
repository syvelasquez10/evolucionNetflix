// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseUIViewSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "uiView";
    protected IClientLogging$ModalView mView;
    
    public BaseUIViewSession(final IClientLogging$ModalView mView) {
        this.mView = mView;
    }
    
    @Override
    public String getCategory() {
        return "uiView";
    }
    
    public IClientLogging$ModalView getView() {
        return this.mView;
    }
}
