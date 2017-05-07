// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.jsadapter;

import android.webkit.WebView;
import java.net.URISyntaxException;
import com.google.android.gms.internal.ct;
import java.net.URI;
import android.text.TextUtils;
import android.webkit.WebViewClient;

public final class BannerWebViewClient extends WebViewClient
{
    private final String A;
    private boolean B;
    private final JavascriptAdapter r;
    
    public BannerWebViewClient(final JavascriptAdapter r, final String s) {
        this.A = this.c(s);
        this.r = r;
        this.B = false;
    }
    
    private boolean b(String s) {
        s = this.c(s);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                final URI uri = new URI(s);
                if ("passback".equals(uri.getScheme())) {
                    ct.r("Passback received");
                    this.r.sendAdNotReceivedUpdate();
                    return true;
                }
                if (!TextUtils.isEmpty((CharSequence)this.A)) {
                    final URI uri2 = new URI(this.A);
                    s = uri2.getHost();
                    final String host = uri.getHost();
                    final String path = uri2.getPath();
                    final String path2 = uri.getPath();
                    if (equals(s, host) && equals(path, path2)) {
                        ct.r("Passback received");
                        this.r.sendAdNotReceivedUpdate();
                        return true;
                    }
                }
            }
            catch (URISyntaxException ex) {
                ct.s(ex.getMessage());
                return false;
            }
        }
        return false;
    }
    
    private String c(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                if (s.endsWith("/")) {
                    return s.substring(0, s.length() - 1);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                ct.s(ex.getMessage());
                return s;
            }
        }
        return s;
    }
    
    private static boolean equals(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        ct.u("onLoadResource: " + s);
        if (!this.b(s)) {
            super.onLoadResource(webView, s);
        }
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        ct.u("onPageFinished: " + s);
        super.onPageFinished(webView, s);
        if (!this.B) {
            this.r.startCheckingForAd();
            this.B = true;
        }
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        ct.u("shouldOverrideUrlLoading: " + s);
        if (this.b(s)) {
            ct.r("shouldOverrideUrlLoading: received passback url");
            return true;
        }
        return false;
    }
}
