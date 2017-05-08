// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.ArrayList;
import com.netflix.mediaclient.partner.playbilling.PlayBillingCallback;
import android.webkit.WebChromeClient;
import android.view.View;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.LoginUtils;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import android.os.Build;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.StatusCode;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.google.android.gms.auth.api.Auth;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.partner.playbilling.PlayBilling;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.playbilling.PlayBilling$OnSetupFinishedListener;

class SignupActivity$2 implements PlayBilling$OnSetupFinishedListener
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$2(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onSetupFinished(final boolean b) {
        Log.d("SignupActivity", "play billing setup finished.");
        this.this$0.mPlayBillingInitDone = true;
        if (this.this$0.mPlayBilling == null) {
            Log.d("SignupActivity", "activity disposed already.. quit");
            return;
        }
        if (!this.this$0.getServiceManager().isReady()) {
            Log.d("SignupActivity", "serviceManager not ready yet.. deferring");
            return;
        }
        if (!b) {
            Log.e("SignupActivity", "Problem setting up in-app billing: ");
        }
        ThreadUtils.assertOnMain();
        this.this$0.setViews(this.this$0.getServiceManager(), this.this$0.canShowPlayBillingOption(this.this$0.getApplicationContext()));
    }
}
