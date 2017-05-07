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
import com.netflix.mediaclient.android.app.Status;
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

@SuppressLint({ "SetJavaScriptEnabled" })
public class SignupActivity extends AccountActivity implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    private static final String BOOTURL = "booturl";
    private static final String DEFAULT_LOCALE = "en";
    private static final int RC_SAVE = 1;
    private static final String TAG = "SignupActivity";
    private GoogleApiClient credentialsApiClient;
    private final SimpleManagerCallback loginQueryCallback;
    private SignupActivity$NFAndroidJS mAndroidJS;
    private String mDeviceCategory;
    private String mESN;
    private String mESNPrefix;
    private String mEmail;
    private String mErrHandler;
    private ViewFlipper mFlipper;
    Runnable mHandleError;
    private Handler mHandler;
    private boolean mIsLoginActivityInFocus;
    Runnable mJumpToSignInTask;
    private String mPassword;
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
    private boolean saveCredentials;
    
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
    
    public static Intent createShowIntent(final Context context) {
        final Intent intent = new Intent(context, (Class)SignupActivity.class);
        intent.addFlags(67141632);
        return intent;
    }
    
    public static Intent createStartIntent(final Context context, final Intent intent) {
        return new Intent(context, (Class)SignupActivity.class);
    }
    
    private void doSaveCredentials(final GoogleApiClient googleApiClient) {
        // monitorenter(this)
        Label_0017: {
            if (googleApiClient != null) {
                break Label_0017;
            }
        Block_3_Outer:
            while (true) {
                try {
                    Log.d("SignupActivity", "GPS client is null, unable to try to save credentials");
                    Label_0014: {
                        return;
                    }
                    // iftrue(Label_0073:, !StringUtils.isEmpty(this.mEmail) && !StringUtils.isEmpty(this.mPassword))
                    while (true) {
                        while (true) {
                            Log.w("SignupActivity", "Credential is empty, do not save it.");
                            return;
                            Log.d("SignupActivity", "Trying to save credentials to GPS");
                            this.saveCredentials = false;
                            continue Block_3_Outer;
                        }
                        continue;
                    }
                }
                // iftrue(Label_0014:, !this.saveCredentials)
                finally {
                }
                // monitorexit(this)
                Label_0073: {
                    final GoogleApiClient googleApiClient2;
                    Auth.CredentialsApi.save(googleApiClient2, new Credential$Builder(this.mEmail).setPassword(this.mPassword).build()).setResultCallback(new SignupActivity$11(this));
                }
            }
        }
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
            this.showToast(2131165594);
            this.clearCookies();
        }
        else {
            this.provideDialog(this.getString(2131165679) + " (" + statusCode.getValue() + ")", this.mHandleError);
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
    
    private void resolveResult(final com.google.android.gms.common.api.Status status) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "Google Play Services: Resolving: " + status);
        }
        if (status != null && status.hasResolution()) {
            Log.d("SignupActivity", "Google Play Services: STATUS: RESOLVING");
            try {
                status.startResolutionForResult(this, 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.e("SignupActivity", "Google Play Services: STATUS: Failed to send resolution.", (Throwable)ex);
                return;
            }
        }
        Log.e("SignupActivity", "Google Play Services: STATUS: FAIL");
        this.showDebugToast("Google Play Services: Could Not Resolve Error");
    }
    
    private void saveCredentials() {
        while (true) {
            Label_0047: {
                synchronized (this) {
                    if (!this.shouldUseAutoLogin()) {
                        Log.d("SignupActivity", "SmartLock is disabled or device does not support GPS");
                    }
                    else {
                        if (this.credentialsApiClient != null) {
                            break Label_0047;
                        }
                        Log.d("SignupActivity", "GPS client unavailable, unable to attempt to save credentials");
                    }
                    return;
                }
            }
            final GoogleApiClient googleApiClient;
            if (Log.isLoggable()) {
                Log.d("SignupActivity", "GPS client is connected " + googleApiClient.isConnected() + " or connecting " + googleApiClient.isConnecting());
            }
            this.saveCredentials = true;
            if (googleApiClient.isConnected()) {
                this.doSaveCredentials(googleApiClient);
            }
        }
    }
    
    private void setUpSignInView(final ServiceManager serviceManager) {
        this.setContentView(2130903214);
        this.mWebView = (WebView)this.findViewById(2131624492);
        this.mFlipper = (ViewFlipper)this.findViewById(2131624168);
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
    
    private boolean shouldUseAutoLogin() {
        return this.isAutoLoginEnabled() && DeviceUtils.canUseGooglePlayServices((Context)this);
    }
    
    private void showDebugToast(final String s) {
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
    
    private void storeNrmNetflixIdInPref(final String s) {
        String s2 = s;
        if (StringUtils.isEmpty(s)) {
            Log.d("SignupActivity", "nrmNetflixId is empty, clearing in preference");
            s2 = "";
        }
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "nrmNetlfixId from webui: " + s2);
        }
        PreferenceUtils.putStringPref(this.getApplicationContext(), "nrm_netflix_id", s2);
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
    protected CustomerServiceLogging$EntryPoint getEntryPoint() {
        return CustomerServiceLogging$EntryPoint.nonMemberLanding;
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
            this.provideTwoButtonDialog(this.getString(2131165678), new SignupActivity$10(this));
        }
        return true;
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        if (this.mIsLoginActivityInFocus) {
            Log.i("SignupActivity", "Login activity is in focus, leave it to finish all account activities when it is ready");
            return;
        }
        Log.i("SignupActivity", "New profile requested - starting profile selection activity...");
        this.startActivity(ProfileSelectionActivity.createStartIntent((Context)this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    protected boolean isAutoLoginEnabled() {
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        Log.d("SignupActivity", "onConnected");
        this.doSaveCredentials(this.credentialsApiClient);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "onConnectionFailed:" + connectionResult);
        }
        this.credentialsApiClient = null;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "onConnectionSuspended:" + n);
        }
        if (this.credentialsApiClient != null) {
            this.credentialsApiClient.reconnect();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        AndroidUtils.setWindowSecureFlag(this);
        if (this.shouldUseAutoLogin()) {
            (this.credentialsApiClient = new GoogleApiClient$Builder((Context)this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build()).connect();
        }
    }
    
    public void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MenuItem menuItem;
        if (this.mSignupMenuItem) {
            menuItem = menu.add((CharSequence)this.getString(2131165593));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$1(this));
        }
        else {
            menuItem = menu.add((CharSequence)this.getString(2131165595));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$2(this));
        }
        if (menuItem != null) {
            final View actionView = menuItem.getActionView();
            if (actionView != null && !actionView.isInTouchMode()) {
                actionView.requestFocus();
            }
        }
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    protected void onDestroy() {
        ApmLogUtils.reportEndSharedContext((Context)this);
        super.onDestroy();
        if (this.credentialsApiClient != null) {
            this.credentialsApiClient.disconnect();
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        this.mIsLoginActivityInFocus = false;
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.mHandler.removeCallbacks(this.mJumpToSignInTask);
    }
    
    void provideDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131165543), runnable)));
    }
    
    void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, s, this.getString(2131165543), runnable, this.getString(2131165413), null)));
    }
    
    @Override
    public boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showHelpInMenu() {
        return true;
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
