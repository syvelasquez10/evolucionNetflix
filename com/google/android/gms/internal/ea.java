// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import java.util.HashMap;
import android.webkit.WebViewClient;

public class ea extends WebViewClient
{
    protected final dz lC;
    private final Object li;
    private az mF;
    private bc mP;
    private a oW;
    private final HashMap<String, bb> rA;
    private u rB;
    private cf rC;
    private boolean rD;
    private boolean rE;
    private ci rF;
    
    public ea(final dz lc, final boolean re) {
        this.rA = new HashMap<String, bb>();
        this.li = new Object();
        this.rD = false;
        this.lC = lc;
        this.rE = re;
    }
    
    private static boolean c(final Uri uri) {
        final String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
    
    private void d(final Uri uri) {
        final String path = uri.getPath();
        final bb bb = this.rA.get(path);
        if (bb != null) {
            final Map<String, String> b = dq.b(uri);
            if (dw.n(2)) {
                dw.y("Received GMSG: " + path);
                for (final String s : b.keySet()) {
                    dw.y("  " + s + ": " + b.get(s));
                }
            }
            bb.b(this.lC, b);
            return;
        }
        dw.y("No GMSG handler found for GMSG: " + uri);
    }
    
    public final void a(final cb cb) {
        cf rc = null;
        final boolean bl = this.lC.bL();
        u rb;
        if (bl && !this.lC.R().lT) {
            rb = null;
        }
        else {
            rb = this.rB;
        }
        if (!bl) {
            rc = this.rC;
        }
        this.a(new ce(cb, rb, rc, this.rF, this.lC.bK()));
    }
    
    protected void a(final ce ce) {
        cc.a(this.lC.getContext(), ce);
    }
    
    public final void a(final a ow) {
        this.oW = ow;
    }
    
    public void a(final u rb, final cf rc, final az mf, final ci rf, final boolean b, final bc mp) {
        this.a("/appEvent", new ay(mf));
        this.a("/canOpenURLs", ba.mH);
        this.a("/click", ba.mI);
        this.a("/close", ba.mJ);
        this.a("/customClose", ba.mK);
        this.a("/httpTrack", ba.mL);
        this.a("/log", ba.mM);
        this.a("/open", new bd(mp));
        this.a("/touch", ba.mN);
        this.a("/video", ba.mO);
        this.rB = rb;
        this.rC = rc;
        this.mF = mf;
        this.mP = mp;
        this.rF = rf;
        this.q(b);
    }
    
    public final void a(final String s, final bb bb) {
        this.rA.put(s, bb);
    }
    
    public final void a(final boolean b, final int n) {
        u rb;
        if (this.lC.bL() && !this.lC.R().lT) {
            rb = null;
        }
        else {
            rb = this.rB;
        }
        this.a(new ce(rb, this.rC, this.rF, this.lC, b, n, this.lC.bK()));
    }
    
    public final void a(final boolean b, final int n, final String s) {
        cf rc = null;
        final boolean bl = this.lC.bL();
        u rb;
        if (bl && !this.lC.R().lT) {
            rb = null;
        }
        else {
            rb = this.rB;
        }
        if (!bl) {
            rc = this.rC;
        }
        this.a(new ce(rb, rc, this.mF, this.rF, this.lC, b, n, s, this.lC.bK(), this.mP));
    }
    
    public final void a(final boolean b, final int n, final String s, final String s2) {
        final boolean bl = this.lC.bL();
        u rb;
        if (bl && !this.lC.R().lT) {
            rb = null;
        }
        else {
            rb = this.rB;
        }
        cf rc;
        if (bl) {
            rc = null;
        }
        else {
            rc = this.rC;
        }
        this.a(new ce(rb, rc, this.mF, this.rF, this.lC, b, n, s, s2, this.lC.bK(), this.mP));
    }
    
    public final void aM() {
        synchronized (this.li) {
            this.rD = false;
            this.rE = true;
            final cc bh = this.lC.bH();
            if (bh != null) {
                if (!dv.bD()) {
                    dv.rp.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            bh.aM();
                        }
                    });
                }
                else {
                    bh.aM();
                }
            }
        }
    }
    
    public boolean bP() {
        synchronized (this.li) {
            return this.rE;
        }
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        dw.y("Loading resource: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.d(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        if (this.oW != null) {
            this.oW.a(this.lC);
            this.oW = null;
        }
    }
    
    public final void q(final boolean rd) {
        this.rD = rd;
    }
    
    public final void reset() {
        synchronized (this.li) {
            this.rA.clear();
            this.rB = null;
            this.rC = null;
            this.oW = null;
            this.mF = null;
            this.rD = false;
            this.rE = false;
            this.mP = null;
            this.rF = null;
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        dw.y("AdWebView shouldOverrideUrlLoading: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.d(parse);
        }
        else {
            if (this.rD && webView == this.lC && c(parse)) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.lC.willNotDraw()) {
                while (true) {
                    try {
                        final l bj = this.lC.bJ();
                        Uri a = parse;
                        if (bj != null) {
                            a = parse;
                            if (bj.a(parse)) {
                                a = bj.a(parse, this.lC.getContext());
                            }
                        }
                        this.a(new cb("android.intent.action.VIEW", a.toString(), null, null, null, null, null));
                        return true;
                    }
                    catch (m m) {
                        dw.z("Unable to append parameter to URL: " + s);
                        final Uri a = parse;
                        continue;
                    }
                    break;
                }
            }
            dw.z("AdWebView unable to handle URL: " + s);
        }
        return true;
    }
    
    public interface a
    {
        void a(final dz p0);
    }
}
