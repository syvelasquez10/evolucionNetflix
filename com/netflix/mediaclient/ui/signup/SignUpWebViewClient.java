// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.net.Uri;
import com.netflix.mediaclient.protocol.nflx.NflxHandler;
import android.webkit.WebViewClient;

class SignUpWebViewClient extends WebViewClient
{
    private static final String TAG = "SignupActivity";
    private boolean mSecurityFailure;
    private final SignupActivity mUi;
    private final NflxHandler nflxHandler;
    
    SignUpWebViewClient(final SignupActivity mUi) {
        this.nflxHandler = new NflxHandler();
        this.mUi = mUi;
    }
    
    private NflxHandler.Response canHandleUri(final String s) {
        try {
            return this.nflxHandler.handleUri(this.mUi, null, null, Uri.parse(s), 0L);
        }
        catch (Throwable t) {
            Log.e("SignupActivity", "Failed to parse nflx url ", t);
            return NflxHandler.Response.NOT_HANDLING;
        }
    }
    
    private boolean isImage(final String s) {
        boolean b;
        if (s.startsWith("data:image") || s.endsWith(".png") || s.contains(".png?")) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (b || s.endsWith(".jpg") || s.contains(".jpg?")) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        return b2 || s.endsWith(".gif") || s.contains(".gif?");
    }
    
    private boolean isRealUrl(final String s) {
        return s != null && s.trim().toLowerCase(Locale.US).startsWith("https");
    }
    
    private void securityCheck(String string) {
        if (this.mSecurityFailure) {
            Log.e("SignupActivity", "We already failed. Ignoring to prevent multiple dialogs! URL: " + string);
            this.mUi.showToast("Loading insecure resource, ERROR:" + string);
        }
        else {
            Log.d("SignupActivity", "Security check for URL: " + string);
            if (string != null) {
                final String trim = string.toLowerCase(Locale.US).trim();
                if (!this.isImage(trim) && !trim.startsWith("https")) {
                    this.mSecurityFailure = true;
                    this.mUi.showToast("Loading insecure resource, ERROR:" + string);
                    Log.e("SignupActivity", "Trying to load from unsecure location in release build. Prevent loading, security breach! URL: " + string);
                    string = this.mUi.getString(2131296646);
                    this.mUi.provideDialog(string, this.mUi.mHandleError);
                }
            }
        }
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        if (Log.isLoggable("SignupActivity", 3)) {
            Log.d("SignupActivity", "onLoadResource: disabling security check from " + s);
        }
        this.securityCheck(s);
        super.onLoadResource(webView, s);
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
    }
    
    public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        Log.e("SignupActivity", "SSL error: " + sslError);
        this.mUi.showToast("SSL Failure loading ERROR: " + sslError.getUrl());
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        if (this.isRealUrl(s)) {
            webView.loadUrl(s);
        }
        else if (this.canHandleUri(s) != NflxHandler.Response.NOT_HANDLING) {
            Log.d("SignupActivity", "=========> URL handled by Nflx protocol" + s);
        }
        else {
            Log.e("SignupActivity", "=========> Invalid URL scheme, protocol not handled" + s);
            this.mUi.showToast("Invalid URL scheme " + s);
        }
        return true;
    }
}
