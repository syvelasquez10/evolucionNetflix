// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.ArrayList;
import com.netflix.mediaclient.partner.playbilling.PlayBillingCallback;
import android.widget.Toast;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.view.View;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.partner.playbilling.PlayBilling$OnSetupFinishedListener;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.util.LoginUtils;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import android.os.Build;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.StatusCode;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.google.android.gms.auth.api.Auth;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.partner.playbilling.PlayBilling;
import android.os.Handler;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.ui.login.AccountActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.Log;

class SignupActivity$4 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$4(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("SignupActivity", "Timeout triggered, switching to LoginActivity");
        if (!this.this$0.mSignupLoaded) {
            this.this$0.startNextActivity(LoginActivity.createStartIntent((Context)this.this$0));
            this.this$0.finish();
        }
    }
}
