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

public class ec extends ea
{
    public ec(final dz dz, final boolean b) {
        super(dz, b);
    }
    
    protected WebResourceResponse d(final Context context, final String s, String s2) throws IOException {
        s2 = (String)new URL(s2).openConnection();
        try {
            dq.a(context, s, true, (HttpURLConnection)s2);
            ((URLConnection)s2).connect();
            return new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(dq.a(new InputStreamReader(((URLConnection)s2).getInputStream())).getBytes("UTF-8")));
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
            if (!(webView instanceof dz)) {
                dw.z("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webView, s);
            }
            final dz dz = (dz)webView;
            dz.bI().aM();
            if (dz.R().lT) {
                dw.y("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_interstitial.js)");
                return this.d(dz.getContext(), this.lC.bK().rq, "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
            }
            if (dz.bL()) {
                dw.y("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js)");
                return this.d(dz.getContext(), this.lC.bK().rq, "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
            }
            dw.y("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_banner.js)");
            return this.d(dz.getContext(), this.lC.bK().rq, "http://media.admob.com/mraid/v1/mraid_app_banner.js");
        }
        catch (IOException ex) {
            dw.z("Could not fetching MRAID JS. " + ex.getMessage());
            return super.shouldInterceptRequest(webView, s);
        }
    }
}
