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
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.R$drawable;
import android.view.View$OnClickListener;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;

public class WebDialog extends Dialog
{
    static final String CANCEL_URI = "fbconnect://cancel";
    public static final int DEFAULT_THEME = 16973840;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    static final String REDIRECT_URI = "fbconnect://success";
    private static final String USER_AGENT = "user_agent";
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private boolean isDetached;
    private boolean listenerCalled;
    private WebDialog$OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private String url;
    private WebView webView;
    
    public WebDialog(final Context context, final String s) {
        this(context, s, 16973840);
    }
    
    public WebDialog(final Context context, final String url, final int n) {
        super(context, n);
        this.listenerCalled = false;
        this.isDetached = false;
        this.url = url;
    }
    
    public WebDialog(final Context context, final String s, final Bundle bundle, final int n, final WebDialog$OnCompleteListener onCompleteListener) {
        super(context, n);
        this.listenerCalled = false;
        this.isDetached = false;
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("display", "touch");
        bundle2.putString("type", "user_agent");
        this.url = Utility.buildUri("m.facebook.com", "dialog/" + s, bundle2).toString();
        this.onCompleteListener = onCompleteListener;
    }
    
    private void createCrossImage() {
        (this.crossImageView = new ImageView(this.getContext())).setOnClickListener((View$OnClickListener)new WebDialog$3(this));
        this.crossImageView.setImageDrawable(this.getContext().getResources().getDrawable(R$drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }
    
    private void sendCancelToListener() {
        this.sendErrorToListener(new FacebookOperationCanceledException());
    }
    
    private void sendErrorToListener(final Throwable t) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            FacebookException ex;
            if (t instanceof FacebookException) {
                ex = (FacebookException)t;
            }
            else {
                ex = new FacebookException(t);
            }
            this.onCompleteListener.onComplete(null, ex);
        }
    }
    
    private void sendSuccessToListener(final Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(bundle, null);
        }
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    private void setUpWebView(final int n) {
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        (this.webView = new WebView(this.getContext())).setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient((WebViewClient)new WebDialog$DialogWebViewClient(this, null));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        linearLayout.setPadding(n, n, n, n);
        linearLayout.addView((View)this.webView);
        this.contentFrameLayout.addView((View)linearLayout);
    }
    
    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached) {
            if (this.spinner.isShowing()) {
                this.spinner.dismiss();
            }
            super.dismiss();
        }
    }
    
    public WebDialog$OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }
    
    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setOnCancelListener((DialogInterface$OnCancelListener)new WebDialog$1(this));
        (this.spinner = new ProgressDialog(this.getContext())).requestWindowFeature(1);
        this.spinner.setMessage((CharSequence)this.getContext().getString(R$string.com_facebook_loading));
        this.spinner.setOnCancelListener((DialogInterface$OnCancelListener)new WebDialog$2(this));
        this.requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(this.getContext());
        this.createCrossImage();
        this.setUpWebView(this.crossImageView.getDrawable().getIntrinsicWidth() / 2);
        this.contentFrameLayout.addView((View)this.crossImageView, new ViewGroup$LayoutParams(-2, -2));
        this.addContentView((View)this.contentFrameLayout, new ViewGroup$LayoutParams(-1, -1));
    }
    
    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }
    
    public void setOnCompleteListener(final WebDialog$OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }
}
