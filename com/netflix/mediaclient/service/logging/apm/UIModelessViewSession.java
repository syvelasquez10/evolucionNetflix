// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging;

public final class UIModelessViewSession extends BaseApmSession
{
    public static final String NAME = "uiModelessView";
    private IClientLogging.ModalView mTarget;
    
    public UIModelessViewSessionEndedEvent createEndedEvent() {
        final UIModelessViewSessionEndedEvent uiModelessViewSessionEndedEvent = new UIModelessViewSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mTarget);
        uiModelessViewSessionEndedEvent.setCategory(this.getCategory());
        uiModelessViewSessionEndedEvent.setSessionId(this.mId);
        uiModelessViewSessionEndedEvent.setModalView(this.mTarget);
        return uiModelessViewSessionEndedEvent;
    }
    
    public UIModelessViewSessionStartedEvent createStartEvent(final boolean b, final IClientLogging.ModalView modalView) {
        this.mTarget = modalView;
        final UIModelessViewSessionStartedEvent uiModelessViewSessionStartedEvent = new UIModelessViewSessionStartedEvent(b, modalView);
        uiModelessViewSessionStartedEvent.setCategory(this.getCategory());
        uiModelessViewSessionStartedEvent.setSessionId(this.mId);
        uiModelessViewSessionStartedEvent.setModalView(modalView);
        return uiModelessViewSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "uiModelessView";
    }
    
    public IClientLogging.ModalView getTarget() {
        return this.mTarget;
    }
}
