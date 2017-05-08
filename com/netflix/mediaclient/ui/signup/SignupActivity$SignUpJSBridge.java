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
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
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

public class SignupActivity$SignUpJSBridge
{
    final /* synthetic */ SignupActivity this$0;
    
    public SignupActivity$SignUpJSBridge(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    private void handleTokenActivate(final Status status, final String s) {
        String message;
        if (status.getMessage() != null) {
            message = status.getMessage();
        }
        else {
            message = "";
        }
        SignupActivity.access$1102(this.this$0, false);
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "Token Activate Complete - Status: " + status.getStatusCode() + " DisplayMsg: " + message);
        }
        final StatusCode statusCode = status.getStatusCode();
        if (status.isSucces() || statusCode == StatusCode.NRD_REGISTRATION_EXISTS) {
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this.this$0, SignInLogging$SignInType.tokenActivate, IClientLogging$CompletionReason.success, (Error)null);
            this.invokeJsCallback(s, null);
        }
        else {
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this.this$0, SignInLogging$SignInType.tokenActivate, IClientLogging$CompletionReason.failed, status.getError());
            this.this$0.provideDialog(this.this$0.getString(2131297005) + " (" + statusCode.getValue() + ")", this.this$0.mHandleError);
            if (s != null) {
                final String string = "javascript:" + s + "('" + statusCode.getValue() + "')";
                Log.d("SignupActivity", "Executing the following javascript:" + string);
                this.this$0.getWebView().loadUrl(string);
            }
        }
    }
    
    private void invokeJsCallback(final String s, final JSONObject jsonObject) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", String.format("invokeJsCallbackQuery - func: %s, data: %s", s, jsonObject));
        }
        this.this$0.getWebView().loadUrl("javascript:" + s + "('" + jsonObject + "')");
    }
    
    @JavascriptInterface
    public String getDeviceCategory() {
        if (SignupActivity.access$1000(this.this$0) != null) {
            return SignupActivity.access$1000(this.this$0);
        }
        return "phone";
    }
    
    @JavascriptInterface
    public String getESN() {
        if (SignupActivity.access$700(this.this$0) != null) {
            return SignupActivity.access$700(this.this$0);
        }
        return "";
    }
    
    @JavascriptInterface
    public String getESNPrefix() {
        if (SignupActivity.access$800(this.this$0) != null) {
            return SignupActivity.access$800(this.this$0);
        }
        return "";
    }
    
    @JavascriptInterface
    public String getLanguage() {
        return this.this$0.getDeviceLanguage();
    }
    
    @JavascriptInterface
    public String getSoftwareVersion() {
        if (SignupActivity.access$900(this.this$0) != null) {
            return SignupActivity.access$900(this.this$0);
        }
        return "";
    }
    
    @JavascriptInterface
    public String isNetflixPreloaded() {
        if (AndroidUtils.isNetflixPreloaded((Context)this.this$0)) {
            return "true";
        }
        return "false";
    }
    
    @JavascriptInterface
    public String isPlayBillingEnabled() {
        final boolean b = false;
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        final boolean b2 = serviceManager != null && serviceManager.isReady() && this.this$0.getServiceManager().getConfiguration() != null && this.this$0.getServiceManager().getConfiguration().isPlayBillingDisabled();
        boolean b3 = b;
        if (!b2) {
            b3 = b;
            if (!AndroidUtils.isNetflixPreloaded((Context)this.this$0)) {
                b3 = true;
            }
        }
        if (b3) {
            return "true";
        }
        return "false";
    }
    
    @JavascriptInterface
    public void launchUrl(String s) {
        if (s == null) {
            s = "http://netflix.com";
        }
        else {
            final String s2 = s = s.trim();
            if (!s2.toLowerCase(Locale.ENGLISH).startsWith("http")) {
                s = "http://" + s2;
            }
        }
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
    }
    
    @JavascriptInterface
    public void loginCompleted() {
        Log.d("SignupActivity", "loginCompleted, noop");
    }
    
    @JavascriptInterface
    public void loginToApp(final String s, final String s2) {
        if (SignupActivity.access$1100(this.this$0)) {
            Log.d("SignupActivity", "loginToApp ongoing, returning NULL operation");
            return;
        }
        Log.d("SignupActivity", "Login with Tokens " + s + " ErrHandler: " + s2);
        SignupActivity.access$1202(this.this$0, s2);
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.this$0)) {
            this.this$0.noConnectivityWarning();
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            if (Log.isLoggable()) {
                Log.d("SignupActivity", "NetflixId: " + jsonObject.getString("NetflixId"));
                Log.d("SignupActivity", "SecureNetflixId: " + jsonObject.getString("SecureNetflixId"));
            }
            final ActivationTokens activationTokens = new ActivationTokens(jsonObject);
            final ServiceManager serviceManager = this.this$0.getServiceManager();
            if (serviceManager != null && serviceManager.isReady()) {
                PreferenceUtils.putBooleanPref((Context)this.this$0, "prefs_non_member_playback", false);
                SignInLogUtils.reportSignInRequestSessionStarted((Context)this.this$0, SignInLogging$SignInType.tokenActivate);
                serviceManager.loginUserByTokens(activationTokens, SignupActivity.access$1300(this.this$0));
                SignupActivity.access$1102(this.this$0, true);
                this.this$0.runOnUiThread((Runnable)new SignupActivity$SignUpJSBridge$2(this));
                return;
            }
        }
        catch (JSONException ex) {
            Log.e("SignupActivity", "Failed to LoginToApp", (Throwable)ex);
            SignupActivity.access$1102(this.this$0, false);
            this.this$0.provideDialog(this.this$0.getString(2131297005), this.this$0.mHandleError);
            return;
        }
        Log.d("SignupActivity", "loginToApp, invalid state to Login, bailing out");
    }
    
    @JavascriptInterface
    public void logoutOfApp() {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            serviceManager.logoutUser(null);
        }
    }
    
    @JavascriptInterface
    public void notifyReady() {
        Log.d("SignupActivity", "Signup UI ready to interact");
        this.this$0.getHandler().removeCallbacks(this.this$0.mJumpToSignInTask);
        PerformanceProfiler.getInstance().endSession(Sessions.NON_MEMBER_TTI);
        this.this$0.runOnUiThread((Runnable)new SignupActivity$SignUpJSBridge$1(this));
    }
    
    @JavascriptInterface
    public void passNonMemberInfo(final String s) {
        Log.e("SignupActivity", "Ignoring passNonMemberInfo %s", s);
    }
    
    @JavascriptInterface
    public void playBillingGetPurchaseHistory(String access$1900, final String s) {
        access$1900 = SignupActivity.access$1900(this.this$0, access$1900);
        Log.d("SignupActivity", "playBillingGetPurchaseHistory");
        if (!SignupActivity.access$1700(this.this$0)) {
            Log.e("SignupActivity", "playBillingGetPurchaseHistory - playBillingNotReady");
            this.invokeJsCallback(s, null);
            return;
        }
        SignupActivity.access$100(this.this$0).getPurchaseHistory(access$1900, (PlayBillingCallback)new SignupActivity$SignUpJSBridge$6(this, s));
    }
    
    @JavascriptInterface
    public void playBillingGetPurchases(String access$1900, final String s) {
        access$1900 = SignupActivity.access$1900(this.this$0, access$1900);
        Log.d("SignupActivity", "playBillingGetPurchases");
        if (!SignupActivity.access$1700(this.this$0)) {
            Log.e("SignupActivity", "playBillingGetPurchases - playBillingNotReady");
            this.invokeJsCallback(s, null);
            return;
        }
        SignupActivity.access$100(this.this$0).getPurchases(access$1900, (PlayBillingCallback)new SignupActivity$SignUpJSBridge$5(this, s));
    }
    
    @JavascriptInterface
    public void playBillingGetSkuDetails(final String s, final String s2) {
        final String[] split = s.split(",");
        final ArrayList<String> list = new ArrayList<String>();
        for (int length = split.length, i = 0; i < length; ++i) {
            list.add(split[i].trim());
        }
        if (Log.isLoggable()) {
            Log.d("SignupActivity", String.format("playBillingGetSkuDetails, input:%s,  List:%s", s, list));
        }
        if (!SignupActivity.access$1700(this.this$0)) {
            Log.e("SignupActivity", "playBillingGetSkuDetails - playBillingNotReady");
            this.invokeJsCallback(s2, null);
            return;
        }
        SignupActivity.access$100(this.this$0).getSkuDetails(list, (PlayBillingCallback)new SignupActivity$SignUpJSBridge$4(this, s2));
    }
    
    @JavascriptInterface
    public void playBillingPurchase(final String s, String access$1900, final int n, String access$1901, final String s2) {
        access$1901 = SignupActivity.access$1900(this.this$0, access$1901);
        access$1900 = SignupActivity.access$1900(this.this$0, access$1900);
        if (Log.isLoggable()) {
            Log.d("SignupActivity", String.format("playBillingPurchase sku:%s, callbackFunc:%s", s, s2));
        }
        if (!SignupActivity.access$1700(this.this$0)) {
            Log.e("SignupActivity", "playBillingPurchase - playBillingNotReady");
            this.invokeJsCallback(s2, null);
            return;
        }
        SignupActivity.access$100(this.this$0).purchase((NetflixActivity)this.this$0, s, access$1900, n, access$1901, 2, (PlayBillingCallback)new SignupActivity$SignUpJSBridge$7(this, s2));
    }
    
    @JavascriptInterface
    public void playVideo(final int n, final int n2, final String s, final String s2) {
        final ServiceManager serviceManager = this.this$0.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            serviceManager.setNonMemberPlayback(true);
        }
        VideoType videoType;
        if ("episode".equals(s)) {
            videoType = VideoType.EPISODE;
        }
        else {
            videoType = VideoType.MOVIE;
        }
        final PlayContextImp playContextImp = new PlayContextImp("mcplayer", n2, 0, 0);
        this.this$0.getBootLoader().setVideoId(Integer.toString(n));
        this.this$0.startActivityForResult(NonMemberPlayerActivity.createColdStartIntent((Context)this.this$0, Integer.toString(n), videoType, (PlayContext)playContextImp), 20);
    }
    
    @JavascriptInterface
    public void playbackTokenActivate(final String s, final String s2) {
        if (SignupActivity.access$1100(this.this$0)) {
            Log.d("SignupActivity", "Another potential token activate ongoing, returning NULL operation");
            return;
        }
        Log.d("SignupActivity", "Token Activate with Tokens " + s);
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.this$0)) {
            this.this$0.noConnectivityWarning();
            return;
        }
        Label_0277: {
            ActivationTokens activationTokens;
            ServiceManager serviceManager;
            try {
                final JSONObject jsonObject = new JSONObject(s);
                if (Log.isLoggable()) {
                    Log.d("SignupActivity", "NetflixId: " + jsonObject.getString("NetflixId"));
                    Log.d("SignupActivity", "SecureNetflixId: " + jsonObject.getString("SecureNetflixId"));
                }
                activationTokens = new ActivationTokens(jsonObject);
                serviceManager = this.this$0.getServiceManager();
                if (serviceManager == null || !serviceManager.isReady()) {
                    break Label_0277;
                }
                if (serviceManager.isUserLoggedIn()) {
                    this.this$0.runOnUiThread((Runnable)new SignupActivity$SignUpJSBridge$8(this, s2));
                    return;
                }
            }
            catch (JSONException ex) {
                Log.e("SignupActivity", "Failed to TokenActivate", (Throwable)ex);
                SignupActivity.access$1102(this.this$0, false);
                this.this$0.provideDialog(this.this$0.getString(2131297005), this.this$0.mHandleError);
                return;
            }
            SignInLogUtils.reportSignInRequestSessionStarted((Context)this.this$0, SignInLogging$SignInType.tokenActivate);
            PreferenceUtils.putBooleanPref((Context)this.this$0, "prefs_non_member_playback", true);
            serviceManager.loginUserByTokens(activationTokens, (ManagerCallback)new SignupActivity$SignUpJSBridge$9(this, s2));
            return;
        }
        Log.d("SignupActivity", "tokenActivate, invalid state to token activate, bailing out");
    }
    
    @JavascriptInterface
    public void saveUserCredentials(final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "saveUserCredentials:: email: " + s + ", passwd: " + s2);
        }
        SignupActivity.access$1402(this.this$0, s);
        SignupActivity.access$1502(this.this$0, s2);
        this.this$0.runOnUiThread((Runnable)new SignupActivity$SignUpJSBridge$3(this));
    }
    
    @JavascriptInterface
    public void setLanguage(final String currentAppLocale) {
        final boolean equals = currentAppLocale.equals(this.getLanguage());
        Log.d("SignupActivity", "Update language to " + currentAppLocale);
        if (!equals) {
            final ServiceManager serviceManager = this.this$0.getServiceManager();
            if (serviceManager == null || !serviceManager.isReady()) {
                Log.w("SignupActivity", "setLanguage  failed, invalid state");
                return;
            }
            this.this$0.getServiceManager().setCurrentAppLocale(currentAppLocale);
            ((NetflixApplication)this.this$0.getApplication()).refreshLocale(currentAppLocale);
            SignupActivity.access$600(this.this$0);
        }
    }
    
    @JavascriptInterface
    public void showSignIn() {
        Log.d("SignupActivity", "Show SignIn: ");
        SignupActivity.access$502(this.this$0, true);
        SignupActivity.access$600(this.this$0);
    }
    
    @JavascriptInterface
    public void showSignOut() {
        Log.d("SignupActivity", "Show SignOut");
        SignupActivity.access$502(this.this$0, false);
        SignupActivity.access$600(this.this$0);
    }
    
    @JavascriptInterface
    public void signupCompleted() {
        Log.d("SignupActivity", "signupCompleted, report");
        LogUtils.reportSignUpOnDevice((Context)this.this$0);
    }
    
    @JavascriptInterface
    public void supportsSignUp(final String s) {
        Log.d("SignupActivity", "SupportSignUp flag: " + s);
    }
    
    @JavascriptInterface
    public void toSignIn() {
        Log.d("SignupActivity", "Redirecting to Login screen");
        this.this$0.startNextActivity(LoginActivity.createStartIntent((Context)this.this$0));
        this.this$0.finish();
    }
}
