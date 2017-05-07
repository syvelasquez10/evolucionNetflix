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
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.StatusCode;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.os.Build$VERSION;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Intent;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import android.os.Handler;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.login.AccountActivity;

@SuppressLint({ "SetJavaScriptEnabled" })
public class SignupActivity extends AccountActivity
{
    private static final String BOOTURL = "booturl";
    private static final String DEFAULT_LOCALE = "en";
    private static final String TAG = "SignupActivity";
    private final SimpleManagerCallback loginQueryCallback;
    private SignupActivity$NFAndroidJS mAndroidJS;
    private String mDeviceCategory;
    private String mESN;
    private String mESNPrefix;
    private String mErrHandler;
    private ViewFlipper mFlipper;
    Runnable mHandleError;
    private Handler mHandler;
    Runnable mJumpToSignInTask;
    private String mSharedContextSessionUuid;
    private SignUpParams mSignUpParams;
    private boolean mSignupLoaded;
    private boolean mSignupMenuItem;
    private boolean mSignupOngoing;
    private String mSoftwareVersion;
    private Bootloader mUiBoot;
    private WebView mWebView;
    private SignUpWebViewClient mWebViewClient;
    private boolean mWebViewVisibility;
    
    public SignupActivity() {
        this.mWebViewVisibility = false;
        this.mSignupMenuItem = true;
        this.mSignupLoaded = false;
        this.mSignupOngoing = false;
        this.mSharedContextSessionUuid = ConsolidatedLoggingUtils.createGUID();
        this.mJumpToSignInTask = new SignupActivity$3(this);
        this.loginQueryCallback = new SignupActivity$7(this);
        this.mHandleError = new SignupActivity$9(this);
    }
    
    public static Intent createStartIntent(final Context context, final Intent intent) {
        return new Intent(context, (Class)SignupActivity.class);
    }
    
    @TargetApi(19)
    private void enableWebViewDebugging() {
        Log.d("SignupActivity", "Attempting to enable WebView Debugging. API Level: " + Build$VERSION.SDK_INT);
        if (Build$VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
    
    private void handleLoginComplete(final Status status) {
        String message;
        if (status.getMessage() != null) {
            message = status.getMessage();
        }
        else {
            message = "";
        }
        this.mSignupOngoing = false;
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "Login Complete - Status: " + status.getStatusCode() + " DisplayMsg: " + message);
        }
        final StatusCode statusCode = status.getStatusCode();
        if (status.isSucces() || statusCode == StatusCode.NRD_REGISTRATION_EXISTS) {
            this.showToast(2131493202);
            this.clearCookies();
        }
        else {
            this.provideDialog(this.getString(2131493258) + " (" + statusCode.getValue() + ")", this.mHandleError);
            if (this.mErrHandler != null) {
                final String string = "javascript:" + this.mErrHandler + "('" + statusCode.getValue() + "')";
                Log.d("SignupActivity", "Executing the following javascript:" + string);
                this.mWebView.loadUrl(string);
                this.mErrHandler = null;
            }
        }
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new SignupActivity$8(this));
    }
    
    private void reloadSignUp(final boolean b) {
        if (b) {
            this.clearCookies();
        }
        this.mWebViewClient.clearHistory();
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
    }
    
    private void setUpSignInView(final ServiceManager serviceManager) {
        this.setContentView(2130903198);
        this.mWebView = (WebView)this.findViewById(2131165680);
        this.mFlipper = (ViewFlipper)this.findViewById(2131165370);
        this.mESN = serviceManager.getESNProvider().getEsn();
        this.mESNPrefix = serviceManager.getESNProvider().getESNPrefix();
        this.mSoftwareVersion = serviceManager.getSoftwareVersion();
        this.mDeviceCategory = serviceManager.getDeviceCategory().getValue();
        this.mSignUpParams = serviceManager.getSignUpParams();
        final String signUpBootloader = this.mSignUpParams.getSignUpBootloader();
        if (this.getIntent().getExtras() != null) {}
        final WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        this.mAndroidJS = new SignupActivity$NFAndroidJS(this);
        this.mWebView.addJavascriptInterface((Object)this.mAndroidJS, "nfandroid");
        this.mWebView.setWebChromeClient((WebChromeClient)new SignupActivity$signUpWebChromeClient(this, null));
        this.mWebViewClient = new SignUpWebViewClient(this);
        this.mWebView.setWebViewClient((WebViewClient)this.mWebViewClient);
        this.mWebView.setOnTouchListener((View$OnTouchListener)new SignupActivity$6(this));
        this.mUiBoot = new Bootloader(serviceManager, signUpBootloader, this.getDeviceLanguage(), AndroidUtils.isNetflixPreloaded((Context)this), this.mSharedContextSessionUuid);
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
        ApmLogUtils.reportStartSharedContext((Context)this, this.mSharedContextSessionUuid);
        Log.d("SignupActivity", "Adding timeout for webview to load");
        this.mHandler.postDelayed(this.mJumpToSignInTask, this.mSignUpParams.getSignUpTimeout());
    }
    
    private void showToast(final int n) {
        this.showToast(this.getString(n));
    }
    
    private void startNextActivity(final Intent intent) {
        this.startActivity(intent);
        Log.d("SignupActivity", "Removing jumpToSignIn");
        this.mHandler.removeCallbacks(this.mJumpToSignInTask);
        this.overridePendingTransition(0, 0);
    }
    
    private void updateMenuItems() {
        this.runOnUiThread((Runnable)new SignupActivity$4(this));
    }
    
    public void clearCookies() {
        Log.d("SignupActivity", "Removing cookies");
        CookieSyncManager.createInstance(this.getBaseContext());
        CookieManager.getInstance().removeAllCookie();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SignupActivity$5(this);
    }
    
    public String getDeviceLanguage() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            return serviceManager.getCurrentAppLocale();
        }
        return "en";
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.nmLanding;
    }
    
    public boolean handleBackPressed() {
        if (this.mWebView == null || !this.mWebView.canGoBackOrForward(-1)) {
            return false;
        }
        if (!this.mWebView.canGoBackOrForward(-2) && this.mSignupMenuItem) {
            this.mWebView.goBack();
        }
        else {
            this.provideTwoButtonDialog(this.getString(2131493259), new SignupActivity$10(this));
        }
        return true;
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.i("SignupActivity", "New profile requested - starting profile selection activity...");
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
    }
    
    public void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        super.onCreateOptionsMenu(menu, menu2);
        MenuItem menuItem;
        if (this.mSignupMenuItem) {
            menuItem = menu.add((CharSequence)this.getString(2131493170));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$1(this));
        }
        else {
            menuItem = menu.add((CharSequence)this.getString(2131493171));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$2(this));
        }
        if (menuItem != null) {
            final View actionView = menuItem.getActionView();
            if (actionView != null && !actionView.isInTouchMode()) {
                actionView.requestFocus();
            }
        }
    }
    
    @Override
    protected void onDestroy() {
        ApmLogUtils.reportEndSharedContext((Context)this);
        super.onDestroy();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.mHandler.removeCallbacks(this.mJumpToSignInTask);
    }
    
    void provideDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131492994), runnable)));
    }
    
    void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, s, this.getString(2131492994), runnable, this.getString(2131493114), null)));
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    void showToast(final String s) {
        Log.v("SignupActivity", "Showing toast: " + s);
    }
    
    void webViewVisibility(final boolean b) {
        if (b != this.mWebViewVisibility) {
            Log.d("SignupActivity", "WebView visibility:" + this.mWebViewVisibility);
            this.mFlipper.showNext();
            this.mWebViewVisibility = !this.mWebViewVisibility;
        }
    }
}
