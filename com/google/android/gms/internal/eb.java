// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebStorage$QuotaUpdater;
import android.webkit.WebViewClient;
import android.webkit.WebView$WebViewTransport;
import android.os.Message;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.webkit.JsPromptResult;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.webkit.JsResult;
import android.app.AlertDialog$Builder;
import android.webkit.WebChromeClient;

public class eb extends WebChromeClient
{
    private final dz lC;
    
    public eb(final dz lc) {
        this.lC = lc;
    }
    
    private static void a(final AlertDialog$Builder alertDialog$Builder, final String message, final JsResult jsResult) {
        alertDialog$Builder.setMessage((CharSequence)message).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsResult.confirm();
            }
        }).setNegativeButton(17039360, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsResult.cancel();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                jsResult.cancel();
            }
        }).create().show();
    }
    
    private static void a(final Context context, final AlertDialog$Builder alertDialog$Builder, final String text, final String text2, final JsPromptResult jsPromptResult) {
        final LinearLayout view = new LinearLayout(context);
        view.setOrientation(1);
        final TextView textView = new TextView(context);
        textView.setText((CharSequence)text);
        final EditText editText = new EditText(context);
        editText.setText((CharSequence)text2);
        view.addView((View)textView);
        view.addView((View)editText);
        alertDialog$Builder.setView((View)view).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsPromptResult.confirm(editText.getText().toString());
            }
        }).setNegativeButton(17039360, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsPromptResult.cancel();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                jsPromptResult.cancel();
            }
        }).create().show();
    }
    
    protected final void a(final View view, final int requestedOrientation, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        final cc bh = this.lC.bH();
        if (bh == null) {
            dw.z("Could not get ad overlay when showing custom view.");
            webChromeClient$CustomViewCallback.onCustomViewHidden();
            return;
        }
        bh.a(view, webChromeClient$CustomViewCallback);
        bh.setRequestedOrientation(requestedOrientation);
    }
    
    protected boolean a(final Context context, final String title, final String s, final String s2, final JsResult jsResult, final JsPromptResult jsPromptResult, final boolean b) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle((CharSequence)title);
        if (b) {
            a(context, alertDialog$Builder, s, s2, jsPromptResult);
        }
        else {
            a(alertDialog$Builder, s, jsResult);
        }
        return true;
    }
    
    public final void onCloseWindow(final WebView webView) {
        if (!(webView instanceof dz)) {
            dw.z("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        final cc bh = ((dz)webView).bH();
        if (bh == null) {
            dw.z("Tried to close an AdWebView not associated with an overlay.");
            return;
        }
        bh.close();
    }
    
    public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        final String string = "JS: " + consoleMessage.message() + " (" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")";
        switch (eb$7.rL[consoleMessage.messageLevel().ordinal()]) {
            default: {
                dw.x(string);
                break;
            }
            case 1: {
                dw.w(string);
                break;
            }
            case 2: {
                dw.z(string);
                break;
            }
            case 3:
            case 4: {
                dw.x(string);
                break;
            }
            case 5: {
                dw.v(string);
                break;
            }
        }
        return super.onConsoleMessage(consoleMessage);
    }
    
    public final boolean onCreateWindow(WebView webView, final boolean b, final boolean b2, final Message message) {
        final WebView$WebViewTransport webView$WebViewTransport = (WebView$WebViewTransport)message.obj;
        webView = new WebView(webView.getContext());
        webView.setWebViewClient((WebViewClient)this.lC.bI());
        webView$WebViewTransport.setWebView(webView);
        message.sendToTarget();
        return true;
    }
    
    public final void onExceededDatabaseQuota(final String s, final String s2, final long n, long min, long n2, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        final long n3 = 5242880L - n2;
        if (n3 <= 0L) {
            webStorage$QuotaUpdater.updateQuota(n);
            return;
        }
        if (n == 0L) {
            if (min > n3 || min > 1048576L) {
                min = 0L;
            }
        }
        else if (min == 0L) {
            min = Math.min(Math.min(131072L, n3) + n, 1048576L);
        }
        else {
            n2 = n;
            if (min <= Math.min(1048576L - n, n3)) {
                n2 = n + min;
            }
            min = n2;
        }
        webStorage$QuotaUpdater.updateQuota(min);
    }
    
    public final void onHideCustomView() {
        final cc bh = this.lC.bH();
        if (bh == null) {
            dw.z("Could not get ad overlay when hiding custom view.");
            return;
        }
        bh.aL();
    }
    
    public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.a(webView.getContext(), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsBeforeUnload(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.a(webView.getContext(), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsConfirm(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.a(webView.getContext(), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
        return this.a(webView.getContext(), s, s2, s3, null, jsPromptResult, true);
    }
    
    public final void onReachedMaxAppCacheSize(long n, final long n2, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        n += 131072L;
        if (5242880L - n2 < n) {
            webStorage$QuotaUpdater.updateQuota(0L);
            return;
        }
        webStorage$QuotaUpdater.updateQuota(n);
    }
    
    public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        this.a(view, -1, webChromeClient$CustomViewCallback);
    }
}
