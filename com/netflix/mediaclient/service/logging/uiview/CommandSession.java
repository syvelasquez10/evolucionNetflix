// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;

public final class CommandSession extends BaseUIViewSession
{
    public static final String NAME = "command";
    protected UIViewLogging$UIViewCommandName mCommandName;
    protected String mUrl;
    
    public CommandSession(final UIViewLogging$UIViewCommandName mCommandName, final IClientLogging$ModalView clientLogging$ModalView, final String mUrl) {
        super(clientLogging$ModalView);
        this.mCommandName = mCommandName;
        this.mUrl = mUrl;
    }
    
    public CommandEndedEvent createEndedEvent() {
        final CommandEndedEvent commandEndedEvent = new CommandEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.getAction(), this.mUrl);
        commandEndedEvent.setCategory(this.getCategory());
        commandEndedEvent.setSessionId(this.mId);
        return commandEndedEvent;
    }
    
    public UIViewLogging$UIViewCommandName getAction() {
        return this.mCommandName;
    }
    
    @Override
    public String getName() {
        return "command";
    }
}
