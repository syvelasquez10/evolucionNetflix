// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.webkit.WebView;

class WebViewAccountActivity$1 extends AccountWebViewClient
{
    final /* synthetic */ WebViewAccountActivity this$0;
    
    WebViewAccountActivity$1(final WebViewAccountActivity this$0, final WebViewAccountActivity webViewAccountActivity) {
        this.this$0 = this$0;
        super(webViewAccountActivity);
    }
    
    @Override
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        this.this$0.onWebViewLoaded();
    }
}
