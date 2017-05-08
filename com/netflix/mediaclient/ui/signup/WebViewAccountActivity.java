// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.content.Intent;
import android.widget.Toast;
import com.netflix.mediaclient.NetflixApplication;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.ArrayList;
import android.content.Context;
import android.webkit.CookieSyncManager;
import android.webkit.CookieManager;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.os.Build$VERSION;
import com.netflix.mediaclient.webapi.WebApiCommand;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.ui.login.AccountActivity;

public abstract class WebViewAccountActivity extends AccountActivity
{
    private static final String COOKIE_SUFFIX = "; ";
    private static final String DEFAULT_LOCALE = "en";
    private static final String NETFLIX_DOMAIN = ".netflix.com";
    private static final String TAG = "WebViewAccountActivity";
    protected static final String USE_LATEST_COOKIES = "useDynecomCookies";
    private ViewFlipper mFlipper;
    private String mSharedContextSessionUuid;
    private Bootloader mUiBoot;
    private WebView mWebView;
    private AccountWebViewClient mWebViewClient;
    private boolean mWebViewLoaded;
    private boolean mWebViewVisibility;
    
    public WebViewAccountActivity() {
        this.mSharedContextSessionUuid = ConsolidatedLoggingUtils.createGUID();
    }
    
    private String[] appendCookies(final String s, final String[] array) {
        if (StringUtils.isEmpty(s)) {
            return array;
        }
        return this.updateToLatestNrmCookies(s, array);
    }
    
    private String[] buildNrmCookies(final NrmConfigData nrmConfigData) {
        final String[] array = new String[2];
        if (nrmConfigData == null || StringUtils.isEmpty(nrmConfigData.netflixId)) {
            return array;
        }
        array[0] = WebApiCommand.getNetflixIdName() + "=" + nrmConfigData.netflixId + "; ";
        array[1] = WebApiCommand.getSecureNetflixIdName() + "=" + nrmConfigData.secureNetflixId + "; ";
        return array;
    }
    
    private boolean cookiesIncludeNetflixId(final String s) {
        final boolean b = false;
        final String[] split = s.split(";");
        final int length = split.length;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            if (split[n].contains(WebApiCommand.getNetflixIdName())) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @TargetApi(19)
    private void enableWebViewDebugging() {
        Log.d("WebViewAccountActivity", "Attempting to enable WebView Debugging. API Level: " + Build$VERSION.SDK_INT);
        if (Build$VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
    
    private String getCookieFromList(final String[] array, final String s) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s2 = array[i];
                if (StringUtils.isNotEmpty(s2) && s2.contains(s)) {
                    return s2;
                }
            }
        }
        return null;
    }
    
    private void setCookieAndSync(final String[] array) {
        final CookieManager instance = CookieManager.getInstance();
        instance.removeAllCookie();
        for (int length = array.length, i = 0; i < length; ++i) {
            instance.setCookie(".netflix.com", array[i]);
        }
        CookieSyncManager.createInstance((Context)this).sync();
    }
    
    private void setNrmCookies(final NrmConfigData nrmConfigData, final boolean b) {
        final String cookie = CookieManager.getInstance().getCookie(".netflix.com");
        if (nrmConfigData == null || (StringUtils.isNotEmpty(cookie) && this.cookiesIncludeNetflixId(cookie) && !b)) {
            Log.d("WebViewAccountActivity", "using existing cookies. ");
            return;
        }
        this.setCookieAndSync(this.appendCookies(cookie, this.buildNrmCookies(nrmConfigData)));
    }
    
    private String[] updateToLatestNrmCookies(final String s, final String[] array) {
        final String[] split = s.split(";");
        final String cookieFromList = this.getCookieFromList(array, WebApiCommand.getSecureNetflixIdName());
        final String cookieFromList2 = this.getCookieFromList(array, WebApiCommand.getNetflixIdName());
        final ArrayList<String> list = new ArrayList<String>();
        for (int length = split.length, i = 0; i < length; ++i) {
            final String s2 = split[i];
            if (!StringUtils.isEmpty(s2)) {
                if (s2.contains(WebApiCommand.getSecureNetflixIdName())) {
                    if (StringUtils.isNotEmpty(cookieFromList)) {
                        list.add(cookieFromList);
                    }
                    else {
                        list.add(s2 + "; ");
                    }
                }
                else if (s2.contains(WebApiCommand.getNetflixIdName())) {
                    if (StringUtils.isNotEmpty(cookieFromList2)) {
                        list.add(cookieFromList2);
                    }
                    else {
                        list.add(s2 + "; ");
                    }
                }
                else {
                    list.add(s2 + "; ");
                }
            }
        }
        return list.toArray(new String[0]);
    }
    
    public void clearCookies() {
        Log.d("WebViewAccountActivity", "Removing cookies");
        CookieSyncManager.createInstance(this.getBaseContext());
        CookieManager.getInstance().removeAllCookie();
    }
    
    public abstract Object createJSBridge();
    
    public AccountWebViewClient createWebViewClient() {
        return new WebViewAccountActivity$1(this, this);
    }
    
    public Bootloader getBootLoader() {
        return this.mUiBoot;
    }
    
    public abstract String getBootUrl();
    
    public String getDeviceLanguage() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            return serviceManager.getCurrentAppLocale();
        }
        return "en";
    }
    
    public abstract Runnable getErrorHandler();
    
    public abstract Runnable getNextTask();
    
    public abstract long getTimeout();
    
    public WebView getWebView() {
        return this.mWebView;
    }
    
    public boolean handleBackPressed() {
        return this.mWebView != null && this.mWebView.canGoBackOrForward(-1);
    }
    
    public boolean isWebViewLoaded() {
        return this.mWebViewLoaded;
    }
    
    protected void noConnectivityWarning() {
        this.runOnUiThread((Runnable)new WebViewAccountActivity$4(this));
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903272);
        this.mWebView = (WebView)this.findViewById(2131690270);
        this.mFlipper = (ViewFlipper)this.findViewById(2131689831);
        this.mWebViewClient = this.createWebViewClient();
        AndroidUtils.setWindowSecureFlag(this);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        if (this.getHandler() != null) {
            this.getHandler().removeCallbacks(this.getNextTask());
        }
    }
    
    public void onWebViewLoaded() {
        Log.d("WebViewAccountActivity", "UI ready to interact");
        PerformanceProfiler.getInstance().endSession(Sessions.ONRAMP_TTR, null);
        if (this.getHandler() != null) {
            this.getHandler().removeCallbacks(this.getNextTask());
        }
        this.runOnUiThread((Runnable)new WebViewAccountActivity$3(this));
    }
    
    public void provideDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131231128), runnable)));
    }
    
    public void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, s, this.getString(2131231128), runnable, this.getString(2131230993), null)));
    }
    
    protected void reload(final boolean b) {
        if (b) {
            this.clearCookies();
        }
        this.mWebViewClient.clearHistory();
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
    }
    
    protected void setViews(final ServiceManager serviceManager, final boolean b) {
        final String bootUrl = this.getBootUrl();
        final WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        this.mWebView.addJavascriptInterface(this.createJSBridge(), "nfandroid");
        this.mWebView.setWebViewClient((WebViewClient)this.mWebViewClient);
        this.mWebView.setOnTouchListener((View$OnTouchListener)new WebViewAccountActivity$2(this));
        this.mUiBoot = new Bootloader(serviceManager, bootUrl, this.getDeviceLanguage(), AndroidUtils.isNetflixPreloaded((Context)this), b, this.mSharedContextSessionUuid);
        PerformanceProfiler.getInstance().startSession(Sessions.ONRAMP_TTR, null);
        Log.d("WebViewAccountActivity", "URL: " + this.mUiBoot.getUrl());
        ApmLogUtils.reportStartSharedContext((Context)this, this.mSharedContextSessionUuid);
        Log.d("WebViewAccountActivity", "All the cookies in a string:" + CookieManager.getInstance().getCookie(".netflix.com"));
        final Bundle extras = this.getIntent().getExtras();
        this.setNrmCookies(serviceManager.getConfiguration().getNrmConfigData(), Boolean.valueOf(extras != null && extras.getBoolean("useDynecomCookies")));
        this.mWebView.loadUrl(this.mUiBoot.getUrl());
        ApmLogUtils.reportStartSharedContext((Context)this, this.mSharedContextSessionUuid);
        Log.d("WebViewAccountActivity", "Adding timeout for webview to load");
        if (this.getHandler() != null) {
            this.getHandler().postDelayed(this.getNextTask(), this.getTimeout());
        }
    }
    
    protected void showToast(final int n) {
        this.showToast(this.getString(n));
    }
    
    protected void showToast(final String s) {
        if (NetflixApplication.isDebugToastEnabled()) {
            Toast.makeText((Context)this, (CharSequence)s, 1).show();
        }
    }
    
    protected void startNextActivity(final Intent intent) {
        this.startActivity(intent);
        Log.d("WebViewAccountActivity", "Removing jumpToSignIn");
        if (this.getHandler() != null) {
            this.getHandler().removeCallbacks(this.getNextTask());
            this.overridePendingTransition(0, 0);
        }
    }
    
    protected void webViewVisibility(final boolean b) {
        if (b != this.mWebViewVisibility) {
            Log.d("WebViewAccountActivity", "WebView visibility:" + this.mWebViewVisibility);
            this.mFlipper.showNext();
            this.mWebViewVisibility = !this.mWebViewVisibility;
        }
    }
}
