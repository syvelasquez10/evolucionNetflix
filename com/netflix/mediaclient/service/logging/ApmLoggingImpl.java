// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.model.UIDataRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.apm.model.UIAssetRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;

class ApmLoggingImpl implements ApplicationPerformanceMetricsLogging
{
    private static final String TAG = "nf_log";
    private ApplicationSession mApplicationSession;
    private Map<String, UIAssetRequestSession> mAssetRequests;
    private IClientLogging.ModalView mCurrentUiView;
    private DataContext mDataContext;
    private Map<String, UIDataRequestSession> mDataRequests;
    private Map<String, UIModelessViewSession> mDialogSessions;
    private EventHandler mEventHandler;
    private String mNrdpLogSessionId;
    private UIBrowseStartupSession mUIBrowseStartupSession;
    private UIStartupSession mUIStartupSession;
    private UserSession mUserSession;
    
    ApmLoggingImpl(final EventHandler mEventHandler, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        this.mDataRequests = Collections.synchronizedMap(new HashMap<String, UIDataRequestSession>());
        this.mAssetRequests = Collections.synchronizedMap(new HashMap<String, UIAssetRequestSession>());
        this.mDialogSessions = Collections.synchronizedMap(new HashMap<String, UIModelessViewSession>());
        this.mEventHandler = mEventHandler;
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    private void sendUserSessionEvent(final UserSessionStartedEvent userSessionStartedEvent, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        this.populateEvent(userSessionStartedEvent, dataContext, modalView);
        this.mEventHandler.post(userSessionStartedEvent);
        Log.d("nf_log", "User session start event posted.");
    }
    
    @Override
    public void endApplicationSession() {
        Log.d("nf_log", "Application session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "Application session ended");
                if (ApmLoggingImpl.this.mApplicationSession == null) {
                    Log.w("nf_log", "Application session does NOT exist!");
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mApplicationSession);
                Log.d("nf_log", "Application session end event posting...");
                final AppSessionEndedEvent endedEvent = ApmLoggingImpl.this.mApplicationSession.createEndedEvent();
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                ApmLoggingImpl.this.mApplicationSession = null;
                Log.d("nf_log", "Application session end event posted.");
            }
        });
        Log.d("nf_log", "Application session end done.");
    }
    
    @Override
    public void endAssetRequestSession(final String s, final IClientLogging.CompletionReason completionReason, final HttpResponse httpResponse, final Error error) {
        Log.d("nf_log", "Asset request session ended and executed in backgound");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                final UIAssetRequestSession uiAssetRequestSession = ApmLoggingImpl.this.mAssetRequests.remove(s);
                if (uiAssetRequestSession == null) {
                    if (Log.isLoggable("nf_log", 6)) {
                        Log.e("nf_log", "UI Asset request session NOT found for URL: " + s + ". Unable to post event!");
                    }
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(uiAssetRequestSession);
                Log.d("nf_log", "Asset request session end event posting...");
                final UIAssetRequestSessionEndedEvent endedEvent = uiAssetRequestSession.createEndedEvent(completionReason, httpResponse, error);
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                Log.d("nf_log", "Asset request session end event posted.");
            }
        });
        Log.d("nf_log", "Asset request session end done.");
    }
    
    @Override
    public void endDataRequestSession(final String s, final List<FalcorPathResult> list, final IClientLogging.CompletionReason completionReason, final HttpResponse httpResponse, final Error error) {
        Log.d("nf_log", "Data request session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                final UIDataRequestSession uiDataRequestSession = ApmLoggingImpl.this.mDataRequests.remove(s);
                if (uiDataRequestSession == null) {
                    if (Log.isLoggable("nf_log", 6)) {
                        Log.e("nf_log", "UI data request session NOT found for '" + s + "'. Unable to post event.");
                    }
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(uiDataRequestSession);
                Log.d("nf_log", "Data request session end event posting...");
                final UIDataRequestSessionEndedEvent endedEvent = uiDataRequestSession.createEndedEvent(completionReason, httpResponse, error);
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                Log.d("nf_log", "Data request session end event posted.");
            }
        });
        Log.d("nf_log", "Data request session end done.");
    }
    
    @Override
    public void endUiBrowseStartupSession(final long n, final boolean b, final UIError uiError) {
        Log.d("nf_log", "UI startup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "UI browse startup session ended");
                if (ApmLoggingImpl.this.mUIBrowseStartupSession == null) {
                    Log.w("nf_log", "UI browse startup session does NOT exist!");
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUIBrowseStartupSession);
                Log.d("nf_log", "UI browse startup session end event posting...");
                final UIBrowseStartupSessionEndedEvent endedEvent = ApmLoggingImpl.this.mUIBrowseStartupSession.createEndedEvent(n, b, uiError);
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                ApmLoggingImpl.this.mUIBrowseStartupSession = null;
                Log.d("nf_log", "UI browse startup session end event posted.");
            }
        });
        Log.d("nf_log", "UI browse startup session end done.");
    }
    
    @Override
    public void endUiModelessViewSession(final String s) {
        Log.d("nf_log", "UI modeless session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "UI modeless session ended");
                final UIModelessViewSession uiModelessViewSession = ApmLoggingImpl.this.mDialogSessions.get(s);
                if (uiModelessViewSession == null) {
                    Log.w("nf_log", "UI modeless session does NOT exist for request ID:" + s);
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(uiModelessViewSession);
                Log.d("nf_log", "UI modeless session end event posting...");
                final UIModelessViewSessionEndedEvent endedEvent = uiModelessViewSession.createEndedEvent();
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                Log.d("nf_log", "UI modeless session end event posted.");
            }
        });
        Log.d("nf_log", "UI modeless session end done.");
    }
    
    @Override
    public void endUiStartupSession(final boolean b, final UIError uiError) {
        if (this.mUIStartupSession == null) {
            return;
        }
        Log.d("nf_log", "UI startup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "UI startup session ended");
                if (ApmLoggingImpl.this.mUIStartupSession == null) {
                    Log.w("nf_log", "UI startup session does NOT exist!");
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUIStartupSession);
                Log.d("nf_log", "UI startup session end event posting...");
                final UIStartupSessionEndedEvent endedEvent = ApmLoggingImpl.this.mUIStartupSession.createEndedEvent(b, uiError);
                ApmLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                ApmLoggingImpl.this.mUIStartupSession = null;
                Log.d("nf_log", "UI startup session end event posted.");
            }
        });
        Log.d("nf_log", "UI startup session end done.");
    }
    
    @Override
    public void endUserSession(final EndReason endReason, final long n) {
        Log.d("nf_log", "User session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "User session ended");
                if (ApmLoggingImpl.this.mUserSession == null) {
                    Log.w("nf_log", "User session does NOT exist!");
                    return;
                }
                final UserSessionEndedEvent endedEvent = ApmLoggingImpl.this.mUserSession.createEndedEvent(endReason, n, this.val$dataContext, this.val$ui);
                if (endedEvent == null) {
                    Log.d("nf_log", "User session still waits on session id, do not post at this time.");
                    return;
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUserSession);
                Log.d("nf_log", "User session end event posting...");
                ApmLoggingImpl.this.mEventHandler.post(endedEvent);
                ApmLoggingImpl.this.mUserSession = null;
                Log.d("nf_log", "User session end event posted.");
            }
        });
        Log.d("nf_log", "User session end done.");
    }
    
    IClientLogging.ModalView getCurrentUiView() {
        return this.mCurrentUiView;
    }
    
    public void handleAssetRequestEnded(final Intent intent) {
        Log.d("nf_log", "Handle asset request ended intent. Running it on main thread.");
        final String stringExtra = intent.getStringExtra("url");
        final IClientLogging.CompletionReason value = IClientLogging.CompletionReason.valueOf(intent.getStringExtra("reason"));
        Error instance = null;
        final HttpResponse httpResponse = null;
        while (true) {
            try {
                instance = Error.createInstance(intent.getStringExtra("error"));
                final HttpResponse instance2 = HttpResponse.createInstance(intent.getStringExtra("http_response"));
                instance = instance;
                this.endAssetRequestSession(stringExtra, value, instance2, instance);
            }
            catch (Exception ex) {
                Log.e("nf_log", "Failed to parse properties", ex);
                final HttpResponse instance2 = httpResponse;
                continue;
            }
            break;
        }
    }
    
    public void handleAssetRequestStarted(final Intent intent) {
        Log.d("nf_log", "Handle asset request started intent. Running it on main thread.");
        this.startAssetRequestSession(intent.getStringExtra("url"), IClientLogging.AssetType.valueOf(intent.getStringExtra("asset_type")));
    }
    
    public void handleDataRequestEnded(final Intent intent) {
        Log.d("nf_log", "Handle data request ended intent. Running it on main thread.");
        final String stringExtra = intent.getStringExtra("request_id");
        final IClientLogging.CompletionReason value = IClientLogging.CompletionReason.valueOf(intent.getStringExtra("reason"));
        Error error = null;
        final HttpResponse httpResponse = null;
        final List<FalcorPathResult> list = null;
        HttpResponse instance = httpResponse;
        while (true) {
            try {
                final UIError instance2 = Error.createInstance(intent.getStringExtra("error"));
                instance = httpResponse;
                error = instance2;
                instance = HttpResponse.createInstance(intent.getStringExtra("http_response"));
                error = instance2;
                final List<FalcorPathResult> list2 = FalcorPathResult.createList(intent.getStringExtra("falcorPathResults"));
                error = instance2;
                instance = instance;
                this.endDataRequestSession(stringExtra, list2, value, instance, error);
            }
            catch (Exception ex) {
                Log.e("nf_log", "Failed to parse properties", ex);
                final List<FalcorPathResult> list2 = list;
                continue;
            }
            break;
        }
    }
    
    public void handleDataRequestStarted(final Intent intent) {
        Log.d("nf_log", "Handle data request started intent. Running it on main thread.");
        this.startDataRequestSession(intent.getStringExtra("url"), intent.getStringExtra("request_id"));
    }
    
    public void handleDialogDisplayed(final Intent intent, final boolean b) {
        this.startUiModelessViewSession(b, IClientLogging.ModalView.valueOf(intent.getStringExtra("dialog_type")), intent.getStringExtra("dialog_id"));
    }
    
    public void handleDialogRemoved(final Intent intent) {
        this.endUiModelessViewSession(intent.getStringExtra("dialog_id"));
    }
    
    @Override
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_STARTED".equals(action)) {
            Log.d("nf_log", "ASSET_REQUEST_STARTED");
            this.handleAssetRequestStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_ENDED".equals(action)) {
            Log.d("nf_log", "ASSET_REQUEST_ENDED");
            this.handleAssetRequestEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED".equals(action)) {
            Log.d("nf_log", "DATA_REQUEST_STARTED");
            this.handleDataRequestStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED".equals(action)) {
            Log.d("nf_log", "DATA_REQUEST_ENDED");
            this.handleDataRequestEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_DISPLAYED".equals(action)) {
            Log.d("nf_log", "DIALOG_DISPLAYED");
            this.handleDialogDisplayed(intent, b);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_REMOVED".equals(action)) {
            Log.d("nf_log", "DIALOG_REMOVED");
            this.handleDialogRemoved(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED".equals(action)) {
            Log.d("nf_log", "UI_MODAL_VIEW_CHANGED");
            this.handleViewChanged(intent, b);
            return true;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    public void handleViewChanged(final Intent intent, final boolean b) {
        this.uiViewChanged(b, IClientLogging.ModalView.valueOf(intent.getStringExtra("view")));
    }
    
    @Override
    public boolean isUserSessionExist() {
        return this.mUserSession != null;
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startApplicationSession(final boolean b) {
        Log.d("nf_log", "Application session started and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "Application session created");
                final String applicationId = ApmLoggingImpl.this.mEventHandler.getApplicationId();
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "startApplicationSession: Application id " + applicationId);
                }
                final long long1 = Long.parseLong(applicationId);
                ApmLoggingImpl.this.mApplicationSession = new ApplicationSession();
                ApmLoggingImpl.this.mApplicationSession.setId(long1);
                ApmLoggingImpl.this.mEventHandler.addSession(ApmLoggingImpl.this.mApplicationSession);
                Log.d("nf_log", "Application session start event posting...");
                final AppSessionStartedEvent startEvent = ApmLoggingImpl.this.mApplicationSession.createStartEvent(b);
                ApmLoggingImpl.this.populateEvent(startEvent, this.val$dataContext, this.val$ui);
                startEvent.setSessionId(new DeviceUniqueId(long1));
                ApmLoggingImpl.this.mEventHandler.post(startEvent);
                Log.d("nf_log", "Application session start event posted.");
            }
        });
        Log.d("nf_log", "Application session start done.");
    }
    
    @Override
    public String startAssetRequestSession(final String s, final IClientLogging.AssetType assetType) {
        if (this.mAssetRequests.get(s) != null) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "UI Asset request session already in progress for URL: " + s);
            }
            return null;
        }
        if (!this.mEventHandler.canSendEvent("uiQOE", "uiAssetRequest")) {
            Log.d("nf_log", "Asset request started. Asset request tracking is not enabled. Done.");
            return null;
        }
        Log.d("nf_log", "Asset request session created");
        final UIAssetRequestSession uiAssetRequestSession = new UIAssetRequestSession(s, assetType);
        this.mEventHandler.addSession(uiAssetRequestSession);
        this.mAssetRequests.put(s, uiAssetRequestSession);
        Log.d("nf_log", "Asset session start done.");
        return s;
    }
    
    @Override
    public boolean startDataRequestSession(final String s, final String s2) {
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_log", "Data request session: url is empty, can not create session!");
            return false;
        }
        if (StringUtils.isEmpty(s2)) {
            Log.e("nf_log", "Data request session: requestId is empty, can not create session!");
            return false;
        }
        if (!this.mEventHandler.canSendEvent("uiQOE", "uiDataRequest")) {
            Log.d("nf_log", "Data request started. Data request tracking is not enabled. Done.");
            return false;
        }
        final UIDataRequestSession uiDataRequestSession = new UIDataRequestSession(s, s2);
        this.mEventHandler.addSession(uiDataRequestSession);
        this.mDataRequests.put(s2, uiDataRequestSession);
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "UI data request session added for '" + s2 + "'");
        }
        Log.d("nf_log", "Data session start done.");
        return true;
    }
    
    @Override
    public void startUiBrowseStartupSession(final long started) {
        Log.d("nf_log", "UI browse startup session started.");
        final UIBrowseStartupSession muiBrowseStartupSession = new UIBrowseStartupSession();
        muiBrowseStartupSession.setStarted(started);
        this.mEventHandler.removeSession(this.mUIBrowseStartupSession);
        this.mEventHandler.addSession(muiBrowseStartupSession);
        this.mUIBrowseStartupSession = muiBrowseStartupSession;
    }
    
    @Override
    public void startUiModelessViewSession(final boolean b, final IClientLogging.ModalView modalView, final String s) {
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "UI modeless session started and posted to executor " + modalView + ". In portrait " + b + ". Dialog id: " + s);
        }
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "UI modeless session created for " + modalView + ". In portrait " + b + ". Session ID: " + s);
                }
                final UIModelessViewSession uiModelessViewSession = new UIModelessViewSession();
                ApmLoggingImpl.this.mDialogSessions.put(s, uiModelessViewSession);
                ApmLoggingImpl.this.mEventHandler.addSession(uiModelessViewSession);
                Log.d("nf_log", "UI modeless session start event posting...");
                final UIModelessViewSessionStartedEvent startEvent = uiModelessViewSession.createStartEvent(b, modalView);
                ApmLoggingImpl.this.populateEvent(startEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(startEvent);
                Log.d("nf_log", "UI modeless session start event posted.");
            }
        });
        Log.d("nf_log", "User modeless session start done.");
    }
    
    @Override
    public void startUiStartupSession(final UiStartupTrigger uiStartupTrigger, final IClientLogging.ModalView modalView, final int n, final String s, final Map<String, Integer> map, final Long n2) {
        Log.d("nf_log", "UI startup session started and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_log", "UI startup session created");
                final UIStartupSession uiStartupSession = new UIStartupSession(uiStartupTrigger, modalView, n, s, map);
                if (n2 != null) {
                    uiStartupSession.setStarted(n2);
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUIStartupSession);
                ApmLoggingImpl.this.mUIStartupSession = uiStartupSession;
                ApmLoggingImpl.this.mEventHandler.addSession(uiStartupSession);
            }
        });
        Log.d("nf_log", "UI startup start done.");
    }
    
    @Override
    public void startUiStartupSession(final UiStartupTrigger uiStartupTrigger, final IClientLogging.ModalView modalView, final Long n) {
        Log.d("nf_log", "UI startup session started and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            @Override
            public void run() {
                if (ApmLoggingImpl.this.mUIStartupSession != null) {
                    Log.e("nf_log", "UI startup session already exist, we do not recreate it!");
                    return;
                }
                Log.d("nf_log", "UI startup session created");
                final UIStartupSession uiStartupSession = new UIStartupSession(uiStartupTrigger, modalView);
                if (n != null) {
                    uiStartupSession.setStarted(n);
                }
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUIStartupSession);
                ApmLoggingImpl.this.mUIStartupSession = uiStartupSession;
                ApmLoggingImpl.this.mEventHandler.addSession(uiStartupSession);
            }
        });
        Log.d("nf_log", "UI startup start done.");
    }
    
    @Override
    public void startUserSession(final Trigger trigger) {
        this.startUserSession(trigger, 0L);
    }
    
    @Override
    public void startUserSession(final Trigger trigger, final long n) {
        if (this.mUserSession != null) {
            Log.d("nf_log", "User session already exist");
            return;
        }
        Log.d("nf_log", "User session started and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                if (ApmLoggingImpl.this.mUserSession != null) {
                    Log.d("nf_log", "User session already exist");
                    return;
                }
                Log.d("nf_log", "User session created");
                final UserSession userSession = new UserSession();
                Log.d("nf_log", "User session start event posting...");
                final UserSessionStartedEvent startEvent = userSession.createStartEvent(trigger, n);
                final String userSessionId = ApmLoggingImpl.this.mEventHandler.getUserSessionId();
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "startUserSession: Current nrdp.log.sessionid " + userSessionId);
                    Log.d("nf_log", "startUserSession: Last used nrdp.log.sessionid " + ApmLoggingImpl.this.mNrdpLogSessionId);
                }
                final com.netflix.mediaclient.javabridge.ui.Log.ResetSessionIdCallback resetSessionIdCallback = new com.netflix.mediaclient.javabridge.ui.Log.ResetSessionIdCallback() {
                    @Override
                    public void sessionCreated(final String s) {
                        ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUserSession);
                        ApmLoggingImpl.this.mUserSession = userSession;
                        userSession.setId(Long.valueOf(s));
                        ApmLoggingImpl.this.mNrdpLogSessionId = s;
                        ApmLoggingImpl.this.mEventHandler.addSession(ApmLoggingImpl.this.mUserSession);
                        final DeviceUniqueId id = userSession.getId();
                        startEvent.setSessionId(id);
                        ApmLoggingImpl.this.sendUserSessionEvent(startEvent, Runnable.this.val$dataContext, Runnable.this.val$ui);
                        final UserSessionEndedEvent pendingEndEvent = userSession.getPendingEndEvent();
                        if (pendingEndEvent != null) {
                            pendingEndEvent.setSessionId(id);
                            ApmLoggingImpl.this.mEventHandler.post(pendingEndEvent);
                            com.netflix.mediaclient.Log.d("nf_log", "Pending user session end event posted.");
                        }
                    }
                };
                if (ApmLoggingImpl.this.mNrdpLogSessionId != null) {
                    if (StringUtils.isEmpty(userSessionId)) {
                        Log.e("nf_log", "User session is not yet created. This should NOT happen!");
                    }
                    else if (ApmLoggingImpl.this.mNrdpLogSessionId.equals(userSessionId)) {
                        Log.e("nf_log", "User session is not yet created. This should NOT happen!");
                    }
                    else {
                        Log.w("nf_log", "Known used and current user session ids are NOT same, this should not happen");
                    }
                    ApmLoggingImpl.this.mEventHandler.createUserSession(resetSessionIdCallback);
                    return;
                }
                Log.d("nf_log", "We never used nrdp.log.sessionid, use it now if user session exist in nrdp");
                if (StringUtils.isEmpty(userSessionId)) {
                    Log.e("nf_log", "User session is not yet created. This should NOT happen!");
                    ApmLoggingImpl.this.mEventHandler.createUserSession(resetSessionIdCallback);
                    return;
                }
                Log.d("nf_log", "User session is created. Set it to event and post event");
                ApmLoggingImpl.this.mEventHandler.removeSession(ApmLoggingImpl.this.mUserSession);
                ApmLoggingImpl.this.mUserSession = userSession;
                userSession.setId(Long.valueOf(userSessionId));
                ApmLoggingImpl.this.mNrdpLogSessionId = userSessionId;
                ApmLoggingImpl.this.mEventHandler.addSession(ApmLoggingImpl.this.mUserSession);
                startEvent.setSessionId(userSession.getId());
                ApmLoggingImpl.this.sendUserSessionEvent(startEvent, this.val$dataContext, this.val$ui);
            }
        });
        Log.d("nf_log", "User session start done.");
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging.ModalView mCurrentUiView) {
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "UI view changed and posted to executor " + mCurrentUiView);
        }
        final DataContext mDataContext = this.mDataContext;
        final IClientLogging.ModalView mCurrentUiView2 = this.mCurrentUiView;
        this.mCurrentUiView = mCurrentUiView;
        this.mEventHandler.executeInBackground(new Runnable() {
            @Override
            public void run() {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "UI view changed " + mCurrentUiView);
                }
                final UIModalViewChangedEvent uiModalViewChangedEvent = new UIModalViewChangedEvent(b, mCurrentUiView);
                ApmLoggingImpl.this.populateEvent(uiModalViewChangedEvent, mDataContext, mCurrentUiView2);
                ApmLoggingImpl.this.mEventHandler.post(uiModalViewChangedEvent);
                Log.d("nf_log", "UI view changed event posted.");
            }
        });
        Log.d("nf_log", "UI view changed done.");
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging.ModalView modalView, final long n) {
        Log.d("nf_log", "UI view changed and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = ApmLoggingImpl.this.mDataContext;
            final /* synthetic */ IClientLogging.ModalView val$ui = ApmLoggingImpl.this.mCurrentUiView;
            
            @Override
            public void run() {
                Log.d("nf_log", "UI view changed");
                final UIModalViewChangedEvent uiModalViewChangedEvent = new UIModalViewChangedEvent(b, modalView);
                uiModalViewChangedEvent.setTime(n);
                ApmLoggingImpl.this.populateEvent(uiModalViewChangedEvent, this.val$dataContext, this.val$ui);
                ApmLoggingImpl.this.mEventHandler.post(uiModalViewChangedEvent);
                Log.d("nf_log", "UI modeless session end event posted.");
            }
        });
        Log.d("nf_log", "UI modeless session end done.");
    }
}
