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
import android.webkit.WebViewClient;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import android.graphics.Picture;
import android.webkit.WebView;
import android.webkit.WebView$PictureListener;

class ReactWebViewManager$3 implements WebView$PictureListener
{
    final /* synthetic */ ReactWebViewManager this$0;
    
    ReactWebViewManager$3(final ReactWebViewManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onNewPicture(final WebView webView, final Picture picture) {
        dispatchEvent(webView, new ContentSizeChangeEvent(webView.getId(), webView.getWidth(), webView.getContentHeight()));
    }
}
