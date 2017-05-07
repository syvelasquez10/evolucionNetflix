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
import android.view.WindowManager$BadTokenException;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.webkit.JsPromptResult;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.webkit.JsResult;
import android.app.AlertDialog$Builder;
import android.webkit.WebChromeClient;

@ez
public class gx extends WebChromeClient
{
    private final gv md;
    
    public gx(final gv md) {
        this.md = md;
    }
    
    private static void a(final AlertDialog$Builder alertDialog$Builder, final String message, final JsResult jsResult) {
        alertDialog$Builder.setMessage((CharSequence)message).setPositiveButton(17039370, (DialogInterface$OnClickListener)new gx$3(jsResult)).setNegativeButton(17039360, (DialogInterface$OnClickListener)new gx$2(jsResult)).setOnCancelListener((DialogInterface$OnCancelListener)new gx$1(jsResult)).create().show();
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
        alertDialog$Builder.setView((View)view).setPositiveButton(17039370, (DialogInterface$OnClickListener)new gx$6(jsPromptResult, editText)).setNegativeButton(17039360, (DialogInterface$OnClickListener)new gx$5(jsPromptResult)).setOnCancelListener((DialogInterface$OnCancelListener)new gx$4(jsPromptResult)).create().show();
    }
    
    protected final void a(final View view, final int requestedOrientation, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        final dk du = this.md.du();
        if (du == null) {
            gs.W("Could not get ad overlay when showing custom view.");
            webChromeClient$CustomViewCallback.onCustomViewHidden();
            return;
        }
        du.a(view, webChromeClient$CustomViewCallback);
        du.setRequestedOrientation(requestedOrientation);
    }
    
    protected boolean a(final Context context, final String title, final String s, final String s2, final JsResult jsResult, final JsPromptResult jsPromptResult, final boolean b) {
        try {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
            alertDialog$Builder.setTitle((CharSequence)title);
            if (b) {
                a(context, alertDialog$Builder, s, s2, jsPromptResult);
            }
            else {
                a(alertDialog$Builder, s, jsResult);
            }
        }
        catch (WindowManager$BadTokenException ex) {
            gs.d("Fail to display Dialog.", (Throwable)ex);
        }
        return true;
    }
    
    public final void onCloseWindow(final WebView webView) {
        if (!(webView instanceof gv)) {
            gs.W("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        final dk du = ((gv)webView).du();
        if (du == null) {
            gs.W("Tried to close an AdWebView not associated with an overlay.");
            return;
        }
        du.close();
    }
    
    public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        final String string = "JS: " + consoleMessage.message() + " (" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")";
        if (string.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (gx$7.xb[consoleMessage.messageLevel().ordinal()]) {
            default: {
                gs.U(string);
                break;
            }
            case 1: {
                gs.T(string);
                break;
            }
            case 2: {
                gs.W(string);
                break;
            }
            case 3:
            case 4: {
                gs.U(string);
                break;
            }
            case 5: {
                gs.S(string);
                break;
            }
        }
        return super.onConsoleMessage(consoleMessage);
    }
    
    public final boolean onCreateWindow(WebView webView, final boolean b, final boolean b2, final Message message) {
        final WebView$WebViewTransport webView$WebViewTransport = (WebView$WebViewTransport)message.obj;
        webView = new WebView(webView.getContext());
        webView.setWebViewClient((WebViewClient)this.md.dv());
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
        final dk du = this.md.du();
        if (du == null) {
            gs.W("Could not get ad overlay when hiding custom view.");
            return;
        }
        du.bX();
    }
    
    public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        Context context = webView.getContext();
        if (webView instanceof gv) {
            context = context;
            if (((gv)webView).dA() != null) {
                context = ((gv)webView).dA();
            }
        }
        return this.a(context, s, s2, null, jsResult, null, false);
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
