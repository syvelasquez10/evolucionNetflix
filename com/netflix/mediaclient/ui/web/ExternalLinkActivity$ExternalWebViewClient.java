// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.web;

import com.netflix.mediaclient.util.StringUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class ExternalLinkActivity$ExternalWebViewClient extends WebViewClient
{
    private boolean mClearHistory;
    private String mCurrentPageURL;
    final /* synthetic */ ExternalLinkActivity this$0;
    
    private ExternalLinkActivity$ExternalWebViewClient(final ExternalLinkActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void clearHistory() {
        this.mClearHistory = true;
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        super.onLoadResource(webView, s);
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        final String originalUrl = webView.getOriginalUrl();
        if (this.mClearHistory && !StringUtils.safeEquals(this.mCurrentPageURL, originalUrl)) {
            webView.clearHistory();
            this.mClearHistory = false;
        }
        this.mCurrentPageURL = originalUrl;
        this.this$0.setWebViewVisibility(true);
        super.onPageFinished(webView, s);
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
    }
}
