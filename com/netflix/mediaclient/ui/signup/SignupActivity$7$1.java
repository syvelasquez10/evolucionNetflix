// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.view.View;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
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
import com.netflix.mediaclient.util.DeviceUtils;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.netflix.mediaclient.StatusCode;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.google.android.gms.auth.api.Auth;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Intent;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import android.os.Handler;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.android.app.Status;

class SignupActivity$7$1 implements Runnable
{
    final /* synthetic */ SignupActivity$7 this$1;
    final /* synthetic */ Status val$res;
    
    SignupActivity$7$1(final SignupActivity$7 this$1, final Status val$res) {
        this.this$1 = this$1;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.handleLoginComplete(this.val$res);
    }
}
