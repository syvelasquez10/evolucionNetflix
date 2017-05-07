// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview;

import com.netflix.mediaclient.service.logging.uiview.model.ImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONObject;

public class ImpressionSession extends BaseUIViewSession
{
    public static final String NAME = "impression";
    private JSONObject mModel;
    private String mOriginatingRequestGuid;
    
    public ImpressionSession(final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        this(clientLogging$ModalView, s, null);
    }
    
    public ImpressionSession(final IClientLogging$ModalView clientLogging$ModalView, final String mOriginatingRequestGuid, final JSONObject mModel) {
        super(clientLogging$ModalView);
        this.mOriginatingRequestGuid = mOriginatingRequestGuid;
        this.mModel = mModel;
    }
    
    public ImpressionSessionEndedEvent createEndedEvent(final boolean b, final Error error) {
        final ImpressionSessionEndedEvent impressionSessionEndedEvent = new ImpressionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mOriginatingRequestGuid, b, error);
        impressionSessionEndedEvent.setCategory(this.getCategory());
        impressionSessionEndedEvent.setSessionId(this.mId);
        impressionSessionEndedEvent.setModel(this.mModel);
        return impressionSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "impression";
    }
}
