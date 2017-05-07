// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.social.model.SocialConnectImpressionEvent;
import com.netflix.mediaclient.service.logging.social.model.SocialConnectActionResponseEvent;
import com.netflix.mediaclient.service.logging.social.model.RecommendPanelSearchedEvent;
import com.netflix.mediaclient.service.logging.social.model.RecommendPanelScrolledEvent;
import com.netflix.mediaclient.service.logging.social.model.RecommendMessageAddedEvent;
import com.netflix.mediaclient.service.logging.social.model.RecommendImplicitFeedbackReadEvent;
import com.netflix.mediaclient.service.logging.social.model.RecommendFriendSelectedEvent;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.social.SocialImpressionSession;
import com.netflix.mediaclient.service.logging.social.SocialConnectSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.SocialLogging;

public class SocialLoggingImpl implements SocialLogging
{
    private static final String TAG = "nf_log";
    private IClientLogging.ModalView mCurrentUiView;
    private DataContext mDataContext;
    private EventHandler mEventHandler;
    private SocialConnectSession mSocialConnectSession;
    private SocialImpressionSession mSocialImpressionSession;
    
    SocialLoggingImpl(final EventHandler mEventHandler) {
        this.mEventHandler = mEventHandler;
    }
    
    private void handleActionResponse(final Intent intent) {
        Log.d("nf_log", "SOCIAL_CONNECT_ACTION_RESPONSE");
        final boolean booleanExtra = intent.getBooleanExtra("success", false);
        Error instance = null;
        while (true) {
            try {
                instance = Error.createInstance(intent.getStringExtra("error"));
                this.createSocialConnectActionResponseEvent(Channel.valueOf(intent.getStringExtra("channel")), Source.valueOf(intent.getStringExtra("source")), booleanExtra, instance);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private void handleConnectEnded(final Intent intent) {
        Log.d("nf_log", "SOCIAL_CONNECT_SESSION_ENDED");
        this.endSocialConnectSession();
    }
    
    private void handleConnectStarted(final Intent intent) {
        Log.d("nf_log", "SOCIAL_CONNECT_SESSION_STARTED");
        this.startSocialConnectSession(Channel.valueOf(intent.getStringExtra("channel")));
    }
    
    private void handleImpression(final Intent intent) {
        Log.d("nf_log", "SOCIAL_CONNECT_IMPRESSION");
        this.createSocialConnectImpressionEvent(IClientLogging.ModalView.valueOf(intent.getStringExtra("view")));
    }
    
    private void handleImpressionSessionEnded(final Intent intent) {
        final boolean booleanExtra = intent.getBooleanExtra("success", false);
        final UIError uiError = null;
        while (true) {
            try {
                final UIError instance = Error.createInstance(intent.getStringExtra("error"));
                this.endSocialImpressionSession(booleanExtra, instance);
            }
            catch (JSONException ex) {
                final UIError instance = uiError;
                continue;
            }
            break;
        }
    }
    
    private void handleImpressionSessionStarted(final Intent intent) {
        this.startSocialImpressionSession(IClientLogging.ModalView.valueOf(intent.getStringExtra("view")), intent.getStringExtra("storyId"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommend(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "nf_log"
        //     2: ldc             "SOCIAL_RECOMMEND"
        //     4: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     7: pop            
        //     8: aload_1        
        //     9: ldc             "view"
        //    11: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    14: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$ModalView.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;
        //    17: astore          4
        //    19: aload_1        
        //    20: ldc             "trackId"
        //    22: iconst_0       
        //    23: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //    26: istore_2       
        //    27: aload_1        
        //    28: ldc             "friendPositions"
        //    30: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    33: astore_1       
        //    34: aconst_null    
        //    35: astore_3       
        //    36: new             Lorg/json/JSONArray;
        //    39: dup            
        //    40: aload_1        
        //    41: invokespecial   org/json/JSONArray.<init>:(Ljava/lang/String;)V
        //    44: astore_1       
        //    45: aload_1        
        //    46: invokestatic    com/netflix/mediaclient/servicemgr/SocialLogging$FriendPosition.fromJsonArray:(Lorg/json/JSONArray;)[Lcom/netflix/mediaclient/servicemgr/SocialLogging$FriendPosition;
        //    49: astore_1       
        //    50: aload_0        
        //    51: aload           4
        //    53: aload_1        
        //    54: iload_2        
        //    55: invokevirtual   com/netflix/mediaclient/service/logging/SocialLoggingImpl.createRecommendFriendSelectedEvent:(Lcom/netflix/mediaclient/servicemgr/IClientLogging$ModalView;[Lcom/netflix/mediaclient/servicemgr/SocialLogging$FriendPosition;I)V
        //    58: return         
        //    59: astore_1       
        //    60: ldc             "nf_log"
        //    62: ldc             "Failed to extract friends position json array"
        //    64: aload_1        
        //    65: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    68: pop            
        //    69: aload_3        
        //    70: astore_1       
        //    71: goto            50
        //    74: astore_1       
        //    75: goto            60
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  36     45     59     60     Ljava/lang/Throwable;
        //  45     50     74     78     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    
    private void handleRecommendMessageAdded(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_MESSAGE_ADDED");
        this.createRecommendMessageAddedEvent(IClientLogging.ModalView.valueOf(intent.getStringExtra("view")), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendRead(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_READ");
        this.createRecommendImplicitFeedbackReadEvent(intent.getStringExtra("msgId"), intent.getStringExtra("videoId"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendScrolled(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_SCROLLED");
        this.createRecommendPanelScrolledEvent(IClientLogging.ModalView.valueOf(intent.getStringExtra("view")), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendSearched(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_SEARCHED");
        this.createRecommendPanelSearchedEvent(IClientLogging.ModalView.valueOf(intent.getStringExtra("view")), intent.getIntExtra("trackId", 0));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging.ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void createRecommendFriendSelectedEvent(final IClientLogging.ModalView modalView, final FriendPosition[] array, final int n) {
        Log.d("nf_log", "createRecommendFriendSelectedEvent started.");
        final RecommendFriendSelectedEvent recommendFriendSelectedEvent = new RecommendFriendSelectedEvent(modalView, array, n);
        this.populateEvent(recommendFriendSelectedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendFriendSelectedEvent);
        Log.d("nf_log", "createRecommendFriendSelectedEvent done.");
    }
    
    @Override
    public void createRecommendImplicitFeedbackReadEvent(final String s, final String s2, final int n) {
        Log.d("nf_log", "RecommendImplicitFeedbackReadEvent started.");
        final RecommendImplicitFeedbackReadEvent recommendImplicitFeedbackReadEvent = new RecommendImplicitFeedbackReadEvent(s, s2, n);
        this.populateEvent(recommendImplicitFeedbackReadEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendImplicitFeedbackReadEvent);
        Log.d("nf_log", "RecommendImplicitFeedbackReadEvent done.");
    }
    
    @Override
    public void createRecommendMessageAddedEvent(final IClientLogging.ModalView modalView, final int n) {
        Log.d("nf_log", "createRecommendMessageAddedEvent started.");
        final RecommendMessageAddedEvent recommendMessageAddedEvent = new RecommendMessageAddedEvent(modalView, n);
        this.populateEvent(recommendMessageAddedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendMessageAddedEvent);
        Log.d("nf_log", "createRecommendMessageAddedEvent done.");
    }
    
    @Override
    public void createRecommendPanelScrolledEvent(final IClientLogging.ModalView modalView, final int n) {
        Log.d("nf_log", "RecommendPanelScrolledEvent started.");
        final RecommendPanelScrolledEvent recommendPanelScrolledEvent = new RecommendPanelScrolledEvent(modalView, n);
        this.populateEvent(recommendPanelScrolledEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendPanelScrolledEvent);
        Log.d("nf_log", "RecommendPanelScrolledEvent done.");
    }
    
    @Override
    public void createRecommendPanelSearchedEvent(final IClientLogging.ModalView modalView, final int n) {
        Log.d("nf_log", "createRecommendPanelSearchedEvent started.");
        final RecommendPanelSearchedEvent recommendPanelSearchedEvent = new RecommendPanelSearchedEvent(modalView, n);
        this.populateEvent(recommendPanelSearchedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendPanelSearchedEvent);
        Log.d("nf_log", "createRecommendPanelSearchedEvent done.");
    }
    
    @Override
    public void createSocialConnectActionResponseEvent(final Channel channel, final Source source, final boolean b, final Error error) {
        Log.d("nf_log", "createSocialConnectActionResponseEvent started.");
        final SocialConnectActionResponseEvent socialConnectActionResponseEvent = new SocialConnectActionResponseEvent(channel, source, b, error);
        this.populateEvent(socialConnectActionResponseEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(socialConnectActionResponseEvent);
        Log.d("nf_log", "createSocialConnectActionResponseEvent done.");
    }
    
    @Override
    public void createSocialConnectImpressionEvent(final IClientLogging.ModalView modalView) {
        Log.d("nf_log", "createSocialConnectImpressionEvent started.");
        final SocialConnectImpressionEvent socialConnectImpressionEvent = new SocialConnectImpressionEvent(modalView);
        this.populateEvent(socialConnectImpressionEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(socialConnectImpressionEvent);
        Log.d("nf_log", "createSocialConnectImpressionEvent done.");
    }
    
    @Override
    public void endSocialConnectSession() {
        Log.d("nf_log", "Social connect session end started");
        if (this.mSocialConnectSession == null) {
            Log.w("nf_log", "Social connect session did not existed before! It should not happen!");
            return;
        }
        this.mEventHandler.removeSession(this.mSocialConnectSession);
        this.mEventHandler.post(this.mSocialConnectSession.createEndedEvent());
        this.mSocialConnectSession = null;
        Log.d("nf_log", "Social connect session end done.");
    }
    
    @Override
    public void endSocialImpressionSession(final boolean b, final Error error) {
        Log.d("nf_log", "Social Impression session end started");
        if (this.mSocialImpressionSession == null) {
            Log.w("nf_log", "Social Impression session did not existed before! It should not happen!");
            return;
        }
        this.mEventHandler.removeSession(this.mSocialImpressionSession);
        this.mEventHandler.post(this.mSocialImpressionSession.createEndEvent(b, error));
        this.mSocialConnectSession = null;
        Log.d("nf_log", "Social Impression session end done.");
    }
    
    @Override
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_ACTION_RESPONSE".equals(action)) {
            this.handleActionResponse(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_IMPRESSION".equals(action)) {
            this.handleImpression(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_STARTED".equals(action)) {
            this.handleConnectStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_ENDED".equals(action)) {
            this.handleConnectEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND".equals(action)) {
            this.handleRecommend(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_MESSAGE_ADDED".equals(action)) {
            this.handleRecommendMessageAdded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_READ".equals(action)) {
            this.handleRecommendRead(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SCROLLED".equals(action)) {
            this.handleRecommendScrolled(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SEARCHED".equals(action)) {
            this.handleRecommendSearched(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_STARTED".equals(action)) {
            this.handleImpressionSessionStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_ENDED".equals(action)) {
            this.handleImpressionSessionEnded(intent);
            return true;
        }
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startSocialConnectSession(final Channel channel) {
        Log.d("nf_log", "Social connect session start started");
        if (this.mSocialConnectSession != null) {
            Log.w("nf_log", "Social connect session existed before! It should not happen!");
            return;
        }
        this.mSocialConnectSession = new SocialConnectSession(channel);
        this.mEventHandler.addSession(this.mSocialConnectSession);
        Log.d("nf_log", "Social connect session start done.");
    }
    
    @Override
    public void startSocialImpressionSession(final IClientLogging.ModalView modalView, final String s, final int n) {
        Log.d("nf_log", "Social Impression session start started");
        if (this.mSocialImpressionSession != null) {
            Log.w("nf_log", "Social Impression session existed before! It should not happen!");
            return;
        }
        this.mSocialImpressionSession = new SocialImpressionSession(modalView, s, n);
        this.mEventHandler.addSession(this.mSocialImpressionSession);
        Log.d("nf_log", "Social Impression session start done.");
    }
}
