// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import com.netflix.mediaclient.util.LoginUtils;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.ServiceErrorsHandler;
import android.app.Fragment;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class LoginActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$1(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        if (Log.isLoggable()) {
            Log.i("LoginActivity", "onManagerReady status=" + status);
        }
        final Fragment access$000 = this.this$0.getActiveFragment();
        if (access$000 != null) {
            ((NetflixFrag)access$000).onManagerReady(serviceManager, status);
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        ServiceErrorsHandler.handleManagerResponse(this.this$0, status);
        final Fragment access$000 = this.this$0.getActiveFragment();
        if (access$000 != null) {
            ((NetflixFrag)access$000).onManagerUnavailable(serviceManager, status);
        }
    }
}
