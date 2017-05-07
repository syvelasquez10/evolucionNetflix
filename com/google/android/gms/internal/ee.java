// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.net.URISyntaxException;
import java.net.URI;
import android.text.TextUtils;
import android.webkit.WebViewClient;

public class ee extends WebViewClient
{
    private final dz lC;
    private final String rM;
    private boolean rN;
    private final ct rO;
    
    public ee(final ct ro, final dz lc, final String s) {
        this.rM = this.B(s);
        this.rN = false;
        this.lC = lc;
        this.rO = ro;
    }
    
    private String B(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                if (s.endsWith("/")) {
                    return s.substring(0, s.length() - 1);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                dw.w(ex.getMessage());
                return s;
            }
        }
        return s;
    }
    
    protected boolean A(String s) {
        s = this.B(s);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                final URI uri = new URI(s);
                if ("passback".equals(uri.getScheme())) {
                    dw.v("Passback received");
                    this.rO.bb();
                    return true;
                }
                if (!TextUtils.isEmpty((CharSequence)this.rM)) {
                    final URI uri2 = new URI(this.rM);
                    s = uri2.getHost();
                    final String host = uri.getHost();
                    final String path = uri2.getPath();
                    final String path2 = uri.getPath();
                    if (fo.equal(s, host) && fo.equal(path, path2)) {
                        dw.v("Passback received");
                        this.rO.bb();
                        return true;
                    }
                }
            }
            catch (URISyntaxException ex) {
                dw.w(ex.getMessage());
                return false;
            }
        }
        return false;
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        dw.v("JavascriptAdWebViewClient::onLoadResource: " + s);
        if (!this.A(s)) {
            this.lC.bI().onLoadResource(this.lC, s);
        }
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        dw.v("JavascriptAdWebViewClient::onPageFinished: " + s);
        if (!this.rN) {
            this.rO.ba();
            this.rN = true;
        }
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        dw.v("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + s);
        if (this.A(s)) {
            dw.v("shouldOverrideUrlLoading: received passback url");
            return true;
        }
        return this.lC.bI().shouldOverrideUrlLoading(this.lC, s);
    }
}
