// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import android.os.Bundle;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.app.Activity;
import com.facebook.Session;
import com.netflix.mediaclient.repository.SecurityRepository;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Session$StatusCallback;
import com.netflix.mediaclient.ui.login.AccountActivity;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.SocialLogging$Source;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class FacebookLoginActivity$ConnectedToFacebookCallback extends LoggingManagerCallback
{
    final /* synthetic */ FacebookLoginActivity this$0;
    
    public FacebookLoginActivity$ConnectedToFacebookCallback(final FacebookLoginActivity this$0) {
        this.this$0 = this$0;
        super("FacebookLoginActivity");
    }
    
    @Override
    public void onConnectWithFacebookComplete(final Status status) {
        super.onConnectWithFacebookComplete(status);
        SocialLoggingUtils.reportSocialConnectActionResponseEvent((Context)this.this$0, SocialLogging$Channel.Facebook, SocialLogging$Source.MDP, status.isSucces(), status.getError());
        if (status.isSucces()) {
            this.this$0.handleConnectSuccess();
            return;
        }
        this.this$0.handleConnectFailure();
    }
}
