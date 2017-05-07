// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URLConnection;
import java.io.IOException;
import java.io.File;
import android.webkit.WebView;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import android.webkit.WebResourceResponse;
import android.content.Context;

@ez
public class gy extends gw
{
    public gy(final gv gv, final boolean b) {
        super(gv, b);
    }
    
    protected WebResourceResponse d(final Context context, final String s, String s2) {
        s2 = (String)new URL(s2).openConnection();
        try {
            gj.a(context, s, true, (HttpURLConnection)s2, true);
            ((URLConnection)s2).addRequestProperty("Cache-Control", "max-stale=3600");
            ((URLConnection)s2).connect();
            return new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(gj.a(new InputStreamReader(((URLConnection)s2).getInputStream())).getBytes("UTF-8")));
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
            if (!(webView instanceof gv)) {
                gs.W("Tried to intercept request from a WebView that wasn't an AdWebView.");
                return super.shouldInterceptRequest(webView, s);
            }
            final gv gv = (gv)webView;
            gv.dv().bY();
            if (gv.Y().og) {
                gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js)");
                return this.d(gv.getContext(), this.md.dy().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
            }
            if (gv.dz()) {
                gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js)");
                return this.d(gv.getContext(), this.md.dy().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
            }
            gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js)");
            return this.d(gv.getContext(), this.md.dy().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
        }
        catch (IOException ex) {
            gs.W("Could not fetch MRAID JS. " + ex.getMessage());
            return super.shouldInterceptRequest(webView, s);
        }
    }
}
