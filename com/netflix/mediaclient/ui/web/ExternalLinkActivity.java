// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.web;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.util.AndroidUtils;
import android.webkit.URLUtil;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import android.content.Context;
import android.webkit.WebView;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class ExternalLinkActivity extends NetflixActivity
{
    private static final String TAG = "ExternalWeb";
    private static final String TAG_JS = "ExternalLinkActivity_js";
    private static final String URL = "url";
    private ViewFlipper mFlipper;
    private WebView mWebView;
    private ExternalLinkActivity$ExternalWebViewClient mWebViewClient;
    private boolean mWebViewVisibility;
    
    public static Intent createStartIntent(final Context context, final String s) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("URL is empty!");
        }
        if (!URLUtil.isNetworkUrl(s)) {
            throw new IllegalArgumentException("Url " + s + " is not a network URL!");
        }
        final Intent intent = new Intent(context, (Class)ExternalLinkActivity.class);
        intent.putExtra("url", s);
        return intent;
    }
    
    @SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
    private void loadUrl(final String s) {
        final WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        if (AndroidUtils.getAndroidVersion() >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSupportMultipleWindows(false);
        this.mWebView.setWebChromeClient((WebChromeClient)new ExternalLinkActivity$ExternalWebClient(this, null));
        this.mWebViewClient = new ExternalLinkActivity$ExternalWebViewClient(this, null);
        this.mWebView.setWebViewClient((WebViewClient)this.mWebViewClient);
        this.mWebView.setOnTouchListener((View$OnTouchListener)new ExternalLinkActivity$1(this));
        this.mWebViewClient.clearHistory();
        this.mWebView.loadUrl(s);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ExternalLinkActivity$2(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.externalLink;
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903133);
        this.mWebView = (WebView)this.findViewById(2131689856);
        this.mFlipper = (ViewFlipper)this.findViewById(2131689855);
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.w("ExternalWeb", "Received new intent when UI is visible");
        if (intent == null) {
            Log.w("ExternalWeb", "Started with no intent! It should no happen!");
            return;
        }
        final String stringExtra = intent.getStringExtra("url");
        if (!URLUtil.isNetworkUrl(stringExtra)) {
            Log.e("ExternalWeb", "Url " + stringExtra + " is not a network URL! Stay!");
            return;
        }
        this.setWebViewVisibility(false);
        this.mWebViewClient.clearHistory();
        this.mWebView.loadUrl(stringExtra);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        final Intent intent = this.getIntent();
        if (intent == null) {
            Log.w("ExternalWeb", "Started with no intent! It should no happen!");
            this.finish();
            return;
        }
        final String stringExtra = intent.getStringExtra("url");
        if (!URLUtil.isNetworkUrl(stringExtra)) {
            Log.e("ExternalWeb", "Url " + stringExtra + " is not a network URL! Destroy activity!");
            this.finish();
            return;
        }
        this.loadUrl(stringExtra);
    }
    
    void setWebViewVisibility(final boolean b) {
        if (b != this.mWebViewVisibility) {
            if (Log.isLoggable()) {
                Log.d("ExternalWeb", "WebView visibility:" + this.mWebViewVisibility);
            }
            this.mFlipper.showNext();
            this.mWebViewVisibility = !this.mWebViewVisibility;
        }
    }
    
    @Override
    public boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return false;
    }
}
