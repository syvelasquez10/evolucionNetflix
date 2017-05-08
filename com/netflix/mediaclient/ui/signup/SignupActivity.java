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
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
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
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
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

@SuppressLint({ "SetJavaScriptEnabled" })
public class SignupActivity extends AccountActivity implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    private static final String BOOTURL = "booturl";
    private static final String DEFAULT_LOCALE = "en";
    private static final int RC_BILLING = 2;
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
    private boolean mIsPlayBillingPresent;
    Runnable mJumpToSignInTask;
    private String mPassword;
    private PlayBilling mPlayBilling;
    private boolean mPlayBillingInitDone;
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
        this.mJumpToSignInTask = new SignupActivity$4(this);
        this.loginQueryCallback = new SignupActivity$8(this);
        this.mHandleError = new SignupActivity$10(this);
    }
    
    private boolean canProceedWithPlayBilling() {
        return this.mPlayBilling != null && this.mPlayBilling.isPlayBillingReady();
    }
    
    private boolean canShowPlayBillingOption(final Context context) {
        boolean b = true;
        final boolean b2 = this.mIsPlayBillingPresent && this.mPlayBillingInitDone && this.mPlayBilling != null && this.mPlayBilling.isPlayBillingReady() && this.isPlayBillingEnabledInConifg(context);
        if (Log.isLoggable()) {
            final boolean mIsPlayBillingPresent = this.mIsPlayBillingPresent;
            final boolean mPlayBillingInitDone = this.mPlayBillingInitDone;
            if (this.mPlayBilling == null || !this.mPlayBilling.isPlayBillingReady()) {
                b = false;
            }
            Log.d("SignupActivity", String.format("canShow? %b, mIsPlayBillingPresent:%b, mPlayBillingInitDone:%b, ready:%b, enabledInConfig:%b", b2, mIsPlayBillingPresent, mPlayBillingInitDone, b, this.isPlayBillingEnabledInConifg(context)));
        }
        return b2;
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
        Label_0018: {
            if (googleApiClient != null) {
                break Label_0018;
            }
            while (true) {
                try {
                    Log.d("SignupActivity", "GPS client is null, unable to try to save credentials");
                    Label_0015: {
                        return;
                    }
                    while (true) {
                        Log.w("SignupActivity", "Credential is empty, do not save it.");
                        return;
                        Log.d("SignupActivity", "Trying to save credentials to GPS");
                        this.saveCredentials = false;
                        continue;
                    }
                }
                // iftrue(Label_0015:, !this.saveCredentials)
                // iftrue(Label_0076:, !StringUtils.isEmpty(this.mEmail) && !StringUtils.isEmpty(this.mPassword))
                finally {
                }
                // monitorexit(this)
                Label_0076: {
                    SignInLogUtils.reportCredentialStoreSessionStarted((Context)this, SignInLogging$CredentialService.GooglePlayService);
                }
                final GoogleApiClient googleApiClient2;
                Auth.CredentialsApi.save(googleApiClient2, new Credential$Builder(this.mEmail).setPassword(this.mPassword).build()).setResultCallback(new SignupActivity$12(this));
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
            this.showToast(2131231177);
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this, SignInLogging$SignInType.tokenActivate, IClientLogging$CompletionReason.success, null);
            this.clearCookies();
        }
        else {
            SignInLogUtils.reportSignInRequestSessionEnded((Context)this, SignInLogging$SignInType.tokenActivate, IClientLogging$CompletionReason.failed, status.getError());
            this.provideDialog(this.getString(2131231282) + " (" + statusCode.getValue() + ")", this.mHandleError);
            if (this.mErrHandler != null) {
                final String string = "javascript:" + this.mErrHandler + "('" + statusCode.getValue() + "')";
                Log.d("SignupActivity", "Executing the following javascript:" + string);
                this.mWebView.loadUrl(string);
                this.mErrHandler = null;
            }
        }
    }
    
    private boolean isPlayBillingAvailable() {
        return DeviceUtils.canUseGooglePlayServices((Context)this);
    }
    
    public static boolean isSignupDisabledDevice() {
        return Build.MANUFACTURER.toLowerCase().contains("amazon");
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new SignupActivity$9(this));
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
                SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, null);
                return;
            }
        }
        Log.e("SignupActivity", "Google Play Services: STATUS: FAIL");
        this.showDebugToast("Google Play Services: Could Not Resolve Error");
        SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, null);
    }
    
    private String sanitizeInputString(final String s) {
        if (StringUtils.isNotEmpty(s) && !StringUtils.safeEquals(s, "undefined")) {
            return s;
        }
        return "";
    }
    
    private void saveCredentials() {
        while (true) {
            Label_0053: {
                synchronized (this) {
                    if (!LoginUtils.shouldUseAutoLogin((Context)this)) {
                        if (Log.isLoggable()) {
                            Log.d("SignupActivity", "SmartLock is disabled or device does not support GPS");
                        }
                    }
                    else {
                        if (this.credentialsApiClient != null) {
                            break Label_0053;
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
    
    private void setUpSignInView(final ServiceManager serviceManager, final boolean b) {
        this.setContentView(2130903264);
        this.mWebView = (WebView)this.findViewById(2131690262);
        this.mFlipper = (ViewFlipper)this.findViewById(2131689833);
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
        this.mWebView.setOnTouchListener((View$OnTouchListener)new SignupActivity$7(this));
        this.mUiBoot = new Bootloader(serviceManager, signUpBootloader, this.getDeviceLanguage(), AndroidUtils.isNetflixPreloaded((Context)this), b, this.mSharedContextSessionUuid);
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
        this.runOnUiThread((Runnable)new SignupActivity$5(this));
    }
    
    public void clearCookies() {
        Log.d("SignupActivity", "Removing cookies");
        CookieSyncManager.createInstance(this.getBaseContext());
        CookieManager.getInstance().removeAllCookie();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new SignupActivity$6(this);
    }
    
    @Override
    public void finish() {
        super.finish();
        PerformanceProfiler.getInstance().endSession(Sessions.SIGN_UP, null);
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
            this.provideTwoButtonDialog(this.getString(2131231281), new SignupActivity$11(this));
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
    
    public boolean isPlayBillingEnabledInConifg(final Context context) {
        final boolean b = true;
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady() && this.getServiceManager().getConfiguration() != null) {
            final boolean playBillingDisabled = serviceManager.getConfiguration().isPlayBillingDisabled();
            final boolean ignorePreloadForPlayBilling = serviceManager.getConfiguration().ignorePreloadForPlayBilling();
            if (Log.isLoggable()) {
                Log.d("SignupActivity", String.format("isPlayBillingEnabledInConifg - playBillingDisabled:%b, ignorePreinstall:%b, isPreInstalled:%b", playBillingDisabled, ignorePreloadForPlayBilling, AndroidUtils.isNetflixPreloaded(context)));
            }
            if (!playBillingDisabled) {
                boolean b2 = b;
                if (!AndroidUtils.isNetflixPreloaded(context)) {
                    return b2;
                }
                b2 = b;
                if (ignorePreloadForPlayBilling) {
                    return b2;
                }
            }
            return false;
        }
        Log.d("SignupActivity", "serviceMgr & configurationAgent is not ready.. disable play billing");
        return false;
    }
    
    @Override
    protected void lockScreenOrientation() {
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "onActivityResult:" + n + ":" + n2 + ":" + intent);
        }
        if (n == 1) {
            if (n2 == -1) {
                this.showDebugToast("Account credentials saved!");
                SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.success, null);
                return;
            }
            this.showDebugToast("Failed to save account credentials!");
            SignInLogUtils.reportCredentialStoreSessionEnded((Context)this, SignInLogging$CredentialService.GooglePlayService, IClientLogging$CompletionReason.failed, SignInLogUtils.credentialRequestResultToError(n2));
        }
        else {
            if (n == 2) {
                if (Log.isLoggable()) {
                    final StringBuilder append = new StringBuilder().append("onActivityResult:").append(n).append(":").append(n2).append(":");
                    Object extras;
                    if (intent != null) {
                        extras = intent.getExtras();
                    }
                    else {
                        extras = "";
                    }
                    Log.d("SignupActivity", append.append(extras).toString());
                }
                this.mPlayBilling.handleActivityResult(n, n2, intent);
                return;
            }
            Log.e("SignupActivity", "onActivityResult: uknown request code" + n);
        }
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
        if (bundle == null) {
            PerformanceProfiler.getInstance().startSession(Sessions.SIGN_UP, null);
        }
        if (isSignupDisabledDevice()) {
            if (Log.isLoggable()) {
                Log.d("SignupActivity", "found signUp disabled device ... goto login");
            }
            this.startNextActivity(LoginActivity.createStartIntent((Context)this));
            this.finish();
        }
        else {
            AndroidUtils.setWindowSecureFlag(this);
            if (LoginUtils.shouldUseAutoLogin((Context)this)) {
                (this.credentialsApiClient = new GoogleApiClient$Builder((Context)this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build()).connect();
            }
            this.mIsPlayBillingPresent = this.isPlayBillingAvailable();
            this.mPlayBillingInitDone = !this.mIsPlayBillingPresent;
            if (this.mIsPlayBillingPresent) {
                Log.d("SignupActivity", "play billing is available. starting...");
                (this.mPlayBilling = new PlayBilling((Context)this, this.getHandler())).startSetup(new SignupActivity$1(this));
            }
        }
    }
    
    public void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MenuItem menuItem;
        if (this.mSignupMenuItem) {
            menuItem = menu.add((CharSequence)this.getString(2131231176));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$2(this));
        }
        else {
            menuItem = menu.add((CharSequence)this.getString(2131231178));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new SignupActivity$3(this));
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
        if (this.mPlayBilling != null) {
            Log.d("SignupActivity", "Destroying inAppBilling.");
            this.mPlayBilling.dispose();
            this.mPlayBilling = null;
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
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131231126), runnable)));
    }
    
    void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, s, this.getString(2131231126), runnable, this.getString(2131230991), null)));
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
        if (Log.isLoggable()) {
            Log.v("SignupActivity", "Showing toast: " + s);
        }
        if (NetflixApplication.isDebugToastEnabled()) {
            Toast.makeText((Context)this, (CharSequence)s, 1).show();
        }
    }
    
    public void testSkuDetails(final String s, final PlayBillingCallback playBillingCallback) {
        final String[] split = s.split(",");
        final ArrayList<String> list = new ArrayList<String>();
        for (int length = split.length, i = 0; i < length; ++i) {
            list.add(split[i].trim());
        }
        if (Log.isLoggable()) {
            Log.d("SignupActivity", String.format("testSkuDetails, input:%s,  List:%s", s, list));
        }
        this.mPlayBilling.getSkuDetails(list, playBillingCallback);
    }
    
    public void testSkuPurchase(final String s, final PlayBillingCallback playBillingCallback) {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", String.format("testSkuPurchase, sku:%s", s));
        }
        this.mPlayBilling.purchase(this, s, "raviPayload", 200, "raviAccountId", 2, playBillingCallback);
    }
    
    void webViewVisibility(final boolean b) {
        if (b != this.mWebViewVisibility) {
            Log.d("SignupActivity", "WebView visibility:" + this.mWebViewVisibility);
            this.mFlipper.showNext();
            this.mWebViewVisibility = !this.mWebViewVisibility;
        }
    }
}
