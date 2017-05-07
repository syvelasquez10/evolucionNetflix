// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.webkit.WebView;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.app.Activity;

public class cc extends ck.a
{
    private dz lC;
    private final Activity nS;
    private ce nT;
    private cg nU;
    private c nV;
    private ch nW;
    private boolean nX;
    private FrameLayout nY;
    private WebChromeClient$CustomViewCallback nZ;
    private boolean oa;
    private boolean ob;
    private RelativeLayout oc;
    
    public cc(final Activity ns) {
        this.oa = false;
        this.ob = false;
        this.nS = ns;
    }
    
    private static RelativeLayout$LayoutParams a(final int n, final int n2, final int n3, final int n4) {
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n3, n4);
        relativeLayout$LayoutParams.setMargins(n, n2, 0, 0);
        relativeLayout$LayoutParams.addRule(10);
        relativeLayout$LayoutParams.addRule(9);
        return relativeLayout$LayoutParams;
    }
    
    public static void a(final Context context, final ce ce) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", ce.kK.rt);
        ce.a(intent, ce);
        intent.addFlags(524288);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }
    
    private void aN() {
        if (this.nS.isFinishing() && !this.ob) {
            this.ob = true;
            if (this.nS.isFinishing()) {
                if (this.lC != null) {
                    this.lC.bF();
                    this.oc.removeView((View)this.lC);
                    if (this.nV != null) {
                        this.lC.p(false);
                        this.nV.of.addView((View)this.lC, this.nV.index, this.nV.oe);
                    }
                }
                if (this.nT != null && this.nT.oi != null) {
                    this.nT.oi.V();
                }
            }
        }
    }
    
    private void j(final boolean b) throws a {
        if (!this.nX) {
            this.nS.requestWindowFeature(1);
        }
        final Window window = this.nS.getWindow();
        window.setFlags(1024, 1024);
        this.setRequestedOrientation(this.nT.orientation);
        if (Build$VERSION.SDK_INT >= 11) {
            dw.v("Enabling hardware acceleration on the AdActivity window.");
            ds.a(window);
        }
        (this.oc = new b((Context)this.nS, this.nT.or)).setBackgroundColor(-16777216);
        this.nS.setContentView((View)this.oc);
        this.N();
        final boolean bp = this.nT.oj.bI().bP();
        if (b) {
            this.lC = dz.a((Context)this.nS, this.nT.oj.R(), true, bp, null, this.nT.kK);
            this.lC.bI().a(null, null, this.nT.ok, this.nT.oo, true, this.nT.oq);
            this.lC.bI().a((ea.a)new ea.a() {
                @Override
                public void a(final dz dz) {
                    dz.bG();
                }
            });
            if (this.nT.nO != null) {
                this.lC.loadUrl(this.nT.nO);
            }
            else {
                if (this.nT.on == null) {
                    throw new a("No URL or HTML to display in ad overlay.");
                }
                this.lC.loadDataWithBaseURL(this.nT.ol, this.nT.on, "text/html", "UTF-8", (String)null);
            }
        }
        else {
            (this.lC = this.nT.oj).setContext((Context)this.nS);
        }
        this.lC.a(this);
        final ViewParent parent = this.lC.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this.lC);
        }
        this.oc.addView((View)this.lC, -1, -1);
        if (!b) {
            this.lC.bG();
        }
        this.h(bp);
    }
    
    public void N() {
        this.nX = true;
    }
    
    public void a(final View view, final WebChromeClient$CustomViewCallback nz) {
        (this.nY = new FrameLayout((Context)this.nS)).setBackgroundColor(-16777216);
        this.nY.addView(view, -1, -1);
        this.nS.setContentView((View)this.nY);
        this.N();
        this.nZ = nz;
    }
    
    public cg aK() {
        return this.nU;
    }
    
    public void aL() {
        if (this.nT != null) {
            this.setRequestedOrientation(this.nT.orientation);
        }
        if (this.nY != null) {
            this.nS.setContentView((View)this.oc);
            this.N();
            this.nY.removeAllViews();
            this.nY = null;
        }
        if (this.nZ != null) {
            this.nZ.onCustomViewHidden();
            this.nZ = null;
        }
    }
    
    public void aM() {
        this.oc.removeView((View)this.nW);
        this.h(true);
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        if (this.nU != null) {
            this.nU.setLayoutParams((ViewGroup$LayoutParams)a(n, n2, n3, n4));
        }
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        if (this.nU == null) {
            this.nU = new cg((Context)this.nS, this.lC);
            this.oc.addView((View)this.nU, 0, (ViewGroup$LayoutParams)a(n, n2, n3, n4));
            this.lC.bI().q(false);
        }
    }
    
    public void close() {
        this.nS.finish();
    }
    
    public void h(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.nW = new ch(this.nS, n);
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
        this.nW.i(this.nT.om);
        this.oc.addView((View)this.nW, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    public void i(final boolean b) {
        if (this.nW != null) {
            this.nW.i(b);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.oa = boolean1;
        Label_0068: {
            try {
                this.nT = ce.a(this.nS.getIntent());
                if (this.nT == null) {
                    throw new a("Could not get info for ad overlay.");
                }
                break Label_0068;
            }
            catch (a a) {
                dw.z(a.getMessage());
                this.nS.finish();
            }
            return;
        }
        if (bundle == null) {
            if (this.nT.oi != null) {
                this.nT.oi.W();
            }
            if (this.nT.op != 1 && this.nT.oh != null) {
                this.nT.oh.P();
            }
        }
        switch (this.nT.op) {
            case 1: {
                this.j(false);
            }
            case 2: {
                this.nV = new c(this.nT.oj);
                this.j(false);
            }
            case 3: {
                this.j(true);
            }
            case 4: {
                if (this.oa) {
                    this.nS.finish();
                    return;
                }
                if (!bz.a((Context)this.nS, this.nT.og, this.nT.oo)) {
                    this.nS.finish();
                }
            }
            default: {
                throw new a("Could not determine ad overlay type.");
            }
        }
    }
    
    public void onDestroy() {
        if (this.nU != null) {
            this.nU.destroy();
        }
        if (this.lC != null) {
            this.oc.removeView((View)this.lC);
        }
        this.aN();
    }
    
    public void onPause() {
        if (this.nU != null) {
            this.nU.pause();
        }
        this.aL();
        if (this.lC != null && (!this.nS.isFinishing() || this.nV == null)) {
            dq.a(this.lC);
        }
        this.aN();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.nT != null && this.nT.op == 4) {
            if (this.oa) {
                this.nS.finish();
            }
            else {
                this.oa = true;
            }
        }
        if (this.lC != null) {
            dq.b(this.lC);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.oa);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.aN();
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.nS.setRequestedOrientation(requestedOrientation);
    }
    
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
    
    private static final class b extends RelativeLayout
    {
        private final dr kF;
        
        public b(final Context context, final String s) {
            super(context);
            this.kF = new dr(context, s);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            this.kF.c(motionEvent);
            return false;
        }
    }
    
    private static final class c
    {
        public final int index;
        public final ViewGroup$LayoutParams oe;
        public final ViewGroup of;
        
        public c(final dz dz) throws a {
            this.oe = dz.getLayoutParams();
            final ViewParent parent = dz.getParent();
            if (parent instanceof ViewGroup) {
                this.of = (ViewGroup)parent;
                this.index = this.of.indexOfChild((View)dz);
                this.of.removeView((View)dz);
                dz.p(true);
                return;
            }
            throw new a("Could not get the parent of the WebView for an overlay.");
        }
    }
}
