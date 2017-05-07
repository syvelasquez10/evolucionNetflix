// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.webkit.WebView;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.app.Activity;

@ez
public class dk extends ds.a
{
    private static final int ru;
    private gv md;
    private final Activity nr;
    private boolean rA;
    private FrameLayout rB;
    private WebChromeClient$CustomViewCallback rC;
    private boolean rD;
    private boolean rE;
    private boolean rF;
    private RelativeLayout rG;
    private dm rv;
    private do rw;
    private c rx;
    private dp ry;
    private boolean rz;
    
    static {
        ru = Color.argb(0, 0, 0, 0);
    }
    
    public dk(final Activity nr) {
        this.rA = false;
        this.rD = false;
        this.rE = false;
        this.rF = false;
        this.nr = nr;
    }
    
    private static RelativeLayout$LayoutParams a(final int n, final int n2, final int n3, final int n4) {
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n3, n4);
        relativeLayout$LayoutParams.setMargins(n, n2, 0, 0);
        relativeLayout$LayoutParams.addRule(10);
        relativeLayout$LayoutParams.addRule(9);
        return relativeLayout$LayoutParams;
    }
    
    public static void a(final Context context, final dm dm) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", dm.lD.wG);
        dm.a(intent, dm);
        intent.addFlags(524288);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }
    
    public void U() {
        this.rz = true;
    }
    
    public void a(final View view, final WebChromeClient$CustomViewCallback rc) {
        (this.rB = new FrameLayout((Context)this.nr)).setBackgroundColor(-16777216);
        this.rB.addView(view, -1, -1);
        this.nr.setContentView((View)this.rB);
        this.U();
        this.rC = rc;
        this.rA = true;
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        if (this.rw != null) {
            this.rw.setLayoutParams((ViewGroup$LayoutParams)a(n, n2, n3, n4));
        }
    }
    
    public do bW() {
        return this.rw;
    }
    
    public void bX() {
        if (this.rv != null && this.rA) {
            this.setRequestedOrientation(this.rv.orientation);
        }
        if (this.rB != null) {
            this.nr.setContentView((View)this.rG);
            this.U();
            this.rB.removeAllViews();
            this.rB = null;
        }
        if (this.rC != null) {
            this.rC.onCustomViewHidden();
            this.rC = null;
        }
        this.rA = false;
    }
    
    public void bY() {
        this.rG.removeView((View)this.ry);
        this.n(true);
    }
    
    void bZ() {
        if (this.nr.isFinishing() && !this.rE) {
            this.rE = true;
            if (this.nr.isFinishing()) {
                if (this.md != null) {
                    this.cb();
                    this.rG.removeView((View)this.md);
                    if (this.rx != null) {
                        this.md.x(false);
                        this.rx.rJ.addView((View)this.md, this.rx.index, this.rx.rI);
                    }
                }
                if (this.rv != null && this.rv.rM != null) {
                    this.rv.rM.ac();
                }
            }
        }
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        if (this.rw == null) {
            this.rw = new do((Context)this.nr, this.md);
            this.rG.addView((View)this.rw, 0, (ViewGroup$LayoutParams)a(n, n2, n3, n4));
            this.md.dv().y(false);
        }
    }
    
    void ca() {
        this.md.ca();
    }
    
    void cb() {
        this.md.cb();
    }
    
    public void close() {
        this.nr.finish();
    }
    
    public void n(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.ry = new dp(this.nr, n);
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
        this.ry.o(this.rv.rQ);
        this.rG.addView((View)this.ry, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    public void o(final boolean b) {
        if (this.ry != null) {
            this.ry.o(b);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.rD = boolean1;
        Label_0068: {
            try {
                this.rv = dm.b(this.nr.getIntent());
                if (this.rv == null) {
                    throw new a("Could not get info for ad overlay.");
                }
                break Label_0068;
            }
            catch (a a) {
                gs.W(a.getMessage());
                this.nr.finish();
            }
            return;
        }
        if (this.rv.rW != null) {
            this.rF = this.rv.rW.lX;
        }
        else {
            this.rF = false;
        }
        if (bundle == null) {
            if (this.rv.rM != null) {
                this.rv.rM.ad();
            }
            if (this.rv.rT != 1 && this.rv.rL != null) {
                this.rv.rL.onAdClicked();
            }
        }
        switch (this.rv.rT) {
            case 1: {
                this.p(false);
            }
            case 2: {
                this.rx = new c(this.rv.rN);
                this.p(false);
            }
            case 3: {
                this.p(true);
            }
            case 4: {
                if (this.rD) {
                    this.nr.finish();
                    return;
                }
                if (!dh.a((Context)this.nr, this.rv.rK, this.rv.rS)) {
                    this.nr.finish();
                }
            }
            default: {
                throw new a("Could not determine ad overlay type.");
            }
        }
    }
    
    public void onDestroy() {
        if (this.rw != null) {
            this.rw.destroy();
        }
        if (this.md != null) {
            this.rG.removeView((View)this.md);
        }
        this.bZ();
    }
    
    public void onPause() {
        if (this.rw != null) {
            this.rw.pause();
        }
        this.bX();
        if (this.md != null && (!this.nr.isFinishing() || this.rx == null)) {
            gj.a(this.md);
        }
        this.bZ();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.rv != null && this.rv.rT == 4) {
            if (this.rD) {
                this.nr.finish();
            }
            else {
                this.rD = true;
            }
        }
        if (this.md != null) {
            gj.b(this.md);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.rD);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.bZ();
    }
    
    void p(final boolean b) throws a {
        if (!this.rz) {
            this.nr.requestWindowFeature(1);
        }
        final Window window = this.nr.getWindow();
        if (!this.rF || this.rv.rW.mh) {
            window.setFlags(1024, 1024);
        }
        this.setRequestedOrientation(this.rv.orientation);
        if (Build$VERSION.SDK_INT >= 11) {
            gs.S("Enabling hardware acceleration on the AdActivity window.");
            gn.a(window);
        }
        this.rG = new b((Context)this.nr, this.rv.rV);
        if (!this.rF) {
            this.rG.setBackgroundColor(-16777216);
        }
        else {
            this.rG.setBackgroundColor(dk.ru);
        }
        this.nr.setContentView((View)this.rG);
        this.U();
        final boolean df = this.rv.rN.dv().dF();
        if (b) {
            this.md = gv.a((Context)this.nr, this.rv.rN.Y(), true, df, null, this.rv.lD);
            this.md.dv().a(null, null, this.rv.rO, this.rv.rS, true, this.rv.rU, this.rv.rN.dv().dE());
            this.md.dv().a((gw.a)new gw.a() {
                @Override
                public void a(final gv gv) {
                    gv.ca();
                }
            });
            if (this.rv.rq != null) {
                this.md.loadUrl(this.rv.rq);
            }
            else {
                if (this.rv.rR == null) {
                    throw new a("No URL or HTML to display in ad overlay.");
                }
                this.md.loadDataWithBaseURL(this.rv.rP, this.rv.rR, "text/html", "UTF-8", (String)null);
            }
        }
        else {
            (this.md = this.rv.rN).setContext((Context)this.nr);
        }
        this.md.a(this);
        final ViewParent parent = this.md.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this.md);
        }
        if (this.rF) {
            this.md.setBackgroundColor(dk.ru);
        }
        this.rG.addView((View)this.md, -1, -1);
        if (!b) {
            this.ca();
        }
        this.n(df);
        if (this.md.dw()) {
            this.o(true);
        }
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.nr.setRequestedOrientation(requestedOrientation);
    }
    
    @ez
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
    
    @ez
    private static final class b extends RelativeLayout
    {
        private final gm ly;
        
        public b(final Context context, final String s) {
            super(context);
            this.ly = new gm(context, s);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            this.ly.c(motionEvent);
            return false;
        }
    }
    
    @ez
    private static final class c
    {
        public final int index;
        public final ViewGroup$LayoutParams rI;
        public final ViewGroup rJ;
        
        public c(final gv gv) throws a {
            this.rI = gv.getLayoutParams();
            final ViewParent parent = gv.getParent();
            if (parent instanceof ViewGroup) {
                this.rJ = (ViewGroup)parent;
                this.index = this.rJ.indexOfChild((View)gv);
                this.rJ.removeView((View)gv);
                gv.x(true);
                return;
            }
            throw new a("Could not get the parent of the WebView for an overlay.");
        }
    }
}
