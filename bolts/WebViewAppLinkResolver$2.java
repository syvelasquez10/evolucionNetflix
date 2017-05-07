// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.Callable;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URLConnection;
import java.util.Map;
import android.content.Context;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.net.Uri;
import org.json.JSONArray;

class WebViewAppLinkResolver$2 implements Continuation<Void, Task<JSONArray>>
{
    final /* synthetic */ WebViewAppLinkResolver this$0;
    final /* synthetic */ Capture val$content;
    final /* synthetic */ Capture val$contentType;
    final /* synthetic */ Uri val$url;
    
    WebViewAppLinkResolver$2(final WebViewAppLinkResolver this$0, final Capture val$contentType, final Uri val$url, final Capture val$content) {
        this.this$0 = this$0;
        this.val$contentType = val$contentType;
        this.val$url = val$url;
        this.val$content = val$content;
    }
    
    @Override
    public Task<JSONArray> then(final Task<Void> task) {
        final Task$TaskCompletionSource create = Task.create();
        final WebView webView = new WebView(this.this$0.context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setNetworkAvailable(false);
        webView.setWebViewClient((WebViewClient)new WebViewAppLinkResolver$2$1(this));
        webView.addJavascriptInterface((Object)new WebViewAppLinkResolver$2$2(this, create), "boltsWebViewAppLinkResolverResult");
        String s;
        if (this.val$contentType.get() != null) {
            s = this.val$contentType.get().split(";")[0];
        }
        else {
            s = null;
        }
        webView.loadDataWithBaseURL(this.val$url.toString(), (String)this.val$content.get(), s, (String)null, (String)null);
        return (Task<JSONArray>)create.getTask();
    }
}
