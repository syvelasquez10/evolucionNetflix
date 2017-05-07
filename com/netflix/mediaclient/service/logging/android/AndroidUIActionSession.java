// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public abstract class AndroidUIActionSession extends BaseLoggingSession
{
    protected static final String ANDROID_CATEGORY = "android";
    protected UserActionLogging$CommandName mAction;
    protected IClientLogging$ModalView mView;
    
    public AndroidUIActionSession(final UserActionLogging$CommandName mAction, final IClientLogging$ModalView mView) {
        this.mAction = mAction;
        this.mView = mView;
    }
    
    public UserActionLogging$CommandName getAction() {
        return this.mAction;
    }
    
    @Override
    public String getCategory() {
        return "android";
    }
    
    public IClientLogging$ModalView getView() {
        return this.mView;
    }
}
