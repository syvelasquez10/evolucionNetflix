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
import com.netflix.mediaclient.partner.playbilling.PlayBillingCallback;

class SignupActivity$SignUpJSBridge$6 extends PlayBillingCallback
{
    final /* synthetic */ SignupActivity$SignUpJSBridge this$1;
    
    SignupActivity$SignUpJSBridge$6(final SignupActivity$SignUpJSBridge this$1, final String s) {
        this.this$1 = this$1;
        super(s);
    }
    
    @Override
    public void onResult(final JSONObject jsonObject) {
        this.this$1.invokeJsCallback(this.getCallback(), jsonObject);
    }
}
