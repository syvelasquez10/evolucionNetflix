// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend;

import com.netflix.mediaclient.service.logging.suspend.model.ForegroundSessionStartedEvent;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.suspend.model.ForegroundSessionEndedEvent;

public class ForegroundSession extends BaseSuspendSession
{
    public static final String NAME = "foreground";
    
    public ForegroundSessionEndedEvent createEndedEvent() {
        final ForegroundSessionEndedEvent foregroundSessionEndedEvent = new ForegroundSessionEndedEvent(System.currentTimeMillis() - this.mStarted);
        foregroundSessionEndedEvent.setCategory(this.getCategory());
        foregroundSessionEndedEvent.setSessionId(this.mId);
        return foregroundSessionEndedEvent;
    }
    
    public ForegroundSessionStartedEvent createStartEvent(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final DeepLink deepLink) {
        final ForegroundSessionStartedEvent foregroundSessionStartedEvent = new ForegroundSessionStartedEvent(applicationPerformanceMetricsLogging$UiStartupTrigger, deepLink);
        foregroundSessionStartedEvent.setCategory(this.getCategory());
        foregroundSessionStartedEvent.setSessionId(this.mId);
        return foregroundSessionStartedEvent;
    }
    
    @Override
    public String getName() {
        return "foreground";
    }
}
