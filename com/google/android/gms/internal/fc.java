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

@ez
public class fc implements Runnable
{
    private final int lf;
    private final int lg;
    protected final gv md;
    private final Handler td;
    private final long te;
    private long tf;
    private gw.a tg;
    protected boolean th;
    protected boolean ti;
    
    public fc(final gw.a a, final gv gv, final int n, final int n2) {
        this(a, gv, n, n2, 200L, 50L);
    }
    
    public fc(final gw.a tg, final gv md, final int lf, final int lg, final long te, final long tf) {
        this.te = te;
        this.tf = tf;
        this.td = new Handler(Looper.getMainLooper());
        this.md = md;
        this.tg = tg;
        this.th = false;
        this.ti = false;
        this.lg = lg;
        this.lf = lf;
    }
    
    public void a(final fk fk, final ha webViewClient) {
        this.md.setWebViewClient((WebViewClient)webViewClient);
        final gv md = this.md;
        String l;
        if (TextUtils.isEmpty((CharSequence)fk.rP)) {
            l = null;
        }
        else {
            l = gj.L(fk.rP);
        }
        md.loadDataWithBaseURL(l, fk.tG, "text/html", "UTF-8", (String)null);
    }
    
    public void b(final fk fk) {
        this.a(fk, new ha(this, this.md, fk.tP));
    }
    
    public void cA() {
        synchronized (this) {
            this.th = true;
        }
    }
    
    public boolean cB() {
        synchronized (this) {
            return this.th;
        }
    }
    
    public boolean cC() {
        return this.ti;
    }
    
    public void cz() {
        this.td.postDelayed((Runnable)this, this.te);
    }
    
    @Override
    public void run() {
        if (this.md == null || this.cB()) {
            this.tg.a(this.md);
            return;
        }
        new a(this.md).execute((Object[])new Void[0]);
    }
    
    protected final class a extends AsyncTask<Void, Void, Boolean>
    {
        private final WebView tj;
        private Bitmap tk;
        
        public a(final WebView tj) {
            this.tj = tj;
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
                                final int width = this.tk.getWidth();
                                final int height = this.tk.getHeight();
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
                                        if (this.tk.getPixel(n, n3) != 0) {
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
            --fc.this.tf;
            if (b || fc.this.cB() || fc.this.tf <= 0L) {
                fc.this.ti = b;
                fc.this.tg.a(fc.this.md);
            }
            else if (fc.this.tf > 0L) {
                if (gs.u(2)) {
                    gs.S("Ad not detected, scheduling another run.");
                }
                fc.this.td.postDelayed((Runnable)fc.this, fc.this.te);
            }
        }
        
        protected void onPreExecute() {
            synchronized (this) {
                this.tk = Bitmap.createBitmap(fc.this.lf, fc.this.lg, Bitmap$Config.ARGB_8888);
                this.tj.setVisibility(0);
                this.tj.measure(View$MeasureSpec.makeMeasureSpec(fc.this.lf, 0), View$MeasureSpec.makeMeasureSpec(fc.this.lg, 0));
                this.tj.layout(0, 0, fc.this.lf, fc.this.lg);
                this.tj.draw(new Canvas(this.tk));
                this.tj.invalidate();
            }
        }
    }
}
