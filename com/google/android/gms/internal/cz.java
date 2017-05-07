// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URLConnection;
import java.io.File;
import android.webkit.WebView;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import android.webkit.WebResourceResponse;
import android.content.Context;

public final class cz extends cx
{
    public cz(final cw cw, final boolean b) {
        super(cw, b);
    }
    
    private static WebResourceResponse d(final Context context, final String s, String s2) throws IOException {
        s2 = (String)new URL(s2).openConnection();
        try {
            co.a(context, s, true, (HttpURLConnection)s2);
            ((URLConnection)s2).connect();
            return new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(co.a(new InputStreamReader(((URLConnection)s2).getInputStream())).getBytes("UTF-8")));
        }
        finally {
            ((HttpURLConnection)s2).disconnect();
        }
    }
    
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
                return super.shouldInterceptRequest(webView, s);
            }
            if (!(webView instanceof cw)) {
                ct.v("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webView, s);
            }
            final cw cw = (cw)webView;
            cw.aC().Y();
            if (cw.y().eG) {
                ct.u("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_interstitial.js)");
                return d(cw.getContext(), this.gv.aE().iJ, "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
            }
            if (cw.aF()) {
                ct.u("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js)");
                return d(cw.getContext(), this.gv.aE().iJ, "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
            }
            ct.u("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_banner.js)");
            return d(cw.getContext(), this.gv.aE().iJ, "http://media.admob.com/mraid/v1/mraid_app_banner.js");
        }
        catch (IOException ex) {
            ct.v("Could not fetching MRAID JS. " + ex.getMessage());
            return super.shouldInterceptRequest(webView, s);
        }
    }
}
