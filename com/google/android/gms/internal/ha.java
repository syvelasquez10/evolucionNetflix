// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.net.URISyntaxException;
import com.google.android.gms.common.internal.m;
import java.net.URI;
import android.text.TextUtils;
import android.webkit.WebViewClient;

@ez
public class ha extends WebViewClient
{
    private final gv md;
    private final String xc;
    private boolean xd;
    private final fc xe;
    
    public ha(final fc xe, final gv md, final String s) {
        this.xc = this.Z(s);
        this.xd = false;
        this.md = md;
        this.xe = xe;
    }
    
    private String Z(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                if (s.endsWith("/")) {
                    return s.substring(0, s.length() - 1);
                }
            }
            catch (IndexOutOfBoundsException ex) {
                gs.T(ex.getMessage());
                return s;
            }
        }
        return s;
    }
    
    protected boolean Y(String s) {
        s = this.Z(s);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                final URI uri = new URI(s);
                if ("passback".equals(uri.getScheme())) {
                    gs.S("Passback received");
                    this.xe.cA();
                    return true;
                }
                if (!TextUtils.isEmpty((CharSequence)this.xc)) {
                    final URI uri2 = new URI(this.xc);
                    s = uri2.getHost();
                    final String host = uri.getHost();
                    final String path = uri2.getPath();
                    final String path2 = uri.getPath();
                    if (m.equal(s, host) && m.equal(path, path2)) {
                        gs.S("Passback received");
                        this.xe.cA();
                        return true;
                    }
                }
            }
            catch (URISyntaxException ex) {
                gs.T(ex.getMessage());
                return false;
            }
        }
        return false;
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        gs.S("JavascriptAdWebViewClient::onLoadResource: " + s);
        if (!this.Y(s)) {
            this.md.dv().onLoadResource(this.md, s);
        }
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        gs.S("JavascriptAdWebViewClient::onPageFinished: " + s);
        if (!this.xd) {
            this.xe.cz();
            this.xd = true;
        }
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        gs.S("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + s);
        if (this.Y(s)) {
            gs.S("shouldOverrideUrlLoading: received passback url");
            return true;
        }
        return this.md.dv().shouldOverrideUrlLoading(this.md, s);
    }
}
