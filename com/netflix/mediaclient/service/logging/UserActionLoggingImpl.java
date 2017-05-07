// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiaction.model.UpgradeStreamsEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SubmitPaymentEndedEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.uiaction.model.StartPlayEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SearchEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RemoveFromPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RegisterEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RateTitleEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.LoginEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AddToPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AcknowledgeSignupEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import java.io.Serializable;
import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.mediaclient.service.logging.uiaction.UpgradeStreamsSession;
import com.netflix.mediaclient.service.logging.uiaction.SubmitPaymentSession;
import com.netflix.mediaclient.service.logging.uiaction.StartPlaySession;
import com.netflix.mediaclient.service.logging.uiaction.SearchSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.uiaction.RemoveFromPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.RegisterSession;
import com.netflix.mediaclient.service.logging.uiaction.RateTitleSession;
import com.netflix.mediaclient.service.logging.uiaction.NavigationSession;
import com.netflix.mediaclient.service.logging.uiaction.LoginSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiaction.AddToPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.AcknowledgeSignupSession;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

final class UserActionLoggingImpl implements UserActionLogging
{
    private static final String TAG = "nf_log";
    private AcknowledgeSignupSession mAcknowledgeSignup;
    private AddToPlaylistSession mAddToPlaylistSession;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private LoginSession mLoginSession;
    private NavigationSession mNavigationSession;
    private RateTitleSession mRateTitleSession;
    private RegisterSession mRegisterSession;
    private RemoveFromPlaylistSession mRemoveFromPlaylistSession;
    private Map<Long, SearchSession> mSearchSessions;
    private StartPlaySession mStartPlaySession;
    private SubmitPaymentSession mSubmitPaymentSession;
    private UpgradeStreamsSession mUpgradeStreamsSession;
    
    UserActionLoggingImpl(final EventHandler mEventHandler) {
        this.mSearchSessions = new ConcurrentHashMap<Long, SearchSession>(5);
        this.mEventHandler = mEventHandler;
    }
    
    private void handleAcknowledgeSignupEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        final String stringExtra3 = instance.getStringExtra("view");
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                Enum<IClientLogging.ModalView> value2 = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                if (StringUtils.isNotEmpty(stringExtra3)) {
                    value2 = IClientLogging.ModalView.valueOf(stringExtra3);
                }
                this.endAcknowledgeSignupSession((IClientLogging.CompletionReason)value, (UIError)instance, (IClientLogging.ModalView)value2);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleAcknowledgeSignupStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startAcknowledgeSignupSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleAddToPlaylistEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        final int intExtra = instance.getIntExtra("title_rank", 0);
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endAddToPlaylistSession((IClientLogging.CompletionReason)value, (UIError)instance, intExtra);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleAddToPlaylistStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startAddToPlaylistSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleLoginEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endLoginSession((IClientLogging.CompletionReason)value, (UIError)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleLoginStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startLoginSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleNavigationEnded(Intent value) {
        final String stringExtra = value.getStringExtra("reason");
        final String stringExtra2 = value.getStringExtra("error");
        UIError instance = null;
        final String stringExtra3 = value.getStringExtra("view");
        value = null;
        if (StringUtils.isNotEmpty(stringExtra3)) {
            value = (Intent)IClientLogging.ModalView.valueOf(stringExtra3);
        }
        while (true) {
            try {
                instance = UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value2 = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value2 = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endNavigationSession((IClientLogging.ModalView)value, (IClientLogging.CompletionReason)value2, instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleNavigationStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startNavigationSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleRateTitleEnded(Intent instance) {
        Serializable s = instance.getStringExtra("reason");
        final String stringExtra = instance.getStringExtra("error");
        final int intExtra = instance.getIntExtra("rating", 0);
        final int intExtra2 = instance.getIntExtra("rank", Integer.MIN_VALUE);
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty((String)s)) {
                    value = IClientLogging.CompletionReason.valueOf((String)s);
                }
                if (intExtra2 == Integer.MIN_VALUE) {
                    s = null;
                }
                else {
                    s = intExtra2;
                }
                this.endRateTitleSession((IClientLogging.CompletionReason)value, (UIError)instance, (Integer)s, intExtra);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleRateTitleStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startRateTitleSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleRegisterEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endRegisterSession((IClientLogging.CompletionReason)value, (UIError)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleRegisterStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startRegisterSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleRemoveFromPlaylistEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endRemoveFromPlaylistSession((IClientLogging.CompletionReason)value, (UIError)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleRemoveFromPlaylistStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startRemoveFromPlaylistSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleSearchEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        final long longExtra = instance.getLongExtra("id", -1L);
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endSearchSession(longExtra, (IClientLogging.CompletionReason)value, (UIError)instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleSearchStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("term");
        final String stringExtra3 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra3)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra3);
        }
        this.startSearchSession(intent.getLongExtra("id", -1L), (CommandName)value, (IClientLogging.ModalView)value2, stringExtra2);
    }
    
    private void handleStartPlayEnded(Intent instance) {
        Serializable s = instance.getStringExtra("reason");
        final String stringExtra = instance.getStringExtra("error");
        final int intExtra = instance.getIntExtra("rank", Integer.MIN_VALUE);
        instance = null;
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty((String)s)) {
                    value = IClientLogging.CompletionReason.valueOf((String)s);
                }
                if (intExtra == Integer.MIN_VALUE) {
                    s = null;
                }
                else {
                    s = intExtra;
                }
                this.endStartPlaySession((IClientLogging.CompletionReason)value, (UIError)instance, (Integer)s);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleStartPlayStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startStartPlaySession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleSubmitPaymentEnded(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "reason"
        //     3: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //     6: astore          6
        //     8: aload_1        
        //     9: ldc_w           "sucess"
        //    12: iconst_0       
        //    13: invokevirtual   android/content/Intent.getBooleanExtra:(Ljava/lang/String;Z)Z
        //    16: istore_2       
        //    17: aload_1        
        //    18: ldc_w           "error_code"
        //    21: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    24: astore          7
        //    26: aload_1        
        //    27: ldc_w           "payment_type"
        //    30: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    33: astore          5
        //    35: aload_1        
        //    36: ldc             "error"
        //    38: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    41: astore_3       
        //    42: aconst_null    
        //    43: astore_1       
        //    44: aload_3        
        //    45: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    48: astore_3       
        //    49: aload_3        
        //    50: astore_1       
        //    51: aconst_null    
        //    52: astore_3       
        //    53: aconst_null    
        //    54: astore          4
        //    56: aload           6
        //    58: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    61: ifne            70
        //    64: aload           6
        //    66: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    69: astore_3       
        //    70: aload           5
        //    72: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    75: ifne            85
        //    78: aload           5
        //    80: invokestatic    com/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;
        //    83: astore          4
        //    85: aconst_null    
        //    86: astore          6
        //    88: aload           6
        //    90: astore          5
        //    92: aload           7
        //    94: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //    97: ifeq            111
        //   100: new             Lorg/json/JSONObject;
        //   103: dup            
        //   104: aload           7
        //   106: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   109: astore          5
        //   111: aload_0        
        //   112: aload_3        
        //   113: aload_1        
        //   114: iload_2        
        //   115: aload           4
        //   117: aload           5
        //   119: invokevirtual   com/netflix/mediaclient/service/logging/UserActionLoggingImpl.endSubmitPaymentSession:(Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;ZLcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;Lorg/json/JSONObject;)V
        //   122: return         
        //   123: astore_3       
        //   124: goto            51
        //   127: astore          5
        //   129: aload           6
        //   131: astore          5
        //   133: goto            111
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  44     49     123    127    Lorg/json/JSONException;
        //  100    111    127    136    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    private void handleSubmitPaymentStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.startSubmitPaymentSession((CommandName)value, (IClientLogging.ModalView)value2);
    }
    
    private void handleUpgradeStreamsEnded(Intent instance) {
        final String stringExtra = instance.getStringExtra("reason");
        final String stringExtra2 = instance.getStringExtra("error");
        final String stringExtra3 = instance.getStringExtra("streams");
        instance = null;
        final Streams find = Streams.find(stringExtra3);
        while (true) {
            try {
                instance = (Intent)UIError.createInstance(stringExtra2);
                Enum<IClientLogging.CompletionReason> value = null;
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging.CompletionReason.valueOf(stringExtra);
                }
                this.endUpgradeStreamsSession((IClientLogging.CompletionReason)value, (UIError)instance, find);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleUpgradeStreamsStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        Enum<CommandName> value = null;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = CommandName.valueOf(stringExtra);
        }
        final String stringExtra2 = intent.getStringExtra("view");
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        final Streams streams = null;
        final String stringExtra3 = intent.getStringExtra("streams");
        Enum<Streams> find = streams;
        if (StringUtils.isNotEmpty(stringExtra3)) {
            find = Streams.find(stringExtra3);
        }
        this.startUpgradeStreamsSession((CommandName)value, (IClientLogging.ModalView)value2, (Streams)find);
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void endAcknowledgeSignupSession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final IClientLogging.ModalView modalView) {
        Log.d("nf_log", "AcknowledgeSignup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "User session ended");
                if (UserActionLoggingImpl.this.mAcknowledgeSignup == null) {
                    Log.w("nf_log", "AcknowledgeSignup session does NOT exist!");
                    return;
                }
                final AcknowledgeSignupEndedEvent endedEvent = UserActionLoggingImpl.this.mAcknowledgeSignup.createEndedEvent(completionReason, uiError, modalView);
                if (endedEvent == null) {
                    Log.d("nf_log", "AcknowledgeSignup session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, modalView);
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mAcknowledgeSignup);
                Log.d("nf_log", "AcknowledgeSignup session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mAcknowledgeSignup = null;
                Log.d("nf_log", "AcknowledgeSignup session end event posted.");
            }
        });
        Log.d("nf_log", "AcknowledgeSignup session end done.");
    }
    
    @Override
    public void endAddToPlaylistSession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final int n) {
        Log.d("nf_log", "AddToPlaylist session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "AddToPlaylist session ended");
                if (UserActionLoggingImpl.this.mAddToPlaylistSession == null) {
                    Log.w("nf_log", "AddToPlaylist session does NOT exist!");
                    return;
                }
                final AddToPlaylistEndedEvent endedEvent = UserActionLoggingImpl.this.mAddToPlaylistSession.createEndedEvent(completionReason, uiError, n);
                if (endedEvent == null) {
                    Log.d("nf_log", "AddToPlaylist session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mAddToPlaylistSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mAddToPlaylistSession);
                Log.d("nf_log", "AddToPlaylist session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mAddToPlaylistSession = null;
                Log.d("nf_log", "AddToPlaylist session end event posted.");
            }
        });
        Log.d("nf_log", "AddToPlaylist session end done.");
    }
    
    @Override
    public void endLoginSession(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        Log.d("nf_log", "Login session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "Login session ended");
                if (UserActionLoggingImpl.this.mLoginSession == null) {
                    Log.w("nf_log", "Login session does NOT exist!");
                    return;
                }
                final LoginEndedEvent endedEvent = UserActionLoggingImpl.this.mLoginSession.createEndedEvent(completionReason, uiError);
                if (endedEvent == null) {
                    Log.d("nf_log", "Login session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mLoginSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mLoginSession);
                Log.d("nf_log", "Login session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mLoginSession = null;
                Log.d("nf_log", "Login session end event posted.");
            }
        });
        Log.d("nf_log", "Login session end done.");
    }
    
    @Override
    public void endNavigationSession(final IClientLogging.ModalView modalView, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        Log.d("nf_log", "Navigation session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "Navigation session ended");
                final NavigationSession access$500 = UserActionLoggingImpl.this.mNavigationSession;
                if (access$500 == null) {
                    Log.w("nf_log", "Navigation session does NOT exist!");
                    return;
                }
                final NavigationEndedEvent endedEvent = access$500.createEndedEvent(modalView, completionReason, uiError);
                if (endedEvent == null) {
                    Log.d("nf_log", "We stayed in same view, cancel session.");
                }
                else {
                    Log.d("nf_log", "Navigation session end event posting...");
                    UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mNavigationSession.getView());
                    UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                    Log.d("nf_log", "Navigation session end event posted.");
                }
                UserActionLoggingImpl.this.mEventHandler.removeSession(access$500);
                UserActionLoggingImpl.this.mNavigationSession = null;
            }
        });
        Log.d("nf_log", "Navigation session end done.");
    }
    
    @Override
    public void endRateTitleSession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n, final int n2) {
        Log.d("nf_log", "RateTitle session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "RateTitle  session ended");
                if (UserActionLoggingImpl.this.mRateTitleSession == null) {
                    Log.w("nf_log", "RateTitle session does NOT exist!");
                    return;
                }
                final RateTitleEndedEvent endedEvent = UserActionLoggingImpl.this.mRateTitleSession.createEndedEvent(completionReason, uiError, n, n2);
                if (endedEvent == null) {
                    Log.d("nf_log", "RateTitle  session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mRateTitleSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mRateTitleSession);
                Log.d("nf_log", "RateTitle session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mRateTitleSession = null;
                Log.d("nf_log", "RateTitle session end event posted.");
            }
        });
        Log.d("nf_log", "RateTitle session end done.");
    }
    
    @Override
    public void endRegisterSession(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        Log.d("nf_log", "Register session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "Register session ended");
                if (UserActionLoggingImpl.this.mRegisterSession == null) {
                    Log.w("nf_log", "Register session does NOT exist!");
                    return;
                }
                final RegisterEndedEvent endedEvent = UserActionLoggingImpl.this.mRegisterSession.createEndedEvent(completionReason, uiError);
                if (endedEvent == null) {
                    Log.d("nf_log", "Register session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mRegisterSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mRegisterSession);
                Log.d("nf_log", "Register session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mRegisterSession = null;
                Log.d("nf_log", "Register session end event posted.");
            }
        });
        Log.d("nf_log", "Register session end done.");
    }
    
    @Override
    public void endRemoveFromPlaylistSession(final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        Log.d("nf_log", "RemoveFromPlaylist session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "RemoveFromPlaylist session ended");
                if (UserActionLoggingImpl.this.mRemoveFromPlaylistSession == null) {
                    Log.w("nf_log", "RemoveFromPlaylist session does NOT exist!");
                    return;
                }
                final RemoveFromPlaylistEndedEvent endedEvent = UserActionLoggingImpl.this.mRemoveFromPlaylistSession.createEndedEvent(completionReason, uiError);
                if (endedEvent == null) {
                    Log.d("nf_log", "RemoveFromPlaylist session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mRemoveFromPlaylistSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mRemoveFromPlaylistSession);
                Log.d("nf_log", "RemoveFromPlaylist session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mRemoveFromPlaylistSession = null;
                Log.d("nf_log", "RemoveFromPlaylist session end event posted.");
            }
        });
        Log.d("nf_log", "RemoveFromPlaylist session end done.");
    }
    
    @Override
    public void endSearchSession(final long n, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        Log.d("nf_log", "Search session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "Search session ended");
                final SearchSession searchSession = UserActionLoggingImpl.this.mSearchSessions.get(n);
                if (searchSession == null) {
                    Log.w("nf_log", "Search session does NOT exist for " + n);
                    return;
                }
                final SearchEndedEvent endedEvent = searchSession.createEndedEvent(completionReason, uiError);
                if (endedEvent == null) {
                    Log.d("nf_log", "Search session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, searchSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(searchSession);
                Log.d("nf_log", "Search session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                Log.d("nf_log", "Search session end event posted.");
            }
        });
        Log.d("nf_log", "Search session end done.");
    }
    
    @Override
    public void endStartPlaySession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n) {
        Log.d("nf_log", "StartPlay session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "StartPlay session ended");
                if (UserActionLoggingImpl.this.mStartPlaySession == null) {
                    Log.w("nf_log", "StartPlay session does NOT exist!");
                    return;
                }
                final StartPlayEndedEvent endedEvent = UserActionLoggingImpl.this.mStartPlaySession.createEndedEvent(completionReason, uiError, n);
                if (endedEvent == null) {
                    Log.d("nf_log", "StartPlay session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mStartPlaySession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mStartPlaySession);
                Log.d("nf_log", "StartPlay session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mStartPlaySession = null;
                Log.d("nf_log", "StartPlay session end event posted.");
            }
        });
        Log.d("nf_log", "StartPlay session end done.");
    }
    
    @Override
    public void endSubmitPaymentSession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final boolean b, final PaymentType paymentType, final JSONObject jsonObject) {
        Log.d("nf_log", "SubmitPayment session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "SubmitPayment session ended");
                if (UserActionLoggingImpl.this.mSubmitPaymentSession == null) {
                    Log.w("nf_log", "SubmitPayment session does NOT exist!");
                    return;
                }
                final SubmitPaymentEndedEvent endedEvent = UserActionLoggingImpl.this.mSubmitPaymentSession.createEndedEvent(completionReason, uiError, b, paymentType, jsonObject);
                if (endedEvent == null) {
                    Log.d("nf_log", "SubmitPayment session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mSubmitPaymentSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mSubmitPaymentSession);
                Log.d("nf_log", "SubmitPayment session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mSubmitPaymentSession = null;
                Log.d("nf_log", "SubmitPayment session end event posted.");
            }
        });
        Log.d("nf_log", "SubmitPayment session end done.");
    }
    
    @Override
    public void endUpgradeStreamsSession(final IClientLogging.CompletionReason completionReason, final UIError uiError, final Streams streams) {
        Log.d("nf_log", "UpgradeStreams session ended and posted to executor");
        this.mEventHandler.executeInBackground(new Runnable() {
            final /* synthetic */ DataContext val$dataContext = UserActionLoggingImpl.this.mDataContext;
            
            @Override
            public void run() {
                Log.d("nf_log", "UpgradeStreams session ended");
                if (UserActionLoggingImpl.this.mUpgradeStreamsSession == null) {
                    Log.w("nf_log", "UpgradeStreams session does NOT exist!");
                    return;
                }
                final UpgradeStreamsEndedEvent endedEvent = UserActionLoggingImpl.this.mUpgradeStreamsSession.createEndedEvent(completionReason, uiError, streams);
                if (endedEvent == null) {
                    Log.d("nf_log", "User session still waits on session id, do not post at this time.");
                    return;
                }
                UserActionLoggingImpl.this.populateEvent(endedEvent, this.val$dataContext, UserActionLoggingImpl.this.mUpgradeStreamsSession.getView());
                UserActionLoggingImpl.this.mEventHandler.removeSession(UserActionLoggingImpl.this.mUpgradeStreamsSession);
                Log.d("nf_log", "UpgradeStreams session end event posting...");
                UserActionLoggingImpl.this.mEventHandler.post(endedEvent);
                UserActionLoggingImpl.this.mUpgradeStreamsSession = null;
                Log.d("nf_log", "UpgradeStreams session end event posted.");
            }
        });
        Log.d("nf_log", "UpgradeStreams session end done.");
    }
    
    @Override
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START".equals(action)) {
            Log.d("nf_log", "ADD_TO_PLAYLIST_START");
            this.handleAddToPlaylistStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED".equals(action)) {
            Log.d("nf_log", "ADD_TO_PLAYLIST_ENDED");
            this.handleAddToPlaylistEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START".equals(action)) {
            Log.d("nf_log", "LOGIN_START");
            this.handleLoginStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED".equals(action)) {
            Log.d("nf_log", "LOGIN_ENDED");
            this.handleLoginEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_START".equals(action)) {
            Log.d("nf_log", "SIGNUP_START");
            this.handleAcknowledgeSignupStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_ENDED".equals(action)) {
            Log.d("nf_log", "SIGNUP_ENDED");
            this.handleAcknowledgeSignupEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START".equals(action)) {
            Log.d("nf_log", "NAVIGATION_START");
            this.handleNavigationStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED".equals(action)) {
            Log.d("nf_log", "NAVIGATION_ENDED");
            this.handleNavigationEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START".equals(action)) {
            Log.d("nf_log", "RATE_TITLE_START");
            this.handleRateTitleStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED".equals(action)) {
            Log.d("nf_log", "RATE_TITLE_ENDED");
            this.handleRateTitleEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_START".equals(action)) {
            Log.d("nf_log", "REGISTER_START");
            this.handleRegisterStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_ENDED".equals(action)) {
            Log.d("nf_log", "REGISTER_ENDED");
            this.handleRegisterEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START".equals(action)) {
            Log.d("nf_log", "REMOVE_FROM_PLAYLIST_START");
            this.handleRemoveFromPlaylistStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED".equals(action)) {
            Log.d("nf_log", "REMOVE_FROM_PLAYLIST_ENDED");
            this.handleRemoveFromPlaylistEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START".equals(action)) {
            Log.d("nf_log", "SEARCH_START");
            this.handleSearchStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED".equals(action)) {
            Log.d("nf_log", "SEARCH_ENDED");
            this.handleSearchEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START".equals(action)) {
            Log.d("nf_log", "START_PLAY_START");
            this.handleStartPlayStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED".equals(action)) {
            Log.d("nf_log", "START_PLAY_ENDED");
            this.handleStartPlayEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_START".equals(action)) {
            Log.d("nf_log", "SUBMIT_PAYMENT_START");
            this.handleSubmitPaymentStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_ENDED".equals(action)) {
            Log.d("nf_log", "SUBMIT_PAYMENT_ENDED");
            this.handleSubmitPaymentEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_START".equals(action)) {
            Log.d("nf_log", "UPGRADE_STREAMS_START");
            this.handleUpgradeStreamsStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_ENDED".equals(action)) {
            Log.d("nf_log", "UPGRADE_STREAMS_ENDED");
            this.handleUpgradeStreamsEnded(intent);
            return true;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startAcknowledgeSignupSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mAcknowledgeSignup != null) {
            Log.e("nf_log", "AcknowledgeSignup session already started!");
            return;
        }
        Log.d("nf_log", "AcknowledgeSignup session starting...");
        final AcknowledgeSignupSession mAcknowledgeSignup = new AcknowledgeSignupSession(commandName, modalView);
        this.mEventHandler.addSession(mAcknowledgeSignup);
        this.mAcknowledgeSignup = mAcknowledgeSignup;
        Log.d("nf_log", "AcknowledgeSignup session start done.");
    }
    
    @Override
    public void startAddToPlaylistSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mAddToPlaylistSession != null) {
            Log.e("nf_log", "AddToPlaylist session already started!");
            return;
        }
        Log.d("nf_log", "AddToPlaylist session starting...");
        final AddToPlaylistSession mAddToPlaylistSession = new AddToPlaylistSession(commandName, modalView);
        this.mEventHandler.addSession(mAddToPlaylistSession);
        this.mAddToPlaylistSession = mAddToPlaylistSession;
        Log.d("nf_log", "AddToPlaylist session start done.");
    }
    
    @Override
    public void startLoginSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mLoginSession != null) {
            Log.e("nf_log", "Login session already started!");
            return;
        }
        Log.d("nf_log", "Login session starting...");
        final LoginSession mLoginSession = new LoginSession(commandName, modalView);
        this.mEventHandler.addSession(mLoginSession);
        this.mLoginSession = mLoginSession;
        Log.d("nf_log", "Login session start done.");
    }
    
    @Override
    public void startNavigationSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mNavigationSession != null) {
            Log.d("nf_log", "Navigation session existed before, overwrite");
            this.mEventHandler.removeSession(this.mNavigationSession);
        }
        else {
            Log.d("nf_log", "Navigation session starting...");
        }
        final NavigationSession mNavigationSession = new NavigationSession(commandName, modalView);
        this.mEventHandler.addSession(mNavigationSession);
        this.mNavigationSession = mNavigationSession;
        Log.d("nf_log", "Navigation session start done.");
    }
    
    @Override
    public void startRateTitleSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mRateTitleSession != null) {
            Log.e("nf_log", "RateTitle session already started!");
            return;
        }
        Log.d("nf_log", "RateTitle session starting...");
        final RateTitleSession mRateTitleSession = new RateTitleSession(commandName, modalView);
        this.mEventHandler.addSession(mRateTitleSession);
        this.mRateTitleSession = mRateTitleSession;
        Log.d("nf_log", "RateTitle session start done.");
    }
    
    @Override
    public void startRegisterSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mRegisterSession != null) {
            Log.e("nf_log", "Register session already started!");
            return;
        }
        Log.d("nf_log", "Register session starting...");
        final RegisterSession mRegisterSession = new RegisterSession(commandName, modalView);
        this.mEventHandler.addSession(mRegisterSession);
        this.mRegisterSession = mRegisterSession;
        Log.d("nf_log", "Register session start done.");
    }
    
    @Override
    public void startRemoveFromPlaylistSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mRemoveFromPlaylistSession != null) {
            Log.e("nf_log", "RemoveFromPlaylist session already started!");
            return;
        }
        Log.d("nf_log", "RemoveFromPlaylist session starting...");
        final RemoveFromPlaylistSession mRemoveFromPlaylistSession = new RemoveFromPlaylistSession(commandName, modalView);
        this.mEventHandler.addSession(mRemoveFromPlaylistSession);
        this.mRemoveFromPlaylistSession = mRemoveFromPlaylistSession;
        Log.d("nf_log", "RemoveFromPlaylist session start done.");
    }
    
    @Override
    public void startSearchSession(final long n, final CommandName commandName, final IClientLogging.ModalView modalView, final String s) {
        Log.d("nf_log", "Search session starting...");
        final SearchSession searchSession = new SearchSession(n, commandName, modalView, s);
        this.mEventHandler.addSession(searchSession);
        this.mSearchSessions.put(n, searchSession);
        Log.d("nf_log", "Search session start done.");
    }
    
    @Override
    public void startStartPlaySession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mStartPlaySession != null) {
            Log.e("nf_log", "StartPlay session already started!");
            return;
        }
        Log.d("nf_log", "StartPlay session starting...");
        final StartPlaySession mStartPlaySession = new StartPlaySession(commandName, modalView);
        this.mEventHandler.addSession(mStartPlaySession);
        this.mStartPlaySession = mStartPlaySession;
        Log.d("nf_log", "StartPlay session start done.");
    }
    
    @Override
    public void startSubmitPaymentSession(final CommandName commandName, final IClientLogging.ModalView modalView) {
        if (this.mSubmitPaymentSession != null) {
            Log.e("nf_log", "SubmitPayment session already started!");
            return;
        }
        Log.d("nf_log", "SubmitPayment session starting...");
        final SubmitPaymentSession mSubmitPaymentSession = new SubmitPaymentSession(commandName, modalView);
        this.mEventHandler.addSession(mSubmitPaymentSession);
        this.mSubmitPaymentSession = mSubmitPaymentSession;
        Log.d("nf_log", "SubmitPayment session start done.");
    }
    
    @Override
    public void startUpgradeStreamsSession(final CommandName commandName, final IClientLogging.ModalView modalView, final Streams streams) {
        if (this.mUpgradeStreamsSession != null) {
            Log.e("nf_log", "UpgradeStreams session already started!");
            return;
        }
        Log.d("nf_log", "UpgradeStreams session starting...");
        final UpgradeStreamsSession mUpgradeStreamsSession = new UpgradeStreamsSession(commandName, modalView, streams);
        this.mEventHandler.addSession(mUpgradeStreamsSession);
        this.mUpgradeStreamsSession = mUpgradeStreamsSession;
        Log.d("nf_log", "UpgradeStreams session start done.");
    }
}
