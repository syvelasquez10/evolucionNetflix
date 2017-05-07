// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class BaseUIViewSession extends BaseLoggingSession
{
    protected static final String CATEGORY = "uiView";
    protected UIViewLogging.UIViewCommandName mCommandName;
    protected IClientLogging.ModalView mView;
    
    public BaseUIViewSession(final UIViewLogging.UIViewCommandName mCommandName, final IClientLogging.ModalView mView) {
        this.mCommandName = mCommandName;
        this.mView = mView;
    }
    
    public UIViewLogging.UIViewCommandName getAction() {
        return this.mCommandName;
    }
    
    @Override
    public String getCategory() {
        return "uiView";
    }
    
    public IClientLogging.ModalView getView() {
        return this.mView;
    }
}
