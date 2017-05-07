// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import android.net.Uri;
import android.content.DialogInterface$OnCancelListener;
import com.facebook.android.R$string;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.facebook.android.R$drawable;
import android.view.View$OnClickListener;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.internal.Utility;
import com.facebook.internal.ServerProtocol;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;

public class WebDialog extends Dialog
{
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private String expectedRedirectUrl;
    private boolean isDetached;
    private boolean isDismissed;
    private boolean listenerCalled;
    private WebDialog$OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private String url;
    private WebView webView;
    
    public WebDialog(final Context context, final String s, final Bundle bundle, final int n, final WebDialog$OnCompleteListener onCompleteListener) {
        super(context, n);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isDismissed = false;
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("redirect_uri", "fbconnect://success");
        bundle2.putString("display", "touch");
        this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), ServerProtocol.getAPIVersion() + "/" + "dialog/" + s, bundle2).toString();
        this.onCompleteListener = onCompleteListener;
    }
    
    private void calculateSize() {
        final Display defaultDisplay = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int n;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n = displayMetrics.widthPixels;
        }
        else {
            n = displayMetrics.heightPixels;
        }
        int n2;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n2 = displayMetrics.heightPixels;
        }
        else {
            n2 = displayMetrics.widthPixels;
        }
        this.getWindow().setLayout(Math.min(this.getScaledSize(n, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(this.getScaledSize(n2, displayMetrics.density, 800, 1280), displayMetrics.heightPixels));
    }
    
    private void createCrossImage() {
        (this.crossImageView = new ImageView(this.getContext())).setOnClickListener((View$OnClickListener)new WebDialog$2(this));
        this.crossImageView.setImageDrawable(this.getContext().getResources().getDrawable(R$drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }
    
    private int getScaledSize(final int n, final float n2, final int n3, final int n4) {
        double n5 = 0.5;
        final int n6 = (int)(n / n2);
        if (n6 <= n3) {
            n5 = 1.0;
        }
        else if (n6 < n4) {
            n5 = 0.5 + (n4 - n6) / (n4 - n3) * 0.5;
        }
        return (int)(n5 * n);
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    private void setUpWebView(final int n) {
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        (this.webView = new WebDialog$3(this, this.getContext())).setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient((WebViewClient)new WebDialog$DialogWebViewClient(this, null));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        linearLayout.setPadding(n, n, n, n);
        linearLayout.addView((View)this.webView);
        linearLayout.setBackgroundColor(-872415232);
        this.contentFrameLayout.addView((View)linearLayout);
    }
    
    public void dismiss() {
        if (!this.isDismissed) {
            this.isDismissed = true;
            if (!this.listenerCalled) {
                this.sendCancelToListener();
            }
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
    }
    
    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.spinner = new ProgressDialog(this.getContext())).requestWindowFeature(1);
        this.spinner.setMessage((CharSequence)this.getContext().getString(R$string.com_facebook_loading));
        this.spinner.setOnCancelListener((DialogInterface$OnCancelListener)new WebDialog$1(this));
        this.requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(this.getContext());
        this.calculateSize();
        this.getWindow().setGravity(17);
        this.getWindow().setSoftInputMode(16);
        this.createCrossImage();
        this.setUpWebView(this.crossImageView.getDrawable().getIntrinsicWidth() / 2 + 1);
        this.contentFrameLayout.addView((View)this.crossImageView, new ViewGroup$LayoutParams(-2, -2));
        this.setContentView((View)this.contentFrameLayout);
    }
    
    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }
    
    protected Bundle parseResponseUri(final String s) {
        final Uri parse = Uri.parse(s);
        final Bundle urlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        urlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return urlQueryString;
    }
    
    protected void sendCancelToListener() {
        this.sendErrorToListener(new FacebookOperationCanceledException());
    }
    
    protected void sendErrorToListener(final Throwable t) {
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
            this.dismiss();
        }
    }
    
    protected void sendSuccessToListener(final Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(bundle, null);
            this.dismiss();
        }
    }
    
    public void setOnCompleteListener(final WebDialog$OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }
}
