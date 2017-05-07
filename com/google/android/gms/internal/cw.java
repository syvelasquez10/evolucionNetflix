// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.MutableContextWrapper;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import java.util.HashMap;
import org.json.JSONException;
import java.util.Map;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.os.Build$VERSION;
import android.content.Context;
import android.webkit.DownloadListener;
import android.webkit.WebView;

public final class cw extends WebView implements DownloadListener
{
    private x fU;
    private final cu fV;
    private final Object fx;
    private final h he;
    private final cx iN;
    private final a iO;
    private bk iP;
    private boolean iQ;
    private boolean iR;
    
    private cw(final a io, final x fu, final boolean iq, final boolean b, final h he, final cu fv) {
        super((Context)io);
        this.fx = new Object();
        this.iO = io;
        this.fU = fu;
        this.iQ = iq;
        this.he = he;
        this.fV = fv;
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        co.a((Context)io, fv.iJ, settings);
        if (Build$VERSION.SDK_INT >= 17) {
            cq.a(this.getContext(), settings);
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            cp.a(this.getContext(), settings);
        }
        this.setDownloadListener((DownloadListener)this);
        if (Build$VERSION.SDK_INT >= 11) {
            this.iN = new cz(this, b);
        }
        else {
            this.iN = new cx(this, b);
        }
        this.setWebViewClient((WebViewClient)this.iN);
        if (Build$VERSION.SDK_INT >= 14) {
            this.setWebChromeClient((WebChromeClient)new da(this));
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            this.setWebChromeClient((WebChromeClient)new cy(this));
        }
        this.aG();
    }
    
    public static cw a(final Context context, final x x, final boolean b, final boolean b2, final h h, final cu cu) {
        return new cw(new a(context), x, b, b2, h, cu);
    }
    
    private void aG() {
        while (true) {
            synchronized (this.fx) {
                if (this.iQ || this.fU.eG) {
                    if (Build$VERSION.SDK_INT < 14) {
                        ct.r("Disabling hardware acceleration on an overlay.");
                        this.aH();
                    }
                    else {
                        ct.r("Enabling hardware acceleration on an overlay.");
                        this.aI();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                ct.r("Disabling hardware acceleration on an AdView.");
                this.aH();
                return;
            }
            ct.r("Enabling hardware acceleration on an AdView.");
            this.aI();
        }
    }
    
    private void aH() {
        synchronized (this.fx) {
            if (!this.iR && Build$VERSION.SDK_INT >= 11) {
                cp.c((View)this);
            }
            this.iR = true;
        }
    }
    
    private void aI() {
        synchronized (this.fx) {
            if (this.iR && Build$VERSION.SDK_INT >= 11) {
                cp.d((View)this);
            }
            this.iR = false;
        }
    }
    
    public void a(final Context baseContext, final x fu) {
        synchronized (this.fx) {
            this.iO.setBaseContext(baseContext);
            this.iP = null;
            this.fU = fu;
            this.iQ = false;
            co.b(this);
            this.loadUrl("about:blank");
            this.iN.reset();
        }
    }
    
    public void a(final bk ip) {
        synchronized (this.fx) {
            this.iP = ip;
        }
    }
    
    public void a(final x fu) {
        synchronized (this.fx) {
            this.fU = fu;
            this.requestLayout();
        }
    }
    
    public void a(String string, final Map<String, ?> map) {
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:AFMA_ReceiveMessage('");
        sb.append(string);
        sb.append("'");
        Label_0053: {
            if (map == null) {
                break Label_0053;
            }
            try {
                string = co.m(map).toString();
                sb.append(",");
                sb.append(string);
                sb.append(");");
                ct.u("Dispatching AFMA event: " + (Object)sb);
                this.loadUrl(sb.toString());
            }
            catch (JSONException ex) {
                ct.v("Could not convert AFMA event parameters to JSON.");
            }
        }
    }
    
    public void aA() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.fV.iJ);
        this.a("onshow", hashMap);
    }
    
    public bk aB() {
        synchronized (this.fx) {
            return this.iP;
        }
    }
    
    public cx aC() {
        return this.iN;
    }
    
    public h aD() {
        return this.he;
    }
    
    public cu aE() {
        return this.fV;
    }
    
    public boolean aF() {
        synchronized (this.fx) {
            return this.iQ;
        }
    }
    
    public void az() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.fV.iJ);
        this.a("onhide", hashMap);
    }
    
    public void l(final boolean iq) {
        synchronized (this.fx) {
            this.iQ = iq;
            this.aG();
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            this.getContext().startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            ct.r("Couldn't find an Activity to view url/mimetype: " + s + " / " + s4);
        }
    }
    
    protected void onMeasure(int n, int n2) {
        int n3;
        int mode;
        int size = 0;
        int mode2 = 0;
        int size2 = 0;
        Label_0102_Outer:Label_0080_Outer:
        while (true) {
            n3 = Integer.MAX_VALUE;
            while (true) {
                Label_0250: {
                    Label_0248: {
                        Label_0241: {
                            synchronized (this.fx) {
                                if (this.isInEditMode() || this.iQ) {
                                    super.onMeasure(n, n2);
                                    return;
                                }
                                mode = View$MeasureSpec.getMode(n);
                                size = View$MeasureSpec.getSize(n);
                                mode2 = View$MeasureSpec.getMode(n2);
                                size2 = View$MeasureSpec.getSize(n2);
                                if (mode != Integer.MIN_VALUE && mode != 1073741824) {
                                    break Label_0241;
                                }
                                break Label_0248;
                                // iftrue(Label_0188:, this.getVisibility() == 8)
                                while (true) {
                                    while (true) {
                                        this.setMeasuredDimension(0, 0);
                                        return;
                                        ct.v("Not enough space to show ad. Needs " + this.fU.widthPixels + "x" + this.fU.heightPixels + " pixels, but only has " + size + "x" + size2 + " pixels.");
                                        this.setVisibility(4);
                                        continue Label_0102_Outer;
                                    }
                                    continue Label_0080_Outer;
                                }
                            }
                            // iftrue(Label_0206:, this.fU.widthPixels <= n && this.fU.heightPixels <= n2)
                            Label_0206: {
                                if (this.getVisibility() != 8) {
                                    this.setVisibility(0);
                                }
                            }
                            this.setMeasuredDimension(this.fU.widthPixels, this.fU.heightPixels);
                            return;
                        }
                        n = Integer.MAX_VALUE;
                        break Label_0250;
                    }
                    n = size;
                }
                if (mode2 != Integer.MIN_VALUE) {
                    n2 = n3;
                    if (mode2 != 1073741824) {
                        continue;
                    }
                }
                n2 = size2;
                continue;
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.he != null) {
            this.he.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setContext(final Context baseContext) {
        this.iO.setBaseContext(baseContext);
    }
    
    public x y() {
        synchronized (this.fx) {
            return this.fU;
        }
    }
    
    private static class a extends MutableContextWrapper
    {
        private Activity iS;
        private Context iT;
        
        public a(final Context baseContext) {
            super(baseContext);
            this.setBaseContext(baseContext);
        }
        
        public void setBaseContext(final Context context) {
            this.iT = context.getApplicationContext();
            Activity is;
            if (context instanceof Activity) {
                is = (Activity)context;
            }
            else {
                is = null;
            }
            this.iS = is;
            super.setBaseContext(this.iT);
        }
        
        public void startActivity(final Intent intent) {
            if (this.iS != null) {
                this.iS.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.iT.startActivity(intent);
        }
    }
}
