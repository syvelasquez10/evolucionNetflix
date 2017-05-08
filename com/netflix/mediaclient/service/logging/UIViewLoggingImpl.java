// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.service.logging.uiview.model.ImpressionEvent;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.uiview.model.ImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputValue;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.Stack;
import com.netflix.mediaclient.service.logging.uiview.ImpressionSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiview.CommandSession;
import com.netflix.mediaclient.servicemgr.UIViewLogging;

public final class UIViewLoggingImpl implements UIViewLogging
{
    private static final String TAG = "nf_log";
    private CommandSession mCommandSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private ImpressionSession mImpressionSession;
    private CommandSession mLeftPanelCommandSession;
    private ImpressionSession mLeftPanelImpressionSession;
    private Stack<ImpressionSession> mNotificationImpressionSessions;
    private String mUrl;
    
    public UIViewLoggingImpl(final EventHandler mEventHandler) {
        this.mNotificationImpressionSessions = new Stack<ImpressionSession>();
        this.mEventHandler = mEventHandler;
    }
    
    private void handleLeftPanelCommandEnded(final Intent intent) {
        if (this.mLeftPanelCommandSession == null) {
            return;
        }
        Log.d("nf_log", "LeftPanelCommandSession command session ended");
        final CommandEndedEvent endedEvent = this.mLeftPanelCommandSession.createEndedEvent();
        if (endedEvent == null) {
            Log.d("nf_log", "LeftPanelCommandSession command session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mLeftPanelCommandSession.getView());
        this.mEventHandler.removeSession(this.mLeftPanelCommandSession);
        Log.d("nf_log", "LeftPanelCommandSession command session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mLeftPanelCommandSession = null;
        Log.d("nf_log", "LeftPanelCommandSession command session end event posted.");
    }
    
    private void handleLeftPanelCommandStart(final Intent intent) {
        if (this.mLeftPanelCommandSession != null) {
            Log.e("nf_log", "LeftPanelCommandSession command session already started!");
            return;
        }
        Log.d("nf_log", "LeftPanelCommandSession command session starting...");
        this.mLeftPanelCommandSession = new CommandSession(UIViewLogging$UIViewCommandName.viewMenu, null, null, CommandEndedEvent$InputValue.valueOf(intent.getStringExtra("inputValue")), null);
        this.mEventHandler.addSession(this.mLeftPanelCommandSession);
        Log.d("nf_log", "LeftPanelCommandSession command session start done.");
    }
    
    private void handleLeftPanelImpressionEnd(final Intent intent) {
        if (this.mLeftPanelImpressionSession == null) {
            return;
        }
        final boolean booleanExtra = intent.getBooleanExtra("success", false);
        Log.d("nf_log", "LeftPanelImpressionEnd impression session ended");
        final ImpressionSessionEndedEvent endedEvent = this.mLeftPanelImpressionSession.createEndedEvent(booleanExtra, null);
        this.populateEvent(endedEvent, this.mDataContext, this.mLeftPanelImpressionSession.getView());
        this.mEventHandler.removeSession(this.mLeftPanelImpressionSession);
        Log.d("nf_log", "LeftPanelImpressionEnd impression session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mLeftPanelImpressionSession = null;
        Log.d("nf_log", "LeftPanelImpressionEnd impression session end event posted.");
    }
    
    private void handleLeftPanelImpressionStart(Intent stringExtra) {
        if (this.mLeftPanelImpressionSession != null) {
            Log.e("nf_log", "LeftPanelImpressionStart impression session already started!");
            return;
        }
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(stringExtra.getStringExtra("model"));
                Log.d("nf_log", "LeftPanelImpressionStart impression session starting...");
                stringExtra = (Intent)stringExtra.getStringExtra("guid");
                this.mLeftPanelImpressionSession = new ImpressionSession(IClientLogging$ModalView.menuPanel, (String)stringExtra, jsonObject);
                this.mEventHandler.addSession(this.mLeftPanelImpressionSession);
            }
            catch (JSONException ex) {
                if (Log.isLoggable()) {
                    Log.e("nf_log", "Couldn't construct JSON model from the following String: " + stringExtra.getStringExtra("model"));
                }
                final JSONObject jsonObject = null;
                continue;
            }
            break;
        }
    }
    
    private void handleNotificationImpressionEnd(final Intent intent) {
        if (this.mNotificationImpressionSessions.size() == 0) {
            return;
        }
        final boolean booleanExtra = intent.getBooleanExtra("success", false);
        Log.d("nf_log", "NotificationImpressionEnd impression session ended");
        final ImpressionSession impressionSession = this.mNotificationImpressionSessions.pop();
        final ImpressionSessionEndedEvent endedEvent = impressionSession.createEndedEvent(booleanExtra, null);
        this.populateEvent(endedEvent, this.mDataContext, impressionSession.getView());
        this.mEventHandler.removeSession(impressionSession);
        Log.d("nf_log", "NotificationImpressionEnd impression session end event posting...");
        this.mEventHandler.post(endedEvent);
        Log.d("nf_log", "NotificationImpressionEnd impression session end event posted.");
    }
    
    private void handleNotificationImpressionStart(Intent stringExtra) {
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(stringExtra.getStringExtra("model"));
                Log.d("nf_log", "NotificationImpressionStart impression session starting...");
                stringExtra = (Intent)stringExtra.getStringExtra("guid");
                stringExtra = (Intent)new ImpressionSession(IClientLogging$ModalView.notification, (String)stringExtra, jsonObject);
                this.mNotificationImpressionSessions.push((ImpressionSession)stringExtra);
                this.mEventHandler.addSession((LoggingSession)stringExtra);
            }
            catch (JSONException ex) {
                if (Log.isLoggable()) {
                    Log.e("nf_log", "Couldn't construct JSON model from the following String: " + stringExtra.getStringExtra("model"));
                }
                final JSONObject jsonObject = null;
                continue;
            }
            break;
        }
    }
    
    private void handleUIViewCommandEnded(final Intent intent) {
        this.endCommandSession();
    }
    
    private void handleUIViewCommandStart(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "cmd"
        //     3: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //     6: astore_2       
        //     7: aload_2        
        //     8: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    11: ifne            228
        //    14: aload_2        
        //    15: invokestatic    com/netflix/mediaclient/servicemgr/UIViewLogging$UIViewCommandName.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/UIViewLogging$UIViewCommandName;
        //    18: astore_2       
        //    19: aload_1        
        //    20: ldc             "view"
        //    22: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    25: astore_3       
        //    26: aload_3        
        //    27: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //    30: ifeq            223
        //    33: aload_3        
        //    34: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$ModalView.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;
        //    37: astore_3       
        //    38: aload_1        
        //    39: ldc             "inputMethod"
        //    41: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    44: astore          4
        //    46: aload           4
        //    48: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    51: ifne            217
        //    54: aload           4
        //    56: invokestatic    com/netflix/mediaclient/service/logging/uiview/model/CommandEndedEvent$InputMethod.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/uiview/model/CommandEndedEvent$InputMethod;
        //    59: astore          4
        //    61: aload_1        
        //    62: ldc             "dataContext"
        //    64: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    67: astore          6
        //    69: aload           6
        //    71: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //    74: ifeq            168
        //    77: new             Lorg/json/JSONObject;
        //    80: dup            
        //    81: aload           6
        //    83: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    86: invokestatic    com/netflix/mediaclient/service/logging/client/model/DataContext.createInstance:(Lorg/json/JSONObject;)Lcom/netflix/mediaclient/service/logging/client/model/DataContext;
        //    89: astore          5
        //    91: aload_1        
        //    92: ldc             "url"
        //    94: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    97: astore          7
        //    99: aload_1        
        //   100: ldc             "model"
        //   102: invokevirtual   android/content/Intent.hasExtra:(Ljava/lang/String;)Z
        //   105: ifeq            212
        //   108: new             Lorg/json/JSONObject;
        //   111: dup            
        //   112: aload_1        
        //   113: ldc             "model"
        //   115: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   118: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   121: astore          6
        //   123: aload           6
        //   125: astore_1       
        //   126: aload_0        
        //   127: aload_2        
        //   128: aload_3        
        //   129: aload           4
        //   131: aload           5
        //   133: aload           7
        //   135: aload_1        
        //   136: invokevirtual   com/netflix/mediaclient/service/logging/UIViewLoggingImpl.startCommandSession:(Lcom/netflix/mediaclient/servicemgr/UIViewLogging$UIViewCommandName;Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;Lcom/netflix/mediaclient/service/logging/uiview/model/CommandEndedEvent$InputMethod;Lcom/netflix/mediaclient/service/logging/client/model/DataContext;Ljava/lang/String;Lorg/json/JSONObject;)V
        //   139: return         
        //   140: astore          5
        //   142: ldc             "nf_log"
        //   144: new             Ljava/lang/StringBuilder;
        //   147: dup            
        //   148: invokespecial   java/lang/StringBuilder.<init>:()V
        //   151: ldc             "failed to create dataContext: "
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: aload           6
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   164: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   167: pop            
        //   168: aconst_null    
        //   169: astore          5
        //   171: goto            91
        //   174: astore          6
        //   176: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   179: ifeq            212
        //   182: ldc             "nf_log"
        //   184: new             Ljava/lang/StringBuilder;
        //   187: dup            
        //   188: invokespecial   java/lang/StringBuilder.<init>:()V
        //   191: ldc             "Couldn't construct JSON model from the following String: "
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: aload_1        
        //   197: ldc             "model"
        //   199: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   208: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop            
        //   212: aconst_null    
        //   213: astore_1       
        //   214: goto            126
        //   217: aconst_null    
        //   218: astore          4
        //   220: goto            61
        //   223: aconst_null    
        //   224: astore_3       
        //   225: goto            38
        //   228: aconst_null    
        //   229: astore_2       
        //   230: goto            19
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  77     91     140    168    Lorg/json/JSONException;
        //  108    123    174    212    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    private void handleUIViewImpression(final Intent intent) {
        final int intExtra = intent.getIntExtra("trackId", 0);
        final String stringExtra = intent.getStringExtra("cmd");
        UIViewLogging$UIViewCommandName value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UIViewLogging$UIViewCommandName.valueOf(stringExtra);
        }
        this.createImpressionEvent(value, intExtra);
    }
    
    private void handleUIViewImpressionEnd(Intent instance) {
        final boolean booleanExtra = instance.getBooleanExtra("success", false);
        final String stringExtra = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)Error.createInstance(stringExtra);
                this.endImpressionSession(booleanExtra, (Error)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleUIViewImpressionStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("view");
        IClientLogging$ModalView value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = IClientLogging$ModalView.valueOf(stringExtra);
        }
        this.startImpressionSession(value, intent.getStringExtra("guid"));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void createImpressionEvent(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final int n) {
        final ImpressionEvent impressionEvent = new ImpressionEvent(uiViewLogging$UIViewCommandName, n);
        this.populateEvent(impressionEvent, this.mDataContext, null);
        this.mEventHandler.post(impressionEvent);
    }
    
    @Override
    public void endAllActiveSessions() {
        this.endCommandSession();
        this.endImpressionSession(true, null);
    }
    
    @Override
    public void endCommandSession() {
        if (this.mCommandSession == null) {
            return;
        }
        Log.d("nf_log", "uiView command session ended");
        final CommandEndedEvent endedEvent = this.mCommandSession.createEndedEvent();
        if (endedEvent == null) {
            Log.d("nf_log", "uiView command session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mCommandSession.getView());
        this.mEventHandler.removeSession(this.mCommandSession);
        Log.d("nf_log", "uiView command session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mCommandSession = null;
        Log.d("nf_log", "uiView command session end event posted.");
    }
    
    @Override
    public void endImpressionSession(final boolean b, final Error error) {
        synchronized (this) {
            if (this.mImpressionSession != null) {
                Log.d("nf_log", "uiView impression session ended");
                final ImpressionSessionEndedEvent endedEvent = this.mImpressionSession.createEndedEvent(b, error);
                this.populateEvent(endedEvent, this.mDataContext, this.mImpressionSession.getView());
                this.mEventHandler.removeSession(this.mImpressionSession);
                Log.d("nf_log", "uiView impression session end event posting...");
                this.mEventHandler.post(endedEvent);
                this.mCommandSession = null;
                Log.d("nf_log", "uiView impression session end event posted.");
            }
        }
    }
    
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START".equals(action)) {
            Log.d("nf_log", "COMMAND_START");
            this.handleUIViewCommandStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED".equals(action)) {
            Log.d("nf_log", "COMMAND_ENDED");
            this.handleUIViewCommandEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_START".equals(action)) {
            Log.d("nf_log", "LEFT_PANEL_VIEW_COMMAND_START");
            this.handleLeftPanelCommandStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_ENDED".equals(action)) {
            Log.d("nf_log", "LEFT_PANEL_VIEW_COMMAND_ENDED");
            this.handleLeftPanelCommandEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_START".equals(action)) {
            Log.d("nf_log", "LEFT_PANEL_VIEW_IMPRESSION_START");
            this.handleLeftPanelImpressionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_ENDED".equals(action)) {
            Log.d("nf_log", "LEFT_PANEL_VIEW_IMPRESSION_ENDED");
            this.handleLeftPanelImpressionEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_START".equals(action)) {
            Log.d("nf_log", "NOTIFICATION_IMPRESSION_START");
            this.handleNotificationImpressionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_ENDED".equals(action)) {
            Log.d("nf_log", "NOTIFICATION_IMPRESSION_ENDED");
            this.handleNotificationImpressionEnd(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION".equals(action)) {
            Log.d("nf_log", "IMPRESSION");
            this.handleUIViewImpression(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED".equals(action)) {
            Log.d("nf_log", "IMPRESSION_SESSION_STARTED");
            this.handleUIViewImpressionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED".equals(action)) {
            Log.d("nf_log", "IMPRESSION_SESSION_ENDED");
            this.handleUIViewImpressionEnd(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startCommandSession(final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final CommandEndedEvent$InputMethod commandEndedEvent$InputMethod, final DataContext mDataContext, final String mUrl, final JSONObject jsonObject) {
        synchronized (this) {
            if (this.mCommandSession != null) {
                Log.e("nf_log", "uiView command session already started!");
            }
            else {
                Log.d("nf_log", "uiView command session starting...");
                final CommandSession mCommandSession = new CommandSession(uiViewLogging$UIViewCommandName, clientLogging$ModalView, mUrl, commandEndedEvent$InputMethod, CommandEndedEvent$InputValue.touch, jsonObject);
                this.mEventHandler.addSession(mCommandSession);
                this.mCommandSession = mCommandSession;
                this.mDataContext = mDataContext;
                this.mUrl = mUrl;
                Log.d("nf_log", "uiView command session start done.");
            }
        }
    }
    
    @Override
    public void startImpressionSession(final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        synchronized (this) {
            if (this.mImpressionSession != null) {
                Log.e("nf_log", "uiView impression session already started!");
            }
            else {
                Log.d("nf_log", "uiView impression session starting...");
                final ImpressionSession mImpressionSession = new ImpressionSession(clientLogging$ModalView, s);
                this.mEventHandler.addSession(mImpressionSession);
                this.mImpressionSession = mImpressionSession;
                Log.d("nf_log", "uiView impression session start done.");
            }
        }
    }
}
