// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.service.logging.uiview.model.ModalViewStartedEvent;
import com.netflix.mediaclient.service.logging.uiview.model.ModalViewEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public final class ModalViewSession extends BaseUIViewSession
{
    public static final String NAME = "viewName";
    
    public ModalViewSession(final IClientLogging$ModalView clientLogging$ModalView) {
        super(clientLogging$ModalView);
    }
    
    public ModalViewEndedEvent createEndedEvent(final boolean b) {
        final ModalViewEndedEvent modalViewEndedEvent = new ModalViewEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.getView(), b);
        modalViewEndedEvent.setCategory(this.getCategory());
        modalViewEndedEvent.setSessionId(this.mId);
        return modalViewEndedEvent;
    }
    
    public ModalViewStartedEvent createStartEvent() {
        final ModalViewStartedEvent modalViewStartedEvent = new ModalViewStartedEvent(this.getView());
        modalViewStartedEvent.setCategory(this.getCategory());
        modalViewStartedEvent.setSessionId(this.mId);
        return modalViewStartedEvent;
    }
    
    @Override
    public String getName() {
        return "viewName";
    }
}
