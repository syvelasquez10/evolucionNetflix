// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.service.logging.client.model.Error;

public interface UIViewLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED", "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_START", "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_START", "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_START", "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_IKO_NOTIFICATION_IMPRESSION_START", "com.netflix.mediaclient.intent.action.LOG_IKO_NOTIFICATION_IMPRESSION_ENDED" };
    public static final String COMMAND_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED";
    public static final String COMMAND_START = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_DATA_CONTEXT = "dataContext";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_GUID = "guid";
    public static final String EXTRA_INPUT_METHOD = "inputMethod";
    public static final String EXTRA_INPUT_VALUE = "inputValue";
    public static final String EXTRA_MODEL = "model";
    public static final String EXTRA_SUCCESS = "success";
    public static final String EXTRA_TRACK_ID = "trackId";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_VIEW = "view";
    public static final String IKO_NOTIFICATION_IMPRESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_IKO_NOTIFICATION_IMPRESSION_ENDED";
    public static final String IKO_NOTIFICATION_IMPRESSION_START = "com.netflix.mediaclient.intent.action.LOG_IKO_NOTIFICATION_IMPRESSION_START";
    public static final String IMPRESSION = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION";
    public static final String IMPRESSION_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED";
    public static final String IMPRESSION_SESSION_STARTED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED";
    public static final String LEFT_PANEL_VIEW_COMMAND_ENDED = "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_ENDED";
    public static final String LEFT_PANEL_VIEW_COMMAND_START = "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_START";
    public static final String LEFT_PANEL_VIEW_IMPRESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_ENDED";
    public static final String LEFT_PANEL_VIEW_IMPRESSION_START = "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_START";
    public static final String NOTIFICATION_IMPRESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_ENDED";
    public static final String NOTIFICATION_IMPRESSION_START = "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_START";
    
    void createImpressionEvent(final UIViewLogging$UIViewCommandName p0, final int p1);
    
    void endAllActiveSessions();
    
    void endCommandSession();
    
    void endImpressionSession(final boolean p0, final Error p1);
    
    void startCommandSession(final UIViewLogging$UIViewCommandName p0, final IClientLogging$ModalView p1, final CommandEndedEvent$InputMethod p2, final DataContext p3, final String p4, final JSONObject p5);
    
    void startImpressionSession(final IClientLogging$ModalView p0, final String p1);
}
