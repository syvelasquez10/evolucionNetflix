// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;

public final class UserSession extends BaseApmSession
{
    public static final String NAME = "userSession";
    private UserSessionEndedEvent mPendingEndEvent;
    private boolean waitingOnSessionId;
    
    public UserSessionEndedEvent createEndedEvent(final ApplicationPerformanceMetricsLogging$EndReason applicationPerformanceMetricsLogging$EndReason, final long n, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        final UserSessionEndedEvent mPendingEndEvent = new UserSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, applicationPerformanceMetricsLogging$EndReason, n);
        mPendingEndEvent.setCategory(this.getCategory());
        mPendingEndEvent.setModalView(modalView);
        mPendingEndEvent.setDataContext(dataContext);
        if (this.waitingOnSessionId) {
            this.mPendingEndEvent = mPendingEndEvent;
            return null;
        }
        mPendingEndEvent.setSessionId(this.getId());
        return mPendingEndEvent;
    }
    
    public UserSessionStartedEvent createStartEvent(final ApplicationPerformanceMetricsLogging$Trigger applicationPerformanceMetricsLogging$Trigger, final long n) {
        final UserSessionStartedEvent userSessionStartedEvent = new UserSessionStartedEvent(applicationPerformanceMetricsLogging$Trigger, n);
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
