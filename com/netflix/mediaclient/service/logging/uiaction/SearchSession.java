// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction;

import com.netflix.mediaclient.service.logging.uiaction.model.SearchEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

public final class SearchSession extends BaseUIActionSession
{
    public static final String NAME = "search";
    private long mRequestId;
    private String mTerm;
    
    public SearchSession(final long mRequestId, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final String mTerm) {
        super(commandName, modalView);
        this.mTerm = mTerm;
        this.mRequestId = mRequestId;
    }
    
    public SearchEndedEvent createEndedEvent(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        final SearchEndedEvent searchEndedEvent = new SearchEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mAction, completionReason, uiError, this.mTerm);
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
