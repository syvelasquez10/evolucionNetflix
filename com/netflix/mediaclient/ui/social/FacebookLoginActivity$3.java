// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import android.os.Bundle;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.repository.SecurityRepository;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.facebook.Request$GraphUserCallback;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.Log;
import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.Session$StatusCallback;

class FacebookLoginActivity$3 implements Session$StatusCallback
{
    final /* synthetic */ FacebookLoginActivity this$0;
    
    FacebookLoginActivity$3(final FacebookLoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void call(final Session session, final SessionState sessionState, final Exception ex) {
        if (Log.isLoggable("FacebookLoginActivity", 2)) {
            Log.v("FacebookLoginActivity", "Callback - Session: " + session + ", State: " + sessionState + ", Exception: " + ex + ", Current Access Token: " + Session.getActiveSession().getAccessToken());
        }
        SocialLoggingUtils.reportEndSocialConnectSession((Context)this.this$0);
        if (sessionState.isOpened()) {
            this.this$0.executeMeRequestIfDebug();
            this.this$0.manager.connectWithFacebook(Session.getActiveSession().getAccessToken(), new FacebookLoginActivity$ConnectedToFacebookCallback(this.this$0));
        }
        else if (sessionState.isClosed()) {
            this.this$0.handleConnectFailure();
        }
    }
}
