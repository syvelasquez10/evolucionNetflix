// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.player.NonMemberPlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.ArrayList;
import com.netflix.mediaclient.partner.playbilling.PlayBillingCallback;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import android.net.Uri;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.AndroidUtils;
import android.webkit.JavascriptInterface;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;

class SignupActivity$SignUpJSBridge$8 implements Runnable
{
    final /* synthetic */ SignupActivity$SignUpJSBridge this$1;
    final /* synthetic */ String val$callbackFunction;
    
    SignupActivity$SignUpJSBridge$8(final SignupActivity$SignUpJSBridge this$1, final String val$callbackFunction) {
        this.this$1 = this$1;
        this.val$callbackFunction = val$callbackFunction;
    }
    
    @Override
    public void run() {
        this.this$1.invokeJsCallback(this.val$callbackFunction, null);
    }
}