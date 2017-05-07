// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import android.os.Bundle;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Intent;
import android.app.Activity;
import com.facebook.Session;
import com.netflix.mediaclient.repository.SecurityRepository;
import android.content.Context;
import android.widget.Toast;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Session$StatusCallback;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class FacebookLoginActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ FacebookLoginActivity this$0;
    
    FacebookLoginActivity$1(final FacebookLoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.manager = serviceManager;
        this.this$0.initFacebookSession();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.this$0.manager = null;
    }
}
