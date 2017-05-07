// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UIViewLogging;

public final class CommandSession extends BaseUIViewSession
{
    public static final String NAME = "command";
    
    public CommandSession(final UIViewLogging.UIViewCommandName uiViewCommandName, final IClientLogging.ModalView modalView) {
        super(uiViewCommandName, modalView);
    }
    
    public CommandEndedEvent createEndedEvent() {
        final CommandEndedEvent commandEndedEvent = new CommandEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.getAction());
        commandEndedEvent.setCategory(this.getCategory());
        commandEndedEvent.setSessionId(this.mId);
        return commandEndedEvent;
    }
    
    @Override
    public String getName() {
        return "command";
    }
}
