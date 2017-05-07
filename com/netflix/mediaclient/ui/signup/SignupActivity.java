// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.webkit.ConsoleMessage;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import org.json.JSONObject;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.net.Uri;
import java.util.Locale;
import android.webkit.JavascriptInterface;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.os.Bundle;
import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.Log;
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
    private NFAndroidJS mAndroidJS;
    private String mDeviceCategory;
    private String mESN;
    private String mESNPrefix;
    private String mErrHandler;
    private ViewFlipper mFlipper;
    Runnable mHandleError;
    private Handler mHandler;
    Runnable mJumpToSignInTask;
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
        this.mJumpToSignInTask = new Runnable() {
            @Override
            public void run() {
                Log.d("SignupActivity", "Timeout triggered, switching to LoginActivity");
                if (!SignupActivity.this.mSignupLoaded) {
                    SignupActivity.this.startNextActivity(LoginActivity.createStartIntent((Context)SignupActivity.this));
                    SignupActivity.this.finish();
                }
            }
        };
        this.loginQueryCallback = new SimpleManagerCallback() {
            @Override
            public void onLoginComplete(final int n, final String s) {
                SignupActivity.this.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        SignupActivity.this.handleLoginComplete(n, s);
                    }
                });
            }
        };
        this.mHandleError = new Runnable() {
            @Override
            public void run() {
                Log.d("SignupActivity", "Handling error during signup");
                SignupActivity.this.clearCookies();
                SignupActivity.this.startNextActivity(LoginActivity.createStartIntent((Context)SignupActivity.this));
                SignupActivity.this.finish();
            }
        };
    }
    
    public static Intent createStartIntent(final Context context, final Intent intent) {
        return new Intent(context, (Class)SignupActivity.class);
    }
    
    private void handleLoginComplete(final int n, String string) {
        if (string == null) {
            string = "";
        }
        this.mSignupOngoing = false;
        Log.d("SignupActivity", "Login Complete - Status: " + n + " DisplayMsg: " + string);
        if (n == 0 || n == -41) {
            this.showToast(2131493216);
            this.clearCookies();
        }
        else {
            this.provideDialog(this.getString(2131493274) + " (" + n + ")", this.mHandleError);
            if (this.mErrHandler != null) {
                string = "javascript:" + this.mErrHandler + "('" + n + "')";
                Log.d("SignupActivity", "Executing the following javascript:" + string);
                this.mWebView.loadUrl(string);
                this.mErrHandler = null;
            }
        }
    }
    
    private void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                SignupActivity.this.displayDialog(AlertDialogFactory.createDialog((Context)SignupActivity.this, SignupActivity.this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, SignupActivity.this.getString(2131493134), SignupActivity.this.getString(17039370), null)));
            }
        });
    }
    
    private void reloadSignUp(final boolean b) {
        if (b) {
            this.clearCookies();
        }
        this.mWebViewClient.clearHistory();
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
    }
    
    private void setUpSignInView(final ServiceManager serviceManager) {
        this.setContentView(2130903165);
        this.mWebView = (WebView)this.findViewById(2131165593);
        this.mFlipper = (ViewFlipper)this.findViewById(2131165513);
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
        this.mAndroidJS = new NFAndroidJS();
        this.mWebView.addJavascriptInterface((Object)this.mAndroidJS, "nfandroid");
        this.mWebView.setWebChromeClient((WebChromeClient)new signUpWebChromeClient());
        this.mWebViewClient = new SignUpWebViewClient(this);
        this.mWebView.setWebViewClient((WebViewClient)this.mWebViewClient);
        this.mWebView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1: {
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                    }
                }
                return false;
            }
        });
        this.mUiBoot = new Bootloader(serviceManager, signUpBootloader, this.getDeviceLanguage(), AndroidUtils.isNetflixPreloaded((Context)this));
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
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
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                SignupActivity.this.invalidateOptionsMenu();
            }
        });
    }
    
    public void clearCookies() {
        Log.d("SignupActivity", "Removing cookies");
        CookieSyncManager.createInstance(this.getBaseContext());
        CookieManager.getInstance().removeAllCookie();
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final int n) {
                Log.d("SignupActivity", "ServiceManager ready: " + n);
                ThreadUtils.assertOnMain();
                SignupActivity.this.setUpSignInView(serviceManager);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
                Log.e("SignupActivity", "NetflixService is NOT available!");
            }
        };
    }
    
    public String getDeviceLanguage() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            return serviceManager.getCurrentAppLocale();
        }
        return "en";
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.nmLanding;
    }
    
    @Override
    protected void handleProfileReadyToSelect() {
        Log.i("SignupActivity", "New profile requested - starting profile selection activity...");
        this.startActivity(ProfileSelectionActivity.createStartIntent(this));
        AccountActivity.finishAllAccountActivities((Context)this);
    }
    
    @Override
    public void onBackPressed() {
        if (this.mWebView == null || !this.mWebView.canGoBackOrForward(-1)) {
            super.onBackPressed();
            return;
        }
        if (!this.mWebView.canGoBackOrForward(-2) && this.mSignupMenuItem) {
            this.mWebView.goBack();
            return;
        }
        this.provideTwoButtonDialog(this.getString(2131493275), new Runnable() {
            @Override
            public void run() {
                SignupActivity.this.reloadSignUp(false);
            }
        });
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        this.getNetflixActionBar().setBackgroundResource(2130837597);
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuItem menuItem;
        if (this.mSignupMenuItem) {
            menuItem = menu.add((CharSequence)this.getString(2131493184));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    Log.d("SignupActivity", "User tapped sign-in button");
                    LogUtils.reportLoginActionStarted((Context)SignupActivity.this, null, SignupActivity.this.getUiScreen());
                    SignupActivity.this.startNextActivity(LoginActivity.createStartIntent((Context)SignupActivity.this));
                    return true;
                }
            });
        }
        else {
            menuItem = menu.add((CharSequence)this.getString(2131493185));
            menuItem.setShowAsAction(1);
            menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    SignupActivity.this.reloadSignUp(true);
                    return true;
                }
            });
        }
        if (menuItem != null) {
            final View actionView = menuItem.getActionView();
            if (actionView != null && !actionView.isInTouchMode()) {
                actionView.requestFocus();
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.mHandler.removeCallbacks(this.mJumpToSignInTask);
    }
    
    void provideDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory.AlertDialogDescriptor(null, s, this.getString(17039370), runnable)));
    }
    
    void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, (AlertDialogFactory.AlertDialogDescriptor)new AlertDialogFactory.TwoButtonAlertDialogDescriptor(null, s, this.getString(17039370), runnable, this.getString(17039360), null)));
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
    
    public class NFAndroidJS
    {
        @JavascriptInterface
        public String getDeviceCategory() {
            if (SignupActivity.this.mDeviceCategory != null) {
                return SignupActivity.this.mDeviceCategory;
            }
            return "phone";
        }
        
        @JavascriptInterface
        public String getESN() {
            if (SignupActivity.this.mESN != null) {
                return SignupActivity.this.mESN;
            }
            return "";
        }
        
        @JavascriptInterface
        public String getESNPrefix() {
            if (SignupActivity.this.mESNPrefix != null) {
                return SignupActivity.this.mESNPrefix;
            }
            return "";
        }
        
        @JavascriptInterface
        public String getLanguage() {
            return SignupActivity.this.getDeviceLanguage();
        }
        
        @JavascriptInterface
        public String getSoftwareVersion() {
            if (SignupActivity.this.mSoftwareVersion != null) {
                return SignupActivity.this.mSoftwareVersion;
            }
            return "";
        }
        
        @JavascriptInterface
        public String isNetflixPreloaded() {
            if (AndroidUtils.isNetflixPreloaded((Context)SignupActivity.this)) {
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
            SignupActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
        }
        
        @JavascriptInterface
        public void loginCompleted() {
            Log.d("SignupActivity", "loginCompleted, noop");
        }
        
        @JavascriptInterface
        public void loginToApp(final String s, final String s2) {
            if (SignupActivity.this.mSignupOngoing) {
                Log.d("SignupActivity", "loginToApp ongoing, returning NULL operation");
                return;
            }
            Log.d("SignupActivity", "Login with Tokens " + s + " ErrHandler: " + s2);
            SignupActivity.this.mErrHandler = s2;
            if (!ConnectivityUtils.isConnectedOrConnecting((Context)SignupActivity.this)) {
                SignupActivity.this.noConnectivityWarning();
                return;
            }
            try {
                final JSONObject jsonObject = new JSONObject(s);
                Log.d("SignupActivity", "NetflixId: " + jsonObject.getString("NetflixId"));
                Log.d("SignupActivity", "SecureNetflixId: " + jsonObject.getString("SecureNetflixId"));
                final ActivationTokens activationTokens = new ActivationTokens(jsonObject);
                final ServiceManager serviceManager = SignupActivity.this.getServiceManager();
                if (serviceManager != null && serviceManager.isReady()) {
                    serviceManager.loginUserByTokens(activationTokens, SignupActivity.this.loginQueryCallback);
                    SignupActivity.this.mSignupOngoing = true;
                    SignupActivity.this.runOnUiThread((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            Log.d("SignupActivity", "Disabling webview visibility");
                            SignupActivity.this.webViewVisibility(false);
                        }
                    });
                    return;
                }
            }
            catch (JSONException ex) {
                Log.e("SignupActivity", "Failed to LoginToApp");
                ex.printStackTrace();
                SignupActivity.this.mSignupOngoing = false;
                SignupActivity.this.provideDialog(SignupActivity.this.getString(2131493274), SignupActivity.this.mHandleError);
                return;
            }
            Log.d("SignupActivity", "loginToApp, invalid state to Login, bailing out");
        }
        
        @JavascriptInterface
        public void notifyReady() {
            Log.d("SignupActivity", "Signup UI ready to interact");
            SignupActivity.this.mHandler.removeCallbacks(SignupActivity.this.mJumpToSignInTask);
            SignupActivity.this.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (!SignupActivity.this.mSignupLoaded) {
                        SignupActivity.this.webViewVisibility(true);
                        SignupActivity.this.mSignupLoaded = true;
                    }
                }
            });
        }
        
        @JavascriptInterface
        public void setLanguage(final String currentAppLocale) {
            final boolean equals = currentAppLocale.equals(this.getLanguage());
            Log.d("SignupActivity", "Update language to " + currentAppLocale);
            if (!equals) {
                final ServiceManager serviceManager = SignupActivity.this.getServiceManager();
                if (serviceManager == null || !serviceManager.isReady()) {
                    Log.w("SignupActivity", "setLanguage  failed, invalid state");
                    return;
                }
                SignupActivity.this.getServiceManager().setCurrentAppLocale(currentAppLocale);
                ((NetflixApplication)SignupActivity.this.getApplication()).refreshLocale(currentAppLocale);
                SignupActivity.this.updateMenuItems();
            }
        }
        
        @JavascriptInterface
        public void showSignIn() {
            Log.d("SignupActivity", "Show SignIn: ");
            SignupActivity.this.mSignupMenuItem = true;
            SignupActivity.this.updateMenuItems();
        }
        
        @JavascriptInterface
        public void showSignOut() {
            Log.d("SignupActivity", "Show SignOut");
            SignupActivity.this.mSignupMenuItem = false;
            SignupActivity.this.updateMenuItems();
        }
        
        @JavascriptInterface
        public void signupCompleted() {
            Log.d("SignupActivity", "signupCompleted, report");
            LogUtils.reportSignUpOnDevice((Context)SignupActivity.this);
        }
        
        @JavascriptInterface
        public void supportsSignUp(final String s) {
            Log.d("SignupActivity", "SupportSignUp flag: " + s);
        }
        
        @JavascriptInterface
        public void toSignIn() {
            Log.d("SignupActivity", "Redirecting to Login screen");
            SignupActivity.this.startNextActivity(LoginActivity.createStartIntent((Context)SignupActivity.this));
            SignupActivity.this.finish();
        }
    }
    
    private class signUpWebChromeClient extends WebChromeClient
    {
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            Log.i("JavaScript", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
    }
}
