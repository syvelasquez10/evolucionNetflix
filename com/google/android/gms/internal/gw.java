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

@ez
public class gw extends WebViewClient
{
    protected final gv md;
    private final Object mw;
    private cb pJ;
    private bz pL;
    private v pM;
    private bw pz;
    private a tg;
    private final HashMap<String, by> wP;
    private t wQ;
    private dn wR;
    private boolean wS;
    private boolean wT;
    private dq wU;
    private final dg wV;
    
    public gw(final gv gv, final boolean b) {
        this(gv, b, new dg(gv, gv.getContext(), new bl(gv.getContext())));
    }
    
    gw(final gv md, final boolean wt, final dg wv) {
        this.wP = new HashMap<String, by>();
        this.mw = new Object();
        this.wS = false;
        this.md = md;
        this.wT = wt;
        this.wV = wv;
    }
    
    private static boolean d(final Uri uri) {
        final String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
    
    private void e(final Uri uri) {
        final String path = uri.getPath();
        final by by = this.wP.get(path);
        if (by != null) {
            final Map<String, String> c = gj.c(uri);
            if (gs.u(2)) {
                gs.V("Received GMSG: " + path);
                for (final String s : c.keySet()) {
                    gs.V("  " + s + ": " + c.get(s));
                }
            }
            by.a(this.md, c);
            return;
        }
        gs.V("No GMSG handler found for GMSG: " + uri);
    }
    
    public final void a(final dj dj) {
        dn wr = null;
        final boolean dz = this.md.dz();
        t wq;
        if (dz && !this.md.Y().og) {
            wq = null;
        }
        else {
            wq = this.wQ;
        }
        if (!dz) {
            wr = this.wR;
        }
        this.a(new dm(dj, wq, wr, this.wU, this.md.dy()));
    }
    
    protected void a(final dm dm) {
        dk.a(this.md.getContext(), dm);
    }
    
    public final void a(final a tg) {
        this.tg = tg;
    }
    
    public void a(final t t, final dn dn, final bw bw, final dq dq, final boolean b, final bz bz, final cb pj, final v v) {
        this.a(t, dn, bw, dq, b, bz, v);
        this.a("/setInterstitialProperties", new ca(pj));
        this.pJ = pj;
    }
    
    public void a(final t wq, final dn wr, final bw pz, final dq wu, final boolean b, final bz pl, final v v) {
        v pm = v;
        if (v == null) {
            pm = new v(false);
        }
        this.a("/appEvent", new bv(pz));
        this.a("/canOpenURLs", bx.pB);
        this.a("/click", bx.pC);
        this.a("/close", bx.pD);
        this.a("/customClose", bx.pE);
        this.a("/httpTrack", bx.pF);
        this.a("/log", bx.pG);
        this.a("/open", new cd(pl, pm));
        this.a("/touch", bx.pH);
        this.a("/video", bx.pI);
        this.a("/mraid", new cc());
        this.wQ = wq;
        this.wR = wr;
        this.pz = pz;
        this.pL = pl;
        this.wU = wu;
        this.pM = pm;
        this.y(b);
    }
    
    public final void a(final String s, final by by) {
        this.wP.put(s, by);
    }
    
    public final void a(final boolean b, final int n) {
        t wq;
        if (this.md.dz() && !this.md.Y().og) {
            wq = null;
        }
        else {
            wq = this.wQ;
        }
        this.a(new dm(wq, this.wR, this.wU, this.md, b, n, this.md.dy()));
    }
    
    public final void a(final boolean b, final int n, final String s) {
        dn wr = null;
        final boolean dz = this.md.dz();
        t wq;
        if (dz && !this.md.Y().og) {
            wq = null;
        }
        else {
            wq = this.wQ;
        }
        if (!dz) {
            wr = this.wR;
        }
        this.a(new dm(wq, wr, this.pz, this.wU, this.md, b, n, s, this.md.dy(), this.pL));
    }
    
    public final void a(final boolean b, final int n, final String s, final String s2) {
        final boolean dz = this.md.dz();
        t wq;
        if (dz && !this.md.Y().og) {
            wq = null;
        }
        else {
            wq = this.wQ;
        }
        dn wr;
        if (dz) {
            wr = null;
        }
        else {
            wr = this.wR;
        }
        this.a(new dm(wq, wr, this.pz, this.wU, this.md, b, n, s, s2, this.md.dy(), this.pL));
    }
    
    public final void bY() {
        synchronized (this.mw) {
            this.wS = false;
            this.wT = true;
            final dk du = this.md.du();
            if (du != null) {
                if (!gr.dt()) {
                    gr.wC.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            du.bY();
                        }
                    });
                }
                else {
                    du.bY();
                }
            }
        }
    }
    
    public v dE() {
        return this.pM;
    }
    
    public boolean dF() {
        synchronized (this.mw) {
            return this.wT;
        }
    }
    
    public void dG() {
        if (this.dF()) {
            this.wV.bQ();
        }
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        gs.V("Loading resource: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.e(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        if (this.tg != null) {
            this.tg.a(this.md);
            this.tg = null;
        }
    }
    
    public final void reset() {
        synchronized (this.mw) {
            this.wP.clear();
            this.wQ = null;
            this.wR = null;
            this.tg = null;
            this.pz = null;
            this.wS = false;
            this.wT = false;
            this.pL = null;
            this.wU = null;
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        gs.V("AdWebView shouldOverrideUrlLoading: " + s);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.e(parse);
        }
        else {
            if (this.wS && webView == this.md && d(parse)) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.md.willNotDraw()) {
                while (true) {
                    try {
                        final k dx = this.md.dx();
                        Uri a = parse;
                        if (dx != null) {
                            a = parse;
                            if (dx.b(parse)) {
                                a = dx.a(parse, this.md.getContext());
                            }
                        }
                        if (this.pM == null || this.pM.av()) {
                            this.a(new dj("android.intent.action.VIEW", a.toString(), null, null, null, null, null));
                            return true;
                        }
                    }
                    catch (l l) {
                        gs.W("Unable to append parameter to URL: " + s);
                        final Uri a = parse;
                        continue;
                    }
                    break;
                }
                this.pM.d(s);
            }
            else {
                gs.W("AdWebView unable to handle URL: " + s);
            }
        }
        return true;
    }
    
    public final void y(final boolean ws) {
        this.wS = ws;
    }
    
    public interface a
    {
        void a(final gv p0);
    }
}
