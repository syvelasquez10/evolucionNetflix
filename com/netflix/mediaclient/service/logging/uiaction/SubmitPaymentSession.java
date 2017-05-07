// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SubmitPaymentEndedEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PaymentType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class SubmitPaymentSession extends BaseUIActionSession
{
    public static final String NAME = "submitPayment";
    
    public SubmitPaymentSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
    }
    
    public SubmitPaymentEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final boolean b, final UserActionLogging$PaymentType userActionLogging$PaymentType, final JSONObject jsonObject) {
        final SubmitPaymentEndedEvent submitPaymentEndedEvent = new SubmitPaymentEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, b, userActionLogging$PaymentType, jsonObject);
        submitPaymentEndedEvent.setCategory(this.getCategory());
        submitPaymentEndedEvent.setSessionId(this.mId);
        return submitPaymentEndedEvent;
    }
    
    @Override
    public String getName() {
        return "submitPayment";
    }
}
