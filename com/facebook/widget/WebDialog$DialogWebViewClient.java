// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.android.R$string;
import android.content.DialogInterface$OnCancelListener;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.R$drawable;
import android.view.View$OnClickListener;
import android.content.Context;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import com.facebook.FacebookServiceException;
import com.facebook.FacebookRequestError;
import com.facebook.android.Util;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.facebook.FacebookDialogException;
import com.facebook.internal.Utility;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class WebDialog$DialogWebViewClient extends WebViewClient
{
    final /* synthetic */ WebDialog this$0;
    
    private WebDialog$DialogWebViewClient(final WebDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        if (!this.this$0.isDetached) {
            this.this$0.spinner.dismiss();
        }
        this.this$0.contentFrameLayout.setBackgroundColor(0);
        this.this$0.webView.setVisibility(0);
        this.this$0.crossImageView.setVisibility(0);
    }
    
    public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + s);
        super.onPageStarted(webView, s, bitmap);
        if (!this.this$0.isDetached) {
            this.this$0.spinner.show();
        }
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
        this.this$0.sendErrorToListener(new FacebookDialogException(s, n, s2));
        this.this$0.dismiss();
    }
    
    public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        this.this$0.sendErrorToListener(new FacebookDialogException(null, -11, null));
        sslErrorHandler.cancel();
        this.this$0.dismiss();
    }
    
    public boolean shouldOverrideUrlLoading(WebView string, String s) {
        Utility.logd("FacebookSDK.WebDialog", "Redirect URL: " + s);
        if (s.startsWith("fbconnect://success")) {
            final Bundle url = Util.parseUrl(s);
            s = url.getString("error");
            if ((string = (WebView)s) == null) {
                string = (WebView)url.getString("error_type");
            }
            if ((s = url.getString("error_msg")) == null) {
                s = url.getString("error_description");
            }
            final String string2 = url.getString("error_code");
        Label_0109:
            while (true) {
                if (Utility.isNullOrEmpty(string2)) {
                    final int int1 = -1;
                    break Label_0109;
                }
                while (true) {
                    while (true) {
                        int int1;
                        try {
                            int1 = Integer.parseInt(string2);
                            if (Utility.isNullOrEmpty((String)string) && Utility.isNullOrEmpty(s) && int1 == -1) {
                                this.this$0.sendSuccessToListener(url);
                                this.this$0.dismiss();
                                return true;
                            }
                        }
                        catch (NumberFormatException ex) {
                            int1 = -1;
                            continue Label_0109;
                        }
                        if (string != null && (((String)string).equals("access_denied") || ((String)string).equals("OAuthAccessDeniedException"))) {
                            this.this$0.sendCancelToListener();
                            continue;
                        }
                        string = (WebView)new FacebookRequestError(int1, (String)string, s);
                        this.this$0.sendErrorToListener(new FacebookServiceException((FacebookRequestError)string, s));
                        continue;
                    }
                }
                break;
            }
        }
        else {
            if (s.startsWith("fbconnect://cancel")) {
                this.this$0.sendCancelToListener();
                this.this$0.dismiss();
                return true;
            }
            if (s.contains("touch")) {
                return false;
            }
            this.this$0.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
            return true;
        }
    }
}
