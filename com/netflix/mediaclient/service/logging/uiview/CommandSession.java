// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputValue;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;

public final class CommandSession extends BaseUIViewSession
{
    public static final String NAME = "command";
    protected UIViewLogging$UIViewCommandName mCommandName;
    protected CommandEndedEvent$InputMethod mInputMethod;
    protected CommandEndedEvent$InputValue mInputValue;
    protected JSONObject mModel;
    protected String mUrl;
    
    public CommandSession(final UIViewLogging$UIViewCommandName mCommandName, final IClientLogging$ModalView clientLogging$ModalView, final String mUrl, final CommandEndedEvent$InputMethod mInputMethod, final CommandEndedEvent$InputValue mInputValue, final JSONObject mModel) {
        super(clientLogging$ModalView);
        this.mCommandName = mCommandName;
        this.mUrl = mUrl;
        this.mInputMethod = mInputMethod;
        this.mInputValue = mInputValue;
        this.mModel = mModel;
    }
    
    public CommandSession(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s, final CommandEndedEvent$InputValue commandEndedEvent$InputValue, final JSONObject jsonObject) {
        this(uiViewLogging$UIViewCommandName, clientLogging$ModalView, s, null, commandEndedEvent$InputValue, jsonObject);
    }
    
    public CommandEndedEvent createEndedEvent() {
        final CommandEndedEvent commandEndedEvent = new CommandEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.getAction(), this.mUrl);
        commandEndedEvent.setCategory(this.getCategory());
        commandEndedEvent.setSessionId(this.mId);
        commandEndedEvent.setInputMethod(this.mInputMethod);
        commandEndedEvent.setInputValue(this.mInputValue);
        commandEndedEvent.setModel(this.mModel);
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
