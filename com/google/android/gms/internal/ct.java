// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.text.TextUtils;
import android.webkit.WebViewClient;
import android.os.Looper;
import android.os.Handler;

public class ct implements Runnable
{
    private final int kr;
    private final int ks;
    protected final dz lC;
    private final Handler oT;
    private final long oU;
    private long oV;
    private ea.a oW;
    protected boolean oX;
    protected boolean oY;
    
    public ct(final ea.a a, final dz dz, final int n, final int n2) {
        this(a, dz, n, n2, 200L, 50L);
    }
    
    public ct(final ea.a ow, final dz lc, final int kr, final int ks, final long ou, final long ov) {
        this.oU = ou;
        this.oV = ov;
        this.oT = new Handler(Looper.getMainLooper());
        this.lC = lc;
        this.oW = ow;
        this.oX = false;
        this.oY = false;
        this.ks = ks;
        this.kr = kr;
    }
    
    public void a(final cz cz, final ee webViewClient) {
        this.lC.setWebViewClient((WebViewClient)webViewClient);
        final dz lc = this.lC;
        String r;
        if (TextUtils.isEmpty((CharSequence)cz.ol)) {
            r = null;
        }
        else {
            r = dq.r(cz.ol);
        }
        lc.loadDataWithBaseURL(r, cz.pm, "text/html", "UTF-8", (String)null);
    }
    
    public void b(final cz cz) {
        this.a(cz, new ee(this, this.lC, cz.pv));
    }
    
    public void ba() {
        this.oT.postDelayed((Runnable)this, this.oU);
    }
    
    public void bb() {
        synchronized (this) {
            this.oX = true;
        }
    }
    
    public boolean bc() {
        synchronized (this) {
            return this.oX;
        }
    }
    
    public boolean bd() {
        return this.oY;
    }
    
    @Override
    public void run() {
        if (this.lC == null || this.bc()) {
            this.oW.a(this.lC);
            return;
        }
        new a(this.lC).execute((Object[])new Void[0]);
    }
    
    protected final class a extends AsyncTask<Void, Void, Boolean>
    {
        private final WebView oZ;
        private Bitmap pa;
        
        public a(final WebView oz) {
            this.oZ = oz;
        }
        
        protected Boolean a(final Void... array) {
            while (true) {
            Label_0052_Outer:
                while (true) {
                    int n = 0;
                Label_0139:
                    while (true) {
                        int n3 = 0;
                        int n4 = 0;
                        Label_0126: {
                            synchronized (this) {
                                final int width = this.pa.getWidth();
                                final int height = this.pa.getHeight();
                                Boolean b;
                                if (width == 0 || height == 0) {
                                    b = false;
                                }
                                else {
                                    n = 0;
                                    final int n2 = 0;
                                    if (n < width) {
                                        n3 = 0;
                                        if (n3 >= height) {
                                            break Label_0139;
                                        }
                                        n4 = n2;
                                        if (this.pa.getPixel(n, n3) != 0) {
                                            n4 = n2 + 1;
                                        }
                                        break Label_0126;
                                    }
                                    else {
                                        b = (n2 / (width * height / 100.0) > 0.1);
                                    }
                                }
                                return b;
                            }
                        }
                        n3 += 10;
                        final int n2 = n4;
                        continue;
                    }
                    n += 10;
                    continue Label_0052_Outer;
                }
            }
        }
        
        protected void a(final Boolean b) {
            --ct.this.oV;
            if (b || ct.this.bc() || ct.this.oV <= 0L) {
                ct.this.oY = b;
                ct.this.oW.a(ct.this.lC);
            }
            else if (ct.this.oV > 0L) {
                if (dw.n(2)) {
                    dw.v("Ad not detected, scheduling another run.");
                }
                ct.this.oT.postDelayed((Runnable)ct.this, ct.this.oU);
            }
        }
        
        protected void onPreExecute() {
            synchronized (this) {
                this.pa = Bitmap.createBitmap(ct.this.kr, ct.this.ks, Bitmap$Config.ARGB_8888);
                this.oZ.setVisibility(0);
                this.oZ.measure(View$MeasureSpec.makeMeasureSpec(ct.this.kr, 0), View$MeasureSpec.makeMeasureSpec(ct.this.ks, 0));
                this.oZ.layout(0, 0, ct.this.kr, ct.this.ks);
                this.oZ.draw(new Canvas(this.pa));
                this.oZ.invalidate();
            }
        }
    }
}
