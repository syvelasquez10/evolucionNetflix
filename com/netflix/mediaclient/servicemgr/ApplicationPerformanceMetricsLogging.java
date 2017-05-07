// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.apm.model.Display;
import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;

public interface ApplicationPerformanceMetricsLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_DISPLAYED", "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_REMOVED", "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED", "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED", "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_STARTED", "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_ENDED", "com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED", "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_ADD_WIDGET", "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_DELETE_WIDGET", "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_STARTED", "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_ENDED" };
    public static final String ASSET_REQUEST_ENDED = "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_ENDED";
    public static final String ASSET_REQUEST_STARTED = "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_STARTED";
    public static final String DATA_REQUEST_ENDED = "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED";
    public static final String DATA_REQUEST_STARTED = "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED";
    public static final String DIALOG_DISPLAYED = "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_DISPLAYED";
    public static final String DIALOG_REMOVED = "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_REMOVED";
    public static final String EXTRA_ASSET_TYPE = "asset_type";
    public static final String EXTRA_DIALOG_ID = "dialog_id";
    public static final String EXTRA_DIALOG_TYPE = "dialog_type";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_FALKOR_PATH_RESULTS = "falkorPathResults";
    public static final String EXTRA_HTTP_RESPONSE = "http_response";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_REQUEST_ID = "request_id";
    public static final String EXTRA_REQUEST_URL = "url";
    public static final String EXTRA_UUID = "uuid";
    public static final String EXTRA_VIEW = "view";
    public static final String PREAPP_ADD_WIDGET = "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_ADD_WIDGET";
    public static final String PREAPP_DELETE_WIDGET = "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_DELETE_WIDGET";
    public static final String SHARED_CONTEXT_SESSION_ENDED = "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_ENDED";
    public static final String SHARED_CONTEXT_SESSION_STARTED = "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_STARTED";
    public static final String UI_MODAL_VIEW_CHANGED = "com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED";
    
    void endAllActiveSessions();
    
    void endApplicationSession();
    
    void endAssetRequestSession(final String p0, final IClientLogging$CompletionReason p1, final HttpResponse p2, final Error p3);
    
    void endDataRequestSession(final String p0, final List<FalkorPathResult> p1, final IClientLogging$CompletionReason p2, final HttpResponse p3, final Error p4);
    
    void endSharedContextSession();
    
    void endUiBrowseStartupSession(final long p0, final boolean p1, final UIError p2);
    
    void endUiModelessViewSession(final String p0);
    
    void endUiStartupSession(final boolean p0, final UIError p1, final PlayerType p2);
    
    void endUserSession(final ApplicationPerformanceMetricsLogging$EndReason p0, final long p1);
    
    void handleConnectivityChange(final Context p0);
    
    boolean handleIntent(final Intent p0, final boolean p1);
    
    boolean isUserSessionExist();
    
    void preappAddWidget(final String p0, final long p1);
    
    void preappDeleteWidget(final String p0, final long p1);
    
    void setDataContext(final DataContext p0);
    
    void startApplicationSession(final boolean p0);
    
    String startAssetRequestSession(final String p0, final IClientLogging$AssetType p1);
    
    boolean startDataRequestSession(final String p0, final String p1);
    
    void startSharedContextSession(final String p0);
    
    void startUiBrowseStartupSession(final long p0);
    
    void startUiModelessViewSession(final boolean p0, final IClientLogging$ModalView p1, final String p2);
    
    void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger p0, final IClientLogging$ModalView p1, final int p2, final String p3, final Map<String, Integer> p4, final Long p5, final Display p6);
    
    void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger p0, final IClientLogging$ModalView p1, final Long p2, final Display p3);
    
    void startUserSession(final ApplicationPerformanceMetricsLogging$Trigger p0);
    
    void startUserSession(final ApplicationPerformanceMetricsLogging$Trigger p0, final long p1);
    
    void uiViewChanged(final boolean p0, final IClientLogging$ModalView p1);
    
    void uiViewChanged(final boolean p0, final IClientLogging$ModalView p1, final long p2);
}
