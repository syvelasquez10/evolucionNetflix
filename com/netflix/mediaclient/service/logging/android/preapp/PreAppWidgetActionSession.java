// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.preapp;

import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetActionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.uiaction.BaseUIActionSession;

public class PreAppWidgetActionSession extends BaseUIActionSession
{
    public static final String PREAPP_CATEGORY = "preAppAndroid";
    private static final String SESSION_NAME = "androidWidgetCommand";
    private String widgetActionData;
    private String widgetLogData;
    
    public PreAppWidgetActionSession(final UserActionLogging$CommandName userActionLogging$CommandName, final String widgetLogData, final String widgetActionData) {
        super(userActionLogging$CommandName, null);
        this.widgetLogData = widgetLogData;
        this.widgetActionData = widgetActionData;
    }
    
    public PreAppWidgetActionEndedEvent createEndedEvent(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final PreAppWidgetActionEndedEvent preAppWidgetActionEndedEvent = new PreAppWidgetActionEndedEvent("androidWidgetCommand", this.mId, System.currentTimeMillis() - this.mStarted, null, this.mAction, clientLogging$CompletionReason, uiError, this.widgetLogData, this.widgetActionData);
        preAppWidgetActionEndedEvent.setCategory(this.getCategory());
        preAppWidgetActionEndedEvent.setSessionId(this.mId);
        return preAppWidgetActionEndedEvent;
    }
    
    @Override
    public String getCategory() {
        return "preAppAndroid";
    }
    
    @Override
    public String getName() {
        return "androidWidgetCommand";
    }
}
