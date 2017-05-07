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
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.StatusCode;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import android.os.Handler;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import org.json.JSONObject;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.net.Uri;
import java.util.Locale;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.webkit.JavascriptInterface;

public class SignupActivity$NFAndroidJS
{
    final /* synthetic */ SignupActivity this$0;
    
    public SignupActivity$NFAndroidJS(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @JavascriptInterface
    public String getDeviceCategory() {
        if (this.this$0.mDeviceCategory != null) {
            return this.this$0.mDeviceCategory;
        }
        return "phone";
    }
    
    @JavascriptInterface
    public String getESN() {
        if (this.this$0.mESN != null) {
            return this.this$0.mESN;
        }
        return "";
    }
    
    @JavascriptInterface
    public String getESNPrefix() {
        if (this.this$0.mESNPrefix != null) {
            return this.this$0.mESNPrefix;
        }
        return "";
    }
    
    @JavascriptInterface
    public String getLanguage() {
        return this.this$0.getDeviceLanguage();
    }
    
    @JavascriptInterface
    public String getSoftwareVersion() {
        if (this.this$0.mSoftwareVersion != null) {
            return this.this$0.mSoftwareVersion;
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
        if (this.this$0.mSignupOngoing) {
            Log.d("SignupActivity", "loginToApp ongoing, returning NULL operation");
            return;
        }
        Log.d("SignupActivity", "Login with Tokens " + s + " ErrHandler: " + s2);
        this.this$0.mErrHandler = s2;
        if (!ConnectivityUtils.isConnectedOrConnecting((Context)this.this$0)) {
            this.this$0.noConnectivityWarning();
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            Log.d("SignupActivity", "NetflixId: " + jsonObject.getString("NetflixId"));
            Log.d("SignupActivity", "SecureNetflixId: " + jsonObject.getString("SecureNetflixId"));
            final ActivationTokens activationTokens = new ActivationTokens(jsonObject);
            final ServiceManager serviceManager = this.this$0.getServiceManager();
            if (serviceManager != null && serviceManager.isReady()) {
                serviceManager.loginUserByTokens(activationTokens, this.this$0.loginQueryCallback);
                this.this$0.mSignupOngoing = true;
                this.this$0.runOnUiThread((Runnable)new SignupActivity$NFAndroidJS$2(this));
                return;
            }
        }
        catch (JSONException ex) {
            Log.e("SignupActivity", "Failed to LoginToApp");
            ex.printStackTrace();
            this.this$0.mSignupOngoing = false;
            this.this$0.provideDialog(this.this$0.getString(2131493268), this.this$0.mHandleError);
            return;
        }
        Log.d("SignupActivity", "loginToApp, invalid state to Login, bailing out");
    }
    
    @JavascriptInterface
    public void notifyReady() {
        Log.d("SignupActivity", "Signup UI ready to interact");
        this.this$0.mHandler.removeCallbacks(this.this$0.mJumpToSignInTask);
        this.this$0.runOnUiThread((Runnable)new SignupActivity$NFAndroidJS$1(this));
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
            this.this$0.updateMenuItems();
        }
    }
    
    @JavascriptInterface
    public void showSignIn() {
        Log.d("SignupActivity", "Show SignIn: ");
        this.this$0.mSignupMenuItem = true;
        this.this$0.updateMenuItems();
    }
    
    @JavascriptInterface
    public void showSignOut() {
        Log.d("SignupActivity", "Show SignOut");
        this.this$0.mSignupMenuItem = false;
        this.this$0.updateMenuItems();
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
