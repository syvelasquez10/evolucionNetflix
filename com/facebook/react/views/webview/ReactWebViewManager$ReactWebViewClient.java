// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.webview;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.Locale;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import android.view.ViewGroup$LayoutParams;
import com.facebook.react.bridge.LifecycleEventListener;
import android.webkit.WebChromeClient;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import android.webkit.WebView$PictureListener;
import com.facebook.react.uimanager.SimpleViewManager;
import android.content.ActivityNotFoundException;
import com.facebook.common.logging.FLog;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import android.graphics.Bitmap;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReactWebViewManager$ReactWebViewClient extends WebViewClient
{
    private boolean mLastLoadFailed;
    
    protected ReactWebViewManager$ReactWebViewClient() {
        this.mLastLoadFailed = false;
    }
    
    private WritableMap createWebViewEvent(final WebView webView, final String s) {
        final WritableMap map = Arguments.createMap();
        map.putDouble("target", webView.getId());
        map.putString("url", s);
        map.putBoolean("loading", !this.mLastLoadFailed && webView.getProgress() != 100);
        map.putString("title", webView.getTitle());
        map.putBoolean("canGoBack", webView.canGoBack());
        map.putBoolean("canGoForward", webView.canGoForward());
        return map;
    }
    
    private void emitFinishEvent(final WebView webView, final String s) {
        dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), this.createWebViewEvent(webView, s)));
    }
    
    public void doUpdateVisitedHistory(final WebView webView, final String s, final boolean b) {
        super.doUpdateVisitedHistory(webView, s, b);
        dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), this.createWebViewEvent(webView, s)));
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        super.onPageFinished(webView, s);
        if (!this.mLastLoadFailed) {
            final ReactWebViewManager$ReactWebView reactWebViewManager$ReactWebView = (ReactWebViewManager$ReactWebView)webView;
            reactWebViewManager$ReactWebView.callInjectedJavaScript();
            reactWebViewManager$ReactWebView.linkBridge();
            this.emitFinishEvent(webView, s);
        }
    }
    
    public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
        this.mLastLoadFailed = false;
        dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), this.createWebViewEvent(webView, s)));
    }
    
    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        super.onReceivedError(webView, n, s, s2);
        this.mLastLoadFailed = true;
        this.emitFinishEvent(webView, s2);
        final WritableMap webViewEvent = this.createWebViewEvent(webView, s2);
        webViewEvent.putDouble("code", n);
        webViewEvent.putString("description", s);
        dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), webViewEvent));
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        if (s.startsWith("http://") || s.startsWith("https://") || s.startsWith("file://")) {
            return false;
        }
        try {
            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
            intent.setFlags(268435456);
            webView.getContext().startActivity(intent);
            return true;
        }
        catch (ActivityNotFoundException ex) {
            FLog.w("React", "activity not found to handle uri scheme for: " + s, (Throwable)ex);
            return true;
        }
    }
}
