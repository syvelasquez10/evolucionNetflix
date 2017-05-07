// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.jsapi;

import com.netflix.mediaclient.util.ThreadUtils;
import android.webkit.WebView;
import android.os.Handler;
import android.content.Context;
import android.annotation.SuppressLint;

@SuppressLint({ "JavascriptInterface" })
public abstract class JSObject
{
    protected Context context;
    protected Handler mainHandler;
    protected WebView webview;
    
    protected JSObject(final Context context, final WebView webview) {
        ThreadUtils.assertOnMain();
        if (context == null || webview == null) {
            throw new IllegalArgumentException("Can not create a JSObject a null context or webview.");
        }
        this.mainHandler = new Handler();
        this.context = context;
        (this.webview = webview).addJavascriptInterface((Object)this, this.getInterfaceName());
    }
    
    public abstract String getInterfaceName();
    
    protected void injectJavaScript(final String s) {
        if (s == null) {
            return;
        }
        this.mainHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                JSObject.this.webview.loadUrl(s);
            }
        });
    }
    
    public void release() {
        this.webview.removeJavascriptInterface(this.getInterfaceName());
        this.webview = null;
        this.context = null;
    }
}
