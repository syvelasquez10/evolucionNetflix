// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewParent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.view.Window;
import android.os.Build$VERSION;
import android.content.Intent;
import android.content.Context;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.app.Activity;
import android.widget.RelativeLayout;

public final class bk extends bs.a
{
    private boolean gA;
    private boolean gB;
    private RelativeLayout gC;
    private final Activity gs;
    private bm gt;
    private bo gu;
    private cw gv;
    private b gw;
    private bp gx;
    private FrameLayout gy;
    private WebChromeClient$CustomViewCallback gz;
    
    public bk(final Activity gs) {
        this.gA = false;
        this.gB = false;
        this.gs = gs;
    }
    
    private void Z() {
        if (this.gs.isFinishing() && !this.gB) {
            this.gB = true;
            if (this.gs.isFinishing()) {
                if (this.gv != null) {
                    this.gv.az();
                    this.gC.removeView((View)this.gv);
                    if (this.gw != null) {
                        this.gv.l(false);
                        this.gw.gF.addView((View)this.gv, this.gw.index, this.gw.gE);
                    }
                }
                if (this.gt != null && this.gt.gI != null) {
                    this.gt.gI.A();
                }
            }
        }
    }
    
    private static RelativeLayout$LayoutParams a(final int n, final int n2, final int n3, final int n4) {
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n3, n4);
        relativeLayout$LayoutParams.setMargins(n, n2, 0, 0);
        relativeLayout$LayoutParams.addRule(10);
        relativeLayout$LayoutParams.addRule(9);
        return relativeLayout$LayoutParams;
    }
    
    public static void a(final Context context, final bm bm) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", bm.ej.iM);
        bm.a(intent, bm);
        intent.addFlags(524288);
        context.startActivity(intent);
    }
    
    private void h(final boolean b) throws a {
        this.gs.requestWindowFeature(1);
        final Window window = this.gs.getWindow();
        window.setFlags(1024, 1024);
        this.setRequestedOrientation(this.gt.orientation);
        if (Build$VERSION.SDK_INT >= 11) {
            ct.r("Enabling hardware acceleration on the AdActivity window.");
            cp.a(window);
        }
        (this.gC = new RelativeLayout((Context)this.gs)).setBackgroundColor(-16777216);
        this.gs.setContentView((View)this.gC);
        final boolean aj = this.gt.gJ.aC().aJ();
        if (b) {
            this.gv = cw.a((Context)this.gs, this.gt.gJ.y(), true, aj, null, this.gt.ej);
            this.gv.aC().a(null, null, this.gt.gK, this.gt.gO, true);
            this.gv.aC().a((cx.a)new cx.a() {
                @Override
                public void a(final cw cw) {
                    cw.aA();
                }
            });
            if (this.gt.go != null) {
                this.gv.loadUrl(this.gt.go);
            }
            else {
                if (this.gt.gN == null) {
                    throw new a("No URL or HTML to display in ad overlay.");
                }
                this.gv.loadDataWithBaseURL(this.gt.gL, this.gt.gN, "text/html", "UTF-8", (String)null);
            }
        }
        else {
            (this.gv = this.gt.gJ).setContext((Context)this.gs);
        }
        this.gv.a(this);
        this.gC.addView((View)this.gv, -1, -1);
        if (!b) {
            this.gv.aA();
        }
        this.f(aj);
    }
    
    public bo W() {
        return this.gu;
    }
    
    public void X() {
        if (this.gt != null) {
            this.setRequestedOrientation(this.gt.orientation);
        }
        if (this.gy != null) {
            this.gs.setContentView((View)this.gC);
            this.gy.removeAllViews();
            this.gy = null;
        }
        if (this.gz != null) {
            this.gz.onCustomViewHidden();
            this.gz = null;
        }
    }
    
    public void Y() {
        this.gC.removeView((View)this.gx);
        this.f(true);
    }
    
    public void a(final View view, final WebChromeClient$CustomViewCallback gz) {
        (this.gy = new FrameLayout((Context)this.gs)).setBackgroundColor(-16777216);
        this.gy.addView(view, -1, -1);
        this.gs.setContentView((View)this.gy);
        this.gz = gz;
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        if (this.gu != null) {
            this.gu.setLayoutParams((ViewGroup$LayoutParams)a(n, n2, n3, n4));
        }
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        if (this.gu == null) {
            this.gu = new bo((Context)this.gs, this.gv);
            this.gC.addView((View)this.gu, 0, (ViewGroup$LayoutParams)a(n, n2, n3, n4));
            this.gv.aC().m(false);
        }
    }
    
    public void close() {
        this.gs.finish();
    }
    
    public void f(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.gx = new bp(this.gs, n);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.addRule(10);
        int n2;
        if (b) {
            n2 = 11;
        }
        else {
            n2 = 9;
        }
        relativeLayout$LayoutParams.addRule(n2);
        this.gx.g(this.gt.gM);
        this.gC.addView((View)this.gx, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    public void g(final boolean b) {
        if (this.gx != null) {
            this.gx.g(b);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.gA = boolean1;
        Label_0068: {
            try {
                this.gt = bm.a(this.gs.getIntent());
                if (this.gt == null) {
                    throw new a("Could not get info for ad overlay.");
                }
                break Label_0068;
            }
            catch (a a) {
                ct.v(a.getMessage());
                this.gs.finish();
            }
            return;
        }
        if (bundle == null) {
            if (this.gt.gI != null) {
                this.gt.gI.B();
            }
            if (this.gt.gP != 1 && this.gt.gH != null) {
                this.gt.gH.w();
            }
        }
        switch (this.gt.gP) {
            case 1: {
                this.h(false);
            }
            case 2: {
                this.gw = new b(this.gt.gJ);
                this.h(false);
            }
            case 3: {
                this.h(true);
            }
            case 4: {
                if (this.gA) {
                    this.gs.finish();
                    return;
                }
                if (!bh.a((Context)this.gs, this.gt.gG, this.gt.gO)) {
                    this.gs.finish();
                }
            }
            default: {
                throw new a("Could not determine ad overlay type.");
            }
        }
    }
    
    public void onDestroy() {
        if (this.gu != null) {
            this.gu.destroy();
        }
        if (this.gv != null) {
            this.gC.removeView((View)this.gv);
        }
        this.Z();
    }
    
    public void onPause() {
        if (this.gu != null) {
            this.gu.pause();
        }
        this.X();
        if (this.gv != null && (!this.gs.isFinishing() || this.gw == null)) {
            co.a(this.gv);
        }
        this.Z();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.gt != null && this.gt.gP == 4) {
            if (this.gA) {
                this.gs.finish();
            }
            else {
                this.gA = true;
            }
        }
        if (this.gv != null) {
            co.b(this.gv);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.gA);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.Z();
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.gs.setRequestedOrientation(requestedOrientation);
    }
    
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
    
    private static final class b
    {
        public final ViewGroup$LayoutParams gE;
        public final ViewGroup gF;
        public final int index;
        
        public b(final cw cw) throws a {
            this.gE = cw.getLayoutParams();
            final ViewParent parent = cw.getParent();
            if (parent instanceof ViewGroup) {
                this.gF = (ViewGroup)parent;
                this.index = this.gF.indexOfChild((View)cw);
                this.gF.removeView((View)cw);
                cw.l(true);
                return;
            }
            throw new a("Could not get the parent of the WebView for an overlay.");
        }
    }
}
