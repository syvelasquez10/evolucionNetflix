// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import android.content.Context;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import android.net.Uri;
import java.util.concurrent.Callable;

class WebViewAppLinkResolver$3 implements Callable<Void>
{
    final /* synthetic */ WebViewAppLinkResolver this$0;
    final /* synthetic */ Capture val$content;
    final /* synthetic */ Capture val$contentType;
    final /* synthetic */ Uri val$url;
    
    WebViewAppLinkResolver$3(final WebViewAppLinkResolver this$0, final Uri val$url, final Capture val$content, final Capture val$contentType) {
        this.this$0 = this$0;
        this.val$url = val$url;
        this.val$content = val$content;
        this.val$contentType = val$contentType;
    }
    
    @Override
    public Void call() {
        URL url = new URL(this.val$url.toString());
        HttpURLConnection httpURLConnection = null;
        while (url != null) {
            final URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)openConnection).setInstanceFollowRedirects(true);
            }
            openConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
            openConnection.connect();
            if (openConnection instanceof HttpURLConnection) {
                final HttpURLConnection httpURLConnection2 = (HttpURLConnection)openConnection;
                URL url2;
                if (httpURLConnection2.getResponseCode() >= 300 && httpURLConnection2.getResponseCode() < 400) {
                    url2 = new URL(httpURLConnection2.getHeaderField("Location"));
                    httpURLConnection2.disconnect();
                }
                else {
                    url2 = null;
                }
                final URL url3 = url2;
                httpURLConnection = (HttpURLConnection)openConnection;
                url = url3;
            }
            else {
                httpURLConnection = (HttpURLConnection)openConnection;
                url = null;
            }
        }
        try {
            this.val$content.set(readFromConnection(httpURLConnection));
            this.val$contentType.set(httpURLConnection.getContentType());
            return null;
        }
        finally {
            if (httpURLConnection instanceof HttpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
    }
}
