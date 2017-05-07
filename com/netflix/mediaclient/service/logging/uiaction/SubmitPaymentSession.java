// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SubmitPaymentEndedEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class SubmitPaymentSession extends BaseUIActionSession
{
    public static final String NAME = "submitPayment";
    
    public SubmitPaymentSession(final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        super(commandName, modalView);
    }
    
    public SubmitPaymentEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError, final boolean b, final UserActionLogging.PaymentType paymentType, final JSONObject jsonObject) {
        final SubmitPaymentEndedEvent submitPaymentEndedEvent = new SubmitPaymentEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, b, paymentType, jsonObject);
        submitPaymentEndedEvent.setCategory(this.getCategory());
        submitPaymentEndedEvent.setSessionId(this.mId);
        return submitPaymentEndedEvent;
    }
    
    @Override
    public String getName() {
        return "submitPayment";
    }
}
