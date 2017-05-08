// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.webview;

import android.webkit.GeolocationPermissions$Callback;
import android.webkit.WebChromeClient;

class ReactWebViewManager$2 extends WebChromeClient
{
    final /* synthetic */ ReactWebViewManager this$0;
    
    ReactWebViewManager$2(final ReactWebViewManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onGeolocationPermissionsShowPrompt(final String s, final GeolocationPermissions$Callback geolocationPermissions$Callback) {
        geolocationPermissions$Callback.invoke(s, true, false);
    }
}
