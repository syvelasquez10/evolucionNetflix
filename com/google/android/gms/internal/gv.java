// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import android.webkit.ValueCallback;
import java.util.HashMap;
import android.view.Display;
import android.util.DisplayMetrics;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Map;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.os.Build$VERSION;
import android.content.Context;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;

@ez
public class gv extends WebView implements DownloadListener
{
    private final WindowManager mG;
    private final Object mw;
    private ay qr;
    private final gt qs;
    private final k sX;
    private final gw wH;
    private final gv$a wI;
    private dk wJ;
    private boolean wK;
    private boolean wL;
    private boolean wM;
    private boolean wN;
    
    private gv(final gv$a wi, final ay qr, final boolean wk, final boolean b, final k sx, final gt qs) {
        super((Context)wi);
        this.mw = new Object();
        this.wI = wi;
        this.qr = qr;
        this.wK = wk;
        this.sX = sx;
        this.qs = qs;
        this.mG = (WindowManager)this.getContext().getSystemService("window");
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        gj.a((Context)wi, qs.wD, settings);
        if (Build$VERSION.SDK_INT >= 17) {
            gp.a(this.getContext(), settings);
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            gn.a(this.getContext(), settings);
        }
        this.setDownloadListener((DownloadListener)this);
        if (Build$VERSION.SDK_INT >= 11) {
            this.wH = new gy(this, b);
        }
        else {
            this.wH = new gw(this, b);
        }
        this.setWebViewClient((WebViewClient)this.wH);
        if (Build$VERSION.SDK_INT >= 14) {
            this.setWebChromeClient((WebChromeClient)new gz(this));
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            this.setWebChromeClient((WebChromeClient)new gx(this));
        }
        this.dB();
    }
    
    public static gv a(final Context context, final ay ay, final boolean b, final boolean b2, final k k, final gt gt) {
        return new gv(new gv$a(context), ay, b, b2, k, gt);
    }
    
    private void dB() {
        while (true) {
            synchronized (this.mw) {
                if (this.wK || this.qr.og) {
                    if (Build$VERSION.SDK_INT < 14) {
                        gs.S("Disabling hardware acceleration on an overlay.");
                        this.dC();
                    }
                    else {
                        gs.S("Enabling hardware acceleration on an overlay.");
                        this.dD();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                gs.S("Disabling hardware acceleration on an AdView.");
                this.dC();
                return;
            }
            gs.S("Enabling hardware acceleration on an AdView.");
            this.dD();
        }
    }
    
    private void dC() {
        synchronized (this.mw) {
            if (!this.wL && Build$VERSION.SDK_INT >= 11) {
                gn.i((View)this);
            }
            this.wL = true;
        }
    }
    
    private void dD() {
        synchronized (this.mw) {
            if (this.wL && Build$VERSION.SDK_INT >= 11) {
                gn.j((View)this);
            }
            this.wL = false;
        }
    }
    
    protected void X(final String s) {
        synchronized (this.mw) {
            if (!this.isDestroyed()) {
                this.loadUrl(s);
            }
            else {
                gs.W("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public ay Y() {
        synchronized (this.mw) {
            return this.qr;
        }
    }
    
    public void a(final Context baseContext, final ay qr) {
        synchronized (this.mw) {
            this.wI.setBaseContext(baseContext);
            this.wJ = null;
            this.qr = qr;
            this.wK = false;
            this.wN = false;
            gj.b(this);
            this.loadUrl("about:blank");
            this.wH.reset();
            this.setOnTouchListener((View$OnTouchListener)null);
            this.setOnClickListener((View$OnClickListener)null);
        }
    }
    
    public void a(final ay qr) {
        synchronized (this.mw) {
            this.qr = qr;
            this.requestLayout();
        }
    }
    
    public void a(final dk wj) {
        synchronized (this.mw) {
            this.wJ = wj;
        }
    }
    
    public void a(final String s, final Map<String, ?> map) {
        try {
            this.b(s, gj.t(map));
        }
        catch (JSONException ex) {
            gs.W("Could not convert parameters to JSON.");
        }
    }
    
    public void a(final String s, final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        final String string = jsonObject2.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + s + "(");
        sb.append(string);
        sb.append(");");
        this.X(sb.toString());
    }
    
    public void b(final String s, final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        final String string = jsonObject2.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:AFMA_ReceiveMessage('");
        sb.append(s);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        gs.V("Dispatching AFMA event: " + (Object)sb);
        this.X(sb.toString());
    }
    
    public void bT() {
        if (!this.dv().dF()) {
            return;
        }
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        final Display defaultDisplay = this.mG.getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        final int s = gj.s(this.getContext());
        final float n = 160.0f / displayMetrics.densityDpi;
        final int round = Math.round(displayMetrics.widthPixels * n);
        final int round2 = Math.round((displayMetrics.heightPixels - s) * n);
        try {
            this.b("onScreenInfoChanged", new JSONObject().put("width", round).put("height", round2).put("density", (double)displayMetrics.density).put("rotation", defaultDisplay.getRotation()));
        }
        catch (JSONException ex) {
            gs.b("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void ca() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.qs.wD);
        this.a("onshow", hashMap);
    }
    
    public void cb() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.qs.wD);
        this.a("onhide", hashMap);
    }
    
    public Context dA() {
        return this.wI.dA();
    }
    
    public void destroy() {
        synchronized (this.mw) {
            super.destroy();
            this.wM = true;
        }
    }
    
    public dk du() {
        synchronized (this.mw) {
            return this.wJ;
        }
    }
    
    public gw dv() {
        return this.wH;
    }
    
    public boolean dw() {
        return this.wN;
    }
    
    public k dx() {
        return this.sX;
    }
    
    public gt dy() {
        return this.qs;
    }
    
    public boolean dz() {
        synchronized (this.mw) {
            return this.wK;
        }
    }
    
    public void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.mw) {
            if (this.isDestroyed()) {
                gs.W("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
                return;
            }
            super.evaluateJavascript(s, (ValueCallback)valueCallback);
        }
    }
    
    public boolean isDestroyed() {
        synchronized (this.mw) {
            return this.wM;
        }
    }
    
    public void o(final boolean wn) {
        synchronized (this.mw) {
            if (this.wJ != null) {
                this.wJ.o(wn);
            }
            else {
                this.wN = wn;
            }
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            this.getContext().startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            gs.S("Couldn't find an Activity to view url/mimetype: " + s + " / " + s4);
        }
    }
    
    protected void onMeasure(int n, int n2) {
        int n3;
        int mode;
        int size = 0;
        int mode2 = 0;
        int size2 = 0;
        float density;
        Label_0103_Outer:Label_0081_Outer:
        while (true) {
            n3 = Integer.MAX_VALUE;
            while (true) {
                Label_0283: {
                    Label_0280: {
                        Label_0273: {
                            synchronized (this.mw) {
                                if (this.isInEditMode() || this.wK) {
                                    super.onMeasure(n, n2);
                                    return;
                                }
                                mode = View$MeasureSpec.getMode(n);
                                size = View$MeasureSpec.getSize(n);
                                mode2 = View$MeasureSpec.getMode(n2);
                                size2 = View$MeasureSpec.getSize(n2);
                                if (mode != Integer.MIN_VALUE && mode != 1073741824) {
                                    break Label_0273;
                                }
                                break Label_0280;
                                // iftrue(Label_0220:, this.getVisibility() == 8)
                                while (true) {
                                    while (true) {
                                        this.setMeasuredDimension(0, 0);
                                        return;
                                        density = this.wI.getResources().getDisplayMetrics().density;
                                        gs.W("Not enough space to show ad. Needs " + (int)(this.qr.widthPixels / density) + "x" + (int)(this.qr.heightPixels / density) + " dp, but only has " + (int)(size / density) + "x" + (int)(size2 / density) + " dp.");
                                        this.setVisibility(4);
                                        continue Label_0103_Outer;
                                    }
                                    continue Label_0081_Outer;
                                }
                            }
                            // iftrue(Label_0238:, this.qr.widthPixels <= n && this.qr.heightPixels <= n2)
                            Label_0238: {
                                if (this.getVisibility() != 8) {
                                    this.setVisibility(0);
                                }
                            }
                            this.setMeasuredDimension(this.qr.widthPixels, this.qr.heightPixels);
                            return;
                        }
                        n = Integer.MAX_VALUE;
                        break Label_0283;
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
        if (this.sX != null) {
            this.sX.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setContext(final Context baseContext) {
        this.wI.setBaseContext(baseContext);
    }
    
    public void x(final boolean wk) {
        synchronized (this.mw) {
            this.wK = wk;
            this.dB();
        }
    }
}
