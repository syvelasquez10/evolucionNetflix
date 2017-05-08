// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.client.model.UIError;

public interface OfflineLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_ENDED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_ENDED" };
    public static final String ADD_CACHED_VIDEO_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_ENDED";
    public static final String ADD_CACHED_VIDEO_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_STARTED";
    public static final String CACHED_PLAY_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_ENDED";
    public static final String CACHED_PLAY_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_STARTED";
    public static final String DOWNLOAD_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_ENDED";
    public static final String DOWNLOAD_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_STARTED";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_DXID = "dxid";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_LOGICAL_END = "logicalEnd";
    public static final String EXTRA_LOGICAL_START = "logicalStart";
    public static final String EXTRA_OXID = "oxid";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_RUNTIME = "runtime";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_VIDEOID = "videoid";
    public static final String EXTRA_VIEW = "view";
    public static final String REMOVE_CACHED_VIDEO_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_ENDED";
    public static final String REMOVE_CACHED_VIDEO_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_STARTED";
    
    void endAddCachedVideoSession(final String p0, final IClientLogging$ModalView p1, final IClientLogging$CompletionReason p2, final UIError p3);
    
    void endAllActiveSessions();
    
    void endCachedPlaySession(final IClientLogging$ModalView p0, final IClientLogging$CompletionReason p1, final UIError p2);
    
    void endDownloadSession(final String p0, final IClientLogging$ModalView p1, final IClientLogging$CompletionReason p2, final UIError p3);
    
    void endRemoveCachedVideoSession(final String p0, final IClientLogging$ModalView p1, final IClientLogging$CompletionReason p2, final UIError p3);
    
    void setDataContext(final DataContext p0);
    
    void startAddCachedVideoSession(final String p0, final UserActionLogging$CommandName p1);
    
    void startCachedPlaySession(final UserActionLogging$CommandName p0, final String p1, final String p2, final int p3, final int p4, final int p5);
    
    void startDownloadSession(final String p0, final UserActionLogging$CommandName p1);
    
    void startRemoveCachedVideoSession(final String p0, final UserActionLogging$CommandName p1);
}
