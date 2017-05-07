// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;

public interface CustomerServiceLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_START", "com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_START", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_QUALITY_CHANGED", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_CONNECTED", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_EXIT", "com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_BACK_TO" };
    public static final String CALL_CONNECTED = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_CONNECTED";
    public static final String CALL_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_ENDED";
    public static final String CALL_SESSION_QUALITY_CHANGED = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_QUALITY_CHANGED";
    public static final String CALL_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_START";
    public static final String CALL_UI_BACK_TO = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_BACK_TO";
    public static final String CALL_UI_EXIT = "com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_EXIT";
    public static final String EXTRA_ACTION = "action";
    public static final String EXTRA_CALL_QUALITY = "call_quality";
    public static final String EXTRA_DIALOG_TYPE = "dialog_type";
    public static final String EXTRA_ENTRY = "entry";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_ORIENTATION = "orientation";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_SOURCE = "source";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_USING = "using";
    public static final String HELP_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_ENDED";
    public static final String HELP_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_START";
    
    void callConnected(final CustomerServiceLogging$CallQuality p0);
    
    void callQualityChanged(final CustomerServiceLogging$CallQuality p0);
    
    void createBackToDialScreenEvent(final IClientLogging$ModalView p0, final Orientation p1, final CustomerServiceLogging$ReturnToDialScreenFrom p2);
    
    void createDialScreenDismissedEvent(final CustomerServiceLogging$Action p0);
    
    void endAllActiveSessions();
    
    void endCustomerSupportCallSession(final IClientLogging$CompletionReason p0, final Error p1);
    
    void endHelpRequestSession(final CustomerServiceLogging$Action p0, final String p1, final IClientLogging$CompletionReason p2, final Error p3);
    
    void startCustomerSupportCallSession();
    
    void startHelpRequestSession(final CustomerServiceLogging$EntryPoint p0);
}
