// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SearchEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;

public final class SearchSession extends BaseUIActionSession
{
    public static final String NAME = "search";
    private long mRequestId;
    private String mTerm;
    
    public SearchSession(final long mRequestId, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String mTerm) {
        super(userActionLogging$CommandName, clientLogging$ModalView);
        this.mTerm = mTerm;
        this.mRequestId = mRequestId;
    }
    
    public SearchEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final SearchEndedEvent searchEndedEvent = new SearchEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, clientLogging$CompletionReason, uiError, this.mTerm);
        searchEndedEvent.setCategory(this.getCategory());
        searchEndedEvent.setSessionId(this.mId);
        return searchEndedEvent;
    }
    
    @Override
    public String getName() {
        return "search";
    }
    
    public long getRequestId() {
        return this.mRequestId;
    }
}
