// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$EndReason;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.apm.model.SharedContextSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.apm.model.UserSessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.Collections;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.apm.UserSession;
import com.netflix.mediaclient.service.logging.apm.UIStartupSession;
import com.netflix.mediaclient.service.logging.apm.UIBrowseStartupSession;
import com.netflix.mediaclient.service.logging.apm.SharedContextSession;
import com.netflix.mediaclient.service.logging.apm.NetworkConnectionSession;
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
        this.mEventHandler = mEventHandler;
    }
    
    private void endMobileNetworkConnectivitySession() {
        Log.d("nf_log_apm", "Mobile networking session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$20(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Mobile networking session end done.");
    }
    
    private void endWifiNetworkConnectivitySession() {
        Log.d("nf_log_apm", "Wifi networking session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$18(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Wifi networking session end done.");
    }
    
    private void endWiredNetworkConnectivitySession() {
        Log.d("nf_log_apm", "Wired networking session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$16(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Wired networking session end done.");
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
        //     5: ldc_w           "Handle data request ended intent. Running it on main thread."
        //     8: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: aload_1        
        //    13: ldc_w           "request_id"
        //    16: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    19: astore          6
        //    21: aload_1        
        //    22: ldc             "reason"
        //    24: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    27: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    30: astore          7
        //    32: aload_1        
        //    33: ldc             "error"
        //    35: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    38: invokestatic    com/netflix/mediaclient/service/logging/client/model/Error.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    41: astore_2       
        //    42: aload_1        
        //    43: ldc             "http_response"
        //    45: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    48: invokestatic    com/netflix/mediaclient/service/logging/client/model/HttpResponse.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;
        //    51: astore_3       
        //    52: aload_1        
        //    53: ldc_w           "falkorPathResults"
        //    56: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    59: invokestatic    com/netflix/mediaclient/service/logging/client/model/FalkorPathResult.createList:(Ljava/lang/String;)Ljava/util/List;
        //    62: astore_1       
        //    63: aload_3        
        //    64: astore          4
        //    66: aload_1        
        //    67: astore_3       
        //    68: aload_0        
        //    69: aload           6
        //    71: aload_3        
        //    72: aload           7
        //    74: aload           4
        //    76: aload_2        
        //    77: invokevirtual   com/netflix/mediaclient/service/logging/ApmLoggingImpl.endDataRequestSession:(Ljava/lang/String;Ljava/util/List;Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/HttpResponse;Lcom/netflix/mediaclient/service/logging/client/model/Error;)V
        //    80: return         
        //    81: astore          4
        //    83: aconst_null    
        //    84: astore_1       
        //    85: aconst_null    
        //    86: astore_2       
        //    87: ldc             "nf_log_apm"
        //    89: ldc             "Failed to parse properties"
        //    91: aload           4
        //    93: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: aload           5
        //    99: astore_3       
        //   100: aload_1        
        //   101: astore          4
        //   103: goto            68
        //   106: astore          4
        //   108: aconst_null    
        //   109: astore_1       
        //   110: goto            87
        //   113: astore          4
        //   115: aload_3        
        //   116: astore_1       
        //   117: goto            87
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     42     81     87     Ljava/lang/Exception;
        //  42     52     106    113    Ljava/lang/Exception;
        //  52     63     113    120    Ljava/lang/Exception;
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
            this.mEventHandler.removeSession(networkConnectionSession);
        }
        else if (Log.isLoggable("nf_log_apm", 6)) {
            Log.e("nf_log_apm", "Trying to terminate " + s + " networking session that does not exist!");
        }
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
        if (Log.isLoggable("nf_log_apm", 2)) {
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
        Log.d("nf_log_apm", "Mobile networking session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$19(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Mobile networking session start done.");
    }
    
    private void startWifiNetworkConnectivitySession() {
        Log.d("nf_log_apm", "Wifi networking session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$17(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Wifi networking session start done.");
    }
    
    private void startWiredNetworkConnectivitySession() {
        Log.d("nf_log_apm", "Wired networking session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$15(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Wired networking session start done.");
    }
    
    @Override
    public void endApplicationSession() {
        Log.d("nf_log_apm", "Application session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$2(this, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Application session end done.");
    }
    
    @Override
    public void endAssetRequestSession(final String s, final IClientLogging$CompletionReason clientLogging$CompletionReason, final HttpResponse httpResponse, final Error error) {
        Log.d("nf_log_apm", "Asset request session ended and executed in backgound");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$14(this, s, clientLogging$CompletionReason, httpResponse, error, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Asset request session end done.");
    }
    
    @Override
    public void endDataRequestSession(final String s, final List<FalkorPathResult> list, final IClientLogging$CompletionReason clientLogging$CompletionReason, final HttpResponse httpResponse, final Error error) {
        Log.d("nf_log_apm", "Data request session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$13(this, s, clientLogging$CompletionReason, httpResponse, error, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Data request session end done.");
    }
    
    @Override
    public void endSharedContextSession() {
        Log.d("nf_log_apm", "Shared context session ended");
        if (this.mSharedContextSession == null) {
            Log.w("nf_log_apm", "Shared context  does NOT exist!");
            return;
        }
        final SharedContextSessionEndedEvent endedEvent = this.mSharedContextSession.createEndedEvent();
        Log.d("nf_log_apm", "Shared context session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mEventHandler.removeSession(this.mSharedContextSession);
        this.mSharedContextSession = null;
        Log.d("nf_log_apm", "Shared context session end done.");
    }
    
    @Override
    public void endUiBrowseStartupSession(final long n, final boolean b, final UIError uiError) {
        Log.d("nf_log_apm", "UI startup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$8(this, n, b, uiError, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "UI browse startup session end done.");
    }
    
    @Override
    public void endUiModelessViewSession(final String s) {
        Log.d("nf_log_apm", "UI modeless session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$10(this, s, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "UI modeless session end done.");
    }
    
    @Override
    public void endUiStartupSession(final boolean b, final UIError uiError, final PlayerType playerType) {
        if (this.mUIStartupSession == null) {
            return;
        }
        Log.d("nf_log_apm", "UI startup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$7(this, b, uiError, playerType, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "UI startup session end done.");
    }
    
    @Override
    public void endUserSession(final ApplicationPerformanceMetricsLogging$EndReason applicationPerformanceMetricsLogging$EndReason, final long n) {
        Log.d("nf_log_apm", "User session ended and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$4(this, applicationPerformanceMetricsLogging$EndReason, n, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "User session end done.");
    }
    
    public ApplicationSession getApplicationSession() {
        return this.mApplicationSession;
    }
    
    IClientLogging$ModalView getCurrentUiView() {
        return this.mCurrentUiView;
    }
    
    public UserSession getUserSession() {
        return this.mUserSession;
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
                if (Log.isLoggable("nf_log_apm", 3)) {
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
        Log.d("nf_log_apm", "Application session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$1(this, b, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "Application session start done.");
    }
    
    @Override
    public String startAssetRequestSession(final String s, final IClientLogging$AssetType clientLogging$AssetType) {
        if (this.mAssetRequests.get(s) != null) {
            if (Log.isLoggable("nf_log_apm", 3)) {
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
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "UI data request session added for '" + s2 + "'");
        }
        Log.d("nf_log_apm", "Data session start done.");
        return true;
    }
    
    @Override
    public void startSharedContextSession(final String s) {
        if (Log.isLoggable("nf_log_apm", 3)) {
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
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "UI modeless session started and posted to executor " + clientLogging$ModalView + ". In portrait " + b + ". Dialog id: " + s);
        }
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$9(this, clientLogging$ModalView, b, s, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "User modeless session start done.");
    }
    
    @Override
    public void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final IClientLogging$ModalView clientLogging$ModalView, final int n, final String s, final Map<String, Integer> map, final Long n2, final Display display) {
        Log.d("nf_log_apm", "UI startup session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$6(this, applicationPerformanceMetricsLogging$UiStartupTrigger, clientLogging$ModalView, n, s, map, display, n2));
        Log.d("nf_log_apm", "UI startup start done.");
    }
    
    @Override
    public void startUiStartupSession(final ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger, final IClientLogging$ModalView clientLogging$ModalView, final Long n, final Display display) {
        Log.d("nf_log_apm", "UI startup session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$5(this, applicationPerformanceMetricsLogging$UiStartupTrigger, clientLogging$ModalView, display, n));
        Log.d("nf_log_apm", "UI startup start done.");
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
        Log.d("nf_log_apm", "User session started and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$3(this, applicationPerformanceMetricsLogging$Trigger, n, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "User session start done.");
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging$ModalView mCurrentUiView) {
        if (Log.isLoggable("nf_log_apm", 3)) {
            Log.d("nf_log_apm", "UI view changed and posted to executor " + mCurrentUiView);
        }
        final DataContext mDataContext = this.mDataContext;
        final IClientLogging$ModalView mCurrentUiView2 = this.mCurrentUiView;
        this.mCurrentUiView = mCurrentUiView;
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$11(this, mCurrentUiView, b, mDataContext, mCurrentUiView2));
        Log.d("nf_log_apm", "UI view changed done.");
    }
    
    @Override
    public void uiViewChanged(final boolean b, final IClientLogging$ModalView clientLogging$ModalView, final long n) {
        Log.d("nf_log_apm", "UI view changed and posted to executor");
        this.mEventHandler.executeInBackground(new ApmLoggingImpl$12(this, b, clientLogging$ModalView, n, this.mDataContext, this.mCurrentUiView));
        Log.d("nf_log_apm", "UI modeless session end done.");
    }
}
