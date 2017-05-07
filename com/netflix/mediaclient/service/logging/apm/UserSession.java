// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;

public final class UserSession extends BaseApmSession
{
    public static final String NAME = "userSession";
    private UserSessionEndedEvent mPendingEndEvent;
    private boolean waitingOnSessionId;
    
    public UserSessionEndedEvent createEndedEvent(final ApplicationPerformanceMetricsLogging.EndReason endReason, final long n, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        final UserSessionEndedEvent mPendingEndEvent = new UserSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, endReason, n);
        mPendingEndEvent.setCategory(this.getCategory());
        mPendingEndEvent.setModalView(modalView);
        mPendingEndEvent.setDataContext(dataContext);
        UserSessionEndedEvent userSessionEndedEvent = mPendingEndEvent;
        if (this.waitingOnSessionId) {
            this.mPendingEndEvent = mPendingEndEvent;
            userSessionEndedEvent = null;
        }
        return userSessionEndedEvent;
    }
    
    public UserSessionStartedEvent createStartEvent(final ApplicationPerformanceMetricsLogging.Trigger trigger, final long n) {
        final UserSessionStartedEvent userSessionStartedEvent = new UserSessionStartedEvent(trigger, n);
        userSessionStartedEvent.setCategory(this.getCategory());
        return userSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "userSession";
    }
    
    public UserSessionEndedEvent getPendingEndEvent() {
        return this.mPendingEndEvent;
    }
    
    public boolean isWaitingOnSessionId() {
        return this.waitingOnSessionId;
    }
    
    @Override
    public void setId(final long id) {
        super.setId(id);
        this.waitingOnSessionId = false;
    }
}
