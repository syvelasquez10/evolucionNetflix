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
import com.netflix.mediaclient.servicemgr.SocialLogging$FriendPosition;
import org.json.JSONArray;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.SocialLogging$Source;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.social.SocialImpressionSession;
import com.netflix.mediaclient.service.logging.social.SocialConnectSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.SocialLogging;

public class SocialLoggingImpl implements SocialLogging
{
    private static final String TAG = "nf_log";
    private IClientLogging$ModalView mCurrentUiView;
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
                this.createSocialConnectActionResponseEvent(SocialLogging$Channel.valueOf(intent.getStringExtra("channel")), SocialLogging$Source.valueOf(intent.getStringExtra("source")), booleanExtra, instance);
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
        this.startSocialConnectSession(SocialLogging$Channel.valueOf(intent.getStringExtra("channel")));
    }
    
    private void handleImpression(final Intent intent) {
        Log.d("nf_log", "SOCIAL_CONNECT_IMPRESSION");
        this.createSocialConnectImpressionEvent(IClientLogging$ModalView.valueOf(intent.getStringExtra("view")));
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
        this.startSocialImpressionSession(IClientLogging$ModalView.valueOf(intent.getStringExtra("view")), intent.getStringExtra("guid"), intent.getStringExtra("storyId"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommend(Intent fromJsonArray) {
        Log.d("nf_log", "SOCIAL_RECOMMEND");
        final IClientLogging$ModalView value = IClientLogging$ModalView.valueOf(fromJsonArray.getStringExtra("view"));
        final String stringExtra = fromJsonArray.getStringExtra("guid");
        final int intExtra = fromJsonArray.getIntExtra("trackId", 0);
        final String stringExtra2 = fromJsonArray.getStringExtra("friendPositions");
        fromJsonArray = null;
        while (true) {
            try {
                fromJsonArray = (Intent)(Object)SocialLogging$FriendPosition.fromJsonArray(new JSONArray(stringExtra2));
                this.createRecommendFriendSelectedEvent(value, stringExtra, (SocialLogging$FriendPosition[])(Object)fromJsonArray, intExtra);
            }
            catch (Throwable t) {
                Log.e("nf_log", "Failed to extract friends position json array", t);
                continue;
            }
            break;
        }
    }
    
    private void handleRecommendMessageAdded(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_MESSAGE_ADDED");
        this.createRecommendMessageAddedEvent(IClientLogging$ModalView.valueOf(intent.getStringExtra("view")), intent.getStringExtra("guid"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendRead(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_READ");
        this.createRecommendImplicitFeedbackReadEvent(intent.getStringExtra("msgId"), intent.getStringExtra("videoId"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendScrolled(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_SCROLLED");
        this.createRecommendPanelScrolledEvent(IClientLogging$ModalView.valueOf(intent.getStringExtra("view")), intent.getStringExtra("guid"), intent.getIntExtra("trackId", 0));
    }
    
    private void handleRecommendSearched(final Intent intent) {
        Log.d("nf_log", "SOCIAL_RECOMMEND_SEARCHED");
        this.createRecommendPanelSearchedEvent(IClientLogging$ModalView.valueOf(intent.getStringExtra("view")), intent.getStringExtra("guid"), intent.getIntExtra("trackId", 0));
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void createRecommendFriendSelectedEvent(final IClientLogging$ModalView clientLogging$ModalView, final String s, final SocialLogging$FriendPosition[] array, final int n) {
        Log.d("nf_log", "createRecommendFriendSelectedEvent started.");
        final RecommendFriendSelectedEvent recommendFriendSelectedEvent = new RecommendFriendSelectedEvent(clientLogging$ModalView, s, array, n);
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
    public void createRecommendMessageAddedEvent(final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        Log.d("nf_log", "createRecommendMessageAddedEvent started.");
        final RecommendMessageAddedEvent recommendMessageAddedEvent = new RecommendMessageAddedEvent(clientLogging$ModalView, s, n);
        this.populateEvent(recommendMessageAddedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendMessageAddedEvent);
        Log.d("nf_log", "createRecommendMessageAddedEvent done.");
    }
    
    @Override
    public void createRecommendPanelScrolledEvent(final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        Log.d("nf_log", "RecommendPanelScrolledEvent started.");
        final RecommendPanelScrolledEvent recommendPanelScrolledEvent = new RecommendPanelScrolledEvent(clientLogging$ModalView, s, n);
        this.populateEvent(recommendPanelScrolledEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendPanelScrolledEvent);
        Log.d("nf_log", "RecommendPanelScrolledEvent done.");
    }
    
    @Override
    public void createRecommendPanelSearchedEvent(final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        Log.d("nf_log", "createRecommendPanelSearchedEvent started.");
        final RecommendPanelSearchedEvent recommendPanelSearchedEvent = new RecommendPanelSearchedEvent(clientLogging$ModalView, s, n);
        this.populateEvent(recommendPanelSearchedEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(recommendPanelSearchedEvent);
        Log.d("nf_log", "createRecommendPanelSearchedEvent done.");
    }
    
    @Override
    public void createSocialConnectActionResponseEvent(final SocialLogging$Channel socialLogging$Channel, final SocialLogging$Source socialLogging$Source, final boolean b, final Error error) {
        Log.d("nf_log", "createSocialConnectActionResponseEvent started.");
        final SocialConnectActionResponseEvent socialConnectActionResponseEvent = new SocialConnectActionResponseEvent(socialLogging$Channel, socialLogging$Source, b, error);
        this.populateEvent(socialConnectActionResponseEvent, this.mDataContext, this.mCurrentUiView);
        this.mEventHandler.post(socialConnectActionResponseEvent);
        Log.d("nf_log", "createSocialConnectActionResponseEvent done.");
    }
    
    @Override
    public void createSocialConnectImpressionEvent(final IClientLogging$ModalView clientLogging$ModalView) {
        Log.d("nf_log", "createSocialConnectImpressionEvent started.");
        final SocialConnectImpressionEvent socialConnectImpressionEvent = new SocialConnectImpressionEvent(clientLogging$ModalView);
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
        this.mSocialImpressionSession = null;
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
    public void startSocialConnectSession(final SocialLogging$Channel socialLogging$Channel) {
        Log.d("nf_log", "Social connect session start started");
        if (this.mSocialConnectSession != null) {
            Log.w("nf_log", "Social connect session existed before! It should not happen!");
            return;
        }
        this.mSocialConnectSession = new SocialConnectSession(socialLogging$Channel);
        this.mEventHandler.addSession(this.mSocialConnectSession);
        Log.d("nf_log", "Social connect session start done.");
    }
    
    @Override
    public void startSocialImpressionSession(final IClientLogging$ModalView clientLogging$ModalView, final String s, final String s2, final int n) {
        Log.d("nf_log", "Social Impression session start started");
        if (this.mSocialImpressionSession != null) {
            Log.w("nf_log", "Social Impression session existed before! It should not happen!");
            return;
        }
        this.mSocialImpressionSession = new SocialImpressionSession(clientLogging$ModalView, s, s2, n);
        this.mEventHandler.addSession(this.mSocialImpressionSession);
        Log.d("nf_log", "Social Impression session start done.");
    }
}
