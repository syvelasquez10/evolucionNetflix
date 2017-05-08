// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.webview;

import android.text.TextUtils;
import android.content.Context;
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
import com.facebook.react.uimanager.events.Event;
import android.webkit.WebView$PictureListener;
import android.webkit.WebView;
import com.facebook.react.uimanager.SimpleViewManager;

public class ReactWebViewManager extends SimpleViewManager<WebView>
{
    private static final String BLANK_URL = "about:blank";
    private static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    protected static final String REACT_CLASS = "RCTWebView";
    private WebView$PictureListener mPictureListener;
    private WebViewConfig mWebViewConfig;
    
    public ReactWebViewManager() {
        this.mWebViewConfig = new ReactWebViewManager$1(this);
    }
    
    public ReactWebViewManager(final WebViewConfig mWebViewConfig) {
        this.mWebViewConfig = mWebViewConfig;
    }
    
    private static void dispatchEvent(final WebView webView, final Event event) {
        ((ReactContext)webView.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(event);
    }
    
    private WebView$PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = (WebView$PictureListener)new ReactWebViewManager$3(this);
        }
        return this.mPictureListener;
    }
    
    protected void addEventEmitters(final ThemedReactContext themedReactContext, final WebView webView) {
        webView.setWebViewClient((WebViewClient)new ReactWebViewManager$ReactWebViewClient());
    }
    
    protected WebView createViewInstance(final ThemedReactContext themedReactContext) {
        final ReactWebViewManager$ReactWebView reactWebViewManager$ReactWebView = new ReactWebViewManager$ReactWebView(themedReactContext);
        reactWebViewManager$ReactWebView.setWebChromeClient((WebChromeClient)new ReactWebViewManager$2(this));
        themedReactContext.addLifecycleEventListener(reactWebViewManager$ReactWebView);
        this.mWebViewConfig.configWebView(reactWebViewManager$ReactWebView);
        reactWebViewManager$ReactWebView.getSettings().setBuiltInZoomControls(true);
        reactWebViewManager$ReactWebView.getSettings().setDisplayZoomControls(false);
        reactWebViewManager$ReactWebView.getSettings().setDomStorageEnabled(true);
        reactWebViewManager$ReactWebView.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
        return reactWebViewManager$ReactWebView;
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("goBack", 1, "goForward", 2, "reload", 3, "stopLoading", 4, "postMessage", 5, "injectJavaScript", 6);
    }
    
    @Override
    public String getName() {
        return "RCTWebView";
    }
    
    public void onDropViewInstance(final WebView webView) {
        super.onDropViewInstance((T)webView);
        ((ThemedReactContext)webView.getContext()).removeLifecycleEventListener((LifecycleEventListener)webView);
        ((ReactWebViewManager$ReactWebView)webView).cleanupCallbacksAndDestroy();
    }
    
    public void receiveCommand(final WebView webView, final int n, final ReadableArray readableArray) {
        switch (n) {
            default: {}
            case 1: {
                webView.goBack();
            }
            case 2: {
                webView.goForward();
            }
            case 3: {
                webView.reload();
            }
            case 4: {
                webView.stopLoading();
            }
            case 5: {
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("data", (Object)readableArray.getString(0));
                    webView.loadUrl("javascript:(function () {var event;var data = " + jsonObject.toString() + ";" + "try {" + "event = new MessageEvent('message', data);" + "} catch (e) {" + "event = document.createEvent('MessageEvent');" + "event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);" + "}" + "document.dispatchEvent(event);" + "})();");
                }
                catch (JSONException ex) {
                    throw new RuntimeException((Throwable)ex);
                }
            }
            case 6: {
                webView.loadUrl("javascript:" + readableArray.getString(0));
            }
        }
    }
    
    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(final WebView webView, final boolean allowUniversalAccessFromFileURLs) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(allowUniversalAccessFromFileURLs);
    }
    
    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(final WebView webView, final boolean domStorageEnabled) {
        webView.getSettings().setDomStorageEnabled(domStorageEnabled);
    }
    
    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(final WebView webView, final String injectedJavaScript) {
        ((ReactWebViewManager$ReactWebView)webView).setInjectedJavaScript(injectedJavaScript);
    }
    
    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(final WebView webView, final boolean javaScriptEnabled) {
        webView.getSettings().setJavaScriptEnabled(javaScriptEnabled);
    }
    
    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(final WebView webView, final boolean mediaPlaybackRequiresUserGesture) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(mediaPlaybackRequiresUserGesture);
    }
    
    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(final WebView webView, final boolean messagingEnabled) {
        ((ReactWebViewManager$ReactWebView)webView).setMessagingEnabled(messagingEnabled);
    }
    
    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(final WebView webView, final boolean b) {
        if (b) {
            webView.setPictureListener(this.getPictureListener());
            return;
        }
        webView.setPictureListener((WebView$PictureListener)null);
    }
    
    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(final WebView webView, final boolean b) {
        webView.getSettings().setUseWideViewPort(!b);
    }
    
    @ReactProp(name = "source")
    public void setSource(final WebView webView, ReadableMap map) {
        Label_0319: {
            if (map != null) {
                if (map.hasKey("html")) {
                    final String string = map.getString("html");
                    if (!map.hasKey("baseUrl")) {
                        webView.loadData(string, "text/html; charset=utf-8", "UTF-8");
                        return;
                    }
                    webView.loadDataWithBaseURL(map.getString("baseUrl"), string, "text/html; charset=utf-8", "UTF-8", (String)null);
                }
                else {
                    if (!map.hasKey("uri")) {
                        break Label_0319;
                    }
                    final String string2 = map.getString("uri");
                    Object o = webView.getUrl();
                    if (o == null || !((String)o).equals(string2)) {
                        if (map.hasKey("method") && map.getString("method").equals("POST")) {
                            while (true) {
                                if (!map.hasKey("body")) {
                                    final Object o2 = null;
                                    break Label_0167;
                                }
                                o = map.getString("body");
                                try {
                                    final Object o2 = ((String)o).getBytes("UTF-8");
                                    o = o2;
                                    if (o2 == null) {
                                        o = new byte[0];
                                    }
                                    webView.postUrl(string2, (byte[])o);
                                    return;
                                }
                                catch (UnsupportedEncodingException ex) {
                                    final Object o2 = ((String)o).getBytes();
                                    continue;
                                }
                                break;
                            }
                        }
                        final HashMap<String, String> hashMap = new HashMap<String, String>();
                        if (map.hasKey("headers")) {
                            map = map.getMap("headers");
                            final ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                            while (keySetIterator.hasNextKey()) {
                                final String nextKey = keySetIterator.nextKey();
                                if ("user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                    if (webView.getSettings() == null) {
                                        continue;
                                    }
                                    webView.getSettings().setUserAgentString(map.getString(nextKey));
                                }
                                else {
                                    hashMap.put(nextKey, map.getString(nextKey));
                                }
                            }
                        }
                        webView.loadUrl(string2, (Map)hashMap);
                    }
                }
                return;
            }
        }
        webView.loadUrl("about:blank");
    }
    
    @ReactProp(name = "userAgent")
    public void setUserAgent(final WebView webView, final String userAgentString) {
        if (userAgentString != null) {
            webView.getSettings().setUserAgentString(userAgentString);
        }
    }
}
