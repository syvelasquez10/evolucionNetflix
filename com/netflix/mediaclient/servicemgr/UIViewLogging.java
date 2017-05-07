// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.client.model.Error;

public interface UIViewLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED" };
    public static final String COMMAND_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED";
    public static final String COMMAND_START = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_DATA_CONTEXT = "dataContext";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_GUID = "guid";
    public static final String EXTRA_SUCCESS = "success";
    public static final String EXTRA_TRACK_ID = "trackId";
    public static final String EXTRA_VIEW = "view";
    public static final String IMPRESSION = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION";
    public static final String IMPRESSION_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED";
    public static final String IMPRESSION_SESSION_STARTED = "com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED";
    
    void createImpressionEvent(final UIViewCommandName p0, final int p1);
    
    void endCommandSession();
    
    void endImpressionSession(final boolean p0, final Error p1);
    
    void startCommandSession(final UIViewCommandName p0, final IClientLogging.ModalView p1, final DataContext p2);
    
    void startImpressionSession(final IClientLogging.ModalView p0, final String p1);
    
    public enum UIViewCommandName
    {
        actionBarBackButton, 
        actionBarKidsEntry, 
        actionBarKidsExit, 
        backButton, 
        facebookConnectButton, 
        genreKidsEntry, 
        moreButton, 
        slidingMenuClosed, 
        slidingMenuKidsEntry, 
        slidingMenuKidsExit, 
        slidingMenuOpened, 
        socialRecommendButton, 
        upButton;
    }
}
