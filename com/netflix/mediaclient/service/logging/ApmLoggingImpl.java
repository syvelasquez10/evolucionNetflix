// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.apm.model.UIModalViewChangedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$ResetSessionIdCallback;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionStartedEvent;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdSetListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetInstallEvent$WidgetInstallAction;
import android.content.Context;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIModelessViewSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIDataRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.UIAssetRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionEndedEvent;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.Collection;
import java.util.HashSet;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.WiredNetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.WifiNetworkConnectionSession;
import com.netflix.mediaclient.service.logging.apm.MobileNetworkConnectionSession;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.apm.model.AppSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.logging.apm.UIModelessViewSession;
import com.netflix.mediaclient.service.logging.apm.UIDataRequestSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.apm.UIAssetRequestSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.apm.ApplicationSession;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;

class ApmLoggingImpl implements ApplicationPerformanceMetricsLogging
{
    private static final String TAG = "nf_log_apm";
    private ApplicationSession mApplicationSession;
    private final Map<String, UIAssetRequestSession> mAssetRequests;
    private IClientLogging$ModalView mCurrentUiView;
    private DataContext mDataContext;
    private final Map<String, UIDataRequestSession> mDataRequests;
    private final Map<String, UIModelessViewSession> mDialogSessions;
    private final EventHandler mEventHandler;
    private AtomicBoolean mLogoutInProgress;
    private final Map<String, NetworkConnectionSession> mNetworkConnectionSessions;
    private final ApmLoggingImpl$NetworkStatusMonitor mNetworkStatusMonitor;
    private String mNrdpLogSessionId;
    private SharedContextSession mSharedContextSession;
    private UIBrowseStartupSession mUIBrowseStartupSession;
    private UIStartupSession mUIStartupSession;
    private UserSession mUserSession;
    
    ApmLoggingImpl(final EventHandler mEventHandler) {
        this.mDataRequests = Collections.synchronizedMap(new HashMap<String, UIDataRequestSession>());
        this.mAssetRequests = Collections.synchronizedMap(new HashMap<String, UIAssetRequestSession>());
        this.mDialogSessions = Collections.synchronizedMap(new HashMap<String, UIModelessViewSession>());
        this.mNetworkConnectionSessions = Collections.synchronizedMap(new HashMap<String, NetworkConnectionSession>());
        this.mNetworkStatusMonitor = new ApmLoggingImpl$NetworkStatusMonitor(this, null);
        this.mLogoutInProgress = new AtomicBoolean(false);
        this.mEventHandler = mEventHandler;
    }
    
    private void doStartApplicationSession(final boolean b, final String s, long long1) {
        long1 = Long.parseLong(s);
        (this.mApplicationSession = new ApplicationSession()).setId(long1);
        this.mEventHandler.addSession(this.mApplicationSession);
        Log.d("nf_log_apm", "Application session start event posting...");
        final AppSessionStartedEvent startEvent = this.mApplicationSession.createStartEvent(b);
        this.populateEvent(startEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(startEvent);
        Log.d("nf_log_apm", "Application session start event posted.");
    }
    
    private void endMobileNetworkConnectivitySession() {
        this.handleEndedNetworkConnectionSession("mobileNetworkConnection", this.mDataContext, this.mCurrentUiView);
    }
    
    private void endWifiNetworkConnectivitySession() {
        this.handleEndedNetworkConnectionSession("wifiNetworkConnection", this.mDataContext, this.mCurrentUiView);
    }
    
    private void endWiredNetworkConnectivitySession() {
        this.handleEndedNetworkConnectionSession("wiredNetworkConnection", this.mDataContext, this.mCurrentUiView);
    }
    
    private void handleAssetRequestEnded(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: ldc             "nf_log_apm"
        //     4: ldc             "Handle asset request ended intent. Running it on main thread."
        //     6: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     9: pop            
        //    10: aload_1        
        //    11: ldc             "url"
        //    13: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    16: astore          4
        //    18: aload_1        
        //    19: ldc             "reason"
        //    21: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    24: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    27: astore          5
        //    29: aload_1        
        //    30: ldc             "error"
        //    32: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    35: invokestatic    com/netflix/mediaclient/service/logging/client/model/Error.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    38: astore_2       
        //    39: aload_1        
        //    40: ldc             "http_response"
        //    42: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    45: invokestatic    com/netflix/mediaclient/service/logging/client/model/HttpResponse.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;
        //    48: astore_1       
        //    49: aload_0        
        //    50: aload           4
        //    52: aload           5
        //    54: aload_1        
        //    55: aload_2        
        //    56: invokevirtual   com/netflix/mediaclient/service/logging/ApmLoggingImpl.endAssetRequestSession:(Ljava/lang/String;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;Lcom/netflix/mediaclient/service/logging/client/model/Error;)V
        //    59: return         
        //    60: astore_1       
        //    61: aconst_null    
        //    62: astore_2       
        //    63: ldc             "nf_log_apm"
        //    65: ldc             "Failed to parse properties"
        //    67: aload_1        
        //    68: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    71: pop            
        //    72: aload_3        
        //    73: astore_1       
        //    74: goto            49
        //    77: astore_1       
        //    78: goto            63
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  29     39     60     63     Ljava/lang/Exception;
        //  39     49     77     81     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void handleAssetRequestStarted(final Intent intent) {
        Log.d("nf_log_apm", "Handle asset request started intent. Running it on main thread.");
        this.startAssetRequestSession(intent.getStringExtra("url"), IClientLogging$AssetType.valueOf(intent.getStringExtra("asset_type")));
    }
    
    private void handleDataRequestEnded(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: ldc             "nf_log_apm"
        //     5: ldc             "Handle data request ended intent. Running it on main thread."
        //     7: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    10: pop            
        //    11: aload_1        
        //    12: ldc             "request_id"
        //    14: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    17: astore          6
        //    19: aload_1        
        //    20: ldc             "reason"
        //    22: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    25: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    28: astore          7
        //    30: aload_1        
        //    31: ldc             "error"
        //    33: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    36: invokestatic    com/netflix/mediaclient/service/logging/client/model/Error.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    39: astore_2       
        //    40: aload_1        
        //    41: ldc             "http_response"
        //    43: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    46: invokestatic    com/netflix/mediaclient/service/logging/client/model/HttpResponse.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;
        //    49: astore_3       
        //    50: aload_1        
        //    51: ldc_w           "falkorPathResults"
        //    54: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    57: invokestatic    com/netflix/mediaclient/service/logging/client/model/FalkorPathResult.createList:(Ljava/lang/String;)Ljava/util/List;
        //    60: astore_1       
        //    61: aload_3        
        //    62: astore          4
        //    64: aload_1        
        //    65: astore_3       
        //    66: aload_0        
        //    67: aload           6
        //    69: aload_3        
        //    70: aload           7
        //    72: aload           4
        //    74: aload_2        
        //    75: invokevirtual   com/netflix/mediaclient/service/logging/ApmLoggingImpl.endDataRequestSession:(Ljava/lang/String;Ljava/util/List;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;Lcom/netflix/mediaclient/service/logging/client/model/Error;)V
        //    78: return         
        //    79: astore          4
        //    81: aconst_null    
        //    82: astore_1       
        //    83: aconst_null    
        //    84: astore_2       
        //    85: ldc             "nf_log_apm"
        //    87: ldc             "Failed to parse properties"
        //    89: aload           4
        //    91: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    94: pop            
        //    95: aload           5
        //    97: astore_3       
        //    98: aload_1        
        //    99: astore          4
        //   101: goto            66
        //   104: astore          4
        //   106: aconst_null    
        //   107: astore_1       
        //   108: goto            85
        //   111: astore          4
        //   113: aload_3        
        //   114: astore_1       
        //   115: goto            85
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     40     79     85     Ljava/lang/Exception;
        //  40     50     104    111    Ljava/lang/Exception;
        //  50     61     111    118    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 65, Size: 65
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void handleDataRequestStarted(final Intent intent) {
        Log.d("nf_log_apm", "Handle data request started intent. Running it on main thread.");
        this.startDataRequestSession(intent.getStringExtra("url"), intent.getStringExtra("request_id"));
    }
    
    private void handleDialogDisplayed(final Intent intent, final boolean b) {
        Log.d("nf_log_apm", "DIALOG_DISPLAYED");
        this.startUiModelessViewSession(b, IClientLogging$ModalView.valueOf(intent.getStringExtra("dialog_type")), intent.getStringExtra("dialog_id"));
    }
    
    private void handleDialogRemoved(final Intent intent) {
        Log.d("nf_log_apm", "DIALOG_REMOVED");
        this.endUiModelessViewSession(intent.getStringExtra("dialog_id"));
    }
    
    private void handleEndedNetworkConnectionSession(final String s, final DataContext dataContext, final IClientLogging$ModalView clientLogging$ModalView) {
        final NetworkConnectionSession networkConnectionSession = this.mNetworkConnectionSessions.get(s);
        if (networkConnectionSession != null) {
            if (Log.isLoggable()) {
                Log.d("nf_log_apm", "Terminated " + s + " networking sessio!");
            }
            this.mEventHandler.removeSession(networkConnectionSession);
        }
        else if (Log.isLoggable()) {
            Log.e("nf_log_apm", "Trying to terminate " + s + " networking session that does not exist!");
        }
    }
    
    private void handlePreappAddWidget(final Intent intent) {
        Log.d("nf_log_apm", "PREAPP_ADD_WIDGET");
        this.preappAddWidget(intent.getStringExtra("widgetData"), intent.getLongExtra("eventTime", System.currentTimeMillis()));
    }
    
    private void handlePreappDeleteWidget(final Intent intent) {
        Log.d("nf_log_apm", "PREAPP_DELETE_WIDGET");
        this.preappDeleteWidget(intent.getStringExtra("widgetData"), intent.getLongExtra("eventTime", System.currentTimeMillis()));
    }
    
    private void handleSharedContextEnded(final Intent intent) {
        Log.d("nf_log_apm", "SHARED_CONTEXT_SESSION_ENDED");
        this.endSharedContextSession();
    }
    
    private void handleSharedContextStarted(final Intent intent) {
        Log.d("nf_log_apm", "SHARED_CONTEXT_SESSION_STARTED");
        this.startSharedContextSession(intent.getStringExtra("uuid"));
    }
    
    private void handleStartNetworkConnectionSession(final NetworkConnectionSession networkConnectionSession, final DataContext dataContext, final IClientLogging$ModalView clientLogging$ModalView) {
        final NetworkConnectionSession networkConnectionSession2 = this.mNetworkConnectionSessions.get(networkConnectionSession.getName());
        if (networkConnectionSession2 != null) {
            this.mEventHandler.removeSession(networkConnectionSession2);
        }
        this.mNetworkConnectionSessions.put(networkConnectionSession.getName(), networkConnectionSession);
        this.mEventHandler.addSession(networkConnectionSession);
        final Event startEvent = networkConnectionSession.createStartEvent();
        this.populateEvent(startEvent, dataContext, clientLogging$ModalView);
        this.mEventHandler.post(startEvent);
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("Started network connection session, event: ");
            String string;
            if (startEvent == null) {
                string = "n/a";
            }
            else {
                string = startEvent.toString();
            }
            Log.v("nf_log_apm", append.append(string).toString());
        }
    }
    
    private void handleViewChanged(final Intent intent, final boolean b) {
        Log.d("nf_log_apm", "UI_MODAL_VIEW_CHANGED");
        this.uiViewChanged(b, IClientLogging$ModalView.valueOf(intent.getStringExtra("view")));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    private void sendUserSessionEvent(final UserSessionStartedEvent userSessionStartedEvent, final DataContext dataContext, final IClientLogging$ModalView clientLogging$ModalView) {
        this.populateEvent(userSessionStartedEvent, dataContext, clientLogging$ModalView);
        this.mEventHandler.post(userSessionStartedEvent);
        Log.d("nf_log_apm", "User session start event posted.");
    }
    
    private void startMobileNetworkConnectivitySession() {
        this.handleStartNetworkConnectionSession(new MobileNetworkConnectionSession(), this.mDataContext, this.mCurrentUiView);
    }
    
    private void startWifiNetworkConnectivitySession() {
        this.handleStartNetworkConnectionSession(new WifiNetworkConnectionSession(), this.mDataContext, this.mCurrentUiView);
    }
    
    private void startWiredNetworkConnectivitySession() {
        this.handleStartNetworkConnectionSession(new WiredNetworkConnectionSession(), this.mDataContext, this.mCurrentUiView);
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.mLogoutInProgress.set(true);
            this.endMobileNetworkConnectivitySession();
            this.endWifiNetworkConnectivitySession();
            this.endWiredNetworkConnectivitySession();
            this.endSharedContextSession();
            this.endUiBrowseStartupSession(0L, true, null);
            this.endUiStartupSession(true, null, null);
            final HashSet<String> set = new HashSet<String>(this.mDataRequests.size());
            set.addAll((Collection<?>)this.mDataRequests.keySet());
            final Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                this.endDataRequestSession(iterator.next(), null, IClientLogging$CompletionReason.canceled, null, null);
            }
        }
        final HashSet<String> set2 = new HashSet<String>(this.mAssetRequests.size());
        set2.addAll((Collection<?>)this.mAssetRequests.keySet());
        final Iterator<Object> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            this.endAssetRequestSession(iterator2.next(), IClientLogging$CompletionReason.canceled, null, null);
        }
        final HashSet<String> set3 = new HashSet<String>(this.mDialogSessions.size());
        set3.addAll((Collection<?>)this.mDialogSessions.keySet());
        final Iterator<Object> iterator3 = set3.iterator();
        while (iterator3.hasNext()) {
            this.endUiModelessViewSession(iterator3.next());
        }
        this.mDataRequests.clear();
        this.mAssetRequests.clear();
        this.mNetworkConnectionSessions.clear();
        this.mDialogSessions.clear();
        this.endUserSession(ApplicationPerformanceMetricsLogging$EndReason.logout, System.currentTimeMillis());
        this.endApplicationSession();
    }
    // monitorexit(this)
    
    @Override
    public void endApplicationSession() {
        Log.d("nf_log_apm", "Application session ended");
        if (this.mApplicationSession == null) {
            Log.w("nf_log_apm", "Application session does NOT exist!");
            return;
        }
        this.mEventHandler.removeSession(this.mApplicationSession);
        Log.d("nf_log_apm", "Application session end event posting...");
        final AppSessionEndedEvent endedEvent = this.mApplicationSession.createEndedEvent();
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        this.mApplicationSession = null;
        Log.d("nf_log_apm", "Application session end event posted.");
    }
    
    @Override
    public void endAssetRequestSession(final String s, final IClientLogging$CompletionReason clientLogging$CompletionReason, final HttpResponse httpResponse, final Error error) {
        final UIAssetRequestSession uiAssetRequestSession = this.mAssetRequests.remove(s);
        if (uiAssetRequestSession == null) {
            return;
        }
        Log.d("nf_log_apm", "Asset request session ended");
        this.mEventHandler.removeSession(uiAssetRequestSession);
        Log.d("nf_log_apm", "Asset request session end event posting...");
        final UIAssetRequestSessionEndedEvent endedEvent = uiAssetRequestSession.createEndedEvent(clientLogging$CompletionReason, httpResponse, error);
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        Log.d("nf_log_apm", "Asset request session end event posted.");
    }
    
    @Override
    public void endDataRequestSession(final String s, final List<FalkorPathResult> list, final IClientLogging$CompletionReason clientLogging$CompletionReason, final HttpResponse httpResponse, final Error error) {
        final UIDataRequestSession uiDataRequestSession = this.mDataRequests.remove(s);
        if (uiDataRequestSession == null) {
            return;
        }
        Log.d("nf_log_apm", "Data request session ended");
        this.mEventHandler.removeSession(uiDataRequestSession);
        Log.d("nf_log_apm", "Data request session end event posting...");
        final UIDataRequestSessionEndedEvent endedEvent = uiDataRequestSession.createEndedEvent(clientLogging$CompletionReason, httpResponse, error);
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        Log.d("nf_log_apm", "Data request session end event posted.");
    }
    
    @Override
    public void endSharedContextSession() {
        if (this.mSharedContextSession == null) {
            return;
        }
        Log.d("nf_log_apm", "Shared context session ended");
        final SharedContextSessionEndedEvent endedEvent = this.mSharedContextSession.createEndedEvent();
        Log.d("nf_log_apm", "Shared context session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mEventHandler.removeSession(this.mSharedContextSession);
        this.mSharedContextSession = null;
        Log.d("nf_log_apm", "Shared context session end done.");
    }
    
    @Override
    public void endUiBrowseStartupSession(final long n, final boolean b, final UIError uiError) {
        if (this.mUIBrowseStartupSession == null) {
            return;
        }
        Log.d("nf_log_apm", "UI browse startup session ended");
        this.mEventHandler.removeSession(this.mUIBrowseStartupSession);
        Log.d("nf_log_apm", "UI browse startup session end event posting...");
        final UIBrowseStartupSessionEndedEvent endedEvent = this.mUIBrowseStartupSession.createEndedEvent(n, b, uiError);
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        this.mUIBrowseStartupSession = null;
        Log.d("nf_log_apm", "UI browse startup session end event posted.");
    }
    
    @Override
    public void endUiModelessViewSession(final String s) {
        final UIModelessViewSession uiModelessViewSession = this.mDialogSessions.get(s);
        if (uiModelessViewSession == null) {
            if (Log.isLoggable()) {
                Log.d("nf_log_apm", "UI modeless session does NOT exist for request ID:" + s);
            }
            return;
        }
        Log.d("nf_log_apm", "UI modeless session ended");
        this.mEventHandler.removeSession(uiModelessViewSession);
        Log.d("nf_log_apm", "UI modeless session end event posting...");
        final UIModelessViewSessionEndedEvent endedEvent = uiModelessViewSession.createEndedEvent();
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        Log.d("nf_log_apm", "UI modeless session end event posted.");
    }
    
    @Override
    public void endUiStartupSession(final boolean b, final UIError uiError, final PlayerType playerType) {
        if (this.mUIStartupSession == null) {
            return;
        }
        Log.d("nf_log_apm", "UI startup session ended");
        this.mEventHandler.removeSession(this.mUIStartupSession);
        Log.d("nf_log_apm", "UI startup session end event posting...");
        final UIStartupSessionEndedEvent endedEvent = this.mUIStartupSession.createEndedEvent(b, uiError, playerType);
        this.populateEvent(endedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(endedEvent);
        this.mUIStartupSession = null;
        Log.d("nf_log_apm", "UI startup session end event posted.");
    }
    
    @Override
    public void endUserSession(final ApplicationPerformanceMetricsLogging$EndReason applicationPerformanceMetricsLogging$EndReason, final long n) {
        Log.d("nf_log_apm", "User session ended");
        if (this.mUserSession == null) {
            Log.w("nf_log_apm", "User session does NOT exist!");
            return;
        }
        final UserSessionEndedEvent endedEvent = this.mUserSession.createEndedEvent(applicationPerformanceMetricsLogging$EndReason, n, this.mDataContext, this.mCurrentUiView);
        if (endedEvent == null) {
            Log.d("nf_log_apm", "User session still waits on session id, do not post at this time.");
            return;
        }
        this.mEventHandler.removeSession(this.mUserSession);
        Log.d("nf_log_apm", "User session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mUserSession = null;
        Log.d("nf_log_apm", "User session end event posted.");
    }
    
    IClientLogging$ModalView getCurrentUiView() {
        return this.mCurrentUiView;
    }
    
    @Override
    public void handleConnectivityChange(final Context context) {
        this.mNetworkStatusMonitor.handleConnectivityChange(context);
    }
    
    @Override
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        switch (action) {
            default: {
                if (Log.isLoggable()) {
                    Log.d("nf_log_apm", "We do not support action " + action);
                }
                return false;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_STARTED": {
                this.handleAssetRequestStarted(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_ASSET_REQUEST_ENDED": {
                this.handleAssetRequestEnded(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED": {
                this.handleDataRequestStarted(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED": {
                this.handleDataRequestEnded(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_DISPLAYED": {
                this.handleDialogDisplayed(intent, b);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DIALOG_REMOVED": {
                this.handleDialogRemoved(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED": {
                this.handleViewChanged(intent, b);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_ADD_WIDGET": {
                this.handlePreappAddWidget(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_PREAPP_DELETE_WIDGET": {
                this.handlePreappDeleteWidget(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_STARTED": {
                this.handleSharedContextStarted(intent);
                return true;
            }
            case "com.netflix.mediaclient.intent.action.LOG_APM_DATA_SHARED_CONTEXT_SESSION_ENDED": {
                this.handleSharedContextEnded(intent);
                return true;
            }
        }
    }
    
    public boolean isLogoutInProgress() {
        return this.mLogoutInProgress.get();
    }
    
    @Override
    public boolean isUserSessionExist() {
        return this.mUserSession != null;
    }
    
    public void logoutCompleted() {
        this.mLogoutInProgress.set(false);
    }
    
    @Override
    public void preappAddWidget(final String s, final long n) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "preapp add widget");
        }
        this.mEventHandler.post(new PreAppWidgetInstallEvent(PreAppWidgetInstallEvent$WidgetInstallAction.ADD, s, n));
        Log.d("nf_log_apm", "preapp add widget done");
    }
    
    @Override
    public void preappDeleteWidget(final String s, final long n) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "preapp delete widget");
        }
        this.mEventHandler.post(new PreAppWidgetInstallEvent(PreAppWidgetInstallEvent$WidgetInstallAction.DELETE, s, n));
        Log.d("nf_log_apm", "preapp delete widget done");
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startApplicationSession(final boolean b) {
        Log.d("nf_log_apm", "Application session created");
        final String applicationId = this.mEventHandler.getApplicationId();
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "startApplicationSession: Application id " + applicationId);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (StringUtils.isNotEmpty(applicationId)) {
            Log.d("nf_log_apm", "Application ID is ready, start application session...");
            this.doStartApplicationSession(b, applicationId, currentTimeMillis);
            return;
        }
        Log.w("nf_log_apm", "Application ID is not received from NRDLIb yet, postpone application session start...");
        this.mEventHandler.setAppIdSetListener(new ApmLoggingImpl$1(this, b, currentTimeMillis));
    }
    
    @Override
    public String startAssetRequestSession(final String s, final IClientLogging$AssetType clientLogging$AssetType) {
        if (this.mAssetRequests.get(s) != null) {
            if (Log.isLoggable()) {
                Log.d("nf_log_apm", "UI Asset request session already in progress for URL: " + s);
            }
            return null;
        }
        if (!this.mEventHandler.canSendEvent("uiQOE", "uiAssetRequest")) {
            Log.d("nf_log_apm", "Asset request started. Asset request tracking is not enabled. Done.");
            return null;
        }
        Log.d("nf_log_apm", "Asset request session created");
        final UIAssetRequestSession uiAssetRequestSession = new UIAssetRequestSession(s, clientLogging$AssetType);
        this.mEventHandler.addSession(uiAssetRequestSession);
        this.mAssetRequests.put(s, uiAssetRequestSession);
        Log.d("nf_log_apm", "Asset session start done.");
        return s;
    }
    
    @Override
    public boolean startDataRequestSession(final String s, final String s2) {
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_log_apm", "Data request session: url is empty, can not create session!");
            return false;
        }
        if (StringUtils.isEmpty(s2)) {
            Log.e("nf_log_apm", "Data request session: requestId is empty, can not create session!");
            return false;
        }
        if (!this.mEventHandler.canSendEvent("uiQOE", "uiDataRequest")) {
            Log.d("nf_log_apm", "Data request started. Data request tracking is not enabled. Done.");
            return false;
        }
        final UIDataRequestSession uiDataRequestSession = new UIDataRequestSession(s, s2);
        this.mEventHandler.addSession(uiDataRequestSession);
        this.mDataRequests.put(s2, uiDataRequestSession);
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "UI data request session added for '" + s2 + "'");
        }
        Log.d("nf_log_apm", "Data session start done.");
        return true;
    }
    
    @Override
    public void startSharedContextSession(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "Shared context session started with uuid " + s);
        }
        if (s == null) {
            throw new IllegalArgumentException("UUID can not be null!");
        }
        if (this.mSharedContextSession != null) {
            Log.e("nf_log_apm", "Shared context session already exist, we do not recreate it!");
            return;
        }
        Log.d("nf_log_apm", "Shared context session created");
        this.mSharedContextSession = new SharedContextSession(s);
        this.mEventHandler.addSession(this.mSharedContextSession);
        this.mEventHandler.post(this.mSharedContextSession.createStartEvent());
        Log.d("nf_log_apm", "Shared context start done.");
    }
    
    @Override
    public void startUiBrowseStartupSession(final long started) {
        Log.d("nf_log_apm", "UI browse startup session started.");
        final UIBrowseStartupSession muiBrowseStartupSession = new UIBrowseStartupSession();
        muiBrowseStartupSession.setStarted(started);
        this.mEventHandler.removeSession(this.mUIBrowseStartupSession);
        this.mEventHandler.addSession(muiBrowseStartupSession);
        this.mUIBrowseStartupSession = muiBrowseStartupSession;
    }
    
    @Override
    public void startUiModelessViewSession(final boolean b, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "UI modeless session created for " + clientLogging$ModalView + ". In portrait " + b + ". Dialog ID: " + s);
        }
        final UIModelessViewSession uiModelessViewSession = new UIModelessViewSession();
        this.mDialogSessions.put(s, uiModelessViewSession);
        this.mEventHandler.addSession(uiModelessViewSession);
        Log.d("nf_log_apm", "UI modeless session start event posting...");
        final UIModelessViewSessionStartedEvent startEvent = uiModelessViewSession.createStartEvent(b, clientLogging$ModalView);
        this.populateEvent(startEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(startEvent);
        Log.d("nf_log_apm", "UI modeless session start event posted.");
    }
    
    @Override
    public void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final IClientLogging$ModalView clientLogging$ModalView, final int n, final String s, final Map<String, Integer> map, final Long n2, final Display display) {
        Log.d("nf_log_apm", "UI startup session created");
        final UIStartupSession muiStartupSession = new UIStartupSession(applicationPerformanceMetricsLogging$UiStartupTrigger, clientLogging$ModalView, n, s, map, display);
        if (n2 != null) {
            muiStartupSession.setStarted(n2);
        }
        this.mEventHandler.removeSession(this.mUIStartupSession);
        this.mUIStartupSession = muiStartupSession;
        this.mEventHandler.addSession(muiStartupSession);
    }
    
    @Override
    public void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final IClientLogging$ModalView clientLogging$ModalView, final Long n, final Display display) {
        if (this.mUIStartupSession != null) {
            Log.w("nf_log_apm", "UI startup session already exist, we do not recreate it!");
            return;
        }
        Log.d("nf_log_apm", "UI startup session created");
        final UIStartupSession muiStartupSession = new UIStartupSession(applicationPerformanceMetricsLogging$UiStartupTrigger, clientLogging$ModalView, display);
        if (n != null) {
            muiStartupSession.setStarted(n);
        }
        this.mEventHandler.removeSession(this.mUIStartupSession);
        this.mUIStartupSession = muiStartupSession;
        this.mEventHandler.addSession(muiStartupSession);
    }
    
    public void startUserSession() {
        if (this.mUserSession != null) {
            Log.d("nf_log_apm", "User session already exist");
            return;
        }
        Log.d("nf_log_apm", "User session started");
        final String userSessionId = this.mEventHandler.getUserSessionId();
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "startUserSession: Current nrdp.log.sessionid " + userSessionId);
        }
        final UserSession mUserSession = new UserSession();
        (this.mUserSession = mUserSession).setId(Long.valueOf(userSessionId));
        Log.d("nf_log_apm", "User session start event posting...");
        final UserSessionStartedEvent startEvent = mUserSession.createStartEvent(ApplicationPerformanceMetricsLogging$Trigger.logout, 0L);
        this.mNrdpLogSessionId = userSessionId;
        this.mEventHandler.addSession(this.mUserSession);
        this.sendUserSessionEvent(startEvent, this.mDataContext, this.mCurrentUiView);
    }
    
    @Override
    public void startUserSession(final ApplicationPerformanceMetricsLogging$Trigger applicationPerformanceMetricsLogging$Trigger) {
        this.startUserSession(applicationPerformanceMetricsLogging$Trigger, 0L);
    }
    
    @Override
    public void startUserSession(final ApplicationPerformanceMetricsLogging$Trigger applicationPerformanceMetricsLogging$Trigger, final long n) {
        if (this.mUserSession != null) {
            Log.d("nf_log_apm", "User session already exist");
            return;
        }
        Log.d("nf_log_apm", "User session started");
        final UserSession mUserSession = new UserSession();
        Log.d("nf_log_apm", "User session start event posting...");
        final UserSessionStartedEvent startEvent = mUserSession.createStartEvent(applicationPerformanceMetricsLogging$Trigger, n);
        final String userSessionId = this.mEventHandler.getUserSessionId();
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "startUserSession: Current nrdp.log.sessionid " + userSessionId);
            Log.d("nf_log_apm", "startUserSession: Last used nrdp.log.sessionid " + this.mNrdpLogSessionId);
        }
        final ApmLoggingImpl$2 apmLoggingImpl$2 = new ApmLoggingImpl$2(this, mUserSession, startEvent);
        if (this.mNrdpLogSessionId != null) {
            this.mEventHandler.createUserSession(apmLoggingImpl$2);
            return;
        }
        Log.d("nf_log_apm", "We never used nrdp.log.sessionid, use it now if user session exist in nrdp");
        if (StringUtils.isEmpty(userSessionId)) {
            Log.e("nf_log_apm", "User session is not yet created. This should NOT happen!");
            this.mEventHandler.createUserSession(apmLoggingImpl$2);
            return;
        }
        Log.d("nf_log_apm", "User session is created. Set it to event and post event");
        this.mEventHandler.removeSession(this.mUserSession);
        (this.mUserSession = mUserSession).setId(Long.valueOf(userSessionId));
        this.mNrdpLogSessionId = userSessionId;
        this.mEventHandler.addSession(this.mUserSession);
        startEvent.setSessionId(this.mUserSession.getId());
        this.sendUserSessionEvent(startEvent, this.mDataContext, this.mCurrentUiView);
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging$ModalView mCurrentUiView) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "UI view changed " + mCurrentUiView);
        }
        this.mCurrentUiView = mCurrentUiView;
        final UIModalViewChangedEvent uiModalViewChangedEvent = new UIModalViewChangedEvent(b, mCurrentUiView);
        this.populateEvent(uiModalViewChangedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(uiModalViewChangedEvent);
        Log.d("nf_log_apm", "UI view changed event posted.");
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging$ModalView mCurrentUiView, final long time) {
        if (Log.isLoggable()) {
            Log.d("nf_log_apm", "UI view changed " + mCurrentUiView);
        }
        this.mCurrentUiView = mCurrentUiView;
        final UIModalViewChangedEvent uiModalViewChangedEvent = new UIModalViewChangedEvent(b, mCurrentUiView);
        uiModalViewChangedEvent.setTime(time);
        this.populateEvent(uiModalViewChangedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(uiModalViewChangedEvent);
        Log.d("nf_log_apm", "UI view changed event posted.");
    }
}
