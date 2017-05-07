// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import android.support.v7.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.auth.api.credentials.CredentialRequest$Builder;
import android.os.Bundle;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.netflix.mediaclient.util.IntentUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.protocol.nflx.NflxHandlerFactory;
import com.netflix.mediaclient.protocol.netflixcom.NetflixComHandlerFactory;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.Credential;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.api.GoogleApiClient;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.google.android.gms.common.api.Result;
import com.netflix.mediaclient.Log;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.ResultCallback;

class LaunchActivity$5 implements ResultCallback<CredentialRequestResult>
{
    final /* synthetic */ LaunchActivity this$0;
    
    LaunchActivity$5(final LaunchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResult(final CredentialRequestResult credentialRequestResult) {
        if (Log.isLoggable()) {
            Log.d("LaunchActivity", "Received resolution for GPS credential APIs: " + credentialRequestResult);
        }
        if (credentialRequestResult.getStatus().getStatusCode() == 4) {
            Log.d("LaunchActivity", "Sign in is required, go with regular workflow");
            if (credentialRequestResult.getCredential() != null) {
                this.this$0.mHint = credentialRequestResult.getCredential().getId();
                Log.d("LaunchActivity", "Saving hint in case user ends up on login page " + this.this$0.mHint);
            }
            else {
                Log.d("LaunchActivity", "No credentials!");
            }
            this.this$0.handleUserNotSignedInWithoutCredentials(this.this$0.getServiceManager());
            return;
        }
        if (credentialRequestResult.getStatus().isSuccess()) {
            this.this$0.onCredentialRetrieved(credentialRequestResult.getCredential());
            return;
        }
        this.this$0.resolveResult(credentialRequestResult.getStatus());
    }
}
