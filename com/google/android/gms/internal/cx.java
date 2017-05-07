// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.Iterator;
import java.util.Map;
import android.net.UrlQuerySanitizer$ParameterValuePair;
import android.net.UrlQuerySanitizer;
import android.net.Uri;
import java.util.HashMap;
import android.webkit.WebViewClient;

public class cx extends WebViewClient
{
    private al fm;
    private final Object fx;
    protected final cw gv;
    private final HashMap<String, an> iU;
    private q iV;
    private bn iW;
    private a iX;
    private boolean iY;
    private boolean iZ;
    private bq ja;
    
    public cx(final cw gv, final boolean iz) {
        this.iU = new HashMap<String, an>();
        this.fx = new Object();
        this.iY = false;
        this.gv = gv;
        this.iZ = iz;
    }
    
    private void a(final bm bm) {
        bk.a(this.gv.getContext(), bm);
    }
    
    private static boolean b(final Uri uri) {
        final String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
    
    private void c(final Uri uri) {
        final String path = uri.getPath();
        final an an = this.iU.get(path);
        if (an != null) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            final UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
            urlQuerySanitizer.parseUrl(uri.toString());
            for (final UrlQuerySanitizer$ParameterValuePair urlQuerySanitizer$ParameterValuePair : urlQuerySanitizer.getParameterList()) {
                hashMap.put(urlQuerySanitizer$ParameterValuePair.mParameter, urlQuerySanitizer$ParameterValuePair.mValue);
            }
            if (ct.n(2)) {
                ct.u("Received GMSG: " + path);
                for (final String s : hashMap.keySet()) {
                    ct.u("  " + s + ": " + hashMap.get(s));
                }
            }
            an.a(this.gv, hashMap);
            return;
        }
        ct.v("No GMSG handler found for GMSG: " + uri);
    }
    
    public final void Y() {
        synchronized (this.fx) {
            this.iY = false;
            this.iZ = true;
            final bk ab = this.gv.aB();
            if (ab != null) {
                if (!cs.ay()) {
                    cs.iI.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ab.Y();
                        }
                    });
                }
                else {
                    ab.Y();
                }
            }
        }
    }
    
    public final void a(final bj bj) {
        bn iw = null;
        final boolean af = this.gv.aF();
        q iv;
        if (af && !this.gv.y().eG) {
            iv = null;
        }
        else {
            iv = this.iV;
        }
        if (!af) {
            iw = this.iW;
        }
        this.a(new bm(bj, iv, iw, this.ja, this.gv.aE()));
    }
    
    public final void a(final a ix) {
        this.iX = ix;
    }
    
    public void a(final q iv, final bn iw, final al fm, final bq ja, final boolean b) {
        this.a("/appEvent", new ak(fm));
        this.a("/canOpenURLs", am.fn);
        this.a("/click", am.fo);
        this.a("/close", am.fp);
        this.a("/customClose", am.fq);
        this.a("/httpTrack", am.fr);
        this.a("/log", am.fs);
        this.a("/open", am.ft);
        this.a("/touch", am.fu);
        this.a("/video", am.fv);
        this.iV = iv;
        this.iW = iw;
        this.fm = fm;
        this.ja = ja;
        this.m(b);
    }
    
    public final void a(final String s, final an an) {
        this.iU.put(s, an);
    }
    
    public final void a(final boolean b, final int n) {
        q iv;
        if (this.gv.aF() && !this.gv.y().eG) {
            iv = null;
        }
        else {
            iv = this.iV;
        }
        this.a(new bm(iv, this.iW, this.ja, this.gv, b, n, this.gv.aE()));
    }
    
    public final void a(final boolean b, final int n, final String s) {
        bn iw = null;
        final boolean af = this.gv.aF();
        q iv;
        if (af && !this.gv.y().eG) {
            iv = null;
        }
        else {
            iv = this.iV;
        }
        if (!af) {
            iw = this.iW;
        }
        this.a(new bm(iv, iw, this.fm, this.ja, this.gv, b, n, s, this.gv.aE()));
    }
    
    public final void a(final boolean b, final int n, final String s, final String s2) {
        bn iw = null;
        final boolean af = this.gv.aF();
        q iv;
        if (af && !this.gv.y().eG) {
            iv = null;
        }
        else {
            iv = this.iV;
        }
        if (!af) {
            iw = this.iW;
        }
        this.a(new bm(iv, iw, this.fm, this.ja, this.gv, b, n, s, s2, this.gv.aE()));
    }
    
    public boolean aJ() {
        synchronized (this.fx) {
            return this.iZ;
        }
    }
    
    public final void m(final boolean iy) {
        this.iY = iy;
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        if (this.iX != null) {
            this.iX.a(this.gv);
            this.iX = null;
        }
    }
    
    public final void reset() {
        synchronized (this.fx) {
            this.iU.clear();
            this.iV = null;
            this.iW = null;
            this.iX = null;
            this.fm = null;
            this.iY = false;
            this.iZ = false;
            this.ja = null;
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        ct.u("AdWebView shouldOverrideUrlLoading: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.c(parse);
        }
        else {
            if (this.iY && webView == this.gv && b(parse)) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.gv.willNotDraw()) {
                while (true) {
                    try {
                        final h ad = this.gv.aD();
                        Uri a = parse;
                        if (ad != null) {
                            a = parse;
                            if (ad.a(parse)) {
                                a = ad.a(parse, this.gv.getContext());
                            }
                        }
                        this.a(new bj("android.intent.action.VIEW", a.toString(), null, null, null, null, null));
                        return true;
                    }
                    catch (i i) {
                        ct.v("Unable to append parameter to URL: " + s);
                        final Uri a = parse;
                        continue;
                    }
                    break;
                }
            }
            ct.v("AdWebView unable to handle URL: " + s);
        }
        return true;
    }
    
    public interface a
    {
        void a(final cw p0);
    }
}
