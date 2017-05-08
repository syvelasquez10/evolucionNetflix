// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.client.model.UIError;

public interface IkoLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_IKO_MODE_START", "com.netflix.mediaclient.intent.action.LOG_IKO_MODE_ENDED", "com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_START", "com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_ENDED", "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_START", "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_END", "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_START", "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_END" };
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_ERROR_CODE = "errorCode";
    public static final String EXTRA_ERROR_SUB_CODE = "errorSubCode";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_VIEW = "view";
    public static final String INTENT_ACTION_IKO_MODE_ENDED = "com.netflix.mediaclient.intent.action.LOG_IKO_MODE_ENDED";
    public static final String INTENT_ACTION_IKO_MODE_START = "com.netflix.mediaclient.intent.action.LOG_IKO_MODE_START";
    public static final String INTENT_ACTION_IKO_MOMENT_ENDED = "com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_ENDED";
    public static final String INTENT_ACTION_IKO_MOMENT_START = "com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_START";
    public static final String INTENT_ACTION_VIDEO_LOAD_END = "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_END";
    public static final String INTENT_ACTION_VIDEO_LOAD_START = "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_START";
    public static final String INTENT_ACTION_VIDEO_PLAYBACK_END = "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_END";
    public static final String INTENT_ACTION_VIDEO_PLAYBACK_START = "com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_START";
    
    void endAllActiveSessions();
    
    void endIkoModeSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endIkoMomentSession(final IClientLogging$CompletionReason p0, final UIError p1, final String p2, final String p3, final int p4, final String p5);
    
    void endIkoVideoLoadSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endIkoVideoPlaybackSession(final IClientLogging$CompletionReason p0, final int p1, final int p2);
    
    void setDataContext(final DataContext p0);
    
    void startIkoModeSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startIkoMomentSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startIkoVideoLoadSession(final String p0);
    
    void startIkoVideoPlaybackSession(final String p0);
}
