// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.webview;

import android.text.TextUtils;
import android.webkit.WebViewClient;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.LifecycleEventListener;
import android.webkit.WebView;

public class ReactWebViewManager$ReactWebView extends WebView implements LifecycleEventListener
{
    private String injectedJS;
    private boolean messagingEnabled;
    
    public ReactWebViewManager$ReactWebView(final ThemedReactContext themedReactContext) {
        super((Context)themedReactContext);
        this.messagingEnabled = false;
    }
    
    private void cleanupCallbacksAndDestroy() {
        this.setWebViewClient((WebViewClient)null);
        this.destroy();
    }
    
    public void callInjectedJavaScript() {
        if (this.getSettings().getJavaScriptEnabled() && this.injectedJS != null && !TextUtils.isEmpty((CharSequence)this.injectedJS)) {
            this.loadUrl("javascript:(function() {\n" + this.injectedJS + ";\n})();");
        }
    }
    
    public void linkBridge() {
        if (this.messagingEnabled) {
            this.loadUrl("javascript:(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
        }
    }
    
    public void onHostDestroy() {
        this.cleanupCallbacksAndDestroy();
    }
    
    public void onHostPause() {
    }
    
    public void onHostResume() {
    }
    
    public void setInjectedJavaScript(final String injectedJS) {
        this.injectedJS = injectedJS;
    }
    
    public void setMessagingEnabled(final boolean messagingEnabled) {
        if (this.messagingEnabled == messagingEnabled) {
            return;
        }
        this.messagingEnabled = messagingEnabled;
        if (messagingEnabled) {
            this.addJavascriptInterface((Object)new ReactWebViewManager$ReactWebView$ReactWebViewBridge(this, this), "__REACT_WEB_VIEW_BRIDGE");
            this.linkBridge();
            return;
        }
        this.removeJavascriptInterface("__REACT_WEB_VIEW_BRIDGE");
    }
}
