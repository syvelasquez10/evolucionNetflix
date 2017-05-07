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
import android.view.Display;
import android.util.DisplayMetrics;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Map;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.os.Build$VERSION;
import android.content.Context;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;

public class dz extends WebView implements DownloadListener
{
    private final Object li;
    private final WindowManager ls;
    private ak nq;
    private final dx nr;
    private final l oJ;
    private final ea ru;
    private final a rv;
    private cc rw;
    private boolean rx;
    private boolean ry;
    
    private dz(final a rv, final ak nq, final boolean rx, final boolean b, final l oj, final dx nr) {
        super((Context)rv);
        this.li = new Object();
        this.rv = rv;
        this.nq = nq;
        this.rx = rx;
        this.oJ = oj;
        this.nr = nr;
        this.ls = (WindowManager)this.getContext().getSystemService("window");
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        dq.a((Context)rv, nr.rq, settings);
        if (Build$VERSION.SDK_INT >= 17) {
            dt.a(this.getContext(), settings);
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            ds.a(this.getContext(), settings);
        }
        this.setDownloadListener((DownloadListener)this);
        if (Build$VERSION.SDK_INT >= 11) {
            this.ru = new ec(this, b);
        }
        else {
            this.ru = new ea(this, b);
        }
        this.setWebViewClient((WebViewClient)this.ru);
        if (Build$VERSION.SDK_INT >= 14) {
            this.setWebChromeClient((WebChromeClient)new ed(this));
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            this.setWebChromeClient((WebChromeClient)new eb(this));
        }
        this.bM();
    }
    
    public static dz a(final Context context, final ak ak, final boolean b, final boolean b2, final l l, final dx dx) {
        return new dz(new a(context), ak, b, b2, l, dx);
    }
    
    private void bM() {
        while (true) {
            synchronized (this.li) {
                if (this.rx || this.nq.lT) {
                    if (Build$VERSION.SDK_INT < 14) {
                        dw.v("Disabling hardware acceleration on an overlay.");
                        this.bN();
                    }
                    else {
                        dw.v("Enabling hardware acceleration on an overlay.");
                        this.bO();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                dw.v("Disabling hardware acceleration on an AdView.");
                this.bN();
                return;
            }
            dw.v("Enabling hardware acceleration on an AdView.");
            this.bO();
        }
    }
    
    private void bN() {
        synchronized (this.li) {
            if (!this.ry && Build$VERSION.SDK_INT >= 11) {
                ds.d((View)this);
            }
            this.ry = true;
        }
    }
    
    private void bO() {
        synchronized (this.li) {
            if (this.ry && Build$VERSION.SDK_INT >= 11) {
                ds.e((View)this);
            }
            this.ry = false;
        }
    }
    
    public ak R() {
        synchronized (this.li) {
            return this.nq;
        }
    }
    
    public void a(final Context baseContext, final ak nq) {
        synchronized (this.li) {
            this.rv.setBaseContext(baseContext);
            this.rw = null;
            this.nq = nq;
            this.rx = false;
            dq.b(this);
            this.loadUrl("about:blank");
            this.ru.reset();
        }
    }
    
    public void a(final ak nq) {
        synchronized (this.li) {
            this.nq = nq;
            this.requestLayout();
        }
    }
    
    public void a(final cc rw) {
        synchronized (this.li) {
            this.rw = rw;
        }
    }
    
    public void a(final String s, final Map<String, ?> map) {
        try {
            this.b(s, dq.p(map));
        }
        catch (JSONException ex) {
            dw.z("Could not convert parameters to JSON.");
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
        this.loadUrl(sb.toString());
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
        dw.y("Dispatching AFMA event: " + (Object)sb);
        this.loadUrl(sb.toString());
    }
    
    public void bE() {
        if (!this.bI().bP()) {
            return;
        }
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        final Display defaultDisplay = this.ls.getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        try {
            this.b("onScreenInfoChanged", new JSONObject().put("width", displayMetrics.widthPixels).put("height", displayMetrics.heightPixels).put("density", (double)displayMetrics.density).put("rotation", defaultDisplay.getRotation()));
        }
        catch (JSONException ex) {
            dw.b("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void bF() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.nr.rq);
        this.a("onhide", hashMap);
    }
    
    public void bG() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.nr.rq);
        this.a("onshow", hashMap);
    }
    
    public cc bH() {
        synchronized (this.li) {
            return this.rw;
        }
    }
    
    public ea bI() {
        return this.ru;
    }
    
    public l bJ() {
        return this.oJ;
    }
    
    public dx bK() {
        return this.nr;
    }
    
    public boolean bL() {
        synchronized (this.li) {
            return this.rx;
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            this.getContext().startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            dw.v("Couldn't find an Activity to view url/mimetype: " + s + " / " + s4);
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
                            synchronized (this.li) {
                                if (this.isInEditMode() || this.rx) {
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
                                Label_0188:
                                    while (true) {
                                        this.setVisibility(4);
                                        break Label_0188;
                                        dw.z("Not enough space to show ad. Needs " + this.nq.widthPixels + "x" + this.nq.heightPixels + " pixels, but only has " + size + "x" + size2 + " pixels.");
                                        continue Label_0102_Outer;
                                    }
                                    this.setMeasuredDimension(0, 0);
                                    return;
                                    continue Label_0080_Outer;
                                }
                            }
                            // iftrue(Label_0206:, this.nq.widthPixels <= n && this.nq.heightPixels <= n2)
                            Label_0206: {
                                if (this.getVisibility() != 8) {
                                    this.setVisibility(0);
                                }
                            }
                            this.setMeasuredDimension(this.nq.widthPixels, this.nq.heightPixels);
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
        if (this.oJ != null) {
            this.oJ.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void p(final boolean rx) {
        synchronized (this.li) {
            this.rx = rx;
            this.bM();
        }
    }
    
    public void setContext(final Context baseContext) {
        this.rv.setBaseContext(baseContext);
    }
    
    private static class a extends MutableContextWrapper
    {
        private Context lp;
        private Activity rz;
        
        public a(final Context baseContext) {
            super(baseContext);
            this.setBaseContext(baseContext);
        }
        
        public void setBaseContext(final Context context) {
            this.lp = context.getApplicationContext();
            Activity rz;
            if (context instanceof Activity) {
                rz = (Activity)context;
            }
            else {
                rz = null;
            }
            this.rz = rz;
            super.setBaseContext(this.lp);
        }
        
        public void startActivity(final Intent intent) {
            if (this.rz != null) {
                this.rz.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.lp.startActivity(intent);
        }
    }
}
